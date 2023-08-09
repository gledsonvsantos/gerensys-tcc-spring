package com.gerensys.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Financas {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idFinancas")
	private Long idFinancas;
	
	private double pgtoFuncionario;
	
	private double pecas;

	private double seguros;

	public Long getIdFinancas() {
		return idFinancas;
	}

	public void setIdFinancas(Long idFinancas) {
		this.idFinancas = idFinancas;
	}

	public double getPgtoFuncionario() {
		return pgtoFuncionario;
	}

	public void setPgtoFuncionario(double pgtoFuncionario) {
		this.pgtoFuncionario = pgtoFuncionario;
	}

	public double getPecas() {
		return pecas;
	}

	public void setPecas(double pecas) {
		this.pecas = pecas;
	}

	public double getSeguros() {
		return seguros;
	}

	public void setSeguros(double seguros) {
		this.seguros = seguros;
	}
}
