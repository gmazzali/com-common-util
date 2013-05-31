package com.common.util.model.filter;

import java.io.Serializable;

/**
 * La clase que corresponde a un filtro que compara un atributo con un valor dado.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * @param <F>
 *            La clase que vamos a utilizar para representar el valor que vamos a comparar con el atributo.
 */
public class CompareFilter<F extends Serializable> extends AtomicFilter<F> {

	/**
	 * La enumeraci�n que detalla los tipos de comparaciones que podemos hacer dentro del filtro.
	 */
	public enum CompareFilterType {
		/**
		 * El comparador de igualdad.
		 */
		EQUALS("="),
		/**
		 * El comparador de no igualdad.
		 */
		NOT_EQUALS("<>"),
		/**
		 * El comparador de mayor.
		 */
		GREATER(">"),
		/**
		 * El comparador de mayor o igual.
		 */
		GREATER_OR_EQUALS(">="),
		/**
		 * El comparador de menor.
		 */
		LESSER("<"),
		/**
		 * El comparador de menor o igual.
		 */
		LESSER_OR_EQUALS("<=");

		/**
		 * El nombre del tipo de filtro.
		 */
		private String name;

		/**
		 * El contructor de un tipo de filtro de comparaci�n.
		 * 
		 * @param name
		 *            El nombre del tipo de filtro de comparaci�n.
		 */
		private CompareFilterType(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return this.name;
		}
	}

	/**
	 * El tipo de operaci�n de comparaci�n del filtro.
	 */
	private CompareFilterType operation;
	/**
	 * El valor con el que va a compararse el atributo.
	 */
	private F value;

	/**
	 * El constructor de un filtro de comparaci�n de un atributo y un valor.
	 * 
	 * @param attribute
	 *            El atributo con el que va a compararse el valor.
	 * @param operation
	 *            El tipo de operaci�n que vamos a realizar entre el atributo y el valor.
	 * @param value
	 *            El valor a compararse con el atributo.
	 */
	protected CompareFilter(String attribute, CompareFilterType operation, F value) {
		super(attribute);
		this.operation = operation;
		this.value = value;
	}

	/**
	 * La funci�n que retorna el tipo de coparaci�n que va a contener este filtro.
	 * 
	 * @return El tipo de comparaci�n del filtro.
	 */
	public CompareFilterType getCompareFilterType() {
		return this.operation;
	}

	/**
	 * La funci�n que permite retornar el valor con el que va a compararse el atributo.
	 * 
	 * @return El valor de comparaci�n con el atributo.
	 */
	public F getValue() {
		return this.value;
	}

	@Override
	public String toString() {
		String output = "";
		output += this.getAttribute();
		output += " " + this.operation;
		output += " " + this.value;
		return output;
	}

	@Override
	public AtomicFilterType getAtomicFilterType() {
		return AtomicFilterType.COMPARE;
	}
}
