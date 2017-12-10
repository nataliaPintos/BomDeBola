/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.tcc.controller;

import br.com.crescer.tcc.entity.Partida;
import br.com.crescer.tcc.entity.Usuario_Partida;
import br.com.crescer.tcc.service.PartidaService;
import br.com.crescer.tcc.service.Usuario_PartidaService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author luanp
 */
@RestController
@RequestMapping("/usuario_partida")
@RequiredArgsConstructor
public class Usuario_PartidaController {
    private final Usuario_PartidaService usuario_partidaService;
    private final PartidaService partidaService;
    
    @PutMapping("/aceitar-partida")
    public ResponseEntity update(@RequestBody @Valid Long id) {
        Usuario_Partida usuario_partida = usuario_partidaService.loadById(id);
        Partida partida = usuario_partida.getPartida();
        if(partida.getTime_atual() < partida.getTime_max()){
            partida.setTime_atual(partida.getTime_atual() + 1);
            partidaService.save(partida);
            return ResponseEntity.ok(usuario_partidaService.update(usuario_partida));
        }else{
            return ResponseEntity.ok("Partida Lotada");
        }
    }
}
