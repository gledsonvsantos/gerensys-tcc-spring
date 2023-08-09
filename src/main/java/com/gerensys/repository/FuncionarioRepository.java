package com.gerensys.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.gerensys.model.Funcionario;

@Repository
@Transactional
public interface FuncionarioRepository extends CrudRepository<Funcionario, Long> {

  @Query("select f from Funcionario f where f.nome like %?1%")
  List<Funcionario> findFuncionarioByName(String nome);

  @Query("select f from Funcionario f where f.nomeCargo = 'MOTORISTA'")
  List<Funcionario> findFuncionarioByCargo(String nomeCargo);

  @Query("select f from Funcionario f where f.cpf = ?1")
  Funcionario findFuncionarioByCpf(String cpf);

  @Modifying
  @Query(value = "insert into funcionarios_role (funcionario_id, role_id) VALUES (:funcionarioid, :roleid)", nativeQuery = true)
  void insertFuncionarioAndRole(@Param("funcionarioid")Long funcionarioid, @Param("roleid") Long roleid);

  @Modifying
  @Query(value = "insert into usuarios_role (usuario_id, role_id) VALUES (:funcionarioid, :roleid)", nativeQuery = true)
  void insertUsuarioAndRole(@Param("funcionarioid")Long funcionarioid, @Param("roleid") Long roleid);
}
