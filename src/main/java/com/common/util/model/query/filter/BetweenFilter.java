package com.common.util.model.query.filter;

import java.io.Serializable;

/**
 * La clase que representa un filtro que verifica que un atributo este dentro de un rango de valores.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <F>
 *            La clase que representa los valores que van a almacenarse dentro del rango.
 */
public class BetweenFilter<F extends Serializable> extends AtomicFilter<F> {

	/**
	 * El valor bajo del rango.
	 */
	private F lowValue;
	/**
	 * El valor alto del rango.
	 */
	private F highValue;

	/**
	 * El constructor de un filtro de verificación de un atributo dentro de un rango.
	 * 
	 * @param attribute
	 *            El atributo sobre el que realizamos el filtro.
	 * @param lowValue
	 *            El valor más bajo del rango de valores.
	 * @param highValue
	 *            El valor más alto del rango de valores.
	 */
	protected BetweenFilter(String attribute, F lowValue, F highValue) {
		super(attribute);
		this.lowValue = lowValue;
		this.highValue = highValue;
	}

	/**
	 * La función que permite retornar el valor bajo del rango de valores a verificar dentro del filtro.
	 * 
	 * @return El valor bajo del rango de busqueda.
	 */
	public F getLowValue() {
		return this.lowValue;
	}

	/**
	 * La función que permite retornar el valor alto del rango de valores a verificar dentro del filtro.
	 * 
	 * @return El valor alto del rango de busqueda.
	 */
	public F getHighValue() {
		return this.highValue;
	}

	@Override
	public String toString() {
		String output = "";
		output += this.getAttribute();
		output += " BETWEEN " + this.lowValue;
		output += " AND " + this.highValue;
		return output;
	}

	@Override
	public AtomicFilterType getAtomicFilterType() {
		return AtomicFilterType.BETWEEN;
	}
}
