/**
 * 
 */
package com.webcontext.apps.gk2.tests;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.webcontext.apps.gk2.models.Game;
import com.webcontext.apps.gk2.persist.GameService;

/**
 * @author Frederic Delorme<frederic.delorme@gmail.com>
 *
 */
public class GameServiceTest extends GenericServiceTest {

	private GameService gameService;
	
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		gameService = new GameService();
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.webcontext.apps.gk2.persist.GenericService#findAll()}.
	 */
	@Test
	public void testFindAll() {
		List<Game> games = gameService.findAll();
		
	}

	/**
	 * Test method for {@link com.webcontext.apps.gk2.persist.GenericService#findAll(int, int)}.
	 */
	@Test
	public void testFindAllIntInt() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.webcontext.apps.gk2.persist.GenericService#findById(java.lang.Object)}.
	 */
	@Test
	public void testFindById() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.webcontext.apps.gk2.persist.GenericService#count()}.
	 */
	@Test
	public void testCount() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.webcontext.apps.gk2.persist.GenericService#save(java.lang.Object)}.
	 */
	@Test
	public void testSave() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.webcontext.apps.gk2.persist.GenericService#delete(java.lang.Object)}.
	 */
	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

}
