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
    public ResponseEntity<Grupo> save(@RequestBody @Valid GrupoModel grupoModel) {        
        Grupo grupo = new Grupo(grupoModel.nome, grupoModel.imagem, grupoModel.time_max, grupoModel.time_min, 
                grupoModel.latitude, grupoModel.longitude, grupoModel.dia_semana, grupoModel.hora_inicio,
                grupoModel.hora_final, grupoModel.dias_confirmacao, grupoModel.horas_confirmacao, grupoModel.tempo_avaliacao);
        grupoService.save(grupo);
        return ResponseEntity.ok().body(grupo);
    }
    
    @PutMapping("/alteracao/{id}")
    public ResponseEntity<Boolean> update(@PathVariable Long id, @RequestBody @Valid GrupoModel grupoModel){
        Grupo grupo = grupoService.loadById(id);
            return ResponseEntity.ok(grupoService.update(grupoModel, grupo));
    }
    
    @GetMapping("/lista-usuarios")
    public List<Usuario_Grupo> listaUsuarios() {
	return usuario_grupoService.lista();
    }
    
    @PostMapping("/convite")
    public ResponseEntity<Usuario_Grupo> convite(@RequestBody @Valid Usuario_GrupoModel usuario_grupoModel) {
        Usuario usuario = usuarioService.findByEmail(usuario_grupoModel.email_usuario);
        Grupo grupo = grupoService.loadById(usuario_grupoModel.id_grupo);
        Usuario_Grupo ug = new Usuario_Grupo(usuario, grupo);
        usuario_grupoService.save(ug);
        return ResponseEntity.ok().body(ug);
    }
    
    @PutMapping("/aceitar")
    public ResponseEntity<Usuario_Grupo> update(@RequestBody @Valid Long id) {
        Usuario_Grupo usuario_grupo = usuario_grupoService.loadById(id);
        return ResponseEntity.ok(usuario_grupoService.update(usuario_grupo));        
    }
}
