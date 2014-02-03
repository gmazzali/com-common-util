package com.common.util.model.scale;

import org.apache.log4j.Logger;

import com.common.util.exception.UncheckedException;
import com.common.util.exception.error.Errors;
import com.common.util.model.Entity;

/**
 * La clase que define un intervalo de valores entre un valor mínimo y uno máximo. En caso de que los valores que tenemos almacenados sean iguales,
 * esto significa que se trata de dicho valor definido como un punto aislado de los demás, es decir, cuando minValue == maxValue => [minValue;
 * maxValue] y si dicho intervalo tiene un intervalo siguiente, este valor se repite dentro de ese intervalo de manera de no romper el esquema
 * original.
 * 
 * @see Scale
 * @see Extreme
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <N>
 *            La clase de los valores númericos que vamos a manejar dentro de este intervalo.
 */
public class Interval<N extends Number> extends Entity<Long> implements Comparable<Interval<N>> {
	private static final long serialVersionUID = 1L;

	/**
	 * El Logger que vamos a ocupar dentro de la clase.
	 */
	private static final Logger log = Logger.getLogger(Interval.class);

	/**
	 * @see Entity.Attributes
	 */
	public interface Attributes extends Entity.Attributes {
		final String MIN_EXTREME = "minExtreme";
		final String MIN_VALUE = "minValue";
		final String MAX_VALUE = "maxValue";
		final String MAX_EXTREME = "maxExtreme";
	}

	/**
	 * El extremo inferior.
	 */
	protected final Extreme minExtreme;
	/**
	 * El valor del extremo inferior.
	 */
	protected final N minValue;
	/**
	 * El valor del extremo superior.
	 */
	protected final N maxValue;
	/**
	 * El extremo superior.
	 */
	protected final Extreme maxExtreme;

	/**
	 * El constructor por copia del intervalo que recibimos.
	 * 
	 * @param interval
	 *            El intervalo que recibimos para hacer una copia.
	 */
	public Interval(Interval<N> interval) {
		this(interval.minExtreme, interval.minValue, interval.maxValue, interval.maxExtreme);
	}

	/**
	 * El constructor de un intervalo del tipo [minValue; maxValue)
	 * 
	 * @param minValue
	 *            El valor del extremo inferior.
	 * @param maxValue
	 *            El valor del extremo superior.
	 */
	public Interval(N minValue, N maxValue) {
		this(Extreme.CLOSE, minValue, maxValue, Extreme.OPEN);
	}

	/**
	 * El constructor por copia de todos los elementos.
	 * 
	 * @param minExtreme
	 *            El estado del extremo inferior.
	 * @param minValue
	 *            El valor del extremo inferior.
	 * @param maxValue
	 *            El valor del extremo superior.
	 * @param maxExtreme
	 *            El estado del extremo superior.
	 */
	public Interval(Extreme minExtreme, N minValue, N maxValue, Extreme maxExtreme) {
		super();

		Interval.log.trace("create");

		this.minExtreme = minExtreme;
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.maxExtreme = maxExtreme;
	}

