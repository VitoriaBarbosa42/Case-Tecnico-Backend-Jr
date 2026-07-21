package com.desafio.casebackend.error;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes unitários para ErrorResponse")
public class ErrorResponseTest {

    private static final int STATUS = 400;
    private static final String ERROR = "Bad Request";
    private static final String MESSAGE = "Invalid input";
    private static final String PATH = "/api/test";

    @Test
    @DisplayName("Deve criar ErrorResponse com construtor completo")
    void shouldCreateErrorResponseWithFullConstructor() {
        LocalDateTime timestamp = LocalDateTime.now();
        ErrorResponse response = new ErrorResponse(timestamp, STATUS, ERROR, MESSAGE, PATH);

        assertEquals(timestamp, response.getTimestamp());
        assertEquals(STATUS, response.getStatus());
        assertEquals(ERROR, response.getError());
        assertEquals(MESSAGE, response.getMessage());
        assertEquals(PATH, response.getPath());
    }

    @Test
    @DisplayName("Deve criar ErrorResponse com construtor simplificado")
    void shouldCreateErrorResponseWithSimplifiedConstructor() {
        ErrorResponse response = new ErrorResponse(STATUS, ERROR, MESSAGE, PATH);

        assertNotNull(response.getTimestamp());
        assertEquals(STATUS, response.getStatus());
        assertEquals(ERROR, response.getError());
        assertEquals(MESSAGE, response.getMessage());
        assertEquals(PATH, response.getPath());
    }

    @Test
    @DisplayName("Deve criar ErrorResponse com construtor padrão")
    void shouldCreateErrorResponseWithDefaultConstructor() {
        ErrorResponse response = new ErrorResponse();

        assertNotNull(response);
        assertNull(response.getTimestamp());
        assertEquals(0, response.getStatus());
        assertNull(response.getError());
        assertNull(response.getMessage());
        assertNull(response.getPath());
    }

    @Test
    @DisplayName("Deve definir e obter timestamp")
    void shouldSetAndGetTimestamp() {
        ErrorResponse response = new ErrorResponse();
        LocalDateTime timestamp = LocalDateTime.now();
        
        response.setTimestamp(timestamp);
        
        assertEquals(timestamp, response.getTimestamp());
    }

    @Test
    @DisplayName("Deve definir e obter status")
    void shouldSetAndGetStatus() {
        ErrorResponse response = new ErrorResponse();
        
        response.setStatus(STATUS);
        
        assertEquals(STATUS, response.getStatus());
    }

    @Test
    @DisplayName("Deve definir e obter error")
    void shouldSetAndGetError() {
        ErrorResponse response = new ErrorResponse();
        
        response.setError(ERROR);
        
        assertEquals(ERROR, response.getError());
    }

    @Test
    @DisplayName("Deve definir e obter message")
    void shouldSetAndGetMessage() {
        ErrorResponse response = new ErrorResponse();
        
        response.setMessage(MESSAGE);
        
        assertEquals(MESSAGE, response.getMessage());
    }

    @Test
    @DisplayName("Deve definir e obter path")
    void shouldSetAndGetPath() {
        ErrorResponse response = new ErrorResponse();
        
        response.setPath(PATH);
        
        assertEquals(PATH, response.getPath());
    }
}