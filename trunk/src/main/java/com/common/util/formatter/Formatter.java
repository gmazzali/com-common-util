package com.common.util.formatter;

/**
 * La clase que vamos a definir con métodos estáticos para el formateo de los diferentes elementos dentro de un sistema, como ser cadenas, fechas,
 * horas o demás elementos.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class Formatter {

	public static String zeroFill(Integer value, Integer size) {

		// Si alguno de los valores son nulos, retornamos un string nulo.
		if (value == null) {
			return null;
		} else if (size == null) {
			size = 0;
		}

		// Convertimos el número en un string.
		String number = "";
		Integer rest = Math.abs(value);
		do {
			number = rest % 10 + number;
			rest /= 10;
		} while (rest > 0);

		// Si el tamaño de la salida tiene que ser mayor al número, lo completamos, sino, retornamos el mismo valor.
		Integer fill = size - number.length();
		for (Integer i = 0; i < fill; i++) {
			number = "0" + number;
		}

		// Si el valor es nulo, le agregamos el signo negátivo a la salida.
		if (value < 0) {
			return "-" + number;
		} else {
			return number;
		}
	}

	public static void main(String[] args) {
		System.out.println(Formatter.zeroFill(1230, 10));
	}
}
