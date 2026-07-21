package com.desafio.casebackend.dtos;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes unitários para ViaCepResponseDTO")
public class ViaCepResponseDTOTest {

    @Test
    @DisplayName("Deve converter logradouro para minúsculo quando fornecido")
    void shouldConvertLogradouroToLowerCaseWhenProvided() {
        ViaCepResponseDTO dto = new ViaCepResponseDTO(
                "01001-000",
                "PRAÇA DA SÉ",
                "Lado Ímpar",
                "Sé",
                "São Paulo"
        );

        assertEquals("praça da sé", dto.logradouro());
        assertEquals("01001-000", dto.cep());
        assertEquals("Lado Ímpar", dto.complemento());
        assertEquals("Sé", dto.bairro());
        assertEquals("São Paulo", dto.localidade());
    }

    @Test
    @DisplayName("Deve manter logradouro nulo quando for nulo")
    void shouldKeepLogradouroNullWhenNull() {
        ViaCepResponseDTO dto = new ViaCepResponseDTO(
                "01001-000",
                null,
                "Lado Ímpar",
                "Sé",
                "São Paulo"
        );

        assertNull(dto.logradouro());
    }

    @Test
    @DisplayName("Deve manter logradouro vazio quando for vazio")
    void shouldKeepLogradouroEmptyWhenEmpty() {
        ViaCepResponseDTO dto = new ViaCepResponseDTO(
                "01001-000",
                "",
                "Lado Ímpar",
                "Sé",
                "São Paulo"
        );

        assertEquals("", dto.logradouro());
    }
}
