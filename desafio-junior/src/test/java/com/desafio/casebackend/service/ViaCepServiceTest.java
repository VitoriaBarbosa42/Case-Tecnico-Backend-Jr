package com.desafio.casebackend.service;

import com.desafio.casebackend.DTOs.ViaCepResponseDTO;
import com.desafio.casebackend.Utils.Validadores;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ViaCepServiceTest {


    @Mock
    ViaCepHttpClient client;

    @InjectMocks
    ViaCepService viaCepService;

    @Test
    void deveRetornarResponseEndereco_QuandoApiExternaResponderComSucesso() {

        ViaCepResponseDTO expect = ViaCepResponseDTO.builder()
                .cep("01001-000")
                .logradouro("Praça da Sé")
                .complemento("lado ímpar")
                .bairro("Sé")
                .localidade("São Paulo")
                .build();

        Mockito.when(client.clienteHttp("01001000")).thenReturn(expect);

        ViaCepResponseDTO result = viaCepService.buscaPorCep("01001000");

        Assertions.assertEquals(expect, result);
    }
}
