package com.common.util.business.service;

import java.io.Serializable;
import java.util.List;

import com.common.util.domain.exception.ServiceException;
import com.common.util.domain.exception.ValidationException;
import com.common.util.domain.model.entity.Persistence;
import com.common.util.domain.model.entity.impl.Entity;
import com.common.util.persistence.dao.BaseDao;
import com.common.util.persistence.filter.BaseFilter;
import com.common.util.persistence.filter.Order;

/**
 * The interface that define all the commons behavior of the Services.
 * 
 * @see BaseFilter
 * @see Entity
 * @see Orders
 * @see Persistence
 * @see Serializable
 * 
 * @see BaseDao
 * 
 * @since 05/02/2014
 * @author Guillermo S. Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            The entity of this filter.
 * @param <PK>
 *            The primary key of the entity of this Service.
 */
public interface BaseService<E extends Persistence<PK>, PK extends Serializable> extends Serializable {

	/**
	 * Allow validated an entity.
	 * 
	 * @param entity
	 *            The entity who will be validated.
	 * @throws ValidationException
	 *             When something is invalid in the entity.
	 */
	public void validate(E entity) throws ValidationException;

	/**
	 * Allow count all the rows in the table of the entity.
	 * 
	 * @see #count(BaseFilter)
	 * 
	 * @return The amount of rows of entities.
	 * @throws ServiceException
	 *             When something go wrong.
	 */
	public Long count() throws ServiceException;

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
	 * @throws ServiceException
	 *             When something go wrong.
	 */
	public Long count(BaseFilter<E, PK> filter) throws ServiceException;

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
	 * @throws ServiceException
	 *             When something go wrong.
	 */
	public E getById(PK id) throws ServiceException;

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
	 * @return The entity related with the identifier, if don't retrieve any entity this method throw an {@link ServiceException}.
	 * @throws ServiceException
	 *             When something go wrong.
	 */
	public E findById(PK id) throws ServiceException;

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
	 * @throws ServiceException
	 *             When something go wrong.
	 */
	public List<E> getAll(Order... orders) throws ServiceException;

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
	 * @throws ServiceException
	 *             When something go wrong.
	 */
	public List<E> filter(BaseFilter<E, PK> filter) throws ServiceException;

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
	 * @throws ValidationException
	 *             When something is invalid in the entity.
	 * @throws ServiceException
	 *             When something go wrong.
	 */
	public PK save(E entity) throws ValidationException, ServiceException;

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
	 * @throws ValidationException
	 *             When something is invalid in the entity.
	 * @throws ServiceException
	 *             When something go wrong.
	 */
	public void update(E entity) throws ValidationException, ServiceException;

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
	 * @throws ValidationException
	 *             When something is invalid in the entity.
	 * @throws ServiceException
	 *             When something go wrong.
	 */
	public void saveOrUpdate(E entity) throws ValidationException, ServiceException;

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
	 * @throws ServiceException
	 *             When something go wrong.
	 */
	public void delete(E entity) throws ServiceException;

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
	 * @throws ServiceException
	 *             When something go wrong.
	 */
	public void deleteById(PK id) throws ServiceException;
}