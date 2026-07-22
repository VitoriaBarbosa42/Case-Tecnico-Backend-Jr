package com.desafio.casebackend.dtos;

import org.springframework.http.ResponseEntity;

public record ViaCepResponseDTO(
        String cep,
        String logradouro,
        String complemento,
        String bairro,
        String localidade,
        Boolean erro
) {
    public ViaCepResponseDTO {
        if(logradouro != null) {
            logradouro = logradouro.toLowerCase();
        }
    }

    public ViaCepResponseDTO(String cep, String logradouro, String complemento, String bairro, String localidade) {
        this(cep, logradouro, complemento, bairro, localidade, false);
    }
}
