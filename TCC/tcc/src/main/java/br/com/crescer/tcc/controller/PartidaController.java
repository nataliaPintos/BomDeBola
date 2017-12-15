/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.tcc.controller;

import br.com.crescer.tcc.Models.PartidaModelGet;
import br.com.crescer.tcc.Models.PartidaModelPost;
import br.com.crescer.tcc.entity.Grupo;
import br.com.crescer.tcc.entity.Partida;
import br.com.crescer.tcc.entity.UsuarioPartida;
import br.com.crescer.tcc.service.GrupoService;
import br.com.crescer.tcc.service.PartidaService;
import br.com.crescer.tcc.service.UsuarioPartidaService;
import java.time.LocalDateTime;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/partida")
@RequiredArgsConstructor
public class PartidaController {
    
    @Autowired
    private final PartidaService partidaService;
    
    @Autowired
    private final GrupoService grupoService;
    
    @Autowired
    private final UsuarioPartidaService usuario_partidaService;
    
    @GetMapping("/{id}")
    public Partida getPartidaById(@PathVariable Long id) {
	return partidaService.loadById(id);
    }
    
    @GetMapping("/lista")
    public List<Partida> list() {
	return partidaService.lista();
    }
    
    @PostMapping("/nova-partida")
    public ResponseEntity<Partida> save(@RequestBody @Valid PartidaModelPost partidaModel) {
        return partidaService.save(partidaModel);
    }
    
    @GetMapping("/nova-partida/padrao/{id}")
    public PartidaModelGet getPartidaModel(@PathVariable Long id) {
        return partidaService.partidaModelRetorno(id);
    }
    
    @PutMapping("/aceita-partida/")
    public ResponseEntity<Boolean> update(@RequestBody @Valid Long idUsuario_Partida) {
        return usuario_partidaService.update(idUsuario_Partida);
    }
    
    @GetMapping("/lista-jogadores/{id}")
    public List<UsuarioPartida> listaJogadores(@PathVariable Long id) {
	return usuario_partidaService.listaDeParticipantes(id);
    }
    
    @GetMapping("/sorteia-times/{id}")
    public List<UsuarioPartida> sorteiaTimes(@PathVariable Long id) {
	return usuario_partidaService.sortearTime(id);
    }
}
