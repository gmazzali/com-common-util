package com.common.util.business.tool.collection;

import java.io.Serializable;

/**
 * Define un procesamiento de elementos de una lista.
 * 
 * @since 05/03/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <I>
 *            La clase de los elementos que vamos a procesar.
 */
public interface Process<I extends Serializable> {

	/**
	 * Permite procesar un elemento de una lista.
	 * 
	 * @param item
	 *            El elemento de la lista que vamos a procesar.
	 */
	public void process(I item);
}