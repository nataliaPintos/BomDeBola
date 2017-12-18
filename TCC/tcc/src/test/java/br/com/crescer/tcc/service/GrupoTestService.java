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
import java.time.LocalDateTime;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
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
        LocalDateTime inicio = LocalDateTime.of(1999, 05, 22, 19, 00, 00);
        LocalDateTime finall = LocalDateTime.of(1999, 05, 22, 20, 00, 00);
        LocalDateTime confirmacao = LocalDateTime.now(); confirmacao.plusDays(2); confirmacao.plusHours(12); confirmacao.plusMinutes(30);
        LocalDateTime avaliacao = LocalDateTime.now(); avaliacao.plusHours(12); avaliacao.plusMinutes(30);
        Grupo grupo = new Grupo("Grupo", "img", 16, 14, 8759, 8654864, 1, inicio, finall, 2,
        confirmacao, avaliacao);
        
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
        assertEquals(grupo.getHoraFinal(), grupo2.getHoraFinal());
        assertEquals(grupo.getHoraInicio(), grupo2.getHoraInicio());
        assertEquals(grupo.getHorasConfirmacao(), grupo2.getHorasConfirmacao());
        assertEquals(grupo.getTempoAvaliacao(), grupo2.getTempoAvaliacao());
    }
    
    @Test
    public void testeDelete() {
        grupoService.delete(1L);
        verify(grupoRepository).delete(1L);
    }
}
