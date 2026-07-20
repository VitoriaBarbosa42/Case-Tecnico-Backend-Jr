package com.desafio.casebackend.controller;


import com.desafio.casebackend.DTOs.ViaCepResponseDTO;

import com.desafio.casebackend.service.ViaCepService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("/viacep")
@Validated
public class ViaCepController {

    private final ViaCepService viaCepService;

    public ViaCepController(ViaCepService viaCepService) {
        this.viaCepService = viaCepService;
    }

    @GetMapping("/")
    public ResponseEntity<ViaCepResponseDTO> cepNull(){

        return ResponseEntity.ok(viaCepService.buscaPorCep(null));
    }

    @GetMapping("/{cep}")
    public ResponseEntity<ViaCepResponseDTO> getAddress (
            @PathVariable
            String cep){

        return ResponseEntity.ok(viaCepService.buscaPorCep(cep));
    }
}
