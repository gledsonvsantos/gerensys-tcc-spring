package com.gerensys.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Cargo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idCargo")
	private Long idCargo;

	private String nomeCargo;

	// Descricao do cargo?
	private String descricao;

	/*
	 * @OneToOne(mappedBy = "cargo") private Funcionario funcionario;
	 */

	public Long getIdCargo() {
		return idCargo;
	}

	public void setIdCargo(Long idCargo) {
		this.idCargo = idCargo;
	}

	public String getNomeCargo() {
		return nomeCargo;
	}

	public void setNomeCargo(String nomeCargo) {
		this.nomeCargo = nomeCargo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
