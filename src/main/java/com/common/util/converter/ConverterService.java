package com.common.util.converter;

/**
 * La interfaz que define el comportamiento básico de un convertidor de un objeto a otro dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface ConverterService {

	/**
	 * La función encargada de convertir un objeto de una clase origen en un objeto de otra clase destino.
	 * 
	 * @param source
	 *            El objeto que vamos a ocupar de fuente para la conversión.
	 * @param targetClass
	 *            La clase a la que vamos a convertir el objeto fuente.
	 * @return El objeto de la clase destino que contiene los datos del objeto de la clase fuente.
	 */
	public <S, T> T convertTo(S source, Class<T> targetClass);

	/**
	 * La función encargada de validar que un objeto de una clase fuente puede ser convertida en un objeto de una clase destino dentro del convertidor
	 * que definimos dentro de este elemento.
	 * 
	 * @param sourceClass
	 *            La clase del objeto fuente.
	 * @param targetClass
	 *            La clase del objeto destino.
	 * @return TRUE en caso de que el convertidor actual pueda convertir de la clase fuente a la destino, en caso contrario retornamos FALSE.
	 */
	public <S, T> boolean canConvertTo(Class<S> sourceClass, Class<T> targetClass);
}
