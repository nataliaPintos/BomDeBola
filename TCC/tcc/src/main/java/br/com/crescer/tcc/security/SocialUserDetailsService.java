package br.com.crescer.tcc.security;

import br.com.crescer.tcc.entity.Usuario;
import br.com.crescer.tcc.service.UsuarioService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SocialUserDetailsService implements UserDetailsService {

  @Autowired
    private UsuarioService usuarioService;

    public static class CustomUserDetails extends User {

        @Getter
        @Setter
        private String nome;

        public CustomUserDetails(Usuario usuario, Collection<? extends GrantedAuthority> authorities
        ) {
            super(usuario.getEmail(), usuario.getSenha(), authorities);
            this.nome = usuario.getNome();
        }

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        final Usuario usuario = usuarioService.findByEmail(username);
        if (usuario == null) {
            return null;
        }
        final List<GrantedAuthority> grants = new ArrayList<>();
        grants.add(() -> "ROLE_ADMIN");
        return new CustomUserDetails(usuario, grants);
    }

}
