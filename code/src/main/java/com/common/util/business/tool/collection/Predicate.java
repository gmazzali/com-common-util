package com.common.util.business.tool.collection;

import java.io.Serializable;

/**
 * Define un predicado para la evaluación de elementos de una lista para su filtrado.
 * 
 * @since 05/03/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <I>
 *            La clase de los elementos que vamos a evaluar.
 */
public interface Predicate<I extends Serializable> {

	/**
	 * Permite evaluar un elemento de una lista para ver si cumple con una condición de selección.
	 * 
	 * @param item
	 *            El elemento de la lista que vamos a evaluar.
	 * @return <i>true</i> en caso de que querramos que el elemento se seleccione, en caso contrario, retorna <i>false</i>.
	 */
	public boolean evaluate(I item);
}