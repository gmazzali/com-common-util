package com.common.util.model.query.filter;

/**
 * La clase que detalla un filtro complejo, como puede ser un filtro para expresiones booleanas o expresiones negadas.
 * 
 * @since 05/02/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public abstract class ComplexFilter extends Filter {
	private static final long serialVersionUID = 1L;

	/**
	 * La enumeración que detalla los 2 tipos de fitros complejos que podemos crear, un filtro unario o un filtro binario.
	 * 
	 * <ul>
	 * <li>{@link ComplexRestrictionType#UNARY}</li>
	 * <li>{@link ComplexRestrictionType#BINARY}</li>
	 * </ul>
	 * 
	 * @author Guillermo Mazzali
	 * @version 1.0
	 */
	public enum ComplexRestrictionType {
		UNARY, BINARY;
	}

	/**
	 * La función encargada de retornar el tipo de filtro complejo que tenemos seleccionado.
	 * 
	 * @return El tipo de filtro complejo que tenemos.
	 */
	public abstract ComplexRestrictionType getComplexType();
}