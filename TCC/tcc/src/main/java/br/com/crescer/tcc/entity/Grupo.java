/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.tcc.entity;

import br.com.crescer.tcc.utilitarios.LocalDateTimeConverter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties({"usuario_grupo", "partida"})
public class Grupo implements Serializable{
    
    private static final String SQ_GRUPO = "SQ_GRUPO";
    
    public Grupo(){}
    
    public Grupo(String nome, String imagem, int timeMax, int timeMin, double latitude, double longitude, 
            int diaSemana, LocalDateTime horaInicio, LocalDateTime horaFinal, int diasConfirmacao,
            LocalDateTime horasConfirmacao, LocalDateTime tempoAvaliacao){
        this.nome = nome; this.imagem = imagem; this.timeMax = timeMax; this.timeMin = timeMin;
        this.latitude = latitude; this.longitude = longitude; this.diaSemana = diaSemana; 
        this.horaInicio = horaInicio; this.horaFinal = horaFinal; this.diasConfirmacao = diasConfirmacao;
        this.horasConfirmacao = horasConfirmacao; this.tempoAvaliacao = tempoAvaliacao;
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
    private int timeMax;
    
    @NotNull(message = "Campo obrigatório")
    @Basic(optional = false)
    @Column(name = "TIME_MIN")
    private int timeMin;
    
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
    private int diaSemana;
    
    @JsonFormat(pattern = "dd-MM-yyyy@HH:mm:ss")
    @NotNull(message = "Campo obrigatório")
    @Basic(optional = false)
    @Column(name = "HORA_INICIO")
    private LocalDateTime horaInicio;
    
    @JsonFormat(pattern = "dd-MM-yyyy@HH:mm:ss")
    @NotNull(message = "Campo obrigatório")
    @Basic(optional = false)
    @Column(name = "HORA_FINAL")
    private LocalDateTime horaFinal;
    
    @NotNull(message = "Campo obrigatório")
    @Basic(optional = false)
    @Column(name = "DIA_CONFIRMACAO")
    private int diasConfirmacao;
    
    @JsonFormat(pattern = "dd-MM-yyyy@HH:mm:ss")
    @NotNull(message = "Campo obrigatório")
    @Basic(optional = false)
    @Column(name = "HORAS_CONFIRMACAO")
    private LocalDateTime horasConfirmacao;    
    
    @JsonFormat(pattern = "dd-MM-yyyy@HH:mm:ss")
    @NotNull(message = "Campo obrigatório")
    @Basic(optional = false)
    @Column(name = "TEMPO_AVALIACAO")
    //@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    //@Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime tempoAvaliacao;
    
    @OneToMany(mappedBy="grupo")
    private List<UsuarioGrupo> usuarioGrupo;
    
    @OneToMany(mappedBy="grupo")
    private List<Partida> partida;
}
