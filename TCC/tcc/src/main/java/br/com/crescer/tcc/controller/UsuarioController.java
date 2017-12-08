/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.tcc.controller;

import br.com.crescer.tcc.Models.UsuarioModel;
import br.com.crescer.tcc.entity.Usuario;
import br.com.crescer.tcc.service.UsuarioService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author luan.avila
 */
@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {
    
    private final UsuarioService usuarioService;

	@GetMapping
	public ResponseEntity<UserDetails> getUser(Authentication authentication) {
		return ResponseEntity.ok().body((UserDetails) authentication.getPrincipal());
	}
        
        @GetMapping("/{id}")
	public Usuario getUsuarioById(@PathVariable Long id) {
		return usuarioService.loadById(id);
	}
        
        @GetMapping("/lista")
	public List<Usuario> list() {
		return usuarioService.lista();
	}

	@PostMapping("/novo-usuario")
	public ResponseEntity<Usuario> save(@RequestBody @Valid UsuarioModel usuarioModel) {
            Usuario usuarioExistente = usuarioService.findByEmail(usuarioModel.email);
            if(usuarioExistente == null){
                Usuario usuario = new Usuario(usuarioModel.nome, usuarioModel.email, usuarioModel.telefone, usuarioModel.senha, usuarioModel.nascimento);
		usuarioService.save(usuario);
		return ResponseEntity.ok().body(usuario);
            }else{
                return (ResponseEntity<Usuario>) ResponseEntity.badRequest();
            }
	}
}
