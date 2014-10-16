package com.common.util.business.tool.collection;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.common.util.business.tool.VerifierUtil;
import com.common.util.domain.exception.UncheckedException;

/**
 * Contiene las funciones b�sicas de manejo de colecciones dentro de un sistema.
 * 
 * @see ArrayUtil
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

	public static <T> ArrayList<T> newArrayList(T... item) {
		ArrayList<T> list = new ArrayList<T>();
		if (item != null) {
			Collections.addAll(list, item);
		}
		return list;
	}

	/**
	 * Funci�n Null-safe que verifica si una colecci�n es vacia.
	 * 
	 * @param items
	 *            La colecci�n que vamos a verificar, puede ser <code>null</code>.
	 * @return <i>true</i> si la colecci�n recibida es <code>null</code> o esta vac�a, en caso contrario retorna <i>false</i>.
	 */
	public static boolean isEmpty(Collection<? extends Object> items) {
		return (items == null || items.isEmpty());
	}

	/**
	 * Funci�n Null-safe que verifica si una colecci�n no es vacia.
	 * 
	 * @param items
	 *            La colecci�n que vamos a verificar, puede ser nula.
	 * @return <i>true</i> si la colecci�n recibida no es nula y no esta vac�a, en caso contrario retorna <i>false</i>.
	 */
	public static boolean isNotEmpty(Collection<? extends Object> items) {
		return !CollectionUtil.isEmpty(items);
	}

	/**
	 * Funci�n Null-safe que verifica si una colecci�n contiene un elemento.
	 * 
	 * @param items
	 *            La colecci�n que vamos a verificar, puede ser nula.
	 * @param item
	 *            El item que vamos a verificar si existe dentro de la colecci�n.
	 * @return <i>true</i> si la colecci�n recibida no es nula y contiene el elemento recibido, en caso contrario retorna <i>false</i>.
	 */
	public static <I extends Serializable> boolean isInclude(Collection<I> items, I item) {
		return items != null ? items.contains(item) : false;
	}

	/**
	 * Retorna un mapa conteniendo cada elemento unico con el valor que representa la cantidad de ocurrencias del elemento en la colecci�n recibida.
	 * 
	 * @param items
	 *            La colecci�n que vamos a recorrer para obtener la cardinalidad de cada uno de los items que tenemos en esta.
	 * @return El mapa que contiene cada elemento �nico de la colecci�n con la cantidad de ocurrencias del mismo.
	 * @throws UncheckedException
	 *             En caso de que el par�metro sea nulo.
	 */
	public static <I extends Serializable> Map<I, Integer> cardinalities(Collection<I> items) {
		// Verificamos que la colecci�n recibida no sea nula.
		VerifierUtil.checkNotNull(items, "The collection cannot be null", "util.collection.error.cardinalities.collection.null");

		Map<I, Integer> cardinalities = new HashMap<I, Integer>();
		Iterator<I> iterator = items.iterator();

		while (iterator.hasNext()) {
			I key = iterator.next();
			Integer value = cardinalities.get(key);

			if (value == null) {
				cardinalities.put(key, 1);
			} else {
				cardinalities.put(key, ++value);
			}
		}
		return cardinalities;
	}

	/**
	 * Calcula la cantidad de ocurrencias del elemento recibido dentro de la colecci�n.
	 * 
	 * @param items
	 *            La colecci�n que vamos a recorrer.
	 * @param item
	 *            El elemento que vamos a contar. Puede ser nulo.
	 * @return El numero de ocurrencias que tenemos del elemento dentro de la colecci�n.
	 * @throws UncheckedException
	 *             En caso de que alguno de los par�metros sea nulo.
	 */
	public static <I extends Serializable> int cardinality(Collection<I> items, I item) {
		// Verificamos que la colecci�n recibida no sea nula.
		VerifierUtil.checkNotNull(items, "The collection cannot be null", "util.collection.error.cardinality.collection.null");

		int count = 0;
		Iterator<I> it = items.iterator();

		while (it.hasNext()) {
			I collectionItem = it.next();
			if (VerifierUtil.<I> equals(collectionItem, item)) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Cuenta la cantidad de elementos de la colecci�n que cumplen la condici�n del predicado.
	 * 
	 * @see Predicate
	 * 
	 * @param items
	 *            La colecci�n que vamos a utilizar para contar.
	 * @param predicate
	 *            El predicado que vamos a usar para contar los elementos.
	 * @return La cantidad de veces que el predicado fue evaluado de manera verdadera dentro de la colecci�n recibida.
	 * @throws UncheckedException
	 *             En caso de que alguno de los par�metros sea nulo.
	 */
	public static <I extends Serializable> int count(Collection<I> items, Predicate<I> predicate) {
		// Verificamos que la colecci�n o el predicado no sean nulos.
		VerifierUtil.checkNotNull(items, "The collection cannot be null", "util.collection.error.count.collection.null");
		VerifierUtil.checkNotNull(predicate, "The predicate cannot be null", "util.collection.error.count.predicate.null");

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
	 * Busca la primer ocurrencia del elemento que cumple con la condici�n del predicado.
	 * 
	 * @see Predicate
	 * 
	 * @param items
	 *            La colecci�n donde vamos a iterar para buscar un elemento.
	 * @param predicate
	 *            El predicado de busqueda.
	 * @return La primer ocurrencia del elemento que pasa la validaci�n del predicado. En caso de no encontrar ninguno, se retorna un valor
	 *         <b>null</b>.
	 * @throws UncheckedException
	 *             En caso de que alguno de los par�metros sea nulo.
	 */
	public static <I extends Serializable> I find(Collection<I> items, Predicate<I> predicate) {
		// Verificamos que la colecci�n o el predicado no sean nulos.
		VerifierUtil.checkNotNull(items, "The collection cannot be null", "util.collection.error.find.collection.null");
		VerifierUtil.checkNotNull(predicate, "The predicate cannot be null", "util.collection.error.find.predicate.null");

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
	 * Filtra una colecci�n recibida a la que se le aplica el predicado a cada uno de los elementos que contiene para mantenerlo.
	 * 
	 * @see Predicate
	 * @see CollectionUtil#select(Collection, Predicate)
	 * 
	 * @param items
	 *            La colecci�n que vamos a filtrar.
	 * @param predicate
	 *            El predicado que vamos a usar para filtrado.
	 * @throws UncheckedException
	 *             En caso de que alguno de los par�metros sea nulo.
	 */
	public static <I extends Serializable> void filter(Collection<I> items, Predicate<I> predicate) {
		// Verificamos que la colecci�n o el predicado no sean nulos.
		VerifierUtil.checkNotNull(items, "The collection cannot be null", "util.collection.error.filter.collection.null");
		VerifierUtil.checkNotNull(predicate, "The predicate cannot be null", "util.collection.error.filter.predicate.null");

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
	 *            La colecci�n que vamos a recorrer para ver si existe un elemento dado.
	 * @param predicate
	 *            El predicado que vamos a usar para la busqueda.
	 * @return <i>true</i> si existe un elemento que cumple la condici�n del predicado, en caso contrario retorna <i>false</i>.
	 * @throws UncheckedException
	 *             En caso de que alguno de los par�metros sea nulo.
	 */
	public static <I extends Serializable> boolean exist(Collection<I> items, Predicate<I> predicate) {
		// Verificamos que la colecci�n o el predicado no sean nulos.
		VerifierUtil.checkNotNull(items, "The collection cannot be null", "util.collection.error.exist.collection.null");
		VerifierUtil.checkNotNull(predicate, "The predicate cannot be null", "util.collection.error.exist.predicate.null");

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
	 * Selecciona los elementos de una lista que cumplen con la condici�n que tiene el predicado recibido.
	 * 
	 * @see Predicate
	 * @see CollectionUtil#filter(Collection, Predicate)
	 * 
	 * @param items
	 *            La colecci�n sobre la que vamos a seleccionar los elementos.
	 * @param predicate
	 *            El predicado de selecci�n de elementos.
	 * @return Una nueva lista con los elementos de la lista original que pasaron la evaluaci�n del predicado.
	 * @throws UncheckedException
	 *             En caso de que alguno de los par�metros sea nulo.
	 */
	public static <I extends Serializable> Collection<I> select(Collection<I> items, Predicate<I> predicate) {
		// Verificamos que la colecci�n o el predicado no sean nulos.
		VerifierUtil.checkNotNull(items, "The collection cannot be null", "util.collection.error.select.collection.null");
		VerifierUtil.checkNotNull(predicate, "The predicate cannot be null", "util.collection.error.select.predicate.null");

		ArrayList<I> outputCollection = new ArrayList<I>(items.size());
		Iterator<I> iterator = items.iterator();

		while (iterator.hasNext()) {
			I item = iterator.next();
			if (predicate.evaluate(item)) {
				outputCollection.add(item);
			}
		}
		return outputCollection;
	}

	/**
	 * Ejecuta el proceso recibido para cada uno de los elementos de la colecci�n recibida.
	 * 
	 * @see Process
	 * 
	 * @param items
	 *            La colecci�n que vamos a procesar.
	 * @param process
	 *            El proceso que va a ejecutarse a cada uno de los elementos de la lista.
	 * @throws UncheckedException
	 *             En caso de que alguno de los par�metros sea nulo.
	 */
	public static <I extends Serializable> void processAll(Collection<I> items, Process<I> process) {
		// Verificamos que la colecci�n o el procesador no sean nulos.
		VerifierUtil.checkNotNull(items, "The collection cannot be null", "util.collection.error.processall.collection.null");
		VerifierUtil.checkNotNull(process, "The process cannot be null", "util.collection.error.processall.process.null");

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
	 *            La colecci�n que vamos a transformar.
	 * @param transform
	 *            El transformador de elementos que vamos a usar.
	 * @throws UncheckedException
	 *             En caso de que alguno de los par�metros sea nulo.
	 */
	public static <I extends Serializable, O extends Serializable> Collection<O> transform(Collection<I> items, Transformer<I, O> transformer) {
		// Verificamos que la colecci�n o el transformador no sean nulos.
		VerifierUtil.checkNotNull(items, "The collection cannot be null", "util.collection.error.transformer.collection.null");
		VerifierUtil.checkNotNull(transformer, "The transformer cannot be null", "util.collection.error.transformer.transformer.null");

		ArrayList<O> outputCollection = new ArrayList<O>(items.size());
		Iterator<I> iterator = items.iterator();

		while (iterator.hasNext()) {
			I item = iterator.next();
			outputCollection.add(transformer.transform(item));
		}
		return outputCollection;
	}

	/**
	 * Crea una nueva colecci�n que contiene la uni�n de las 2 colecciones recibidas.
	 * 
	 * @see Collection#addAll
	 * 
	 * @param firstCollection
	 *            La primer colecci�n para unir.
	 * @param secondCollection
	 *            La segunda colecci�n para unir.
	 * @return Una nueva colecci�n con la uni�n de las 2 listas.
	 * @throws UncheckedException
	 *             En caso de que alguno de los par�metros sea nulo.
	 */
	public static <I extends Serializable> Collection<I> union(Collection<I> firstCollection, Collection<I> secondCollection) {
		// Verificamos que las colecciones no sean nulas.
		VerifierUtil.checkNotNull(firstCollection, "The first collection cannot be null", "util.collection.error.union.collection.1.null");
		VerifierUtil.checkNotNull(secondCollection, "The second collection cannot be null", "util.collection.error.union.collection.2.null");

		ArrayList<I> outputCollection = new ArrayList<I>();

		Map<I, Integer> firstCardinalityMap = CollectionUtil.<I> cardinalities(firstCollection);
		Map<I, Integer> secondCardinalityMap = CollectionUtil.<I> cardinalities(secondCollection);

		Set<I> itemSet = new HashSet<I>(firstCollection);
		itemSet.addAll(secondCollection);

		Iterator<I> iterator = itemSet.iterator();
		while (iterator.hasNext()) {
			I item = iterator.next();
			int maxCardinality = Math.max(CollectionUtil.<I> getFrequency(item, firstCardinalityMap),
					CollectionUtil.<I> getFrequency(item, secondCardinalityMap));
			for (int i = 0; i < maxCardinality; i++) {
				outputCollection.add(item);
			}
		}
		return outputCollection;
	}

	/**
	 * Crea una nueva colecci�n que contiene la intersecci�n de las 2 colecci�nes recibidas.
	 * 
	 * @see Collection#retainAll
	 * 
	 * @param firstCollection
	 *            La primer colecci�n para intersectar.
	 * @param secondCollection
	 *            La segunda colecci�n para intersectar.
	 * @return Una nueva colecci�n con la intersecci�n de las 2 listas.
	 * @throws UncheckedException
	 *             En caso de que alguno de los par�metros sea nulo.
	 */
	public static <I extends Serializable> Collection<I> intersection(Collection<I> firstCollection, Collection<I> secondCollection) {
		// Verificamos que las colecciones no sean nulas.
		VerifierUtil.checkNotNull(firstCollection, "The first collection cannot be null", "util.collection.error.intersection.collection.1.null");
		VerifierUtil.checkNotNull(secondCollection, "The second collection cannot be null", "util.collection.error.intersection.collection.2.null");

		ArrayList<I> outputCollection = new ArrayList<I>();

		Map<I, Integer> firstCardinalityMap = CollectionUtil.<I> cardinalities(firstCollection);
		Map<I, Integer> secondCardinalityMap = CollectionUtil.<I> cardinalities(secondCollection);

		Set<I> itemSet = new HashSet<I>(firstCollection);
		itemSet.addAll(secondCollection);

		Iterator<I> iterator = itemSet.iterator();
		while (iterator.hasNext()) {
			I item = iterator.next();
			int maxCardinality = Math.min(CollectionUtil.<I> getFrequency(item, firstCardinalityMap),
					CollectionUtil.<I> getFrequency(item, secondCardinalityMap));
			for (int i = 0; i < maxCardinality; i++) {
				outputCollection.add(item);
			}
		}
		return outputCollection;
	}

	/**
	 * Crea una nueva colecci�n que contiene los elementos de las 2 colecciones, menos los elementos que tienen en com�n.
	 * 
	 * @see Collection#addAll
	 * 
	 * @param firstCollection
	 *            La primer colecci�n.
	 * @param secondCollection
	 *            La segunda colecci�n.
	 * @return Una nueva colecci�n que contiene los elementos de las 2 colecciones, menos los elementos que tienen en com�n.
	 * @throws UncheckedException
	 *             En caso de que alguno de los par�metros sea nulo.
	 */
	public static <I extends Serializable> Collection<I> disjunction(Collection<I> firstCollection, Collection<I> secondCollection) {
		// Verificamos que las colecciones no sean nulas.
		VerifierUtil.checkNotNull(firstCollection, "The first collection cannot be null", "util.collection.error.disjunction.collection.1.null");
		VerifierUtil.checkNotNull(secondCollection, "The second collection cannot be null", "util.collection.error.disjunction.collection.2.null");

		ArrayList<I> outputCollection = new ArrayList<I>();

		Map<I, Integer> firstCardinalityMap = CollectionUtil.<I> cardinalities(firstCollection);
		Map<I, Integer> secondCardinalityMap = CollectionUtil.<I> cardinalities(secondCollection);

		Set<I> itemSet = new HashSet<I>(firstCollection);
		itemSet.addAll(secondCollection);

		Iterator<I> iterator = itemSet.iterator();

		while (iterator.hasNext()) {
			I item = iterator.next();
			int maxCardinality = Math.max(CollectionUtil.<I> getFrequency(item, firstCardinalityMap),
					CollectionUtil.<I> getFrequency(item, secondCardinalityMap))
					- Math.min(CollectionUtil.<I> getFrequency(item, firstCardinalityMap),
							CollectionUtil.<I> getFrequency(item, secondCardinalityMap));
			for (int i = 0; i < maxCardinality; i++) {
				outputCollection.add(item);
			}
		}
		return outputCollection;
	}

	/**
	 * Crea una nueva colecci�n que contiene los elementos que estan en la primer colecci�n, menos los que estan dentro de la segunda.
	 * 
	 * @see Collection#removeAll
	 * 
	 * @param firstCollection
	 *            La primer colecci�n.
	 * @param secondCollection
	 *            La segunda colecci�n.
	 * @return Una nueva colecci�n con los elementos de la primer colecci�n menos los elementos de la segunda.
	 * @throws UncheckedException
	 *             En caso de que alguno de los par�metros sea nulo.
	 */
	public static <I extends Serializable> Collection<I> subtract(Collection<I> firstCollection, Collection<I> secondCollection) {
		// Verificamos que las colecciones no sean nulas.
		VerifierUtil.checkNotNull(firstCollection, "The first collection cannot be null", "util.collection.error.subtract.collection.1.null");
		VerifierUtil.checkNotNull(secondCollection, "The second collection cannot be null", "util.collection.error.subtract.collection.2.null");

		ArrayList<I> outputCollection = new ArrayList<I>(firstCollection);
		Iterator<I> iterator = secondCollection.iterator();

		while (iterator.hasNext()) {
			I item = iterator.next();
			outputCollection.remove(item);
		}
		return outputCollection;
	}

	/**
	 * Permite recuperar la frecuencia que tenemos dentro de un mapa de un elemento dado.
	 * 
	 * @param item
	 *            El elemento del que vamos a recuperar la frecuencia.
	 * @param frequencyMap
	 *            El mapa donde tenemos almacenado la frecuencia.
	 * @return La frecuencia que corresponde con el elemento recibido.
	 */
	private static <I extends Serializable> int getFrequency(I item, Map<I, Integer> frequencyMap) {
		return frequencyMap.get(item) != null ? frequencyMap.get(item) : 0;
	}
}