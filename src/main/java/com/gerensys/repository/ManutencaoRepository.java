package com.gerensys.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gerensys.model.Manutencao;

import java.util.List;

@Repository
@Transactional
public interface ManutencaoRepository extends CrudRepository<Manutencao, Long> {

    @Query("select m from Manutencao m where m.veiculo.placa like %?1%")
    List<Manutencao> findManutencaoByName (String placa);
}
