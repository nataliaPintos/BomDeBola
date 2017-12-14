/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.tcc.service;

import br.com.crescer.tcc.Models.PartidaModel;
import br.com.crescer.tcc.Repository.GrupoRepository;
import br.com.crescer.tcc.Repository.PartidaRepository;
import br.com.crescer.tcc.entity.Grupo;
import br.com.crescer.tcc.entity.Partida;
import br.com.crescer.tcc.entity.UsuarioGrupo;
import br.com.crescer.tcc.entity.UsuarioPartida;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import br.com.crescer.tcc.Repository.UsuarioGrupoRepository;
import br.com.crescer.tcc.Repository.UsuarioPartidaRepository;

/**
 *
 * @author luanp
 */
@Service
@RequiredArgsConstructor
public class PartidaService {
    @Autowired
    private final PartidaRepository partidaRepository;
    private final UsuarioGrupoRepository usuarioGrupoRepository;
    private final UsuarioPartidaRepository usuarioPartidaRepository;
    private final GrupoRepository grupoRepository;
    private final EmailService emailService;
    
    public Partida loadById(Long id) {
	return partidaRepository.findOne(id);
    }
        
    public List<Partida> lista() {
	return (List<Partida>) partidaRepository.findAll();
    }

    public ResponseEntity save(PartidaModel partidaModel) {
        Grupo grupo = grupoRepository.findOne(partidaModel.getIdGrupo());
        if(grupo == null){
            return ResponseEntity.badRequest().body("Grupo não cadastrado");
        }else{
            Partida partida = new Partida(partidaModel.getTimeMax(), partidaModel.getTimeMin(), partidaModel.getLatitude(),
                partidaModel.getLongitude(), partidaModel.getDiaSemana(), partidaModel.getHoraInicio(), partidaModel.getHoraFinal(),
                partidaModel.getTempoConfirmacao(), partidaModel.getTempoAvaliacao(), grupo);
            partidaRepository.save(partida);
            
            //Carregar todos UsuarioGrupo onde Grupo = grupo para então fazer um for e criar um UsuarioPartida para cada UsuarioGrupo carregado
            List<UsuarioGrupo> listaUsuarioGrupo = usuarioGrupoRepository.findByGrupo(grupo);
            for(UsuarioGrupo usuariogrupo : listaUsuarioGrupo){
                UsuarioPartida usuariopartida = new UsuarioPartida(partida, usuariogrupo);
                usuarioPartidaRepository.save(usuariopartida);
                emailService.enviarEmail(usuariogrupo.getUsuario().getEmail(), grupo.getNome()+emailService.partida);
            }
            return ResponseEntity.ok().body(partida);
        }
    }
    
    public PartidaModel partidaModelRetorno(Long idGrupo){
        Grupo grupo = grupoRepository.findOne(idGrupo);
        PartidaModel partidaModel = new PartidaModel();
        partidaModel.setHoraFinal(grupo.getHoraFinal());
        partidaModel.setHoraInicio(grupo.getHoraInicio());
        partidaModel.setIdGrupo(grupo.getId());
        partidaModel.setLatitude(grupo.getLatitude());
        partidaModel.setLongitude(grupo.getLongitude());
        partidaModel.setTempoAvaliacao(grupo.getTempoAvaliacao());
        partidaModel.setTimeMax(grupo.getTimeMax());
        partidaModel.setTimeMin(grupo.getTimeMin());
        
        LocalDate diaDoJogo = LocalDate.now();
        switch (grupo.getDiaSemana()) {
            case 1:
                diaDoJogo = diaDoJogo.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
                break;
            case 2:
                diaDoJogo = diaDoJogo.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
                break;
            case 3:
                diaDoJogo = diaDoJogo.with(TemporalAdjusters.next(DayOfWeek.TUESDAY));
                break;
            case 4:
                diaDoJogo = diaDoJogo.with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY));
                break;
            case 5:
                diaDoJogo = diaDoJogo.with(TemporalAdjusters.next(DayOfWeek.THURSDAY));
                break;
            case 6:
                diaDoJogo = diaDoJogo.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
                break;
            case 7:
                diaDoJogo = diaDoJogo.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
                break;
            default:
                break;
        }
        partidaModel.setDiaSemana(diaDoJogo);
        
        LocalDateTime diaDaConfirmacao = LocalDateTime.now();
        switch (grupo.getDiasConfirmacao()) {
            case 1:
                diaDaConfirmacao = diaDaConfirmacao.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
                break;
            case 2:
                diaDaConfirmacao = diaDaConfirmacao.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
                break;
            case 3:
                diaDaConfirmacao = diaDaConfirmacao.with(TemporalAdjusters.next(DayOfWeek.TUESDAY));
                break;
            case 4:
                diaDaConfirmacao = diaDaConfirmacao.with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY));
                break;
            case 5:
                diaDaConfirmacao = diaDaConfirmacao.with(TemporalAdjusters.next(DayOfWeek.THURSDAY));
                break;
            case 6:
                diaDaConfirmacao = diaDaConfirmacao.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
                break;
            case 7:
                diaDaConfirmacao = diaDaConfirmacao.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
                break;
            default:
                break;
        }
        diaDaConfirmacao.withHour(grupo.getHorasConfirmacao().getHour());
        diaDaConfirmacao.withMinute(grupo.getHorasConfirmacao().getMinute());
        diaDaConfirmacao.withSecond(grupo.getHorasConfirmacao().getSecond());
        partidaModel.setTempoConfirmacao(diaDaConfirmacao);
        return partidaModel;
    }
    
    public Partida update(PartidaModel partidaModel, Partida partida) {
            partida.setDiaSemana(partidaModel.getDiaSemana());
            partida.setTempoConfirmacao(partidaModel.getTempoConfirmacao());
            partida.setHoraFinal(partidaModel.getHoraFinal());
            partida.setHoraInicio(partidaModel.getHoraInicio());
            partida.setLatitude(partidaModel.getLatitude());
            partida.setLongitude(partidaModel.getLongitude());
            partida.setTimeMax(partidaModel.getTimeMax());
            partida.setTimeMin(partidaModel.getTimeMin());
            return partidaRepository.save(partida);
    }
    
    public void delete(Long id) {
        partidaRepository.delete(id);
    }
}
