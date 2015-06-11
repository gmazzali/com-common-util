package com.common.util.persistence.filter;

import java.io.Serializable;

import com.common.util.domain.model.entity.ActivePersistence;

/**
 * Representa un filtro que extiende {@link BaseFilter} y que podemos utilizar para la busqueda de las entidades activables.
 * 
 * @since 02/07/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            Las entidades activables que vamos a manipular con este filtro.
 * @param <PK>
 *            La clave que corresponde con el ID de las entidades auditables de filtrado.
 */
public class ActiveBaseFilter<E extends ActivePersistence<PK>, PK extends Serializable> extends BaseFilter<E, PK> {
	private static final long serialVersionUID = 1L;

	/**
	 * El valor booleano que nos permite definir los tipos de entidades vamos a buscar o si buscamos todos.
	 */
	protected Boolean active = Boolean.TRUE;

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
}