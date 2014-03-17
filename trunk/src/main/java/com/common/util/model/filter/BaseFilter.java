package com.common.util.model.filter;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;

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
	}

	@Override
	public String toString() {
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