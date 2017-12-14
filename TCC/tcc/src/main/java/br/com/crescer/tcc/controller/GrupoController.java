/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.tcc.controller;

import br.com.crescer.tcc.Models.GrupoModel;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author luanp
 */
@RestController
@RequestMapping("/grupo")
@RequiredArgsConstructor
public class GrupoController {
    
    private final GrupoService grupoService;
    private final Usuario_GrupoService usuario_grupoService;
    private final UsuarioService usuarioService;
    
    @GetMapping("/{id}")
    public Grupo getGrupoById(@PathVariable Long id) {
	return grupoService.loadById(id);
    }
    
    @GetMapping("/lista")
    public List<Grupo> list() {
	return grupoService.lista();
    }
    
    //Cria o grupo e cria uma linha na tabela Usuario_Grupo onde armazena o participante do grupo como adm
    //Todas outras interações entre usuarios e grupo estão na Usuario_GrupoController
    @PostMapping("/novo-grupo")
    public ResponseEntity save(@RequestBody @Valid GrupoModel grupoModel) {
        return grupoService.save(grupoModel);
    }
    
    @PutMapping("/altera/{id}")
    public ResponseEntity<Boolean> update(@PathVariable Long id, @RequestBody @Valid GrupoModel grupoModel){
        return grupoService.update(grupoModel, id);
    }
    
    @GetMapping("/lista-usuarios/{id}")
    public List<Usuario_Grupo> listaUsuarios(@PathVariable Long id) {
	return usuario_grupoService.findByGrupo(id);
    }
    
    @GetMapping("/lista-grupos/{id}")
    public List<Grupo> listaGrupos(@PathVariable Long id) {
	return usuario_grupoService.listaGrupo(id);
    }
    
    @PostMapping("/inclui-usuario")
    public ResponseEntity convite(@RequestBody @Valid Usuario_GrupoModel usuario_grupoModel) {
        return usuario_grupoService.save(usuario_grupoModel);
    }
}
