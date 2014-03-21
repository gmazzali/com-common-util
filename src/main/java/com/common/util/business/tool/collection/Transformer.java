package com.common.util.business.tool.collection;

import java.io.Serializable;

/**
 * Define la transformación de un elemento de una lista en otro.
 * 
 * @since 05/03/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <I>
 *            La clase del item que vamos a convertir.
 * @param <O>
 *            La clase a a la que vamos a convertir el item recibido.
 */
public interface Transformer<I extends Serializable, O extends Serializable> {

	/**
	 * Permite convertir un elemento en otro distinto.
	 * 
	 * @param item
	 *            El item que vamos a usar de fuente.
	 * @return El item transformado.
	 */
	public O transform(I item);
}