/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.tcc.service;

/**
 *
 * @author luanp
 */
import br.com.crescer.tcc.Repository.Usuario_GrupoRepository;
import br.com.crescer.tcc.entity.Grupo;
import br.com.crescer.tcc.entity.Usuario;
import br.com.crescer.tcc.entity.Usuario_Grupo;
import java.time.LocalDate;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class Usuario_GrupoTest {
    @Mock
    private Usuario_GrupoRepository usuario_grupoRepository;
    
    @InjectMocks
    private Usuario_GrupoService usuario_grupoService;
    
    @Test
    public void testarLoadById() {
        Grupo grupo = new Grupo();
        grupo.setDia_semana(2);
        grupo.setDias_confirmacao(1);
        grupo.setImagem("img_grupo");
        grupo.setLatitude(12345);
        grupo.setLongitude(54321);
        grupo.setNome("Grupo");
        grupo.setTime_max(16);
        grupo.setTime_min(14);
        
        Usuario usuario = new Usuario();
        usuario.setNome("Luan");
        usuario.setEmail("luanparcival@gmail.com");
        usuario.setTelefone("982580230");
        usuario.setSenha("1234");
        usuario.setImagem_perfil("img");
        LocalDate nascimento = LocalDate.of(1999, 05, 22);
        usuario.setNascimento(nascimento);
        
        Usuario_Grupo ug = new Usuario_Grupo();
        ug.setAdm(true);
        ug.setGrupo(grupo);
        ug.setUsuario(usuario);
        
        
        when(usuario_grupoRepository.findOne(1L)).thenReturn(ug);
        
        final Usuario_Grupo ug2 = usuario_grupoService.loadById(1L);
        
        assertEquals(ug.getGrupo(), ug2.getGrupo());
        assertEquals(ug.getGrupo(), grupo);
        assertEquals(ug.getUsuario(), ug2.getUsuario());
        assertEquals(ug.getUsuario(), usuario);
        assertEquals(ug.getAdm(), ug2.getAdm());
    }
    
}
