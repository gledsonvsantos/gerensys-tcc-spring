package com.gerensys.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gerensys.model.Financas;

@Repository
@Transactional
public interface FinancasRepository extends CrudRepository<Financas, Long> {

}
