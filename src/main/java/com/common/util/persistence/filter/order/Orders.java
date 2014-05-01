package com.common.util.persistence.filter.order;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * La clase que permite definir el orden de los elementos que vamos a recuperar desde la base de datos.
 * 
 * @see Order
 * 
 * @since 05/02/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class Orders implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * El mapa de los atributos y sus ordenes.
	 */
	Map<String, Order> orders;

	/**
	 * El constructor por omisión de un listado de ordenes.
	 */
	public Orders() {
		super();
		this.orders = new HashMap<String, Order>();
	}

	/**
	 * El constructor de un listado de ordenes que recibe un atributo y el orden del mismo.
	 * 
	 * @param property
	 *            El atributo que vamos a ordenar.
	 * @param order
	 *            El orden del atributo. Puede ser {@link Order#ASC} o {@link Order#DESC}.
	 */
	public Orders(String property, Order order) {
		this();
		this.orders.put(property, order);
	}

	/**
	 * Función encargada de agregar una nueva restricción de orden a las que ya tenemos.
	 * 
	 * @param property
	 *            El atributo que vamos a ordenar.
	 * @param order
	 *            El orden en el que se va a recuperar. Este orden puede ser {@link Order#ASC} o {@link Order#DESC}.
	 */
	public Orders addOrder(String property, Order order) {
		this.orders.put(property, order);
		return this;
	}

	/**
	 * Función encargada de retornar el listado de los atributos y los ordenes el los que queremos recuperarlos.
	 * 
	 * @return El listado de los atributos y sus ordenes.
	 */
	public Map<String, Order> getOrders() {
		return this.orders;
	}
}