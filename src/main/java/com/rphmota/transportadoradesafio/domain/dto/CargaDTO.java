package com.rphmota.transportadoradesafio.domain.dto;

import com.rphmota.transportadoradesafio.domain.entity.Carga;

public class CargaDTO {
    private Long id;
    private String tipo;
    private String descricao;



    public CargaDTO() {
    }

    public CargaDTO(Carga carga) {
        this.id = carga.getId();
        this.tipo = carga.getTipo();
        this.descricao = carga.getDescricao();
    }

    // Getters e setters
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
