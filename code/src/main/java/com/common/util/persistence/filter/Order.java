package com.common.util.persistence.filter;

import java.io.Serializable;

/**
 * Define the order of a property.
 * 
 * <ul>
 * <li>{@link OrderType#ASC}</li>
 * <li>{@link OrderType#DESC}</li>
 * </ul>
 * 
 * @since 17/11/2016
 * @author Guillermo S. Mazzali
 * @version 1.0
 */
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * The type of the order.
	 * 
	 * <ul>
	 * <li>{@link OrderType#ASC}</li>
	 * <li>{@link OrderType#DESC}</li>
	 * </ul>
	 * 
	 * @since 17/11/2016
	 * @author Guillermo S. Mazzali
	 * @version 1.0
	 */
	public static enum OrderType {
		ASC, DESC;
	}

	/**
	 * The property of the order.
	 */
	private final String property;
	/**
	 * The order type.
	 */
	private final OrderType orderType;

	/**
	 * Allow create an ASC order for one property.
	 * 
	 * @param property
	 *            The property for the order.
	 * @return The new order.
	 */
	public Order asc(String property) {
		return new Order(OrderType.ASC, property);
	}

	/**
	 * Allow create an DESC order for one property.
	 * 
	 * @param property
	 *            The property for the order.
	 * @return The new order.
	 */
	public Order desc(String property) {
		return new Order(OrderType.DESC, property);
	}

	/**
	 * The constructor with parameters.
	 * 
	 * @param order
	 *            The order type.
	 * @param property
	 *            The property for the order.
	 */
	private Order(OrderType orderType, String property) {
		super();
		this.orderType = orderType;
		this.property = property;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(this.orderType);
		buffer.append(":");
		buffer.append(this.property);
		return buffer.toString();
	}

	/**
	 * Retrieve the property.
	 * 
	 * @return The property for the order.
	 */
	public String getProperty() {
		return property;
	}

	/**
	 * Retrieve the order type.
	 * 
	 * @return The order type.
	 */
	public OrderType getOrderType() {
		return orderType;
	}
}