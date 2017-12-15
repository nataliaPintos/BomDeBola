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
import br.com.crescer.tcc.entity.Usuario;
import br.com.crescer.tcc.entity.UsuarioGrupo;
import br.com.crescer.tcc.utilitarios.UsuarioComponente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import br.com.crescer.tcc.Repository.UsuarioGrupoRepository;
import br.com.crescer.tcc.Repository.UsuarioRepository;

/**
 *
 * @author luanp
 */

@Service
@RequiredArgsConstructor
public class GrupoService {
        @Autowired
	private final GrupoRepository grupoRepository;
        
        @Autowired
        private final UsuarioGrupoRepository usuarioGrupoRepository;
        
        @Autowired
        private final UsuarioRepository usuarioRepository;
        
        @Autowired
        private final UsuarioComponente usuarioComponente;
        
        public Grupo loadById(Long id) {
		return grupoRepository.findOne(id);
	}
        
        public List<Grupo> lista() {
		return (List<Grupo>) grupoRepository.findAll();
	}

	public ResponseEntity<Grupo> save(GrupoModel grupoModel) {
                Grupo grupo = new Grupo(grupoModel.getNome(), grupoModel.getImagem(), grupoModel.getTimeMax(), grupoModel.getTimeMin(), 
                grupoModel.getLatitude(), grupoModel.getLongitude(), grupoModel.getDiaSemana(), grupoModel.getHoraInicio(),
                grupoModel.getHoraFinal(), grupoModel.getDiasConfirmacao(), grupoModel.getHorasConfirmacao(), grupoModel.getTempoAvaliacao());
                
		Grupo result = grupoRepository.save(grupo);
                UsuarioGrupo usuarioGrupo = new UsuarioGrupo(usuarioComponente.usuarioLogadoDetalhes(), grupo);
                usuarioGrupo.setAdm(true);
                usuarioGrupoRepository.save(usuarioGrupo);
                
                return ResponseEntity.ok().body(result);
	}
        
        public ResponseEntity<Grupo> update(GrupoModel grupoModel, Long id) {
            Grupo grupo = grupoRepository.findOne(id);
            if(grupo == null){
                return (ResponseEntity<Grupo>) ResponseEntity.badRequest();
            }else{
                grupo.setDiaSemana(grupoModel.getDiaSemana());
                grupo.setDiasConfirmacao(grupoModel.getDiasConfirmacao());
                grupo.setHoraFinal(grupoModel.getHoraFinal());
                grupo.setHoraInicio(grupoModel.getHoraInicio());
                grupo.setHorasConfirmacao(grupoModel.getHorasConfirmacao());
                grupo.setImagem(grupoModel.getImagem());
                grupo.setLatitude(grupoModel.getLatitude());
                grupo.setLongitude(grupoModel.getLongitude());
                grupo.setNome(grupoModel.getNome());
                grupo.setTimeMax(grupoModel.getTimeMax());
                grupo.setTimeMin(grupoModel.getTimeMin());
                return ResponseEntity.ok().body(grupoRepository.save(grupo));
            }
	}
        
        public void delete(Long id) {
                grupoRepository.delete(id);
	}
}
