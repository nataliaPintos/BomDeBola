/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.tcc.service;

import br.com.crescer.tcc.entity.Grupo;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import br.com.crescer.tcc.Repository.GrupoRepository;

/**
 *
 * @author luanp
 */

@Service
@RequiredArgsConstructor
public class GrupoService {
	private final GrupoRepository grupoRepository;
        
        public Grupo loadById(Long id) {
		return grupoRepository.findOne(id);
	}
        
        public List<Grupo> lista() {
		return (List<Grupo>) grupoRepository.findAll();
	}

	public void save(Grupo grupo) {
		grupo = grupoRepository.save(grupo);
	}
        
        public void delete(Long id) {
                grupoRepository.delete(id);
	}
}
