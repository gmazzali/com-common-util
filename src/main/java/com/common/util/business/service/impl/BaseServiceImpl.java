package com.common.util.business.service.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;

import com.common.util.business.service.BaseService;
import com.common.util.domain.exception.ServiceException;
import com.common.util.domain.model.Persistence;
import com.common.util.persistence.dao.BaseDao;
import com.common.util.persistence.filter.BaseFilter;
import com.common.util.persistence.filter.order.Orders;

/**
 * La clase que permite establecer un servicio para un elemento gen�rico junto al DAO correspondiente a este y que implementa la interfaz
 * {@link BaseService}.
 * 
 * @see BaseFilter
 * @see Orders
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
	public Long count() {
		try {
			return this.dao.count();
		} catch (Exception e) {
			log.error("count failed", e);
			throw new ServiceException(e);
		}
	}

	@Override
	public Long countByFilter(BaseFilter<E, PK> filter) {
		try {
			return this.dao.countByFilter(filter);
		} catch (Exception e) {
			log.error("count by filter failed", e);
			throw new ServiceException(e);
		}
	}

	@Override
	public List<E> findAll() {
		try {
			return this.dao.findAll();
		} catch (Exception e) {
			log.error("find all failed", e);
			throw new ServiceException(e);
		}
	}

	@Override
	public List<E> findAll(Orders orders) {
		try {
			return this.dao.findAll(orders);
		} catch (Exception e) {
			log.error("find all failed", e);
			throw new ServiceException(e);
		}
	}

	@Override
	public List<E> findByFilter(BaseFilter<E, PK> filter) {
		try {
			return this.dao.findByFilter(filter);
		} catch (Exception e) {
			log.error("find by filter failed", e);
			throw new ServiceException(e);
		}
	}

	@Override
	public E findById(PK id) {
		try {
			return this.dao.findById(id);
		} catch (Exception e) {
			log.error("find by id failed", e);
			throw new ServiceException(e);
		}
	}

	@Override
	public void save(E entity) {
		try {
			this.validate(entity);
			this.dao.save(entity);
		} catch (Exception e) {
			log.error("save failed", e);
			throw new ServiceException(e);
		}
	}

	@Override
	public void saveOrUpdate(E entity) {
		try {
			this.validate(entity);
			this.dao.saveOrUpdate(entity);
		} catch (Exception e) {
			log.error("save or update failed", e);
			throw new ServiceException(e);
		}
	}

	@Override
	public void update(E entity) {
		try {
			this.validate(entity);
			this.dao.update(entity);
		} catch (Exception e) {
			log.error("update failed", e);
			throw new ServiceException(e);
		}
	}

	@Override
	public void delete(E entity) {
		try {
			this.dao.delete(entity);
		} catch (Exception e) {
			log.error("delete failed", e);
			throw new ServiceException(e);
		}
	}

	@Override
	public void deleteById(PK id) {
		try {
			this.dao.deleteById(id);
		} catch (Exception e) {
			log.error("delete by id failed", e);
			throw new ServiceException(e);
		}
	}
}