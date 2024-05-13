package com.rphmota.transportadoradesafio.domain.repository;

import com.rphmota.transportadoradesafio.domain.entity.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Month;
import java.util.List;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Long> {

    List<Entrega> findByCaminhaoId(Long caminhaoId);

    @Query("SELECT e FROM Entrega e WHERE e.valorBase > 30000")
    List<Entrega> findEntregasValiosas();

    @Query("SELECT COUNT(e) FROM Entrega e WHERE e.motorista.id = :motoristaId AND EXTRACT(MONTH FROM e.dataEntrega) = :month AND EXTRACT(YEAR FROM e.dataEntrega) = :year")
    long countEntregasByMotoristaAndMonth(Long motoristaId, int month, int year);

    @Query("SELECT COUNT(e) FROM Entrega e WHERE e.caminhao.id = :caminhaoId AND EXTRACT(MONTH FROM e.dataEntrega) = :month AND EXTRACT(YEAR FROM e.dataEntrega) = :year")
    long countEntregasByCaminhaoAndMonth(Long caminhaoId, int month, int year);

    @Query("SELECT COUNT(e) FROM Entrega e WHERE e.motorista.id = :motoristaId AND LOWER(e.destino) = LOWER(:destino)")
    long countEntregasByMotoristaAndDestino(Long motoristaId, String destino);



}


