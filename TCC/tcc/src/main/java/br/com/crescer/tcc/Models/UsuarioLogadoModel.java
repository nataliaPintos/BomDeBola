/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.tcc.Models;

/**
 *
 * @author luanp
 */
import br.com.crescer.tcc.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioLogadoModel {
   
   private Long id;
   private String email;

 
   public static UsuarioLogadoModel converterParaUsuarioLogado(Usuario usuario) {
       return UsuarioLogadoModel.builder()
               .id(usuario.getId())
               .email(usuario.getEmail())
               .build();
   }
   
}
