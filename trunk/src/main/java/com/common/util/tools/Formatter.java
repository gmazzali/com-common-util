package com.common.util.tools;

/**
 * La clase que vamos a definir con m�todos est�ticos para el formateo de los diferentes elementos dentro de un sistema, como ser cadenas, fechas,
 * horas o dem�s elementos.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class Formatter {
	
	/**
	 * La funci�n encargada de tomar un valor entero y agregar una cantidad dada de ceros para completar un tama�o dado.
	 * 
	 * @param value
	 *            El valor entero que vamos a completar con ceros, en caso de ser un valor nulo, se retornar� un valor nulo.
	 * @param size
	 *            El tama�o que vamos a completar con ceros.
	 * @return La cadena que contiene el entero recibido y que se encuentra completado con ceros hasta el tama�o recibido.
	 */
	public static String zeroFill(Integer value, Integer size) {

		// Si el valor recibido es nulo, retornamos nulo.
		if (value == null) {
			return null;
		}
		// Si el tama�o es nulo lo cargamos en cero.
		if (size == null || size < 0) {
			size = 0;
		}

		// Convertimos el n�mero en un string.
		String stringNumber = "";
		Integer temporalNumber = Math.abs(value);
		do {
			stringNumber = temporalNumber % 10 + stringNumber;
			temporalNumber /= 10;
		} while (temporalNumber > 0);

		// Completamos con ceros los lugares que falten.
		Integer fill = size - stringNumber.length();
		for (Integer i = 0; i < fill; i++) {
			stringNumber = "0" + stringNumber;
		}

		// Agregamos el signo negativo si hace falta.
		if (value < 0) {
			stringNumber = "-" + stringNumber;
		}

		return stringNumber;
	}
}
