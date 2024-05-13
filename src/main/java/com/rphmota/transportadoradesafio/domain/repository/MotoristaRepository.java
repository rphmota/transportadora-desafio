package com.rphmota.transportadoradesafio.domain.repository;
import com.rphmota.transportadoradesafio.domain.entity.Motorista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface MotoristaRepository extends JpaRepository<Motorista, Long> {

    List<Motorista> findByCaminhaoId(Long caminhaoId);

    boolean existsByCaminhaoId(Long id);
}


