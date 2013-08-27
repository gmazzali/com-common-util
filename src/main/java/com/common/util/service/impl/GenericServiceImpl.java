package com.common.util.service.impl;

import java.io.Serializable;
import java.util.List;

import com.common.util.dao.GenericDao;
import com.common.util.exception.CheckedException;
import com.common.util.model.Persistence;
import com.common.util.model.filter.Filter;
import com.common.util.service.GenericService;

/**
 * La clase que permite establecer un servicio para un elemento genérico junto al DAO correspondiente a este y que implementa la interfaz
 * {@link GenericService}.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @see GenericService
 * 
 * @param <E>
 *            La clase del modelo que vamos a persistir dentro de la base de datos.
 * @param <PK>
 *            La clase que va a representar la clave primaria de la clase <E>.
 */
public abstract class GenericServiceImpl<E extends Persistence<PK>, PK extends Serializable> implements GenericService<E, PK> {

	/**
	 * El DAO que permite manejar los elementos dentro de la base de datos.
	 */
	protected GenericDao<E, PK> dao;

	@Override
	public void setDao(GenericDao<E, PK> dao) {
		this.dao = dao;
	}

	@Override
	public Integer count() throws CheckedException {
		return this.dao.count();
	}

	@Override
	public Integer countByFilter(Filter filter) throws CheckedException {
		return this.dao.countByFilter(filter);
	}

	@Override
	public List<E> findAll() throws CheckedException {
		return this.dao.findAll();
	}

	@Override
	public E findById(PK id) throws CheckedException {
		return this.dao.findById(id);
	}

	@Override
	public List<E> findByFilter(Filter filter) throws CheckedException {
		return this.dao.findByFilter(filter);
	}

	@Override
	public void save(E entity) throws CheckedException {
		this.validate(entity);
		this.dao.save(entity);
	}

	@Override
	public void update(E entity) throws CheckedException {
		this.validate(entity);
		this.dao.update(entity);
	}

	@Override
	public void saveOrUpdate(E entity) throws CheckedException {
		this.validate(entity);
		this.dao.saveOrUpdate(entity);
	}

	@Override
	public void delete(E entity) throws CheckedException {
		this.dao.delete(entity);
	}

	@Override
	public void deleteById(PK id) throws CheckedException {
		this.dao.deleteById(id);
	}
}
