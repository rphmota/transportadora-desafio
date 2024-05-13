package com.rphmota.transportadoradesafio.application.service;

import com.rphmota.transportadoradesafio.domain.entity.Carga;
import com.rphmota.transportadoradesafio.domain.repository.CargaRepository;
import com.rphmota.transportadoradesafio.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CargaService {

    private final CargaRepository cargaRepository;

    @Autowired
    public CargaService(CargaRepository cargaRepository) {
        this.cargaRepository = cargaRepository;
    }

    public Carga getCargaById(Long id) {
        return cargaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Carga não encontrada com ID: " + id));
    }

    public Carga createCarga(Carga carga) {
        return cargaRepository.save(carga);
    }

    public Carga updateCarga(Long id, Carga updatedCarga) {
        return cargaRepository.findById(id).map(carga -> {
            carga.setTipo(updatedCarga.getTipo());
            carga.setDescricao(updatedCarga.getDescricao());
            return cargaRepository.save(carga);
        }).orElseThrow(() -> new ResourceNotFoundException("Carga não encontrada com ID: " + id));
    }

    public void deleteCarga(Long id) {
        Carga carga = cargaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Carga não encontrada com ID: " + id));
        cargaRepository.delete(carga);
    }

    public List<Carga> findAllCargas() {
        return cargaRepository.findAll();
    }
}
