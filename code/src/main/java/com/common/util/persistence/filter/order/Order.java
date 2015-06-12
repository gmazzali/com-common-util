package com.common.util.persistence.filter.order;

/**
 * La enumeración que determina los ordenes en los que vamos a poder ordenar los atributos de una consulta.
 * 
 * <ul>
 * <li>{@link Order#ASC}</li>
 * <li>{@link Order#DESC}</li>
 * </ul>
 * 
 * @since 05/02/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public enum Order {

	/**
	 * Determina un orden ascendente del atributo.
	 */
	ASC,
	/**
	 * Determina un orden descendente del atributo.
	 */
	DESC;
}