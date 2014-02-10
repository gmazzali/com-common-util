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

		// DESDE CELSIUS
		inputTemperature = 0.0;
		expectedTemperature = 0.0;
		sourceUnit = TemperatureUnit.CELSIUS;
		targetUnit = TemperatureUnit.CELSIUS;
		outputTemperature = PhisicConverterUtil.convertTemperature(inputTemperature, sourceUnit, targetUnit);
		Assert.assertEquals(expectedTemperature, outputTemperature, 0.00000001);

		inputTemperature = 100.0;
		expectedTemperature = 100.0;
		sourceUnit = TemperatureUnit.CELSIUS;
		targetUnit = TemperatureUnit.CELSIUS;
		outputTemperature = PhisicConverterUtil.convertTemperature(inputTemperature, sourceUnit, targetUnit);
		Assert.assertEquals(expectedTemperature, outputTemperature, 0.00000001);

		inputTemperature = 0.0;
		expectedTemperature = 32.0;
		sourceUnit = TemperatureUnit.CELSIUS;
		targetUnit = TemperatureUnit.FARENHEIT;
		outputTemperature = PhisicConverterUtil.convertTemperature(inputTemperature, sourceUnit, targetUnit);
		Assert.assertEquals(expectedTemperature, outputTemperature, 0.00000001);

		inputTemperature = 100.0;
		expectedTemperature = 212.0;
		sourceUnit = TemperatureUnit.CELSIUS;
		targetUnit = TemperatureUnit.FARENHEIT;
		outputTemperature = PhisicConverterUtil.convertTemperature(inputTemperature, sourceUnit, targetUnit);
		Assert.assertEquals(expectedTemperature, outputTemperature, 0.00000001);

		inputTemperature = 0.0;
		expectedTemperature = 273.15;
		sourceUnit = TemperatureUnit.CELSIUS;
		targetUnit = TemperatureUnit.KELVIN;
		outputTemperature = PhisicConverterUtil.convertTemperature(inputTemperature, sourceUnit, targetUnit);
		Assert.assertEquals(expectedTemperature, outputTemperature, 0.00000001);

		inputTemperature = 100.0;
		expectedTemperature = 373.15;
		sourceUnit = TemperatureUnit.CELSIUS;
		targetUnit = TemperatureUnit.KELVIN;
		outputTemperature = PhisicConverterUtil.convertTemperature(inputTemperature, sourceUnit, targetUnit);
		Assert.assertEquals(expectedTemperature, outputTemperature, 0.00000001);

		inputTemperature = 0.0;
		expectedTemperature = 491.67;
		sourceUnit = TemperatureUnit.CELSIUS;
		targetUnit = TemperatureUnit.RANKINE;
		outputTemperature = PhisicConverterUtil.convertTemperature(inputTemperature, sourceUnit, targetUnit);
		Assert.assertEquals(expectedTemperature, outputTemperature, 0.00000001);

		inputTemperature = 100.0;
		expectedTemperature = 671.67;
		sourceUnit = TemperatureUnit.CELSIUS;
		targetUnit = TemperatureUnit.RANKINE;
		outputTemperature = PhisicConverterUtil.convertTemperature(inputTemperature, sourceUnit, targetUnit);
		Assert.assertEquals(expectedTemperature, outputTemperature, 0.00000001);

		inputTemperature = 0.0;
		expectedTemperature = 0.0;
		sourceUnit = TemperatureUnit.CELSIUS;
		targetUnit = TemperatureUnit.REAUMUR;
		outputTemperature = PhisicConverterUtil.convertTemperature(inputTemperature, sourceUnit, targetUnit);
		Assert.assertEquals(expectedTemperature, outputTemperature, 0.00000001);

		inputTemperature = 100.0;
		expectedTemperature = 80.0;
		sourceUnit = TemperatureUnit.CELSIUS;
		targetUnit = TemperatureUnit.REAUMUR;
		outputTemperature = PhisicConverterUtil.convertTemperature(inputTemperature, sourceUnit, targetUnit);
		Assert.assertEquals(expectedTemperature, outputTemperature, 0.00000001);
		
		// DESDE FARENHEIT
		inputTemperature = 0.0;
		expectedTemperature = -17.77777778;
		sourceUnit = TemperatureUnit.FARENHEIT;
		targetUnit = TemperatureUnit.CELSIUS;
		outputTemperature = PhisicConverterUtil.convertTemperature(inputTemperature, sourceUnit, targetUnit);
		Assert.assertEquals(expectedTemperature, outputTemperature, 0.00000001);

		inputTemperature = 100.0;
		expectedTemperature = 37.77777778;
		sourceUnit = TemperatureUnit.FARENHEIT;
		targetUnit = TemperatureUnit.CELSIUS;
		outputTemperature = PhisicConverterUtil.convertTemperature(inputTemperature, sourceUnit, targetUnit);
		Assert.assertEquals(expectedTemperature, outputTemperature, 0.00000001);

		inputTemperature = 0.0;
		expectedTemperature = 0.0;
		sourceUnit = TemperatureUnit.FARENHEIT;
		targetUnit = TemperatureUnit.FARENHEIT;
		outputTemperature = PhisicConverterUtil.convertTemperature(inputTemperature, sourceUnit, targetUnit);
		Assert.assertEquals(expectedTemperature, outputTemperature, 0.00000001);

		inputTemperature = 100.0;
		expectedTemperature = 100.0;
		sourceUnit = TemperatureUnit.FARENHEIT;
		targetUnit = TemperatureUnit.FARENHEIT;
		outputTemperature = PhisicConverterUtil.convertTemperature(inputTemperature, sourceUnit, targetUnit);
		Assert.assertEquals(expectedTemperature, outputTemperature, 0.00000001);

		inputTemperature = 0.0;
		expectedTemperature = 255.37222222;
		sourceUnit = TemperatureUnit.FARENHEIT;
		targetUnit = TemperatureUnit.KELVIN;
		outputTemperature = PhisicConverterUtil.convertTemperature(inputTemperature, sourceUnit, targetUnit);
		Assert.assertEquals(expectedTemperature, outputTemperature, 0.00000001);

		inputTemperature = 100.0;
		expectedTemperature = 310.92777778;
		sourceUnit = TemperatureUnit.FARENHEIT;
		targetUnit = TemperatureUnit.KELVIN;
		outputTemperature = PhisicConverterUtil.convertTemperature(inputTemperature, sourceUnit, targetUnit);
		Assert.assertEquals(expectedTemperature, outputTemperature, 0.00000001);

		inputTemperature = 0.0;
		expectedTemperature = 459.67;
		sourceUnit = TemperatureUnit.FARENHEIT;
		targetUnit = TemperatureUnit.RANKINE;
		outputTemperature = PhisicConverterUtil.convertTemperature(inputTemperature, sourceUnit, targetUnit);
		Assert.assertEquals(expectedTemperature, outputTemperature, 0.00000001);

		inputTemperature = 100.0;
		expectedTemperature = 559.67;
		sourceUnit = TemperatureUnit.FARENHEIT;
		targetUnit = TemperatureUnit.RANKINE;
		outputTemperature = PhisicConverterUtil.convertTemperature(inputTemperature, sourceUnit, targetUnit);
		Assert.assertEquals(expectedTemperature, outputTemperature, 0.00000001);

		inputTemperature = 0.0;
		expectedTemperature = -14.22222222;
		sourceUnit = TemperatureUnit.FARENHEIT;
		targetUnit = TemperatureUnit.REAUMUR;
		outputTemperature = PhisicConverterUtil.convertTemperature(inputTemperature, sourceUnit, targetUnit);
		Assert.assertEquals(expectedTemperature, outputTemperature, 0.00000001);

		inputTemperature = 100.0;
		expectedTemperature = 30.22222222;
		sourceUnit = TemperatureUnit.FARENHEIT;
		targetUnit = TemperatureUnit.REAUMUR;
		outputTemperature = PhisicConverterUtil.convertTemperature(inputTemperature, sourceUnit, targetUnit);
		Assert.assertEquals(expectedTemperature, outputTemperature, 0.00000001);
	}
}