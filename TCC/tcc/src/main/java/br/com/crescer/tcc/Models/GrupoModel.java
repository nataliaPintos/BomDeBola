/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.tcc.Models;

import java.time.LocalDateTime;

/**
 *
 * @author luanp
 */
public class GrupoModel {
    public String nome;
    public String imagem;
    public int time_max;
    public int time_min;
    public double latitude;
    public double longitude;
    public int dia_semana;
    public LocalDateTime hora_inicio;
    public LocalDateTime hora_final;
    public int dias_confirmacao;
    public LocalDateTime horas_confirmacao;
    public LocalDateTime tempo_avaliacao;
}
