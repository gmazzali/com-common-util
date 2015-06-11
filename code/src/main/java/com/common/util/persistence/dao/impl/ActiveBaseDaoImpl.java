package com.common.util.persistence.dao.impl;

import java.io.Serializable;
import java.util.List;

import com.common.util.domain.exception.PersistenceException;
import com.common.util.domain.model.entity.ActiveEntity;
import com.common.util.domain.model.entity.ActivePersistence;
import com.common.util.persistence.filter.ActiveBaseFilter;
import com.common.util.persistence.filter.BaseFilter;
import com.common.util.persistence.filter.order.Orders;

/**
 * La clase que extienda {@link BaseDaoImpl} pero permite manejar las entidades activables {@link ActiveEntity} que tenemos dentro del sistema.
 * 
 * @see ActivePersistence
 * @see BaseDaoImpl
 * @see ActiveEntity
 * 
 * @since 02/07/2014
 * @author Guillermo Mazzali
 * @version 1.1
 * 
 * @param <E>
 *            La clase que corresponde a la entidad activable que vamos a persistir dentro de la base de datos.
 * @param <PK>
 *            La clase que corresponde al identificador de la entidad {@link E}.
 */
public abstract class ActiveBaseDaoImpl<E extends ActivePersistence<PK>, PK extends Serializable> extends BaseDaoImpl<E, PK> {
	private static final long serialVersionUID = 1L;

	@Override
	public Long count() {
		ActiveBaseFilter<E, PK> filter = new ActiveBaseFilter<E, PK>();
		filter.setActive(Boolean.TRUE);
		return super.countByFilter(filter);
	}

	@Override
	public Long countByFilter(BaseFilter<E, PK> filter) {
		return super.countByFilter((ActiveBaseFilter<E, PK>) filter);
	}
	
	@Override
	public List<E> getAll() throws PersistenceException {
		ActiveBaseFilter<E, PK> filter = new ActiveBaseFilter<E, PK>();
		filter.setActive(Boolean.TRUE);
		return super.getByFilter(filter);
	}

	@Override
	public List<E> getAll(Orders orders) {
		ActiveBaseFilter<E, PK> filter = new ActiveBaseFilter<E, PK>();
		filter.setActive(Boolean.TRUE);
		filter.setOrders(orders);
		return super.getByFilter(filter);
	}

	@Override
	public E getById(PK id) {
		return super.getById(id);
	}

	@Override
	public List<E> getByFilter(BaseFilter<E, PK> filter) {
		return super.getByFilter((ActiveBaseFilter<E, PK>) filter);
	}

	@Override
	public PK save(E entity) {
		entity.setActive(Boolean.TRUE);
		return super.save(entity);
	}

	@Override
	public void saveOrUpdate(E entity) {
		entity.setActive(Boolean.TRUE);
		super.saveOrUpdate(entity);
	}

	@Override
	public void update(E entity) {
		entity.setActive(Boolean.TRUE);
		super.saveOrUpdate(entity);
	}

	@Override
	public void delete(E entity) {
		entity.setActive(Boolean.FALSE);
		super.saveOrUpdate(entity);
	}

	@Override
	public void deleteById(PK id) {
		E entity = this.getById(id);
		if (entity != null) {
			this.delete(entity);
		}
	}
}