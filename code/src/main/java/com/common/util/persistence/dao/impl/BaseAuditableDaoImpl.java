package com.common.util.persistence.dao.impl;

import java.io.Serializable;
import java.util.List;

import com.common.util.business.service.DateService;
import com.common.util.domain.model.entity.AuditablePersistence;
import com.common.util.domain.model.entity.impl.AuditableEntity;
import com.common.util.persistence.dao.BaseAuditableDao;
import com.common.util.persistence.filter.BaseFilter;
import com.common.util.persistence.filter.Order;

/**
 * The extension of {@link BaseActiveDaoImpl} that managed active entities {@link AuditablePersistence}.
 * 
 * @see BaseActiveDaoImpl
 * @see AuditablePersistence
 * @see AuditableEntity
 * 
 * @since 25/11/2016
 * @author Guillermo S. Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            The auditable entity of this DAO.
 * @param <PK>
 *            The primary key of the auditable entity of this DAO.
 */
public abstract class BaseAuditableDaoImpl<E extends AuditablePersistence<PK>, PK extends Serializable> extends BaseActiveDaoImpl<E, PK> implements
		BaseAuditableDao<E, PK> {

	private static final long serialVersionUID = 1L;

	/**
	 * The date service.
	 */
	protected DateService dateService;

	@Override
	public Long count() {
		return super.count();
	}

	@Override
	public Long count(BaseFilter<E, PK> filter) {
		return super.count(filter);
	}

	@Override
	public List<E> getAll(Order... orders) {
		return super.getAll(orders);
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
		this.saveOrUpdateAudit(entity);
		return super.save(entity);
	}

	@Override
	public void saveOrUpdate(E entity) {
		this.saveOrUpdateAudit(entity);
		super.saveOrUpdate(entity);
	}

	@Override
	public void update(E entity) {
		this.saveOrUpdateAudit(entity);
		super.update(entity);
	}

	@Override
	public void delete(E entity) {
		this.deleteAudit(entity);
		super.delete(entity);
	}

	@Override
	public void deleteById(PK id) {
		E entity = this.getById(id);
		if (entity != null) {
			this.delete(entity);
		}
	}

	/**
	 * Allow audit an entity when it edited.
	 * 
	 * @param entity
	 *            The entity that is edited.
	 */
	protected void saveOrUpdateAudit(E entity) {
		if (entity.isNew()) {
			entity.setCreateDate(this.dateService.getCurrentDate());
		} else {
			entity.setModifyDate(this.dateService.getCurrentDate());
		}
		entity.setDeleteDate(null);
	}

	/**
	 * Allow audit an entity when it deleted.
	 * 
	 * @param entity
	 *            The entity that is deleted.
	 */
	protected void deleteAudit(E entity) {
		entity.setDeleteDate(this.dateService.getCurrentDate());
	}

	/**
	 * Set the date service.
	 * 
	 * @param dateService
	 *            The date service.
	 */
	public void setDateService(DateService dateService) {
		this.dateService = dateService;
	}
}