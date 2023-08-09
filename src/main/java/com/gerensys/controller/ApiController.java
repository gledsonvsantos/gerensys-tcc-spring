package com.gerensys.controller;

import com.gerensys.model.Cliente;
import com.gerensys.model.Funcionario;
import com.gerensys.model.Pedido;
import com.gerensys.model.Relatorio;
import com.gerensys.model.Usuario;
import com.gerensys.model.Viagem;
import com.gerensys.repository.ClienteRepository;
import com.gerensys.repository.FuncionarioRepository;
import com.gerensys.repository.PedidoRepository;
import com.gerensys.repository.RelatorioRepository;
import com.gerensys.repository.UsuarioRepository;
import com.gerensys.repository.ViagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class ApiController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private ViagemRepository viagemRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private RelatorioRepository relatorioRepository;

    @GetMapping("/listafuncionarios")
    public ResponseEntity<List<Funcionario>> funcionarios() {

        List<Funcionario> list = (List<Funcionario>) funcionarioRepository.findAll();

        return new ResponseEntity<List<Funcionario>>(list, HttpStatus.OK);
    }

    @GetMapping("/funcionario/{id}")
    public ResponseEntity<Funcionario> buscarpelocodigo(@PathVariable(value = "id") Long id){
        Optional<Funcionario> funcionario = funcionarioRepository.findById(id);

        return new ResponseEntity<Funcionario>(funcionario.get(), HttpStatus.OK);
    }

    @GetMapping("/listaviagens")
    public ResponseEntity<List<Viagem>> viagens() {

        Iterable<Viagem> viagens = viagemRepository.findAll();

        return new ResponseEntity<List<Viagem>>((List<Viagem>) viagens, HttpStatus.OK);
    }

    @PostMapping(value = "/pedido", produces = "application/json")
    public ResponseEntity<Pedido> cadastraPedido(@RequestBody Pedido pedido) {
        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        return new ResponseEntity<Pedido>(pedidoSalvo, HttpStatus.OK);
    }

    @GetMapping("/pedidos")
    public ResponseEntity<List<Pedido>> pedidos() {

        Iterable<Pedido> pedidos = pedidoRepository.findAll();

        return new ResponseEntity<List<Pedido>>((List<Pedido>) pedidos, HttpStatus.OK);
    }

    @GetMapping("/clientes")
    public ResponseEntity<List<Cliente>> clientes() {

        Iterable<Cliente> clientes = clienteRepository.findAll();

        return new ResponseEntity<List<Cliente>>((List<Cliente>) clientes, HttpStatus.OK);
    }

    @PostMapping(value = "/novocliente", produces = "application/json")
    public ResponseEntity<Cliente> cadastraCliente(@RequestBody Cliente cliente) {
        Cliente clienteSalvo = clienteRepository.save(cliente);

        return new ResponseEntity<Cliente>(clienteSalvo, HttpStatus.OK);
    }
    
    @PostMapping(value = "/novofuncionario", produces = "application/json")
    public ResponseEntity<Funcionario> cadastraFuncionario(@RequestBody Funcionario funcionario) {
    	
    	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String result = encoder.encode(funcionario.getSenha());
        funcionario.setSenha(result);
    	
        Funcionario funcionarioSalvo = funcionarioRepository.save(funcionario);

        return new ResponseEntity<Funcionario>(funcionarioSalvo, HttpStatus.OK);
    }
    
    @PostMapping(value = "/novorelatorio", produces = "application/json")
    public ResponseEntity<Relatorio> cadastraRelatorio(@RequestBody Relatorio relatorio) {
        Relatorio relatorioSalvo = relatorioRepository.save(relatorio);

        return new ResponseEntity<Relatorio>(relatorioSalvo, HttpStatus.OK);
    }

}
