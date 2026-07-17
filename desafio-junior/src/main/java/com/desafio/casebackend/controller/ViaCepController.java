package com.desafio.casebackend.controller;


import com.desafio.casebackend.DTOs.EnderecoResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("endereco")
public class ViaCepController {

    @GetMapping("{cep}")
    public ResponseEntity<EnderecoResponseDTO> getAddress (@PathVariable String cep){



    }
}
