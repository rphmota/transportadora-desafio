package com.rphmota.transportadoradesafio.adapter.input.controller;

import com.rphmota.transportadoradesafio.application.service.MotoristaService;
import com.rphmota.transportadoradesafio.domain.dto.CriarMotoristaDTO;
import com.rphmota.transportadoradesafio.domain.entity.Motorista;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    public ResponseEntity<Void> createMotorista(@Valid @RequestBody CriarMotoristaDTO motoristaDTO) {
        System.out.println("poddd");
        Motorista savedMotorista = motoristaService.createMotorista(motoristaDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedMotorista.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
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
