package com.common.util.domain.model.entity;

import java.io.Serializable;

/**
 * La clase que representa una entidad activable dentro de la base de datos para el borrado lógico de la misma.
 * 
 * @see ActivePersistence
 * @see Entity
 * 
 * @since 11/112014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <PK>
 *            La clase que va a corresponder a la clave primaria de la entidad que vamos a representar.
 */
public abstract class ActiveEntity<PK extends Serializable> extends Entity<PK> implements ActivePersistence<PK> {
	private static final long serialVersionUID = 1L;

	/**
	 * La interfaz que contiene el nombre de los atributos de esta entidad.
	 */
	public interface Attributes extends Entity.Attributes {
		static final String ACTIVE = "active";
	}

	/**
	 * El valor que indica si esta activa la entidad.
	 */
	protected Boolean active = Boolean.TRUE;

	@Override
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(super.toString()).append(" ACTIVE: ").append(this.active);
		return stringBuffer.toString();
	}

	@Override
	public Boolean getActive() {
		return this.active;
	}

	@Override
	public void setActive(Boolean active) {
		this.active = active;
	}
}