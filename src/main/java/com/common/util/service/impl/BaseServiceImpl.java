package com.common.util.service.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;

import com.common.util.dao.BaseDao;
import com.common.util.exception.CheckedException;
import com.common.util.model.Persistence;
import com.common.util.model.filter.BaseFilter;
import com.common.util.model.filter.order.OrderBy;
import com.common.util.service.BaseService;

/**
 * La clase que permite establecer un servicio para un elemento genérico junto al DAO correspondiente a este y que implementa la interfaz
 * {@link BaseService}.
 * 
 * @see BaseFilter
 * @see OrderBy
 * @see BaseService
 * 
 * @since 05/02/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase que corresponde a la entidad que vamos a manipular dentro de este servicio.
 * @param <PK>
 *            La clase que corresponde al identificador de la entidad {@link E}.
 */
public abstract class BaseServiceImpl<E extends Persistence<PK>, PK extends Serializable> implements BaseService<E, PK> {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(BaseServiceImpl.class);

	/**
	 * El DAO que permite manejar los elementos dentro de la base de datos.
	 */
	protected BaseDao<E, PK> dao;

	@Override
	public void setDao(BaseDao<E, PK> dao) {
		this.dao = dao;
	}

	@Override
	public Long count() throws CheckedException {
		try {
			return this.dao.count();
		} catch (Exception e) {
			log.error("count failed", e);
			throw new CheckedException(e);
		}
	}

	@Override
	public Long countByFilter(BaseFilter<PK> filter) throws CheckedException {
		try {
			return this.dao.countByFilter(filter);
		} catch (Exception e) {
			log.error("count by filter failed", e);
			throw new CheckedException(e);
		}
	}

	@Override
	public List<E> findAll(OrderBy orders) throws CheckedException {
		try {
			return this.dao.findAll(orders);
		} catch (Exception e) {
			log.error("find all failed", e);
			throw new CheckedException(e);
		}
	}

	@Override
	public List<E> findByFilter(BaseFilter<PK> filter, OrderBy orders) throws CheckedException {
		try {
			return this.dao.findByFilter(filter, orders);
		} catch (Exception e) {
			log.error("find by filter failed", e);
			throw new CheckedException(e);
		}
	}

	@Override
	public E findById(PK id) throws CheckedException {
		try {
			return this.dao.findById(id);
		} catch (Exception e) {
			log.error("find by id failed", e);
			throw new CheckedException(e);
		}
	}

	@Override
	public void save(E entity) throws CheckedException {
		try {
			this.validate(entity);
			this.dao.save(entity);
		} catch (Exception e) {
			log.error("save failed", e);
			throw new CheckedException(e);
		}
	}

	@Override
	public void saveOrUpdate(E entity) throws CheckedException {
		try {
			this.validate(entity);
			this.dao.saveOrUpdate(entity);
		} catch (Exception e) {
			log.error("save or update failed", e);
			throw new CheckedException(e);
		}
	}

	@Override
	public void update(E entity) throws CheckedException {
		try {
			this.validate(entity);
			this.dao.update(entity);
		} catch (Exception e) {
			log.error("update failed", e);
			throw new CheckedException(e);
		}
	}

	@Override
	public void delete(E entity) throws CheckedException {
		try {
			this.dao.delete(entity);
		} catch (Exception e) {
			log.error("delete failed", e);
			throw new CheckedException(e);
		}
	}

	@Override
	public void deleteById(PK id) throws CheckedException {
		try {
			this.dao.deleteById(id);
		} catch (Exception e) {
			log.error("delete by id failed", e);
			throw new CheckedException(e);
		}
	}
}