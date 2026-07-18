package com.desafio.casebackend.service;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@WireMockTest(httpPort = 8089)
@SpringBootTest
public class ViaCepServiceTest {


    @Test
    void deveRetornarCepDto_QuandoApiExternaResponderComSucesso() {
        String cep = "01001000";
        String url = "https://viacep.com.br/ws/" + cep + "/json/";



    }



}
