package com.rphmota.transportadoradesafio.adapter.input.controller;

import com.rphmota.transportadoradesafio.application.service.MotoristaService;
import com.rphmota.transportadoradesafio.domain.entity.Motorista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/motoristas")
public class MotoristaController {

    private final MotoristaService motoristaService;

    @Autowired
    public MotoristaController(MotoristaService motoristaService) {
        this.motoristaService = motoristaService;
    }

    @GetMapping
    public ResponseEntity<List<Motorista>> getAllMotoristas() {
        List<Motorista> motoristas = motoristaService.findAll();
        return ResponseEntity.ok(motoristas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Motorista> getMotoristaById(@PathVariable Long id) {
        Motorista motorista = motoristaService.getMotoristaById(id);
        return ResponseEntity.ok(motorista);
    }

    @PostMapping
    public ResponseEntity<Motorista> createMotorista(@RequestBody Motorista motorista) {
        Motorista savedMotorista = motoristaService.createMotorista(motorista);
        return ResponseEntity.ok(savedMotorista);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Motorista> updateMotorista(@PathVariable Long id, @RequestBody Motorista updatedMotorista) {
        Motorista updatedMotoristaResponse = motoristaService.updateMotorista(id, updatedMotorista);
        return ResponseEntity.ok(updatedMotoristaResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMotorista(@PathVariable Long id) {
        motoristaService.deleteMotorista(id);
        return ResponseEntity.noContent().build();
    }
}
