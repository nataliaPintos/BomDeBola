/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.tcc.service;

import br.com.crescer.tcc.Models.UsuarioModel;
import br.com.crescer.tcc.entity.Usuario;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import br.com.crescer.tcc.Repository.UsuarioRepository;
import br.com.crescer.tcc.utilitarios.UsuarioComponente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
/**
 *
 * @author luan.avila
 */
@Service
@RequiredArgsConstructor
public class UsuarioService {
        @Autowired
	private final UsuarioRepository usuarioRepository;
        private final UsuarioComponente usuarioComponente;

	public Usuario findByEmail(String username) {
		return usuarioRepository.findByEmailIgnoreCase(username);
	}
        
        public Usuario loadById(Long id) {
		return usuarioRepository.findOne(id);
	}
        
        public List<Usuario> lista() {
		return (List<Usuario>) usuarioRepository.findAll();
	}

	public ResponseEntity save(UsuarioModel usuarioModel) {
            Usuario usuarioExistente = usuarioRepository.findByEmailIgnoreCase(usuarioModel.getEmail());
            if(usuarioExistente == null){
                Usuario usuario = new Usuario(usuarioModel.getNome(), usuarioModel.getEmail(), usuarioModel.getTelefone(), usuarioModel.getSenha(), usuarioModel.getNascimento());
		final String senha = usuario.getSenha();
		usuario.setSenha(new BCryptPasswordEncoder().encode(senha));
		usuario = usuarioRepository.save(usuario);
                return ResponseEntity.ok(usuario);
            }else{
                return ResponseEntity.badRequest().body("Usuario já existente");
            }
	}
        
        public ResponseEntity update(UsuarioModel usuarioModel) {
            Usuario usuario = usuarioComponente.usuarioLogadoDetalhes();
            if(usuario != null){
                usuario.setEmail(usuarioModel.getEmail());
                usuario.setImagemPerfil(usuarioModel.getImagem_perfil());
                usuario.setNascimento(usuarioModel.getNascimento());
                usuario.setNome(usuarioModel.getNome());
                usuario.setSenha(new BCryptPasswordEncoder().encode(usuarioModel.getSenha()));
                usuario.setTelefone(usuarioModel.getTelefone());
                usuarioRepository.save(usuario);
                return ResponseEntity.ok().body(usuario);
            }else{
                return ResponseEntity.badRequest().body("Usuario inexistente");
            }
	}
        
        public void delete(Long id) {
                usuarioRepository.delete(id);
	}
}
