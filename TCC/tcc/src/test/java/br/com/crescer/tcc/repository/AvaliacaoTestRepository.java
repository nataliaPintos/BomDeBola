/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.tcc.repository;

import br.com.crescer.tcc.Repository.AvaliacaoRepository;
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
public class AvaliacaoTestRepository {
    @Autowired
	private TestEntityManager testEntityManager;

	@Autowired
	private AvaliacaoRepository avaliacaoRepository;

	/**
	 * Test of save method, of class AtorService.
	 */
	@Test
	public void testSave() {
                LocalDate nascimento = LocalDate.of(1999, 05, 22);
		final Usuario usuarioAvaliador = new Usuario("Luan", "luanparcival@gmail.com", "982580230",
                "1234", nascimento);
                final Usuario usuarioAvaliado = new Usuario("Juca", "juca@gmail.com", "983390056",
                "1234", nascimento);
                
                LocalDateTime inicio = LocalDateTime.of(1999, 05, 22, 19, 00, 00);
                LocalDateTime finall = LocalDateTime.of(1999, 05, 22, 20, 00, 00);
                LocalDateTime confirmacao = LocalDateTime.now(); confirmacao.plusDays(2); confirmacao.plusHours(12); confirmacao.plusMinutes(30);
                LocalDateTime avaliacaoTime = LocalDateTime.now(); avaliacaoTime.plusHours(12); avaliacaoTime.plusMinutes(30);
                Grupo grupo = new Grupo("Grupo", "img", 16, 14, 8759, 8654864, 1, inicio, finall, 2,
                confirmacao, avaliacaoTime);
                
                UsuarioGrupo uGAvaliador = new UsuarioGrupo(usuarioAvaliador, grupo);
                UsuarioGrupo uGAvaliado = new UsuarioGrupo(usuarioAvaliado, grupo);
                
                LocalDate semana = LocalDate.of(2017, 05, 12);
                Partida partida = new Partida(16, 14, 8759, 8654864, semana, inicio, finall, confirmacao, avaliacaoTime, grupo);
                
                UsuarioPartida uPAvaliador = new UsuarioPartida(partida, uGAvaliador);
                UsuarioPartida uPAvaliado = new UsuarioPartida(partida, uGAvaliado);
                
                Avaliacao avaliacao = new Avaliacao(5, uPAvaliador, uPAvaliado);
		avaliacaoRepository.save(avaliacao);
                
		assertEquals(avaliacao.getNota(), testEntityManager.find(Avaliacao.class, avaliacao.getId()).getNota());
                assertEquals(avaliacao.getAvaliador(), testEntityManager.find(Avaliacao.class, avaliacao.getId()).getAvaliador());
                assertEquals(avaliacao.getAvaliado(), testEntityManager.find(Avaliacao.class, avaliacao.getId()).getAvaliado());
	}

	@Test
	public void testFindAll() {
                LocalDate nascimento = LocalDate.of(1999, 05, 22);
		final Usuario usuarioAvaliador = new Usuario("Luan", "luanparcival@gmail.com", "982580230",
                "1234", nascimento);
                final Usuario usuarioAvaliado = new Usuario("Juca", "juca@gmail.com", "983390056",
                "1234", nascimento);
                testEntityManager.persist(usuarioAvaliador);
                testEntityManager.persist(usuarioAvaliado);
                
                LocalDateTime inicio = LocalDateTime.of(1999, 05, 22, 19, 00, 00);
                LocalDateTime finall = LocalDateTime.of(1999, 05, 22, 20, 00, 00);
                LocalDateTime confirmacao = LocalDateTime.now(); confirmacao.plusDays(2); confirmacao.plusHours(12); confirmacao.plusMinutes(30);
                LocalDateTime avaliacaoTime = LocalDateTime.now(); avaliacaoTime.plusHours(12); avaliacaoTime.plusMinutes(30);
                Grupo grupo = new Grupo("Grupo", "img", 16, 14, 8759, 8654864, 1, inicio, finall, 2,
                confirmacao, avaliacaoTime);
                testEntityManager.persist(grupo);
                
                UsuarioGrupo uGAvaliador = new UsuarioGrupo(usuarioAvaliador, grupo);
                UsuarioGrupo uGAvaliado = new UsuarioGrupo(usuarioAvaliado, grupo);
                testEntityManager.persist(uGAvaliador);
                testEntityManager.persist(uGAvaliado);
                
                LocalDate semana = LocalDate.of(2017, 05, 12);
                Partida partida = new Partida(16, 14, 8759, 8654864, semana, inicio, finall, confirmacao, avaliacaoTime, grupo);
                testEntityManager.persist(partida);
                
                UsuarioPartida uPAvaliador = new UsuarioPartida(partida, uGAvaliador);
                UsuarioPartida uPAvaliado = new UsuarioPartida(partida, uGAvaliado);
                testEntityManager.persist(uPAvaliador);
                testEntityManager.persist(uPAvaliado);
                
                Avaliacao avaliacao = new Avaliacao(5, uPAvaliador, uPAvaliado);
		testEntityManager.persist(avaliacao);

		assertTrue(StreamSupport.stream(avaliacaoRepository.findAll().spliterator(), false)
				.map(Avaliacao::getNota).collect(toList()).contains(avaliacao.getNota()));
                assertTrue(StreamSupport.stream(avaliacaoRepository.findAll().spliterator(), false)
				.map(Avaliacao::getAvaliador).collect(toList()).contains(avaliacao.getAvaliador()));
                assertTrue(StreamSupport.stream(avaliacaoRepository.findAll().spliterator(), false)
				.map(Avaliacao::getAvaliado).collect(toList()).contains(avaliacao.getAvaliado()));
	}

