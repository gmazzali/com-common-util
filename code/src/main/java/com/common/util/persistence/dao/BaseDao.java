package com.common.util.persistence.dao;

import java.io.Serializable;
import java.util.List;

import com.common.util.domain.exception.PersistenceException;
import com.common.util.domain.model.entity.Persistence;
import com.common.util.domain.model.entity.impl.Entity;
import com.common.util.persistence.filter.BaseFilter;
import com.common.util.persistence.filter.Order;

/**
 * The interface that define all the commons behavior of the DAOs.
 * 
 * @see Entity
 * @see Persistence
 * @see Serializable
 * 
 * @since 05/02/2014
 * @author Guillermo S. Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            The entity of this DAO.
 * @param <PK>
 *            The primary key of the entity of this DAO.
 */
public abstract interface BaseDao<E extends Persistence<PK>, PK extends Serializable> extends Serializable {

	/**
	 * Allow count all the rows in the table of the entity.
	 * 
	 * @see #count(BaseFilter)
	 * 
	 * @return The amount of rows of entities.
	 * @throws PersistenceException
	 *             When something go wrong in the query.
	 */
	public Long count() throws PersistenceException;

	/**
	 * Allow count all the rows in the table of the entity with a filter.
	 * 
	 * @see BaseFilter
	 * 
	 * @see #count()
	 * 
	 * @param filter
	 *            The {@link BaseFilter} used to count the rows of the entities.
	 * @return The amount of rows of entities.
	 * @throws PersistenceException
	 *             When something go wrong in the query.
	 */
	public Long count(BaseFilter<E, PK> filter) throws PersistenceException;

	/**
	 * Allow retrieve an entity related with the identifier received.
	 * 
	 * @see #findById(Serializable)
	 * @see #getAll()
	 * @see #getAll(Orders)
	 * @see #filter(BaseFilter)
	 * 
	 * @param id
	 *            The identifier for the entity wanted.
	 * @return The entity related with the identifier, if don't retrieve any entity this method return <i>NULL</i>.
	 * @throws PersistenceException
	 *             When something go wrong in the query.
	 */
	public E getById(PK id) throws PersistenceException;

	/**
	 * Allow retrieve an entity related with the identifier received.
	 * 
	 * @see #getById(Serializable)
	 * @see #getAll()
	 * @see #getAll(Orders)
	 * @see #filter(BaseFilter)
	 * 
	 * @param id
	 *            The identifier of the entity wanted.
	 * @return The entity related with the identifier, if don't retrieve any entity this method throw an {@link PersistenceException}.
	 * @throws PersistenceException
	 *             When something go wrong in the query.
	 */
	public E findById(PK id) throws PersistenceException;

	/**
	 * Allow retrieve all the entities of the database ordered for the parameters received.
	 * 
	 * @see Order
	 * 
	 * @see #getAll()
	 * @see #findById(Serializable)
	 * @see #getById(Serializable)
	 * @see #filter(BaseFilter)
	 * 
	 * @param orders
	 *            The order in what we want to return the entities
	 * @return The list of all entities sorted for the order received.
	 * @throws PersistenceException
	 *             When something go wrong in the query.
	 */
	public List<E> getAll(Order... orders) throws PersistenceException;

	/**
	 * Allow retrieve the entities of the database filtered for the {@link BaseFilter} received.
	 * 
	 * @see BaseFilter
	 * @see Order
	 * 
	 * @see #getAll()
	 * @see #getAll(Order)
	 * @see #getById(Serializable)
	 * @see #findById(Serializable)
	 * 
	 * @param filter
	 *            The {@link BaseFilter} received for the query.
	 * @return The list of all entities filtered for the filter received.
	 * @throws PersistenceException
	 *             When something go wrong in the query.
	 */
	public List<E> filter(BaseFilter<E, PK> filter) throws PersistenceException;

	/**
	 * Save a new entity in the database.
	 * 
	 * @see Entity
	 * 
	 * @see #saveOrUpdate(Persistence)
	 * @see #update(Persistence)
	 * @see #delete(Persistence)
	 * @see #deleteById(Serializable)
	 * 
	 * @param entity
	 *            The entity of {@link E} that want to save in the database.
	 * @return The identifier {@link PK} of the entity recently saved.
	 * @throws PersistenceException
	 *             When something go wrong in the query.
	 */
	public PK save(E entity) throws PersistenceException;

	/**
	 * Update an entity in the database.
	 * 
	 * @see Entity
	 * 
	 * @see #save(Persistence)
	 * @see #saveOrUpdate(Persistence)
	 * @see #delete(Persistence)
	 * @see #deleteById(Serializable)
	 * 
	 * @param entity
	 *            The entity of {@link E} that want to update.
	 * @throws PersistenceException
	 *             When something go wrong in the query.
	 */
	public void update(E entity) throws PersistenceException;

	/**
	 * Save or Update an entity in the database.
	 * 
	 * @see Entity
	 * 
	 * @see #save(Persistence)
	 * @see #update(Persistence)
	 * @see #delete(Persistence)
	 * @see #deleteById(Serializable)
	 * 
	 * @param entity
	 *            The entity of {@link E} that want to save or update.
	 * @throws PersistenceException
	 *             When something go wrong in the query.
	 */
	public void saveOrUpdate(E entity) throws PersistenceException;

	/**
	 * Delete an entity in the database.
	 * 
	 * @see Entity
	 * 
	 * @see #save(Persistence)
	 * @see #update(Persistence)
	 * @see #saveOrUpdate(Persistence)
	 * @see #deleteById(Serializable)
	 * 
	 * @param entity
	 *            The entity of {@link E} that want to delete.
	 * @throws PersistenceException
	 *             When something go wrong in the query.
	 */
	public void delete(E entity) throws PersistenceException;

	/**
	 * Delete an entity in the database give its identifier.
	 * 
	 * @see #save(Persistence)
	 * @see #update(Persistence)
	 * @see #saveOrUpdate(Persistence)
	 * @see #delete(Persistence)
	 * 
	 * @param id
	 *            The identifier {@link PK} of the entity that want to delete.
	 * @throws PersistenceException
	 *             When something go wrong in the query.
	 */
	public void deleteById(PK id) throws PersistenceException;
}