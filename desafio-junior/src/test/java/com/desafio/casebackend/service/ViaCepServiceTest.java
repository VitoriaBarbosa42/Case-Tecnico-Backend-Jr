package com.desafio.casebackend.service;

import com.desafio.casebackend.dtos.ViaCepResponseDTO;
import com.desafio.casebackend.client.ViaCepClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes unitários para ViaCepService")
public class ViaCepServiceTest {

    private static final String CEP_VALIDO = "01001000";
    private static final String CEP_COM_HIFEN = "01001-000";
    private static final String CEP_NULO = null;
    private static final String CEP_VAZIO = "";
    private static final String CEP_INVALIDO = "123";

    @Mock
    private ViaCepClient client;

    @InjectMocks
    private ViaCepService viaCepService;

    @Test
    @DisplayName("Deve retornar endereço quando CEP válido e API responde com sucesso")
    void shouldReturnAddressWhenValidCepAndApiRespondsSuccessfully() {
        ViaCepResponseDTO expectedResponse = new ViaCepResponseDTO(
                "01001-000",
                "praça da sé",
                "lado ímpar",
                "Sé",
                "São Paulo"
        );

        when(client.getEnderecoCep(CEP_VALIDO)).thenReturn(expectedResponse);

        ViaCepResponseDTO result = viaCepService.buscaPorCep(CEP_VALIDO);

        assertEquals(expectedResponse, result);
        verify(client, times(1)).getEnderecoCep(CEP_VALIDO);
    }

    @Test
    @DisplayName("Deve retornar endereço quando CEP com hífen e API responde com sucesso")
    void shouldReturnAddressWhenCepWithHyphenAndApiRespondsSuccessfully() {
        ViaCepResponseDTO expectedResponse = new ViaCepResponseDTO(
                "01001-000",
                "praça da sé",
                "lado ímpar",
                "Sé",
                "São Paulo"
        );

        when(client.getEnderecoCep(CEP_COM_HIFEN)).thenReturn(expectedResponse);

        ViaCepResponseDTO result = viaCepService.buscaPorCep(CEP_COM_HIFEN);

        assertEquals(expectedResponse, result);
        verify(client, times(1)).getEnderecoCep(CEP_COM_HIFEN);
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException quando CEP é nulo")
    void shouldThrowExceptionWhenCepIsNull() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> viaCepService.buscaPorCep(CEP_NULO)
        );

        assertEquals("O CEP não pode ser nulo nem estar em branco", exception.getMessage());
        verify(client, never()).getEnderecoCep(anyString());
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException quando CEP está vazio")
    void shouldThrowExceptionWhenCepIsEmpty() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> viaCepService.buscaPorCep(CEP_VAZIO)
        );

        assertEquals("O CEP não pode ser nulo nem estar em branco", exception.getMessage());
        verify(client, never()).getEnderecoCep(anyString());
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException quando CEP tem formato inválido")
    void shouldThrowExceptionWhenCepHasInvalidFormat() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> viaCepService.buscaPorCep(CEP_INVALIDO)
        );

        assertEquals("O CEP deve conter 8 caracteres numericos", exception.getMessage());
        verify(client, never()).getEnderecoCep(anyString());
    }

    @Test
    @DisplayName("Deve propagar exceção quando client lança exceção")
    void shouldPropagateExceptionWhenClientThrowsException() {
        when(client.getEnderecoCep(CEP_VALIDO)).thenThrow(new RuntimeException("Erro de comunicação"));

        assertThrows(
                RuntimeException.class,
                () -> viaCepService.buscaPorCep(CEP_VALIDO)
        );

        verify(client, times(1)).getEnderecoCep(CEP_VALIDO);
    }
}
