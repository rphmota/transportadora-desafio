package com.rphmota.transportadoradesafio.adapter.input.controller;

import com.rphmota.transportadoradesafio.application.service.CaminhaoService;
import com.rphmota.transportadoradesafio.domain.entity.Caminhao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/caminhoes")
public class CaminhaoController {

    private final CaminhaoService caminhaoService;

    @Autowired
    public CaminhaoController(CaminhaoService caminhaoService) {
        this.caminhaoService = caminhaoService;
    }

    @GetMapping
    public ResponseEntity<List<Caminhao>> getAllCaminhoes() {
        List<Caminhao> caminhoes = caminhaoService.getAllCaminhoes();
        return ResponseEntity.ok(caminhoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Caminhao> getCaminhaoById(@PathVariable Long id) {
        Caminhao caminhao = caminhaoService.getCaminhaoById(id);
        return ResponseEntity.ok(caminhao);
    }

    @PostMapping
    public ResponseEntity<Caminhao> createCaminhao(@RequestBody Caminhao caminhao) {
        Caminhao savedCaminhao = caminhaoService.createCaminhao(caminhao);
        return ResponseEntity.ok(savedCaminhao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Caminhao> updateCaminhao(@PathVariable Long id, @RequestBody Caminhao caminhao) {
        Caminhao updatedCaminhao = caminhaoService.updateCaminhao(id, caminhao);
        return ResponseEntity.ok(updatedCaminhao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCaminhao(@PathVariable Long id) {
        caminhaoService.deleteCaminhao(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{caminhaoId}/associateMotorista/{motoristaId}")
    public ResponseEntity<Caminhao> associateMotoristaToCaminhao(@PathVariable Long caminhaoId, @PathVariable Long motoristaId) {
        try {
            Caminhao caminhao = caminhaoService.associateMotoristaToCaminhao(caminhaoId, motoristaId);
            return ResponseEntity.ok(caminhao);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
