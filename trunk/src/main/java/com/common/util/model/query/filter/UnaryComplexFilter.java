package com.common.util.model.query.filter;

/**
 * La clase que vamos a utilizar para una operación compleja unaria sobre un filtro. Esta operación hasta este momento es la operación NOT sobre un
 * filtro.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class UnaryComplexFilter extends ComplexFilter {

	/**
	 * El filtro que vamos a negar.
	 */
	private Filter filter;

	/**
	 * El constructor de un filtro negado del filtro recibido.
	 * 
	 * @param filter
	 *            El filtro que queremos negar.
	 */
	protected UnaryComplexFilter(Filter filter) {
		this.filter = filter;
	}

	/**
	 * La función encargada de retornar el filtro que tenemos negado.
	 * 
	 * @return El filtro que tenemos negado.
	 */
	public Filter getFilter() {
		return this.filter;
	}

	@Override
	public String toString() {
		String output = "";
		output += "NOT (\n" + this.filter;
		output += ")\n";
		return output;
	}

	@Override
	public ComplexRestrictionType getComplexType() {
		return ComplexRestrictionType.UNARY;
	}
}