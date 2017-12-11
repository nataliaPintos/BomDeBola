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
/**
 *
 * @author luan.avila
 */
@Service
@RequiredArgsConstructor
public class UsuarioService {
        @Autowired
	private final UsuarioRepository usuarioRepository;
        
        @Autowired
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

	public boolean save(Usuario usuario, UsuarioModel usuarioModel) {
            Usuario usuarioExistente = usuarioRepository.findByEmailIgnoreCase(usuarioModel.email);
            if(usuarioExistente == null){
		final String senha = usuario.getSenha();
		usuario.setSenha(new BCryptPasswordEncoder().encode(senha));
		usuario = usuarioRepository.save(usuario);
                return true;
            }else{
                return false;
            }
	}
        
        public Usuario update(UsuarioModel usuarioModel) {
            Usuario usuario = usuarioComponente.usuarioLogadoDetalhes();
            usuario.setEmail(usuarioModel.email);
            usuario.setImagem_perfil(usuarioModel.imagem_perfil);
            usuario.setNascimento(usuarioModel.nascimento);
            usuario.setNome(usuarioModel.nome);
            usuario.setSenha(new BCryptPasswordEncoder().encode(usuarioModel.senha));
            usuario.setTelefone(usuarioModel.telefone);
            return usuarioRepository.save(usuario);
	}
        
        public void delete(Long id) {
                usuarioRepository.delete(id);
	}
}
