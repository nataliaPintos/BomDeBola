/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.tcc.service;

import br.com.crescer.tcc.Models.AvaliacaoModel;
import br.com.crescer.tcc.Repository.AvaliacaoRepository;
import br.com.crescer.tcc.entity.Avaliacao;
import br.com.crescer.tcc.entity.UsuarioPartida;
import java.util.List;
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
public class AvaliacaoService {
    @Autowired
	private final AvaliacaoRepository avaliacaoRepository;
        private final UsuarioPartidaRepository usuario_partidaRepository;
    
    public Avaliacao loadById(Long id) {
	return avaliacaoRepository.findOne(id);
    }
    
    public List<Avaliacao> lista() {
	return (List<Avaliacao>) avaliacaoRepository.findAll();
    }
    
    public Avaliacao loadByUsuario(Long id) {
        UsuarioPartida usuario_partida = usuario_partidaRepository.findOne(id);
	return avaliacaoRepository.findByAvaliador(usuario_partida);
    }
    
    public ResponseEntity save(AvaliacaoModel avaliacaoModel){
        UsuarioPartida avaliador = usuario_partidaRepository.findOne(avaliacaoModel.getIdAvaliador());
        Avaliacao avaliacaoExistente = avaliacaoRepository.findByAvaliador(avaliador);
        if(avaliacaoExistente == null){
            UsuarioPartida avaliado = usuario_partidaRepository.findOne(avaliacaoModel.getIdAvaliado());
            Avaliacao avaliacao = new Avaliacao(avaliacaoModel.getNota(), avaliador, avaliado);
            avaliado.setAvaliacoes(avaliado.getAvaliacoes()+1);
            avaliado.setNotaPartida((avaliado.getNotaPartida()+avaliacaoModel.getNota())/avaliado.getAvaliacoes());
            avaliacaoRepository.save(avaliacao);
            usuario_partidaRepository.save(avaliado);
            return ResponseEntity.ok().body(avaliacao);
        }else{
            return ResponseEntity.badRequest().body("Usuario já avaliado");
        }
    }
}
