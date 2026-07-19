package com.desafio.casebackend.service;

import com.desafio.casebackend.DTOs.ViaCepResponseDTO;
import com.desafio.casebackend.Utils.Validadores;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ViaCepService {

    private final ViaCepHttpClient client;

    public ViaCepResponseDTO buscaPorCep(String cepString) {

        return  client.clienteHttp(Validadores.validaCep(cepString));
    }
}


