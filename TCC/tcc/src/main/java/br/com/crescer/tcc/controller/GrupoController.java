/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.tcc.controller;

import br.com.crescer.tcc.Models.GrupoModel;
import br.com.crescer.tcc.entity.Grupo;
import br.com.crescer.tcc.entity.Usuario;
import br.com.crescer.tcc.entity.Usuario_Grupo;
import br.com.crescer.tcc.service.GrupoService;
import br.com.crescer.tcc.service.Usuario_GrupoService;
import br.com.crescer.tcc.utilitarios.UsuarioComponente;
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
    private final UsuarioComponente usuarioComponente;
    
    @GetMapping("/{id}")
    public Grupo getGrupoById(@PathVariable Long id) {
	return grupoService.loadById(id);
    }
    
    @GetMapping("/lista")
    public List<Grupo> list() {
	return grupoService.lista();
    }
    
    @PostMapping("/novo-grupo")
    public ResponseEntity<Grupo> save(@RequestBody @Valid GrupoModel grupoModel) {        
        Grupo grupo = new Grupo(grupoModel.nome, grupoModel.imagem, grupoModel.time_max, grupoModel.time_min, 
                grupoModel.latitude, grupoModel.longitude, grupoModel.dia_semana, grupoModel.hora_inicio,
                grupoModel.hora_final, grupoModel.dias_confirmacao, grupoModel.horas_confirmacao, grupoModel.tempo_avaliacao);
        grupoService.save(grupo);
        
        Usuario usuario = usuarioComponente.usuarioLogadoDetalhes();
        
        Usuario_Grupo usuario_grupo = new Usuario_Grupo(usuario, grupo);
        usuario_grupo.setAdm(true);
        usuario_grupo.setSolicitacao(false);
        usuario_grupoService.save(usuario_grupo);
        return ResponseEntity.ok().body(grupo);
    }
    
    @PutMapping("/atualizar-grupo/{id}")
    public ResponseEntity<Grupo> update(@RequestBody @Valid GrupoModel grupoModel, Long id){
        Grupo grupo = grupoService.loadById(id);
        if(grupo == null){
            return (ResponseEntity<Grupo>) ResponseEntity.badRequest();
        }else{
            grupoService.update(grupoModel, grupo);
            return ResponseEntity.ok().body(grupo);
        }
    }
}
