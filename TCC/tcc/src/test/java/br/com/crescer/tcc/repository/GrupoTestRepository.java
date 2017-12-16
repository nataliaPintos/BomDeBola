/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.tcc.repository;

import br.com.crescer.tcc.Repository.GrupoRepository;
import br.com.crescer.tcc.entity.Grupo;
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
public class GrupoTestRepository {
    @Autowired
	private TestEntityManager testEntityManager;

	@Autowired
	private GrupoRepository grupoRepository;

	/**
	 * Test of save method, of class AtorService.
	 */
	@Test
	public void testSave() {
                LocalDateTime inicio = LocalDateTime.of(1999, 05, 22, 19, 00, 00);
                LocalDateTime finall = LocalDateTime.of(1999, 05, 22, 20, 00, 00);
                LocalDateTime confirmacao = LocalDateTime.now(); confirmacao.plusDays(2); confirmacao.plusHours(12); confirmacao.plusMinutes(30);
                LocalDateTime avaliacao = LocalDateTime.now(); avaliacao.plusHours(12); avaliacao.plusMinutes(30);
                Grupo grupo = new Grupo("Grupo", "img", 16, 14, 8759, 8654864, 1, inicio, finall, 2,
                confirmacao, avaliacao);
		grupoRepository.save(grupo);
		assertEquals(grupo.getNome(), testEntityManager.find(Grupo.class, grupo.getId()).getNome());
                assertEquals(grupo.getImagem(), testEntityManager.find(Grupo.class, grupo.getId()).getImagem());
                assertEquals(grupo.getTimeMax(), testEntityManager.find(Grupo.class, grupo.getId()).getTimeMax());
                assertEquals(grupo.getTimeMin(), testEntityManager.find(Grupo.class, grupo.getId()).getTimeMin());
                assertEquals(grupo.getLatitude(), testEntityManager.find(Grupo.class, grupo.getId()).getLatitude(), 0.001);
                assertEquals(grupo.getLongitude(), testEntityManager.find(Grupo.class, grupo.getId()).getLongitude(), 0.001);
                assertEquals(grupo.getDiaSemana(), testEntityManager.find(Grupo.class, grupo.getId()).getDiaSemana());
                assertEquals(grupo.getHoraInicio(), testEntityManager.find(Grupo.class, grupo.getId()).getHoraInicio());
                assertEquals(grupo.getHoraFinal(), testEntityManager.find(Grupo.class, grupo.getId()).getHoraFinal());
                assertEquals(grupo.getDiasConfirmacao(), testEntityManager.find(Grupo.class, grupo.getId()).getDiasConfirmacao());
                assertEquals(grupo.getTempoAvaliacao(), testEntityManager.find(Grupo.class, grupo.getId()).getTempoAvaliacao());
	}

