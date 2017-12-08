/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.tcc.controller;

import br.com.crescer.tcc.Models.GrupoModel;
import br.com.crescer.tcc.entity.Grupo;
import br.com.crescer.tcc.service.GrupoService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
@RestController
@RequestMapping("/grupo")
@RequiredArgsConstructor
public class GrupoController {
    
    private final GrupoService grupoService;
    
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
        return ResponseEntity.ok().body(grupo);
    }
    
}
