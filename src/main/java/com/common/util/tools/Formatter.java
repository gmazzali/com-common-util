package com.common.util.tools;

/**
 * La clase que vamos a definir con métodos estáticos para el formateo de los diferentes elementos dentro de un sistema, como ser cadenas, fechas,
 * horas o demás elementos.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class Formatter {
	
	/**
	 * La función encargada de tomar un valor entero y agregar una cantidad dada de ceros para completar un tamaño dado.
	 * 
	 * @param value
	 *            El valor entero que vamos a completar con ceros, en caso de ser un valor nulo, se retornará un valor nulo.
	 * @param size
	 *            El tamaño que vamos a completar con ceros.
	 * @return La cadena que contiene el entero recibido y que se encuentra completado con ceros hasta el tamaño recibido.
	 */
	public static String zeroFill(Integer value, Integer size) {

		// Si el valor recibido es nulo, retornamos nulo.
		if (value == null) {
			return null;
		}
		// Si el tamaño es nulo lo cargamos en cero.
		if (size == null || size < 0) {
			size = 0;
		}

		// Convertimos el número en un string.
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