	@Test
	public void testFindAll() {
                LocalDateTime inicio = LocalDateTime.of(1999, 05, 22, 19, 00, 00);
                LocalDateTime finall = LocalDateTime.of(1999, 05, 22, 20, 00, 00);
                LocalDateTime confirmacao = LocalDateTime.now(); confirmacao.plusDays(2); confirmacao.plusHours(12); confirmacao.plusMinutes(30);
                LocalDateTime avaliacao = LocalDateTime.now(); avaliacao.plusHours(12); avaliacao.plusMinutes(30);
                Grupo grupo = new Grupo("Grupo", "img", 16, 14, 8759, 8654864, 1, inicio, finall, 2,
                confirmacao, avaliacao);
		testEntityManager.persist(grupo);

		assertTrue(StreamSupport.stream(grupoRepository.findAll().spliterator(), false)
				.map(Grupo::getNome).collect(toList()).contains(grupo.getNome()));
                assertTrue(StreamSupport.stream(grupoRepository.findAll().spliterator(), false)
				.map(Grupo::getImagem).collect(toList()).contains(grupo.getImagem()));
                assertTrue(StreamSupport.stream(grupoRepository.findAll().spliterator(), false)
				.map(Grupo::getTimeMax).collect(toList()).contains(grupo.getTimeMax()));
                assertTrue(StreamSupport.stream(grupoRepository.findAll().spliterator(), false)
				.map(Grupo::getTimeMin).collect(toList()).contains(grupo.getTimeMin()));
                assertTrue(StreamSupport.stream(grupoRepository.findAll().spliterator(), false)
				.map(Grupo::getLatitude).collect(toList()).contains(grupo.getLatitude()));
                assertTrue(StreamSupport.stream(grupoRepository.findAll().spliterator(), false)
				.map(Grupo::getLongitude).collect(toList()).contains(grupo.getLongitude()));
                assertTrue(StreamSupport.stream(grupoRepository.findAll().spliterator(), false)
				.map(Grupo::getDiaSemana).collect(toList()).contains(grupo.getDiaSemana()));
                assertTrue(StreamSupport.stream(grupoRepository.findAll().spliterator(), false)
				.map(Grupo::getHoraInicio).collect(toList()).contains(grupo.getHoraInicio()));
                assertTrue(StreamSupport.stream(grupoRepository.findAll().spliterator(), false)
				.map(Grupo::getHoraFinal).collect(toList()).contains(grupo.getHoraFinal()));
                assertTrue(StreamSupport.stream(grupoRepository.findAll().spliterator(), false)
				.map(Grupo::getDiasConfirmacao).collect(toList()).contains(grupo.getDiasConfirmacao()));
                assertTrue(StreamSupport.stream(grupoRepository.findAll().spliterator(), false)
				.map(Grupo::getHorasConfirmacao).collect(toList()).contains(grupo.getHorasConfirmacao()));
                assertTrue(StreamSupport.stream(grupoRepository.findAll().spliterator(), false)
				.map(Grupo::getTempoAvaliacao).collect(toList()).contains(grupo.getTempoAvaliacao()));
	}

	@Test
	public void testFindOne() {
		LocalDateTime inicio = LocalDateTime.of(1999, 05, 22, 19, 00, 00);
                LocalDateTime finall = LocalDateTime.of(1999, 05, 22, 20, 00, 00);
                LocalDateTime confirmacao = LocalDateTime.now(); confirmacao.plusDays(2); confirmacao.plusHours(12); confirmacao.plusMinutes(30);
                LocalDateTime avaliacao = LocalDateTime.now(); avaliacao.plusHours(12); avaliacao.plusMinutes(30);
                Grupo grupo = new Grupo("Grupo", "img", 16, 14, 8759, 8654864, 1, inicio, finall, 2,
                confirmacao, avaliacao);
		testEntityManager.persist(grupo);
		assertEquals(grupo.getNome(), grupoRepository.findOne(grupo.getId()).getNome());
                assertEquals(grupo.getImagem(), grupoRepository.findOne(grupo.getId()).getImagem());
                assertEquals(grupo.getTimeMax(), grupoRepository.findOne(grupo.getId()).getTimeMax());
                assertEquals(grupo.getTimeMin(), grupoRepository.findOne(grupo.getId()).getTimeMin());
                assertEquals(grupo.getLatitude(), grupoRepository.findOne(grupo.getId()).getLatitude(), 0.001);
                assertEquals(grupo.getLongitude(), grupoRepository.findOne(grupo.getId()).getLongitude(), 0.001);
                assertEquals(grupo.getDiaSemana(), grupoRepository.findOne(grupo.getId()).getDiaSemana());
                assertEquals(grupo.getHoraInicio(), grupoRepository.findOne(grupo.getId()).getHoraInicio());
                assertEquals(grupo.getHoraFinal(), grupoRepository.findOne(grupo.getId()).getHoraFinal());
                assertEquals(grupo.getDiasConfirmacao(), grupoRepository.findOne(grupo.getId()).getDiasConfirmacao());
                assertEquals(grupo.getTempoAvaliacao(), grupoRepository.findOne(grupo.getId()).getTempoAvaliacao());
	}

}
