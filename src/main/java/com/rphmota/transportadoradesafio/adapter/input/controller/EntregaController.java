package com.rphmota.transportadoradesafio.adapter.input.controller;

import com.rphmota.transportadoradesafio.application.service.entrega.EntregaService;
import com.rphmota.transportadoradesafio.domain.entity.Entrega;
import com.rphmota.transportadoradesafio.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/entregas")
public class EntregaController {

    private final EntregaService entregaService;

    @Autowired
    public EntregaController(EntregaService entregaService) {
        this.entregaService = entregaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entrega> getEntregaById(@PathVariable Long id) {
        try {
            Entrega entrega = entregaService.getEntregaById(id);
            return ResponseEntity.ok(entrega);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Object> criarEntrega(@RequestBody Entrega novaEntrega) {
        try {
            System.out.println("Dados recebidos: " + novaEntrega);

            Entrega entregaCriada = entregaService.criarEntrega(novaEntrega);
            return new ResponseEntity<>(entregaCriada, HttpStatus.CREATED);
        } catch (Exception e) {

            System.out.println("Erro ao criar entrega: " + e.getMessage());

            // Retornando menssagem mais especifica para o cliuente utilizando estrutura de hashmap para ficar
            // em forma de melhor leitura
            Map<String, String> errorDetails = new HashMap<>();
            errorDetails.put("error", "Falha ao criar entrega");
            errorDetails.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
        }
    }
}
