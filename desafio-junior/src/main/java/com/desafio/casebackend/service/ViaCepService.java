package com.desafio.casebackend.service;

import com.desafio.casebackend.DTOs.ViaCepResponseDTO;
import com.desafio.casebackend.Utils.Validadores;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Service
public class ViaCepService {

    private static final String viaCepUrl = "https://viacep.com.br/ws/";
    private static final Gson gson = new Gson();

    public ViaCepResponseDTO buscaCep(String cepString) {

        Validadores validate = new Validadores();
        validate.validaCep(cepString);

        try {
            HttpClient httpClient = HttpClient.newBuilder()
                    .connectTimeout(Duration.ofSeconds(30))
                    .build();

                HttpRequest httpRequest = HttpRequest.newBuilder()
                        .GET()
                        .uri(URI.create(viaCepUrl+cepString+"/json"))
                        .build();

            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            return gson.fromJson(httpResponse.body(), ViaCepResponseDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

}


