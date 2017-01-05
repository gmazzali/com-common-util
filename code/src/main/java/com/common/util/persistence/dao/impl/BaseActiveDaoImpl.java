package com.common.util.persistence.dao.impl;

import java.io.Serializable;
import java.util.List;

import com.common.util.domain.model.entity.ActivePersistence;
import com.common.util.domain.model.entity.impl.ActiveEntity;
import com.common.util.persistence.dao.BaseActiveDao;
import com.common.util.persistence.filter.ActiveBaseFilter;
import com.common.util.persistence.filter.BaseFilter;
import com.common.util.persistence.filter.Order;

/**
 * The extension of {@link BaseDaoImpl} that managed active entities {@link ActivePersistence}.
 * 
 * @see BaseDaoImpl
 * @see ActivePersistence
 * @see ActiveEntity
 * 
 * @since 25/11/2016
 * @author Guillermo S. Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            The active entity of this DAO.
 * @param <PK>
 *            The primary key of the active entity of this DAO.
 */
public abstract class BaseActiveDaoImpl<E extends ActivePersistence<PK>, PK extends Serializable> extends BaseDaoImpl<E, PK> implements
		BaseActiveDao<E, PK> {
	
	private static final long serialVersionUID = 1L;

	@Override
	public Long count() {
		ActiveBaseFilter<E, PK> filter = new ActiveBaseFilter<E, PK>();
		filter.setActive(Boolean.TRUE);
		return super.count(filter);
	}

	@Override
	public Long count(BaseFilter<E, PK> filter) {
		return super.count(filter);
	}

	@Override
	public List<E> getAll(Order... orders) {
		ActiveBaseFilter<E, PK> filter = new ActiveBaseFilter<E, PK>();
		filter.setActive(Boolean.TRUE);
		filter.addOrder(orders);
		return super.filter(filter);
	}

	@Override
	public E getById(PK id) {
		return super.getById(id);
	}

	@Override
	public List<E> filter(BaseFilter<E, PK> filter) {
		return super.filter(filter);
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