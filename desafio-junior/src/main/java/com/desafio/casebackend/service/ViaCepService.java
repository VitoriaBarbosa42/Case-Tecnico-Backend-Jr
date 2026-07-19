package com.desafio.casebackend.service;

import com.desafio.casebackend.DTOs.ViaCepResponseDTO;
import com.desafio.casebackend.Utils.Validadores;
import org.springframework.stereotype.Service;

@Service
public class ViaCepService {

    ViaCepHttpClient client = new ViaCepHttpClient();

    public ViaCepResponseDTO buscaPorCep(String cepString) {

        return  client.clienteHttp(Validadores.validaCep(cepString));
    }
}


