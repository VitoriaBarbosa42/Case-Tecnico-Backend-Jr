package com.desafio.casebackend.dtos;

import java.time.LocalDateTime;

public record ExceptionsDTO(
        LocalDateTime timestamp,
        int status, String error,
        String message,
        String path) {
}
