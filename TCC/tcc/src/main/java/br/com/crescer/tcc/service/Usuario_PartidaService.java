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
import java.util.Collections;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
        
        public ResponseEntity update(Long id) {
            Usuario_Partida usuario_partida = usuario_partidaRepository.findOne(id);
            Partida partida = usuario_partida.getPartida();
            if(usuario_partida == null){
                if(partida.getTime_atual() < partida.getTime_max()){
                    partida.setTime_atual(partida.getTime_atual() + 1);
                    partidaRepository.save(partida);
                    usuario_partida.setSolicitacao(false);
                    usuario_partidaRepository.save(usuario_partida);
                    return ResponseEntity.ok().body(usuario_partida);
                }else{
                    return ResponseEntity.badRequest().body("O time já está cheio");
                }
            }else{
                return ResponseEntity.badRequest().body("Usuario não está registrado na partida");
            }
	}
        
        public void delete(Long id) {
                usuario_partidaRepository.delete(id);
	}
        
        public List<Usuario_Partida> listaDeParticipantes(Long id) {
		return usuario_partidaRepository.findByIdAndSolicitacao(id, false);
	}
        
        public List<Usuario_Partida> findByPartida(Partida partida) {
		return usuario_partidaRepository.findByPartida(partida);
	}
        
        public List<Usuario_Partida> sortearTime(Long id) {
            List<Usuario_Partida> lista = usuario_partidaRepository.findByIdAndSolicitacao(id, false);
            int tamanho = lista.size();
            Collections.shuffle(lista);
            if(tamanho % 2 != 0){
                Random gerador = new Random();
                if(gerador.nextInt() % 2 == 0){
                    lista.get(tamanho).setTime('A');
                }else{
                    lista.get(tamanho).setTime('B');
                }
                tamanho--;
            }
            for(int i = 0; i < tamanho; i++){
                if(i % 2 == 0){
                    lista.get(i).setTime('A');
                }else{
                    lista.get(i).setTime('B');
                }
            }
            return lista;
	}
}
