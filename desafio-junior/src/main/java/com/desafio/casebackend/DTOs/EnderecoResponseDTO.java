package com.desafio.casebackend.DTOs;


public record EnderecoResponseDTO(
        String cep,
        String logradouro,
        String complemento,
        String bairro,
        String localidade,
        String uf
) {
}
