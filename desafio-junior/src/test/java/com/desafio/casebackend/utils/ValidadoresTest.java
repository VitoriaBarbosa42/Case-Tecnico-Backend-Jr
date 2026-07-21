package com.desafio.casebackend.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes unitários para Validadores")
public class ValidadoresTest {

    private static final String CEP_VALIDO = "05662020";
    private static final String CEP_COM_HIFEN = "05662-020";
    private static final String CEP_LETRAS = "05662ABC";
    private static final String CEP_MENOS_OITO = "0566200";
    private static final String CEP_MAIS_OITO = "0566204242240";
    private static final String CEP_VAZIO = "";
    private static final String CEP_BRANCO = "   ";
    private static final String MENSAGEM_CEP_INVALIDO = "O CEP deve conter 8 caracteres numericos";
    private static final String MENSAGEM_CEP_NULO = "O CEP não pode ser nulo nem estar em branco";

    @Test
    @DisplayName("Deve retornar CEP quando formato válido")
    void shouldReturnCepWhenValidFormat() {
        String resultado = Validadores.validaCep(CEP_VALIDO);
        assertEquals(CEP_VALIDO, resultado);
    }

    @Test
    @DisplayName("Deve retornar CEP original quando com hífen")
    void shouldReturnOriginalCepWhenWithHyphen() {
        String resultado = Validadores.validaCep(CEP_COM_HIFEN);
        assertEquals(CEP_COM_HIFEN, resultado);
    }

    @Test
    @DisplayName("Deve lançar exceção quando CEP tem letras resultando em menos de 8 dígitos")
    void shouldThrowExceptionWhenCepWithLettersResultsInLessThanEightDigits() {
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> Validadores.validaCep(CEP_LETRAS)
        );
        assertEquals(MENSAGEM_CEP_INVALIDO, excecao.getMessage());
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException quando CEP tem menos de 8 caracteres")
    void shouldThrowExceptionWhenCepHasLessThanEightCharacters() {
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> Validadores.validaCep(CEP_MENOS_OITO)
        );
        assertEquals(MENSAGEM_CEP_INVALIDO, excecao.getMessage());
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException quando CEP tem mais de 8 caracteres")
    void shouldThrowExceptionWhenCepHasMoreThanEightCharacters() {
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> Validadores.validaCep(CEP_MAIS_OITO)
        );
        assertEquals(MENSAGEM_CEP_INVALIDO, excecao.getMessage());
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException quando CEP é nulo")
    void shouldThrowExceptionWhenCepIsNull() {
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> Validadores.validaCep(null)
        );
        assertEquals(MENSAGEM_CEP_NULO, excecao.getMessage());
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException quando CEP está vazio")
    void shouldThrowExceptionWhenCepIsEmpty() {
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> Validadores.validaCep(CEP_VAZIO)
        );
        assertEquals(MENSAGEM_CEP_NULO, excecao.getMessage());
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException quando CEP está em branco")
    void shouldThrowExceptionWhenCepIsBlank() {
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> Validadores.validaCep(CEP_BRANCO)
        );
        assertEquals(MENSAGEM_CEP_NULO, excecao.getMessage());
    }
}
