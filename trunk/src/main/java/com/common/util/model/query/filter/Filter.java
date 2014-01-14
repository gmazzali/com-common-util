package com.common.util.model.query.filter;

import java.io.Serializable;
import java.util.List;

import com.common.util.model.query.filter.BinaryComplexFilter.BynaryComplexFilterType;
import com.common.util.model.query.filter.CompareFilter.CompareFilterType;
import com.common.util.model.query.filter.LikeFilter.LikeType;

/**
 * La clase que detalla un filtro dentro de una consulta dentro de la base de datos.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class Filter {

	/**
	 * La función encargada de retornar un filtro que detalla la igualdad entre un atributo y un valor dado.
	 * 
	 * @param attibute
	 *            El atributo que va a compararse.
	 * @param value
	 *            El valor con el que va a compararse el atributo.
	 * @return El filtro que corresponde a la igualdad entre el atributo y el valor recibido.
	 */
	public static CompareFilter<Serializable> eq(String attibute, Serializable value) {
		return new CompareFilter<Serializable>(attibute, CompareFilterType.EQUALS, value);
	}

	/**
	 * La función encargada de retornar un filtro que detalla la no igualdad entre un atributo y un valor dado.
	 * 
	 * @param attibute
	 *            El atributo que va a compararse.
	 * @param value
	 *            El valor con el que va a compararse el atributo.
	 * @return El filtro que corresponde a la no igualdad entre el atributo y el valor recibido.
	 */
	public static CompareFilter<Serializable> neq(String attibute, Serializable value) {
		return new CompareFilter<Serializable>(attibute, CompareFilterType.NOT_EQUALS, value);
	}

	/**
	 * La función encargada de retornar un filtro que detalla la desigualdad de mayor (">") entre un atributo y un valor dado.
	 * 
	 * @param attibute
	 *            El atributo que va a compararse.
	 * @param value
	 *            El valor con el que va a compararse el atributo.
	 * @return El filtro que corresponde a la desigualdad de mayor (">") entre el atributo y el valor recibido.
	 */
	public static CompareFilter<Serializable> gt(String attibute, Serializable value) {
		return new CompareFilter<Serializable>(attibute, CompareFilterType.GREATER, value);
	}

	/**
	 * La función encargada de retornar un filtro que detalla la desigualdad de mayor o igual (">=") entre un atributo y un valor dado.
	 * 
	 * @param attibute
	 *            El atributo que va a compararse.
	 * @param value
	 *            El valor con el que va a compararse el atributo.
	 * @return El filtro que corresponde a la desigualdad de mayor o igual (">=") entre el atributo y el valor recibido.
	 */
	public static CompareFilter<Serializable> ge(String attibute, Serializable value) {
		return new CompareFilter<Serializable>(attibute, CompareFilterType.GREATER_OR_EQUALS, value);
	}

