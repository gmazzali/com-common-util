package com.common.util.converter;

import java.util.Collection;
import java.util.List;

/**
 * La interfaz que define el comportamiento básico de un convertidor de un objeto a otro dentro del sistema.
 * 
 * @since 05/02/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface ConverterService {

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
	 * Se encarga de convertir una colección de elementos de un tipo a una lista de otro tipo.
	 * 
	 * @param collection
	 *            La colección de elementos que vamos a convertir.
	 * @param returnClass
	 *            La clase de los elementos a los que queremos convertir la colección.
	 * @return El listado con los elementos de la primer colección convertidos.
	 */
	public <S, T> List<T> convertListTo(Collection<S> collection, Class<T> returnClass);
}

// <bean id="conversionService" class="com.common.util.converter.impl.ConverterServiceImpl">
// <property name="converters">
// <list>
// <bean class="nombre.de.la.clase.que.es.una.implementación.de.la.interfaz.GenericCoverter" />
// <bean class="com.topgroup.wga.converter.AccessRowBeanConverter" />
// <bean class="com.topgroup.wga.converter.UserBeanConverter" />
// </list>
// </property>
// </bean>