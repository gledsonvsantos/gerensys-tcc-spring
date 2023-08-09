package com.gerensys.controller;

import com.gerensys.model.Pedido;
import com.gerensys.model.Usuario;
import com.gerensys.repository.FuncionarioRepository;
import com.gerensys.repository.PedidoRepository;
import com.gerensys.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @GetMapping("/pedido")
    public ModelAndView pedido() {
        ModelAndView andView = new ModelAndView("cadastro/cadastro-pedido");

        return andView;
    }

    @PostMapping("**/salvarpedido")
    public ModelAndView salvar(Pedido pedido) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String result = encoder.encode(pedido.getCliente().getSenha());
        pedido.getCliente().setSenha(result);

        pedidoRepository.save(pedido);

        Usuario usuario = new Usuario();
        usuario.setSenha(result);
        usuario.setLogin(pedido.getCliente().getCpf());
        usuario.setId(pedido.getCliente().getId());
        usuarioRepository.save(usuario);

        Long clienteid = pedido.getCliente().getId();
        funcionarioRepository.insertUsuarioAndRole(clienteid, (long) 5);

        ModelAndView andView = new ModelAndView("detalhe/detalhe-pedido"); //cliente irá para página de acompanhamento
        andView.addObject("objpedido", pedido);

        return andView;
    }

    @GetMapping("/acompanhamento")
    public ModelAndView acompanhamento() {
        ModelAndView andView = new ModelAndView("detalhe/detalhe-pedido");


        return andView;
    }
}
