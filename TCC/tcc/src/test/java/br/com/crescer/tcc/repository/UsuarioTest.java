/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.tcc.repository;

import br.com.crescer.tcc.Repository.UsuarioRepository;
import br.com.crescer.tcc.entity.Usuario;
import java.time.LocalDate;
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

/**
 * @author carloshenrique
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional(propagation = Propagation.REQUIRED)
@ActiveProfiles("test")
public class UsuarioTest {

	@Autowired
	private TestEntityManager testEntityManager;

	@Autowired
	private UsuarioRepository usuarioRepository;

	/**
	 * Test of save method, of class AtorService.
	 */
	@Test
	public void testSave() {
		LocalDate nascimento = LocalDate.of(1999, 05, 22);
		final Usuario usuario = new Usuario("Luan", "luanparcival@gmail.com", "982580230",
                "1234", nascimento);
		usuarioRepository.save(usuario);
		assertEquals(usuario.getNome(), testEntityManager.find(Usuario.class, usuario.getId()).getNome());
                assertEquals(usuario.getEmail(), testEntityManager.find(Usuario.class, usuario.getId()).getEmail());
                assertEquals(usuario.getTelefone(), testEntityManager.find(Usuario.class, usuario.getId()).getTelefone());
                assertEquals(usuario.getSenha(), testEntityManager.find(Usuario.class, usuario.getId()).getSenha());
                assertEquals(usuario.getNascimento(), testEntityManager.find(Usuario.class, usuario.getId()).getNascimento());
                assertEquals(usuario.getGols(), testEntityManager.find(Usuario.class, usuario.getId()).getGols());
                assertEquals(usuario.getNota_geral(), testEntityManager.find(Usuario.class, usuario.getId()).getNota_geral(), 0.001);
	}

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
                assertTrue(StreamSupport.stream(usuarioRepository.findAll().spliterator(), false)
				.map(Usuario::getGols).collect(toList()).contains(usuario.getGols()));
                assertTrue(StreamSupport.stream(usuarioRepository.findAll().spliterator(), false)
				.map(Usuario::getNota_geral).collect(toList()).contains(usuario.getNota_geral()));
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
                assertEquals(usuario.getGols(), usuarioRepository.findOne(usuario.getId()).getGols());
                assertEquals(usuario.getNota_geral(), usuarioRepository.findOne(usuario.getId()).getNota_geral(), 0.001);
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
                assertEquals(usuario.getGols(), usuarioRepository.findByEmailIgnoreCase(usuario.getEmail()).getGols());
                assertEquals(usuario.getNota_geral(), usuarioRepository.findByEmailIgnoreCase(usuario.getEmail()).getNota_geral(), 0.001);
	}

}
