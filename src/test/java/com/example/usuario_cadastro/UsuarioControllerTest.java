package com.example.usuario_cadastro;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.usuario_cadastro.controller.UsuarioController;
import com.example.usuario_cadastro.model.Usuario;
import com.example.usuario_cadastro.service.UsuarioService;

@SpringBootTest
public class UsuarioControllerTest {

	


	    @InjectMocks
	    private UsuarioController usuarioController;

	    @Mock
	    private UsuarioService usuarioService;

	    private MockMvc mockMvc;

	    @BeforeEach
	    public void setUp() {
	        MockitoAnnotations.openMocks(this);
	        mockMvc = MockMvcBuilders.standaloneSetup(usuarioController).build();
	    }

	    @Test
	    public void testRegistrarUsuario() throws Exception {
	        Usuario usuario = new Usuario();
	        usuario.setNome("John Doe");
	        usuario.setEmail("john@example.com");
	        usuario.setSenha("password");
	        usuario.setConfirmarSenha("password");

	        when(usuarioService.registrarUsuario(any(Usuario.class))).thenReturn(usuario);

	        mockMvc.perform(post("/api/usuarios/registrar")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content("{\"nome\":\"John Doe\",\"email\":\"john@example.com\",\"senha\":\"password\",\"confirmarSenha\":\"password\"}"))
	                .andExpect(status().isCreated())
	                .andExpect(jsonPath("$.nome").value("John Doe"));
	    }

	    @Test
	    public void testAtualizarUsuario() throws Exception {
	        Usuario usuario = new Usuario();
	        usuario.setNome("Jane Doe");

	        when(usuarioService.editarUsuario(any(Long.class), any(Usuario.class))).thenReturn(usuario);

	        mockMvc.perform(put("/api/usuarios/alterar/1")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content("{\"nome\":\"Jane Doe\"}"))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.nome").value("Jane Doe"));
	    }

	    @Test
	    public void testListarUsuarios() throws Exception {
	        when(usuarioService.listarUsuarios()).thenReturn(List.of(new Usuario()));

	        mockMvc.perform(get("/api/usuarios"))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$").isArray());
	    }

	    @Test
	    public void testBuscarUsuarioPorId() throws Exception {
	        Usuario usuario = new Usuario();
	        usuario.setNome("John Doe");

	        when(usuarioService.buscarUsuarioPorId(1L)).thenReturn(Optional.of(usuario));

	        mockMvc.perform(get("/api/usuarios/1"))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.nome").value("John Doe"));
	    }

	    @Test
	    public void testExcluirUsuario() throws Exception {
	        doNothing().when(usuarioService).excluirUsuario(1L);

	        mockMvc.perform(delete("/api/usuarios/1"))
	                .andExpect(status().isNoContent());
	    }
	}
