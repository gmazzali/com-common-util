package com.common.util.model.filter;

import java.io.Serializable;

/**
 * La clase que define un filtro que permite crear una consulta LIKE sobre un elemento de String.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class LikeFilter extends AtomicFilter<Serializable> {

	/**
	 * La enumeración que contiene los tipos de comparaciones de String.
	 */
	public enum LikeType {

		EQUAL, START, END, ANY;

		/**
		 * Función encargada de convertir la palabra que se recibe para convertirla de acuerdo al tipo de busqueda que queremos realizar sobre la base
		 * de datos.
		 * 
		 * @param like
		 *            La palabra que va a convertirse para la consulta.
		 * @return La palabra lista para la consulta LIKE.
		 */
		public String convert(String like) {

			switch (this) {

				case EQUAL:
					return like;

				case START:
					return like + "%";

				case END:
					return "%" + like;

				case ANY:
					return "%" + like + "%";

				default:
					return null;
			}
		}
	}

	/**
	 * El tipo de consulta like que vamos a realizar.
	 */
	private LikeType type;
	/**
	 * La palabra que vamos a ocupar para la consulta like.
	 */
	private String like;

	/**
	 * El constructor de un filtro LIKE.
	 * 
	 * @param attribute
	 *            El atributo que va a compararse el valor.
	 * @param like
	 *            La palabra con la que va a hacerse la comparación.
	 * @param type
	 *            El tipo de comparación que vamos a realizar.
	 */
	protected LikeFilter(String attribute, String like, LikeType type) {
		super(attribute);
		this.like = like;
		this.type = type;
	}

	/**
	 * La función que permite retornar el tipo de consulta like que vamos a realizar.
	 * 
	 * @return El tipo de consulta like que vamos a realizar.
	 */
	public LikeType getType() {
		return this.type;
	}

	/**
	 * La función que permite retornar la palabra sobre la que vamos a realizar la consulta like.
	 * 
	 * @return La palabra que vamos a ocupar dentro de la consulta like.
	 */
	public String getLike() {
		return this.like;
	}

	@Override
	public String toString() {
		String output = "";
		output += this.getAttribute();
		output += " LIKE " + this.type.convert(this.like);
		return output;
	}

	@Override
	public AtomicFilterType getAtomicFilterType() {
		return AtomicFilterType.LIKE;
	}
}
