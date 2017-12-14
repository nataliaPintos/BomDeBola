/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.tcc.controller;

import br.com.crescer.tcc.Models.PartidaModel;
import br.com.crescer.tcc.entity.Grupo;
import br.com.crescer.tcc.entity.Partida;
import br.com.crescer.tcc.entity.Usuario_Partida;
import br.com.crescer.tcc.service.GrupoService;
import br.com.crescer.tcc.service.PartidaService;
import br.com.crescer.tcc.service.Usuario_PartidaService;
import java.time.LocalDateTime;
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
@RequestMapping("/partida")
@RequiredArgsConstructor
public class PartidaController {
    private final PartidaService partidaService;
    private final GrupoService grupoService;
    private final Usuario_PartidaService usuario_partidaService;
    
    @GetMapping("/{id}")
    public Partida getPartidaById(@PathVariable Long id) {
	return partidaService.loadById(id);
    }
    
    @GetMapping("/lista")
    public List<Partida> list() {
	return partidaService.lista();
    }
    
    @PostMapping("/nova-partida")
    public ResponseEntity<Partida> save(@RequestBody @Valid PartidaModel partidaModel) {
        return partidaService.save(partidaModel);
    }
    
    @GetMapping("/nova-partida/padrao/{id}")
    public PartidaModel getPartidaModel(@PathVariable Long id) {
        return partidaService.partidaModelRetorno(id);
    }
    
    @PutMapping("/aceita-partida/")
    public ResponseEntity<Boolean> update(@RequestBody @Valid Long idUsuario_Partida) {
        return usuario_partidaService.update(idUsuario_Partida);
    }
    
    @GetMapping("/lista-jogadores/{id}")
    public List<Usuario_Partida> listaJogadores(@PathVariable Long id) {
	return usuario_partidaService.listaDeParticipantes(id);
    }
    
    @GetMapping("/sorteia-times/{id}")
    public List<Usuario_Partida> sorteiaTimes(@PathVariable Long id) {
	return usuario_partidaService.sortearTime(id);
    }
    
    @GetMapping("/nova-partida/teste")
    public PartidaModel teste() {
        PartidaModel partida = new PartidaModel();
        partida.hora_inicio = LocalDateTime.now();
        return partida;
    }
}
