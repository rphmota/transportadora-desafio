package com.rphmota.transportadoradesafio.adapter.input.controller;

import com.rphmota.transportadoradesafio.application.service.CargaService;
import com.rphmota.transportadoradesafio.domain.entity.Carga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cargas")
public class CargaController {

    private final CargaService cargaService;

    @Autowired
    public CargaController(CargaService cargaService) {
        this.cargaService = cargaService;
    }

    @GetMapping
    public ResponseEntity<List<Carga>> getAllCargas() {
        List<Carga> cargas = cargaService.findAllCargas();
        return ResponseEntity.ok(cargas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carga> getCargaById(@PathVariable Long id) {
        Carga carga = cargaService.getCargaById(id);
        return ResponseEntity.ok(carga);
    }

    @PostMapping
    public ResponseEntity<Carga> createCarga(@RequestBody Carga carga) {
        Carga savedCarga = cargaService.createCarga(carga);
        return ResponseEntity.ok(savedCarga);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carga> updateCarga(@PathVariable Long id, @RequestBody Carga updatedCarga) {
        Carga updatedCargaResponse = cargaService.updateCarga(id, updatedCarga);
        return ResponseEntity.ok(updatedCargaResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCarga(@PathVariable Long id) {
        cargaService.deleteCarga(id);
        return ResponseEntity.noContent().build();
    }
}
