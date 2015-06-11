package com.common.util.business.converter;

import java.io.Serializable;

/**
 * Permite definir un componente que puede convertir de un objeto a otro.
 * 
 * @since 16/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <S>
 *            La clase desde la que vamos a convertir.
 * @param <T>
 *            La clase hacia la que vamos a convertir.
 */
public abstract class Converter<S, T> implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * La función encargada de convertir un objeto de una clase origen en un objeto de otra clase destino.
	 * 
	 * @param source
	 *            El objeto que vamos a ocupar de fuente para la conversión.
	 * @return El objeto de la clase destino que contiene los datos del objeto de la clase fuente.
	 */
	public abstract T convert(S source);
}