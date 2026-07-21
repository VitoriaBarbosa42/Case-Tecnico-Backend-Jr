package com.desafio.casebackend.exceptions;

import com.desafio.casebackend.dtos.ExceptionsDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes unitários para GlobalExceptionHandler")
public class GlobalExceptionHandlerTest {

    private static final String ERROR_MESSAGE = "O CEP deve conter 8 caracteres numericos";
    private static final String REQUEST_URI = "/viacep/123";

    @Mock
    private HttpServletRequest request;

    private GlobalExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        exceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    @DisplayName("Deve tratar IllegalArgumentException e retornar ResponseEntity com status 400")
    void shouldHandleIllegalArgumentExceptionAndReturnBadRequest() {
        IllegalArgumentException exception = new IllegalArgumentException(ERROR_MESSAGE);
        when(request.getRequestURI()).thenReturn(REQUEST_URI);

        ResponseEntity<ExceptionsDTO> response = exceptionHandler.handleIllegalArgument(exception, request);

        assertNotNull(response);
        assertEquals(400, response.getStatusCode().value());
        
        ExceptionsDTO errorBody = response.getBody();
        assertNotNull(errorBody);
        assertEquals(400, errorBody.status());
        assertEquals("Requisição Inválida", errorBody.error());
        assertEquals(ERROR_MESSAGE, errorBody.message());
        assertEquals(REQUEST_URI, errorBody.path());
        assertNotNull(errorBody.timestamp());
        
        verify(request, times(1)).getRequestURI();
    }

    @Test
    @DisplayName("Deve incluir timestamp atual no ExceptionsDTO")
    void shouldIncludeCurrentTimestampInExceptionsDTO() {
        IllegalArgumentException exception = new IllegalArgumentException(ERROR_MESSAGE);
        when(request.getRequestURI()).thenReturn(REQUEST_URI);

        long beforeTime = System.currentTimeMillis();
        ResponseEntity<ExceptionsDTO> response = exceptionHandler.handleIllegalArgument(exception, request);
        long afterTime = System.currentTimeMillis();

        ExceptionsDTO errorBody = response.getBody();
        assertNotNull(errorBody);
        assertNotNull(errorBody.timestamp());
        
        long timestampMillis = errorBody.timestamp().atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli();
        assertTrue(timestampMillis >= beforeTime && timestampMillis <= afterTime);
    }

    @Test
    @DisplayName("Deve usar mensagem da exceção no ExceptionsDTO")
    void shouldUseExceptionMessageInExceptionsDTO() {
        String customMessage = "Custom error message";
        IllegalArgumentException exception = new IllegalArgumentException(customMessage);
        when(request.getRequestURI()).thenReturn(REQUEST_URI);

        ResponseEntity<ExceptionsDTO> response = exceptionHandler.handleIllegalArgument(exception, request);

        assertNotNull(response.getBody());
        assertEquals(customMessage, response.getBody().message());
    }

    @Test
    @DisplayName("Deve usar URI da requisição no ExceptionsDTO")
    void shouldUseRequestUriInExceptionsDTO() {
        String customUri = "/api/custom/endpoint";
        IllegalArgumentException exception = new IllegalArgumentException(ERROR_MESSAGE);
        when(request.getRequestURI()).thenReturn(customUri);

        ResponseEntity<ExceptionsDTO> response = exceptionHandler.handleIllegalArgument(exception, request);

        assertNotNull(response.getBody());
        assertEquals(customUri, response.getBody().path());
    }
}