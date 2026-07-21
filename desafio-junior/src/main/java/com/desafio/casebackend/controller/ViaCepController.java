package com.desafio.casebackend.controller;


import com.desafio.casebackend.DTOs.ViaCepResponseDTO;

import com.desafio.casebackend.service.ViaCepService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/viacep")
@Validated
public class ViaCepController {

    private final ViaCepService viaCepService;

    public ViaCepController(ViaCepService viaCepService) {
        this.viaCepService = viaCepService;
    }

    @GetMapping("/{cep}")
    public ResponseEntity<ViaCepResponseDTO> getAddress (
            @PathVariable
            String cep){
        return ResponseEntity.ok(viaCepService.buscaPorCep(cep));
    }
}