	@Test
	public void testFindOne() {
		LocalDate nascimento = LocalDate.of(1999, 05, 22);
		final Usuario usuarioAvaliador = new Usuario("Luan", "luanparcival@gmail.com", "982580230",
                "1234", nascimento);
                final Usuario usuarioAvaliado = new Usuario("Juca", "juca@gmail.com", "983390056",
                "1234", nascimento);
                testEntityManager.persist(usuarioAvaliador);
                testEntityManager.persist(usuarioAvaliado);
                
                LocalDateTime inicio = LocalDateTime.of(1999, 05, 22, 19, 00, 00);
                LocalDateTime finall = LocalDateTime.of(1999, 05, 22, 20, 00, 00);
                LocalDateTime confirmacao = LocalDateTime.now(); confirmacao.plusDays(2); confirmacao.plusHours(12); confirmacao.plusMinutes(30);
                LocalDateTime avaliacaoTime = LocalDateTime.now(); avaliacaoTime.plusHours(12); avaliacaoTime.plusMinutes(30);
                Grupo grupo = new Grupo("Grupo", "img", 16, 14, 8759, 8654864, 1, inicio, finall, 2,
                confirmacao, avaliacaoTime);
                testEntityManager.persist(grupo);
                
                UsuarioGrupo uGAvaliador = new UsuarioGrupo(usuarioAvaliador, grupo);
                UsuarioGrupo uGAvaliado = new UsuarioGrupo(usuarioAvaliado, grupo);
                testEntityManager.persist(uGAvaliador);
                testEntityManager.persist(uGAvaliado);
                
                LocalDate semana = LocalDate.of(2017, 05, 12);
                Partida partida = new Partida(16, 14, 8759, 8654864, semana, inicio, finall, confirmacao, avaliacaoTime, grupo);
                testEntityManager.persist(partida);
                
                UsuarioPartida uPAvaliador = new UsuarioPartida(partida, uGAvaliador);
                UsuarioPartida uPAvaliado = new UsuarioPartida(partida, uGAvaliado);
                testEntityManager.persist(uPAvaliador);
                testEntityManager.persist(uPAvaliado);
                
                Avaliacao avaliacao = new Avaliacao(5, uPAvaliador, uPAvaliado);
		testEntityManager.persist(avaliacao);
		assertEquals(avaliacao.getNota(), avaliacaoRepository.findOne(avaliacao.getId()).getNota());
                assertEquals(avaliacao.getAvaliador(), avaliacaoRepository.findOne(avaliacao.getId()).getAvaliador());
                assertEquals(avaliacao.getAvaliado(), avaliacaoRepository.findOne(avaliacao.getId()).getAvaliado());
	}
        
        @Test
	public void testFindByAvaliador() {
		LocalDate nascimento = LocalDate.of(1999, 05, 22);
		final Usuario usuarioAvaliador = new Usuario("Luan", "luanparcival@gmail.com", "982580230",
                "1234", nascimento);
                final Usuario usuarioAvaliado = new Usuario("Juca", "juca@gmail.com", "983390056",
                "1234", nascimento);
                testEntityManager.persist(usuarioAvaliador);
                testEntityManager.persist(usuarioAvaliado);
                
                LocalDateTime inicio = LocalDateTime.of(1999, 05, 22, 19, 00, 00);
                LocalDateTime finall = LocalDateTime.of(1999, 05, 22, 20, 00, 00);
                LocalDateTime confirmacao = LocalDateTime.now(); confirmacao.plusDays(2); confirmacao.plusHours(12); confirmacao.plusMinutes(30);
                LocalDateTime avaliacaoTime = LocalDateTime.now(); avaliacaoTime.plusHours(12); avaliacaoTime.plusMinutes(30);
                Grupo grupo = new Grupo("Grupo", "img", 16, 14, 8759, 8654864, 1, inicio, finall, 2,
                confirmacao, avaliacaoTime);
                testEntityManager.persist(grupo);
                
                UsuarioGrupo uGAvaliador = new UsuarioGrupo(usuarioAvaliador, grupo);
                UsuarioGrupo uGAvaliado = new UsuarioGrupo(usuarioAvaliado, grupo);
                testEntityManager.persist(uGAvaliador);
                testEntityManager.persist(uGAvaliado);
                
                LocalDate semana = LocalDate.of(2017, 05, 12);
                Partida partida = new Partida(16, 14, 8759, 8654864, semana, inicio, finall, confirmacao, avaliacaoTime, grupo);
                testEntityManager.persist(partida);
                
                UsuarioPartida uPAvaliador = new UsuarioPartida(partida, uGAvaliador);
                UsuarioPartida uPAvaliado = new UsuarioPartida(partida, uGAvaliado);
                testEntityManager.persist(uPAvaliador);
                testEntityManager.persist(uPAvaliado);
                
                Avaliacao avaliacao = new Avaliacao(5, uPAvaliador, uPAvaliado);
		testEntityManager.persist(avaliacao);
		assertEquals(avaliacao.getNota(), avaliacaoRepository.findByAvaliador(uPAvaliador).getNota());
                assertEquals(avaliacao.getAvaliador(), avaliacaoRepository.findByAvaliador(uPAvaliador).getAvaliador());
                assertEquals(avaliacao.getAvaliado(), avaliacaoRepository.findByAvaliador(uPAvaliador).getAvaliado());
	}
}
