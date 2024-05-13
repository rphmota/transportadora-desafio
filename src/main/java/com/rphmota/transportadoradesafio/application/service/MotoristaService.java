package com.rphmota.transportadoradesafio.application.service;

import com.rphmota.transportadoradesafio.domain.dto.CriarMotoristaDTO;
import com.rphmota.transportadoradesafio.domain.entity.Caminhao;
import com.rphmota.transportadoradesafio.domain.entity.Motorista;
import com.rphmota.transportadoradesafio.domain.repository.CaminhaoRepository;
import com.rphmota.transportadoradesafio.domain.repository.EntregaRepository;
import com.rphmota.transportadoradesafio.domain.repository.MotoristaRepository;
import com.rphmota.transportadoradesafio.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MotoristaService {

    private final MotoristaRepository motoristaRepository;
    private final EntregaRepository entregaRepository;
    private final CaminhaoRepository caminhaoRepository;

    @Autowired
    public MotoristaService(MotoristaRepository motoristaRepository, EntregaRepository entregaRepository, CaminhaoRepository caminhaoRepository) {
        this.motoristaRepository = motoristaRepository;
        this.entregaRepository = entregaRepository;
        this.caminhaoRepository = caminhaoRepository;
    }

    public Motorista getMotoristaById(Long id) {
        return motoristaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Motorista não encontrado"));
    }

    public Motorista createMotorista(CriarMotoristaDTO motoristaDTO) {
        Motorista motorista = new Motorista();
        motorista.setNome(motoristaDTO.getNome());

        if (motoristaDTO.getCaminhaoId() != null) {
            Caminhao caminhao = caminhaoRepository.findById(motoristaDTO.getCaminhaoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Caminhão não encontrado com ID: " + motoristaDTO.getCaminhaoId()));
            motorista.setCaminhao(caminhao);
        }

        return motoristaRepository.save(motorista);
    }

    public Motorista updateMotorista(Long id, Motorista updatedMotorista) {
        return motoristaRepository.findById(id).map(motorista -> {
            motorista.setNome(updatedMotorista.getNome());
            if (updatedMotorista.getCaminhao() != null && !motorista.getCaminhao().equals(updatedMotorista.getCaminhao())) {
                if (motoristaRepository.existsByCaminhaoId(updatedMotorista.getCaminhao().getId())) {
                    throw new IllegalStateException("Caminhão já está atribuído a outro motorista");
                }
                motorista.setCaminhao(updatedMotorista.getCaminhao());
            }
            return motoristaRepository.save(motorista);
        }).orElseThrow(() -> new ResourceNotFoundException("Motorista não encontrado com ID: " + id));
    }

    public void deleteMotorista(Long id) {
        Motorista motorista = motoristaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Motorista não encontrado com ID: " + id));
        motoristaRepository.delete(motorista);
    }

    public List<Motorista> findAll() {
        return motoristaRepository.findAll();
    }


    public void validarLimiteEntregasMotorista(Motorista motorista, LocalDate dataEntrega) throws Exception {
        int monthValue = dataEntrega.getMonthValue(); // Gets the month as an integer
        int yearValue = dataEntrega.getYear();

        long entregasMotorista = entregaRepository.countEntregasByMotoristaAndMonth(motorista.getId(), monthValue, yearValue);
        if (entregasMotorista >= 2) {
            throw new Exception("O motorista já atingiu o limite de 2 entregas neste mês.");
        }
    }

}
