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
	 * El valor m�nimo del intervalo.
	 */
	private T from;
	/**
	 * El valor m�ximo del intervalo.
	 */
	private T to;

	/**
	 * El constructor de un rango.
	 */
	public RangeType() {
		super();
	}

	/**
	 * El constructor que recibe los par�metros del intervalo.
	 * 
	 * @param from
	 *            El valor m�nimo del intervalo.
	 * @param to
	 *            El valor m�ximo del intervalo.
	 */
	public RangeType(T from, T to) {
		this();
		this.from = from;
		this.to = to;
	}

	/**
	 * Retorna el valor m�nimo del intervalo.
	 * 
	 * @return El valor m�nimo del intervalo.
	 */
	public T getFrom() {
		return from;
	}

	/**
	 * Carga el valor m�nimo del intervalo.
	 * 
	 * @param from
	 *            El valor m�nimo del intervalo.
	 */
	public void setFrom(T from) {
		this.from = from;
	}

	/**
	 * Retorna el valor max�mo del intervalo.
	 * 
	 * @return El valor max�mo del intervalo.
	 */
	public T getTo() {
		return to;
	}

	/**
	 * Carga el valor max�mo del intervalo.
	 * 
	 * @param to
	 *            El valor max�mo del intervalo.
	 */
	public void setTo(T to) {
		this.to = to;
	}

	/**
	 * Verifica que el intervalo este vac�o.
	 * 
	 * @return <i>true</i> en caso de que el intervalo tenga los 2 valores <code>null</code>, en caso contrario, retornamos <i>false</i>.
	 */
	public boolean isEmpty() {
		return this.from == null && this.to == null;
	}
}