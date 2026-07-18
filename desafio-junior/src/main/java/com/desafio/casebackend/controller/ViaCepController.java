package com.desafio.casebackend.controller;


import com.desafio.casebackend.DTOs.ViaCepResponseDTO;

import com.desafio.casebackend.service.ViaCepService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("endereco")
public class ViaCepController {

    private final ViaCepService viaCepService;

    public ViaCepController(ViaCepService viaCepService) {
        this.viaCepService = viaCepService;
    }


    @GetMapping("{cep}")
    public ViaCepResponseDTO getAddress (@PathVariable String cep){

        ViaCepResponseDTO viaCepResponseDTO = viaCepService.buscaCep(cep);

        return viaCepResponseDTO;
    }
}
