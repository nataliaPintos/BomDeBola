/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.tcc.repository;

import br.com.crescer.tcc.Repository.UsuarioGrupoRepository;
import br.com.crescer.tcc.entity.Avaliacao;
import br.com.crescer.tcc.entity.Grupo;
import br.com.crescer.tcc.entity.Partida;
import br.com.crescer.tcc.entity.Usuario;
import br.com.crescer.tcc.entity.UsuarioGrupo;
import br.com.crescer.tcc.entity.UsuarioPartida;
import java.time.LocalDate;
import java.time.LocalDateTime;
import static java.util.stream.Collectors.toList;
import java.util.stream.StreamSupport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author luan.avila
 */

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional(propagation = Propagation.REQUIRED)
@ActiveProfiles("test")
public class UsuarioGrupoRepositoryTest {
    @Autowired
	private TestEntityManager testEntityManager;

	@Autowired
	private UsuarioGrupoRepository usuarioGrupoRepository;

	/**
	 * Test of save method, of class AtorService.
	 */
	@Test
	public void testSave() {
                LocalDate nascimento = LocalDate.of(1999, 05, 22);
		final Usuario usuario = new Usuario("Luan", "luanparcival@gmail.com", "982580230",
                "1234", nascimento);
                
                LocalDateTime inicio = LocalDateTime.of(1999, 05, 22, 19, 00, 00);
                LocalDateTime finall = LocalDateTime.of(1999, 05, 22, 20, 00, 00);
                LocalDateTime confirmacao = LocalDateTime.now(); confirmacao.plusDays(2); confirmacao.plusHours(12); confirmacao.plusMinutes(30);
                LocalDateTime avaliacaoTime = LocalDateTime.now(); avaliacaoTime.plusHours(12); avaliacaoTime.plusMinutes(30);
                Grupo grupo = new Grupo("Grupo", "img", 16, 14, 8759, 8654864, 1, inicio, finall, 2,
                confirmacao, avaliacaoTime);
                
                UsuarioGrupo usuarioGrupo = new UsuarioGrupo(usuario, grupo);
		usuarioGrupoRepository.save(usuarioGrupo);
                
		assertEquals(usuarioGrupo.getUsuario(), testEntityManager.find(UsuarioGrupo.class, usuarioGrupo.getId()).getUsuario());
                assertEquals(usuarioGrupo.getGrupo(), testEntityManager.find(UsuarioGrupo.class, usuarioGrupo.getId()).getGrupo());
                assertEquals(usuarioGrupo.getAdm(), testEntityManager.find(UsuarioGrupo.class, usuarioGrupo.getId()).getAdm());
	}

	@Test
	public void testFindAll() {
                LocalDate nascimento = LocalDate.of(1999, 05, 22);
		final Usuario usuario = new Usuario("Luan", "luanparcival@gmail.com", "982580230",
                "1234", nascimento);
                testEntityManager.persist(usuario);
                
                LocalDateTime inicio = LocalDateTime.of(1999, 05, 22, 19, 00, 00);
                LocalDateTime finall = LocalDateTime.of(1999, 05, 22, 20, 00, 00);
                LocalDateTime confirmacao = LocalDateTime.now(); confirmacao.plusDays(2); confirmacao.plusHours(12); confirmacao.plusMinutes(30);
                LocalDateTime avaliacaoTime = LocalDateTime.now(); avaliacaoTime.plusHours(12); avaliacaoTime.plusMinutes(30);
                Grupo grupo = new Grupo("Grupo", "img", 16, 14, 8759, 8654864, 1, inicio, finall, 2,
                confirmacao, avaliacaoTime);
                testEntityManager.persist(grupo);
                
                UsuarioGrupo usuarioGrupo = new UsuarioGrupo(usuario, grupo);
                testEntityManager.persist(usuarioGrupo);

		assertTrue(StreamSupport.stream(usuarioGrupoRepository.findAll().spliterator(), false)
				.map(UsuarioGrupo::getGrupo).collect(toList()).contains(usuarioGrupo.getGrupo()));
                assertTrue(StreamSupport.stream(usuarioGrupoRepository.findAll().spliterator(), false)
				.map(UsuarioGrupo::getUsuario).collect(toList()).contains(usuarioGrupo.getUsuario()));
                assertTrue(StreamSupport.stream(usuarioGrupoRepository.findAll().spliterator(), false)
				.map(UsuarioGrupo::getAdm).collect(toList()).contains(usuarioGrupo.getAdm()));
	}

