package br.com.crescer.tcc.service;

import br.com.crescer.tcc.Repository.UsuarioRepository;
import br.com.crescer.tcc.entity.Usuario;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author carloshenrique
 */
@RunWith(MockitoJUnitRunner.class)
public class ExemploServiceTest {

    @Mock
    //private Exemplo2Service exemplo2Service;
    private UsuarioRepository usuarioRepository;
    
    @InjectMocks
    //private ExemploService exemploService;
    private UsuarioService usuarioService;

    /**
     * Test of get method, of class ExemploService.
     */
    /*@Test
    public void testGet() {
        
        when(exemplo2Service.getTen()).thenReturn(BigDecimal.ONE);
        
        final BigDecimal bigDecimal = exemploService.get();
        
        assertEquals(BigDecimal.ONE,  bigDecimal);
        
        Mockito.verify(exemplo2Service).save(BigDecimal.ONE);

    }*/
    
    @Test
    public void testarFindByEmail() {
        Usuario usuario = new Usuario();
        usuario.setNome("Luan");
        usuario.setEmail("luanparcival@gmail.com");
        usuario.setImagem_perfil("img");
        usuario.setSenha("1234");
        usuario.setTelefone("982580230");
        when(usuarioRepository.findByEmailIgnoreCase("luanparcival@gmail.com")).thenReturn(usuario);
        
        final Usuario usuario2 = usuarioService.findByEmail("luanparcival@gmail.com");
        
        assertEquals(usuario.getNome(), usuario2.getNome());
        assertEquals(usuario.getEmail(), usuario2.getEmail());
        assertEquals(usuario.getImagem_perfil(), usuario2.getImagem_perfil());
        assertEquals(usuario.getTelefone(), usuario2.getTelefone());
        assertEquals(usuario.getSenha(), usuario2.getSenha());
    }
}
