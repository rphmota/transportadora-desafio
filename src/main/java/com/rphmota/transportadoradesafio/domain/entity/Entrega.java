package com.rphmota.transportadoradesafio.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "entregas")
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String destino;

    @Column(nullable = false)
    private double valorBase;

    @Column(nullable = false)
    private LocalDate dataEntrega;

    @Transient
    private double valorTotal;

    @Column
    private Boolean valiosa;

    @Column
    private Boolean segurada;

    @Column
    private Boolean perigosa;


    @ManyToOne
    @JoinColumn(name = "caminhao_id", nullable = false)
    @JsonIgnoreProperties({"entregas"}) // Ignora a lista de entregas em Caminhao
    private Caminhao caminhao;

    @ManyToOne
    @JoinColumn(name = "motorista_id", nullable = false)
    @JsonIgnoreProperties({"entregas"}) // Ignora a lista de entregas em Motorista
    private Motorista motorista;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "carga_id")
    private Carga carga;

    @Override
    public String toString() {
        return "Entrega{" +
                "caminhao=" + caminhao +
                ", id=" + id +
                ", destino='" + destino + '\'' +
                ", valorBase=" + valorBase +
                ", dataEntrega=" + dataEntrega +
                ", valorTotal=" + valorTotal +
                ", valiosa=" + valiosa +
                ", segurada=" + segurada +
                ", perigosa=" + perigosa +
                ", motorista=" + motorista +
                ", carga=" + carga +
                '}';
    }

    // Métodos de cálculo do valor total
    public void calcularValorTotal() {
        this.valorTotal = valorBase;
        double taxaAdicional = 0;
        switch (destino.toLowerCase()) {
            case "nordeste":
                taxaAdicional = 0.20; // Acréscimo de 20%
                break;
            case "argentina":
                taxaAdicional = 0.40; // Acréscimo de 40%
                break;
            case "amazônia":
                taxaAdicional = 0.30; // Acréscimo de 30%
                break;
        }
        this.valorTotal += this.valorBase * taxaAdicional;

        // Definir como valiosa se o valor total exceder 30000
        this.valiosa = this.valorTotal > 30000;
    }


    // Getters e setters
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
        calcularValorTotal(); // Recalcula o valor total sempre que o destino for atualizado
    }

    public double getValorBase() {
        return valorBase;
    }

    public void setValorBase(double valorBase) {
        this.valorBase = valorBase;
        calcularValorTotal(); // Recalcula o valor total sempre que o valor base for atualizado
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public Boolean isValiosa() {
        return valiosa;
    }

    public void setValiosa(boolean valiosa) {
        this.valiosa = valiosa;
    }

    public Boolean isSegurada() {
        return segurada;
    }

    public void setSegurada(boolean segurada) {
        this.segurada = segurada;
    }

    public Boolean isPerigosa() {
        return perigosa;
    }

    public void setPerigosa(boolean perigosa) {
        this.perigosa = perigosa;
    }

    public Caminhao getCaminhao() {
        return caminhao;
    }

    public void setCaminhao(Caminhao caminhao) {
        this.caminhao = caminhao;
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }

    public Carga getCarga() {
        return carga;
    }

    public void setCarga(Carga carga) {
        this.carga = carga;
    }
}
