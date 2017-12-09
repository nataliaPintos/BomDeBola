/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.tcc.service;

import br.com.crescer.tcc.Repository.Usuario_GrupoRepository;
import br.com.crescer.tcc.entity.Usuario_Grupo;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author luanp
 */
@Service
@RequiredArgsConstructor
public class Usuario_GrupoService {
    private final Usuario_GrupoRepository usuario_grupoRepository;
    
    public Usuario_Grupo loadById(Long id) {
		return usuario_grupoRepository.findOne(id);
	}
        
        public List<Usuario_Grupo> lista() {
		return (List<Usuario_Grupo>) usuario_grupoRepository.findAll();
	}

	public void save(Usuario_Grupo usuario_grupo) {
		usuario_grupo = usuario_grupoRepository.save(usuario_grupo);
	}
        
        public void delete(Long id) {
                usuario_grupoRepository.delete(id);
	}
}
