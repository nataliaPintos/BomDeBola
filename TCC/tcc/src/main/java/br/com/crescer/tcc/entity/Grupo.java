/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.tcc.entity;

import br.com.crescer.tcc.utilitarios.LocalDateTimeConverter;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.*;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
/**
 *
 * @author luanp
 */
@Entity
@Getter
@Setter
@Table(name = "GRUPO")
public class Grupo implements Serializable{
    
    private static final String SQ_GRUPO = "SQ_GRUPO";
    
    public Grupo(){}
    
    public Grupo(String nome, String imagem, int time_max, int time_min, double latitude, double longitude, 
            int dia_semana, LocalDateTime hora_inicio, LocalDateTime hora_final, int dias_confirmacao,
            LocalDateTime horas_confirmacao, LocalDateTime tempo_avaliacao){
        this.nome = nome; this.imagem = imagem; this.time_max = time_max; this.time_min = time_min;
        this.latitude = latitude; this.longitude = longitude; this.dia_semana = dia_semana; 
        this.hora_inicio = hora_inicio; this.hora_final = hora_final; this.dias_confirmacao = dias_confirmacao;
        this.horas_confirmacao = horas_confirmacao; this.tempo_avaliacao = tempo_avaliacao;
    }
    
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = SQ_GRUPO)
    @SequenceGenerator(name = SQ_GRUPO, sequenceName = SQ_GRUPO, allocationSize = 1)
    @Column(name = "ID")
    private Long id;
    
    @Size
    @NotNull(message = "Campo obrigatório")
    @Basic(optional = false)
    @Column(name = "NOME")
    private String nome;
    
    @Size
    @Basic(optional = true)
    @Column(name = "IMAGEM")
    private String imagem;
    
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
    @Column(name = "LATITUDE")
    private double latitude;
    
    @NotNull(message = "Campo obrigatório")
    @Basic(optional = false)
    @Column(name = "LONGITUDE")
    private double longitude;
    
    @NotNull(message = "Campo obrigatório")
    @Basic(optional = false)
    @Column(name = "DIA_SEMANA")
    private int dia_semana;
    
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
    @Column(name = "DIA_CONFIRMACAO")
    private int dias_confirmacao;
    
    @JsonFormat(pattern = "HH:mm")
    @NotNull(message = "Campo obrigatório")
    @Basic(optional = false)
    @Column(name = "HORAS_CONFIRMACAO")
    private LocalDateTime horas_confirmacao;    
    
    @JsonFormat(pattern = "HH:mm")
    @NotNull(message = "Campo obrigatório")
    @Basic(optional = false)
    @Column(name = "TEMPO_AVALIACAO")
    //@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    //@Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime tempo_avaliacao;
    
    @OneToMany(mappedBy="grupo")
        private List<Usuario_Grupo> usuario_grupo;
}
