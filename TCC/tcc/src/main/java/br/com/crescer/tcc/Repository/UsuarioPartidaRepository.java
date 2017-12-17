/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.tcc.Repository;

import br.com.crescer.tcc.entity.Partida;
import br.com.crescer.tcc.entity.UsuarioGrupo;
import br.com.crescer.tcc.entity.UsuarioPartida;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author luanp
 */
public interface UsuarioPartidaRepository extends PagingAndSortingRepository<UsuarioPartida, Long> {
    
    List<UsuarioPartida> findByIdAndSolicitacao(Long id, boolean solicitacao);
    
    List<UsuarioPartida> findByPartida(Partida partida);
    
    
    List<UsuarioPartida> findByPartidaIdAndSolicitacao(Long id, boolean solicitacao);
    
    List<UsuarioPartida> findByUsuarioGrupoAndSolicitacao(UsuarioGrupo usuarioGrupo, boolean solicitacao);


    
}
