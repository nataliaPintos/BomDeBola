/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.tcc.Repository;

import br.com.crescer.tcc.entity.Partida;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author luanp
 */
public interface PartidaRepository extends PagingAndSortingRepository<Partida, Long> {
    
}
