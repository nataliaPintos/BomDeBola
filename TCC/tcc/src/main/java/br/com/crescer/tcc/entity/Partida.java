/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.tcc.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author luanp
 */
@Entity
@Getter
@Setter
@Table(name = "PARTIDA")
public class Partida implements Serializable{
    private static final String SQ_PARTIDA = "SQ_PARTIDA";
    
    public Partida(){}
    public Partida(int time_max, int time_min, double latitude, double longitude, 
            LocalDate dia_semana, LocalDateTime hora_inicio, LocalDateTime hora_final,
            LocalDateTime tempo_confirmacao, LocalDateTime tempo_avaliacao, Grupo grupo){
        this.time_max = time_max; this.time_min = time_min; this.latitude = latitude; 
        this.longitude = longitude; this.dia_semana = dia_semana; this.hora_inicio = hora_inicio; 
        this.hora_final = hora_final; this.tempo_confirmacao = tempo_confirmacao; 
        this.tempo_avaliacao = tempo_avaliacao; this.time_atual = 0; this.grupo = grupo;
        this.confirmada = false;
    }
    
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = SQ_PARTIDA)
    @SequenceGenerator(name = SQ_PARTIDA, sequenceName = SQ_PARTIDA, allocationSize = 1)
    @Column(name = "ID")
    private Long id;
    
    @JsonFormat(pattern = "HH:mm")
    @NotNull(message = "Campo obrigatório")
    @Basic(optional = false)
    @Column(name = "HORA_INICIO")
    private LocalDateTime hora_inicio;
    
    @JsonFormat(pattern = "HH:mm")
    @NotNull(message = "Campo obrigatório")
    @Basic(optional = false)
    @Column(name = "HORA_FINAL")
    private LocalDateTime hora_final;
    
    @NotNull(message = "Campo obrigatório")
    @Basic(optional = false)
    @Column(name = "LATITUDE")
    private double latitude;
    
    @NotNull(message = "Campo obrigatório")
    @Basic(optional = false)
    @Column(name = "LONGITUDE")
    private double longitude;
    
    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "Campo obrigatório")
    @Basic(optional = false)
    @Column(name = "DIA_SEMANA")
    private LocalDate dia_semana;
    
    @NotNull(message = "Campo obrigatório")
    @Basic(optional = false)
    @Column(name = "TIME_MAX")
    private int time_max;
    
    @NotNull(message = "Campo obrigatório")
    @Basic(optional = false)
    @Column(name = "TIME_MIN")
    private int time_min;
    
    @NotNull(message = "Campo obrigatório")
    @Basic(optional = false)
    @Column(name = "TIME_ATUAL")
    private int time_atual;
    
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    @NotNull(message = "Campo obrigatório")
    @Basic(optional = false)
    @Column(name = "HORAS_CONFIRMACAO")
    private LocalDateTime tempo_confirmacao;    
    
    @JsonFormat(pattern = "HH:mm")
    @NotNull(message = "Campo obrigatório")
    @Basic(optional = false)
    @Column(name = "TEMPO_AVALIACAO")
    private LocalDateTime tempo_avaliacao;
    
    @NotNull(message = "Campo obrigatório")
    @Basic(optional = false)
    @Column(name = "CONFIRMADA")
    private boolean confirmada;
    
    @ManyToOne
    @JoinColumn(name = "ID_GRUPO")
    private Grupo grupo;
    
    @OneToMany(mappedBy="partida")
    private List<Usuario_Partida> usuario_partida;
}
