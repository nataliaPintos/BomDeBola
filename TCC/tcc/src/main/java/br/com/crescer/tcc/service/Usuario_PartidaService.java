/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.tcc.service;

import br.com.crescer.tcc.Repository.PartidaRepository;
import br.com.crescer.tcc.Repository.Usuario_PartidaRepository;
import br.com.crescer.tcc.entity.Partida;
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
public class Usuario_PartidaService {
    @Autowired
    private final Usuario_PartidaRepository usuario_partidaRepository;
    private final PartidaRepository partidaRepository;
    
    public Usuario_Partida loadById(Long id) {
		return usuario_partidaRepository.findOne(id);
	}
        
        public List<Usuario_Partida> lista() {
		return (List<Usuario_Partida>) usuario_partidaRepository.findAll();
	}

	public void save(Usuario_Partida usuario_partida) {
		usuario_partida = usuario_partidaRepository.save(usuario_partida);
	}
        
        public boolean update(Usuario_Partida usuario_partida, Partida partida) {
            if(partida.getTime_atual() < partida.getTime_max()){
                partida.setTime_atual(partida.getTime_atual() + 1);
                partidaRepository.save(partida);
                usuario_partida.setSolicitacao(false);
                usuario_partidaRepository.save(usuario_partida);
                return true;
            }else{
                return false;
            }
	}
        
        public void delete(Long id) {
                usuario_partidaRepository.delete(id);
	}
        
        public List<Usuario_Partida> listaDeParticipantes(Long id) {
		return usuario_partidaRepository.findByIdAndSolicitacao(id, false);
	}
}
