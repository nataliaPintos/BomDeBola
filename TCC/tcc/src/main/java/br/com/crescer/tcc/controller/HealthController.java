package br.com.crescer.tcc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author carloshenrique
 */
@RestController
@RequestMapping(HealthController.PATH)
public class HealthController {

    public static final String PATH = "/health";

    @GetMapping
    public boolean health() {
        return true;
    }
}
