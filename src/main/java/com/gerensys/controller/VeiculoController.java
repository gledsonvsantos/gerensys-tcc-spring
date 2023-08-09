package com.gerensys.controller;

import com.gerensys.model.Seguro;
import com.gerensys.model.Veiculo;
import com.gerensys.repository.SeguroRepository;
import com.gerensys.repository.VeiculoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class VeiculoController {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private SeguroRepository seguroRepository;

    @GetMapping("/cadastroveiculo")
    public ModelAndView inicio() {

        ModelAndView andView = new ModelAndView("cadastro/cadastro-veiculo");

        return andView;
    }

    @PostMapping("**/salvarveiculo")
    public ModelAndView salvarSeguro(Seguro seguro) {

        seguro.getVeiculo().setSeguro(seguro);
        seguroRepository.save(seguro);

        ModelAndView andView = new ModelAndView("listas/listar-veiculo");
        Iterable<Veiculo> veicAll = veiculoRepository.findAll();
        andView.addObject("veiculos", veicAll);

        return andView;
    }

    @GetMapping("/listaveiculo")
    public ModelAndView veiculos() {

        ModelAndView andView = new ModelAndView("listas/listar-veiculo");
        Iterable<Veiculo> veicAll = veiculoRepository.findAll();
        andView.addObject("veiculos", veicAll);

        return andView;
    }

    @GetMapping("/editarveiculo/{renavamveiculo}")
    public ModelAndView editar(@PathVariable("renavamveiculo") Long renavamveiculo) {

        Optional<Veiculo> veiculo = veiculoRepository.findById(renavamveiculo);
        ModelAndView andView = new ModelAndView("editar/editar-veiculo");
        andView.addObject("objveiculo", veiculo.get().getSeguro());

        return andView;

    }

    @PostMapping("**/editveiculo")
    public ModelAndView salvaredit(Seguro seguro) {

        seguro.getVeiculo().setSeguro(seguro);
        seguroRepository.save(seguro);

        ModelAndView andView = new ModelAndView("listas/listar-veiculo");
        Iterable<Veiculo> veicAll = veiculoRepository.findAll();
        andView.addObject("veiculos", veicAll);

        return andView;
    }

    @GetMapping("/removerveiculo/{numapoliceseguro}")
    public ModelAndView excluir(@PathVariable("numapoliceseguro") Long numapoliceseguro) {

        seguroRepository.deleteById(numapoliceseguro);
        ModelAndView andView = new ModelAndView("listas/listar-veiculo");
        Iterable<Veiculo> veicAll = veiculoRepository.findAll();
        andView.addObject("veiculos", veicAll);

        return andView;

    }

    @PostMapping("**/pesquisarveiculo")
    public ModelAndView pesquisar(@RequestParam("nomepesquisa") String nomepesquisa) {

        ModelAndView andView = new ModelAndView("listas/listar-veiculo");
        andView.addObject("veiculos", veiculoRepository.findVeiculoByName(nomepesquisa));

        return andView;
    }
}