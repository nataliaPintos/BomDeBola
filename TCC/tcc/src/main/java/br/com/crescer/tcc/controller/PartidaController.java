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
        Grupo grupo = grupoService.loadById(partidaModel.id_grupo);
        Partida partida = new Partida(partidaModel.time_max, partidaModel.time_min, partidaModel.latitude,
        partidaModel.longitude, partidaModel.dia_semana, partidaModel.hora_inicio, partidaModel.hora_final,
        partidaModel.tempo_confirmacao, partidaModel.tempo_avaliacao, grupo);
        partidaService.save(partida, grupo);
        return ResponseEntity.ok().body(partida);
    }
    
    @GetMapping("/nova-partida")
    public PartidaModel getPartidaModel(@PathVariable Long id) {
	Grupo grupo = grupoService.loadById(id);
        PartidaModel partidaModel = new PartidaModel();
        return partidaService.partidaModelRetorno(partidaModel, grupo);
    }
    
    @PutMapping("/aceita-partida")
    public ResponseEntity<Boolean> update(@RequestBody @Valid Long id) {
        Usuario_Partida usuario_partida = usuario_partidaService.loadById(id);
        Partida partida = usuario_partida.getPartida();
        return ResponseEntity.ok(usuario_partidaService.update(usuario_partida, partida));
    }
    
    @GetMapping("/lista-jogadores")
    public List<Usuario_Partida> listaJogadores(Long id) {
	return usuario_partidaService.listaDeParticipantes(id);
    }
    
    @GetMapping("/sorteia-times")
    public List<Usuario_Partida> sorteiaTimes(Long id) {
        List<Usuario_Partida> lista = usuario_partidaService.listaDeParticipantes(id);
	return usuario_partidaService.sortearTime(lista);
    }
}
