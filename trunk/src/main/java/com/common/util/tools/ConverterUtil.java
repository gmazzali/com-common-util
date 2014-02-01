package com.common.util.tools;

import com.common.util.exception.UncheckedException;

public class ConverterUtil {

	/**
	 * El conjunto de los nombres de los grupos de unidades.
	 * 
	 * <ul>
	 * <li>0: cero</li>
	 * <li>1: uno</li>
	 * <li>2: dos</li>
	 * <li>3: tres</li>
	 * <li>4: cuatro</li>
	 * <li>5: cinco</li>
	 * <li>6: seis</li>
	 * <li>7: siete</li>
	 * <li>8: ocho</li>
	 * <li>9: nueve</li>
	 * </ul>
	 */
	private static String[] unitsGroup =
		{ "cero", "uno", "dos", "tres", "cuatro", "cinco", "seis", "siete", "ocho", "nueve" };

	private static String[] PRIMER_DECENA =
		{ null, "once", "doce", "trece", "catorce", "quince", "dieciseis", "diecisiete", "dieciocho", "diecinueve" };

	private static String[] SEGUNDA_DECENA =
		{ null, "ventiuno", "ventidos", "ventitres", "venticuatro", "venticinco", "ventiseis", "ventisiete", "ventiocho", "ventinueve" };

	/**
	 * El conjunto de los nombres de los grupos de decenas.
	 * 
	 * <ul>
	 * <li>0: y</li>
	 * <li>1: diez</li>
	 * <li>2: veinte</li>
	 * <li>3: treinta</li>
	 * <li>4: cuarenta</li>
	 * <li>5: cincuenta</li>
	 * <li>6: sesenta</li>
	 * <li>7: setenta</li>
	 * <li>8: ochenta</li>
	 * <li>9: noventa</li>
	 * </ul>
	 */
	private static String[] tensGroup =
		{ "y", "diez", "veinte", "treinta", "cuarenta", "cincuenta", "sesenta", "setenta", "ochenta", "noventa" };

	/**
	 * El conjunto de los nombres de los grupos de centenas.
	 * 
	 * <ul>
	 * <li>0: cientos</li>
	 * <li>1: cien</li>
	 * <li>2: doscientos</li>
	 * <li>3: trescientos</li>
	 * <li>4: cuatrocientos</li>
	 * <li>5: quinientos</li>
	 * <li>6: seiscientos</li>
	 * <li>7: setecientos</li>
	 * <li>8: ochocientos</li>
	 * <li>9: novecientos</li>
	 * </ul>
	 */
	private static String[] hundredsGroup =
		{ "ciento", "cien", "doscientos", "trescientos", "cuatrocientos", "quinientos", "seiscientos", "setecientos", "ochocientos", "novecientos" };

	/**
	 * El conjunto de los nombres de los grupos de miles en singular.
	 * 
	 * <ul>
	 * <li>0: mil</li>
	 * <li>1: millón</li>
	 * <li>2: billón</li>
	 * <li>3: trillón</li>
	 * <li>4: cuatrillón</li>
	 * </ul>
	 */
	private static String[] millonsGroup =
		{ "mil", "millón", "billón", "trillón" };

	/**
	 * El conjunto de los nombres de los grupos de miles en plural.
	 * 
	 * <ul>
	 * <li>0: miles</li>
	 * <li>1: millones</li>
	 * <li>2: billones</li>
	 * <li>3: trillones</li>
	 * <li>4: cuatrillones</li>
	 * </ul>
	 */
	private static String[] pluralMillonsGroup =
		{ "miles", "millones", "billones", "trillones" };

	/**
	 * Permite cargar el listado de los nombre de los grupos de decenas.
	 * 
	 * @param tensGroup
	 *            El arreglo con los nombres de los grupos de decenas.
	 */
	public static void setTensGroup(String[] tensGroup) {
		ConverterUtil.tensGroup = tensGroup;
	}

	/**
	 * Permite cargar el listado de los nombre de los grupos de cientos.
	 * 
	 * @param hundredsGroup
	 *            El arreglo con los nombres de los grupos de cientos.
	 */
	public static void setHundredsGroup(String[] hundredsGroup) {
		ConverterUtil.hundredsGroup = hundredsGroup;
	}

	/**
	 * Permite cargar el listado de los nombre de los grupos de millones (en singular) para poder convertir los valores mayores al millón.
	 * 
	 * @param millonsGroup
	 *            El arreglo con los nombres de los grupos de millones (en singular).
	 */
	public static void setMillonsGroup(String[] millonsGroup) {
		ConverterUtil.millonsGroup = millonsGroup;
	}

	/**
	 * Permite cargar el listado de los nombre de los grupos de millones (en plural) para poder convertir los valores mayores al millón.
	 * 
	 * @param pluralMillonsGroup
	 *            El arreglo con los nombres de los grupos de millones (en plural).
	 */
	public static void setPluralMillonsGroup(String[] pluralMillonsGroup) {
		ConverterUtil.pluralMillonsGroup = pluralMillonsGroup;
	}

