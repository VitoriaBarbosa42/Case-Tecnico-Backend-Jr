package com.desafio.casebackend.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class ValidadoresTest {


    @Test
    @DisplayName("")
    void test_RetornaCep_QuandoFormatoValido(){

        String cepValido = "05662020";
        String result = Validadores.validaCep(cepValido);
        Assertions.assertEquals(result, cepValido);
    }

    @Test
    @DisplayName("")
    void test_RetornaError_SeHoverMenosDeOitoCaracter(){
        String cepMenosDeOitoCaracter = "0566200";
        IllegalArgumentException excecaoCapturada = Assertions.assertThrows(
                IllegalArgumentException.class, () -> Validadores.validaCep(cepMenosDeOitoCaracter)
        );

        Assertions.assertEquals("O CEP deve conter 8 caracteres numericos", excecaoCapturada.getMessage());
    }

    @Test
    @DisplayName("")
    void test_RetornaError_SeHoverMaisDeOitoCaracter(){
        String cepMaisDeOitoCaracter = "0566204242240";
        IllegalArgumentException excecaoCapturada = Assertions.assertThrows(
                IllegalArgumentException.class, () -> Validadores.validaCep(cepMaisDeOitoCaracter)
        );

        Assertions.assertEquals("O CEP deve conter 8 caracteres numericos", excecaoCapturada.getMessage());
    }

    @Test
    @DisplayName("")
    void test_RetornaError_parametroNull(){
        NullPointerException excecaoCapturada = Assertions.assertThrows(
                NullPointerException.class, () -> Validadores.validaCep(null)
        );

        Assertions.assertEquals("O CEP não pode ser nulo nem estar em branco", excecaoCapturada.getMessage());
    }
}
