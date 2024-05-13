package com.rphmota.transportadoradesafio.domain.dto;


import com.rphmota.transportadoradesafio.domain.entity.Motorista;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

//Usando lombok para nao fazer os get and set
@Getter
@Setter
@NoArgsConstructor
public class MotoristaDTO {
    private Long id;
    private String nome;
    private List<EntregaDTO> entregas;

    public MotoristaDTO(Motorista motorista) {
        this.id = motorista.getId();
        this.nome = motorista.getNome();
        //Caso o motorista nao tenha entregas ele passa por cima
        this.entregas = motorista.getEntregas() != null ? motorista.getEntregas().stream()
                .map(EntregaDTO::new)
                .collect(Collectors.toList()) : null;
    }
}
