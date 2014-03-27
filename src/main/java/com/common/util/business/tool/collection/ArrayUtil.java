package com.common.util.business.tool.collection;

/**
 * Contiene las funciones b�sicas de manejo de arreglos dentro de un sistema.
 * 
 * @see CollectionUtil
 * 
 * @since 27/03/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class ArrayUtil {

	/**
	 * Funci�n Null-safe que verifica si un arreglo es vac�o.
	 * 
	 * @param array
	 *            El arreglo que vamos a verificar, puede ser <code>null</code>.
	 * @return <i>true</i> si el arreglo recibido es <code>null</code> o esta vac�o, en caso contrario retorna <i>false</i>.
	 */
	public static boolean isEmpty(Object[] array) {
		return (array == null || array.length == 0);
	}

	/**
	 * Funci�n Null-safe que verifica si un arreglo no es vac�o.
	 * 
	 * @param array
	 *            El arreglo que vamos a verificar, puede ser <code>null</code>.
	 * @return <i>true</i> si el arreglo recibido no es nulo y no esta vac�o, en caso contrario retorna <i>false</i>.
	 */
	public static boolean isNotEmpty(Object[] array) {
		return !ArrayUtil.isEmpty(array);
	}
}