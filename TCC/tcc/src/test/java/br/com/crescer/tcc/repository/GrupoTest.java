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
public class GrupoTest {
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
                assertEquals(grupo.getTime_max(), testEntityManager.find(Grupo.class, grupo.getId()).getTime_max());
                assertEquals(grupo.getTime_min(), testEntityManager.find(Grupo.class, grupo.getId()).getTime_min());
                assertEquals(grupo.getLatitude(), testEntityManager.find(Grupo.class, grupo.getId()).getLatitude(), 0.001);
                assertEquals(grupo.getLongitude(), testEntityManager.find(Grupo.class, grupo.getId()).getLongitude(), 0.001);
                assertEquals(grupo.getDia_semana(), testEntityManager.find(Grupo.class, grupo.getId()).getDia_semana());
                assertEquals(grupo.getHora_inicio(), testEntityManager.find(Grupo.class, grupo.getId()).getHora_inicio());
                assertEquals(grupo.getHora_final(), testEntityManager.find(Grupo.class, grupo.getId()).getHora_final());
                assertEquals(grupo.getDias_confirmacao(), testEntityManager.find(Grupo.class, grupo.getId()).getDias_confirmacao());
                assertEquals(grupo.getTempo_avaliacao(), testEntityManager.find(Grupo.class, grupo.getId()).getTempo_avaliacao());
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
				.map(Grupo::getTime_max).collect(toList()).contains(grupo.getTime_max()));
                assertTrue(StreamSupport.stream(grupoRepository.findAll().spliterator(), false)
				.map(Grupo::getTime_min).collect(toList()).contains(grupo.getTime_min()));
                assertTrue(StreamSupport.stream(grupoRepository.findAll().spliterator(), false)
				.map(Grupo::getLatitude).collect(toList()).contains(grupo.getLatitude()));
                assertTrue(StreamSupport.stream(grupoRepository.findAll().spliterator(), false)
				.map(Grupo::getLongitude).collect(toList()).contains(grupo.getLongitude()));
                assertTrue(StreamSupport.stream(grupoRepository.findAll().spliterator(), false)
				.map(Grupo::getDia_semana).collect(toList()).contains(grupo.getDia_semana()));
                assertTrue(StreamSupport.stream(grupoRepository.findAll().spliterator(), false)
				.map(Grupo::getHora_inicio).collect(toList()).contains(grupo.getHora_inicio()));
                assertTrue(StreamSupport.stream(grupoRepository.findAll().spliterator(), false)
				.map(Grupo::getHora_final).collect(toList()).contains(grupo.getHora_final()));
                assertTrue(StreamSupport.stream(grupoRepository.findAll().spliterator(), false)
				.map(Grupo::getDias_confirmacao).collect(toList()).contains(grupo.getDias_confirmacao()));
                assertTrue(StreamSupport.stream(grupoRepository.findAll().spliterator(), false)
				.map(Grupo::getHoras_confirmacao).collect(toList()).contains(grupo.getHoras_confirmacao()));
                assertTrue(StreamSupport.stream(grupoRepository.findAll().spliterator(), false)
				.map(Grupo::getTempo_avaliacao).collect(toList()).contains(grupo.getTempo_avaliacao()));
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
                assertEquals(grupo.getTime_max(), grupoRepository.findOne(grupo.getId()).getTime_max());
                assertEquals(grupo.getTime_min(), grupoRepository.findOne(grupo.getId()).getTime_min());
                assertEquals(grupo.getLatitude(), grupoRepository.findOne(grupo.getId()).getLatitude(), 0.001);
                assertEquals(grupo.getLongitude(), grupoRepository.findOne(grupo.getId()).getLongitude(), 0.001);
                assertEquals(grupo.getDia_semana(), grupoRepository.findOne(grupo.getId()).getDia_semana());
                assertEquals(grupo.getHora_inicio(), grupoRepository.findOne(grupo.getId()).getHora_inicio());
                assertEquals(grupo.getHora_final(), grupoRepository.findOne(grupo.getId()).getHora_final());
                assertEquals(grupo.getDias_confirmacao(), grupoRepository.findOne(grupo.getId()).getDias_confirmacao());
                assertEquals(grupo.getTempo_avaliacao(), grupoRepository.findOne(grupo.getId()).getTempo_avaliacao());
	}

}
