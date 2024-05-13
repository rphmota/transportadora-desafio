package com.rphmota.transportadoradesafio.domain.dto;

public class AtualizarMotoristaDTO {
    private Long id;
    private String nome;
    private Long caminhaoId; // Pode ser null para desassociar o caminhão atual

    // Construtores, getters e setters
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

    public Long getCaminhaoId() {
        return caminhaoId;
    }

    public void setCaminhaoId(Long caminhaoId) {
        this.caminhaoId = caminhaoId;
    }
}
