/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.tcc.repository;

import br.com.crescer.tcc.Repository.UsuarioRepository;
import br.com.crescer.tcc.entity.Usuario;
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
		final Usuario usuario = new Usuario();
		usuario.setNome("Luan");
		usuarioRepository.save(usuario);
		assertEquals(usuario.getNome(), testEntityManager.find(Usuario.class, usuario.getId()).getNome());
	}
/*
	@Test
	public void testFindAll() {
		final Usuario usuario = new Usuario();
		usuario.setNome("Luan");
		testEntityManager.persist(usuario);

		assertTrue(StreamSupport.stream
        (usuarioRepository.findAll()
                .spliterator(), 
                false)
				.map(Usuario::getNome)
				.collect(toList())
				.contains(usuario.getNome()));
	}*/

	@Test
	public void testFindOne() {
		final Usuario usuario = new Usuario();
		usuario.setNome("Luan");
		testEntityManager.persist(usuario);
		assertEquals(usuario.getNome(), usuarioRepository.findOne(usuario.getId()).getNome());
	}

}
