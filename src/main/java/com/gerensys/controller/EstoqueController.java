package com.gerensys.controller;

import java.util.Iterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gerensys.model.Estoque;
import com.gerensys.repository.EstoqueRepository;

@Controller
public class EstoqueController {

    @Autowired
    private EstoqueRepository estoqueRepository;

    @GetMapping("/cadastraestoque")
    public ModelAndView cadastro() {

        ModelAndView andView = new ModelAndView("cadastro/cadastro-estoque");

        return andView;
    }

    @PostMapping("**/salvarestoque")
    public ModelAndView salvar(Estoque estoque) {

        estoqueRepository.save(estoque);

        ModelAndView andView = new ModelAndView("listas/listar-estoque");
        Iterable<Estoque> itens = estoqueRepository.findAll();
        andView.addObject("itens", itens);

        return andView;
    }

    @GetMapping("/listaestoque")
    public ModelAndView estoque() {

        ModelAndView andView = new ModelAndView("listas/listar-estoque");
        Iterable<Estoque> itens = estoqueRepository.findAll();
        andView.addObject("itens", itens);

        return andView;
    }

    @GetMapping("/editaritem/{iditem}")
    public ModelAndView editar(@PathVariable("iditem") Long iditem) {

        Optional<Estoque> estoque = estoqueRepository.findById(iditem);
        ModelAndView andView = new ModelAndView("editar/editar-estoque");
        andView.addObject("objestoque", estoque.get());

        return andView;

    }

    @PostMapping("**/editestoque")
    public ModelAndView atualiza(Estoque estoque) {

        estoqueRepository.save(estoque);

        ModelAndView andView = new ModelAndView("listas/listar-estoque");
        Iterable<Estoque> itens = estoqueRepository.findAll();
        andView.addObject("itens", itens);

        return andView;
    }

    @GetMapping("/removeritem/{iditem}")
    public ModelAndView excluir(@PathVariable("iditem") Long iditem) {

        estoqueRepository.deleteById(iditem);
        ModelAndView andView = new ModelAndView("listas/listar-estoque");
        Iterable<Estoque> itens = estoqueRepository.findAll();
        andView.addObject("itens", itens);

        return andView;
    }

    @PostMapping("**/pesquisaritem")
    public ModelAndView pesquisar(@RequestParam("nomepesquisa") String nomepesquisa) {

        ModelAndView andView = new ModelAndView("listas/listar-estoque");
        andView.addObject("itens", estoqueRepository.findEstoqueByNameItem(nomepesquisa));

        return andView;
    }


}
