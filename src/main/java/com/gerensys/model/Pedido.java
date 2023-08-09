package com.gerensys.model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idpedido")
@JsonIgnoreProperties({"funcionario", "viagem"})
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idPedido")
	private Long idpedido;
	
	//Ir para tal endereco
	private String endereco;
	
	private String data;
	
	//Hora atual do pedido?
	private String hora;
	
	//Hora devolucao?
	private String horaDevolucao;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_cliente")
	@JsonBackReference(value = "cliente")
	private Cliente cliente;
	
	@OneToOne
	@JsonManagedReference(value = "pedido")
	private Funcionario funcionario;

	@OneToOne(mappedBy = "pedido")
	@JsonManagedReference(value = "viagem")
	private Viagem viagem;

	public Long getIdpedido() {
		return idpedido;
	}

	public void setIdpedido(Long idpedido) {
		this.idpedido = idpedido;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getHoraDevolucao() {
		return horaDevolucao;
	}

	public void setHoraDevolucao(String horaDevolucao) {
		this.horaDevolucao = horaDevolucao;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Viagem getViagem() {
		return viagem;
	}

	public void setViagem(Viagem viagem) {
		this.viagem = viagem;
	}
}