/**
	 * La función encargada de retornar un filtro que detalla la desigualdad de menor ("<") entre un atributo y un valor dado.
	 * @param attibute El atributo que va a compararse.
	 * @param value El valor con el que va a compararse el atributo.
	 * @return El filtro que corresponde a la desigualdad de menor ("<") entre el atributo y el valor recibido.
	 */
	public static CompareFilter<Serializable> lt(String attibute, Serializable value) {
		return new CompareFilter<Serializable>(attibute, CompareFilterType.LESSER, value);
	}

	/**
	 * La función encargada de retornar un filtro que detalla la desigualdad de menor o igual ("<=") entre un atributo y un valor dado.
	 * 
	 * @param attibute
	 *            El atributo que va a compararse.
	 * @param value
	 *            El valor con el que va a compararse el atributo.
	 * @return El filtro que corresponde a la desigualdad de menor o igual ("<=") entre el atributo y el valor recibido.
	 */
	public static CompareFilter<Serializable> le(String attibute, Serializable value) {
		return new CompareFilter<Serializable>(attibute, CompareFilterType.LESSER_OR_EQUALS, value);
	}

	/**
	 * La función encargada de retornar un filtro que detalla la inclusión de un atributo dentro de un listado de valores.
	 * 
	 * @param attribute
	 *            El atributo que va a verificarse.
	 * @param list
	 *            El listado donde va a verse si el atributo esta dentro.
	 * @return
	 */
	public static InFilter<Serializable> in(String attribute, List<? extends Serializable> list) {
		return new InFilter<Serializable>(attribute, list);
	}

	/**
	 * La función encargada de crear un filtro para verificar que el valor de un atributo se encuentre entre 2 valores.
	 * 
	 * @param attribute
	 *            El atributo que va a verificarse.
	 * @param lowValue
	 *            El valor bajo del rango de verificación.
	 * @param highValue
	 *            El valor alto del rango de verificación.
	 * @return El filtro para verificar que un atributo este dentro de un rango de valores.
	 */
	public static BetweenFilter<Serializable> between(String attribute, Serializable lowValue, Serializable highValue) {
		return new BetweenFilter<Serializable>(attribute, lowValue, highValue);
	}

	/**
	 * La función encargada de crear un filtro para la función LIKE para comparar oraciones.
	 * 
	 * @param attribute
	 *            El atributo que vamos a ocupar para comparar.
	 * @param like
	 *            La palabra que vamos a ocupar para comparar.
	 * @param type
	 *            El tipo de comparación que vamos a realizar.
	 * @return El filtro del tipo LIKE para consultas.
	 */
	public static LikeFilter like(String attribute, String like, LikeType type) {
		return new LikeFilter(attribute, like, type);
	}

	/**
	 * La función encargada de crear un filtro para la función LIKE para comparar oraciones solo por igualdad.
	 * 
	 * @param attribute
	 *            El atributo que vamos a ocupar para comparar por igualdad.
	 * @param like
	 *            La palabra que vamos a ocupar para comparar por igualdad.
	 * @return El filtro del tipo LIKE para consultas por igualdad.
	 */
	public static LikeFilter like(String attribute, String like) {
		return new LikeFilter(attribute, like, LikeType.EQUAL);
	}

	/**
	 * La función encargada de crear un filtro para verificar si un atributo es NULO o no.
	 * 
	 * @param attribute
	 *            El atributo que va a verificarse.
	 * @return El filtro para poder verificar si un atributo es nulo o no.
	 */
	public static NullFilter isNull(String attribute) {
		return new NullFilter(attribute);
	}

	/**
	 * La función encargada de retornar el filtro complejo binario del tipo AND que relaciona 2 filtros.
	 * 
	 * @param firstFilter
	 *            El primer filtro que va a relacionarse.
	 * @param secondFilter
	 *            El segundo filtro a relacionarse.
	 * @return El filtro resultante de la relación AND entre el primer filtro y el segundo.calc
	 */
	public static BinaryComplexFilter and(Filter firstFilter, Filter secondFilter) {
		return new BinaryComplexFilter(firstFilter, BynaryComplexFilterType.AND, secondFilter);
	}

	/**
	 * La función encargada de retornar el filtro complejo binario del tipo OR que relaciona 2 filtros.
	 * 
	 * @param firstFilter
	 *            El primer filtro que va a relacionarse.
	 * @param secondFilter
	 *            El segundo filtro a relacionarse.
	 * @return El filtro resultante de la relación OR entre el primer filtro y el segundo.
	 */
	public static BinaryComplexFilter or(Filter firstFilter, Filter secondFilter) {
		return new BinaryComplexFilter(firstFilter, BynaryComplexFilterType.OR, secondFilter);
	}

	/**
	 * La función encargada de crear un filtro negando el filtro que se recibe como parámetro.
	 * 
	 * @param filter
	 *            El filtro que va a negarse.
	 * @return El filtro ya negado.
	 */
	public static UnaryComplexFilter not(Filter filter) {
		return new UnaryComplexFilter(filter);
	}
}