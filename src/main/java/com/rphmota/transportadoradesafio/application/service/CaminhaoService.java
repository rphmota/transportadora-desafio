package com.rphmota.transportadoradesafio.application.service;

import com.rphmota.transportadoradesafio.domain.entity.Caminhao;
import com.rphmota.transportadoradesafio.domain.entity.Motorista;
import com.rphmota.transportadoradesafio.domain.repository.CaminhaoRepository;
import com.rphmota.transportadoradesafio.domain.repository.ClienteRepository;
import com.rphmota.transportadoradesafio.domain.repository.EntregaRepository;
import com.rphmota.transportadoradesafio.domain.repository.MotoristaRepository;
import com.rphmota.transportadoradesafio.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CaminhaoService {

    private final CaminhaoRepository caminhaoRepository;
    private final ClienteRepository clienteRepository;
    private final EntregaRepository entregaRepository;
    private final MotoristaRepository motoristaRepository;

    @Autowired
    public CaminhaoService(CaminhaoRepository caminhaoRepository, ClienteRepository clienteRepository, EntregaRepository entregaRepository,MotoristaRepository motoristaRepository) {
        this.caminhaoRepository = caminhaoRepository;
        this.clienteRepository = clienteRepository;
        this.entregaRepository = entregaRepository;
        this.motoristaRepository = motoristaRepository;

    }

    public Caminhao getCaminhaoById(Long id) {
        return caminhaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Caminhão não encontrado com ID: " + id));
    }

    public List<Caminhao> getAllCaminhoes() {
        return caminhaoRepository.findAll();
    }

    public Caminhao createCaminhao(Caminhao caminhao) {
        return caminhaoRepository.save(caminhao);
    }

    public Caminhao updateCaminhao(Long id, Caminhao updatedCaminhao) {
        return caminhaoRepository.findById(id).map(caminhao -> {
            caminhao.setPlaca(updatedCaminhao.getPlaca());
            caminhao.setCapacidade(updatedCaminhao.getCapacidade());
            if (updatedCaminhao.getCliente() != null && updatedCaminhao.getCliente().getId() != null) {
                clienteRepository.findById(updatedCaminhao.getCliente().getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com ID: " + updatedCaminhao.getCliente().getId()));
            }
            caminhao.setCliente(updatedCaminhao.getCliente());
            return caminhaoRepository.save(caminhao);
        }).orElseThrow(() -> new ResourceNotFoundException("Caminhão não encontrado com ID: " + id));
    }

    public void deleteCaminhao(Long id) {
        Caminhao caminhao = caminhaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Caminhão não encontrado com ID: " + id));
        caminhaoRepository.delete(caminhao);
    }

    public void validarLimiteEntregasCaminhao(Caminhao caminhao, LocalDate dataEntrega) throws Exception {
        int monthValue = dataEntrega.getMonthValue(); // Gets the month as an integer
        int yearValue = dataEntrega.getYear();

        long entregasCaminhao = entregaRepository.countEntregasByCaminhaoAndMonth(caminhao.getId(), monthValue, yearValue);
        if (entregasCaminhao >= 4) {
            throw new Exception("O caminhão já atingiu o limite de 4 entregas neste mês.");
        }
    }

    public Caminhao associateMotoristaToCaminhao(Long caminhaoId, Long motoristaId) {
        return caminhaoRepository.findById(caminhaoId).map(caminhao -> {
            // Aqui você deverá ter acesso ao repositório de motoristas também
            Motorista motorista = motoristaRepository.findById(motoristaId)
                    .orElseThrow(() -> new ResourceNotFoundException("Motorista não encontrado com ID: " + motoristaId));

            // Verificar se o caminhão já está associado a outro motorista
            if (caminhao.getMotorista() != null && caminhao.getMotorista().getId() != motoristaId) {
                throw new IllegalStateException("Caminhão já está associado a outro motorista");
            }

            caminhao.setMotorista(motorista);
            return caminhaoRepository.save(caminhao);
        }).orElseThrow(() -> new ResourceNotFoundException("Caminhão não encontrado com ID: " + caminhaoId));
    }
}
