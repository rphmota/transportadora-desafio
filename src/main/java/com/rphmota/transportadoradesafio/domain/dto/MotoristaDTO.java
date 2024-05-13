package com.rphmota.transportadoradesafio.dto;

import com.rphmota.transportadoradesafio.domain.dto.CaminhaoDTO;
import com.rphmota.transportadoradesafio.domain.dto.EntregaDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;

public class MotoristaDTO {
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 255, message = "O nome deve ter entre 3 e 255 caracteres")
    private String nome;

    private CaminhaoDTO caminhao; // CaminhaoDTO pode incluir detalhes como modelo, placa, etc.
    private List<EntregaDTO> entregas; // EntregaDTO pode resumir informações chave

    // Construtor vazio necessário para frameworks
    public MotoristaDTO() {}

    // Construtor com todos os campos
    public MotoristaDTO(Long id, String nome, CaminhaoDTO caminhao, List<EntregaDTO> entregas) {
        this.id = id;
        this.nome = nome;
        this.caminhao = caminhao;
        this.entregas = entregas;
    }

    // Getters e Setters
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

    public CaminhaoDTO getCaminhao() {
        return caminhao;
    }

    public void setCaminhao(CaminhaoDTO caminhao) {
        this.caminhao = caminhao;
    }

    public List<EntregaDTO> getEntregas() {
        return entregas;
    }

    public void setEntregas(List<EntregaDTO> entregas) {
        this.entregas = entregas;
    }
}
