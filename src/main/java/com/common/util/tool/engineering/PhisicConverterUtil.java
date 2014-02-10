package com.common.util.tool.engineering;

import com.common.util.exception.UncheckedException;

/**
 * La clase que contiene las funciones basicas de conversiones físicas.
 * 
 * @see TemperatureUnit
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class PhisicConverterUtil {

	/**
	 * Permite convertir un valor de una unidad de temperatura dada a una unidad de temperatura objectivo.
	 * 
	 * @param sourceTemperature
	 *            El valor que vamos a convertir.
	 * @param sourceUnit
	 *            La unidad en la que se encuentra el valor recibido.
	 * @param targetUnit
	 *            La unidad de temperatura a la que queremos convertir el valor recibido.
	 * @return El valor de la temperatura convertido desde la unidad de origen a la de destino.
	 * @throws UncheckedException
	 *             En caso de que alguno de los parámetros sea nulo.
	 */
	public static Double convertTemperature(Double sourceTemperature, TemperatureUnit sourceUnit, TemperatureUnit targetUnit) {
		// Si el valor es nulo, lanzamos la excepción.
		if (sourceTemperature == null) {
			throw new UncheckedException("The temperature source must don't be null.");
		}
		if (sourceUnit == null) {
			throw new UncheckedException("The temperature unit source must don't be null.");
		}
		if (targetUnit == null) {
			throw new UncheckedException("The temperature unit target must don't be null.");
		}

		Double targetTemperature = null;

		// Si las unidades son iguales, retornamos el valor recibido.
		if (sourceUnit.equals(targetUnit)) {
			targetTemperature = sourceTemperature;

		} else {
			// El valor comun para las conversiones.
			Double farenheitTemperature = null;

			// Covertimos las temperaturas a una unidad en común, como °F.
			switch (sourceUnit) {
			case CELSIUS:
				// °F = °C * 1.8 + 32
				farenheitTemperature = sourceTemperature * 1.8 + 32;
				break;

			case FARENHEIT:
				// °F = °F
				farenheitTemperature = sourceTemperature;
				break;

			case KELVIN:
				// °F = °K * 1.8 - 459.67
				farenheitTemperature = sourceTemperature * 1.8 - 459.67;
				break;

			case RANKINE:
				// °F = °Ra - 459.67
				farenheitTemperature = sourceTemperature - 459.67;
				break;

			case REAUMUR:
				// °F = °Re * 2.25 + 32
				farenheitTemperature = sourceTemperature * 2.25 + 32;
				break;
			}

			// Convertimos de °F a la unidad de destino.
			switch (targetUnit) {
			case CELSIUS:
				// °C = (°F - 32) / 1.8
				targetTemperature = (farenheitTemperature - 32) / 1.8;
				break;

			case FARENHEIT:
				// °F = °F
				targetTemperature = farenheitTemperature;
				break;

			case KELVIN:
				// °K = (°F + 459.67) / 1.8
				targetTemperature = (farenheitTemperature + 459.67) / 1.8;
				break;

			case RANKINE:
				// °Ra = °F + 459.67
				targetTemperature = farenheitTemperature + 459.67;
				break;

			case REAUMUR:
				// °Re = (°F - 32)/2.25
				targetTemperature = (farenheitTemperature - 32) / 2.25;
				break;
			}
		}

		return targetTemperature;
	}
}