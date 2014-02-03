package com.common.util.model.query.filter;


/**
 * La clase que detalla un filtro complejo, como puede ser un filtro para expresiones booleanas o expresiones negadas.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public abstract class ComplexFilter extends Filter {

	/**
	 * La enumeraci�n que detalla los 2 tipos de fitros complejos que podemos crear, un filtro unario o un filtro binario.
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
	 * La funci�n encargada de retornar el tipo de filtro complejo que tenemos seleccionado.
	 * 
	 * @return El tipo de filtro complejo que tenemos.
	 */
	public abstract ComplexRestrictionType getComplexType();
}