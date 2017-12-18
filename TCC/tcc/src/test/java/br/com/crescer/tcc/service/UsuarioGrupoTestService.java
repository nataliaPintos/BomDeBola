/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.tcc.service;

/**
 *
 * @author luanp
 */
import br.com.crescer.tcc.Models.GrupoModel;
import br.com.crescer.tcc.Models.UsuarioGrupoModel;
import br.com.crescer.tcc.Models.UsuarioModel;
import br.com.crescer.tcc.Repository.GrupoRepository;
import br.com.crescer.tcc.entity.Grupo;
import br.com.crescer.tcc.entity.Usuario;
import br.com.crescer.tcc.entity.UsuarioGrupo;
import java.time.LocalDate;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import br.com.crescer.tcc.Repository.UsuarioGrupoRepository;
import br.com.crescer.tcc.Repository.UsuarioRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.Ignore;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RunWith(MockitoJUnitRunner.class)
public class UsuarioGrupoTestService {
    @Mock
    private UsuarioGrupoRepository usuarioGrupoRepository;
    
    @Mock
    private UsuarioRepository usuarioRepository;
    
    @Mock
    private GrupoRepository grupoRepository;
    
    @InjectMocks
    private UsuarioGrupoService usuarioGrupoService;
    
    @InjectMocks
    private UsuarioService usuarioService;
    
    @InjectMocks
    private GrupoService grupoService;
    
    @Test
    public void testarLoadById() {
        Grupo grupo = new Grupo();
        grupo.setDiaSemana(2);
        grupo.setDiasConfirmacao(1);
        grupo.setImagem("img_grupo");
        grupo.setLatitude(12345);
        grupo.setLongitude(54321);
        grupo.setNome("Grupo");
        grupo.setTimeMax(16);
        grupo.setTimeMin(14);
        
        Usuario usuario = new Usuario();
        usuario.setNome("Luan");
        usuario.setEmail("luanparcival@gmail.com");
        usuario.setTelefone("982580230");
        usuario.setSenha("1234");
        usuario.setImagemPerfil("img");
        LocalDate nascimento = LocalDate.of(1999, 05, 22);
        usuario.setNascimento(nascimento);
        
        UsuarioGrupo ug = new UsuarioGrupo();
        ug.setAdm(true);
        ug.setGrupo(grupo);
        ug.setUsuario(usuario);
        
        
        when(usuarioGrupoRepository.findOne(1L)).thenReturn(ug);
        
        final UsuarioGrupo ug2 = usuarioGrupoService.loadById(1L);
        
        assertEquals(ug.getGrupo(), ug2.getGrupo());
        assertEquals(ug.getGrupo(), grupo);
        assertEquals(ug.getUsuario(), ug2.getUsuario());
        assertEquals(ug.getUsuario(), usuario);
        assertEquals(ug.getAdm(), ug2.getAdm());
    }
    
    @Test
    public void testeDelete() {
        usuarioGrupoService.delete(1L);
        verify(usuarioGrupoRepository).delete(1L);
    }
    
    @Test
    public void testFindAll() {
        Grupo grupo = new Grupo();
        grupo.setDiaSemana(2);
        grupo.setDiasConfirmacao(1);
        grupo.setImagem("img_grupo");
        grupo.setLatitude(12345);
        grupo.setLongitude(54321);
        grupo.setNome("Grupo");
        grupo.setTimeMax(16);
        grupo.setTimeMin(14);
        
        Usuario usuario = new Usuario();
        usuario.setNome("Luan");
        usuario.setEmail("luanparcival@gmail.com");
        usuario.setTelefone("982580230");
        usuario.setSenha("1234");
        usuario.setImagemPerfil("img");
        LocalDate nascimento = LocalDate.of(1999, 05, 22);
        usuario.setNascimento(nascimento);
        
        UsuarioGrupo ug = new UsuarioGrupo();
        ug.setAdm(true);
        ug.setGrupo(grupo);
        ug.setUsuario(usuario);
                
        List all = new ArrayList();
        all.add(ug);
        
        when(usuarioGrupoRepository.findAll()).thenReturn(all); 
                
        usuarioGrupoService.lista();
        verify(usuarioGrupoRepository).findAll();
    }
}
