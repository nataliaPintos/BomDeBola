/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.tcc.controller;

import br.com.crescer.tcc.Models.AvaliacaoModel;
import br.com.crescer.tcc.entity.Avaliacao;
import br.com.crescer.tcc.service.AvaliacaoService;
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
@RequestMapping("/avaliacao")
@RequiredArgsConstructor
public class AvaliacaoController {
    
    private final AvaliacaoService avaliacaoService;
    
    @GetMapping("/{id}")
    public Avaliacao getGrupoById(@PathVariable Long id) {
	return avaliacaoService.loadById(id);
    }
    
    @GetMapping("/lista")
    public List<Avaliacao> list() {
	return avaliacaoService.lista();
    }
    
    @PostMapping("/nova-avaliacao")
    public ResponseEntity save(@RequestBody @Valid AvaliacaoModel avaliacaoModel) {        
        return avaliacaoService.save(avaliacaoModel);
    }
}
