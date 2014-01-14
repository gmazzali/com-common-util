package com.common.util.model.query.filter;


/**
 * La clase que representa un filtro complejo binario, donde posee 2 filtros unidos por una operaci�n binaria.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class BinaryComplexFilter extends ComplexFilter {

	/**
	 * La enumeraci�n que tiene los tipos de operaciones booleanas que podemos usar dentro del filtro binario complejo.
	 * 
	 * <ul>
	 * <li>{@link BynaryComplexFilterType#AND}</li>
	 * <li>{@link BynaryComplexFilterType#OR}</li>
	 * </ul>
	 * 
	 * @author Guillermo Mazzali
	 * @version 1.0
	 */
	public enum BynaryComplexFilterType {

		/**
		 * La funci�n booleana OR.
		 */
		OR("OR"),
		/**
		 * LA funci�n binaria AND.
		 */
		AND("AND");

		/**
		 * El nombre que detalla el tipo de filtro.
		 */
		private String name;

		/**
		 * El contructor de un tipo de filtro.
		 * 
		 * @param name
		 *            El nombre que detalla el tipo de filtro.
		 */
		private BynaryComplexFilterType(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return this.name;
		}
	}

	/**
	 * El tipo de relaci�n binaria.
	 */
	private BynaryComplexFilterType type;
	/**
	 * El primer filtro de la relaci�n binaria.
	 */
	private Filter firstFilter;
	/**
	 * El segundo filtro de la relaci�n binaria.
	 */
	private Filter secondFilter;

	/**
	 * El contructor de un filtro complejo binario.
	 * 
	 * @param firstFilter
	 *            El primer filtro de la relaci�n binaria.
	 * @param operation
	 *            El tipo de relaci�n binaria.
	 * @param secondFilter
	 *            El segundo filtro de la relaci�n binaria.
	 */
	protected BinaryComplexFilter(Filter firstFilter, BynaryComplexFilterType operation, Filter secondFilter) {
		this.firstFilter = firstFilter;
		this.type = operation;
		this.secondFilter = secondFilter;
	}

	/**
	 * La funci�n encargada de retornar el tipo de operaci�n binaria que tenemos dentro de este filtro complejo.
	 * 
	 * @return El tipo de operaci�n binaria dentro de este filtro.
	 */
	public BynaryComplexFilterType getBinaryType() {
		return this.type;
	}

	/**
	 * La funci�n encargada de retornar el primer filtro de la relaci�n binaria.
	 * 
	 * @return El primer filtro de la relaci�n binaria.
	 */
	public Filter getFirstFilter() {
		return this.firstFilter;
	}

	/**
	 * La funci�n encargada de retornar el segundo filtro de la relaci�n binaria.
	 * 
	 * @return El segundo filtro de la relaci�n binaria.
	 */
	public Filter getSecondFilter() {
		return this.secondFilter;
	}

	@Override
	public String toString() {
		String output = "(";
		if (this.firstFilter != null) {
			output += this.firstFilter.toString();
		}
		output += "\n";
		if (this.type != null) {
			output += " " + this.type.toString();
		}
		output += "\n";
		if (this.secondFilter != null) {
			output += " " + this.secondFilter.toString();
		}
		output += ")";
		return output;
	}

	@Override
	public ComplexRestrictionType getComplexType() {
		return ComplexRestrictionType.BINARY;
	}
}