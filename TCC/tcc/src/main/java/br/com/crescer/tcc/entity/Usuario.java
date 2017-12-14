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
import java.util.List;
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
@JsonIgnoreProperties({"usuarioGrupo"})
public class Usuario implements Serializable{
    
    private static final String SQ_USUARIO = "SQ_USUARIO";
    
    public Usuario(String nome, String email, String telefone, String senha, LocalDate nascimento){
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.nascimento = nascimento;
        this.notaGeral = 0;
        this.partidasJogadas = 0;
        this.gols = 0;
    }
    
    public Usuario(){
        
    }

	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = SQ_USUARIO)
	@SequenceGenerator(name = SQ_USUARIO, sequenceName = SQ_USUARIO, allocationSize = 1)
	@Column(name = "ID")
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
	private String imagemPerfil;
        
        @JsonFormat(pattern = "dd/MM/yyyy")
        @NotNull(message = "Campo obrigatório")
	@Basic(optional = false)
        @Column(name = "NASCIMENTO")
        private LocalDate nascimento;
        
        @NotNull(message = "Campo obrigatório")
	@Basic(optional = false)
	@Column(name = "NOTA_GERAL")
	private double notaGeral;
        
	@Basic(optional = true)
	@Column(name = "PARTIDAS_JOGADAS")
	private int partidasJogadas;
        
	@Basic(optional = true)
	@Column(name = "GOLS")
	private int gols;
        
        @OneToMany(mappedBy="usuario", cascade=CascadeType.ALL)
        private List<UsuarioGrupo> usuarioGrupo;
}
