package com.rphmota.transportadoradesafio.domain.repository;

import com.rphmota.transportadoradesafio.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    @Query("SELECT c.nome, SUM(e.valorBase * (CASE " +
            "WHEN e.destino = 'nordeste' THEN 1.20 " +
            "WHEN e.destino = 'argentina' THEN 1.40 " +
            "WHEN e.destino = 'amazônia' THEN 1.30 " +
            "ELSE 1.0 END)) AS totalValor FROM Cliente c " +
            "JOIN c.frota f " +
            "JOIN f.entregas e " +
            "GROUP BY c.nome")
    List<Object[]>   gccbfindTotalValorEntregasPorCliente();

}

