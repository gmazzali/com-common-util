package com.common.util.tool;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.Test;

import com.common.util.tools.FormatUtil;

/**
 * La clase de pruebas de la clase base que permite formatear elementos.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class FormatUtilUnitTest {

	/**
	 * Cuando se termina de ejecutar la prueba, dejamos un espacio en blanco.
	 */
	@AfterClass
	public static void afterClass() {
		System.out.println();
	}

	/**
	 * La pruebas sobre el método <i><b>formatNumber</b></i> de {@link FormatUtil} con valores enteros.
	 * 
	 * @see FormatUtil#formatNumber(Number, Integer, Integer, Integer, Integer, Character, Integer, Character)
	 */
	@Test
	public void testFormatNumberIntegers() {
		try {
			// Probamos con el valor nulo.
			FormatUtil.formatNumber(null, null, null, null, null, null, null, null);
			Assert.fail();
		} catch (Exception ex) {
			Assert.assertTrue(true);
		}

		Number number = 1;
		String output = "";
		String expectedOutput = "";

		number = 12345;
		expectedOutput = "12345";
		output = FormatUtil.formatNumber(number, null, null, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-12345";
		output = FormatUtil.formatNumber(number, null, null, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		// Minima cantidad de digitos enteros.
		number = 12345;
		expectedOutput = "12345";
		output = FormatUtil.formatNumber(number, -1, null, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-12345";
		output = FormatUtil.formatNumber(number, -1, null, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "12345";
		output = FormatUtil.formatNumber(number, 0, null, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-12345";
		output = FormatUtil.formatNumber(number, 0, null, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "12345";
		output = FormatUtil.formatNumber(number, 1, null, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-12345";
		output = FormatUtil.formatNumber(number, 1, null, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "12345";
		output = FormatUtil.formatNumber(number, 5, null, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-12345";
		output = FormatUtil.formatNumber(number, 5, null, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "012345";
		output = FormatUtil.formatNumber(number, 6, null, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-012345";
		output = FormatUtil.formatNumber(number, 6, null, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		// Máxima cantidad de digitos enteros.
		number = 12345;
		expectedOutput = "12345";
		output = FormatUtil.formatNumber(number, null, 6, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-12345";
		output = FormatUtil.formatNumber(number, null, 6, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "12345";
		output = FormatUtil.formatNumber(number, null, 5, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-12345";
		output = FormatUtil.formatNumber(number, null, 5, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "2345";
		output = FormatUtil.formatNumber(number, null, 4, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-2345";
		output = FormatUtil.formatNumber(number, null, 4, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "5";
		output = FormatUtil.formatNumber(number, null, 1, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-5";
		output = FormatUtil.formatNumber(number, null, 1, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "12345";
		output = FormatUtil.formatNumber(number, null, 0, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-12345";
		output = FormatUtil.formatNumber(number, null, 0, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "12345";
		output = FormatUtil.formatNumber(number, null, -1, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-12345";
		output = FormatUtil.formatNumber(number, null, -1, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		// Agrupación de enteros.
		number = 12345;
		expectedOutput = "1.23.45";
		output = FormatUtil.formatNumber(number, null, null, null, null, null, 2, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-1.23.45";
		output = FormatUtil.formatNumber(number, null, null, null, null, null, 2, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "12345";
		output = FormatUtil.formatNumber(number, null, null, null, null, null, 10, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-12345";
		output = FormatUtil.formatNumber(number, null, null, null, null, null, 10, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "12345";
		output = FormatUtil.formatNumber(number, null, null, null, null, null, 10, '_');
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-12345";
		output = FormatUtil.formatNumber(number, null, null, null, null, null, 10, '_');
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "1_23_45";
		output = FormatUtil.formatNumber(number, null, null, null, null, null, 2, '_');
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-1_23_45";
		output = FormatUtil.formatNumber(number, null, null, null, null, null, 2, '_');
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "12_345";
		output = FormatUtil.formatNumber(number, null, null, null, null, null, 3, '_');
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-12_345";
		output = FormatUtil.formatNumber(number, null, null, null, null, null, 3, '_');
		Assert.assertEquals(expectedOutput, output);

	}
}