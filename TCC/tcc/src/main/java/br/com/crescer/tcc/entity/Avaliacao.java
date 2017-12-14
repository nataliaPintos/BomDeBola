/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.tcc.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "AVALIACAO")
public class Avaliacao implements Serializable{
    private static final String SQ_AVALIACAO = "SQ_AVALIACAO";
    
    public Avaliacao(){}
    
    public Avaliacao(int nota, UsuarioPartida avaliador, UsuarioPartida avaliado){
        this.nota = nota;
        this.avaliador = avaliador;
        this.avaliado = avaliado;
    }
    
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = SQ_AVALIACAO)
    @SequenceGenerator(name = SQ_AVALIACAO, sequenceName = SQ_AVALIACAO, allocationSize = 1)
    @Column(name = "ID")
    private Long id;
    
    @NotNull(message = "Campo obrigatório")
    @Basic(optional = false)
    @Column(name = "NOTA")
    private int nota;
    
    @ManyToOne
    @JoinColumn(name = "ID_AVALIADOR")
    private UsuarioPartida avaliador;
    
    @ManyToOne
    @JoinColumn(name = "ID_AVALIADO")
    private UsuarioPartida avaliado;
}
