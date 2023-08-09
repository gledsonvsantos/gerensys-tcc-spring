package com.gerensys.controller;

import com.gerensys.model.Manutencao;
import com.gerensys.model.Veiculo;
import com.gerensys.repository.ManutencaoRepository;
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
public class ManutencaoController {

    @Autowired
    private ManutencaoRepository manutencaoRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    @GetMapping("/manutencao/{idveiculo}")
    public ModelAndView manutencao(@PathVariable("idveiculo") Long idveiculo) {

        Optional<Veiculo> veiculo = veiculoRepository.findById(idveiculo);
        ModelAndView andView = new ModelAndView("cadastro/cadastro-manutencao");
        andView.addObject("objveiculo", veiculo.get());
        andView.addObject("objmanu", new Manutencao());

        return andView;
    }

    @PostMapping("**/salvarmanutencao")
    public ModelAndView salvarmanutencao(Veiculo veiculo) {

        Long veiculoid = veiculo.getRenavam();
        Veiculo veiculo2 = veiculoRepository.findById(veiculoid).get();

        Manutencao manutencao = veiculo.getManutencao();
        manutencao.setVeiculo(veiculo2);

        manutencaoRepository.save(manutencao);
        veiculoRepository.save(veiculo);


        ModelAndView andView = new ModelAndView("listas/listar-manutencao");

        Iterable<Manutencao> manAll = manutencaoRepository.findAll();
        andView.addObject("manutencoes", manAll);

        return andView;
    }

    @GetMapping("/listamanutencao")
    public ModelAndView manutencoes() {

        ModelAndView andView = new ModelAndView("listas/listar-manutencao");
        Iterable<Manutencao> manAll = manutencaoRepository.findAll();
        andView.addObject("manutencoes", manAll);

        return andView;
    }

    @GetMapping("/removermanutencao/{idmanutencao}")
    public ModelAndView excluir(@PathVariable("idmanutencao") Long idmanutencao) {

        manutencaoRepository.deleteById(idmanutencao);

        ModelAndView andView = new ModelAndView("listas/listar-manutencao");
        Iterable<Manutencao> manAll = manutencaoRepository.findAll();
        andView.addObject("manutencoes", manAll);

        return andView;

    }

    @GetMapping("/editarmanutencao/{idmanutencao}")
    public ModelAndView editar(@PathVariable("idmanutencao") Long idmanutencao) {

        Optional<Manutencao> manutencao = manutencaoRepository.findById(idmanutencao);
        ModelAndView andView = new ModelAndView("editar/editar-manutencao");
        andView.addObject("objmanu", manutencao.get());

        return andView;

    }

    @PostMapping("**/editmanutencao")
    public ModelAndView atualiza(Manutencao manutencao) {

        manutencaoRepository.save(manutencao);

        ModelAndView andView = new ModelAndView("listas/listar-manutencao");
        Iterable<Manutencao> manAll = manutencaoRepository.findAll();
        andView.addObject("manutencoes", manAll);

        return andView;
    }

    @PostMapping("**/pesquisarmanutencao")
    public ModelAndView pesquisar(@RequestParam("nomepesquisa") String nomepesquisa) {

        ModelAndView andView = new ModelAndView("listas/listar-manutencao");
        andView.addObject("manutencoes", manutencaoRepository.findManutencaoByName(nomepesquisa));

        return andView;
    }


}
