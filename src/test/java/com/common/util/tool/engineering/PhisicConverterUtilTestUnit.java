package com.common.util.tool.engineering;

import junit.framework.Assert;

import org.junit.Test;

/**
 * La clase de pruebas de la clase que permite manipular conversiones físicas.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class PhisicConverterUtilTestUnit {

	/**
	 * La pruebas sobre el método <i><b>convertTemperature</b></i> de {@link PhisicConverterUtil}
	 * 
	 * @see PhisicConverterUtil#convertTemperature(Double, TemperatureUnit, TemperatureUnit)
	 */
	@Test
	public void testConvertTemperature() {

		Double inputTemperature = null;
		Double outputTemperature = null;
		Double expectedTemperature = null;
		TemperatureUnit sourceUnit = null;
		TemperatureUnit targetUnit = null;

		try {
			PhisicConverterUtil.convertTemperature(null, TemperatureUnit.CELSIUS, TemperatureUnit.CELSIUS);
			Assert.fail();
		} catch (Exception e) {
		}

		try {
			PhisicConverterUtil.convertTemperature(10.00, null, TemperatureUnit.CELSIUS);
			Assert.fail();
		} catch (Exception e) {
		}

		try {
			PhisicConverterUtil.convertTemperature(10.00, TemperatureUnit.CELSIUS, null);
			Assert.fail();
		} catch (Exception e) {
		}

		inputTemperature = 0.0;
		expectedTemperature = 0.0;
		sourceUnit = TemperatureUnit.CELSIUS;
		targetUnit = TemperatureUnit.CELSIUS;
		outputTemperature = PhisicConverterUtil.convertTemperature(inputTemperature, sourceUnit, targetUnit);
		Assert.assertEquals(expectedTemperature, outputTemperature, 0);
	}
}