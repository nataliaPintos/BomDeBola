package br.com.crescer.tcc.controller;

import java.util.Optional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author carloshenrique
 */
@RestController
@RequestMapping(LoggedUserController.PATH)
public class LoggedUserController {

    public static final String PATH = "/logged-user";

    @GetMapping
    public User getUserDetails(Authentication authentication) {
        return Optional.ofNullable(authentication)
                .map(Authentication::getPrincipal)
                .map(User.class::cast)
                .orElse(null);
    }
}
