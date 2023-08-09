package com.gerensys.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idViagem")
public class Viagem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idViagem;

    @OneToOne
    @JoinColumn(name = "idFuncionario")
    @JsonBackReference(value = "funcionario")
    private Funcionario funcionario;

    @OneToOne
    @JoinColumn(name = "idVeiculo")
    @JsonBackReference
    private Veiculo veiculo;

    @OneToOne
    @JoinColumn(name = "idPedido")
    @JsonBackReference(value = "viagem")
    private Pedido pedido;

    @OneToOne(mappedBy = "viagem")
    @JsonManagedReference
    private Relatorio relatorio;

    public Long getIdViagem() {
        return idViagem;
    }

    public void setIdViagem(Long idViagem) {
        this.idViagem = idViagem;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Relatorio getRelatorio() {
        return relatorio;
    }

    public void setRelatorio(Relatorio relatorio) {
        this.relatorio = relatorio;
    }

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
}
