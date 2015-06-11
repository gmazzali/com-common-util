package com.common.util.domain.model.entity;

import java.io.Serializable;
import java.util.Date;

import com.common.util.business.tool.date.DateUtil;

/**
 * La clase que representa una entidad auditable en la base de datos.
 * 
 * @see AuditablePersistence
 * @see ActiveEntity
 * 
 * @since 02/07/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <PK>
 *            La clase que va a corresponder a la clave primaria de la entidad que vamos a representar.
 */
public abstract class AuditableEntity<PK extends Serializable> extends ActiveEntity<PK> implements AuditablePersistence<PK> {
	private static final long serialVersionUID = 1L;

	/**
	 * La interfaz que contiene el nombre de los atributos de esta entidad.
	 */
	public interface Attributes extends ActiveEntity.Attributes {
		static final String CREATE_DATE = "createDate";
		static final String MODIFY_DATE = "modifyDate";
		static final String DELETE_DATE = "deleteDate";
	}

	/**
	 * La fecha de alta de la entidad.
	 */
	protected Date createDate;
	/**
	 * La fecha de modificación de la entidad.
	 */
	protected Date modifyDate;
	/**
	 * La fecha de baja de la entidad.
	 */
	protected Date deleteDate;

	/**
	 * El contructor por omisión.
	 */
	public AuditableEntity() {
		this.createDate = new Date();
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(super.toString()).append(" [C: ").append(DateUtil.formatDate(this.createDate)).append(" - M: ")
				.append(DateUtil.formatDate(this.modifyDate)).append(" - D: ").append(DateUtil.formatDate(this.deleteDate)).append("]");
		return buffer.toString();
	}

	@Override
	public Boolean isNew() {
		return this.id == null;
	}

	@Override
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Override
	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}
}