package com.desafio.casebackend.Utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class ValidadoresTest {

    private Validadores validadores;

    @BeforeEach
    void setValidadores(){
        validadores = new Validadores();
    }

//    @Test
//    @DisplayName("")
//    void test_RetornaCep_QuandoFormatoValido(){
//
//        String cepValido = "05662020";
//        String result = validadores.validaCep(cepValido);
//        Assertions.assertEquals(result, cepValido);
//    }

    @Test
    @DisplayName("")
    void test_RetornaError_SeHoverMenosDeOitoCaracter(){
        String cepMenosDeOitoCaracter = "0566200";
        IllegalArgumentException excecaoCapturada = Assertions.assertThrows(
                IllegalArgumentException.class, () -> validadores.validaCep(cepMenosDeOitoCaracter)
        );

        Assertions.assertEquals("O CEP deve conter 8 caracteres", excecaoCapturada.getMessage());
    }

    @Test
    @DisplayName("")
    void test_RetornaError_SeHoverMaisDeOitoCaracter(){
        String cepMaisDeOitoCaracter = "0566204242240";
        IllegalArgumentException excecaoCapturada = Assertions.assertThrows(
                IllegalArgumentException.class, () -> validadores.validaCep(cepMaisDeOitoCaracter)
        );

        Assertions.assertEquals("O CEP deve conter 8 caracteres", excecaoCapturada.getMessage());
    }

    @Test
    @DisplayName("")
    void test_RetornaError_SeDigitaLetra(){
        String cepComLetra = "056620b0";
        IllegalArgumentException excecaoCapturada = Assertions.assertThrows(
                IllegalArgumentException.class, () -> validadores.validaCep(cepComLetra)
        );

        Assertions.assertEquals("O CEP deve conter apenas numeros", excecaoCapturada.getMessage());
    }

    @Test
    @DisplayName("")
    void test_RetornaError_parametro_null(){
        NullPointerException excecaoCapturada = Assertions.assertThrows(
                NullPointerException.class, () -> validadores.validaCep(null)
        );

        Assertions.assertEquals("CEP não pode ser nulo", excecaoCapturada.getMessage());
    }
}
