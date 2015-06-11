package com.common.util.business.util;

import com.common.util.business.tool.date.DateUtil;

/**
 * La enumeraci�n que vamos a ocupar para definir la precisi�n con la que se va a manipular las fechas.
 * 
 * <ul>
 * <li>{@link DatePrecisionEnum#YEAR YEAR}</li>
 * <li>{@link DatePrecisionEnum#MONTH MONTH}</li>
 * <li>{@link DatePrecisionEnum#DAY DAY}</li>
 * <li>{@link DatePrecisionEnum#HOUR HOUR}</li>
 * <li>{@link DatePrecisionEnum#MINUTE MINUTE}</li>
 * <li>{@link DatePrecisionEnum#SECOND SECOND}</li>
 * <li>{@link DatePrecisionEnum#MILLISECOND MILLISECOND}</li>
 * </ul>
 * 
 * @see DateUtil
 * 
 * @since 05/02/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public enum DatePrecisionEnum {

	YEAR(1), 
	
	MONTH(2), 
	
	DAY(3), 
	
	HOUR(4), 
	
	MINUTE(5), 
	
	SECOND(6), 
	
	MILLISECOND(7);

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
	DatePrecisionEnum(Integer level) {
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