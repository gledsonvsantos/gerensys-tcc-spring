package com.gerensys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.gerensys.model.Estoque;
import com.gerensys.model.Funcionario;
import com.gerensys.model.Relatorio;
import com.gerensys.repository.RelatorioRepository;

@Controller
public class IndexController {
	
	@Autowired
	private RelatorioRepository relatorioRepository;

	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/listarelatorio")
    public ModelAndView estoque() {

        ModelAndView andView = new ModelAndView("listas/listar-relatorio");
        Iterable<Relatorio> relatorios = relatorioRepository.findAll();
        andView.addObject("relatorios", relatorios);

        return andView;
    }
	
	@GetMapping("/removerrelatorio/{idrelatorio}")
    public ModelAndView excluir(@PathVariable("idrelatorio") Long idrelatorio) {

       relatorioRepository.deleteById(idrelatorio);
       ModelAndView andView = new ModelAndView("listas/listar-relatorio");
       Iterable<Relatorio> relatorios = relatorioRepository.findAll();
       andView.addObject("relatorios", relatorios);

        return andView;
    }
}
