package com.desafio.casebackend.service;

import com.desafio.casebackend.DTOs.ViaCepResponseDTO;
import com.desafio.casebackend.Utils.Validadores;
import com.desafio.casebackend.client.ViaCepClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class ViaCepService {

    private final ViaCepClient client;

    public ViaCepService(ViaCepClient client) {
        this.client = client;
    }

    public ViaCepResponseDTO buscaPorCep(String cepString) {

        return client.getEnderecoCep(Validadores.validaCep(cepString));
    }
}


