/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.tcc.service;

import br.com.crescer.tcc.Repository.Usuario_PartidaRepository;
import br.com.crescer.tcc.entity.Grupo;
import br.com.crescer.tcc.entity.Partida;
import br.com.crescer.tcc.entity.Usuario;
import br.com.crescer.tcc.entity.Usuario_Grupo;
import br.com.crescer.tcc.entity.Usuario_Partida;
import java.time.LocalDate;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author luanp
 */
@RunWith(MockitoJUnitRunner.class)
public class Usuario_PartidaTest {
    
    @Mock
    private Usuario_PartidaRepository usuario_partidaRepository;
    
    @InjectMocks
    private Usuario_PartidaService usuario_partidaService;
    
    @Test
    public void testarLoadById() {
        Grupo grupo = new Grupo();
        grupo.setDia_semana(2);
        grupo.setDias_confirmacao(1);
        grupo.setImagem("img_grupo");
        grupo.setLatitude(12345);
        grupo.setLongitude(54321);
        grupo.setNome("Grupo");
        grupo.setTime_max(16);
        grupo.setTime_min(14);
        
        Usuario usuario = new Usuario();
        usuario.setNome("Luan");
        usuario.setEmail("luanparcival@gmail.com");
        usuario.setTelefone("982580230");
        usuario.setSenha("1234");
        usuario.setImagem_perfil("img");
        LocalDate nascimento = LocalDate.of(1999, 05, 22);
        usuario.setNascimento(nascimento);
        
        Usuario_Grupo ug = new Usuario_Grupo();
        ug.setSolicitacao(false);
        ug.setAdm(true);
        ug.setGrupo(grupo);
        ug.setUsuario(usuario);
        
        Partida partida = new Partida();
        partida.setDia_semana(2);
        partida.setDias_confirmacao(1);
        partida.setLatitude(12345);
        partida.setLongitude(54321);
        partida.setTime_max(16);
        partida.setTime_min(14);
        partida.setGrupo(grupo);
        
        Usuario_Partida up = new Usuario_Partida();
        up.setPartida(partida);
        up.setUsuario_grupo(ug);
        up.setGols(2);
        up.setNota_partida(4);
        up.setSolicitacao(false);
        up.setTime('A');
        when(usuario_partidaRepository.findOne(1L)).thenReturn(up);
        
        final Usuario_Partida up2 = usuario_partidaService.loadById(1L);
        
        assertEquals(up.getGols(), up2.getGols());
        assertEquals(up.getNota_partida(), up2.getNota_partida(), 0.001);
        assertEquals(up.getTime(), up2.getTime());
        assertEquals(up.getPartida(), up2.getPartida());
        assertEquals(up.getUsuario_grupo(), up2.getUsuario_grupo());
    }
}
