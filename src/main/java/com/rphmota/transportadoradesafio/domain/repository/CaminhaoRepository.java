package com.rphmota.transportadoradesafio.domain.repository;

import com.rphmota.transportadoradesafio.domain.entity.Caminhao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaminhaoRepository extends JpaRepository<Caminhao, Long> {
    // Este método está correto se a entidade Caminhao tem uma referência correta a Cliente
    List<Caminhao> findByClienteId(Long clienteId);
}


