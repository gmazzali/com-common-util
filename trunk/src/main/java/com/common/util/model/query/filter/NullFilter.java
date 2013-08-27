package com.common.util.model.query.filter;

import java.io.Serializable;

/**
 * La clase que define un filtro que permite verificar que un atributo de un elemento sea NULL o no.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class NullFilter extends AtomicFilter<Serializable> {

	/**
	 * El constructor de un filtro NULL.
	 * 
	 * @param attribute
	 *            El atributo que va a compararse el valor.
	 */
	protected NullFilter(String attribute) {
		super(attribute);
	}

	@Override
	public String toString() {
		String output = "";
		output += this.getAttribute();
		output += " IS NULL";
		return output;
	}

	@Override
	public AtomicFilterType getAtomicFilterType() {
		return AtomicFilterType.NULL;
	}
}