/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.tcc.utilitarios;

/**
 *
 * @author luanp
 */

import br.com.crescer.tcc.Models.UsuarioLogadoModel;
import br.com.crescer.tcc.entity.Usuario;
import br.com.crescer.tcc.service.UsuarioService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class UsuarioComponente {

    @Autowired
    UsuarioService usuarioServico;

    public UsuarioLogadoModel usuarioLogado() {
        return Optional
                .ofNullable(usuarioLogadoDetalhes())
                .map(UsuarioLogadoModel::converterParaUsuarioLogado)
                .orElse(null);
    }

    public Usuario usuarioLogadoDetalhes() {
        return Optional
                .ofNullable(user())
                .map(User::getUsername)
                .map(usuarioServico::findByEmail)
                .orElse(null);
    }

    private User user() {
        return Optional
                .ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(Authentication::getPrincipal)
                .map(User.class::cast)
                .orElse(null);
    }
}
