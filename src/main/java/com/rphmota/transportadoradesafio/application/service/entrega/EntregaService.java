package com.rphmota.transportadoradesafio.application.service.entrega;

import com.rphmota.transportadoradesafio.application.service.CaminhaoService;
import com.rphmota.transportadoradesafio.application.service.MotoristaService;
import com.rphmota.transportadoradesafio.domain.entity.Caminhao;
import com.rphmota.transportadoradesafio.domain.entity.Carga;
import com.rphmota.transportadoradesafio.domain.entity.Entrega;
import com.rphmota.transportadoradesafio.domain.entity.Motorista;
import com.rphmota.transportadoradesafio.domain.repository.CargaRepository;
import com.rphmota.transportadoradesafio.domain.repository.EntregaRepository;
import com.rphmota.transportadoradesafio.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EntregaService {

    private final EntregaRepository entregaRepository;
    private final CargaRepository cargaRepository;
    private final ValidacaoEntregaService validacaoEntregaService;
    private final CaminhaoService caminhaoService;
    private final MotoristaService motoristaService;

    @Autowired
    public EntregaService(EntregaRepository entregaRepository, CargaRepository cargaRepository,
                          ValidacaoEntregaService validacaoEntregaService, CaminhaoService caminhaoService,
                          MotoristaService motoristaService) {
        this.entregaRepository = entregaRepository;
        this.cargaRepository = cargaRepository;
        this.validacaoEntregaService = validacaoEntregaService;
        this.caminhaoService = caminhaoService;
        this.motoristaService = motoristaService;
    }

    @Transactional(rollbackFor = Exception.class)
    public Entrega criarEntrega(Entrega novaEntrega) throws Exception {

        Caminhao caminhao = caminhaoService.getCaminhaoById(novaEntrega.getCaminhao().getId());
        Motorista motorista = motoristaService.getMotoristaById(novaEntrega.getMotorista().getId());
        Carga carga = cargaRepository.findById(novaEntrega.getCarga().getId())
                .orElseThrow(() -> new IllegalStateException("Carga com ID fornecido não encontrada"));



        novaEntrega.setCaminhao(caminhao);
        novaEntrega.setMotorista(motorista);
        novaEntrega.setCarga(carga);

        //Aqui eu forço que se for entrega de combustivel e perigosa
        if (novaEntrega.getCarga().getTipo().equals("Combustível")) {
            novaEntrega.setPerigosa(true);
        }

        validacaoEntregaService.validarEntrega(novaEntrega);

        return entregaRepository.save(novaEntrega);
    }

    public Entrega getEntregaById(Long id) throws ResourceNotFoundException {
        return entregaRepository.findById(id).map(entrega -> {
            entrega.calcularValorTotal();
            return entrega;
        }).orElseThrow(() -> new ResourceNotFoundException("Entrega não encontrada com ID: " + id));
    }
}
