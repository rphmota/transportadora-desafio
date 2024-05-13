package com.rphmota.transportadoradesafio.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "motoristas")
public class Motorista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 255, message = "O nome deve ter entre 3 e 255 caracteres")
    @Column(nullable = false)
    private String nome;

    @OneToOne
    @JoinColumn(name = "caminhao_id", nullable = true)  // Alterado para nullable = true
    private Caminhao caminhao;

    @OneToMany(mappedBy = "motorista", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Entrega> entregas;

    // Getters e setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Caminhao getCaminhao() {
        return caminhao;
    }

    public void setCaminhao(Caminhao caminhao) {
        this.caminhao = caminhao;
    }

    public List<Entrega> getEntregas() {
        return entregas;
    }

    public void setEntregas(List<Entrega> entregas) {
        this.entregas = entregas;
    }
}
