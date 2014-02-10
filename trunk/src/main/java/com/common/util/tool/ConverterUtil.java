package com.common.util.tool;

import com.common.util.exception.UncheckedException;

public class ConverterUtil {

	/**
	 * El conjunto de los nombres de los grupos de unidades desde el cero al 29.
	 * 
	 * <ul>
	 * <li>00: cero</li>
	 * <li>01: uno</li>
	 * <li>02: dos</li>
	 * <li>03: tres</li>
	 * <li>04: cuatro</li>
	 * <li>05: cinco</li>
	 * <li>06: seis</li>
	 * <li>07: siete</li>
	 * <li>08: ocho</li>
	 * <li>09: nueve</li>
	 * <li>10: diez</li>
	 * <li>11: once</li>
	 * <li>12: doce</li>
	 * <li>13: trece</li>
	 * <li>14: catorce</li>
	 * <li>15: quince</li>
	 * <li>16: dieciseis</li>
	 * <li>17: diecisiete</li>
	 * <li>18: dieciocho</li>
	 * <li>19: diecinueve</li>
	 * <li>20: veinte</li>
	 * <li>21: veintiun</li>
	 * <li>22: veintidos</li>
	 * <li>23: veintitres</li>
	 * <li>24: veinticuatro</li>
	 * <li>25: veinticinco</li>
	 * <li>26: veintiseis</li>
	 * <li>27: veintisiete</li>
	 * <li>28: veintiocho</li>
	 * <li>29: veintinueve</li>
	 * </ul>
	 */
	private static String[] unitsGroup =
		{ "cero", "uno", "dos", "tres", "cuatro", "cinco", "seis", "siete", "ocho", "nueve", "diez", "once", "doce", "trece", "catorce", "quince",
				"dieciseis", "diecisiete", "dieciocho", "diecinueve", "veinte", "veintiun", "veintidos", "veintitres", "veinticuatro", "veinticinco",
				"veintiseis", "veintisiete", "veintiocho", "veintinueve" };

	/**
	 * El conjunto de los nombres de los grupos de decenas.
	 * 
	 * <ul>
	 * <li>0: cero</li>
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
		{ "cero", "diez", "veinte", "treinta", "cuarenta", "cincuenta", "sesenta", "setenta", "ochenta", "noventa" };

	/**
	 * La frase que vamos a usar para conectar la parte de la decena con la parte de la unidad.
	 */
	private static String tenConnector = "y";

	/**
	 * El conjunto de los nombres de los grupos de centenas.
	 * 
	 * <ul>
	 * <li>0: cien</li>
	 * <li>1: cientos</li>
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
		{ "cien", "ciento", "doscientos", "trescientos", "cuatrocientos", "quinientos", "seiscientos", "setecientos", "ochocientos", "novecientos" };

	/**
	 * El conjunto de los nombres de los grupos de miles en singular.
	 * 
	 * <ul>
	 * <li>0: mil</li>
	 * <li>1: millón</li>
	 * <li>2: billón</li>
	 * <li>3: trillón</li>
	 * <li>4: cuatrillón</li>
	 * <li>5: quintillón</li>
	 * </ul>
	 */
	private static String[] millonsGroup =
		{ "mil", "millón", "billón", "trillón", "cuatrillón", "quintillón" };

	/**
	 * El conjunto de los nombres de los grupos de miles en plural.
	 * 
	 * <ul>
	 * <li>0: miles</li>
	 * <li>1: millones</li>
	 * <li>2: billones</li>
	 * <li>3: trillones</li>
	 * <li>4: cuatrillones</li>
	 * <li>5: quintillones</li>
	 * </ul>
	 */
	private static String[] pluralMillonsGroup =
		{ "miles", "millones", "billones", "trillones", "cuatrillones", "quintillones" };

	/**
	 * Permite cargar el listado de los nombre de los grupos de las unidades desde el 0 al 29.
	 * 
	 * @param unitsGroup
	 *            El arreglo con los nombres de los grupos de las unidades desde el 0 al 29.
	 */
	public void setUnitsGroup(String[] unitsGroup) {
		ConverterUtil.unitsGroup = unitsGroup;
	}

	/**
	 * Permite cargar el listado de los nombre de los grupos de decenas.
	 * 
	 * @param tensGroup
	 *            El arreglo con los nombres de los grupos de decenas.
	 */
	public void setTensGroup(String[] tensGroup) {
		ConverterUtil.tensGroup = tensGroup;
	}

	/**
	 * Permite cargar el conector que vamos a usar para conectar la parte de la decena con la parte de la unidad.
	 * 
	 * @param tenConnector
	 *            El conector que vamos a usar para conectar la parte de la decena con la parte de la unidad.
	 */
	public static void setTenConnector(String tenConnector) {
		ConverterUtil.tenConnector = tenConnector;
	}

