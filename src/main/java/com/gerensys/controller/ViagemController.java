package com.gerensys.controller;

import com.gerensys.model.Funcionario;
import com.gerensys.model.Pedido;
import com.gerensys.model.Veiculo;
import com.gerensys.model.Viagem;
import com.gerensys.repository.FuncionarioRepository;
import com.gerensys.repository.PedidoRepository;
import com.gerensys.repository.VeiculoRepository;
import com.gerensys.repository.ViagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class ViagemController {

	@Autowired
	private ViagemRepository viagemRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private VeiculoRepository veiculoRepository;

	@GetMapping("/cadastroviagem")
	public ModelAndView inicio() {

		ModelAndView andView = new ModelAndView("cadastro/cadastro-viagem");

		Iterable<Pedido> pedidos = pedidoRepository.findAll();
		andView.addObject("pedidos", pedidos);
		return andView;
	}

	@GetMapping("/cadastroviagemparte2/{pedidoid}")
	public ModelAndView viagemparte2(@PathVariable("pedidoid") Long pedidoid) {

		Optional<Pedido> pedido = pedidoRepository.findById(pedidoid);

		ModelAndView andView = new ModelAndView("cadastro/cadastro-viagem2");

		andView.addObject("pedidoobj", pedido.get());
		andView.addObject("funcionarios", funcionarioRepository.findFuncionarioByCargo("MOTORISTA"));
		return andView;
	}

	@GetMapping("/cadastroviagemparte3/{funcionarioid}/{pedidoid}")
	public ModelAndView viagemparte3(@PathVariable("funcionarioid") Long funcionarioid,
			@PathVariable("pedidoid") Long pedidoid) {

		Optional<Funcionario> funcionario = funcionarioRepository.findById(funcionarioid);
		Optional<Pedido> pedido = pedidoRepository.findById(pedidoid);
		Iterable<Veiculo> veiculos = veiculoRepository.findAll();

		ModelAndView andView = new ModelAndView("cadastro/cadastro-viagem3");
		andView.addObject("funcionarioobj", funcionario.get());
		andView.addObject("pedidoobj", pedido.get());
		andView.addObject("veiculos", veiculos);
		return andView;
	}

	@GetMapping("/salvarviagem/{funcionarioid}/{pedidoid}/{veiculoid}")
	public ModelAndView salvarviagem(@PathVariable("funcionarioid") Long funcionarioid,
			@PathVariable("pedidoid") Long pedidoid, @PathVariable("veiculoid") Long veiculoid) {

		Funcionario funcionario = funcionarioRepository.findById(funcionarioid).get();
		Pedido pedido = pedidoRepository.findById(pedidoid).get();
		Veiculo veiculo = veiculoRepository.findById(veiculoid).get();
		Viagem viagem = new Viagem();

		funcionario.setPedido(pedido);
		funcionario.setVeiculo(veiculo);

		viagem.setFuncionario(funcionario);
		viagem.setPedido(pedido);
		viagem.setVeiculo(veiculo);

		viagemRepository.save(viagem);
		funcionarioRepository.save(funcionario);

		ModelAndView modelAndView = new ModelAndView("listas/listar-viagem");
		modelAndView.addObject("viagens", viagemRepository.findAll());

		return modelAndView;
	}

	@GetMapping("/listaviagem")
	public ModelAndView viagens() {

		Iterable<Viagem> viagens = viagemRepository.findAll();

		ModelAndView modelAndView = new ModelAndView("listas/listar-viagem");
		modelAndView.addObject("viagens", viagens);

		return modelAndView;
	}
}
