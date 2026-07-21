package com.desafio.casebackend.utils;

public class Validadores {

    public static String validaCep(String cep) {

        if (cep == null || cep.isBlank()) {
            throw new IllegalArgumentException("O CEP não pode ser nulo nem estar em branco");
        }

        String cepNoNull = cep.replaceAll("\\D", "");

        if (cepNoNull.length() != 8) {
            throw new IllegalArgumentException("O CEP deve conter 8 caracteres numericos");
        }
        return cep;
    }
}
