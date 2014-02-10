package com.common.util.tool;

import junit.framework.Assert;

import org.junit.Test;

/**
 * La clase de pruebas de la clase que permite convertir un numero en una cadena de caracteres con su descripción.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class ConverterUtilUnitTest {

	/**
	 * La pruebas sobre el método <i><b>formatCurrency</b></i> de {@link ConverterUtil}
	 * 
	 * @see ConverterUtil#convertShortToDescription(Integer)
	 */
	@Test
	public void testConvertNumberToString() {

		Short value = null;
		String output = null;

		try {
			value = null;
			output = "";
			Assert.assertEquals(output, ConverterUtil.convertShortToDescription(value));
		} catch (Exception ex) {
			Assert.fail();
		}

		value = 0;
		output = "";
		Assert.assertEquals(output, ConverterUtil.convertShortToDescription(value));

		value = 1;
		output = "uno";
		Assert.assertEquals(output, ConverterUtil.convertShortToDescription(value));

		value = 2;
		output = "dos";
		Assert.assertEquals(output, ConverterUtil.convertShortToDescription(value));

		value = 3;
		output = "tres";
		Assert.assertEquals(output, ConverterUtil.convertShortToDescription(value));

		value = 10;
		output = "diez";
		Assert.assertEquals(output, ConverterUtil.convertShortToDescription(value));

		value = 11;
		output = "once";
		Assert.assertEquals(output, ConverterUtil.convertShortToDescription(value));

		value = 12;
		output = "doce";
		Assert.assertEquals(output, ConverterUtil.convertShortToDescription(value));

		value = 20;
		output = "veinte";
		Assert.assertEquals(output, ConverterUtil.convertShortToDescription(value));

		value = 21;
		output = "veintiun";
		Assert.assertEquals(output, ConverterUtil.convertShortToDescription(value));

		value = 22;
		output = "veintidos";
		Assert.assertEquals(output, ConverterUtil.convertShortToDescription(value));

		value = 30;
		output = "treinta";
		Assert.assertEquals(output, ConverterUtil.convertShortToDescription(value));

		value = 31;
		output = "treinta y uno";
		Assert.assertEquals(output, ConverterUtil.convertShortToDescription(value));

		value = 40;
		output = "cuarenta";
		Assert.assertEquals(output, ConverterUtil.convertShortToDescription(value));

		value = 41;
		output = "cuarenta y uno";
		Assert.assertEquals(output, ConverterUtil.convertShortToDescription(value));

		value = 49;
		output = "cuarenta y nueve";
		Assert.assertEquals(output, ConverterUtil.convertShortToDescription(value));

		value = 50;
		output = "cincuenta";
		Assert.assertEquals(output, ConverterUtil.convertShortToDescription(value));

		value = 60;
		output = "sesenta";
		Assert.assertEquals(output, ConverterUtil.convertShortToDescription(value));

		value = 90;
		output = "noventa";
		Assert.assertEquals(output, ConverterUtil.convertShortToDescription(value));

		value = 100;
		output = "cien";
		Assert.assertEquals(output, ConverterUtil.convertShortToDescription(value));

		value = 101;
		output = "ciento uno";
		Assert.assertEquals(output, ConverterUtil.convertShortToDescription(value));

		value = 111;
		output = "ciento once";
		Assert.assertEquals(output, ConverterUtil.convertShortToDescription(value));

		value = 121;
		output = "ciento veintiun";
		Assert.assertEquals(output, ConverterUtil.convertShortToDescription(value));

		value = 150;
		output = "ciento cincuenta";
		Assert.assertEquals(output, ConverterUtil.convertShortToDescription(value));

		value = 157;
		output = "ciento cincuenta y siete";
		Assert.assertEquals(output, ConverterUtil.convertShortToDescription(value));

		value = 200;
		output = "doscientos";
		Assert.assertEquals(output, ConverterUtil.convertShortToDescription(value));

		value = 201;
		output = "doscientos uno";
		Assert.assertEquals(output, ConverterUtil.convertShortToDescription(value));

		value = 299;
		output = "doscientos noventa y nueve";
		Assert.assertEquals(output, ConverterUtil.convertShortToDescription(value));

		value = 300;
		output = "trescientos";
		Assert.assertEquals(output, ConverterUtil.convertShortToDescription(value));

		value = 400;
		output = "cuatrocientos";
		Assert.assertEquals(output, ConverterUtil.convertShortToDescription(value));

		value = 500;
		output = "quinientos";
		Assert.assertEquals(output, ConverterUtil.convertShortToDescription(value));

		value = 600;
		output = "seiscientos";
		Assert.assertEquals(output, ConverterUtil.convertShortToDescription(value));

		value = 900;
		output = "novecientos";
		Assert.assertEquals(output, ConverterUtil.convertShortToDescription(value));

		value = 990;
		output = "novecientos noventa";
		Assert.assertEquals(output, ConverterUtil.convertShortToDescription(value));

		value = 999;
		output = "novecientos noventa y nueve";
		Assert.assertEquals(output, ConverterUtil.convertShortToDescription(value));

		value = 1000;
		output = "";
		Assert.assertEquals(output, ConverterUtil.convertShortToDescription(value));

		value = 1123;
		output = "";
		Assert.assertEquals(output, ConverterUtil.convertShortToDescription(value));
	}
}