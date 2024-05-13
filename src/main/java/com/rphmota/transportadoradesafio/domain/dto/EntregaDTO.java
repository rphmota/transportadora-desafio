package com.rphmota.transportadoradesafio.domain.dto;

import java.time.LocalDate;

public class EntregaDTO {

    private Long id;
    private String destino;
    private double valorBase;
    private LocalDate dataEntrega;
    private Boolean valiosa;
    private Boolean segurada;
    private Boolean perigosa;
    private Long caminhaoId;
    private Long motoristaId;
    private CargaDTO carga; // Supondo que CargaDTO é um DTO simplificado para a entidade Carga

    // Construtores, getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public double getValorBase() {
        return valorBase;
    }

    public void setValorBase(double valorBase) {
        this.valorBase = valorBase;
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Boolean isValiosa() {
        return valiosa;
    }

    public void setValiosa(Boolean valiosa) {
        this.valiosa = valiosa;
    }

    public Boolean isSegurada() {
        return segurada;
    }

    public void setSegurada(Boolean segurada) {
        this.segurada = segurada;
    }

    public Boolean isPerigosa() {
        return perigosa;
    }

    public void setPerigosa(Boolean perigosa) {
        this.perigosa = perigosa;
    }

    public Long getCaminhaoId() {
        return caminhaoId;
    }

    public void setCaminhaoId(Long caminhaoId) {
        this.caminhaoId = caminhaoId;
    }

    public Long getMotoristaId() {
        return motoristaId;
    }

    public void setMotoristaId(Long motoristaId) {
        this.motoristaId = motoristaId;
    }

    public CargaDTO getCarga() {
        return carga;
    }

    public void setCarga(CargaDTO carga) {
        this.carga = carga;
    }
}
