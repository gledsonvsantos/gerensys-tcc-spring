package com.gerensys.controller;

import com.gerensys.model.*;
import com.gerensys.repository.EstoqueRepository;
import com.gerensys.repository.FuncionarioRepository;
import com.gerensys.repository.SeguroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gerensys.repository.FinancasRepository;

import java.util.Iterator;
import java.util.Optional;

@Controller
public class FinancasController {

	@Autowired
	private FinancasRepository financasRepository;

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private SeguroRepository seguroRepository;
	
	@GetMapping("/despesas")
    public ModelAndView cadastro() {

        ModelAndView andView = new ModelAndView("listas/listar-despesas");

        Iterable<Financas> financas = financasRepository.findAll();
        andView.addObject("financas", financas);
        return andView;
    }

    @GetMapping("/atualizardespesas")
    public ModelAndView atualizardespesas() {

        ModelAndView andView = new ModelAndView("listas/listar-despesas");
        Iterable<Financas> financas = financasRepository.findAll();
        andView.addObject("financas", financas);

        Iterable<Estoque> itens = estoqueRepository.findAll();
        Iterator<Estoque> iterator = itens.iterator();
        double totalpecas = 0;
        while (iterator.hasNext()) {
            Estoque estoque = iterator.next();
            totalpecas += (estoque.getPreco() * estoque.getQuantidade());
        }

        Iterable<Funcionario> funcionarios = funcionarioRepository.findAll();
        andView.addObject("funcionarios", funcionarios);
        Iterator<Funcionario> funcionarioIterator = funcionarios.iterator();
        double totalPgt = 0;
        while (funcionarioIterator.hasNext()) {
            Funcionario funcionario = funcionarioIterator.next();
            totalPgt += funcionario.getSalario();
        }

        Iterable<Seguro> seguros = seguroRepository.findAll();
        Iterator<Seguro> seguroIterator = seguros.iterator();
        double totalSeguro = 0;
        while (seguroIterator.hasNext()) {
            Seguro seguro = seguroIterator.next();
            totalSeguro += seguro.getValorSeguro();
        }

        //Financas financa = new Financas();
        Optional<Financas> financa = financasRepository.findById((long) 33);
        financa.get().setPecas(totalpecas);
        financa.get().setPgtoFuncionario(totalPgt);
        financa.get().setSeguros(totalSeguro);
        financasRepository.save(financa.get());

        return andView;
    }
}
