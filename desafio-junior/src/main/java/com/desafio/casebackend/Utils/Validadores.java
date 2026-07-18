package com.desafio.casebackend.Utils;

public class Validadores {

    public String validaCep(String cep){
        if(cep.length() != 8){
            throw new IllegalArgumentException("O CEP deve conter 8 caracteres");
        } else {
            for(int i = 0 ; i < cep.length() ; i ++ ) {
                char caracter = cep.charAt(i);
                if(Character.isLetter(caracter)){
                    throw new IllegalArgumentException("O CEP deve conter apenas numeros");
                }
            }
            return cep;
        }
    }
}
