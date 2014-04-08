/**
 * 
 */
package com.webcontext.apps.gk2.persist;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.apache.log4j.Logger;

/**
 * 
 * Generic Service to provide Persistence to Entity T.
 * 
 * @author Frederic Delorme<frederic.delorme@gmail.com>
 * 
 */
public class GenericService<T, PK> {

	private static Logger logger = Logger.getLogger(GenericService.class);

	protected EntityManagerFactory emf;
	protected EntityManager em;

	protected long offset;
	protected long pageSize;

	/**
	 * Entity Class name manipulated by this DAO.
	 */
	protected Class<T> entityClass;

	/**
	 * Default Constructor.
	 */
	public GenericService() {
		ParameterizedType genericSuperClass = (ParameterizedType) getClass()
				.getGenericSuperclass();
		@SuppressWarnings("unchecked")
		Class<T> entityClass = (Class<T>) genericSuperClass
				.getActualTypeArguments()[0];
		this.entityClass = entityClass;
	}

	public GenericService(String unitName) {
		this();
		if (emf == null) {
			emf = Persistence.createEntityManagerFactory(unitName);
			logger.debug("Entity Manager Factory Initialiazed");
		}
		init();
	}

	/**
	 * Default parameterized constructor for DAO.
	 * 
	 * @param emf
	 *            EntityManager
	 */
	public GenericService(EntityManagerFactory emf) {
		this();
		this.emf = emf;
		init();
	}

	public EntityManagerFactory getEmf() {
		return this.emf;
	}

	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public void init() {
		em = emf.createEntityManager();
		logger.debug("EntityManager initialized.");
	}

	/**
	 * <p>
	 * Retrieve all <code>T</code> entity respecting <code>pageSize</code>.
	 * </p>
	 * 
	 * @return
	 */
	public List<T> findAll() {
		List<T> list;
		logger.debug(String.format(
				"Retrieve all entities %s without pagination...",
				entityClass.getSimpleName()));
		list = findAll(0, -1);
		logger.debug("done.");
		return list;
	}

	/**
	 * <p>
	 * Retrieve paginated data for T entity.
	 * </p>
	 * <p/>
	 * <p>
	 * If <code>pageSize</code> equal <strong>-1</strong>, pageSize is not added
	 * to request to retrieve all occurrences (performance issue).
	 * </p>
	 * <p>
	 * If <code>offset</code> is greater than <strong>0</strong>, the offset
	 * would not be integrate to request (performance issue).
	 * </p>
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAll(int offset, int pageSize) {
		List<T> list;
		logger.debug(String.format("Retrieve entities %s...",
				entityClass.getSimpleName()));

		Query q = em.createQuery("SELECT x from " + entityClass.getSimpleName()
				+ " x");
		if (offset > 0) {
			this.offset = offset;
			q.setFirstResult(offset);
			logger.debug(String.format("set offset=%d pagination", offset));
		}
		if (pageSize != -1) {
			this.pageSize = pageSize;
			q.setMaxResults(pageSize);
			logger.debug(String.format("set pageSize=%d pagination", pageSize));
		}
		list = q.getResultList();
		logger.debug("done.");
		return list;
	}

	/**
	 * Retrieve the <code>entity</code> T on its primaryKey <code>id</code>.
	 * 
	 * @param id
	 *            primary key for the T entity.
	 * @return
	 */
	public T findById(PK id) {
		T entity = null;
		logger.debug(String.format(
				"Retrieve Entity %s with Primary key %s ...", entityClass, id));
		entity = em.find(entityClass, id);
		logger.debug("done.");
		return entity;
	}

	/**
	 * Retrieve number of occurrence for the entity.
	 * 
	 * @return
	 */
	public long count() {
		long entityCount = 0;
		logger.debug(String.format("Count Entity %s occurrence ...",
				entityClass));
		Query query = em.createQuery("select count(c) from Contact c");
		entityCount = (Long) query.getSingleResult();
		logger.debug(String.format(" +-> found %d occurrences", entityCount));
		return entityCount;
	}

	/**
	 * Save to persistence the entity in a transaction.
	 * 
	 * @param entity
	 * @return
	 */
	public T save(T entity) {
		logger.debug(String.format("Save entity %s ...",
				entityClass.getSimpleName()));
		try {

			em.persist(entity);
			em.refresh(entity);
			logger.debug(String.format("insert %s :  %s",
					entityClass.getSimpleName(), entity));
		} catch (PersistenceException e) {
			entity = null;
		}
		logger.debug("done.");
		return entity;
	}

	/**
	 * Delete an entity on its <code>id</code>.
	 * 
	 * @param id
	 * @return
	 */
	public T delete(PK id) {
		logger.debug(String.format("Delete Entity %s on Primary key %s ...",
				entityClass, id));
		T entity = null;
		try {

			entity = em.find(entityClass, id);
			em.remove(entity);
			logger.debug(String.format("remove entity %s(%s)", entityClass,
					entity));

		} catch (Throwable t) {
			entity = null;
		}
		logger.debug("done.");
		return entity;
	}

	/**
	 * @return the offset
	 */
	public long getOffset() {
		return this.offset;
	}

	/**
	 * @param offset
	 *            the offset to set
	 */
	public void setOffset(long offset) {
		this.offset = offset;
	}

	/**
	 * @return the pageSize
	 */
	public long getPageSize() {
		return this.pageSize;
	}

	/**
	 * @param pageSize
	 *            the pageSize to set
	 */
	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * Return the EntityManager.
	 * 
	 * @return
	 */
	public EntityManager getEm() {
		return em;
	}

	/**
	 * Commit the EntityTransaction et.
	 * 
	 * @param et
	 */
	public void commit(EntityTransaction et) {
		et.commit();
	}

	/**
	 * Rollback the EntityTransaction et.
	 * 
	 * @param et
	 */
	public void rollback(EntityTransaction et) {
		et.rollback();
	}

	/**
	 * Start a new transaction en return an EntityTransaction.
	 * 
	 * @return
	 */
	public EntityTransaction startTransaction() {
		return this.em.getTransaction();
	}

}
