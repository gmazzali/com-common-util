package com.common.util.domain.model.util;

import java.io.Serializable;

/**
 * Permite definir un elemento que contiene un rango de valores para comparaciones.
 * 
 * @since 14/03/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <T>
 *            La clase que va a contener este rango.
 */
public class RangeType<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * El valor mínimo del intervalo.
	 */
	private T from;
	/**
	 * El valor máximo del intervalo.
	 */
	private T to;

	/**
	 * El constructor de un rango.
	 */
	public RangeType() {
		super();
	}

	/**
	 * El constructor que recibe los parámetros del intervalo.
	 * 
	 * @param from
	 *            El valor mínimo del intervalo.
	 * @param to
	 *            El valor máximo del intervalo.
	 */
	public RangeType(T from, T to) {
		this();
		this.from = from;
		this.to = to;
	}

	/**
	 * Retorna el valor mínimo del intervalo.
	 * 
	 * @return El valor mínimo del intervalo.
	 */
	public T getFrom() {
		return from;
	}

	/**
	 * Carga el valor mínimo del intervalo.
	 * 
	 * @param from
	 *            El valor mínimo del intervalo.
	 */
	public void setFrom(T from) {
		this.from = from;
	}

	/**
	 * Retorna el valor maxímo del intervalo.
	 * 
	 * @return El valor maxímo del intervalo.
	 */
	public T getTo() {
		return to;
	}

	/**
	 * Carga el valor maxímo del intervalo.
	 * 
	 * @param to
	 *            El valor maxímo del intervalo.
	 */
	public void setTo(T to) {
		this.to = to;
	}

	/**
	 * Verifica que el intervalo este vacío.
	 * 
	 * @return <i>true</i> en caso de que el intervalo tenga los 2 valores <code>null</code>, en caso contrario, retornamos <i>false</i>.
	 */
	public boolean isEmpty() {
		return this.from == null && this.to == null;
	}
}