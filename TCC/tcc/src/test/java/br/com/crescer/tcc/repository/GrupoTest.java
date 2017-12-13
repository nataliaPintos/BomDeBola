/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.tcc.repository;

import br.com.crescer.tcc.entity.Usuario;
import br.com.crescer.tcc.Repository.GrupoRepository;
import br.com.crescer.tcc.entity.Grupo;
import java.time.LocalDate;
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
		LocalDate nascimento = LocalDate.of(1999, 05, 22);
		final Usuario usuario = new Usuario("Luan", "luanparcival@gmail.com", "982580230",
                "1234", nascimento);
                LocalDateTime inicio = LocalDateTime.of(1999, 05, 22, 19, 00, 00);
                LocalDateTime finall = LocalDateTime.of(1999, 05, 22, 20, 00, 00);
                LocalDateTime confirmacao = LocalDateTime.now(); confirmacao.plusDays(2); confirmacao.plusHours(12); confirmacao.plusMinutes(30);
                LocalDateTime avaliacao = LocalDateTime.now(); avaliacao.plusHours(12); avaliacao.plusMinutes(30);
                Grupo grupo = new Grupo("Grupo", "img", 16, 14, 8759, 8654864, 1, inicio, finall, 2,
                confirmacao, avaliacao);
		grupoRepository.save(grupo);
		assertEquals(grupo.getNome(), testEntityManager.find(Grupo.class, grupo.getId()).getNome());
	}
/*
	@Test
	public void testFindAll() {
                LocalDate nascimento = LocalDate.of(1999, 05, 22);
		final Usuario usuario = new Usuario("Luan", "luanparcival@gmail.com", "982580230",
                "1234", nascimento);
		testEntityManager.persist(usuario);

		assertTrue(StreamSupport.stream(usuarioRepository.findAll().spliterator(), false)
				.map(Usuario::getNome).collect(toList()).contains(usuario.getNome()));
                assertTrue(StreamSupport.stream(usuarioRepository.findAll().spliterator(), false)
				.map(Usuario::getEmail).collect(toList()).contains(usuario.getEmail()));
                assertTrue(StreamSupport.stream(usuarioRepository.findAll().spliterator(), false)
				.map(Usuario::getTelefone).collect(toList()).contains(usuario.getTelefone()));
                assertTrue(StreamSupport.stream(usuarioRepository.findAll().spliterator(), false)
				.map(Usuario::getSenha).collect(toList()).contains(usuario.getSenha()));
                assertTrue(StreamSupport.stream(usuarioRepository.findAll().spliterator(), false)
				.map(Usuario::getNascimento).collect(toList()).contains(usuario.getNascimento()));
	}

	@Test
	public void testFindOne() {
		LocalDate nascimento = LocalDate.of(1999, 05, 22);
		final Usuario usuario = new Usuario("Luan", "luanparcival@gmail.com", "982580230",
                "1234", nascimento);
		testEntityManager.persist(usuario);
		assertEquals(usuario.getNome(), usuarioRepository.findOne(usuario.getId()).getNome());
                assertEquals(usuario.getEmail(), usuarioRepository.findOne(usuario.getId()).getEmail());
                assertEquals(usuario.getTelefone(), usuarioRepository.findOne(usuario.getId()).getTelefone());
                assertEquals(usuario.getSenha(), usuarioRepository.findOne(usuario.getId()).getSenha());
                assertEquals(usuario.getNascimento(), usuarioRepository.findOne(usuario.getId()).getNascimento());
	}
        
        @Test
	public void testFindByEmail() {
		LocalDate nascimento = LocalDate.of(1999, 05, 22);
		final Usuario usuario = new Usuario("Luan", "luanparcival@gmail.com", "982580230",
                "1234", nascimento);
		testEntityManager.persist(usuario);
		assertEquals(usuario.getNome(), usuarioRepository.findByEmailIgnoreCase(usuario.getEmail()).getNome());
                assertEquals(usuario.getEmail(), usuarioRepository.findByEmailIgnoreCase(usuario.getEmail()).getEmail());
                assertEquals(usuario.getTelefone(), usuarioRepository.findByEmailIgnoreCase(usuario.getEmail()).getTelefone());
                assertEquals(usuario.getSenha(), usuarioRepository.findByEmailIgnoreCase(usuario.getEmail()).getSenha());
                assertEquals(usuario.getNascimento(), usuarioRepository.findByEmailIgnoreCase(usuario.getEmail()).getNascimento());
	}*/

}
