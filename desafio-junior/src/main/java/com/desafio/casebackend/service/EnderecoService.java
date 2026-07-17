package com.desafio.casebackend.service;

import com.desafio.casebackend.DTOs.EnderecoResponseDTO;
import com.desafio.casebackend.domain.EnderecoDomain;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class EnderecoService {

    private static final String viaCepUrl = "https://viacep.com.br/ws/";
    private static final Gson gson = new Gson();

    private static EnderecoDomain buscaCep(String cepString) {
        try {
            HttpClient httpClient = HttpClient.newBuilder()
                    .connectTimeout(Duration.ofSeconds(30))
                    .build();

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(viaCepUrl+cepString+"/json"))
                    .build();

            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            return gson.fromJson(httpResponse.body(), EnderecoDomain.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }






    }


}
