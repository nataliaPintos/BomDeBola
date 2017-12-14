/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.tcc.Models;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author luanp
 */
@Getter
@Setter
public class AvaliacaoModel {
    private int nota;
    private Long idAvaliador;
    private Long idAvaliado;
}
