package com.common.util.tools.date;

/**
 * La enumeración que vamos a ocupar para definir la precisión con la que se va a manipular las fechas.
 * 
 * @see DateUtil
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public enum DatePrecision {
	YEAR(1), MONTH(2), DAY(3), HOUR(4), MINUTE(5), SECOND(6), MILLISECOND(7);
	/**
	 * El nivel de precisión.
	 */
	private Integer level;

	/**
	 * Constructor de una precisión para una fecha que recibe su nivel.
	 * 
	 * @param level
	 *            El nivel de precisión.
	 */
	DatePrecision(Integer level) {
		this.level = level;
	}

	/**
	 * Retorna el nivel de precisión.
	 * 
	 * @return El nivel de precisión.
	 */
	public Integer getLevel() {
		return this.level;
	}
}