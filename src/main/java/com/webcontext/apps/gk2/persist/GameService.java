package com.webcontext.apps.gk2.persist;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import com.webcontext.apps.gk2.models.Game;

/**
 * Persistence Service for Game Entity. Provide basic CRUD operation from
 * GenericService<T,PK>.
 * 
 * @see GenericService
 * 
 * @author Frederic Delorme<frederic.delorme@gmail.com>
 * @since 2014/04/08
 * 
 */
public class GameService extends GenericService<Game, Long> {

	public GameService() {
		super();
	}
	
	/**
	 * @param emf
	 */
	public GameService(EntityManagerFactory emf) {
		super(emf);
	}

	/**
	 * @param unitName
	 */
	public GameService(String unitName) {
		super(unitName);
	}

	/**
	 * Retrieve the game corresponding to the <code>gameName</code>.
	 * 
	 * @param gameTitle
	 *            the game title to search for
	 * @return list of corresponding game to the <code>gameTitle</code>.
	 */
	public List<Game> findByTitle(String gameTitle) {
		TypedQuery<Game> query = em.createQuery(
				"select g from Game g where g.title like :gameTitle",
				Game.class);
		query.setParameter("gameTitle", gameTitle);
		return query.getResultList();
	}

}
