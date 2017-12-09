/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.tcc.service;

import br.com.crescer.tcc.Models.GrupoModel;
import br.com.crescer.tcc.entity.Grupo;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import br.com.crescer.tcc.Repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author luanp
 */

@Service
@RequiredArgsConstructor
public class GrupoService {
        @Autowired
	private final GrupoRepository grupoRepository;
        
        public Grupo loadById(Long id) {
		return grupoRepository.findOne(id);
	}
        
        public List<Grupo> lista() {
		return (List<Grupo>) grupoRepository.findAll();
	}

	public void save(Grupo grupo) {
		grupo = grupoRepository.save(grupo);
	}
        
        public Grupo update(GrupoModel grupoModel, Grupo grupo) {
            grupo.setDia_semana(grupoModel.dia_semana);
            grupo.setDias_confirmacao(grupoModel.dias_confirmacao);
            grupo.setHora_final(grupoModel.hora_final);
            grupo.setHora_inicio(grupoModel.hora_inicio);
            grupo.setHoras_confirmacao(grupoModel.horas_confirmacao);
            grupo.setImagem(grupoModel.imagem);
            grupo.setLatitude(grupoModel.latitude);
            grupo.setLongitude(grupoModel.longitude);
            grupo.setNome(grupoModel.nome);
            grupo.setTime_max(grupoModel.time_max);
            grupo.setTime_min(grupoModel.time_min);
            return grupoRepository.save(grupo);
	}
        
        public void delete(Long id) {
                grupoRepository.delete(id);
	}
}
