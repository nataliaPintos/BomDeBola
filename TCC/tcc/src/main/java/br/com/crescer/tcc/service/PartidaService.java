/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.tcc.service;

import br.com.crescer.tcc.Models.PartidaModel;
import br.com.crescer.tcc.Repository.PartidaRepository;
import br.com.crescer.tcc.Repository.Usuario_GrupoRepository;
import br.com.crescer.tcc.Repository.Usuario_PartidaRepository;
import br.com.crescer.tcc.entity.Grupo;
import br.com.crescer.tcc.entity.Partida;
import br.com.crescer.tcc.entity.Usuario_Grupo;
import br.com.crescer.tcc.entity.Usuario_Partida;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author luanp
 */
@Service
@RequiredArgsConstructor
public class PartidaService {
    @Autowired
    private final PartidaRepository partidaRepository;
    private final Usuario_GrupoRepository usuario_grupoRepository;
    private final Usuario_PartidaRepository usuario_partidaRepository;
    private final EmailService emailService;
    
    public Partida loadById(Long id) {
	return partidaRepository.findOne(id);
    }
        
    public List<Partida> lista() {
	return (List<Partida>) partidaRepository.findAll();
    }

    public void save(Partida partida) {
	partida = partidaRepository.save(partida);
    }
    
    public void save(Partida partida, Grupo grupo) {
        //Carregar todos Usuario_Grupo onde Grupo = grupo para então gazer um for e criar um Usuario_Partida para cada Usuario_Grupo carregado
        List<Usuario_Grupo> listaUsuario_Grupo = usuario_grupoRepository.findByGrupo(grupo);
        for(Usuario_Grupo usuario_grupo : listaUsuario_Grupo){
            Usuario_Partida usuario_partida = new Usuario_Partida(partida, usuario_grupo);
            usuario_partidaRepository.save(usuario_partida);
            emailService.enviarEmail(usuario_grupo.getUsuario().getEmail(), grupo.getNome()+emailService.partida);
        }
	partida = partidaRepository.save(partida);
    }
    
    public PartidaModel partidaModelRetorno(PartidaModel partidaModel, Grupo grupo){
        partidaModel.hora_final = grupo.getHora_final();
        partidaModel.hora_inicio = grupo.getHora_inicio();
        partidaModel.id_grupo = grupo.getId();
        partidaModel.latitude = grupo.getLatitude();
        partidaModel.longitude = grupo.getLongitude();
        partidaModel.tempo_avaliacao = grupo.getTempo_avaliacao();
        partidaModel.time_max = grupo.getTime_max();
        partidaModel.time_min = grupo.getTime_min();
        
        LocalDate diaDoJogo = LocalDate.now();
        switch (grupo.getDia_semana()) {
            case 1:
                diaDoJogo.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
                break;
            case 2:
                diaDoJogo.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
                break;
            case 3:
                diaDoJogo.with(TemporalAdjusters.next(DayOfWeek.TUESDAY));
                break;
            case 4:
                diaDoJogo.with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY));
                break;
            case 5:
                diaDoJogo.with(TemporalAdjusters.next(DayOfWeek.THURSDAY));
                break;
            case 6:
                diaDoJogo.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
                break;
            case 7:
                diaDoJogo.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
                break;
            default:
                break;
        }
        
        LocalDateTime diaDaConfirmacao = LocalDateTime.now();
        switch (grupo.getDias_confirmacao()) {
            case 1:
                diaDaConfirmacao.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
                break;
            case 2:
                diaDaConfirmacao.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
                break;
            case 3:
                diaDaConfirmacao.with(TemporalAdjusters.next(DayOfWeek.TUESDAY));
                break;
            case 4:
                diaDaConfirmacao.with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY));
                break;
            case 5:
                diaDaConfirmacao.with(TemporalAdjusters.next(DayOfWeek.THURSDAY));
                break;
            case 6:
                diaDaConfirmacao.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
                break;
            case 7:
                diaDaConfirmacao.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
                break;
            default:
                break;
        }
        diaDaConfirmacao.withHour(grupo.getHoras_confirmacao().getHour());
        diaDaConfirmacao.withMinute(grupo.getHoras_confirmacao().getMinute());
        diaDaConfirmacao.withSecond(grupo.getHoras_confirmacao().getSecond());
        partidaModel.tempo_confirmacao = diaDaConfirmacao;
        return partidaModel;
    }
    
    public Partida update(PartidaModel partidaModel, Partida partida) {
            partida.setDia_semana(partidaModel.dia_semana);
            partida.setTempo_confirmacao(partidaModel.tempo_confirmacao);
            partida.setHora_final(partidaModel.hora_final);
            partida.setHora_inicio(partidaModel.hora_inicio);
            partida.setLatitude(partidaModel.latitude);
            partida.setLongitude(partidaModel.longitude);
            partida.setTime_max(partidaModel.time_max);
            partida.setTime_min(partidaModel.time_min);
            return partidaRepository.save(partida);
    }
    
    public void delete(Long id) {
        partidaRepository.delete(id);
    }
}
