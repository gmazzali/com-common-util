package com.common.util.persistence.filter.order;

import java.io.Serializable;

/**
 * La clase que define un orden de una propiedad.
 * 
 * <ul>
 * <li>{@link Order#ASC}</li>
 * <li>{@link Order#DESC}</li>
 * </ul>
 * 
 * @since 12/06/2015
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class Orders implements Serializable {

	private static final long serialVersionUID = 1L;

	private final Order order;

	private final String property;

	/**
	 * Permite construir un orden ascendente para la propiedad recibida.
	 * 
	 * @param property
	 *            La propiedad a la que vamos a crearle el orden.
	 * @return El orden creado.
	 */
	public Orders asc(String property) {
		return new Orders(Order.ASC, property);
	}

	/**
	 * Permite construir un orden descendente para la propiedad recibida.
	 * 
	 * @param property
	 *            La propiedad a la que vamos a crearle el orden.
	 * @return El orden creado.
	 */
	public Orders desc(String property) {
		return new Orders(Order.DESC, property);
	}

	/**
	 * El constructor de un orden.
	 * 
	 * @param order
	 *            El orden.
	 * @param property
	 *            El nombre de la propiedad.
	 */
	private Orders(Order order, String property) {
		super();
		this.order = order;
		this.property = property;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(this.order).append(":").append(this.property);
		return buffer.toString();
	}

	/**
	 * Permite recuperar el orden.
	 * 
	 * @return El orden.
	 */
	public Order getOrder() {
		return order;
	}

	/**
	 * Permite recuperar la propiedad del orden.
	 * 
	 * @return La propiedad del orden.
	 */
	public String getProperty() {
		return property;
	}
}