package com.desafio.casebackend.Utils;

public class Validadores {

    public static String validaCep(String cep) {

        if (cep.isBlank() || cep == null) {
            throw new IllegalArgumentException("O CEP não pode ser nulo nem estar em branco ");
        }

        String cepNoNull = cep.replaceAll("\\D", "");

        if (cepNoNull.length() != 8) {
            throw new IllegalArgumentException("O CEP deve conter 8 caracteres numericos");
        }
        return cep;
    }
}
