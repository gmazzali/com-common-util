package com.common.util.domain.model.entity;

import java.io.Serializable;

/**
 * La clase que representa una entidad que va a almacenarse en la base de datos y que tiene un objeto que lo identifica.
 * 
 * @see Persistence
 * 
 * @since 05/02/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <PK>
 *            La clase que va a corresponder a la clave primaria de la entidad que vamos a representar.
 */
public abstract class Entity<PK extends Serializable> implements Persistence<PK> {
	private static final long serialVersionUID = 1L;

	/**
	 * La interfaz que contiene el nombre de los atributos de esta entidad.
	 */
	public interface Attributes {
		static final String ID = "id";
	}

	/**
	 * El identificador de la entidad.
	 */
	protected PK id;

	@Override
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(" ID: ").append(this.id);
		return stringBuffer.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (this.id == null ? 0 : this.id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		Entity<?> other = (Entity<?>) obj;
		if (this.id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public void setId(PK id) {
		this.id = id;
	}
}