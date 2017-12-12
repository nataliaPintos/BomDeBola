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
import br.com.crescer.tcc.Repository.Usuario_GrupoRepository;
import br.com.crescer.tcc.entity.Usuario;
import br.com.crescer.tcc.entity.Usuario_Grupo;
import br.com.crescer.tcc.utilitarios.UsuarioComponente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author luanp
 */

@Service
@RequiredArgsConstructor
public class GrupoService {
        @Autowired
	private final GrupoRepository grupoRepository;
        private final Usuario_GrupoRepository usuario_grupoRepository;
        private final UsuarioComponente usuarioComponente;
        
        public Grupo loadById(Long id) {
		return grupoRepository.findOne(id);
	}
        
        public List<Grupo> lista() {
		return (List<Grupo>) grupoRepository.findAll();
	}

	public void save(Grupo grupo) {
		grupo = grupoRepository.save(grupo);
                Usuario usuario = usuarioComponente.usuarioLogadoDetalhes();
                Usuario_Grupo usuario_grupo = new Usuario_Grupo(usuario, grupo);
                usuario_grupo.setAdm(true);
                usuario_grupoRepository.save(usuario_grupo);
	}
        
        public boolean update(GrupoModel grupoModel, Grupo grupo) {
            if(grupo == null){
                return false;
            }else{
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
                grupoRepository.save(grupo);
                return true;
            }
	}
        
        public void delete(Long id) {
                grupoRepository.delete(id);
	}
}
