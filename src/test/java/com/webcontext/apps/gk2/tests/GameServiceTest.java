package com.webcontext.apps.gk2.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.webcontext.apps.gk2.models.Game;
import com.webcontext.apps.gk2.persist.GameService;

/**
 * Unit Test for GameService.
 * 
 * @author Frederic Delorme<frederic.delorme@gmail.com>
 * 
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GameServiceTest extends GenericServiceTest {

	private static GameService gameService = new GameService(emf);

	private static Map<String, Game> games = new HashMap<String, Game>();

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUp() throws Exception {

		games.put("game1", new Game("Game 1", "This is the game 1", "", 1, 2,
				new Date(), "test"));
		games.put("game2", new Game("Game 2", "This is the game 2", "", 2, 4,
				new Date(), "test"));
		games.put("game3", new Game("Game 3", "This is the game 3", "", 3, 6,
				new Date(), "test"));

		createGames();

	}

	/**
	 * Create prepared games for test purpose.
	 */
	private static void createGames() {
		for (Game game : games.values()) {
			gameService.save(game);
		}
	}

	/**
	 * Test method for
	 * {@link com.webcontext.apps.gk2.persist.GenericService#findAll()}.
	 */
	@Test
	public void test_1_FindAll() {
		List<Game> games = gameService.findAll();
		assertEquals("Number of retrieved games in database does not match.",
				3, games.size());
	}

	/**
	 * Test method for
	 * {@link com.webcontext.apps.gk2.persist.GenericService#findAll(int, int)}.
	 */
	@Test
	public void test_2_FindAllIntInt() {
		List<Game> games = gameService.findAll(1, 2);
		assertEquals("Number of retrieved games in database does not match.",
				2, games.size());
	}

	/**
	 * Test method for
	 * {@link com.webcontext.apps.gk2.persist.GenericService#findById(java.lang.Object)}
	 * .
	 */
	@Test
	public void test_3_FindById() {
		Game game = gameService.findById(new Long(1));
		assertTrue("Game has not been retrieved from database", game != null);
	}

	/**
	 * Test method for
	 * {@link com.webcontext.apps.gk2.persist.GenericService#count()}.
	 */
	@Test
	public void test_4_Count() {
		long count = gameService.count();
		assertEquals("Number of pref=defined game for test is not found !", 3,
				count);
	}

	/**
	 * Test method for
	 * {@link com.webcontext.apps.gk2.persist.GenericService#save(java.lang.Object)}
	 * .
	 */
	@Test
	public void test_5_Save() {
		Game gameN = new Game("Game N", "This is the game N", "", 9, 9,
				new Date(), "test");

		gameService.save(gameN);

		List<Game> retrievedGames = gameService.findByTitle("Game N");
		assertEquals("Game N was not saved.", 1, retrievedGames.size());
	}

	/**
	 * Test method for
	 * {@link com.webcontext.apps.gk2.persist.GenericService#findByTitle(java.lang.String)}
	 * .
	 */
	@Test
	public void test_6_FindByTitle() {
		List<Game> retrievedGames = gameService.findByTitle("%Game%");
		assertEquals("Games which title's like '%Game%' was not found !", 4,
				retrievedGames.size());
	}

	/**
	 * Test method for
	 * {@link com.webcontext.apps.gk2.persist.GenericService#delete(java.lang.Object)}
	 * .
	 */
	@Test
	public void test_7_Delete() {
		Game gameToDelete = gameService.findById(new Long(1));
		gameService.delete(gameToDelete.getId());

		List<Game> gamesDeletedNotHere = gameService.findByTitle(gameToDelete
				.getTitle());

		assertTrue("Game has not been deleted !",
				gamesDeletedNotHere.size() == 0);

	}

}
