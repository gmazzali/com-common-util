package com.common.util.tools.date;

/**
 * La enumeraci�n que vamos a ocupar para definir la precisi�n con la que se va a manipular las fechas.
 * 
 * @see DateUtil
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public enum DatePrecision {
	YEAR(1), MONTH(2), DAY(3), HOUR(4), MINUTE(5), SECOND(6), MILLISECOND(7);
	/**
	 * El nivel de precisi�n.
	 */
	private Integer level;

	/**
	 * Constructor de una precisi�n para una fecha que recibe su nivel.
	 * 
	 * @param level
	 *            El nivel de precisi�n.
	 */
	DatePrecision(Integer level) {
		this.level = level;
	}

	/**
	 * Retorna el nivel de precisi�n.
	 * 
	 * @return El nivel de precisi�n.
	 */
	public Integer getLevel() {
		return this.level;
	}
}