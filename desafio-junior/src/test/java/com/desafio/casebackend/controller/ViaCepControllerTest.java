package com.desafio.casebackend.controller;

import com.desafio.casebackend.dtos.ViaCepResponseDTO;
import com.desafio.casebackend.service.ViaCepService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes unitários para ViaCepController")
public class ViaCepControllerTest {

    @Mock
    private ViaCepService viaCepService;

    @InjectMocks
    private ViaCepController viaCepController;

    @Test
    @DisplayName("Deve retornar endereço quando CEP for válido")
    void shouldReturnAddressWhenCepIsValid() {
        String cep = "01001000";
        ViaCepResponseDTO expectedResponse = new ViaCepResponseDTO(
                "01001-000",
                "praça da sé",
                "lado ímpar",
                "Sé",
                "São Paulo"
        );

        when(viaCepService.buscaPorCep(cep)).thenReturn(expectedResponse);

        ResponseEntity<ViaCepResponseDTO> response = viaCepController.getAddress(cep);

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(expectedResponse, response.getBody());
        verify(viaCepService, times(1)).buscaPorCep(cep);
    }
}
