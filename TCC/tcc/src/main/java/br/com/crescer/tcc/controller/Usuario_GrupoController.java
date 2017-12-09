/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.tcc.controller;

import br.com.crescer.tcc.Models.Usuario_GrupoModel;
import br.com.crescer.tcc.entity.Grupo;
import br.com.crescer.tcc.entity.Usuario;
import br.com.crescer.tcc.entity.Usuario_Grupo;
import br.com.crescer.tcc.service.GrupoService;
import br.com.crescer.tcc.service.UsuarioService;
import br.com.crescer.tcc.service.Usuario_GrupoService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author luanp
 */
//Responsavel pelas interações de usuario e grupo
@RestController
@RequestMapping("/usuario_grupo")
@RequiredArgsConstructor
public class Usuario_GrupoController {
    private final Usuario_GrupoService usuario_grupoService;
    private final GrupoService grupoService;
    private final UsuarioService usuarioService;
    
    @GetMapping("/{id}")
    public Usuario_Grupo getGrupoById(@PathVariable Long id) {
	return usuario_grupoService.loadById(id);
    }
    
    @GetMapping("/lista")
    public List<Usuario_Grupo> list() {
	return usuario_grupoService.lista();
    }
    
    @PostMapping("/adicionar-usuario")
    public ResponseEntity<Usuario_Grupo> save(@RequestBody @Valid Usuario_GrupoModel usuario_grupoModel) {
        Usuario usuario = usuarioService.findByEmail(usuario_grupoModel.email_usuario);
        Grupo grupo = grupoService.loadById(usuario_grupoModel.id_grupo);
        Usuario_Grupo ug = new Usuario_Grupo(usuario, grupo);
        usuario_grupoService.save(ug);
        return ResponseEntity.ok().body(ug);
    }
}
