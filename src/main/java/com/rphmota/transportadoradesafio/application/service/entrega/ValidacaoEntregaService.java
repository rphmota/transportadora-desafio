package com.rphmota.transportadoradesafio.application.service.entrega;

import com.rphmota.transportadoradesafio.application.service.CaminhaoService;
import com.rphmota.transportadoradesafio.application.service.MotoristaService;
import com.rphmota.transportadoradesafio.domain.entity.Caminhao;
import com.rphmota.transportadoradesafio.domain.entity.Entrega;
import com.rphmota.transportadoradesafio.domain.entity.Motorista;
import com.rphmota.transportadoradesafio.domain.repository.EntregaRepository;
import com.rphmota.transportadoradesafio.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ValidacaoEntregaService {

    private final EntregaRepository entregaRepository;
    private final CaminhaoService caminhaoService;
    private final MotoristaService motoristaService;

    @Autowired
    public ValidacaoEntregaService(EntregaRepository entregaRepository,
                                   CaminhaoService caminhaoService,
                                   MotoristaService motoristaService) {
        this.entregaRepository = entregaRepository;
        this.caminhaoService = caminhaoService;
        this.motoristaService = motoristaService;
    }

    //utilizo o varios metodos abaixo para aplicar as regras de negocio descritas no desafio
    public void validarEntrega(Entrega entrega) throws Exception {
        validarLimiteEntregasCaminhao(entrega.getCaminhao(), entrega.getDataEntrega());
        validarLimiteEntregasMotorista(entrega.getMotorista(), entrega.getDataEntrega());
        validarEntregaParaNordeste(entrega.getMotorista(), entrega.getDestino());
        validarSegurancaCarga(entrega);
        calcularTaxasRegionais(entrega);
    }

    private void validarSegurancaCarga(Entrega entrega) throws ValidationException {
        if ("Eletrônicos".equals(entrega.getCarga().getTipo()) && (entrega.isSegurada() == null)) {
            throw new ValidationException("Cargas eletrônicas devem conter a informação do seguro.");
        }
        if (entrega.getCarga().getTipo().equals("Combustível") && (entrega.isPerigosa() == null)) {
            throw new ValidationException("Cargas de combustível devem ser marcadas como perigosas.");
        }
    }


    private void calcularTaxasRegionais(Entrega entrega) {
        entrega.calcularValorTotal();
    }

    private void validarLimiteEntregasCaminhao(Caminhao caminhao, LocalDate dataEntrega) throws Exception {
        int monthValue = dataEntrega.getMonthValue();
        int yearValue = dataEntrega.getYear();

        long entregasCaminhao = entregaRepository.countEntregasByCaminhaoAndMonth(caminhao.getId(), monthValue, yearValue);
        if (entregasCaminhao >= 4) {
            throw new Exception("O caminhão já atingiu o limite de 4 entregas neste mês.");
        }
    }

    private void validarLimiteEntregasMotorista(Motorista motorista, LocalDate dataEntrega) throws Exception {
        int monthValue = dataEntrega.getMonthValue();
        int yearValue = dataEntrega.getYear();

        long entregasMotorista = entregaRepository.countEntregasByMotoristaAndMonth(motorista.getId(), monthValue, yearValue);
        if (entregasMotorista >= 2) {
            throw new Exception("O motorista já atingiu o limite de 2 entregas neste mês.");
        }
    }

    private void validarEntregaParaNordeste(Motorista motorista, String destino) throws Exception {
        if ("nordeste".equalsIgnoreCase(destino)) {
            long entregasParaNordeste = entregaRepository.countEntregasByMotoristaAndDestino(motorista.getId(), destino);
            if (entregasParaNordeste > 0) {
                throw new Exception("O motorista já realizou uma entrega para o Nordeste e não pode fazer outra.");
            }
        }
    }

}
