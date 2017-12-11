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
        if(partida.getHora_final() == null){partida.setHora_final(grupo.getHora_final());}
        if(partida.getHora_inicio() == null){partida.setHora_inicio(grupo.getHora_inicio());}
        if(partida.getHoras_confirmacao() == null){partida.setHoras_confirmacao(grupo.getHoras_confirmacao());}
        if(partida.getTempo_avaliacao() == null){partida.setTempo_avaliacao(grupo.getTempo_avaliacao());}
        if(partida.getDia_semana() == 0){partida.setDia_semana(grupo.getDia_semana());}
        if(partida.getDias_confirmacao() == 0){partida.setDias_confirmacao(grupo.getDias_confirmacao());}
        if(partida.getLatitude() == 0){partida.setLatitude(grupo.getLatitude());}
        if(partida.getLongitude() == 0){partida.setLongitude(grupo.getLongitude());}
        if(partida.getTime_max() == 0){partida.setTime_max(grupo.getTime_max());}
        if(partida.getTime_min() == 0){partida.setTime_min(grupo.getTime_min());}
        
        //Carregar todos Usuario_Grupo onde Grupo = grupo para então gazer um for e criar um Usuario_Partida para cada Usuario_Grupo carregado
        List<Usuario_Grupo> listaUsuario_Grupo = usuario_grupoRepository.findByGrupoAndSolicitacao(grupo, false);
        for(Usuario_Grupo usuario_grupo : listaUsuario_Grupo){
            Usuario_Partida usuario_partida = new Usuario_Partida(partida, usuario_grupo);
            usuario_partidaRepository.save(usuario_partida);
        }
	partida = partidaRepository.save(partida);
    }
    
    public Partida update(PartidaModel partidaModel, Partida partida) {
            partida.setDia_semana(partidaModel.dia_semana);
            partida.setDias_confirmacao(partidaModel.dias_confirmacao);
            partida.setHora_final(partidaModel.hora_final);
            partida.setHora_inicio(partidaModel.hora_inicio);
            partida.setHoras_confirmacao(partidaModel.horas_confirmacao);
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
