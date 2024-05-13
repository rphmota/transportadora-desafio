package com.rphmota.transportadoradesafio.domain.repository;
import com.rphmota.transportadoradesafio.domain.entity.Motorista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface MotoristaRepository extends JpaRepository<Motorista, Long> {
    // Corrigido para referenciar o nome exato da propriedade na entidade Caminhao
    List<Motorista> findByCaminhaoId(Long caminhaoId);

    boolean existsByCaminhaoId(Long id);
}


