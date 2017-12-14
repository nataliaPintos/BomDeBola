/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.tcc.Repository;

import br.com.crescer.tcc.entity.Grupo;
import br.com.crescer.tcc.entity.Usuario;
import br.com.crescer.tcc.entity.UsuarioGrupo;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author luanp
 */
public interface UsuarioGrupoRepository extends PagingAndSortingRepository<UsuarioGrupo, Long> {
    
    List<UsuarioGrupo> findByGrupo(Grupo grupo);
    List<UsuarioGrupo> findByUsuario(Usuario usuario);
    
}
