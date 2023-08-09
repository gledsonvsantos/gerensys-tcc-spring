package com.gerensys.controller;

import java.util.List;
import java.util.Optional;

import com.gerensys.model.Funcionario;
import com.gerensys.model.Usuario;
import com.gerensys.repository.FuncionarioRepository;

import com.gerensys.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/cadastrofuncionarios")
    public ModelAndView cadastro() {
        ModelAndView andView = new ModelAndView("cadastro/cadastro-funcionario");

        return andView;
    }

    @PostMapping("**/salvarfuncionario")
    public ModelAndView salvar(Funcionario funcionario) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String result = encoder.encode(funcionario.getSenha());
        funcionario.setSenha(result);

        funcionarioRepository.save(funcionario);

        Usuario usuario = new Usuario();
        usuario.setSenha(result);
        usuario.setLogin(funcionario.getCpf());
        usuario.setId(funcionario.getId());
        usuarioRepository.save(usuario);

        Long funcionarioid = funcionario.getId();
        switch (funcionario.getNomeCargo().toString()) {
            case "ADMINISTRATIVO":
                funcionarioRepository.insertUsuarioAndRole(funcionarioid, (long) 1);
                break;
            case "FINANCEIRO":
                funcionarioRepository.insertUsuarioAndRole(funcionarioid, (long) 2);
                break;
            case "GERENTE":
                funcionarioRepository.insertUsuarioAndRole(funcionarioid, (long) 3);
                break;
            case "MOTORISTA":
                funcionarioRepository.insertUsuarioAndRole(funcionarioid, (long) 4);
                break;
        }


        ModelAndView andView = new ModelAndView("listas/listar-funcionario");
        Iterable<Funcionario> funcAll = funcionarioRepository.findAll();
        andView.addObject("funcionarios", funcAll);

        return andView;
    }

    @GetMapping("/listafuncionarios")
    public ModelAndView funcionarios() {

        ModelAndView andView = new ModelAndView("listas/listar-funcionario");
        Iterable<Funcionario> funcAll = funcionarioRepository.findAll();
        andView.addObject("funcionarios", funcAll);

        return andView;
    }

    @GetMapping("/editarfuncionario/{idfuncionario}")
    public ModelAndView editar(@PathVariable("idfuncionario") Long idfuncionario) {

        Optional<Funcionario> funcionario = funcionarioRepository.findById(idfuncionario);
        ModelAndView andView = new ModelAndView("editar/editar-funcionario");
        andView.addObject("objfuncionario", funcionario.get());

        return andView;

    }

    @PostMapping("**/salvaredit")
    public ModelAndView atualiza(Funcionario funcionario) {

        funcionarioRepository.save(funcionario);

        ModelAndView andView = new ModelAndView("listas/listar-funcionario");
        andView.addObject("objfuncionario", new Funcionario());
        Iterable<Funcionario> funcAll = funcionarioRepository.findAll();
        andView.addObject("funcionarios", funcAll);

        return andView;
    }

    @GetMapping("/removerfuncionario/{idfuncionario}")
    public ModelAndView excluir(@PathVariable("idfuncionario") Long idfuncionario) {

        funcionarioRepository.deleteById(idfuncionario);
        ModelAndView andView = new ModelAndView("listas/listar-funcionario");
        Iterable<Funcionario> funcAll = funcionarioRepository.findAll();
        andView.addObject("funcionarios", funcAll);

        return andView;
    }

    @PostMapping("**/pesquisarfuncionario")
    public ModelAndView pesquisar(@RequestParam("nomepesquisa") String nomepesquisa) {

        ModelAndView andView = new ModelAndView("listas/listar-funcionario");
        andView.addObject("funcionarios", funcionarioRepository.findFuncionarioByName(nomepesquisa));

        return andView;
    }
    
    @GetMapping("/visualizarfuncionario/{idfuncionario}")
    public ModelAndView detalhes(@PathVariable("idfuncionario") Long idfuncionario) {

        Optional<Funcionario> funcionario = funcionarioRepository.findById(idfuncionario);
        ModelAndView andView = new ModelAndView("detalhe/detalhe-funcionario");
        andView.addObject("objfuncionario", funcionario.get());

        return andView;

    }

}
