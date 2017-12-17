/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.tcc.service;

import br.com.crescer.tcc.Repository.PartidaRepository;
import br.com.crescer.tcc.entity.Partida;
import br.com.crescer.tcc.entity.UsuarioPartida;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import br.com.crescer.tcc.Repository.UsuarioPartidaRepository;

/**
 *
 * @author luanp
 */
@Service
@RequiredArgsConstructor
public class UsuarioPartidaService {
    @Autowired
    private final UsuarioPartidaRepository usuarioPartidaRepository;
    private final PartidaRepository partidaRepository;
    
    public UsuarioPartida loadById(Long id) {
            return usuarioPartidaRepository.findOne(id);
    }

    public List<UsuarioPartida> lista() {
            return (List<UsuarioPartida>) usuarioPartidaRepository.findAll();
    }

    public void save(UsuarioPartida usuarioPartida) {
            usuarioPartida = usuarioPartidaRepository.save(usuarioPartida);
    }

    public ResponseEntity update(Long id) {
        UsuarioPartida usuarioPartida = usuarioPartidaRepository.findOne(id);
        Partida partida = usuarioPartida.getPartida();
        if(usuarioPartida != null){
            if(partida.getTimeAtual() < partida.getTimeMax()){
                partida.setTimeAtual(partida.getTimeAtual() + 1);
                partidaRepository.save(partida);
                usuarioPartida.setSolicitacao(false);
                usuarioPartidaRepository.save(usuarioPartida);
                return ResponseEntity.ok().body(usuarioPartida);
            }else{
                return ResponseEntity.badRequest().body("O time já está cheio");
            }
        }else{
            return ResponseEntity.badRequest().body("Usuario não está registrado na partida");
        }
    }

    public void delete(Long id) {
            usuarioPartidaRepository.delete(id);
    }

    public List<UsuarioPartida> listaDeParticipantes(Long id) {
            return usuarioPartidaRepository.findByIdAndSolicitacao(id, false);
    }

    public List<UsuarioPartida> listaDeParticipantesPartida(Long id) {
            return usuarioPartidaRepository.findByPartidaIdAndSolicitacao(id, false);
    }

    public List<UsuarioPartida> findByPartida(Partida partida) {
            return usuarioPartidaRepository.findByPartida(partida);
    }

    public List<UsuarioPartida> sortearTime(Long id) {
        List<UsuarioPartida> lista = usuarioPartidaRepository.findByPartidaIdAndSolicitacao(id, false);
        int tamanho = lista.size();
        Collections.shuffle(lista);

        for(int i = 0; i < tamanho; i++){
            if(i % 2 == 0){
                lista.get(i).setTime('A');
            }else{
                lista.get(i).setTime('B');
            }
        }
        usuarioPartidaRepository.save(lista);
        return lista;
    }
}