	@Test
	public void testFindOne() {
		LocalDate nascimento = LocalDate.of(1999, 05, 22);
		final Usuario usuario = new Usuario("Luan", "luanparcival@gmail.com", "982580230",
                "1234", nascimento);
                testEntityManager.persist(usuario);
                
                LocalDateTime inicio = LocalDateTime.of(1999, 05, 22, 19, 00, 00);
                LocalDateTime finall = LocalDateTime.of(1999, 05, 22, 20, 00, 00);
                LocalDateTime confirmacao = LocalDateTime.now(); confirmacao.plusDays(2); confirmacao.plusHours(12); confirmacao.plusMinutes(30);
                LocalDateTime avaliacaoTime = LocalDateTime.now(); avaliacaoTime.plusHours(12); avaliacaoTime.plusMinutes(30);
                Grupo grupo = new Grupo("Grupo", "img", 16, 14, 8759, 8654864, 1, inicio, finall, 2,
                confirmacao, avaliacaoTime);
                testEntityManager.persist(grupo);
                
                UsuarioGrupo usuarioGrupo = new UsuarioGrupo(usuario, grupo);
                testEntityManager.persist(usuarioGrupo);
                
		assertEquals(usuarioGrupo.getUsuario(), usuarioGrupoRepository.findOne(usuarioGrupo.getId()).getUsuario());
                assertEquals(usuarioGrupo.getGrupo(), usuarioGrupoRepository.findOne(usuarioGrupo.getId()).getGrupo());
                assertEquals(usuarioGrupo.getAdm(), usuarioGrupoRepository.findOne(usuarioGrupo.getId()).getAdm());
	}
        
        @Test
	public void testFindByGrupo() {
                LocalDate nascimento = LocalDate.of(1999, 05, 22);
		final Usuario usuario = new Usuario("Luan", "luanparcival@gmail.com", "982580230",
                "1234", nascimento);
                testEntityManager.persist(usuario);
                
                LocalDateTime inicio = LocalDateTime.of(1999, 05, 22, 19, 00, 00);
                LocalDateTime finall = LocalDateTime.of(1999, 05, 22, 20, 00, 00);
                LocalDateTime confirmacao = LocalDateTime.now(); confirmacao.plusDays(2); confirmacao.plusHours(12); confirmacao.plusMinutes(30);
                LocalDateTime avaliacaoTime = LocalDateTime.now(); avaliacaoTime.plusHours(12); avaliacaoTime.plusMinutes(30);
                Grupo grupo = new Grupo("Grupo", "img", 16, 14, 8759, 8654864, 1, inicio, finall, 2,
                confirmacao, avaliacaoTime);
                testEntityManager.persist(grupo);
                
                UsuarioGrupo usuarioGrupo = new UsuarioGrupo(usuario, grupo);
                testEntityManager.persist(usuarioGrupo);

		assertTrue(StreamSupport.stream(usuarioGrupoRepository.findByGrupo(grupo).spliterator(), false)
				.map(UsuarioGrupo::getGrupo).collect(toList()).contains(usuarioGrupo.getGrupo()));
                assertTrue(StreamSupport.stream(usuarioGrupoRepository.findByGrupo(grupo).spliterator(), false)
				.map(UsuarioGrupo::getUsuario).collect(toList()).contains(usuarioGrupo.getUsuario()));
                assertTrue(StreamSupport.stream(usuarioGrupoRepository.findByGrupo(grupo).spliterator(), false)
				.map(UsuarioGrupo::getAdm).collect(toList()).contains(usuarioGrupo.getAdm()));
	}
        
