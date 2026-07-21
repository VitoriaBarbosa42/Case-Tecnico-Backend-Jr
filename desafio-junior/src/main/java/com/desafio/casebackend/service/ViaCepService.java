package com.desafio.casebackend.service;

import com.desafio.casebackend.DTOs.ViaCepResponseDTO;
import com.desafio.casebackend.utils.Validadores;
import com.desafio.casebackend.client.ViaCepClient;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class ViaCepService {

    private final ViaCepClient client;

    public ViaCepService(ViaCepClient client) {
        this.client = client;
    }

    public ViaCepResponseDTO buscaPorCep(String cepString) {

        ViaCepResponseDTO response =  client.getEnderecoCep(Validadores.validaCep(cepString));
        response.logradouro().toLowerCase(Locale.getDefault());

        return response;
    }
}


