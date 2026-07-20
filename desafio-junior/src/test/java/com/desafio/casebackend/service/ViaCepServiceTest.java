package com.desafio.casebackend.service;

import com.desafio.casebackend.DTOs.ViaCepResponseDTO;
import com.desafio.casebackend.client.ViaCepClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class ViaCepServiceTest {


    @Mock
    ViaCepClient client;

    @InjectMocks
    ViaCepService viaCepService;

    @Test
    void deveRetornarResponseEndereco_QuandoApiExternaResponderComSucesso() {

        ViaCepResponseDTO expect = new ViaCepResponseDTO(
                    "01001-000",
                    "Praça da Sé",
                    "lado ímpar",
                    "Sé",
                    "São Paulo"
                );

        Mockito.when(client.getEnderecoCep("01001000")).thenReturn(expect);

        Assertions.assertEquals(expect, viaCepService.buscaPorCep("01001000"));
    }
}

