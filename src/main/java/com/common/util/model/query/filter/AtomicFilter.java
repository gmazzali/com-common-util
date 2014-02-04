package com.common.util.model.query.filter;

import java.io.Serializable;

/**
 * La clase que detalla un filtro atomico para comparar atributos con un conjunto de valores que van a compararse.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <F>
 *            La clase que va a utilizarse como valor de comparación entre los atributos.
 */
public abstract class AtomicFilter<F extends Serializable> extends Filter {
	private static final long serialVersionUID = 1L;

	/**
	 * La enumeración que detalla los posibles tipos de filtros atómicos que pueden generarse.
	 * 
	 * <ul>
	 * <li>{@link AtomicFilterType#COMPARE}</li>
	 * <li>{@link AtomicFilterType#IN}</li>
	 * <li>{@link AtomicFilterType#BETWEEN}</li>
	 * <li>{@link AtomicFilterType#LIKE}</li>
	 * <li>{@link AtomicFilterType#NULL}</li>
	 * </ul>
	 * 
	 * @author Guillermo Mazzali
	 * @version 1.0
	 */
	public enum AtomicFilterType {
		COMPARE, IN, BETWEEN, LIKE, NULL;
	}

	/**
	 * El nombre del atributo que vamos a comparar dentro del filtro.
	 */
	private String attribute;

	/**
	 * El constructor de un filtro atómico.
	 * 
	 * @param attribute
	 *            El atributo que vamos a filtrar.
	 */
	public AtomicFilter(String attribute) {
		this.attribute = attribute;
	}

	/**
	 * La funcion que permite retornar el nombre del atributo que corresponde al filtro.
	 * 
	 * @return El nombre del atributo del filtro.
	 */
	public String getAttribute() {
		return this.attribute;
	}

	/**
	 * El tipo de filtro que corresponde a la isntancia que vamos a crear.
	 * 
	 * @return El tipo de filtro que vamos a manejar.
	 */
	public abstract AtomicFilterType getAtomicFilterType();
}