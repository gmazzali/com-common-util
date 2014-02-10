package com.common.util.tool.engineering;

/**
 * La enumeración que contiene las unidades de temperaturas.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public enum TemperatureUnit {
	CELSIUS("°C"), KELVIN("°K"), FARENHEIT("°F"), RANKINE("°Ra"), REAUMUR("°Re");
	/**
	 * La unidad de medida.
	 */
	private String unit;

	/**
	 * El constructor que recibe los datos de una unidad de temperatura.
	 * 
	 * @param unit
	 *            La unidad de medida.
	 */
	private TemperatureUnit(String unit) {
		this.unit = unit;
	}

	@Override
	public String toString() {
		return this.getUnit();
	}

	/**
	 * Retorna la cadena que contiene la unidad de medida.
	 * 
	 * @return La unidad de medida.
	 */
	public String getUnit() {
		return unit;
	}
}