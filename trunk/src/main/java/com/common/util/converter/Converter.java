package com.common.util.converter;

import java.io.Serializable;

/**
 * La clase que nos permite convertir entre 2 tipos de clases para poder manejar objetos similares.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @Deprecated
 * 
 * @param <C1>
 *            Una de las clases que vamos a convertir en la clase <C2>.
 * @param <C2>
 *            La otra clase que vamos a poder convertir en una clase <C1>.
 */
@Deprecated
public interface Converter<C1 extends Serializable, C2 extends Serializable> {

	/**
	 * La función que nos permite convertir un objeto de una clase <C1> en un convertir de una clase <C2>.
	 * 
	 * @Deprecated
	 * 
	 * @param entity
	 *            objeto de la clase <C1> que vamos a convertir en un objeto de la clase <C2>.
	 * @return El objeto de la clase <C2> que convertimos a partir del objeto de la clase <C1>.
	 */
	@Deprecated
	public C2 converter(C1 entity);

	/**
	 * La función que nos permite convertir un objeto de una clase <C2> en un convertir de una clase <C1>.
	 * 
	 * @Deprecated
	 * 
	 * @param entity
	 *            objeto de la clase <C2> que vamos a convertir en un objeto de la clase <C1>.
	 * @return El objeto de la clase <C1> que convertimos a partir del objeto de la clase <C2>.
	 */
	@Deprecated
	public C1 reverter(C2 entity);
}
