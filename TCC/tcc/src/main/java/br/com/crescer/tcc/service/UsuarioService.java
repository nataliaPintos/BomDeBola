/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.tcc.service;

import br.com.crescer.tcc.entity.Usuario;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import br.com.crescer.tcc.Repository.UsuarioRepository;
/**
 *
 * @author luan.avila
 */
@Service
@RequiredArgsConstructor
public class UsuarioService {
	private final UsuarioRepository usuarioRepository;

	public Usuario findByEmail(String username) {
		return usuarioRepository.findByEmailIgnoreCase(username);
	}
        
        public Usuario loadById(Long id) {
		return usuarioRepository.findOne(id);
	}
        
        public List<Usuario> lista() {
		return (List<Usuario>) usuarioRepository.findAll();
	}

	public void save(Usuario usuario) {
		final String senha = usuario.getSenha();
		usuario.setSenha(new BCryptPasswordEncoder().encode(senha));
		usuario = usuarioRepository.save(usuario);
	}
        
        public void delete(Long id) {
                usuarioRepository.delete(id);
	}
}
