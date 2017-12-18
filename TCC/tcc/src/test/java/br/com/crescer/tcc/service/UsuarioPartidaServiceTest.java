/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.tcc.service;

import br.com.crescer.tcc.entity.Grupo;
import br.com.crescer.tcc.entity.Partida;
import br.com.crescer.tcc.entity.Usuario;
import br.com.crescer.tcc.entity.UsuarioGrupo;
import br.com.crescer.tcc.entity.UsuarioPartida;
import java.time.LocalDate;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import br.com.crescer.tcc.Repository.UsuarioPartidaRepository;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Ignore;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author luanp
 */
@RunWith(MockitoJUnitRunner.class)
public class UsuarioPartidaServiceTest {
    
    @Mock
    private UsuarioPartidaRepository usuarioPartidaRepository;
    
    @InjectMocks
    private UsuarioPartidaService usuarioPartidaService;
    
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
        
        Partida partida = new Partida();
        partida.setLatitude(12345);
        partida.setLongitude(54321);
        partida.setTimeMax(16);
        partida.setTimeMin(14);
        partida.setGrupo(grupo);
        
        UsuarioPartida up = new UsuarioPartida();
        up.setPartida(partida);
        up.setUsuarioGrupo(ug);
        up.setGols(2);
        up.setNotaPartida(4);
        up.setSolicitacao(false);
        up.setTime('A');
        when(usuarioPartidaRepository.findOne(1L)).thenReturn(up);
        
        final UsuarioPartida up2 = usuarioPartidaService.loadById(1L);
        
        assertEquals(up.getGols(), up2.getGols());
        assertEquals(up.getNotaPartida(), up2.getNotaPartida(), 0.001);
        assertEquals(up.getTime(), up2.getTime());
        assertEquals(up.getPartida(), up2.getPartida());
        assertEquals(up.getUsuarioGrupo(), up2.getUsuarioGrupo());
    }
    
    @Test
    public void testeDelete() {
        usuarioPartidaService.delete(1L);
        verify(usuarioPartidaRepository).delete(1L);
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
        
        Partida partida = new Partida();
        partida.setLatitude(12345);
        partida.setLongitude(54321);
        partida.setTimeMax(16);
        partida.setTimeMin(14);
        partida.setGrupo(grupo);
        
        UsuarioPartida up = new UsuarioPartida();
        up.setPartida(partida);
        up.setUsuarioGrupo(ug);
        up.setGols(2);
        up.setNotaPartida(4);
        up.setSolicitacao(false);
        up.setTime('A');
                
        List all = new ArrayList();
        all.add(up);
        
        when(usuarioPartidaRepository.findAll()).thenReturn(all); 
                
        usuarioPartidaService.lista();
        verify(usuarioPartidaRepository).findAll();
    }
}