	/**
	 * Permite convertir un número entero en una cadena de texto que contiene el nombre del número. Por ejemplo:
	 * 
	 * <pre>
	 * INPUT: 123
	 * OUTPUT: ciento veintitres
	 * </pre>
	 * 
	 * <pre>
	 * INPUT: 1000
	 * OUTPUT: mil
	 * </pre>
	 * 
	 * <pre>
	 * INPUT: 12345
	 * OUTPUT: doce mil trecientos cuarenta y cinco
	 * </pre>
	 * 
	 * <pre>
	 * INPUT: 1111111
	 * OUTPUT: un millon ciento once mil ciento once
	 * </pre>
	 * 
	 * @param value
	 *            El valor que vamos a convertir en la cadena de texto.
	 * @return La cadena de texto que contiene el valor del número recibido.
	 * @throws UncheckedException
	 *             En caso de que el número recibido sea nulo.
	 */
	public static String convertIntegerNumberToWords(final Integer value) {
		// Verificamos que el número recibido no sea nulo.
		if (value == null) {
			throw new UncheckedException("The value don't must be null.");
		}

		Integer integer = value.intValue();

		String output = "";
		int grupo = 0;

		while (integer != 0 && grupo < ConverterUtil.millonsGroup.length) {

			// Cortamos los ultimos 6 digitos del número y lo partimos en miles y cien miles.
			int fragmento = integer % 1000000;
			int primerMillar = fragmento % 1000;
			int segundoMillar = fragmento / 1000;

			// Tomamos el nombre del grupo solo despues del segundo grupo.
			String nombreGrupo = "";
			if (grupo > 0) {
				nombreGrupo = ConverterUtil.millonsGroup[grupo] + " ";
				if (fragmento > 1) {
					nombreGrupo = ConverterUtil.pluralMillonsGroup[grupo] + " ";
				}
			}

			if (segundoMillar != 0 || primerMillar != 0) {
				switch (segundoMillar) {
					case 0:
						// Si tenemos un fragmento < 1000.
						output = ConverterUtil.convertThousandToWord(primerMillar) + nombreGrupo + output;
						break;

					case 1:
						// Si tenemos un fragmento entre [1000-1999].
						output = ConverterUtil.millonsGroup[0] + " " + ConverterUtil.convertThousandToWord(primerMillar) + nombreGrupo + output;
						break;

					default:
						// Si tenemos un fragmento > 1000.
						output = ConverterUtil.convertThousandToWord(segundoMillar) + ConverterUtil.millonsGroup[0] + " "
								+ ConverterUtil.convertThousandToWord(primerMillar) + nombreGrupo + output;
						break;
				}
			}

			// Tomamos el proximo grupo de valores.
			integer /= 1000000;
			grupo++;
		}

		return output;
	}

	/**
	 * Convierte un valor entre [0-999] en su cadena de texto equivalente.
	 * 
	 * @param value
	 *            El valor que vamos a convertir a la cadena.
	 * @return La cadena de texto que contiene el detalle del numero recibido.
	 */
	private static String convertThousandToWord(Integer value) {
		String salida = "";

		// Si el valor es cero, retornamos una cadena vacia.
		if (value == 0) {
			return salida;
		} else {
			// Obtenemos la centena, la decena y la unidad del número.
			int centena = value / 100;
			int decena = (value % 100) / 10;
			int unidad = value % 10;

			// Si la unidad es cero, comenzamos con las decenas.
			if (unidad == 0) {
				if (decena > 0) {
					salida = ConverterUtil.tensGroup[decena];
				}
			} else {
				// Si la unidad no es cero, comenzamos con la unidad y la decena.
				switch (decena) {
					case 0:
						// Si el valor esta entre [1-9].
						salida = ConverterUtil.unitsGroup[unidad];
						break;

					case 1:
						// Si el valor esta entre [10-19].
						salida = ConverterUtil.PRIMER_DECENA[unidad];
						break;

					case 2:
						// Si el valor esta entre [20-29].
						salida = ConverterUtil.SEGUNDA_DECENA[unidad];
						break;

					default:
						// Si el valor es > 29.
						salida = ConverterUtil.tensGroup[decena] + " " + ConverterUtil.tensGroup[0]
								+ (ConverterUtil.tensGroup[0].isEmpty() ? "" : " ") + ConverterUtil.unitsGroup[unidad];
						break;
				}
			}

			switch (centena) {
				case 0:
					// Si tenemos un valor entre [1-99].
					break;

				case 1:
					// Si tenemos un valor entre [100-199].
					if (VerifierUtil.isEmpty(salida)) {
						// Si el valor es 100.
						salida = ConverterUtil.hundredsGroup[1];
					} else {
						// Si el valor esta entre [101-199]
						salida = ConverterUtil.hundredsGroup[0] + " " + salida;
					}

				default:
					// Si tenemos un valor entre [200-999].
					salida = ConverterUtil.hundredsGroup[centena] + " " + salida;
			}
		}
		return salida + " ";
	}

	public static String formatDecimal(double n) {
		// Tomamos solo 2 valores.
		int decimal = (int) ((n * 100) % 100);

		if (decimal > 0) {
			if (decimal < 10) {
				return " con 0" + decimal + "/100";
			} else {
				return " con " + decimal + "/100";
			}
		} else {
			return "";
		}
	}
}
