/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.tcc.entity;

import java.io.Serializable;
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
@Table(name = "USUARIO_PARTIDA")
public class Usuario_Partida implements Serializable{
    private static final String SQ_USUARIO_PARTIDA = "SQ_USUARIO_PARTIDA";
    
    public Usuario_Partida(){}
    
    public Usuario_Partida(Partida partida, Usuario_Grupo usuario_grupo){
        this.partida = partida;
        this.nota_partida = 0;
        this.avaliacoes = 0;
        this.usuario_grupo = usuario_grupo;
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
    private double nota_partida;
    
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
    private Usuario_Grupo usuario_grupo;
    
    @OneToMany(mappedBy="usuario_partida")
    private List<Avaliacao> avaliadores;
    
    @OneToMany(mappedBy="usuario_partida")
    private List<Avaliacao> avaliados;
}
