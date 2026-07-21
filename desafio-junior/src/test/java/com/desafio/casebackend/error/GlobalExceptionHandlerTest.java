package com.desafio.casebackend.error;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
    @DisplayName("Deve handle IllegalArgumentException e retornar ResponseEntity com status 400")
    void shouldHandleIllegalArgumentExceptionAndReturnBadRequest() {
        IllegalArgumentException exception = new IllegalArgumentException(ERROR_MESSAGE);
        when(request.getRequestURI()).thenReturn(REQUEST_URI);

        var response = exceptionHandler.handleIllegalArgument(exception, request);

        assertNotNull(response);
        assertEquals(400, response.getStatusCode().value());
        
        ErrorResponse errorBody = response.getBody();
        assertNotNull(errorBody);
        assertEquals(400, errorBody.getStatus());
        assertEquals("Requisição Inválida", errorBody.getError());
        assertEquals(ERROR_MESSAGE, errorBody.getMessage());
        assertEquals(REQUEST_URI, errorBody.getPath());
        assertNotNull(errorBody.getTimestamp());
        
        verify(request, times(1)).getRequestURI();
    }

    @Test
    @DisplayName("Deve incluir timestamp atual no ErrorResponse")
    void shouldIncludeCurrentTimestampInErrorResponse() {
        IllegalArgumentException exception = new IllegalArgumentException(ERROR_MESSAGE);
        when(request.getRequestURI()).thenReturn(REQUEST_URI);

        var beforeTime = System.currentTimeMillis();
        var response = exceptionHandler.handleIllegalArgument(exception, request);
        var afterTime = System.currentTimeMillis();

        ErrorResponse errorBody = response.getBody();
        assertNotNull(errorBody.getTimestamp());
        
        var timestampMillis = errorBody.getTimestamp().atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli();
        assertTrue(timestampMillis >= beforeTime && timestampMillis <= afterTime);
    }

    @Test
    @DisplayName("Deve usar mensagem da exceção no ErrorResponse")
    void shouldUseExceptionMessageInErrorResponse() {
        String customMessage = "Custom error message";
        IllegalArgumentException exception = new IllegalArgumentException(customMessage);
        when(request.getRequestURI()).thenReturn(REQUEST_URI);

        var response = exceptionHandler.handleIllegalArgument(exception, request);

        assertEquals(customMessage, response.getBody().getMessage());
    }

    @Test
    @DisplayName("Deve usar URI da requisição no ErrorResponse")
    void shouldUseRequestUriInErrorResponse() {
        String customUri = "/api/custom/endpoint";
        IllegalArgumentException exception = new IllegalArgumentException(ERROR_MESSAGE);
        when(request.getRequestURI()).thenReturn(customUri);

        var response = exceptionHandler.handleIllegalArgument(exception, request);

        assertEquals(customUri, response.getBody().getPath());
    }
}