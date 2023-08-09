package com.gerensys.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gerensys.model.Veiculo;

import java.util.List;

@Repository
@Transactional
public interface VeiculoRepository extends CrudRepository<Veiculo, Long> {

    @Query("select v from Veiculo v where v.placa like %?1%")
    List<Veiculo> findVeiculoByName(String nomepesquisa);
}
