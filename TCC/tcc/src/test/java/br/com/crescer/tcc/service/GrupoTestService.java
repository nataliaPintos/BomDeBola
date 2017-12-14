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
import br.com.crescer.tcc.Repository.GrupoRepository;
import br.com.crescer.tcc.entity.Grupo;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GrupoTestService {
    @Mock
    private GrupoRepository grupoRepository;
    
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
        
        when(grupoRepository.findOne(1L)).thenReturn(grupo);
        
        final Grupo grupo2 = grupoService.loadById(1L);
        
        assertEquals(grupo.getDiaSemana(), grupo2.getDiaSemana());
        assertEquals(grupo.getDiasConfirmacao(), grupo2.getDiasConfirmacao());
        assertEquals(grupo.getImagem(), grupo2.getImagem());
        assertEquals(grupo.getLatitude(), grupo2.getLatitude(), 0.001);
        assertEquals(grupo.getLongitude(), grupo2.getLongitude(), 0.001);
        assertEquals(grupo.getNome(), grupo2.getNome());
        assertEquals(grupo.getTimeMax(), grupo2.getTimeMax());
        assertEquals(grupo.getTimeMin(), grupo2.getTimeMin());
    }
}
