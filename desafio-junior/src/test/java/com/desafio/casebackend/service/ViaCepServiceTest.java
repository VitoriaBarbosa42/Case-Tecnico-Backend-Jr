package com.desafio.casebackend.service;

import com.desafio.casebackend.DTOs.ViaCepResponseDTO;
import com.desafio.casebackend.Utils.Validadores;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@WireMockTest(httpPort = 8089)
@SpringBootTest
public class ViaCepServiceTest {

    @InjectMocks
    ViaCepService viaCepService = new ViaCepService();

//    @Mock
//    ViaCepHttpClient client = new ViaCepHttpClient();

    @Test
    void deveRetornarResponseEndereco_QuandoApiExternaResponderComSucesso() {
        String cepValido = Validadores.validaCep("01001000");
        viaCepService = new ViaCepService();

        ViaCepResponseDTO result = viaCepService.buscaPorCep(cepValido);

        ViaCepResponseDTO meuDto = ViaCepResponseDTO.builder()
                .cep("01001-000")
                .logradouro("Praça da Sé")
                .complemento("lado ímpar")
                .bairro("Sé")
                .localidade("São Paulo")
                .build();
        Assertions.assertEquals(meuDto, result);
    }
}
