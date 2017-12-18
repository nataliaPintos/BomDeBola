/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.tcc.repository;

import br.com.crescer.tcc.Repository.UsuarioPartidaRepository;
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
public class UsuarioPartidaRepositoryTest {
    @Autowired
	private TestEntityManager testEntityManager;

	@Autowired
	private UsuarioPartidaRepository usuarioPartidaRepository;

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
                
                LocalDate semana = LocalDate.of(2017, 05, 12);
                Partida partida = new Partida(16, 14, 8759, 8654864, semana, inicio, finall, confirmacao, avaliacaoTime, grupo);
                
                UsuarioPartida usuarioPartida = new UsuarioPartida(partida, usuarioGrupo);
		usuarioPartidaRepository.save(usuarioPartida);
                
		assertEquals(usuarioPartida.getPartida(), testEntityManager.find(UsuarioPartida.class, usuarioPartida.getId()).getPartida());
                assertEquals(usuarioPartida.getAvaliacoes(), testEntityManager.find(UsuarioPartida.class, usuarioPartida.getId()).getAvaliacoes());
                assertEquals(usuarioPartida.getGols(), testEntityManager.find(UsuarioPartida.class, usuarioPartida.getId()).getGols());
                assertEquals(usuarioPartida.getNotaPartida(), testEntityManager.find(UsuarioPartida.class, usuarioPartida.getId()).getNotaPartida(), 0.001);
                assertEquals(usuarioPartida.getTime(), testEntityManager.find(UsuarioPartida.class, usuarioPartida.getId()).getTime());
                assertEquals(usuarioPartida.getUsuarioGrupo(), testEntityManager.find(UsuarioPartida.class, usuarioPartida.getId()).getUsuarioGrupo());
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
                
                LocalDate semana = LocalDate.of(2017, 05, 12);
                Partida partida = new Partida(16, 14, 8759, 8654864, semana, inicio, finall, confirmacao, avaliacaoTime, grupo);
                testEntityManager.persist(partida);
                
                UsuarioPartida usuarioPartida = new UsuarioPartida(partida, usuarioGrupo);
		usuarioPartidaRepository.save(usuarioPartida);
		testEntityManager.persist(usuarioPartida);

		assertTrue(StreamSupport.stream(usuarioPartidaRepository.findAll().spliterator(), false)
				.map(UsuarioPartida::getAvaliacoes).collect(toList()).contains(usuarioPartida.getAvaliacoes()));
                assertTrue(StreamSupport.stream(usuarioPartidaRepository.findAll().spliterator(), false)
				.map(UsuarioPartida::getGols).collect(toList()).contains(usuarioPartida.getGols()));
                assertTrue(StreamSupport.stream(usuarioPartidaRepository.findAll().spliterator(), false)
				.map(UsuarioPartida::getNotaPartida).collect(toList()).contains(usuarioPartida.getNotaPartida()));
                assertTrue(StreamSupport.stream(usuarioPartidaRepository.findAll().spliterator(), false)
				.map(UsuarioPartida::getPartida).collect(toList()).contains(usuarioPartida.getPartida()));
                assertTrue(StreamSupport.stream(usuarioPartidaRepository.findAll().spliterator(), false)
				.map(UsuarioPartida::getTime).collect(toList()).contains(usuarioPartida.getTime()));
                assertTrue(StreamSupport.stream(usuarioPartidaRepository.findAll().spliterator(), false)
				.map(UsuarioPartida::getTime).collect(toList()).contains(usuarioPartida.getTime()));
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
                
                LocalDate semana = LocalDate.of(2017, 05, 12);
                Partida partida = new Partida(16, 14, 8759, 8654864, semana, inicio, finall, confirmacao, avaliacaoTime, grupo);
                testEntityManager.persist(partida);
                
                UsuarioPartida usuarioPartida = new UsuarioPartida(partida, usuarioGrupo);
		usuarioPartidaRepository.save(usuarioPartida);
		testEntityManager.persist(usuarioPartida);
                
