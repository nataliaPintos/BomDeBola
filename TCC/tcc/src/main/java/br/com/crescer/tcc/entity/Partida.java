/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.tcc.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@JsonIgnoreProperties({"usuarioPartida", "grupo"})
public class Partida implements Serializable{
    private static final String SQ_PARTIDA = "SQ_PARTIDA";
    
    public Partida(){}
    public Partida(int timeMax, int timeMin, double latitude, double longitude, 
            LocalDate diaSemana, LocalDateTime horaInicio, LocalDateTime horaFinal,
            LocalDateTime tempoConfirmacao, LocalDateTime tempoAvaliacao, Grupo grupo){
        this.timeMax = timeMax; this.timeMin = timeMin; this.latitude = latitude; 
        this.longitude = longitude; this.diaSemana = diaSemana; this.horaInicio = horaInicio; 
        this.horaFinal = horaFinal; this.tempoConfirmacao = tempoConfirmacao; 
        this.tempoAvaliacao = tempoAvaliacao; this.timeAtual = 0; this.grupo = grupo;
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
    private LocalDateTime horaInicio;
    
    @JsonFormat(pattern = "HH:mm")
    @NotNull(message = "Campo obrigatório")
    @Basic(optional = false)
    @Column(name = "HORA_FINAL")
    private LocalDateTime horaFinal;
    
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
    private LocalDate diaSemana;
    
    @NotNull(message = "Campo obrigatório")
    @Basic(optional = false)
    @Column(name = "TIME_MAX")
    private int timeMax;
    
    @NotNull(message = "Campo obrigatório")
    @Basic(optional = false)
    @Column(name = "TIME_MIN")
    private int timeMin;
    
    @NotNull(message = "Campo obrigatório")
    @Basic(optional = false)
    @Column(name = "TIME_ATUAL")
    private int timeAtual;
    
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    @NotNull(message = "Campo obrigatório")
    @Basic(optional = false)
    @Column(name = "HORAS_CONFIRMACAO")
    private LocalDateTime tempoConfirmacao;    
    
    @JsonFormat(pattern = "HH:mm")
    @NotNull(message = "Campo obrigatório")
    @Basic(optional = false)
    @Column(name = "TEMPO_AVALIACAO")
    private LocalDateTime tempoAvaliacao;
    
    @NotNull(message = "Campo obrigatório")
    @Basic(optional = false)
    @Column(name = "CONFIRMADA")
    private boolean confirmada;
    
    @ManyToOne
    @JoinColumn(name = "ID_GRUPO")
    private Grupo grupo;
    
    @OneToMany(mappedBy="partida", cascade=CascadeType.ALL)
    private List<UsuarioPartida> usuarioPartida;
}
