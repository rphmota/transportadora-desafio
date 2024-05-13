package com.rphmota.transportadoradesafio.domain.repository;

import com.rphmota.transportadoradesafio.domain.entity.Caminhao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaminhaoRepository extends JpaRepository<Caminhao, Long> {

    List<Caminhao> findByClienteId(Long clienteId);
}


