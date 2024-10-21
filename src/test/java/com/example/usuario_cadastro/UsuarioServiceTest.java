package com.example.usuario_cadastro;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.usuario_cadastro.model.Usuario;
import com.example.usuario_cadastro.repository.UsuarioRepository;
import com.example.usuario_cadastro.service.UsuarioService;

@SpringBootTest
public class UsuarioServiceTest {

	@InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    private Usuario usuario;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        usuario = new Usuario();
        usuario.setNome("John Doe");
        usuario.setEmail("john@example.com");
        usuario.setSenha("password");
        usuario.setConfirmarSenha("password");
    }

    @Test
    public void testRegistrarUsuario() {
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        Usuario novoUsuario = usuarioService.registrarUsuario(usuario);

        assertEquals("John Doe", novoUsuario.getNome());
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    public void testRegistrarUsuario_SenhasNaoCoincidem() {
        usuario.setConfirmarSenha("differentPassword");

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            usuarioService.registrarUsuario(usuario);
        });

        assertEquals("As senhas não coincidem.", exception.getMessage());
        verify(usuarioRepository, times(0)).save(any());
    }

    @Test
    public void testListarUsuarios() {
        when(usuarioRepository.findAll()).thenReturn(List.of(usuario));

        List<Usuario> usuarios = usuarioService.listarUsuarios();

        assertFalse(usuarios.isEmpty());
        assertEquals(1, usuarios.size());
    }

    @Test
    public void testBuscarUsuarioPorId_UsuarioExistente() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        Optional<Usuario> encontrado = usuarioService.buscarUsuarioPorId(1L);

        assertTrue(encontrado.isPresent());
        assertEquals("John Doe", encontrado.get().getNome());
    }

    @Test
    public void testBuscarUsuarioPorId_UsuarioNaoExistente() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Usuario> encontrado = usuarioService.buscarUsuarioPorId(1L);

        assertFalse(encontrado.isPresent());
    }

    @Test
    public void testExcluirUsuario() {
        doNothing().when(usuarioRepository).deleteById(1L);
        
        assertDoesNotThrow(() -> usuarioService.excluirUsuario(1L));
        verify(usuarioRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testEditarUsuario_UsuarioExistente() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        Usuario usuarioAtualizado = new Usuario();
        usuarioAtualizado.setNome("Jane Doe");

        Usuario usuarioRetornado = usuarioService.editarUsuario(1L, usuarioAtualizado);

        assertEquals("Jane Doe", usuarioRetornado.getNome());
        verify(usuarioRepository, times(1)).save(any());
    }

    @Test
    public void testEditarUsuario_UsuarioNaoExistente() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            usuarioService.editarUsuario(1L, usuario);
        });

        assertEquals("Usuário não encontrado com ID: 1", exception.getMessage());
        verify(usuarioRepository, times(0)).save(any());
    }
}
