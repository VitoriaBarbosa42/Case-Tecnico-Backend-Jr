package com.desafio.casebackend.service;

import com.desafio.casebackend.dtos.ViaCepResponseDTO;
import com.desafio.casebackend.client.ViaCepClient;
import org.springframework.stereotype.Service;

@Service
public class ViaCepService {

    private final ViaCepClient client;

    public ViaCepService(ViaCepClient client) {
        this.client = client;
    }

    public ViaCepResponseDTO buscaPorCep(String cep) {
        if (cep == null || cep.trim().isEmpty()) {
            throw new IllegalArgumentException("O CEP não pode ser nulo nem estar em branco");
        }

        String cleanCep = cep.replace("-", "").trim();
        if (!cleanCep.matches("\\d{8}")) {
            throw new IllegalArgumentException("O CEP deve conter 8 caracteres numericos");
        }

        ViaCepResponseDTO response = client.getEnderecoCep(cep);
        
        if (response == null || (response.erro() != null && response.erro())) {
            throw new IllegalArgumentException("CEP não encontrado");
        }
        
        return response;
    }
}