	/**
	 * Permite crear un nuevo intervalo a partir de los datos de este intervalo.
	 * 
	 * @return El intervalo clonado de este.
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new Interval<N>(this);
	}

	/**
	 * Retorna una cadena de texto conteniendo las propiedades de este intervalo.
	 * 
	 * @return La cadena de texto que contiene las propiedades de este intervalo.
	 */
	@Override
	public String toString() {
		String interval = "";
		interval += this.minExtreme == null ? "X" : this.minExtreme == Extreme.OPEN ? "(" : "[";
		interval += this.maxValue + "; " + this.maxValue;
		interval += this.maxExtreme == null ? "X" : this.maxExtreme == Extreme.OPEN ? ")" : "]";
		return interval;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((minExtreme == null) ? 0 : minExtreme.hashCode());
		result = prime * result + ((minValue == null) ? 0 : minValue.hashCode());
		result = prime * result + ((maxValue == null) ? 0 : maxValue.hashCode());
		result = prime * result + ((maxExtreme == null) ? 0 : maxExtreme.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Interval<?> other = (Interval<?>) obj;
		if (minExtreme != other.minExtreme) {
			return false;
		}
		if (minValue == null) {
			if (other.minValue != null) {
				return false;
			}
		} else if (!minValue.equals(other.minValue)) {
			return false;
		}
		if (maxValue == null) {
			if (other.maxValue != null) {
				return false;
			}
		} else if (!maxValue.equals(other.maxValue)) {
			return false;
		}
		if (maxExtreme != other.maxExtreme) {
			return false;
		}
		return true;
	}

	/**
	 * Se encarga de comparar este intervalo con otro para poder ordenarlos dentro de una escala.
	 * 
	 * @param otherInterval
	 *            El otro intervalo para comparar.
	 * @return <b>1</b> si el valor medio de este intervalo es mayor al valor medio del segundo intervalo, <b>-1</b> si el valor medio de este
	 *         intervalo es menor que el valor medio del segundo intervalo o <b>0</b> en caso de que los dos intervalos tengan el mismo valor medio.
	 */
	@Override
	public int compareTo(Interval<N> otherInterval) {
		Double myMidValue = this.maxValue.doubleValue() + this.minValue.doubleValue() / 2;
		Double otherMidValue = otherInterval.maxValue.doubleValue() + otherInterval.minValue.doubleValue() / 2;

		if (myMidValue > otherMidValue) {
			return 1;
		} else if (myMidValue < otherMidValue) {
			return -1;
		} else {
			return 0;
		}
	}

	/**
	 * Se encarga de validar el contenido del intervalo.
	 */
	public void validateInterval() {
		Interval.log.trace("validate");

		Errors errors = new Errors();

		// Validamos el extremos y el valor inferior.
		if (this.minExtreme == null) {
			errors.addError("interval.min.extreme.null", new Object[] { this.id });
		}

		if (this.minValue == null) {
			errors.addError("interval.min.value.null", new Object[] { this.id });
		}

		// Validamos el valor y el extremo superior.
		if (this.maxValue == null) {
			errors.addError("interval.max.value.null", new Object[] { this.id });
		}

		if (this.minExtreme == null) {
			errors.addError("interval.min.extreme.null", new Object[] { this.id });
		}

		// Validamos que los extremos no esten invertidos.
		if (this.minValue.doubleValue() > this.maxValue.doubleValue()) {
			errors.addError("interval.extreme.invert", new Object[] { this.id });
		}

		if (errors.hasErrorsDetails()) {
			throw new UncheckedException(errors);
		}
	}

	/**
	 * Permite verificar que un valor dado se encuentre dentro de este intervalo dado.
	 * 
	 * @param value
	 *            El valor que vamos a verificar si se encuentra dentro de este intervalo.
	 * @return <i>true</i> en caso de que el valor recibido se encuentre dentro de este intervalo, en caso de que no se encuentre o el mismo sea nulo,
	 *         se retorna <i>false</i>.
	 */
	public boolean hasInclude(N value) {
		Interval.log.trace("has include");

		boolean include = true;

		if (value == null) {
			return false;
		}

		switch (this.minExtreme) {
		case CLOSE:
			include &= value.doubleValue() >= this.minValue.doubleValue();
			break;

		case OPEN:
			include &= value.doubleValue() > this.minValue.doubleValue();
			break;
		}

		switch (this.maxExtreme) {
		case CLOSE:
			include &= value.doubleValue() <= this.maxValue.doubleValue();
			break;

		case OPEN:
			include &= value.doubleValue() < this.maxValue.doubleValue();
			break;
		}

		return include;
	}

	/**
	 * La función encargada de retornar el estado del extremo inferior de un intervalo.
	 * 
	 * @return El estado del extremo inferior del intervalo.
	 */
	public Extreme getMinExtreme() {
		return minExtreme;
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
	 * La función encargada de retornar el valor máximo del intervalo.
	 * 
	 * @return El valor máximo del intervalo.
	 */
	public N getMaxValue() {
		return this.maxValue;
	}

	/**
	 * La función encargada de retornar el estado del extremo superior de un intervalo.
	 * 
	 * @return El estado del extremo superior del intervalo.
	 */
	public Extreme getMaxExtreme() {
		return maxExtreme;
	}
}