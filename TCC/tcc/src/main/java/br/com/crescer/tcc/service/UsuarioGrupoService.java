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
    private final UsuarioGrupoRepository usuarioGrupoRepository;
    private final UsuarioRepository usuarioRepository;
    private final GrupoRepository grupoRepository;
    private final EmailService emailService;
    
    public UsuarioGrupo loadById(Long id) {
		return usuarioGrupoRepository.findOne(id);
	}
        
        public List<UsuarioGrupo> lista() {
		return (List<UsuarioGrupo>) usuarioGrupoRepository.findAll();
	}

	public ResponseEntity save(UsuarioGrupoModel usuarioGrupoModel) {
            Usuario usuario = usuarioRepository.findByEmailIgnoreCase(usuarioGrupoModel.getEmailUsuario());
            Grupo grupo = grupoRepository.findOne(usuarioGrupoModel.getIdGrupo());
            UsuarioGrupo usuarioGrupo = usuarioGrupoRepository.findByUsuarioAndGrupo(usuario, grupo);
            if(usuarioGrupo == null){
                if(grupo == null){
                    return ResponseEntity.badRequest().body("Grupo não cadastrado");
                }else if(usuario == null){
                    usuarioGrupoRepository.save(usuarioGrupo);
                    return ResponseEntity.ok().body("Usuario convidado");
                }else{
                    emailService.enviarEmail(usuario.getEmail(), emailService.grupo);
                    usuarioGrupoRepository.save(usuarioGrupo);
                    return ResponseEntity.ok().body(usuarioGrupo);
                }
                }else{
                    return ResponseEntity.badRequest().body("O usuario já foi convidado");
                }
	}
        
        public void delete(Long id) {
                usuarioGrupoRepository.delete(id);
	}
        
        public List<UsuarioGrupo> findByGrupo(Long id) {
            Grupo grupo = grupoRepository.findOne(id);
                return usuarioGrupoRepository.findByGrupo(grupo);
	}
        
        public List<Grupo> listaGrupo(Long id) {
                Usuario usuario = usuarioRepository.findOne(id);
                List<Grupo> listaGrupo = new ArrayList();
                List<UsuarioGrupo> listaUG = usuarioGrupoRepository.findByUsuario(usuario);
                for(UsuarioGrupo usuarioGrupo : listaUG){
                    listaGrupo.add(usuarioGrupo.getGrupo());
                }
                return listaGrupo;
	}
}
