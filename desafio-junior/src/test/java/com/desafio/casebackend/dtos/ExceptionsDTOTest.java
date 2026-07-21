package com.desafio.casebackend.dtos;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes unitários para ExceptionsDTO")
public class ExceptionsDTOTest {

    private static final int STATUS = 400;
    private static final String ERROR = "Bad Request";
    private static final String MESSAGE = "Invalid input";
    private static final String PATH = "/api/test";

    @Test
    @DisplayName("Deve criar ExceptionsDTO com todos os campos e permitir leitura correta")
    void shouldCreateExceptionsDtoWithAllFieldsAndAllowReading() {
        LocalDateTime timestamp = LocalDateTime.now();
        ExceptionsDTO dto = new ExceptionsDTO(timestamp, STATUS, ERROR, MESSAGE, PATH);

        assertEquals(timestamp, dto.timestamp());
        assertEquals(STATUS, dto.status());
        assertEquals(ERROR, dto.error());
        assertEquals(MESSAGE, dto.message());
        assertEquals(PATH, dto.path());
    }
}
