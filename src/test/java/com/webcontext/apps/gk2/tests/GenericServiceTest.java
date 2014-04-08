package com.webcontext.apps.gk2.tests;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.BeforeClass;

/**
 * GenericService test class providing a specific database access without any
 * JavaEE container.
 * 
 * @author Frederic Delorme<frederic.delorme@gmail.com>
 * 
 */
public class GenericServiceTest {
	/**
	 * Internal Test entities to access database on a dedicated test database
	 * instance.
	 */
	protected static EntityManagerFactory emf;
	protected static EntityManager em;

	/**
	 * Initialize Database connection.
	 */
	@BeforeClass
	public static void setup() {
		emf = Persistence.createEntityManagerFactory("gk2-test");
		em = emf.createEntityManager();
	}

}
