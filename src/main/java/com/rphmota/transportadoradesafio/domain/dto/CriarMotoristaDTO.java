package com.rphmota.transportadoradesafio.domain.dto;

public class CriarMotoristaDTO {

    private String nome;
    private Long caminhaoId; // Somente ID, pois a relação pode ser estabelecida por um serviço

    // Construtores
    public CriarMotoristaDTO() {}

    public CriarMotoristaDTO(String nome, Long caminhaoId) {
        this.nome = nome;
        this.caminhaoId = caminhaoId;
    }

    // Getters e Setters
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
