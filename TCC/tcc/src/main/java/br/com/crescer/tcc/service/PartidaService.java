/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.tcc.service;

import br.com.crescer.tcc.Models.PartidaModelGet;
import br.com.crescer.tcc.Models.PartidaModelPost;
import br.com.crescer.tcc.Repository.GrupoRepository;
import br.com.crescer.tcc.Repository.PartidaRepository;
import br.com.crescer.tcc.entity.Grupo;
import br.com.crescer.tcc.entity.Partida;
import br.com.crescer.tcc.entity.UsuarioGrupo;
import br.com.crescer.tcc.entity.UsuarioPartida;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import br.com.crescer.tcc.Repository.UsuarioGrupoRepository;
import br.com.crescer.tcc.Repository.UsuarioPartidaRepository;
import static java.time.temporal.ChronoUnit.DAYS;

/**
 *
 * @author luanp
 */
@Service
@RequiredArgsConstructor
public class PartidaService {
    
    @Autowired
    private final PartidaRepository partidaRepository;

    @Autowired    
    private final UsuarioGrupoRepository usuarioGrupoRepository;
    
    @Autowired
    private final UsuarioPartidaRepository usuarioPartidaRepository;
    
    @Autowired
    private final GrupoRepository grupoRepository;
    
    @Autowired
    private final EmailService emailService;

    
    public Partida loadById(Long id) {
	return partidaRepository.findOne(id);
    }
        
    public List<Partida> lista(Long idGrupo) {
        Grupo grupo = grupoRepository.findOne(idGrupo);
	return (List<Partida>) partidaRepository.findByGrupoOrderByDiaSemanaDesc(grupo);
    }

    public ResponseEntity save(PartidaModelPost partidaModel) {
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
    
    public PartidaModelGet partidaModelRetorno(Long idGrupo){
        Grupo grupo = grupoRepository.findOne(idGrupo);
        PartidaModelGet partidaModel = new PartidaModelGet();
        partidaModel.setHoraFinal(grupo.getHoraFinal());
        partidaModel.setHoraInicio(grupo.getHoraInicio());
        partidaModel.setIdGrupo(grupo.getId());
        partidaModel.setLatitude(grupo.getLatitude());
        partidaModel.setLongitude(grupo.getLongitude());
        partidaModel.setTimeMax(grupo.getTimeMax());
        partidaModel.setTimeMin(grupo.getTimeMin());
        
        //calcula proximo dia do jogo
        LocalDateTime diaDoJogo = LocalDateTime.now().truncatedTo(DAYS);
        diaDoJogo = diaDoJogo.with(TemporalAdjusters.next(DayOfWeek.of(grupo.getDiaSemana())));
        partidaModel.setDiaSemana(diaDoJogo);
        
        //calcula a data e hora limite para avaliacao - de acordo com o numero de horas estabelecidas
        LocalDateTime dataLimiteAvaliacao = LocalDateTime.now().truncatedTo(DAYS);
        dataLimiteAvaliacao = dataLimiteAvaliacao.with(TemporalAdjusters.next(DayOfWeek.of(grupo.getDiaSemana())));
        
        if ((grupo.getTempoAvaliacao().getMinute() + grupo.getTempoAvaliacao().getHour()) == 0){
            dataLimiteAvaliacao = dataLimiteAvaliacao.with(TemporalAdjusters.next(DayOfWeek.of(grupo.getDiaSemana()+1)));
            dataLimiteAvaliacao = dataLimiteAvaliacao.withHour(grupo.getHoraFinal().getHour());
            dataLimiteAvaliacao = dataLimiteAvaliacao.withMinute(grupo.getHoraFinal().getMinute());
        } else {
            dataLimiteAvaliacao = dataLimiteAvaliacao.withHour(grupo.getHoraFinal().getHour() + grupo.getTempoAvaliacao().getHour());
            dataLimiteAvaliacao = dataLimiteAvaliacao.withMinute(grupo.getHoraFinal().getMinute() + grupo.getTempoAvaliacao().getMinute());
        }
        
        partidaModel.setTempoAvaliacao(dataLimiteAvaliacao);
        
        //calcula a data e hora limite cofirmar a participação na partida 
        LocalDateTime diaDaConfirmacao = LocalDateTime.now().truncatedTo(DAYS);
        diaDaConfirmacao = diaDaConfirmacao.with(TemporalAdjusters.next(DayOfWeek.of(grupo.getDiasConfirmacao())));
        diaDaConfirmacao = diaDaConfirmacao.withHour(grupo.getHorasConfirmacao().getHour());
        diaDaConfirmacao = diaDaConfirmacao.withMinute(grupo.getHorasConfirmacao().getMinute());
        partidaModel.setTempoConfirmacao(diaDaConfirmacao);
        
        return partidaModel;
    }
    
    public Partida update(PartidaModelPost partidaModel, Partida partida) {
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
    
    public Partida confirmarPartida(Long id) {
        Partida partida = partidaRepository.findOne(id);
        if(partida != null) {
            partida.setConfirmada(true);
            return partidaRepository.save(partida);
        } else {
            return null;
        }
    }
    
    
    public void delete(Long id) {
        partidaRepository.delete(id);
    }
}
