package com.common.util.business.tool;

import org.apache.log4j.BasicConfigurator;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * La clase de pruebas de la clase base que permite formatear elementos.
 * 
 * @since 05/03/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class FormatUtilTest {

	public static final String GROUP_SEPARATOR = ".";

	public static final String DECIMAL_SEPARATOR = ",";

	@BeforeClass
	public static void initClass() {
		BasicConfigurator.configure();
	}

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
		}

		Number number = null;
		String output = "";
		String expectedOutput = "";

		number = 12345;
		expectedOutput = "12345";
		output = FormatUtil.formatNumber(number, null, null, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 0;
		expectedOutput = "0";
		output = FormatUtil.formatNumber(number, null, null, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-12345";
		output = FormatUtil.formatNumber(number, null, null, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		// MINIMA CANTIDAD DE DIGITOS ENTEROS.

		// POSITIVOS.
		number = 12345;
		expectedOutput = "12345";
		output = FormatUtil.formatNumber(number, -1, null, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "12345";
		output = FormatUtil.formatNumber(number, 0, null, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "12345";
		output = FormatUtil.formatNumber(number, 1, null, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "12345";
		output = FormatUtil.formatNumber(number, 5, null, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "012345";
		output = FormatUtil.formatNumber(number, 6, null, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "0000012345";
		output = FormatUtil.formatNumber(number, 10, null, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		// CERO.
		number = 0;
		expectedOutput = "0";
		output = FormatUtil.formatNumber(number, -1, null, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 0;
		expectedOutput = "0";
		output = FormatUtil.formatNumber(number, 0, null, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 0;
		expectedOutput = "0";
		output = FormatUtil.formatNumber(number, 1, null, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 0;
		expectedOutput = "00000";
		output = FormatUtil.formatNumber(number, 5, null, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 0;
		expectedOutput = "000000";
		output = FormatUtil.formatNumber(number, 6, null, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 0;
		expectedOutput = "0000000000";
		output = FormatUtil.formatNumber(number, 10, null, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		// NEGATIVOS.
		number = -12345;
		expectedOutput = "-12345";
		output = FormatUtil.formatNumber(number, -1, null, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-12345";
		output = FormatUtil.formatNumber(number, 0, null, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-12345";
		output = FormatUtil.formatNumber(number, 1, null, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-12345";
		output = FormatUtil.formatNumber(number, 5, null, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-012345";
		output = FormatUtil.formatNumber(number, 6, null, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-0000012345";
		output = FormatUtil.formatNumber(number, 10, null, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		// MAXIMA CANTIDAD DE NUMEROS ENTEROS.

		// POSITIVOS.
		number = 12345;
		expectedOutput = "12345";
		output = FormatUtil.formatNumber(number, null, -1, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "12345";
		output = FormatUtil.formatNumber(number, null, 0, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "5";
		output = FormatUtil.formatNumber(number, null, 1, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);
		number = 12345;
		expectedOutput = "2345";
		output = FormatUtil.formatNumber(number, null, 4, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "12345";
		output = FormatUtil.formatNumber(number, null, 5, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "12345";
		output = FormatUtil.formatNumber(number, null, 6, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "12345";
		output = FormatUtil.formatNumber(number, null, 10, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		// CERO.
		number = 0;
		expectedOutput = "0";
		output = FormatUtil.formatNumber(number, null, -1, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 0;
		expectedOutput = "0";
		output = FormatUtil.formatNumber(number, null, 0, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 0;
		expectedOutput = "0";
		output = FormatUtil.formatNumber(number, null, 1, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 0;
		expectedOutput = "0";
		output = FormatUtil.formatNumber(number, null, 4, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 0;
		expectedOutput = "0";
		output = FormatUtil.formatNumber(number, null, 5, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 0;
		expectedOutput = "0";
		output = FormatUtil.formatNumber(number, null, 6, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 0;
		expectedOutput = "0";
		output = FormatUtil.formatNumber(number, null, 10, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		// NEGATIVOS.
		number = -12345;
		expectedOutput = "-12345";
		output = FormatUtil.formatNumber(number, null, -1, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-12345";
		output = FormatUtil.formatNumber(number, null, 0, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-5";
		output = FormatUtil.formatNumber(number, null, 1, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-2345";
		output = FormatUtil.formatNumber(number, null, 4, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-12345";
		output = FormatUtil.formatNumber(number, null, 5, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-12345";
		output = FormatUtil.formatNumber(number, null, 6, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-12345";
		output = FormatUtil.formatNumber(number, null, 10, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		// AGRUPACION DE VALORES ENTEROS.

		// POSITIVOS.
		number = 12345;
		expectedOutput = "12345";
		output = FormatUtil.formatNumber(number, null, null, null, null, null, -1, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "12345";
		output = FormatUtil.formatNumber(number, null, null, null, null, null, 0, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "1" + FormatUtilTest.GROUP_SEPARATOR + "2" + FormatUtilTest.GROUP_SEPARATOR + "3"
				+ FormatUtilTest.GROUP_SEPARATOR + "4" + FormatUtilTest.GROUP_SEPARATOR + "5";
		output = FormatUtil.formatNumber(number, null, null, null, null, null, 1, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "1" + FormatUtilTest.GROUP_SEPARATOR + "23" + FormatUtilTest.GROUP_SEPARATOR + "45";
		output = FormatUtil.formatNumber(number, null, null, null, null, null, 2, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "12345";
		output = FormatUtil.formatNumber(number, null, null, null, null, null, 5, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "12345";
		output = FormatUtil.formatNumber(number, null, null, null, null, null, 10, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "12345";
		output = FormatUtil.formatNumber(number, null, null, null, null, null, -1, '_');
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "12345";
		output = FormatUtil.formatNumber(number, null, null, null, null, null, 0, '_');
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "1_2_3_4_5";
		output = FormatUtil.formatNumber(number, null, null, null, null, null, 1, '_');
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "1_23_45";
		output = FormatUtil.formatNumber(number, null, null, null, null, null, 2, '_');
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "12345";
		output = FormatUtil.formatNumber(number, null, null, null, null, null, 5, '_');
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "12345";
		output = FormatUtil.formatNumber(number, null, null, null, null, null, 10, '_');
		Assert.assertEquals(expectedOutput, output);

		// CERO.
		number = 0;
		expectedOutput = "0";
		output = FormatUtil.formatNumber(number, null, null, null, null, null, -1, null);
		Assert.assertEquals(expectedOutput, output);

		number = 0;
		expectedOutput = "0";
		output = FormatUtil.formatNumber(number, null, null, null, null, null, 0, null);
		Assert.assertEquals(expectedOutput, output);

		number = 0;
		expectedOutput = "0";
		output = FormatUtil.formatNumber(number, null, null, null, null, null, 1, null);
		Assert.assertEquals(expectedOutput, output);

		number = 0;
		expectedOutput = "0";
		output = FormatUtil.formatNumber(number, null, null, null, null, null, -1, '_');
		Assert.assertEquals(expectedOutput, output);

		number = 0;
		expectedOutput = "0";
		output = FormatUtil.formatNumber(number, null, null, null, null, null, 0, '_');
		Assert.assertEquals(expectedOutput, output);

		number = 0;
		expectedOutput = "0";
		output = FormatUtil.formatNumber(number, null, null, null, null, null, 1, '_');
		Assert.assertEquals(expectedOutput, output);

		// NEGATIVOS.
		number = -12345;
		expectedOutput = "-12345";
		output = FormatUtil.formatNumber(number, null, null, null, null, null, -1, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-12345";
		output = FormatUtil.formatNumber(number, null, null, null, null, null, 0, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-1" + FormatUtilTest.GROUP_SEPARATOR + "2" + FormatUtilTest.GROUP_SEPARATOR + "3"
				+ FormatUtilTest.GROUP_SEPARATOR + "4" + FormatUtilTest.GROUP_SEPARATOR + "5";
		output = FormatUtil.formatNumber(number, null, null, null, null, null, 1, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-1" + FormatUtilTest.GROUP_SEPARATOR + "23" + FormatUtilTest.GROUP_SEPARATOR + "45";
		output = FormatUtil.formatNumber(number, null, null, null, null, null, 2, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-12345";
		output = FormatUtil.formatNumber(number, null, null, null, null, null, 5, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-12345";
		output = FormatUtil.formatNumber(number, null, null, null, null, null, 10, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-12345";
		output = FormatUtil.formatNumber(number, null, null, null, null, null, -1, '_');
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-12345";
		output = FormatUtil.formatNumber(number, null, null, null, null, null, 0, '_');
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-1_2_3_4_5";
		output = FormatUtil.formatNumber(number, null, null, null, null, null, 1, '_');
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-1_23_45";
		output = FormatUtil.formatNumber(number, null, null, null, null, null, 2, '_');
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-12345";
		output = FormatUtil.formatNumber(number, null, null, null, null, null, 5, '_');
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-12345";
		output = FormatUtil.formatNumber(number, null, null, null, null, null, 10, '_');
		Assert.assertEquals(expectedOutput, output);
	}

	/**
	 * La pruebas sobre el método <i><b>formatNumber</b></i> de {@link FormatUtil} con valores decimales.
	 * 
	 * @see FormatUtil#formatNumber(Number, Integer, Integer, Integer, Integer, Character, Integer, Character)
	 */
	@Test
	public void testFormatNumberDecimals() {
		try {
			// Probamos con el valor nulo.
			FormatUtil.formatNumber(null, null, null, null, null, null, null, null);
			Assert.fail();
		} catch (Exception ex) {
			Assert.assertTrue(true);
		}

		Number number = null;
		String output = "";
		String expectedOutput = "";

		// CANTIDADES DE DIGITOS DECIMALES NULAS.

		number = 12345.12345;
		expectedOutput = "12345" + FormatUtilTest.DECIMAL_SEPARATOR + "12345";
		output = FormatUtil.formatNumber(number, null, null, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345.12345;
		expectedOutput = "-12345" + FormatUtilTest.DECIMAL_SEPARATOR + "12345";
		output = FormatUtil.formatNumber(number, null, null, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		// MINIMA CANTIDAD DE DIGITOS DECIMALES.

		// POSITIVOS
		number = 12345.12345;
		expectedOutput = "12345" + FormatUtilTest.DECIMAL_SEPARATOR + "12345";
		output = FormatUtil.formatNumber(number, null, null, -1, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345.12345;
		expectedOutput = "12345" + FormatUtilTest.DECIMAL_SEPARATOR + "12345";
		output = FormatUtil.formatNumber(number, null, null, 0, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345.12345;
		expectedOutput = "12345" + FormatUtilTest.DECIMAL_SEPARATOR + "12345";
		output = FormatUtil.formatNumber(number, null, null, 1, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345.12345;
		expectedOutput = "12345" + FormatUtilTest.DECIMAL_SEPARATOR + "12345";
		output = FormatUtil.formatNumber(number, null, null, 5, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345.12345;
		expectedOutput = "12345" + FormatUtilTest.DECIMAL_SEPARATOR + "123450";
		output = FormatUtil.formatNumber(number, null, null, 6, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345.12345;
		expectedOutput = "12345" + FormatUtilTest.DECIMAL_SEPARATOR + "1234500000";
		output = FormatUtil.formatNumber(number, null, null, 10, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		// CERO
		number = 0.0;
		expectedOutput = "0";
		output = FormatUtil.formatNumber(number, 1, null, -1, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 0.0;
		expectedOutput = "0";
		output = FormatUtil.formatNumber(number, 1, null, 0, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 0.0;
		expectedOutput = "0" + FormatUtilTest.DECIMAL_SEPARATOR + "0";
		output = FormatUtil.formatNumber(number, 1, null, 1, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 0.0;
		expectedOutput = "0" + FormatUtilTest.DECIMAL_SEPARATOR + "00000";
		output = FormatUtil.formatNumber(number, 1, null, 5, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 0.0;
		expectedOutput = "0" + FormatUtilTest.DECIMAL_SEPARATOR + "000000";
		output = FormatUtil.formatNumber(number, 1, null, 6, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 0.0;
		expectedOutput = "0" + FormatUtilTest.DECIMAL_SEPARATOR + "0000000000";
		output = FormatUtil.formatNumber(number, 1, null, 10, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		// NEGATIVOS
		number = -12345.12345;
		expectedOutput = "-12345" + FormatUtilTest.DECIMAL_SEPARATOR + "12345";
		output = FormatUtil.formatNumber(number, null, null, -1, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345.12345;
		expectedOutput = "-12345" + FormatUtilTest.DECIMAL_SEPARATOR + "12345";
		output = FormatUtil.formatNumber(number, null, null, 0, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345.12345;
		expectedOutput = "-12345" + FormatUtilTest.DECIMAL_SEPARATOR + "12345";
		output = FormatUtil.formatNumber(number, null, null, 1, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345.12345;
		expectedOutput = "-12345" + FormatUtilTest.DECIMAL_SEPARATOR + "12345";
		output = FormatUtil.formatNumber(number, null, null, 5, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345.12345;
		expectedOutput = "-12345" + FormatUtilTest.DECIMAL_SEPARATOR + "123450";
		output = FormatUtil.formatNumber(number, null, null, 6, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345.12345;
		expectedOutput = "-12345" + FormatUtilTest.DECIMAL_SEPARATOR + "1234500000";
		output = FormatUtil.formatNumber(number, null, null, 10, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		// MINIMA CANTIDAD DE DIGITOS DECIMALES.

		// POSITIVOS.
		number = 12345.12345;
		expectedOutput = "12345" + FormatUtilTest.DECIMAL_SEPARATOR + "12345";
		output = FormatUtil.formatNumber(number, null, null, null, -1, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345.12345;
		expectedOutput = "12345" + FormatUtilTest.DECIMAL_SEPARATOR + "12345";
		output = FormatUtil.formatNumber(number, null, null, null, 0, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345.12345;
		expectedOutput = "12345" + FormatUtilTest.DECIMAL_SEPARATOR + "1";
		output = FormatUtil.formatNumber(number, null, null, null, 1, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345.12345;
		expectedOutput = "12345" + FormatUtilTest.DECIMAL_SEPARATOR + "12345";
		output = FormatUtil.formatNumber(number, null, null, null, 5, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345.12345;
		expectedOutput = "12345" + FormatUtilTest.DECIMAL_SEPARATOR + "12345";
		output = FormatUtil.formatNumber(number, null, null, null, 6, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345.12345;
		expectedOutput = "12345" + FormatUtilTest.DECIMAL_SEPARATOR + "12345";
		output = FormatUtil.formatNumber(number, null, null, null, 10, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		// NEGATIVOS.
		number = -12345.12345;
		expectedOutput = "-12345" + FormatUtilTest.DECIMAL_SEPARATOR + "12345";
		output = FormatUtil.formatNumber(number, null, null, null, -1, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345.12345;
		expectedOutput = "-12345" + FormatUtilTest.DECIMAL_SEPARATOR + "12345";
		output = FormatUtil.formatNumber(number, null, null, null, 0, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345.12345;
		expectedOutput = "-12345" + FormatUtilTest.DECIMAL_SEPARATOR + "1";
		output = FormatUtil.formatNumber(number, null, null, null, 1, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345.12345;
		expectedOutput = "-12345" + FormatUtilTest.DECIMAL_SEPARATOR + "12345";
		output = FormatUtil.formatNumber(number, null, null, null, 5, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345.12345;
		expectedOutput = "-12345" + FormatUtilTest.DECIMAL_SEPARATOR + "12345";
		output = FormatUtil.formatNumber(number, null, null, null, 6, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345.12345;
		expectedOutput = "-12345" + FormatUtilTest.DECIMAL_SEPARATOR + "12345";
		output = FormatUtil.formatNumber(number, null, null, null, 10, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		// CARACTER SEPARADOR DE DECIMALES.

		// POSITIVOS.
		number = 12345.12345;
		expectedOutput = "12345" + FormatUtilTest.DECIMAL_SEPARATOR + "12345";
		output = FormatUtil.formatNumber(number, null, null, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345.12345;
		expectedOutput = "12345_12345";
		output = FormatUtil.formatNumber(number, null, null, null, null, '_', null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345.12345;
		expectedOutput = "12345:12345";
		output = FormatUtil.formatNumber(number, null, null, null, null, ':', null, null);
		Assert.assertEquals(expectedOutput, output);

		// NEGATIVOS.
		number = -12345.12345;
		expectedOutput = "-12345" + FormatUtilTest.DECIMAL_SEPARATOR + "12345";
		output = FormatUtil.formatNumber(number, null, null, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345.12345;
		expectedOutput = "-12345_12345";
		output = FormatUtil.formatNumber(number, null, null, null, null, '_', null, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345.12345;
		expectedOutput = "-12345:12345";
		output = FormatUtil.formatNumber(number, null, null, null, null, ':', null, null);
		Assert.assertEquals(expectedOutput, output);
	}

	/**
	 * La pruebas sobre el método <i><b>zeroFill</b></i> de {@link FormatUtil}.
	 * 
	 * @see FormatUtil#zeroFill(Number, Integer)
	 */
	@Test
	public void testZeroFill() {
		// Probamos con valores nulos.
		try {
			FormatUtil.zeroFill(null, null);
			Assert.assertTrue(true);
		} catch (Exception ex) {
			Assert.fail();
		}
		try {
			FormatUtil.zeroFill(null, 0);
			Assert.assertTrue(true);
		} catch (Exception ex) {
			Assert.fail();
		}

		Number number = null;
		String output = "";
		String expectedOutput = "";

		// POSITIVOS.
		number = 12345;
		expectedOutput = "12345";
		output = FormatUtil.zeroFill(number, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "12345";
		output = FormatUtil.zeroFill(number, -1);
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "12345";
		output = FormatUtil.zeroFill(number, 0);
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "12345";
		output = FormatUtil.zeroFill(number, 1);
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "12345";
		output = FormatUtil.zeroFill(number, 5);
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "012345";
		output = FormatUtil.zeroFill(number, 6);
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "0000012345";
		output = FormatUtil.zeroFill(number, 10);
		Assert.assertEquals(expectedOutput, output);

		// NEGATIVOS.
		number = -12345;
		expectedOutput = "-12345";
		output = FormatUtil.zeroFill(number, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-12345";
		output = FormatUtil.zeroFill(number, -1);
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-12345";
		output = FormatUtil.zeroFill(number, 0);
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-12345";
		output = FormatUtil.zeroFill(number, 1);
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-12345";
		output = FormatUtil.zeroFill(number, 5);
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-012345";
		output = FormatUtil.zeroFill(number, 6);
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-0000012345";
		output = FormatUtil.zeroFill(number, 10);
		Assert.assertEquals(expectedOutput, output);
	}
}