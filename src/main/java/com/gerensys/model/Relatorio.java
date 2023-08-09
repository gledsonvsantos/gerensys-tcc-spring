package com.gerensys.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "numeroRelatorio")
public class Relatorio {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long numeroRelatorio;
	
	//Tipo??
	private String tipo;
	
	@Column(columnDefinition = "TEXT")
	private String descricao;
	
	@OneToOne
	@JoinColumn(name = "idViagem")
	@JsonBackReference
	private Viagem viagem;

	public Long getNumeroRelatorio() {
		return numeroRelatorio;
	}

	public void setNumeroRelatorio(Long numeroRelatorio) {
		this.numeroRelatorio = numeroRelatorio;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Viagem getViagem() {
		return viagem;
	}

	public void setViagem(Viagem viagem) {
		this.viagem = viagem;
	}
}
