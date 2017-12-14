/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.tcc.repository;

import br.com.crescer.tcc.entity.Grupo;
import br.com.crescer.tcc.entity.Partida;
import java.time.LocalDate;
import br.com.crescer.tcc.Repository.PartidaRepository;
import java.time.LocalDateTime;
import static java.util.stream.Collectors.toList;
import java.util.stream.StreamSupport;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Ignore;
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
public class PartidaTestRepository {
    @Autowired
	private TestEntityManager testEntityManager;

	@Autowired
	private PartidaRepository partidaRepository;

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
                
                LocalDate semana = LocalDate.of(2017, 05, 12);
                Partida partida = new Partida(16, 14, 8759, 8654864, semana, inicio, finall, confirmacao, avaliacao, grupo);
		partidaRepository.save(partida);
                assertEquals(partida.getTimeMax(), testEntityManager.find(Partida.class, partida.getId()).getTimeMax());
                assertEquals(partida.getTimeMin(), testEntityManager.find(Partida.class, partida.getId()).getTimeMin());
                assertEquals(partida.getLatitude(), testEntityManager.find(Partida.class, partida.getId()).getLatitude(), 0.001);
                assertEquals(partida.getLongitude(), testEntityManager.find(Partida.class, partida.getId()).getLongitude(), 0.001);
                assertEquals(partida.getDiaSemana(), testEntityManager.find(Partida.class, partida.getId()).getDiaSemana());
                assertEquals(partida.getHoraInicio(), testEntityManager.find(Partida.class, partida.getId()).getHoraInicio());
                assertEquals(partida.getHoraFinal(), testEntityManager.find(Partida.class, partida.getId()).getHoraFinal());
                assertEquals(partida.getTempoConfirmacao(), testEntityManager.find(Partida.class, partida.getId()).getTempoConfirmacao());
                assertEquals(partida.getTempoAvaliacao(), testEntityManager.find(Partida.class, partida.getId()).getTempoAvaliacao());
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
                LocalDate semana = LocalDate.of(2017, 05, 12);
                Partida partida = new Partida(16, 14, 8759, 8654864, semana, inicio, finall, confirmacao, avaliacao, grupo);
                testEntityManager.persist(partida);

                assertTrue(StreamSupport.stream(partidaRepository.findAll().spliterator(), false)
				.map(Partida::getTimeMax).collect(toList()).contains(partida.getTimeMax()));
                assertTrue(StreamSupport.stream(partidaRepository.findAll().spliterator(), false)
				.map(Partida::getTimeMin).collect(toList()).contains(partida.getTimeMin()));
                assertTrue(StreamSupport.stream(partidaRepository.findAll().spliterator(), false)
				.map(Partida::getLatitude).collect(toList()).contains(partida.getLatitude()));
                assertTrue(StreamSupport.stream(partidaRepository.findAll().spliterator(), false)
				.map(Partida::getLongitude).collect(toList()).contains(partida.getLongitude()));
                assertTrue(StreamSupport.stream(partidaRepository.findAll().spliterator(), false)
				.map(Partida::getDiaSemana).collect(toList()).contains(partida.getDiaSemana()));
                assertTrue(StreamSupport.stream(partidaRepository.findAll().spliterator(), false)
				.map(Partida::getHoraInicio).collect(toList()).contains(partida.getHoraInicio()));
                assertTrue(StreamSupport.stream(partidaRepository.findAll().spliterator(), false)
				.map(Partida::getHoraFinal).collect(toList()).contains(partida.getHoraFinal()));
                assertTrue(StreamSupport.stream(partidaRepository.findAll().spliterator(), false)
				.map(Partida::getTempoConfirmacao).collect(toList()).contains(partida.getTempoConfirmacao()));
                assertTrue(StreamSupport.stream(partidaRepository.findAll().spliterator(), false)
				.map(Partida::getTempoAvaliacao).collect(toList()).contains(partida.getTempoAvaliacao()));
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
                LocalDate semana = LocalDate.of(2017, 05, 12);
                Partida partida = new Partida(16, 14, 8759, 8654864, semana, inicio, finall, confirmacao, avaliacao, grupo);
                testEntityManager.persist(partida);
                
                assertEquals(partida.getTimeMax(), partidaRepository.findOne(partida.getId()).getTimeMax());
                assertEquals(partida.getTimeMin(), partidaRepository.findOne(partida.getId()).getTimeMin());
                assertEquals(partida.getLatitude(), partidaRepository.findOne(partida.getId()).getLatitude(), 0.001);
                assertEquals(partida.getLongitude(), partidaRepository.findOne(partida.getId()).getLongitude(), 0.001);
                assertEquals(partida.getDiaSemana(), partidaRepository.findOne(partida.getId()).getDiaSemana());
                assertEquals(partida.getHoraInicio(), partidaRepository.findOne(partida.getId()).getHoraInicio());
                assertEquals(partida.getHoraFinal(), partidaRepository.findOne(partida.getId()).getHoraFinal());
                assertEquals(partida.getTempoConfirmacao(), partidaRepository.findOne(partida.getId()).getTempoConfirmacao());
                assertEquals(partida.getTempoAvaliacao(), partidaRepository.findOne(partida.getId()).getTempoAvaliacao());
	}
}
