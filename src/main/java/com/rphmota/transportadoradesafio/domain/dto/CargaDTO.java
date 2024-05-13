package com.rphmota.transportadoradesafio.domain.dto;

public class CargaDTO {
    private Long id;
    private String tipo;
    private String descricao;

    // Construtor vazio necessário para frameworks
    public CargaDTO() {}

    // Construtor com todos os campos
    public CargaDTO(Long id, String tipo, String descricao) {
        this.id = id;
        this.tipo = tipo;
        this.descricao = descricao;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
