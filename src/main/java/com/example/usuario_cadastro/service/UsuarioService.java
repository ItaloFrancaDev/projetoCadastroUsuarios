package com.example.usuario_cadastro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.usuario_cadastro.model.Usuario;
import com.example.usuario_cadastro.repository.UsuarioRepository;

import jakarta.validation.Valid;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario registrarUsuario(@Valid Usuario usuario) {
        // Valida se a senha e a confirmação de senha são iguais
        if (!usuario.getSenha().equals(usuario.getConfirmarSenha())) {
            throw new RuntimeException("As senhas não coincidem.");
        }
        
        // Aqui você pode adicionar lógica adicional, como criptografia de senha
        return usuarioRepository.save(usuario); // Salva o novo usuário no banco de dados
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public void excluirUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
    
    public Usuario editarUsuario(Long id, Usuario usuarioAtualizado) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);
        if (!usuarioExistente.isPresent()) {
            throw new RuntimeException("Usuário não encontrado com ID: " + id);
        }
        if (usuarioExistente.isPresent()) {
            Usuario usuario = usuarioExistente.get();
            usuario.setNome(usuarioAtualizado.getNome());
            usuario.setEmail(usuarioAtualizado.getEmail());
            usuario.setSenha(usuarioAtualizado.getSenha());
            return usuarioRepository.save(usuario);
        } else {
            throw new RuntimeException("Usuário não encontrado com o ID: " + id);
        }
    }
    
}


