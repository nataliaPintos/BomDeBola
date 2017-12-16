package br.com.crescer.tcc.service;

import br.com.crescer.tcc.Repository.UsuarioRepository;
import br.com.crescer.tcc.entity.Usuario;
import java.time.LocalDate;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UsuarioTestService {
    @Mock
    private UsuarioRepository usuarioRepository;
    
    @InjectMocks
    private UsuarioService usuarioService;
    
    @Test
    public void testeFindByEmail() {
        LocalDate nascimento = LocalDate.of(1999, 05, 22);
	final Usuario usuario = new Usuario("Luan", "luanparcival@gmail.com", "982580230",
        "1234", nascimento);
        
        when(usuarioRepository.findByEmailIgnoreCase("luanparcival@gmail.com")).thenReturn(usuario);
        
        final Usuario usuario2 = usuarioService.findByEmail("luanparcival@gmail.com");
        
        assertEquals(usuario.getNome(), usuario2.getNome());
        assertEquals(usuario.getEmail(), usuario2.getEmail());
        assertEquals(usuario.getImagemPerfil(), usuario2.getImagemPerfil());
        assertEquals(usuario.getTelefone(), usuario2.getTelefone());
        assertEquals(usuario.getSenha(), usuario2.getSenha());
        assertEquals(usuario.getNascimento(), usuario2.getNascimento());
    }
    
    @Test
    public void testeLoadById() {
        LocalDate nascimento = LocalDate.of(1999, 05, 22);
	final Usuario usuario = new Usuario("Luan", "luanparcival@gmail.com", "982580230",
        "1234", nascimento);
        
        when(usuarioRepository.findOne(1L)).thenReturn(usuario);
        
        final Usuario usuario2 = usuarioService.loadById(1L);
        
        assertEquals(usuario.getNome(), usuario2.getNome());
        assertEquals(usuario.getEmail(), usuario2.getEmail());
        assertEquals(usuario.getImagemPerfil(), usuario2.getImagemPerfil());
        assertEquals(usuario.getTelefone(), usuario2.getTelefone());
        assertEquals(usuario.getSenha(), usuario2.getSenha());
        assertEquals(usuario.getNascimento(), usuario2.getNascimento());
    }
}
