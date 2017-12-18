package br.com.crescer.tcc.service;

import br.com.crescer.tcc.Repository.AvaliacaoRepository;
import br.com.crescer.tcc.entity.Avaliacao;
import br.com.crescer.tcc.entity.Grupo;
import br.com.crescer.tcc.entity.Partida;
import br.com.crescer.tcc.entity.Usuario;
import br.com.crescer.tcc.entity.UsuarioGrupo;
import br.com.crescer.tcc.entity.UsuarioPartida;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AvaliacaoTestService {
    @Mock
    private AvaliacaoRepository avaliacaoRepository;
    
    @InjectMocks
    private AvaliacaoService avaliacaoService;
    
    @Test
    public void testeLoadById() {
        LocalDate nascimento = LocalDate.of(1999, 05, 22);
	final Usuario usuario1 = new Usuario("Luan", "luanparcival@gmail.com", "982580230",
        "1234", nascimento);
        final Usuario usuario2 = new Usuario("Juca", "juca@gmail.com", "982580230",
        "1234", nascimento);
        
        LocalDateTime inicio = LocalDateTime.of(1999, 05, 22, 19, 00, 00);
        LocalDateTime finall = LocalDateTime.of(1999, 05, 22, 20, 00, 00);
        LocalDateTime confirmacao = LocalDateTime.now(); confirmacao.plusDays(2); confirmacao.plusHours(12); confirmacao.plusMinutes(30);
        LocalDateTime avaliacaoTime = LocalDateTime.now(); avaliacaoTime.plusHours(12); avaliacaoTime.plusMinutes(30);
        Grupo grupo = new Grupo("Grupo", "img", 16, 14, 8759, 8654864, 1, inicio, finall, 2,
        confirmacao, avaliacaoTime);
        
        UsuarioGrupo usuarioGrupo1 = new UsuarioGrupo(usuario1, grupo);
        UsuarioGrupo usuarioGrupo2 = new UsuarioGrupo(usuario2, grupo);
        
        LocalDate semana = LocalDate.of(2017, 05, 12);
        Partida partida = new Partida(16, 14, 8759, 8654864, semana, inicio, finall, confirmacao, avaliacaoTime, grupo);
        
        UsuarioPartida usuarioPartida1 = new UsuarioPartida(partida, usuarioGrupo1);
        UsuarioPartida usuarioPartida2 = new UsuarioPartida(partida, usuarioGrupo2);
        
        Avaliacao avaliacao1 = new Avaliacao(5, usuarioPartida1, usuarioPartida2);
        
        when(avaliacaoRepository.findOne(1L)).thenReturn(avaliacao1);
        
        final Avaliacao avaliacao2 = avaliacaoService.loadById(1L);
        
        assertEquals(avaliacao1.getNota(), avaliacao2.getNota());
        assertEquals(avaliacao1.getAvaliador(), avaliacao2.getAvaliador());
        assertEquals(avaliacao1.getAvaliado(), avaliacao2.getAvaliado());
    }
    
    @Test
    public void testeDelete() {
        avaliacaoService.delete(1L);
        verify(avaliacaoRepository).delete(1L);
    }
}
