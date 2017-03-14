package com.common.util.business.service.impl;

import java.io.Serializable;
import java.util.List;

import com.common.util.business.service.BaseService;
import com.common.util.business.util.ApplicationLogger;
import com.common.util.domain.exception.BusinessException;
import com.common.util.domain.exception.ValidationException;
import com.common.util.domain.model.entity.Persistence;
import com.common.util.persistence.dao.BaseDao;
import com.common.util.persistence.filter.BaseFilter;
import com.common.util.persistence.filter.Order;

/**
 * The implementation of the interface that define all the commons behavior of the Services.
 * 
 * @see BaseFilter
 * @see Orders
 * @see BaseService
 * 
 * @since 05/02/2014
 * @author Guillermo S. Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            The entity of this Services.
 * @param <PK>
 *            The primary key of the entity of this Service.
 */
public abstract class BaseServiceImpl<E extends Persistence<PK>, PK extends Serializable> implements BaseService<E, PK> {

	private static final long serialVersionUID = 1L;

	/**
	 * The DAO of this service.
	 */
	protected BaseDao<E, PK> dao;

	public void setDao(BaseDao<E, PK> dao) {
		this.dao = dao;
	}

	@Override
	public Long count() throws BusinessException {
		try {
			return this.dao.count();
		} catch (Exception e) {
			ApplicationLogger.error(this, "count failed", e);
			throw new BusinessException(e);
		}
	}

	@Override
	public Long count(BaseFilter<E, PK> filter) throws BusinessException {
		try {
			return this.dao.count(filter);
		} catch (Exception e) {
			ApplicationLogger.error(this, "count by filter failed", e);
			throw new BusinessException(e);
		}
	}

	@Override
	public E getById(PK id) throws BusinessException {
		try {
			return this.dao.getById(id);
		} catch (Exception e) {
			ApplicationLogger.error(this, "get by id failed", e);
			throw new BusinessException(e);
		}
	}

	@Override
	public E findById(PK id) throws BusinessException {
		try {
			return this.dao.findById(id);
		} catch (Exception e) {
			ApplicationLogger.error(this, "find by id failed", e);
			throw new BusinessException(e);
		}
	}

	@Override
	public List<E> getAll(Order... orders) throws BusinessException {
		try {
			return this.dao.getAll(orders);
		} catch (Exception e) {
			ApplicationLogger.error(this, "get all failed", e);
			throw new BusinessException(e);
		}
	}

	@Override
	public List<E> filter(BaseFilter<E, PK> filter) throws BusinessException {
		try {
			return this.dao.filter(filter);
		} catch (Exception e) {
			ApplicationLogger.error(this, "find by filter failed", e);
			throw new BusinessException(e);
		}
	}

	@Override
	public PK save(E entity) throws ValidationException, BusinessException {
		try {
			this.validate(entity);
			return this.dao.save(entity);
		} catch (Exception e) {
			ApplicationLogger.error(this, "save failed", e);
			throw new BusinessException(e);
		}
	}

	@Override
	public void update(E entity) throws ValidationException, BusinessException {
		try {
			this.validate(entity);
			this.dao.update(entity);
		} catch (Exception e) {
			ApplicationLogger.error(this, "update failed", e);
			throw new BusinessException(e);
		}
	}

	@Override
	public void saveOrUpdate(E entity) throws ValidationException, BusinessException {
		try {
			this.validate(entity);
			this.dao.saveOrUpdate(entity);
		} catch (Exception e) {
			ApplicationLogger.error(this, "save or update failed", e);
			throw new BusinessException(e);
		}
	}

	@Override
	public void delete(E entity) throws BusinessException {
		try {
			this.dao.delete(entity);
		} catch (Exception e) {
			ApplicationLogger.error(this, "delete failed", e);
			throw new BusinessException(e);
		}
	}

	@Override
	public void deleteById(PK id) throws BusinessException {
		try {
			this.dao.deleteById(id);
		} catch (Exception e) {
			ApplicationLogger.error(this, "delete by id failed", e);
			throw new BusinessException(e);
		}
	}
}