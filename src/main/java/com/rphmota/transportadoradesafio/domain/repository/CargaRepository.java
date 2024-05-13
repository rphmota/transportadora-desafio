package com.rphmota.transportadoradesafio.domain.repository;

import com.rphmota.transportadoradesafio.domain.entity.Carga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CargaRepository extends JpaRepository<Carga, Long> {

    List<Carga> findByTipo(String tipo);
}

