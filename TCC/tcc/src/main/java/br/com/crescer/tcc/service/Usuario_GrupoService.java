/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.tcc.service;

import br.com.crescer.tcc.Repository.UsuarioRepository;
import br.com.crescer.tcc.Repository.Usuario_GrupoRepository;
import br.com.crescer.tcc.entity.Grupo;
import br.com.crescer.tcc.entity.Usuario;
import br.com.crescer.tcc.entity.Usuario_Grupo;
import java.util.ArrayList;
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
    private final UsuarioRepository usuarioRepository;
    private final EmailService emailService;
    
    public Usuario_Grupo loadById(Long id) {
		return usuario_grupoRepository.findOne(id);
	}
        
        public List<Usuario_Grupo> lista() {
		return (List<Usuario_Grupo>) usuario_grupoRepository.findAll();
	}

	public void save(Usuario_Grupo usuario_grupo, String email) {
            emailService.enviarEmail(email, emailService.grupo);
            usuario_grupo = usuario_grupoRepository.save(usuario_grupo);
	}
        
        public void delete(Long id) {
                usuario_grupoRepository.delete(id);
	}
        
        public List<Usuario_Grupo> findByGrupo(Grupo grupo) {
                return usuario_grupoRepository.findByGrupo(grupo);
	}
        
        public List<Grupo> listaGrupo(Long id) {
                Usuario usuario = usuarioRepository.findOne(id);
                List<Grupo> listaGrupo = new ArrayList();
                List<Usuario_Grupo> listaUG = usuario_grupoRepository.findByUsuario(usuario);
                for(Usuario_Grupo usuario_grupo : listaUG){
                    listaGrupo.add(usuario_grupo.getGrupo());
                }
                return listaGrupo;
	}
}
