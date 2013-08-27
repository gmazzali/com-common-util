package com.common.util.tool;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.Test;

import com.common.util.tools.Formatter;

/**
 * La clase de pruebas de la clase base que permite formatear elementos.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class FormatterUnitTest {

	/**
	 * Cuando se termina de ejecutar la prueba, dejamos un espacio en blanco.
	 */
	@AfterClass
	public static void afterClass() {
		System.out.println();
	}

	@Test
	public void testZeroFill() {
		System.out.println("<<<<<<<<<<<<<<< PRUEBAS SOBRE LA FUNCIÓN DE COMPLETAR CON CEROS >>>>>>>>>>>>>>>");

		Assert.assertNull(Formatter.zeroFill(null, null));
		Assert.assertNull(Formatter.zeroFill(null, -1));
		Assert.assertNull(Formatter.zeroFill(null, 0));
		Assert.assertNull(Formatter.zeroFill(null, 1));

		Assert.assertEquals("123", Formatter.zeroFill(123, null));
		Assert.assertEquals("123", Formatter.zeroFill(123, -1));
		Assert.assertEquals("123", Formatter.zeroFill(123, 0));
		Assert.assertEquals("123", Formatter.zeroFill(123, 1));
		Assert.assertEquals("123", Formatter.zeroFill(123, 2));
		Assert.assertEquals("123", Formatter.zeroFill(123, 3));
		Assert.assertEquals("0123", Formatter.zeroFill(123, 4));
		Assert.assertEquals("00123", Formatter.zeroFill(123, 5));
		Assert.assertEquals("0000000123", Formatter.zeroFill(123, 10));

		Assert.assertEquals("-123", Formatter.zeroFill(-123, null));
		Assert.assertEquals("-123", Formatter.zeroFill(-123, -1));
		Assert.assertEquals("-123", Formatter.zeroFill(-123, 0));
		Assert.assertEquals("-123", Formatter.zeroFill(-123, 1));
		Assert.assertEquals("-123", Formatter.zeroFill(-123, 2));
		Assert.assertEquals("-123", Formatter.zeroFill(-123, 3));
		Assert.assertEquals("-0123", Formatter.zeroFill(-123, 4));
		Assert.assertEquals("-00123", Formatter.zeroFill(-123, 5));
		Assert.assertEquals("-0000000123", Formatter.zeroFill(-123, 10));
	}
}