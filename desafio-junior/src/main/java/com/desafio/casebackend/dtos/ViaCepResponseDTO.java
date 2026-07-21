package com.desafio.casebackend.DTOs;

public record ViaCepResponseDTO(
        String cep,
        String logradouro,
        String complemento,
        String bairro,
        String localidade
) {
}
