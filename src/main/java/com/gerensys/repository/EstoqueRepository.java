package com.gerensys.repository;

import com.gerensys.model.Funcionario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gerensys.model.Estoque;

import java.util.List;

@Repository
@Transactional
public interface EstoqueRepository extends CrudRepository<Estoque, Long>{

    @Query("select e from Estoque e where e.nomeItem like %?1%")
    List<Estoque> findEstoqueByNameItem(String nomeItem);


}
