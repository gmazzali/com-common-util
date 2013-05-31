package com.common.util.model.scale;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import com.common.util.exception.UncheckedException;
import com.common.util.model.Entity;

/**
 * La clase que va a contener un listado de intervalos para mantener una escala numerada para corroborrar donde caen un valor dado. Los intervalos
 * dentro de esta escala corresponden al estilo [lowValue; highValue), cuyos limites superiores son abiertos y los inferiores cerrados.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <I>
 *            La clase que corresponde a los intervalos que vamos a almacenar dentro de la escala.
 * @param <N>
 *            La clase de números que vamos a almacenar dentro de los intervalos de esta escala.
 */
public class Scale<I extends Interval<N>, N extends Number> extends Entity<Long> {

	private static final long serialVersionUID = -888922840573092976L;
	
	/**
	 * El Logger que vamos a ocupar dentro de la clase.
	 */
	private static final Logger log = Logger.getLogger(Scale.class);

	/**
	 * @see Entity.Attributes
	 */
	public interface Attributes extends Entity.Attributes {
		final String INTERVALS = "intervals";
		final String LOWER_VALUE = "lowerValue";
		final String HIGHER_VALUE = "higherValue";
	}

	/**
	 * El limite inferior de la escala.
	 */
	protected N lowerValue;
	/**
	 * El limite superior de la escala.
	 */
	protected N higherValue;
	/**
	 * El listado de los intervalos de esta escala.
	 */
	protected List<I> intervals;

	/**
	 * El cosntructor por omision de una escala.
	 */
	public Scale() {
		Scale.log.trace("Scale create");

		this.intervals = new ArrayList<I>();
	}

	/**
	 * La función encargada de validar una escala de valores, con sus intervalos.
	 */
	public void validateintervals() {
		Scale.log.trace("Scale validateScale");

		// Si no hay un rango dentro de la escala, retornamos false.
		if (this.intervals.isEmpty()) {
			throw new UncheckedException("scale.interval.empty");
		}

		// Sino, verificamos los rangos.
		for (int i = 0; i < this.intervals.size(); i++) {

			// Primero validamos el rango.
			this.intervals.get(i).validateInterval();

			// Validamos los espacios entre rangos ahora.
			if (i > 0 && i < this.intervals.size() - 1) {

				if (!this.intervals.get(i).getMaxValue().equals(this.intervals.get(i + 1).getMinValue())) {
					throw new UncheckedException("scale.interval.hole");
				}
			}
		}
	}

	/**
	 * Función encargada de cargar el primero y el último valor de la escala.
	 */
	private void setLimitValues() {
		Scale.log.trace("Scale setLimitValues");

		this.lowerValue = this.intervals.get(0).getMinValue();
		this.higherValue = this.intervals.get(this.intervals.size() - 1).getMaxValue();
	}

	/**
	 * La función encargada de verificar si un valor se encuentra dentro del rango de valores que tenemos almacenado.
	 * 
	 * @param value
	 *            El valor que queremos verificar.
	 * @return TRUE en caso de que el valor se encuentre entre los valores límites que tenemos dentro de la escala.
	 */
	public Boolean isIncludeValue(N value) {
		Scale.log.trace("Scale isIncludeValue");

		return value != null && value.doubleValue() >= this.lowerValue.doubleValue() && value.doubleValue() < this.higherValue.doubleValue();
	}

	/**
	 * La función encargada de agregar un nuevo intervalo a la escala.
	 * 
	 * @param interval
	 *            El nuevo intervalo que vamos a agregar.
	 */
	public void addInterval(I interval) {
		Scale.log.trace("Scale addInterval");

		if (interval != null) {
			this.intervals.add(interval);
			Collections.sort(this.intervals);
			this.setLimitValues();
		}
	}

	/**
	 * La función encargada de recuperar el intervalo donde se encuentra un valor recibido.
	 * 
	 * @param value
	 *            El valor que queremos buscar.
	 * @return El intervalo donde esta dicho valor, en caso de no encontrarse se retorna un valor NULL.
	 */
	public I getInterval(N value) {
		Scale.log.trace("Scale getInterval");

		// Si el valor recibido no es nulo.
		if (value != null) {

			// Vemos si el valor esta dentro de la escala, si esta lo buscamos.
			if (this.isIncludeValue(value)) {

				for (I i : this.intervals) {
					if (value.doubleValue() >= i.getMinValue().doubleValue() && value.doubleValue() < i.getMaxValue().doubleValue()) {
						return i;
					}
				}
			}
		}
		return null;
	}

	/**
	 * La función encargada de retornar el valor del limite inferior de la escala.
	 * 
	 * @return El valor del limite inferior de la escala.
	 */
	public N getLowerValue() {
		return this.lowerValue;
	}

	/**
	 * La función encargada de retornar el valor del limite superior de la escala.
	 * 
	 * @return El valor del limite superior de la escala.
	 */
	public N getHigherValue() {
		return this.higherValue;
	}

	/**
	 * La funcion encargada de retornar el listado de los intervalos que hay dentro de la escala.
	 * 
	 * @return El listado de los intervalos.
	 */
	public List<I> getIntervals() {
		return this.intervals;
	}
}