	/**
	 * Permite cargar el listado de los nombre de los grupos de cientos.
	 * 
	 * @param hundredsGroup
	 *            El arreglo con los nombres de los grupos de cientos.
	 */
	public void setHundredsGroup(String[] hundredsGroup) {
		ConverterUtil.hundredsGroup = hundredsGroup;
	}

	/**
	 * Permite cargar el listado de los nombre de los grupos de millones (en singular) para poder convertir los valores mayores al millón.
	 * 
	 * @param millonsGroup
	 *            El arreglo con los nombres de los grupos de millones (en singular).
	 */
	public void setMillonsGroup(String[] millonsGroup) {
		ConverterUtil.millonsGroup = millonsGroup;
	}

	/**
	 * Permite cargar el listado de los nombre de los grupos de millones (en plural) para poder convertir los valores mayores al millón.
	 * 
	 * @param pluralMillonsGroup
	 *            El arreglo con los nombres de los grupos de millones (en plural).
	 */
	public void setPluralMillonsGroup(String[] pluralMillonsGroup) {
		ConverterUtil.pluralMillonsGroup = pluralMillonsGroup;
	}

	/**
	 * Convierte un valor entre [0-999] en una cadena de texto que lo represente.
	 * 
	 * <ul>
	 * <li><b>INPUT:</b> <i>null</i> -> <b>OUTPUT:</b> ""</li>
	 * <li><b>INPUT:</b> 0 -> <b>OUTPUT:</b> ""</li>
	 * <li><b>INPUT:</b> 1 -> <b>OUTPUT:</b> "uno"</li>
	 * <li><b>INPUT:</b> 12 -> <b>OUTPUT:</b> "doce"</li>
	 * <li><b>INPUT:</b> 100 -> <b>OUTPUT:</b> "cien"</li>
	 * <li><b>INPUT:</b> 123 -> <b>OUTPUT:</b> "ciento veintitres"</li>
	 * <li><b>INPUT:</b> 500 -> <b>OUTPUT:</b> "quinientos"</li>
	 * <li><b>INPUT:</b> 999 -> <b>OUTPUT:</b> "novecientos noventa y nueve"</li>
	 * <li><b>INPUT:</b> 1000 -> <b>OUTPUT:</b> ""</li>
	 * <li><b>INPUT:</b> 1234 -> <b>OUTPUT:</b> ""</li>
	 * </ul>
	 * 
	 * @param value
	 *            El valor que vamos a convertir a la cadena, el cual no puede exceder los 1000.
	 * @return La cadena de texto que contiene la descripción del numero recibido.
	 */
	public static String convertShortToString(Short value) {
		String output = "";

		// Si el valor es nulo, es menor que cero o mayor que 1000, retornamos una cadena vacia.
		if (value == null || value <= 0 || value >= 1000) {
			return output;
		} else {
			// Obtenemos la centena, la decena y la unidad del número.
			int unidad = value % 10;
			int decena = (value % 100) / 10;
			int centena = (value / 100) % 10;
			output = "";

			if (centena > 0) {
				output = ConverterUtil.hundredsGroup[centena];

				// Si solo hay valores de la centena.
				if (decena == 0 && unidad == 0) {

					// Si el valor es 100.
					if (centena == 1) {
						return ConverterUtil.hundredsGroup[0];
					} else {
						return ConverterUtil.hundredsGroup[centena];
					}
				}
			}

			switch (decena) {
				case 0:
				case 1:
				case 2:
					if (unidad == 0) {
						output += " " + ConverterUtil.tensGroup[decena];
					} else {
						output += " " + ConverterUtil.unitsGroup[10 * decena + unidad];
					}
					break;

				default:
					if (unidad == 0) {
						output += " " + ConverterUtil.tensGroup[decena];
					} else {
						output += " " + ConverterUtil.tensGroup[decena] + " " + ConverterUtil.tenConnector
								+ (VerifierUtil.isEmpty(ConverterUtil.tenConnector) ? "" : " ") + ConverterUtil.unitsGroup[unidad];
					}
					break;
			}
		}

		return output.trim();
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
				// switch (segundoMillar) {
				// case 0:
				// // Si tenemos un fragmento < 1000.
				// output = ConverterUtil.convertThousandToWord(primerMillar) + nombreGrupo + output;
				// break;
				//
				// case 1:
				// // Si tenemos un fragmento entre [1000-1999].
				// output = ConverterUtil.millonsGroup[0] + " " + ConverterUtil.convertThousandToWord(primerMillar) + nombreGrupo + output;
				// break;
				//
				// default:
				// // Si tenemos un fragmento > 1000.
				// output = ConverterUtil.convertThousandToWord(segundoMillar) + ConverterUtil.millonsGroup[0] + " "
				// + ConverterUtil.convertThousandToWord(primerMillar) + nombreGrupo + output;
				// break;
				// }
			}

			// Tomamos el proximo grupo de valores.
			integer /= 1000000;
			grupo++;
		}

		return output;
	}
}
