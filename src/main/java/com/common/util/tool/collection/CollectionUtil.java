package com.common.util.tool.collection;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.common.util.exception.UncheckedException;

/**
 * Contiene las funciones básicas de manejo de colecciones dentro de un sistema.
 * 
 * @see Collection
 * @see Predicate
 * @see Process
 * @see Transformer
 * 
 * @since 05/03/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class CollectionUtil {

	/**
	 * Retorna un mapa conteniendo cada elemento unico con el valor que representa la cantidad de ocurrencias del elemento en la colección recibida.
	 * 
	 * @param items
	 *            La colección que vamos a recorrer para obtener la cardinalidad de cada uno de los items que tenemos en esta.
	 * @return El mapa que contiene cada elemento único de la colección con la cantidad de ocurrencias del mismo.
	 * @throws UncheckedException
	 *             En caso de que el parámetro sea nulo.
	 */
	public static <I extends Serializable> Map<I, Integer> getCardinalities(Collection<I> items) {
		// Verificamos que la colección recibida no sea nula.
		if (items == null) {
			throw new UncheckedException("The collection cannot be null.");
		}

		Map<I, Integer> cardinalities = new HashMap<I, Integer>();
		Iterator<I> iterator = items.iterator();

		while (iterator.hasNext()) {
			I key = iterator.next();
			Integer value = cardinalities.get(key);

			if (value == null) {
				cardinalities.put(key, 1);
			} else {
				cardinalities.put(key, value++);
			}
		}
		return cardinalities;
	}

	/**
	 * Cuenta la cantidad de elementos de la colección que cumplen la condición del predicado.
	 * 
	 * @see Predicate
	 * 
	 * @param items
	 *            La colección que vamos a utilizar para contar.
	 * @param predicate
	 *            El predicado que vamos a usar para contar los elementos.
	 * @return La cantidad de veces que el predicado fue evaluado de manera verdadera dentro de la colección recibida.
	 * @throws UncheckedException
	 *             En caso de que alguno de los parámetros sea nulo.
	 */
	public static <I extends Serializable> int count(Collection<I> items, Predicate<I> predicate) {
		// Verificamos que la colección recibida no sea nula.
		if (items == null) {
			throw new UncheckedException("The collection cannot be null.");
		}

		// Verificamos que el predicado recibido no sea nulo.
		if (predicate == null) {
			throw new UncheckedException("The predicate cannot be null.");
		}

		int count = 0;
		Iterator<I> iterator = items.iterator();

		while (iterator.hasNext()) {
			I item = iterator.next();
			if (predicate.evaluate(item)) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Busca la primer ocurrencia del elemento que cumple con la condición del predicado.
	 * 
	 * @see Predicate
	 * 
	 * @param items
	 *            La colección donde vamos a iterar para buscar un elemento.
	 * @param predicate
	 *            El predicado de busqueda.
	 * @return La primer ocurrencia del elemento que pasa la validación del predicado. En caso de no encontrar ninguno, se retorna un valor
	 *         <b>null</b>.
	 * @throws UncheckedException
	 *             En caso de que alguno de los parámetros sea nulo.
	 */
	public static <I extends Serializable> I find(Collection<I> items, Predicate<I> predicate) {
		// Verificamos que la colección recibida no sea nula.
		if (items == null) {
			throw new UncheckedException("The collection cannot be null.");
		}

		// Verificamos que el predicado recibido no sea nulo.
		if (predicate == null) {
			throw new UncheckedException("The predicate cannot be null.");
		}

		Iterator<I> iterator = items.iterator();

		while (iterator.hasNext()) {
			I item = iterator.next();
			if (predicate.evaluate(item)) {
				return item;
			}
		}
		return null;
	}

	/**
	 * Filtra una colección recibida a la que se le aplica el predicado a cada uno de los elementos que contiene para mantenerlo.
	 * 
	 * @see Predicate
	 * @see CollectionUtil#select(Collection, Predicate)
	 * 
	 * @param items
	 *            La colección que vamos a filtrar.
	 * @param predicate
	 *            El predicado que vamos a usar para filtrado.
	 * @throws UncheckedException
	 *             En caso de que alguno de los parámetros sea nulo.
	 */
	public static <I extends Serializable> void filter(Collection<I> items, Predicate<I> predicate) {
		// Verificamos que la colección recibida no sea nula.
		if (items == null) {
			throw new UncheckedException("The collection cannot be null.");
		}

		// Verificamos que el predicado recibido no sea nulo.
		if (predicate == null) {
			throw new UncheckedException("The predicate cannot be null.");
		}

		Iterator<I> iterator = items.iterator();

		while (iterator.hasNext()) {
			I item = iterator.next();
			if (!predicate.evaluate(item)) {
				iterator.remove();
			}
		}
	}

	/**
	 * Evalua si exite un elemento dentro de la lista que cumpla con el predicado recibido.
	 * 
	 * @see Predicate
	 * 
	 * @param items
	 *            La colección que vamos a recorrer para ver si existe un elemento dado.
	 * @param predicate
	 *            El predicado que vamos a usar para la busqueda.
	 * @return <i>true</i> si existe un elemento que cumple la condición del predicado, en caso contrario retorna <i>false</i>.
	 * @throws UncheckedException
	 *             En caso de que alguno de los parámetros sea nulo.
	 */
	public static <I extends Serializable> boolean exist(Collection<I> items, Predicate<I> predicate) {
		// Verificamos que la colección recibida no sea nula.
		if (items == null) {
			throw new UncheckedException("The collection cannot be null.");
		}

		// Verificamos que el predicado recibido no sea nulo.
		if (predicate == null) {
			throw new UncheckedException("The predicate cannot be null.");
		}

		Iterator<I> iterator = items.iterator();

		while (iterator.hasNext()) {
			I item = iterator.next();
			if (predicate.evaluate(item)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Selecciona los elementos de una lista que cumplen con la condición que tiene el predicado recibido.
	 * 
	 * @see Predicate
	 * @see CollectionUtil#filter(Collection, Predicate)
	 * 
	 * @param items
	 *            La colección sobre la que vamos a seleccionar los elementos.
	 * @param predicate
	 *            El predicado de selección de elementos.
	 * @return Una nueva lista con los elementos de la lista original que pasaron la evaluación del predicado.
	 * @throws UncheckedException
	 *             En caso de que alguno de los parámetros sea nulo.
	 */
	public static <I extends Serializable> Collection<I> select(Collection<I> items, Predicate<I> predicate) {
		// Verificamos que la colección recibida no sea nula.
		if (items == null) {
			throw new UncheckedException("The collection cannot be null.");
		}

		// Verificamos que el predicado recibido no sea nulo.
		if (predicate == null) {
			throw new UncheckedException("The predicate cannot be null.");
		}

		ArrayList<I> answer = new ArrayList<I>(items.size());
		Iterator<I> iterator = items.iterator();

		while (iterator.hasNext()) {
			I item = iterator.next();
			if (predicate.evaluate(item)) {
				answer.add(item);
			}
		}
		return answer;
	}

	/**
	 * Ejecuta el proceso recibido para cada uno de los elementos de la colección recibida.
	 * 
	 * @see Process
	 * 
	 * @param items
	 *            La colección que vamos a procesar.
	 * @param process
	 *            El proceso que va a ejecutarse a cada uno de los elementos de la lista.
	 * @throws UncheckedException
	 *             En caso de que alguno de los parámetros sea nulo.
	 */
	public static <I extends Serializable> void processAll(Collection<I> items, Process<I> process) {
		// Verificamos que la colección recibida no sea nula.
		if (items == null) {
			throw new UncheckedException("The collection cannot be null.");
		}

		// Verificamos que el proceso recibido no sea nulo.
		if (process == null) {
			throw new UncheckedException("The process cannot be null.");
		}

		Iterator<I> iterator = items.iterator();

		while (iterator.hasNext()) {
			I item = iterator.next();
			process.process(item);
		}
	}

	/**
	 * Transforma los elementos de una lista en otros de acuerdo al transformador recibido y los carga en una lista nueva.
	 * 
	 * @see Transformer
	 * 
	 * @param items
	 *            La colección que vamos a transformar.
	 * @param transform
	 *            El transformador de elementos que vamos a usar.
	 * @throws UncheckedException
	 *             En caso de que alguno de los parámetros sea nulo.
	 */
	public static <I extends Serializable, O extends Serializable> Collection<O> transform(Collection<I> items, Transformer<I, O> transformer) {
		// Verificamos que la colección recibida no sea nula.
		if (items == null) {
			throw new UncheckedException("The collection cannot be null.");
		}

		// Verificamos que el transformador recibido no sea nulo.
		if (transformer == null) {
			throw new UncheckedException("The transformer cannot be null.");
		}

		ArrayList<O> answer = new ArrayList<O>(items.size());
		Iterator<I> iterator = items.iterator();

		while (iterator.hasNext()) {
			I item = iterator.next();
			answer.add(transformer.transform(item));
		}
		return answer;
	}
}