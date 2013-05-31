package com.common.util.model.filter;

import java.io.Serializable;
import java.util.List;

/**
 * La clase que permite crear un filtro para la verificar la inclusión de un atributo dentro de un listado.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <F>
 *            La clase que va a representar los valores que va representar el contenido del listado de valores a comparar.
 */
public class InFilter<F extends Serializable> extends AtomicFilter<F> {

	/**
	 * El listado de valores con el que va a compararse el atributo.
	 */
	private List<? extends F> list;

	/**
	 * El constructor de un filtro de inclusión.
	 * 
	 * @param attribute
	 *            El atributo que va a compararse con el listado.
	 * @param list
	 *            El listado de valores que va a compararse.
	 */
	protected InFilter(String attribute, List<? extends F> list) {
		super(attribute);
		this.list = list;
	}

	/**
	 * La función que permite retornar el listado de los valores que va a compararse.
	 * 
	 * @return El listado de valores a comparar.
	 */
	public List<? extends F> getList() {
		return this.list;
	}

	@Override
	public String toString() {
		String output = "";
		output += this.getAttribute();
		output += " IN (";
		int index = 0;
		for (Serializable s : this.list) {
			output += s;
			if (index < this.list.size() - 1) {
				output += ", ";
			}
			index++;
		}
		output += ")";
		return output;
	}

	@Override
	public AtomicFilterType getAtomicFilterType() {
		return AtomicFilterType.IN;
	}
}