		assertEquals(usuarioPartida.getPartida(), usuarioPartidaRepository.findOne(usuarioPartida.getId()).getPartida());
                assertEquals(usuarioPartida.getAvaliacoes(), usuarioPartidaRepository.findOne(usuarioPartida.getId()).getAvaliacoes());
                assertEquals(usuarioPartida.getGols(), usuarioPartidaRepository.findOne(usuarioPartida.getId()).getGols());
                assertEquals(usuarioPartida.getNotaPartida(), usuarioPartidaRepository.findOne(usuarioPartida.getId()).getNotaPartida(), 0.001);
                assertEquals(usuarioPartida.getTime(), usuarioPartidaRepository.findOne(usuarioPartida.getId()).getTime());
                assertEquals(usuarioPartida.getUsuarioGrupo(), usuarioPartidaRepository.findOne(usuarioPartida.getId()).getUsuarioGrupo());
	}
        
        @Test
	public void testFindByIdAndSolicitacao() {
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
                
                LocalDate semana = LocalDate.of(2017, 05, 12);
                Partida partida = new Partida(16, 14, 8759, 8654864, semana, inicio, finall, confirmacao, avaliacaoTime, grupo);
                testEntityManager.persist(partida);
                
                UsuarioPartida usuarioPartida = new UsuarioPartida(partida, usuarioGrupo);
		usuarioPartidaRepository.save(usuarioPartida);
		testEntityManager.persist(usuarioPartida);

		assertTrue(StreamSupport.stream(usuarioPartidaRepository.findByIdAndSolicitacao(usuarioPartida.getId(), true).spliterator(), false)
				.map(UsuarioPartida::getAvaliacoes).collect(toList()).contains(usuarioPartida.getAvaliacoes()));
                assertTrue(StreamSupport.stream(usuarioPartidaRepository.findByIdAndSolicitacao(usuarioPartida.getId(), true).spliterator(), false)
				.map(UsuarioPartida::getGols).collect(toList()).contains(usuarioPartida.getGols()));
                assertTrue(StreamSupport.stream(usuarioPartidaRepository.findByIdAndSolicitacao(usuarioPartida.getId(), true).spliterator(), false)
				.map(UsuarioPartida::getNotaPartida).collect(toList()).contains(usuarioPartida.getNotaPartida()));
                assertTrue(StreamSupport.stream(usuarioPartidaRepository.findByIdAndSolicitacao(usuarioPartida.getId(), true).spliterator(), false)
				.map(UsuarioPartida::getPartida).collect(toList()).contains(usuarioPartida.getPartida()));
                assertTrue(StreamSupport.stream(usuarioPartidaRepository.findByIdAndSolicitacao(usuarioPartida.getId(), true).spliterator(), false)
				.map(UsuarioPartida::getTime).collect(toList()).contains(usuarioPartida.getTime()));
                assertTrue(StreamSupport.stream(usuarioPartidaRepository.findByIdAndSolicitacao(usuarioPartida.getId(), true).spliterator(), false)
				.map(UsuarioPartida::getTime).collect(toList()).contains(usuarioPartida.getTime()));
	}
        
        @Test
	public void testFindByPartida() {
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
                
                LocalDate semana = LocalDate.of(2017, 05, 12);
                Partida partida = new Partida(16, 14, 8759, 8654864, semana, inicio, finall, confirmacao, avaliacaoTime, grupo);
                testEntityManager.persist(partida);
                
                UsuarioPartida usuarioPartida = new UsuarioPartida(partida, usuarioGrupo);
		usuarioPartidaRepository.save(usuarioPartida);
		testEntityManager.persist(usuarioPartida);

		assertTrue(StreamSupport.stream(usuarioPartidaRepository.findByPartida(partida).spliterator(), false)
				.map(UsuarioPartida::getAvaliacoes).collect(toList()).contains(usuarioPartida.getAvaliacoes()));
                assertTrue(StreamSupport.stream(usuarioPartidaRepository.findByPartida(partida).spliterator(), false)
				.map(UsuarioPartida::getGols).collect(toList()).contains(usuarioPartida.getGols()));
                assertTrue(StreamSupport.stream(usuarioPartidaRepository.findByPartida(partida).spliterator(), false)
				.map(UsuarioPartida::getNotaPartida).collect(toList()).contains(usuarioPartida.getNotaPartida()));
                assertTrue(StreamSupport.stream(usuarioPartidaRepository.findByPartida(partida).spliterator(), false)
				.map(UsuarioPartida::getPartida).collect(toList()).contains(usuarioPartida.getPartida()));
                assertTrue(StreamSupport.stream(usuarioPartidaRepository.findByPartida(partida).spliterator(), false)
				.map(UsuarioPartida::getTime).collect(toList()).contains(usuarioPartida.getTime()));
                assertTrue(StreamSupport.stream(usuarioPartidaRepository.findByPartida(partida).spliterator(), false)
				.map(UsuarioPartida::getTime).collect(toList()).contains(usuarioPartida.getTime()));
	}
        
        @Test
	public void testFindByUsuarioGrupoAndSolicitacao() {
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
                
                LocalDate semana = LocalDate.of(2017, 05, 12);
                Partida partida = new Partida(16, 14, 8759, 8654864, semana, inicio, finall, confirmacao, avaliacaoTime, grupo);
                testEntityManager.persist(partida);
                
                UsuarioPartida usuarioPartida = new UsuarioPartida(partida, usuarioGrupo);
		usuarioPartidaRepository.save(usuarioPartida);
		testEntityManager.persist(usuarioPartida);

		assertTrue(StreamSupport.stream(usuarioPartidaRepository.findByUsuarioGrupoAndSolicitacao(usuarioGrupo, true).spliterator(), false)
				.map(UsuarioPartida::getAvaliacoes).collect(toList()).contains(usuarioPartida.getAvaliacoes()));
                assertTrue(StreamSupport.stream(usuarioPartidaRepository.findByUsuarioGrupoAndSolicitacao(usuarioGrupo, true).spliterator(), false)
				.map(UsuarioPartida::getGols).collect(toList()).contains(usuarioPartida.getGols()));
                assertTrue(StreamSupport.stream(usuarioPartidaRepository.findByUsuarioGrupoAndSolicitacao(usuarioGrupo, true).spliterator(), false)
				.map(UsuarioPartida::getNotaPartida).collect(toList()).contains(usuarioPartida.getNotaPartida()));
                assertTrue(StreamSupport.stream(usuarioPartidaRepository.findByUsuarioGrupoAndSolicitacao(usuarioGrupo, true).spliterator(), false)
				.map(UsuarioPartida::getPartida).collect(toList()).contains(usuarioPartida.getPartida()));
                assertTrue(StreamSupport.stream(usuarioPartidaRepository.findByUsuarioGrupoAndSolicitacao(usuarioGrupo, true).spliterator(), false)
				.map(UsuarioPartida::getTime).collect(toList()).contains(usuarioPartida.getTime()));
                assertTrue(StreamSupport.stream(usuarioPartidaRepository.findByUsuarioGrupoAndSolicitacao(usuarioGrupo, true).spliterator(), false)
				.map(UsuarioPartida::getTime).collect(toList()).contains(usuarioPartida.getTime()));
	}
}
