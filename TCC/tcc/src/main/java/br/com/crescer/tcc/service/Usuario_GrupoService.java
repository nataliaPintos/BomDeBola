/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.tcc.service;

import br.com.crescer.tcc.Repository.Usuario_GrupoRepository;
import br.com.crescer.tcc.entity.Grupo;
import br.com.crescer.tcc.entity.Usuario;
import br.com.crescer.tcc.entity.Usuario_Grupo;
import br.com.crescer.tcc.utilitarios.UsuarioComponente;
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
public class Usuario_GrupoService {
    @Autowired
    private final Usuario_GrupoRepository usuario_grupoRepository;
    private final EmailService emailService;
    private final UsuarioComponente usuarioComponente;
    
    public Usuario_Grupo loadById(Long id) {
		return usuario_grupoRepository.findOne(id);
	}
        
        public List<Usuario_Grupo> lista() {
		return (List<Usuario_Grupo>) usuario_grupoRepository.findAll();
	}

	public void save(Usuario_Grupo usuario_grupo, String email) {
		usuario_grupo = usuario_grupoRepository.save(usuario_grupo);
                Usuario usuarioLogado = usuarioComponente.usuarioLogadoDetalhes();
                emailService.enviarEmail(email, usuarioLogado.getNome()+emailService.grupo);
	}
        
        public void delete(Long id) {
                usuario_grupoRepository.delete(id);
	}
        
        public List<Usuario_Grupo> findByGrupo(Grupo grupo) {
                return usuario_grupoRepository.findByGrupo(grupo);
	}
}
