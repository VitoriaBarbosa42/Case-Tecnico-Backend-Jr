package com.desafio.casebackend.controller;

import com.desafio.casebackend.dtos.ViaCepResponseDTO;
import com.desafio.casebackend.service.ViaCepService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ViaCepController.class)
@DisplayName("Testes unitários para ViaCepController com MockMvc")
public class ViaCepControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ViaCepService viaCepService;

    @Test
    @DisplayName("Deve retornar endereço quando CEP for válido e cadastrado")
    void shouldReturnAddressWhenCepIsValid() throws Exception {
        String cep = "01001000";
        ViaCepResponseDTO expectedResponse = new ViaCepResponseDTO(
                "01001-000",
                "praça da sé",
                "lado ímpar",
                "Sé",
                "São Paulo"
        );

        when(viaCepService.buscaPorCep(cep)).thenReturn(expectedResponse);

        mockMvc.perform(get("/viacep/{cep}", cep)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cep").value("01001-000"))
                .andExpect(jsonPath("$.logradouro").value("praça da sé"))
                .andExpect(jsonPath("$.localidade").value("São Paulo"));

        verify(viaCepService, times(1)).buscaPorCep(cep);
    }

    @Test
    @DisplayName("Deve retornar 400 Bad Request quando CEP estiver em formato inválido")
    void shouldReturnBadRequestWhenCepIsInvalid() throws Exception {
        String invalidCep = "123";

        mockMvc.perform(get("/viacep/{cep}", invalidCep)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Requisição Inválida"))
                .andExpect(jsonPath("$.message").value(org.hamcrest.Matchers.containsString("CEP inválido")));

        verifyNoInteractions(viaCepService);
    }
}
