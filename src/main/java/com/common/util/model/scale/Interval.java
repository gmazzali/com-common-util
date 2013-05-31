package com.common.util.model.scale;

import com.common.util.exception.UncheckedException;
import com.common.util.model.Entity;

/**
 * La clase que define un intervalo de valores entre un valor mínimo y uno máximo. Corresponde a un intervalo del tipo [minValue; maxValue). En caso
 * de que los valores que tenemos almacenados sean iguales, esto significa que se trata de dicho valor definido como un punto aislado de los demás, es
 * decir, cuando minValue == maxValue => [minValue; maxValue] y si dicho intervalo tiene un intervalo siguiente, este valor se repite dentro de ese
 * intervalo de manera de no romper el esquema original.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <N>
 *            La clase de los valores númericos que vamos a manejar dentro de este intervalo.
 * 
 *            TODO Falta lo de los extremos abiertos o cerrados.
 */
public class Interval<N extends Number> extends Entity<Long> implements Comparable<Interval<N>> {

	private static final long serialVersionUID = 6260101442754076705L;

	/**
	 * La enumeración que define el estado en el que vamos a tener los extremos de dicho intervalo para ver si estos son abiertos o cerrados.
	 * 
	 * @author Guillermo Mazzali
	 * @version 1.0
	 */
	public enum ExtremeState {
		OPEN, CLOSE;
	}

	/**
	 * @see Entity.Attributes
	 */
	public interface Attributes extends Entity.Attributes {
		final String MIN_VALUE = "minValue";
		final String MAX_VALUE = "maxValue";
	}

	/**
	 * El estado del extremo inferior del intervalo.
	 */
	protected ExtremeState minExtremeState;
	/**
	 * El valor mínimo del intervalo.
	 */
	protected N minValue;
	/**
	 * El valor máximo del intervalo.
	 */
	protected N maxValue;
	/**
	 * El estado del extremo superior del intervalo.
	 */
	protected ExtremeState maxExtremeState;

	/**
	 * EL constructor por copia de los elementos que tenemos.
	 * 
	 * @param min
	 *            El valor mínimo del intervalo.
	 * @param max
	 *            El valor máximo del intervalo.
	 */
	public Interval(N min, N max) {
		this.minValue = min;
		this.maxValue = max;
	}

	/**
	 * La función encargada de validar el contenido del intervalo.
	 */
	public void validateInterval() {
		// Validamos que el intervalo sea correcto.
		if (this.minValue == null) {
			throw new UncheckedException("inteval.value.min.null");
		}

		if (this.maxValue == null) {
			throw new UncheckedException("inteval.value.max.null");
		}

		if (this.minValue.doubleValue() > this.maxValue.doubleValue()) {
			throw new UncheckedException("inteval.value.invert");
		}
	}

	/**
	 * La función encargada de verificar que 2 intervalos sean iguales.
	 * 
	 * @see com.common.util.model.Entity#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object otherInterval) {
		if (otherInterval == null) {
			return false;
		}
		if (this.getClass() != otherInterval.getClass()) {
			return false;
		}
		@SuppressWarnings("unchecked")
		final Interval<N> interval = (Interval<N>) otherInterval;
		if (this.minValue.equals(interval.minValue) && this.maxValue.equals(interval.maxValue)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * La función encargada de comparar este intervalo con otro para poder ordenarlos dentro de la escala.
	 * 
	 * @param otherInterval
	 *            El otro intervalo para comparar.
	 * @return <b>1</b> si el valor mínimo de este intervalo es mayor al valor mínimo del segundo intervalo, <b>-1</b> si el valor mínimo de este
	 *         intervalo es menor que el valor mínimo del segundo intervalo o <b>0</b> en caso de que los 2 intervalos tengan el mismo valor mínimo.
	 */
	@Override
	public int compareTo(Interval<N> otherInterval) {
		if (this.minValue.doubleValue() > otherInterval.minValue.doubleValue()) {
			return 1;
		} else if (this.minValue.doubleValue() < otherInterval.minValue.doubleValue()) {
			return -1;
		} else {
			return 0;
		}
	}

	/**
	 * La función encargada de setear el valor mínimo del intervalo.
	 * 
	 * @param minValue
	 *            El valor mínimo del intervalo.
	 */
	public void setMinValue(N minValue) {
		this.minValue = minValue;
	}

	/**
	 * La función encargada de retornar el valor mínimo del intervalo.
	 * 
	 * @return El valor mínimo del intervalo.
	 */
	public N getMinValue() {
		return this.minValue;
	}

	/**
	 * La función encargada de setear el valor máximo del intervalo.
	 * 
	 * @param maxValue
	 *            El valor máximo del intervalo.
	 */
	public void setMaxValue(N maxValue) {
		this.maxValue = maxValue;
	}

	/**
	 * La función encargada de retornar el valor máximo del intervalo.
	 * 
	 * @return El valor máximo del intervalo.
	 */
	public N getMaxValue() {
		return this.maxValue;
	}
}