        @Test
	public void testFindByUsuario() {
                LocalDate nascimento = LocalDate.of(1999, 05, 22);
		final Usuario usuario = new Usuario("Luan", "luanparcival@gmail.com", "982580230",
                "1234", nascimento);
                testEntityManager.persist(usuario);
                
                LocalDateTime inicio = LocalDateTime.of(1999, 05, 22, 19, 00, 00);
                LocalDateTime finall = LocalDateTime.of(1999, 05, 22, 20, 00, 00);
                LocalDateTime confirmacao = LocalDateTime.now(); confirmacao.plusDays(2); confirmacao.plusHours(12); confirmacao.plusMinutes(30);
                LocalDateTime avaliacaoTime = LocalDateTime.now(); avaliacaoTime.plusHours(12); avaliacaoTime.plusMinutes(30);
                Grupo grupo = new Grupo("Grupo", "img", 16, 14, 8759, 8654864, 1, inicio, finall, 2,
                confirmacao, avaliacaoTime);
                testEntityManager.persist(grupo);
                
                UsuarioGrupo usuarioGrupo = new UsuarioGrupo(usuario, grupo);
                testEntityManager.persist(usuarioGrupo);

		assertTrue(StreamSupport.stream(usuarioGrupoRepository.findByUsuario(usuario).spliterator(), false)
				.map(UsuarioGrupo::getGrupo).collect(toList()).contains(usuarioGrupo.getGrupo()));
                assertTrue(StreamSupport.stream(usuarioGrupoRepository.findByUsuario(usuario).spliterator(), false)
				.map(UsuarioGrupo::getUsuario).collect(toList()).contains(usuarioGrupo.getUsuario()));
                assertTrue(StreamSupport.stream(usuarioGrupoRepository.findByUsuario(usuario).spliterator(), false)
				.map(UsuarioGrupo::getAdm).collect(toList()).contains(usuarioGrupo.getAdm()));
	}
        
        @Test
	public void testFindByUsuarioAndGrupo() {
		LocalDate nascimento = LocalDate.of(1999, 05, 22);
		final Usuario usuario = new Usuario("Luan", "luanparcival@gmail.com", "982580230",
                "1234", nascimento);
                testEntityManager.persist(usuario);
                
                LocalDateTime inicio = LocalDateTime.of(1999, 05, 22, 19, 00, 00);
                LocalDateTime finall = LocalDateTime.of(1999, 05, 22, 20, 00, 00);
                LocalDateTime confirmacao = LocalDateTime.now(); confirmacao.plusDays(2); confirmacao.plusHours(12); confirmacao.plusMinutes(30);
                LocalDateTime avaliacaoTime = LocalDateTime.now(); avaliacaoTime.plusHours(12); avaliacaoTime.plusMinutes(30);
                Grupo grupo = new Grupo("Grupo", "img", 16, 14, 8759, 8654864, 1, inicio, finall, 2,
                confirmacao, avaliacaoTime);
                testEntityManager.persist(grupo);
                
                UsuarioGrupo usuarioGrupo = new UsuarioGrupo(usuario, grupo);
                testEntityManager.persist(usuarioGrupo);
                
		assertEquals(usuarioGrupo.getUsuario(), usuarioGrupoRepository.findByUsuarioAndGrupo(usuario, grupo).getUsuario());
                assertEquals(usuarioGrupo.getGrupo(), usuarioGrupoRepository.findByUsuarioAndGrupo(usuario, grupo).getGrupo());
                assertEquals(usuarioGrupo.getAdm(), usuarioGrupoRepository.findByUsuarioAndGrupo(usuario, grupo).getAdm());
	}
}
