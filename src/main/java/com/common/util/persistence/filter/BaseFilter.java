package com.common.util.persistence.filter;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;

import com.common.util.persistence.filter.order.Order;
import com.common.util.persistence.filter.order.Orders;

/**
 * Representa un filtro para la busqueda de registros en la base de datos.
 * 
 * @since 17/03/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <PK>
 *            La clave que corresponde con el ID de las entidades de filtrado.
 */
public class BaseFilter<PK extends Serializable> {

	/**
	 * El orden en el que se quiere recuperar la consulta.
	 */
	private Orders orders;
	/**
	 * El listado de ID que vamos a excluir de la consulta.
	 */
	private PK[] excludeIds;
	/**
	 * El primer resultado que queremos.
	 */
	private Integer firstResult;
	/**
	 * La máxima cantidad de registros que vamos a recueperar.
	 */
	private Integer maxResult;

	/**
	 * El constructor por omisión.
	 */
	public BaseFilter() {
		super();
		this.orders = new Orders();
	}

	@Override
	public String toString() {
		// TODO Hacer este metodo mejor.
		StringBuilder buffer = new StringBuilder(getClass().getName());
		try {
			buffer.append("{ ");
			BeanInfo info = Introspector.getBeanInfo(getClass(), Object.class);
			for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
				String value = String.valueOf((pd.getReadMethod() != null ? pd.getReadMethod().invoke(this) : "?"));
				buffer.append(" [" + pd.getName() + "=" + value + "]");
			}
			buffer.append(" }");
		} catch (Exception e) {
			buffer.append("Error: " + e.getMessage());
		}
		return buffer.toString();
	}

	/**
	 * Retorna el listado de los ordenes en los que queremos realizar el filtrado.
	 * 
	 * @return El listado de los ordenes en los que queremos realizar el filtrado.
	 */
	public Orders getOrders() {
		return orders;
	}

	/**
	 * Carga el listado de los ordenes en los que queremos realizar el filtrado.
	 * 
	 * @param orders
	 *            El listado de los ordenes en los que queremos realizar el filtrado.
	 */
	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	/**
	 * Permite agregar un nuevo orden al filtrado de la consulta.
	 * 
	 * @param property
	 *            La propiedad sobre la que va a ordenarse la consulta.
	 * @param order
	 *            El tipo de orden que vamos a utilizar.
	 */
	public void addOrder(String property, Order order) {
		this.orders.addOrder(property, order);
	}

	/**
	 * Permite agregar un nuevo orden {@link Order#ASC} al filtrado de la consulta.
	 * 
	 * @param property
	 *            La propiedad sobre la que va a ordenarse de manera ascendente la consulta.
	 */
	public void addOrder(String property) {
		this.addOrder(property, Order.ASC);
	}

	/**
	 * Retorna el listado de los ID que excluimos de la consulta.
	 * 
	 * @return El listado de los ID que excluimos de la consulta.
	 */
	public PK[] getExcludeIds() {
		return excludeIds;
	}

	/**
	 * Carga el listado de los ID que excluimos de la consulta.
	 * 
	 * @param excludeIds
	 *            El listado de los ID que excluimos de la consulta.
	 */
	public void setExcludeIds(PK... excludeIds) {
		this.excludeIds = excludeIds;
	}

	/**
	 * Retorna el primer registro que queremos recuperar de la consulta.
	 * 
	 * @return El primer registro que queremos recuperar.
	 */
	public Integer getFirstResult() {
		return firstResult;
	}

	/**
	 * Carga el primer registro que queremos recuperar de la consulta.
	 * 
	 * @param firstResult
	 *            El primer registro que queremos recuperar.
	 */
	public void setFirstResult(Integer firstResult) {
		this.firstResult = firstResult;
	}

	/**
	 * Retorna la cantidad máxima de registros que queremos recuperar de la consulta.
	 * 
	 * @return La cantidad máxima de registros que queremos recuperar de la consulta.
	 */
	public Integer getMaxResult() {
		return maxResult;
	}

	/**
	 * Carga la cantidad máxima de registros que queremos recuperar de la consulta.
	 * 
	 * @param maxResult
	 *            La cantidad máxima de registros que queremos recuperar de la consulta.
	 */
	public void setMaxResult(Integer maxResult) {
		this.maxResult = maxResult;
	}
}