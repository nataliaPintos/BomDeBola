/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.tcc.service;

import br.com.crescer.tcc.Repository.PartidaRepository;
import br.com.crescer.tcc.entity.Grupo;
import br.com.crescer.tcc.entity.Partida;
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
public class PartidaTest {
    @Mock
    private PartidaRepository partidaRepository;
    
    @InjectMocks
    private PartidaService partidaService;
    
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
        
        Partida partida = new Partida();
        partida.setLatitude(12345);
        partida.setLongitude(54321);
        partida.setTime_max(16);
        partida.setTime_min(14);
        partida.setGrupo(grupo);
        
        when(partidaRepository.findOne(1L)).thenReturn(partida);
        
        final Partida partida2 = partidaService.loadById(1L);
        
        assertEquals(partida.getLatitude(), partida2.getLatitude(), 0.001);
        assertEquals(partida.getLongitude(), partida2.getLongitude(), 0.001);
        assertEquals(partida.getTime_max(), partida2.getTime_max());
        assertEquals(partida.getTime_min(), partida2.getTime_min());
        assertEquals(partida.getGrupo(), partida2.getGrupo());
        assertEquals(partida.getGrupo(), grupo);
        assertEquals(partida2.getGrupo(), grupo);
    }
}
