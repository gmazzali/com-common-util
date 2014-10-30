package com.common.util.business.tool;

import org.apache.log4j.BasicConfigurator;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * La clase de pruebas de la clase base que permite manipular números.
 * 
 * @since 30/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class NumberUtilTest {

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
	 * La pruebas sobre el método <i><b>isLongNumber</b></i> de {@link NumberUtil}.
	 * 
	 * @see NumberUtil#isLongNumber(String)
	 */
	@Test
	public void testIsLongNumber() throws Exception {
		Assert.assertFalse(NumberUtil.isLongNumber(null));
		Assert.assertFalse(NumberUtil.isLongNumber(""));
		Assert.assertFalse(NumberUtil.isLongNumber(" "));
		
		Assert.assertFalse(NumberUtil.isLongNumber("a"));
		Assert.assertFalse(NumberUtil.isLongNumber("-"));
		Assert.assertFalse(NumberUtil.isLongNumber("?"));
		
		Assert.assertFalse(NumberUtil.isLongNumber("-1.5"));
		Assert.assertFalse(NumberUtil.isLongNumber("1.5"));

		Assert.assertTrue(NumberUtil.isLongNumber("-1"));
		Assert.assertTrue(NumberUtil.isLongNumber("0"));
		Assert.assertTrue(NumberUtil.isLongNumber("1"));
	}

	/**
	 * La pruebas sobre el método <i><b>formatNumber</b></i> de {@link NumberUtil}.
	 * 
	 * @see NumberUtil#isDecimalNumber(String)
	 */
	@Test
	public void testIsDecimalNumber() throws Exception {
		Assert.assertFalse(NumberUtil.isDecimalNumber(null));
		Assert.assertFalse(NumberUtil.isDecimalNumber(""));
		Assert.assertFalse(NumberUtil.isDecimalNumber(" "));
		
		Assert.assertFalse(NumberUtil.isDecimalNumber("a"));
		Assert.assertFalse(NumberUtil.isDecimalNumber("-"));
		Assert.assertFalse(NumberUtil.isDecimalNumber("?"));
		
		Assert.assertTrue(NumberUtil.isDecimalNumber("-1.5"));
		Assert.assertTrue(NumberUtil.isDecimalNumber("1.5"));
		Assert.assertTrue(NumberUtil.isDecimalNumber("-1234.5678"));
		Assert.assertTrue(NumberUtil.isDecimalNumber("1234.5678"));

		Assert.assertTrue(NumberUtil.isDecimalNumber("-1"));
		Assert.assertTrue(NumberUtil.isDecimalNumber("0"));
		Assert.assertTrue(NumberUtil.isDecimalNumber("1"));
	}

	/**
	 * La pruebas sobre el método <i><b>formatNumber</b></i> de {@link NumberUtil} con valores enteros.
	 * 
	 * @see NumberUtil#formatNumber(Number, Integer, Integer, Integer, Integer, Character, Integer, Character)
	 */
	@Test
	public void testFormatNumberIntegers() {
		try {
			// Probamos con el valor nulo.
			NumberUtil.formatNumber(null, null, null, null, null, null, null, null);
			Assert.fail();
		} catch (Exception ex) {
		}

		Number number = null;
		String output = "";
		String expectedOutput = "";

		number = 12345;
		expectedOutput = "12345";
		output = NumberUtil.formatNumber(number, null, null, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 0;
		expectedOutput = "0";
		output = NumberUtil.formatNumber(number, null, null, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-12345";
		output = NumberUtil.formatNumber(number, null, null, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		// MINIMA CANTIDAD DE DIGITOS ENTEROS.

		// POSITIVOS.
		number = 12345;
		expectedOutput = "12345";
		output = NumberUtil.formatNumber(number, -1, null, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "12345";
		output = NumberUtil.formatNumber(number, 0, null, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "12345";
		output = NumberUtil.formatNumber(number, 1, null, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "12345";
		output = NumberUtil.formatNumber(number, 5, null, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "012345";
		output = NumberUtil.formatNumber(number, 6, null, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "0000012345";
		output = NumberUtil.formatNumber(number, 10, null, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		// CERO.
		number = 0;
		expectedOutput = "0";
		output = NumberUtil.formatNumber(number, -1, null, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 0;
		expectedOutput = "0";
		output = NumberUtil.formatNumber(number, 0, null, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 0;
		expectedOutput = "0";
		output = NumberUtil.formatNumber(number, 1, null, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 0;
		expectedOutput = "00000";
		output = NumberUtil.formatNumber(number, 5, null, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 0;
		expectedOutput = "000000";
		output = NumberUtil.formatNumber(number, 6, null, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 0;
		expectedOutput = "0000000000";
		output = NumberUtil.formatNumber(number, 10, null, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		// NEGATIVOS.
		number = -12345;
		expectedOutput = "-12345";
		output = NumberUtil.formatNumber(number, -1, null, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-12345";
		output = NumberUtil.formatNumber(number, 0, null, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-12345";
		output = NumberUtil.formatNumber(number, 1, null, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-12345";
		output = NumberUtil.formatNumber(number, 5, null, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-012345";
		output = NumberUtil.formatNumber(number, 6, null, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-0000012345";
		output = NumberUtil.formatNumber(number, 10, null, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		// MAXIMA CANTIDAD DE NUMEROS ENTEROS.

		// POSITIVOS.
		number = 12345;
		expectedOutput = "12345";
		output = NumberUtil.formatNumber(number, null, -1, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "12345";
		output = NumberUtil.formatNumber(number, null, 0, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "5";
		output = NumberUtil.formatNumber(number, null, 1, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);
		number = 12345;
		expectedOutput = "2345";
		output = NumberUtil.formatNumber(number, null, 4, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "12345";
		output = NumberUtil.formatNumber(number, null, 5, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "12345";
		output = NumberUtil.formatNumber(number, null, 6, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "12345";
		output = NumberUtil.formatNumber(number, null, 10, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		// CERO.
		number = 0;
		expectedOutput = "0";
		output = NumberUtil.formatNumber(number, null, -1, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 0;
		expectedOutput = "0";
		output = NumberUtil.formatNumber(number, null, 0, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 0;
		expectedOutput = "0";
		output = NumberUtil.formatNumber(number, null, 1, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 0;
		expectedOutput = "0";
		output = NumberUtil.formatNumber(number, null, 4, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 0;
		expectedOutput = "0";
		output = NumberUtil.formatNumber(number, null, 5, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 0;
		expectedOutput = "0";
		output = NumberUtil.formatNumber(number, null, 6, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 0;
		expectedOutput = "0";
		output = NumberUtil.formatNumber(number, null, 10, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		// NEGATIVOS.
		number = -12345;
		expectedOutput = "-12345";
		output = NumberUtil.formatNumber(number, null, -1, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-12345";
		output = NumberUtil.formatNumber(number, null, 0, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-5";
		output = NumberUtil.formatNumber(number, null, 1, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-2345";
		output = NumberUtil.formatNumber(number, null, 4, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-12345";
		output = NumberUtil.formatNumber(number, null, 5, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-12345";
		output = NumberUtil.formatNumber(number, null, 6, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-12345";
		output = NumberUtil.formatNumber(number, null, 10, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		// AGRUPACION DE VALORES ENTEROS.

		// POSITIVOS.
		number = 12345;
		expectedOutput = "12345";
		output = NumberUtil.formatNumber(number, null, null, null, null, null, -1, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "12345";
		output = NumberUtil.formatNumber(number, null, null, null, null, null, 0, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "1" + NumberUtilTest.GROUP_SEPARATOR + "2" + NumberUtilTest.GROUP_SEPARATOR + "3" + NumberUtilTest.GROUP_SEPARATOR + "4"
				+ NumberUtilTest.GROUP_SEPARATOR + "5";
		output = NumberUtil.formatNumber(number, null, null, null, null, null, 1, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "1" + NumberUtilTest.GROUP_SEPARATOR + "23" + NumberUtilTest.GROUP_SEPARATOR + "45";
		output = NumberUtil.formatNumber(number, null, null, null, null, null, 2, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "12345";
		output = NumberUtil.formatNumber(number, null, null, null, null, null, 5, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "12345";
		output = NumberUtil.formatNumber(number, null, null, null, null, null, 10, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "12345";
		output = NumberUtil.formatNumber(number, null, null, null, null, null, -1, '_');
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "12345";
		output = NumberUtil.formatNumber(number, null, null, null, null, null, 0, '_');
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "1_2_3_4_5";
		output = NumberUtil.formatNumber(number, null, null, null, null, null, 1, '_');
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "1_23_45";
		output = NumberUtil.formatNumber(number, null, null, null, null, null, 2, '_');
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "12345";
		output = NumberUtil.formatNumber(number, null, null, null, null, null, 5, '_');
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "12345";
		output = NumberUtil.formatNumber(number, null, null, null, null, null, 10, '_');
		Assert.assertEquals(expectedOutput, output);

		// CERO.
		number = 0;
		expectedOutput = "0";
		output = NumberUtil.formatNumber(number, null, null, null, null, null, -1, null);
		Assert.assertEquals(expectedOutput, output);

		number = 0;
		expectedOutput = "0";
		output = NumberUtil.formatNumber(number, null, null, null, null, null, 0, null);
		Assert.assertEquals(expectedOutput, output);

		number = 0;
		expectedOutput = "0";
		output = NumberUtil.formatNumber(number, null, null, null, null, null, 1, null);
		Assert.assertEquals(expectedOutput, output);

		number = 0;
		expectedOutput = "0";
		output = NumberUtil.formatNumber(number, null, null, null, null, null, -1, '_');
		Assert.assertEquals(expectedOutput, output);

		number = 0;
		expectedOutput = "0";
		output = NumberUtil.formatNumber(number, null, null, null, null, null, 0, '_');
		Assert.assertEquals(expectedOutput, output);

		number = 0;
		expectedOutput = "0";
		output = NumberUtil.formatNumber(number, null, null, null, null, null, 1, '_');
		Assert.assertEquals(expectedOutput, output);

		// NEGATIVOS.
		number = -12345;
		expectedOutput = "-12345";
		output = NumberUtil.formatNumber(number, null, null, null, null, null, -1, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-12345";
		output = NumberUtil.formatNumber(number, null, null, null, null, null, 0, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-1" + NumberUtilTest.GROUP_SEPARATOR + "2" + NumberUtilTest.GROUP_SEPARATOR + "3" + NumberUtilTest.GROUP_SEPARATOR + "4"
				+ NumberUtilTest.GROUP_SEPARATOR + "5";
		output = NumberUtil.formatNumber(number, null, null, null, null, null, 1, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-1" + NumberUtilTest.GROUP_SEPARATOR + "23" + NumberUtilTest.GROUP_SEPARATOR + "45";
		output = NumberUtil.formatNumber(number, null, null, null, null, null, 2, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-12345";
		output = NumberUtil.formatNumber(number, null, null, null, null, null, 5, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-12345";
		output = NumberUtil.formatNumber(number, null, null, null, null, null, 10, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-12345";
		output = NumberUtil.formatNumber(number, null, null, null, null, null, -1, '_');
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-12345";
		output = NumberUtil.formatNumber(number, null, null, null, null, null, 0, '_');
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-1_2_3_4_5";
		output = NumberUtil.formatNumber(number, null, null, null, null, null, 1, '_');
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-1_23_45";
		output = NumberUtil.formatNumber(number, null, null, null, null, null, 2, '_');
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-12345";
		output = NumberUtil.formatNumber(number, null, null, null, null, null, 5, '_');
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-12345";
		output = NumberUtil.formatNumber(number, null, null, null, null, null, 10, '_');
		Assert.assertEquals(expectedOutput, output);
	}

	/**
	 * La pruebas sobre el método <i><b>formatNumber</b></i> de {@link NumberUtil} con valores decimales.
	 * 
	 * @see NumberUtil#formatNumber(Number, Integer, Integer, Integer, Integer, Character, Integer, Character)
	 */
	@Test
	public void testFormatNumberDecimals() {
		try {
			// Probamos con el valor nulo.
			NumberUtil.formatNumber(null, null, null, null, null, null, null, null);
			Assert.fail();
		} catch (Exception ex) {
			Assert.assertTrue(true);
		}

		Number number = null;
		String output = "";
		String expectedOutput = "";

		// CANTIDADES DE DIGITOS DECIMALES NULAS.

		number = 12345.12345;
		expectedOutput = "12345" + NumberUtilTest.DECIMAL_SEPARATOR + "12345";
		output = NumberUtil.formatNumber(number, null, null, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345.12345;
		expectedOutput = "-12345" + NumberUtilTest.DECIMAL_SEPARATOR + "12345";
		output = NumberUtil.formatNumber(number, null, null, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		// MINIMA CANTIDAD DE DIGITOS DECIMALES.

		// POSITIVOS
		number = 12345.12345;
		expectedOutput = "12345" + NumberUtilTest.DECIMAL_SEPARATOR + "12345";
		output = NumberUtil.formatNumber(number, null, null, -1, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345.12345;
		expectedOutput = "12345" + NumberUtilTest.DECIMAL_SEPARATOR + "12345";
		output = NumberUtil.formatNumber(number, null, null, 0, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345.12345;
		expectedOutput = "12345" + NumberUtilTest.DECIMAL_SEPARATOR + "12345";
		output = NumberUtil.formatNumber(number, null, null, 1, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345.12345;
		expectedOutput = "12345" + NumberUtilTest.DECIMAL_SEPARATOR + "12345";
		output = NumberUtil.formatNumber(number, null, null, 5, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345.12345;
		expectedOutput = "12345" + NumberUtilTest.DECIMAL_SEPARATOR + "123450";
		output = NumberUtil.formatNumber(number, null, null, 6, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345.12345;
		expectedOutput = "12345" + NumberUtilTest.DECIMAL_SEPARATOR + "1234500000";
		output = NumberUtil.formatNumber(number, null, null, 10, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		// CERO
		number = 0.0;
		expectedOutput = "0";
		output = NumberUtil.formatNumber(number, 1, null, -1, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 0.0;
		expectedOutput = "0";
		output = NumberUtil.formatNumber(number, 1, null, 0, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 0.0;
		expectedOutput = "0" + NumberUtilTest.DECIMAL_SEPARATOR + "0";
		output = NumberUtil.formatNumber(number, 1, null, 1, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 0.0;
		expectedOutput = "0" + NumberUtilTest.DECIMAL_SEPARATOR + "00000";
		output = NumberUtil.formatNumber(number, 1, null, 5, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 0.0;
		expectedOutput = "0" + NumberUtilTest.DECIMAL_SEPARATOR + "000000";
		output = NumberUtil.formatNumber(number, 1, null, 6, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 0.0;
		expectedOutput = "0" + NumberUtilTest.DECIMAL_SEPARATOR + "0000000000";
		output = NumberUtil.formatNumber(number, 1, null, 10, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		// NEGATIVOS
		number = -12345.12345;
		expectedOutput = "-12345" + NumberUtilTest.DECIMAL_SEPARATOR + "12345";
		output = NumberUtil.formatNumber(number, null, null, -1, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345.12345;
		expectedOutput = "-12345" + NumberUtilTest.DECIMAL_SEPARATOR + "12345";
		output = NumberUtil.formatNumber(number, null, null, 0, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345.12345;
		expectedOutput = "-12345" + NumberUtilTest.DECIMAL_SEPARATOR + "12345";
		output = NumberUtil.formatNumber(number, null, null, 1, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345.12345;
		expectedOutput = "-12345" + NumberUtilTest.DECIMAL_SEPARATOR + "12345";
		output = NumberUtil.formatNumber(number, null, null, 5, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345.12345;
		expectedOutput = "-12345" + NumberUtilTest.DECIMAL_SEPARATOR + "123450";
		output = NumberUtil.formatNumber(number, null, null, 6, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345.12345;
		expectedOutput = "-12345" + NumberUtilTest.DECIMAL_SEPARATOR + "1234500000";
		output = NumberUtil.formatNumber(number, null, null, 10, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		// MINIMA CANTIDAD DE DIGITOS DECIMALES.

		// POSITIVOS.
		number = 12345.12345;
		expectedOutput = "12345" + NumberUtilTest.DECIMAL_SEPARATOR + "12345";
		output = NumberUtil.formatNumber(number, null, null, null, -1, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345.12345;
		expectedOutput = "12345" + NumberUtilTest.DECIMAL_SEPARATOR + "12345";
		output = NumberUtil.formatNumber(number, null, null, null, 0, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345.12345;
		expectedOutput = "12345" + NumberUtilTest.DECIMAL_SEPARATOR + "1";
		output = NumberUtil.formatNumber(number, null, null, null, 1, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345.12345;
		expectedOutput = "12345" + NumberUtilTest.DECIMAL_SEPARATOR + "12345";
		output = NumberUtil.formatNumber(number, null, null, null, 5, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345.12345;
		expectedOutput = "12345" + NumberUtilTest.DECIMAL_SEPARATOR + "12345";
		output = NumberUtil.formatNumber(number, null, null, null, 6, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345.12345;
		expectedOutput = "12345" + NumberUtilTest.DECIMAL_SEPARATOR + "12345";
		output = NumberUtil.formatNumber(number, null, null, null, 10, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		// NEGATIVOS.
		number = -12345.12345;
		expectedOutput = "-12345" + NumberUtilTest.DECIMAL_SEPARATOR + "12345";
		output = NumberUtil.formatNumber(number, null, null, null, -1, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345.12345;
		expectedOutput = "-12345" + NumberUtilTest.DECIMAL_SEPARATOR + "12345";
		output = NumberUtil.formatNumber(number, null, null, null, 0, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345.12345;
		expectedOutput = "-12345" + NumberUtilTest.DECIMAL_SEPARATOR + "1";
		output = NumberUtil.formatNumber(number, null, null, null, 1, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345.12345;
		expectedOutput = "-12345" + NumberUtilTest.DECIMAL_SEPARATOR + "12345";
		output = NumberUtil.formatNumber(number, null, null, null, 5, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345.12345;
		expectedOutput = "-12345" + NumberUtilTest.DECIMAL_SEPARATOR + "12345";
		output = NumberUtil.formatNumber(number, null, null, null, 6, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345.12345;
		expectedOutput = "-12345" + NumberUtilTest.DECIMAL_SEPARATOR + "12345";
		output = NumberUtil.formatNumber(number, null, null, null, 10, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		// CARACTER SEPARADOR DE DECIMALES.

		// POSITIVOS.
		number = 12345.12345;
		expectedOutput = "12345" + NumberUtilTest.DECIMAL_SEPARATOR + "12345";
		output = NumberUtil.formatNumber(number, null, null, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345.12345;
		expectedOutput = "12345_12345";
		output = NumberUtil.formatNumber(number, null, null, null, null, '_', null, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345.12345;
		expectedOutput = "12345:12345";
		output = NumberUtil.formatNumber(number, null, null, null, null, ':', null, null);
		Assert.assertEquals(expectedOutput, output);

		// NEGATIVOS.
		number = -12345.12345;
		expectedOutput = "-12345" + NumberUtilTest.DECIMAL_SEPARATOR + "12345";
		output = NumberUtil.formatNumber(number, null, null, null, null, null, null, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345.12345;
		expectedOutput = "-12345_12345";
		output = NumberUtil.formatNumber(number, null, null, null, null, '_', null, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345.12345;
		expectedOutput = "-12345:12345";
		output = NumberUtil.formatNumber(number, null, null, null, null, ':', null, null);
		Assert.assertEquals(expectedOutput, output);
	}

	/**
	 * La pruebas sobre el método <i><b>zeroFill</b></i> de {@link NumberUtil}.
	 * 
	 * @see NumberUtil#zeroFill(Number, Integer)
	 */
	@Test
	public void testZeroFill() {
		// Probamos con valores nulos.
		try {
			NumberUtil.zeroFill(null, null);
			Assert.assertTrue(true);
		} catch (Exception ex) {
			Assert.fail();
		}
		try {
			NumberUtil.zeroFill(null, 0);
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
		output = NumberUtil.zeroFill(number, null);
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "12345";
		output = NumberUtil.zeroFill(number, -1);
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "12345";
		output = NumberUtil.zeroFill(number, 0);
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "12345";
		output = NumberUtil.zeroFill(number, 1);
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "12345";
		output = NumberUtil.zeroFill(number, 5);
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "012345";
		output = NumberUtil.zeroFill(number, 6);
		Assert.assertEquals(expectedOutput, output);

		number = 12345;
		expectedOutput = "0000012345";
		output = NumberUtil.zeroFill(number, 10);
		Assert.assertEquals(expectedOutput, output);

		// NEGATIVOS.
		number = -12345;
		expectedOutput = "-12345";
		output = NumberUtil.zeroFill(number, null);
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-12345";
		output = NumberUtil.zeroFill(number, -1);
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-12345";
		output = NumberUtil.zeroFill(number, 0);
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-12345";
		output = NumberUtil.zeroFill(number, 1);
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-12345";
		output = NumberUtil.zeroFill(number, 5);
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-012345";
		output = NumberUtil.zeroFill(number, 6);
		Assert.assertEquals(expectedOutput, output);

		number = -12345;
		expectedOutput = "-0000012345";
		output = NumberUtil.zeroFill(number, 10);
		Assert.assertEquals(expectedOutput, output);
	}
}