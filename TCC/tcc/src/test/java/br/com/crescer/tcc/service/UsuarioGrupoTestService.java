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
import br.com.crescer.tcc.entity.Grupo;
import br.com.crescer.tcc.entity.Usuario;
import br.com.crescer.tcc.entity.UsuarioGrupo;
import java.time.LocalDate;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import br.com.crescer.tcc.Repository.UsuarioGrupoRepository;

@RunWith(MockitoJUnitRunner.class)
public class UsuarioGrupoTestService {
    @Mock
    private UsuarioGrupoRepository usuario_grupoRepository;
    
    @InjectMocks
    private UsuarioGrupoService usuario_grupoService;
    
    @Test
    public void testarLoadById() {
        Grupo grupo = new Grupo();
        grupo.setDiaSemana(2);
        grupo.setDiasConfirmacao(1);
        grupo.setImagem("img_grupo");
        grupo.setLatitude(12345);
        grupo.setLongitude(54321);
        grupo.setNome("Grupo");
        grupo.setTimeMax(16);
        grupo.setTimeMin(14);
        
        Usuario usuario = new Usuario();
        usuario.setNome("Luan");
        usuario.setEmail("luanparcival@gmail.com");
        usuario.setTelefone("982580230");
        usuario.setSenha("1234");
        usuario.setImagemPerfil("img");
        LocalDate nascimento = LocalDate.of(1999, 05, 22);
        usuario.setNascimento(nascimento);
        
        UsuarioGrupo ug = new UsuarioGrupo();
        ug.setAdm(true);
        ug.setGrupo(grupo);
        ug.setUsuario(usuario);
        
        
        when(usuario_grupoRepository.findOne(1L)).thenReturn(ug);
        
        final UsuarioGrupo ug2 = usuario_grupoService.loadById(1L);
        
        assertEquals(ug.getGrupo(), ug2.getGrupo());
        assertEquals(ug.getGrupo(), grupo);
        assertEquals(ug.getUsuario(), ug2.getUsuario());
        assertEquals(ug.getUsuario(), usuario);
        assertEquals(ug.getAdm(), ug2.getAdm());
    }
    
}
