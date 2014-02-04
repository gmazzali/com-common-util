package com.common.util.model.query.order;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * La clase que permite definir el orden de los elementos que vamos a recuperar desde la base de datos.
 * 
 * @see Order
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class OrderBy implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * El mapa de los atributos y sus ordenes.
	 */
	Map<String, Order> orders;

	/**
	 * Constructor de un orden para un atributo dado.
	 * 
	 * @param attribute
	 *            El atributo que vamos a ordenar.
	 * @param order
	 *            El orden del atributo. Puede ser {@link Order#ASC} o {@link Order#DESC}.
	 */
	public OrderBy(String attribute, Order order) {
		this.orders = new HashMap<String, Order>();
		this.orders.put(attribute, order);
	}

	/**
	 * Función encargada de agregar una nueva restricción de orden a las que ya tenemos.
	 * 
	 * @param attribute
	 *            El atributo que vamos a ordenar.
	 * @param order
	 *            El orden en el que se va a recuperar. Este orden puede ser {@link Order#ASC} o {@link Order#DESC}.
	 */
	public OrderBy addOrder(String attribute, Order order) {
		this.orders.put(attribute, order);
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