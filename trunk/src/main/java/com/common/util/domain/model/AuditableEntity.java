package com.common.util.domain.model;

import java.io.Serializable;
import java.util.Date;

import com.common.util.business.tool.FormatUtil;

/**
 * La clase que representa una entidad auditable que va a almacenarse en la base de datos y que tiene un objeto que lo identifica. Esta entidad solo
 * permite borrado lógico de la entidad dentro de la base de datos.
 * 
 * @see Persistence
 * @see Entity
 * 
 * @since 02/07/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <PK>
 *            La clase que va a corresponder a la clave primaria de la entidad que vamos a representar.
 */
public abstract class AuditableEntity<PK extends Serializable> extends Entity<PK> implements AuditablePersistence<PK> {

	private static final long serialVersionUID = 1L;

	/**
	 * @see Entity.Attributes
	 */
	public interface Attributes extends Entity.Attributes {
		static final String CREATE_DATE = "createDate";
		static final String MODIFY_DATE = "modifyDate";
		static final String DELETE_DATE = "deleteDate";
		static final String ACTIVE = "active";
	}

	/**
	 * La fecha de alta de la entidad.
	 */
	private Date createDate;
	/**
	 * La fecha de modificación de la entidad.
	 */
	private Date modifyDate;
	/**
	 * La fecha de baja de la entidad.
	 */
	private Date deleteDate;
	/**
	 * El valor booleano que indica si la entidad esta activa o no.
	 */
	private Boolean active;

	/**
	 * El contructor por omisión.
	 */
	public AuditableEntity() {
		super();
		this.createDate = new Date();
		this.active = true;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[C: ").append(FormatUtil.formatDate(this.createDate)).append(" - M: ").append(FormatUtil.formatDate(this.modifyDate))
				.append(" - D: ").append(FormatUtil.formatDate(this.deleteDate)).append("]").append(" ACTIVE: ").append(this.active);
		return buffer.toString();
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

	@Override
	public void setActive(Boolean active) {
		this.active = active;
	}
}