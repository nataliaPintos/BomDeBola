/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.tcc.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.validation.constraints.*;
import lombok.*;

/**
 *
 * @author luan.avila
 */
@Entity
@Getter
@Setter
@Table(name = "USUARIO")
public class Usuario implements Serializable{
    
    private static final String SQ_USUARIO = "SQ_USUARIO";
    
    public Usuario(String nome, String email, String telefone, String senha, String imagem_perfil, LocalDate nascimento){
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.imagem_perfil = imagem_perfil;
        this.nascimento = nascimento;
        this.nota_geral = 0;
        this.partidas_jogadas = 0;
        this.gols = 0;
    }
    
    public Usuario(){
        
    }

	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = SQ_USUARIO)
	@SequenceGenerator(name = SQ_USUARIO, sequenceName = SQ_USUARIO, allocationSize = 1)
	@Column(name = "ID_USUARIO")
	private Long id;
    
        @Size
	@NotNull(message = "Campo obrigatório")
	@Basic(optional = false)
	@Column(name = "NOME")
	private String nome;
        
        @Size
	@NotNull(message = "Campo obrigatório")
	@Basic(optional = false)
	@Column(name = "EMAIL")
	private String email;
        
        @Size
	@NotNull(message = "Campo obrigatório")
	@Basic(optional = false)
	@Column(name = "TELEFONE")
	private String telefone;
        
        @Size
	@NotNull(message = "Campo obrigatório")
	@Basic(optional = false)
	@Column(name = "SENHA")
	private String senha;
        
        @Size
	@Basic(optional = true)
	@Column(name = "IMAGEM_PERFIL")
	private String imagem_perfil;
        
        @JsonFormat(pattern = "dd/MM/yyyy")
        @NotNull(message = "Campo obrigatório")
	@Basic(optional = false)
        @Column(name = "NASCIMENTO")
        private LocalDate nascimento;
        
        @NotNull(message = "Campo obrigatório")
	@Basic(optional = false)
	@Column(name = "NOTA_GERAL")
	private double nota_geral;
        
	@Basic(optional = true)
	@Column(name = "PARTIDAS_JOGADAS")
	private int partidas_jogadas;
        
	@Basic(optional = true)
	@Column(name = "GOLS")
	private int gols;
}
