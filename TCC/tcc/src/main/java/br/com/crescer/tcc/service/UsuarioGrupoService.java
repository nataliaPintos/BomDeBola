/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.tcc.service;

import br.com.crescer.tcc.Models.UsuarioGrupoModel;
import br.com.crescer.tcc.Repository.GrupoRepository;
import br.com.crescer.tcc.Repository.UsuarioRepository;
import br.com.crescer.tcc.entity.Grupo;
import br.com.crescer.tcc.entity.Usuario;
import br.com.crescer.tcc.entity.UsuarioGrupo;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import br.com.crescer.tcc.Repository.UsuarioGrupoRepository;

/**
 *
 * @author luanp
 */
@Service
@RequiredArgsConstructor
public class UsuarioGrupoService {
    @Autowired
    private final UsuarioGrupoRepository usuario_grupoRepository;
    private final UsuarioRepository usuarioRepository;
    private final GrupoRepository grupoRepository;
    private final EmailService emailService;
    
    public UsuarioGrupo loadById(Long id) {
		return usuario_grupoRepository.findOne(id);
	}
        
        public List<UsuarioGrupo> lista() {
		return (List<UsuarioGrupo>) usuario_grupoRepository.findAll();
	}

	public ResponseEntity save(UsuarioGrupoModel usuario_grupoModel) {
            Usuario usuario = usuarioRepository.findByEmailIgnoreCase(usuario_grupoModel.getEmailUsuario());
            Grupo grupo = grupoRepository.findOne(usuario_grupoModel.getIdGrupo());
            if(usuario == null || grupo == null){
                return ResponseEntity.badRequest().body("Informações não cadastradas");
            }else{
                UsuarioGrupo usuario_grupo = new UsuarioGrupo(usuario, grupo);
                emailService.enviarEmail(usuario.getEmail(), emailService.grupo);
                usuario_grupoRepository.save(usuario_grupo);
                return ResponseEntity.ok().body(usuario_grupo);
            }
	}
        
        public void delete(Long id) {
                usuario_grupoRepository.delete(id);
	}
        
        public List<UsuarioGrupo> findByGrupo(Long id) {
            Grupo grupo = grupoRepository.findOne(id);
                return usuario_grupoRepository.findByGrupo(grupo);
	}
        
        public List<Grupo> listaGrupo(Long id) {
                Usuario usuario = usuarioRepository.findOne(id);
                List<Grupo> listaGrupo = new ArrayList();
                List<UsuarioGrupo> listaUG = usuario_grupoRepository.findByUsuario(usuario);
                for(UsuarioGrupo usuario_grupo : listaUG){
                    listaGrupo.add(usuario_grupo.getGrupo());
                }
                return listaGrupo;
	}
}
