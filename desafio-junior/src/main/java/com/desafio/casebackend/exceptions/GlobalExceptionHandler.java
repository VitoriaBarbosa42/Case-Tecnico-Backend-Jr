package com.desafio.casebackend.exceptions;

import com.desafio.casebackend.dtos.ExceptionsDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionsDTO> handleIllegalArgument(IllegalArgumentException ex, HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ExceptionsDTO error = new ExceptionsDTO(
                java.time.LocalDateTime.now(),
                status.value(),
                "Requisição Inválida",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler({NullPointerException.class})
    public ResponseEntity<ExceptionsDTO> handleIllegalArgument(NullPointerException ex, HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ExceptionsDTO error = new ExceptionsDTO(
                java.time.LocalDateTime.now(),
                status.value(),
                "Cep invalido",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ExceptionsDTO> handleNoResourceFoundException(NoResourceFoundException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        ExceptionsDTO error = new ExceptionsDTO(
                LocalDateTime.now(),
                status.value(),
                "O CEP não pode estar em branco",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(feign.FeignException.class)
    public ResponseEntity<ExceptionsDTO> handleFeignException(feign.FeignException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_GATEWAY;

        ExceptionsDTO error = new ExceptionsDTO(
                LocalDateTime.now(),
                status.value(),
                "Erro na integração com API externa",
                "Falha na comunicação com a API ViaCEP: " + ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(jakarta.validation.ConstraintViolationException.class)
    public ResponseEntity<ExceptionsDTO> handleConstraintViolationException(jakarta.validation.ConstraintViolationException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ExceptionsDTO error = new ExceptionsDTO(
                LocalDateTime.now(),
                status.value(),
                "Requisição Inválida",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(error);
    }

}
