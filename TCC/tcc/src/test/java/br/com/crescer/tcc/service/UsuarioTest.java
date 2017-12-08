package br.com.crescer.tcc.service;

import br.com.crescer.tcc.Repository.UsuarioRepository;
import br.com.crescer.tcc.entity.Usuario;
import java.time.LocalDate;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UsuarioTest {

    @Mock
    private UsuarioRepository usuarioRepository;
    
    @InjectMocks
    private UsuarioService usuarioService;
    
    @Test
    public void testarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNome("Luan");
        usuario.setEmail("luanparcival@gmail.com");
        usuario.setTelefone("982580230");
        usuario.setSenha("1234");
        usuario.setImagem_perfil("img");
        LocalDate nascimento = LocalDate.of(1999, 05, 22);
        usuario.setNascimento(nascimento);
        
        when(usuarioRepository.findByEmailIgnoreCase("luanparcival@gmail.com")).thenReturn(usuario);
        
        final Usuario usuario2 = usuarioService.findByEmail("luanparcival@gmail.com");
        
        assertEquals(usuario.getNome(), usuario2.getNome());
        assertEquals(usuario.getEmail(), usuario2.getEmail());
        assertEquals(usuario.getImagem_perfil(), usuario2.getImagem_perfil());
        assertEquals(usuario.getTelefone(), usuario2.getTelefone());
        assertEquals(usuario.getSenha(), usuario2.getSenha());
        assertEquals(usuario.getNascimento(), usuario2.getNascimento());
    }
}
