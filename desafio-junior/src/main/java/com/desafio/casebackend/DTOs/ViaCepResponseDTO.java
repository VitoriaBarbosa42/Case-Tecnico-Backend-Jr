package com.desafio.casebackend.DTOs;

import lombok.Builder;

@Builder
public record ViaCepResponseDTO(

        String cep,
        String logradouro,
        String complemento,
        String bairro,
        String localidade
) {
}
