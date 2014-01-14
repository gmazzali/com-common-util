package com.common.util.model.query.filter;


/**
 * La clase que representa un filtro complejo binario, donde posee 2 filtros unidos por una operación binaria.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class BinaryComplexFilter extends ComplexFilter {

	/**
	 * La enumeración que tiene los tipos de operaciones booleanas que podemos usar dentro del filtro binario complejo.
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
		 * La función booleana OR.
		 */
		OR("OR"),
		/**
		 * LA función binaria AND.
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
	 * El tipo de relación binaria.
	 */
	private BynaryComplexFilterType type;
	/**
	 * El primer filtro de la relación binaria.
	 */
	private Filter firstFilter;
	/**
	 * El segundo filtro de la relación binaria.
	 */
	private Filter secondFilter;

	/**
	 * El contructor de un filtro complejo binario.
	 * 
	 * @param firstFilter
	 *            El primer filtro de la relación binaria.
	 * @param operation
	 *            El tipo de relación binaria.
	 * @param secondFilter
	 *            El segundo filtro de la relación binaria.
	 */
	protected BinaryComplexFilter(Filter firstFilter, BynaryComplexFilterType operation, Filter secondFilter) {
		this.firstFilter = firstFilter;
		this.type = operation;
		this.secondFilter = secondFilter;
	}

	/**
	 * La función encargada de retornar el tipo de operación binaria que tenemos dentro de este filtro complejo.
	 * 
	 * @return El tipo de operación binaria dentro de este filtro.
	 */
	public BynaryComplexFilterType getBinaryType() {
		return this.type;
	}

	/**
	 * La función encargada de retornar el primer filtro de la relación binaria.
	 * 
	 * @return El primer filtro de la relación binaria.
	 */
	public Filter getFirstFilter() {
		return this.firstFilter;
	}

	/**
	 * La función encargada de retornar el segundo filtro de la relación binaria.
	 * 
	 * @return El segundo filtro de la relación binaria.
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