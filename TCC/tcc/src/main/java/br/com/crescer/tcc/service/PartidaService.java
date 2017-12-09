/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.tcc.service;

import br.com.crescer.tcc.Models.PartidaModel;
import br.com.crescer.tcc.Repository.PartidaRepository;
import br.com.crescer.tcc.entity.Grupo;
import br.com.crescer.tcc.entity.Partida;
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
    
    public Partida loadById(Long id) {
	return partidaRepository.findOne(id);
    }
        
    public List<Partida> lista() {
	return (List<Partida>) partidaRepository.findAll();
    }

    public void save(Partida partida) {
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
