/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.tcc.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
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
@Table(name = "USUARIO_PARTIDA")
//@JsonIgnoreProperties({"usuarioGrupo"})
public class UsuarioPartida implements Serializable{
    private static final String SQ_USUARIO_PARTIDA = "SQ_USUARIO_PARTIDA";
    
    public UsuarioPartida(){}
    
    public UsuarioPartida(Partida partida, UsuarioGrupo usuarioGrupo){
        this.partida = partida;
        this.notaPartida = 0;
        this.avaliacoes = 0;
        this.usuarioGrupo = usuarioGrupo;
        this.solicitacao = true;
    }
    
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = SQ_USUARIO_PARTIDA)
    @SequenceGenerator(name = SQ_USUARIO_PARTIDA, sequenceName = SQ_USUARIO_PARTIDA, allocationSize = 1)
    @Column(name = "ID")
    private Long id;
    
    @NotNull(message = "Campo obrigatório")
    @Basic(optional = false)
    @Column(name = "NOTA_PARTIDA")
    private double notaPartida;
    
    @NotNull(message = "Campo obrigatório")
    @Basic(optional = false)
    @Column(name = "AVALIACOES")
    private int avaliacoes;
    
    @NotNull(message = "Campo obrigatório")
    @Basic(optional = false)
    @Column(name = "GOLS")
    private int gols;
    
    @NotNull(message = "Campo obrigatório")
    @Basic(optional = false)
    @Column(name = "TIME")
    private char time;
    
    @NotNull(message = "Campo obrigatório")
    @Basic(optional = false)
    @Column(name = "SOLICITACAO")
    private boolean solicitacao;
    
    @ManyToOne
    @JoinColumn(name = "ID_PARTIDA")
    private Partida partida;
    
    @ManyToOne
    @JoinColumn(name = "ID_USUARIO_GRUPO")
    private UsuarioGrupo usuarioGrupo;
}
