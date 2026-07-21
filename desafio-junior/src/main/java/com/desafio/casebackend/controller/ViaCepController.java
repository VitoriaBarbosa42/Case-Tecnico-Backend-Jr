package com.desafio.casebackend.controller;


import com.desafio.casebackend.dtos.ViaCepResponseDTO;

import com.desafio.casebackend.service.ViaCepService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
    public ResponseEntity<ViaCepResponseDTO> getAddress(
            @NotBlank(message = "O CEP não pode estar em branco.")
            @Pattern(regexp = "\\d{5}-?\\d{3}", message = "CEP inválido. O formato deve ser XXXXX-XXX ou XXXXXXXX.")
            @PathVariable
            String cep){
        return ResponseEntity.ok(viaCepService.buscaPorCep(cep));
    }
}
