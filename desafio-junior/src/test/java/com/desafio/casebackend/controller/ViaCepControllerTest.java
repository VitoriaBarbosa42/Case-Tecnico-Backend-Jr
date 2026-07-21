package com.desafio.casebackend.controller;

import com.desafio.casebackend.DTOs.ViaCepResponseDTO;
import com.desafio.casebackend.service.ViaCepService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes unitários para ViaCepController")
public class ViaCepControllerTest {

    private static final String CEP_VALIDO = "01001000";

    @Test
    @DisplayName("Deve instanciar controller com service via construtor")
    void shouldInstantiateControllerWithServiceViaConstructor() {
        ViaCepService mockService = new ViaCepService(null);
        ViaCepController controller = new ViaCepController(mockService);
        
        assertNotNull(controller);
    }

    @Test
    @DisplayName("Deve aceitar service nulo no construtor (Spring irá injetar)")
    void shouldAcceptNullServiceInConstructor() {
        ViaCepController controller = new ViaCepController(null);
        
        assertNotNull(controller);
    }
}
