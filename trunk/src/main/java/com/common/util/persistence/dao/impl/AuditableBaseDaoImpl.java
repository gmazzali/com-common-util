package com.common.util.persistence.dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.common.util.domain.model.AuditableEntity;
import com.common.util.domain.model.AuditablePersistence;
import com.common.util.persistence.filter.AuditableBaseFilter;
import com.common.util.persistence.filter.BaseFilter;
import com.common.util.persistence.filter.order.Orders;

/**
 * La clase que extienda {@link BaseDaoImpl} pero permite manejar las entidades auditable {@link AuditableEntity} que tenemos dentro del sistema.
 * 
 * @see BaseDaoImpl
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
public class AuditableBaseDaoImpl<E extends AuditablePersistence<PK>, PK extends Serializable> extends BaseDaoImpl<E, PK> {

	private static final long serialVersionUID = 1L;

	@Override
	public Long count() {
		AuditableBaseFilter<E, PK> filter = new AuditableBaseFilter<E, PK>();
		filter.setActive(Boolean.TRUE);
		return super.countByFilter(filter);
	}

	@Override
	public Long countByFilter(BaseFilter<E, PK> filter) {
		return super.countByFilter((AuditableBaseFilter<E, PK>) filter);
	}

	@Override
	public List<E> findAll(Orders orders) {
		AuditableBaseFilter<E, PK> filter = new AuditableBaseFilter<E, PK>();
		filter.setActive(Boolean.TRUE);
		filter.setOrders(orders);
		return super.findByFilter(filter);
	}

	@Override
	public E findById(PK id) {
		return super.findById(id);
	}

	@Override
	public List<E> findByFilter(BaseFilter<E, PK> filter) {
		return super.findByFilter((AuditableBaseFilter<E, PK>) filter);
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
		super.saveOrUpdate(entity);
	}

	@Override
	public void delete(E entity) {
		this.deleteAudit(entity);
		super.saveOrUpdate(entity);
	}

	@Override
	public void deleteById(PK id) {
		E entity = this.findById(id);
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
			entity.setCreateDate(new Date());
		} else {
			entity.setModifyDate(new Date());
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
		entity.setDeleteDate(new Date());
	}
}