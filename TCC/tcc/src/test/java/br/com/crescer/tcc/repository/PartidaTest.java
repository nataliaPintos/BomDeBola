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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author luan.avila
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional(propagation = Propagation.REQUIRED)
@ActiveProfiles("test")
public class PartidaTest {
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
                assertEquals(partida.getTime_max(), testEntityManager.find(Partida.class, partida.getId()).getTime_max());
                assertEquals(partida.getTime_min(), testEntityManager.find(Partida.class, partida.getId()).getTime_min());
                assertEquals(partida.getLatitude(), testEntityManager.find(Partida.class, partida.getId()).getLatitude(), 0.001);
                assertEquals(partida.getLongitude(), testEntityManager.find(Partida.class, partida.getId()).getLongitude(), 0.001);
                assertEquals(partida.getDia_semana(), testEntityManager.find(Partida.class, partida.getId()).getDia_semana());
                assertEquals(partida.getHora_inicio(), testEntityManager.find(Partida.class, partida.getId()).getHora_inicio());
                assertEquals(partida.getHora_final(), testEntityManager.find(Partida.class, partida.getId()).getHora_final());
                assertEquals(partida.getTempo_confirmacao(), testEntityManager.find(Partida.class, partida.getId()).getTempo_confirmacao());
                assertEquals(partida.getTempo_avaliacao(), testEntityManager.find(Partida.class, partida.getId()).getTempo_avaliacao());
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
				.map(Partida::getTime_max).collect(toList()).contains(partida.getTime_max()));
                assertTrue(StreamSupport.stream(partidaRepository.findAll().spliterator(), false)
				.map(Partida::getTime_min).collect(toList()).contains(partida.getTime_min()));
                assertTrue(StreamSupport.stream(partidaRepository.findAll().spliterator(), false)
				.map(Partida::getLatitude).collect(toList()).contains(partida.getLatitude()));
                assertTrue(StreamSupport.stream(partidaRepository.findAll().spliterator(), false)
				.map(Partida::getLongitude).collect(toList()).contains(partida.getLongitude()));
                assertTrue(StreamSupport.stream(partidaRepository.findAll().spliterator(), false)
				.map(Partida::getDia_semana).collect(toList()).contains(partida.getDia_semana()));
                assertTrue(StreamSupport.stream(partidaRepository.findAll().spliterator(), false)
				.map(Partida::getHora_inicio).collect(toList()).contains(partida.getHora_inicio()));
                assertTrue(StreamSupport.stream(partidaRepository.findAll().spliterator(), false)
				.map(Partida::getHora_final).collect(toList()).contains(partida.getHora_final()));
                assertTrue(StreamSupport.stream(partidaRepository.findAll().spliterator(), false)
				.map(Partida::getTempo_confirmacao).collect(toList()).contains(partida.getTempo_confirmacao()));
                assertTrue(StreamSupport.stream(partidaRepository.findAll().spliterator(), false)
				.map(Partida::getTempo_avaliacao).collect(toList()).contains(partida.getTempo_avaliacao()));
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
                
                assertEquals(partida.getTime_max(), partidaRepository.findOne(partida.getId()).getTime_max());
                assertEquals(partida.getTime_min(), partidaRepository.findOne(partida.getId()).getTime_min());
                assertEquals(partida.getLatitude(), partidaRepository.findOne(partida.getId()).getLatitude(), 0.001);
                assertEquals(partida.getLongitude(), partidaRepository.findOne(partida.getId()).getLongitude(), 0.001);
                assertEquals(partida.getDia_semana(), partidaRepository.findOne(partida.getId()).getDia_semana());
                assertEquals(partida.getHora_inicio(), partidaRepository.findOne(partida.getId()).getHora_inicio());
                assertEquals(partida.getHora_final(), partidaRepository.findOne(partida.getId()).getHora_final());
                assertEquals(partida.getTempo_confirmacao(), partidaRepository.findOne(partida.getId()).getTempo_confirmacao());
                assertEquals(partida.getTempo_avaliacao(), partidaRepository.findOne(partida.getId()).getTempo_avaliacao());
	}
}
