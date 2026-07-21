package com.desafio.casebackend.dtos;

import org.springframework.http.ResponseEntity;

public record ViaCepResponseDTO(
        String cep,
        String logradouro,
        String complemento,
        String bairro,
        String localidade
) {
    public ViaCepResponseDTO {
        if(logradouro != null && !logradouro.isEmpty()) {
            logradouro = logradouro.toLowerCase();
        } else {
            throw new IllegalArgumentException("CEP não encontrado");
        }
    }
}
