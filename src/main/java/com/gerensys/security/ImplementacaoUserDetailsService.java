package com.gerensys.security;

import com.gerensys.model.Funcionario;
import com.gerensys.model.Usuario;
import com.gerensys.repository.FuncionarioRepository;
import com.gerensys.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ImplementacaoUserDetailsService implements UserDetailsService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findUserByLogin(username);

        Funcionario funcionario = funcionarioRepository.findFuncionarioByCpf(username);

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não foi encontrado");
        }
        return new User(usuario.getUsername(), usuario.getPassword(), usuario.isEnabled(), true, true ,true, usuario.getAuthorities());
    }
}
