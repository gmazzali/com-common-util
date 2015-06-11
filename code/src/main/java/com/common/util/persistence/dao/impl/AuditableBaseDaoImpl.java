package com.common.util.persistence.dao.impl;

import java.io.Serializable;
import java.util.List;

import com.common.util.business.service.DateService;
import com.common.util.domain.model.entity.AuditableEntity;
import com.common.util.domain.model.entity.AuditablePersistence;
import com.common.util.persistence.filter.BaseFilter;
import com.common.util.persistence.filter.order.Orders;

/**
 * La clase que extienda {@link ActiveBaseDaoImpl} pero permite manejar las entidades auditable {@link AuditableEntity} que tenemos dentro del
 * sistema.
 * 
 * @see ActiveBaseDaoImpl
 * @see AuditablePersistence
 * @see AuditableEntity
 * 
 * @since 02/07/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase que corresponde a la entidad auditable que vamos a persistir dentro de la base de datos.
 * @param <PK>
 *            La clase que corresponde al identificador de la entidad {@link E}.
 */
public abstract class AuditableBaseDaoImpl<E extends AuditablePersistence<PK>, PK extends Serializable> extends ActiveBaseDaoImpl<E, PK> {
	private static final long serialVersionUID = 1L;

	/**
	 * El servicio de las fechas.
	 */
	protected DateService dateService;

	/**
	 * Permite cargar el servicio de las fechas.
	 * 
	 * @param dateService
	 *            El servicio de las fechas.
	 */
	public void setDateService(DateService dateService) {
		this.dateService = dateService;
	}

	@Override
	public Long count() {
		return super.count();
	}

	@Override
	public Long countByFilter(BaseFilter<E, PK> filter) {
		return super.countByFilter(filter);
	}

	@Override
	public List<E> getAll() {
		return super.getAll();
	}

	@Override
	public List<E> getAll(Orders orders) {
		return super.getAll(orders);
	}

	@Override
	public E getById(PK id) {
		return super.getById(id);
	}

	@Override
	public List<E> getByFilter(BaseFilter<E, PK> filter) {
		return super.getByFilter(filter);
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
	 * Permite auditar una entidad al momento de guardar o actualizar la misma.
	 * 
	 * @param entity
	 *            La entidad que vamos a auditar.
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
	 * Permite auditar una entidad al momento de eliminar la misma.
	 * 
	 * @param entity
	 *            La entidad que vamos a auditar.
	 */
	protected void deleteAudit(E entity) {
		entity.setDeleteDate(this.dateService.getCurrentDate());
	}
}