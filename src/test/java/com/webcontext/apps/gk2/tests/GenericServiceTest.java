/**
 * 
 */
package com.webcontext.apps.gk2.tests;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Frederic Delorme<frederic.delorme@gmail.com>
 *
 */
public class GenericServiceTest {

	protected EntityManagerFactory emf;
	protected EntityManager em;
	
	
	public GenericServiceTest(){
		emf = Persistence.createEntityManagerFactory("gk2-test");
		em = emf.createEntityManager();
	}
	
	
}
