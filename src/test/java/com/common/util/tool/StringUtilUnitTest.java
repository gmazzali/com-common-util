package com.common.util.tool;

import junit.framework.Assert;

import org.junit.Test;

/**
 * La clase que permite probar los métodos de manejo de Strings base de la libreria.
 * 
 * @since 06/03/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class StringUtilUnitTest {

	/**
	 * La pruebas sobre el método <i><b>isEmpty</b></i> de {@link StringUtil}
	 * 
	 * @see StringUtil#isEmpty(String)
	 */
	@Test
	public void testIsEmpty() {

		// Valor nulo.
		Assert.assertEquals(true, StringUtil.isEmpty(null));

		// Cadenas vacias o con espacios.
		Assert.assertEquals(true, StringUtil.isEmpty(""));
		Assert.assertEquals(false, StringUtil.isEmpty(" "));
		Assert.assertEquals(false, StringUtil.isEmpty("  "));

		// Cadenas con datos.
		Assert.assertEquals(false, StringUtil.isEmpty("bob"));
		Assert.assertEquals(false, StringUtil.isEmpty(" bob "));
		Assert.assertEquals(false, StringUtil.isEmpty("  bob  "));
	}

	/**
	 * La pruebas sobre el método <i><b>isBlank</b></i> de {@link StringUtil}
	 * 
	 * @see StringUtil#isBlank(String)
	 */
	@Test
	public void testIsBlank() {

		// Valor nulo.
		Assert.assertEquals(true, StringUtil.isBlank(null));

		// Cadenas vacias o con espacios.
		Assert.assertEquals(true, StringUtil.isBlank(""));
		Assert.assertEquals(true, StringUtil.isBlank(" "));
		Assert.assertEquals(true, StringUtil.isBlank("  "));

		// Cadenas con datos.
		Assert.assertEquals(false, StringUtil.isBlank("bob"));
		Assert.assertEquals(false, StringUtil.isBlank(" bob "));
		Assert.assertEquals(false, StringUtil.isBlank("  bob  "));
	}

	/**
	 * La pruebas sobre el método <i><b>match</b></i> de {@link StringUtil}
	 * 
	 * @see StringUtil#match(String, String)
	 * @see PatternUtil
	 */
	@Test
	public void testMatch() {

		// Valor nulo.
		Assert.assertEquals(true, StringUtil.match(null, null));

		// Cadenas vacias o con espacios.
		Assert.assertEquals(true, StringUtil.match("", ""));
		Assert.assertEquals(false, StringUtil.match(" ", ""));
		Assert.assertEquals(false, StringUtil.match(" ", ""));
		Assert.assertEquals(false, StringUtil.match("  ", ""));

		// Cadenas con datos.
		Assert.assertEquals(true, StringUtil.match("a", "[a-z]"));
		Assert.assertEquals(false, StringUtil.match("  a  ", "[a-z]"));
		Assert.assertEquals(true, StringUtil.match(" ", "[a-z\\s]"));
		Assert.assertEquals(false, StringUtil.match("a", "[A-Z]"));
		Assert.assertEquals(true, StringUtil.match("A", "[A-Z]"));

		Assert.assertEquals(true, StringUtil.match("asd", "[a-z]*"));
		Assert.assertEquals(false, StringUtil.match("  asd  ", "[a-z]*"));
		Assert.assertEquals(true, StringUtil.match("  asd  ", "[a-z\\s]*"));
		Assert.assertEquals(false, StringUtil.match("asd", "[A-Z]*"));
		Assert.assertEquals(true, StringUtil.match("ASD", "[A-Z]*"));
		
		// Pruebas de los patrones que tenemos.
		// CUIT.
		Assert.assertEquals(false, StringUtil.match(null, PatternUtil.CUIT_PATTERN));
		Assert.assertEquals(false, StringUtil.match("", PatternUtil.CUIT_PATTERN));
		Assert.assertEquals(false, StringUtil.match(" ", PatternUtil.CUIT_PATTERN));
		Assert.assertEquals(false, StringUtil.match("a", PatternUtil.CUIT_PATTERN));
		Assert.assertEquals(false, StringUtil.match("as", PatternUtil.CUIT_PATTERN));
		Assert.assertEquals(false, StringUtil.match("asd", PatternUtil.CUIT_PATTERN));
		Assert.assertEquals(false, StringUtil.match("1", PatternUtil.CUIT_PATTERN));
		Assert.assertEquals(false, StringUtil.match("2012345678", PatternUtil.CUIT_PATTERN));
		Assert.assertEquals(false, StringUtil.match("20123456789", PatternUtil.CUIT_PATTERN));
		Assert.assertEquals(false, StringUtil.match("20 1234567 8", PatternUtil.CUIT_PATTERN));
		Assert.assertEquals(false, StringUtil.match("20 12345678 9", PatternUtil.CUIT_PATTERN));
		Assert.assertEquals(false, StringUtil.match("20/1234567/8", PatternUtil.CUIT_PATTERN));
		Assert.assertEquals(false, StringUtil.match("20/12345678/9", PatternUtil.CUIT_PATTERN));
		
		Assert.assertEquals(true, StringUtil.match("20-1234567-8", PatternUtil.CUIT_PATTERN));
		Assert.assertEquals(true, StringUtil.match("99-9999999-9", PatternUtil.CUIT_PATTERN));
		Assert.assertEquals(true, StringUtil.match("20-12345678-9", PatternUtil.CUIT_PATTERN));
		Assert.assertEquals(true, StringUtil.match("99-99999999-9", PatternUtil.CUIT_PATTERN));
	}
}