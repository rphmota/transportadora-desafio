package com.rphmota.transportadoradesafio.domain.dto;

import com.rphmota.transportadoradesafio.domain.entity.Entrega;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EntregaDTO {

    private Long id;
    private String destino;
    private double valorBase;
    private double valorTotal;
    private LocalDate dataEntrega;
    private Boolean valiosa;
    private Boolean segurada;
    private Boolean perigosa;
    private Long caminhaoId;
    private Long motoristaId;
    private CargaDTO carga;


    public EntregaDTO(Entrega entrega) {
        this.id = entrega.getId();
        this.destino = entrega.getDestino();
        this.valorTotal = entrega.calcularValorTotal();
        this.valorBase = entrega.getValorBase();
        this.dataEntrega = entrega.getDataEntrega();
        this.valiosa = entrega.isValiosa();
        this.segurada = entrega.isSegurada();
        this.perigosa = entrega.isPerigosa();
        this.caminhaoId = entrega.getCaminhao() != null ? entrega.getCaminhao().getId() : null;
        this.motoristaId = entrega.getMotorista() != null ? entrega.getMotorista().getId() : null;
        this.carga = entrega.getCarga() != null ? new CargaDTO(entrega.getCarga()) : null;
    }

}
