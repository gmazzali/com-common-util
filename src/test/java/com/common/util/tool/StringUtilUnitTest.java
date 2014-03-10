package com.common.util.tool;

import java.util.Arrays;

import junit.framework.Assert;

import org.junit.Test;

/**
 * La clase que permite probar los m�todos de manejo de Strings base de la libreria.
 * 
 * @since 06/03/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class StringUtilUnitTest {

	/**
	 * La pruebas sobre el m�todo <i><b>isEmpty</b></i> de {@link StringUtil}.
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
	 * La pruebas sobre el m�todo <i><b>isBlank</b></i> de {@link StringUtil}.
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
	 * La pruebas sobre el m�todo <i><b>arrayToString</b></i> de {@link StringUtil}.
	 * 
	 * @see StringUtil#arrayToString(Character...)
	 */
	@Test
	public void testArrayToString() {

		// Valor nulo.
		Assert.assertEquals("", StringUtil.arrayToString());
		Assert.assertEquals("", StringUtil.arrayToString((Character) null));

		// Cadenas vacias o con espacios.
		Assert.assertEquals(" ", StringUtil.arrayToString(' '));
		Assert.assertEquals("  ", StringUtil.arrayToString(' ', ' '));

		// Cadenas con datos.
		Assert.assertEquals("a", StringUtil.arrayToString('a'));
		Assert.assertEquals("ab", StringUtil.arrayToString('a', 'b'));
		Assert.assertEquals("abc", StringUtil.arrayToString('a', 'b', 'c'));
		Assert.assertEquals("abcd", StringUtil.arrayToString('a', 'b', 'c', 'd'));
		Assert.assertEquals("abcd", StringUtil.arrayToString('a', null, 'b', null, 'c', null, 'd'));
	}

	/**
	 * La pruebas sobre el m�todo <i><b>stringToArray</b></i> de {@link StringUtil}.
	 * 
	 * @see StringUtil#stringToArray(String)
	 */
	@Test
	public void testStringToArray() {

		// Valor nulo.
		Assert.assertTrue(Arrays.deepEquals(new Character[] {}, StringUtil.stringToArray(null)));

		// Cadenas vacias o con espacios.
		Assert.assertTrue(Arrays.deepEquals(new Character[] {}, StringUtil.stringToArray("")));
		Assert.assertTrue(Arrays.deepEquals(new Character[] { ' ' }, StringUtil.stringToArray(" ")));
		Assert.assertTrue(Arrays.deepEquals(new Character[] { ' ', ' ' }, StringUtil.stringToArray("  ")));

		// Cadenas con datos.
		Assert.assertTrue(Arrays.deepEquals(new Character[] { 'a', 'b', 'c' }, StringUtil.stringToArray("abc")));
		Assert.assertTrue(Arrays.deepEquals(new Character[] { ' ', 'a', 'b', 'c', ' ' }, StringUtil.stringToArray(" abc ")));
	}

	/**
	 * La pruebas sobre los m�todos <i><b>trim</b></i>, <i><b>trimToEmpty</b></i> y <i><b>trimToNull</b></i> de {@link StringUtil}.
	 * 
	 * @see StringUtil#trim(String)
	 * @see StringUtil#trimToEmpty(String)
	 * @see StringUtil#trimToNull(String)
	 */
	@Test
	public void testTrim() {

		// Valor nulo.
		Assert.assertEquals(null, StringUtil.trim(null));
		Assert.assertEquals("", StringUtil.trimToEmpty(null));
		Assert.assertEquals(null, StringUtil.trimToNull(null));

		// Cadenas vacias o con espacios.
		Assert.assertEquals("", StringUtil.trim(""));
		Assert.assertEquals("", StringUtil.trim(" "));
		Assert.assertEquals("", StringUtil.trim("  "));

		Assert.assertEquals("", StringUtil.trimToEmpty(""));
		Assert.assertEquals("", StringUtil.trimToEmpty(" "));
		Assert.assertEquals("", StringUtil.trimToEmpty("  "));

		Assert.assertEquals(null, StringUtil.trimToNull(""));
		Assert.assertEquals(null, StringUtil.trimToNull(" "));
		Assert.assertEquals(null, StringUtil.trimToNull("  "));

		// Cadenas con datos.
		Assert.assertEquals("bob", StringUtil.trim("bob"));
		Assert.assertEquals("bob", StringUtil.trim(" bob "));
		Assert.assertEquals("b o b", StringUtil.trim("  b o b  "));

		Assert.assertEquals("bob", StringUtil.trimToEmpty("bob"));
		Assert.assertEquals("bob", StringUtil.trimToEmpty(" bob "));
		Assert.assertEquals("b o b", StringUtil.trimToEmpty("  b o b  "));

		Assert.assertEquals("bob", StringUtil.trimToNull("bob"));
		Assert.assertEquals("bob", StringUtil.trimToNull(" bob "));
		Assert.assertEquals("b o b", StringUtil.trimToNull("  b o b  "));
	}

	/**
	 * La pruebas sobre los m�todos <i><b>strip</b></i>, <i><b>stripEnd</b></i>, <i><b>stripStart</b></i>, <i><b>stripToEmpty</b></i> y
	 * <i><b>stripToNull</b></i> de {@link StringUtil}.
	 * 
	 * @see StringUtil#strip(String, Character...)
	 * @see StringUtil#stripEnd(String, Character...)
	 * @see StringUtil#stripStart(String, Character...)
	 * @see StringUtil#stripToEmpty(String, Character...)
	 * @see StringUtil#stripToNull(String, Character...)
	 */
	@Test
	public void testStrip() {

		// Valor nulo.
		Assert.assertEquals(null, StringUtil.strip(null));
		Assert.assertEquals(null, StringUtil.stripEnd(null));
		Assert.assertEquals(null, StringUtil.stripStart(null));
		Assert.assertEquals("", StringUtil.stripToEmpty(null));
		Assert.assertEquals(null, StringUtil.stripToNull(null));

		Assert.assertEquals(null, StringUtil.strip(null, (Character) null));
		Assert.assertEquals(null, StringUtil.stripEnd(null, (Character) null));
		Assert.assertEquals(null, StringUtil.stripStart(null, (Character) null));
		Assert.assertEquals("", StringUtil.stripToEmpty(null, (Character) null));
		Assert.assertEquals(null, StringUtil.stripToNull(null, (Character) null));

		// Cadenas vacias o con espacios.
		Assert.assertEquals("", StringUtil.strip(""));
		Assert.assertEquals("", StringUtil.stripEnd(""));
		Assert.assertEquals("", StringUtil.stripStart(""));
		Assert.assertEquals("", StringUtil.stripToEmpty(""));
		Assert.assertEquals(null, StringUtil.stripToNull(""));

		Assert.assertEquals("", StringUtil.strip(" "));
		Assert.assertEquals("", StringUtil.stripEnd(" "));
		Assert.assertEquals("", StringUtil.stripStart(" "));
		Assert.assertEquals("", StringUtil.stripToEmpty(" "));
		Assert.assertEquals(null, StringUtil.stripToNull(" "));

		Assert.assertEquals("", StringUtil.strip("", (Character) null));
		Assert.assertEquals("", StringUtil.stripEnd("", (Character) null));
		Assert.assertEquals("", StringUtil.stripStart("", (Character) null));
		Assert.assertEquals("", StringUtil.stripToEmpty("", (Character) null));
		Assert.assertEquals(null, StringUtil.stripToNull("", (Character) null));

		Assert.assertEquals("", StringUtil.strip(" ", (Character) null));
		Assert.assertEquals("", StringUtil.stripEnd(" ", (Character) null));
		Assert.assertEquals("", StringUtil.stripStart(" ", (Character) null));
		Assert.assertEquals("", StringUtil.stripToEmpty(" ", (Character) null));
		Assert.assertEquals(null, StringUtil.stripToNull(" ", (Character) null));

		Assert.assertEquals("", StringUtil.strip("", ' '));
		Assert.assertEquals("", StringUtil.stripEnd("", ' '));
		Assert.assertEquals("", StringUtil.stripStart("", ' '));
		Assert.assertEquals("", StringUtil.stripToEmpty("", ' '));
		Assert.assertEquals(null, StringUtil.stripToNull("", ' '));

		Assert.assertEquals("", StringUtil.strip(" ", ' '));
		Assert.assertEquals("", StringUtil.stripEnd(" ", ' '));
		Assert.assertEquals("", StringUtil.stripStart(" ", ' '));
		Assert.assertEquals("", StringUtil.stripToEmpty(" ", ' '));
		Assert.assertEquals(null, StringUtil.stripToNull(" ", ' '));

		Assert.assertEquals("", StringUtil.strip("", 'a'));
		Assert.assertEquals("", StringUtil.stripEnd("", 'a'));
		Assert.assertEquals("", StringUtil.stripStart("", 'a'));
		Assert.assertEquals("", StringUtil.stripToEmpty("", 'a'));
		Assert.assertEquals(null, StringUtil.stripToNull("", 'a'));

		Assert.assertEquals(" ", StringUtil.strip(" ", 'a'));
		Assert.assertEquals(" ", StringUtil.stripEnd(" ", 'a'));
		Assert.assertEquals(" ", StringUtil.stripStart(" ", 'a'));
		Assert.assertEquals(" ", StringUtil.stripToEmpty(" ", 'a'));
		Assert.assertEquals(" ", StringUtil.stripToNull(" ", 'a'));

		// Cadenas con datos.
		Assert.assertEquals("abc", StringUtil.strip("abc"));
		Assert.assertEquals("abc", StringUtil.strip("abc "));
		Assert.assertEquals("abc", StringUtil.strip(" abc"));
		Assert.assertEquals("abc", StringUtil.strip(" abc "));
		Assert.assertEquals("a b c", StringUtil.strip(" a b c "));
		Assert.assertEquals("abc", StringUtil.strip("xyabcyx", 'x', 'y', 'z'));
		Assert.assertEquals("", StringUtil.strip("xyzyx", 'x', 'y', 'z'));

		Assert.assertEquals("abc", StringUtil.stripEnd("abc"));
		Assert.assertEquals("abc", StringUtil.stripEnd("abc "));
		Assert.assertEquals(" abc", StringUtil.stripEnd(" abc"));
		Assert.assertEquals(" abc", StringUtil.stripEnd(" abc "));
		Assert.assertEquals(" a b c", StringUtil.stripEnd(" a b c "));
		Assert.assertEquals("xyabc", StringUtil.stripEnd("xyabcyx", 'x', 'y', 'z'));
		Assert.assertEquals("xyz", StringUtil.stripEnd("xyzyx", 'x', 'y'));
		Assert.assertEquals("", StringUtil.stripEnd("xyzyx", 'x', 'y', 'z'));

		Assert.assertEquals("abc", StringUtil.stripStart("abc"));
		Assert.assertEquals("abc ", StringUtil.stripStart("abc "));
		Assert.assertEquals("abc", StringUtil.stripStart(" abc"));
		Assert.assertEquals("abc ", StringUtil.stripStart(" abc "));
		Assert.assertEquals("a b c ", StringUtil.stripStart(" a b c "));
		Assert.assertEquals("abcyx", StringUtil.stripStart("xyabcyx", 'x', 'y', 'z'));
		Assert.assertEquals("zyx", StringUtil.stripStart("xyzyx", 'x', 'y'));
		Assert.assertEquals("", StringUtil.stripStart("xyzyx", 'x', 'y', 'z'));

		Assert.assertEquals("abc", StringUtil.stripToEmpty("abc"));
		Assert.assertEquals("abc", StringUtil.stripToEmpty("abc "));
		Assert.assertEquals("abc", StringUtil.stripToEmpty(" abc"));
		Assert.assertEquals("abc", StringUtil.stripToEmpty(" abc "));
		Assert.assertEquals("a b c", StringUtil.stripToEmpty(" a b c "));
		Assert.assertEquals("abc", StringUtil.stripToEmpty("xyabcyx", 'x', 'y', 'z'));
		Assert.assertEquals("", StringUtil.stripToEmpty("xyzyx", 'x', 'y', 'z'));

		Assert.assertEquals("abc", StringUtil.stripToNull("abc"));
		Assert.assertEquals("abc", StringUtil.stripToNull("abc "));
		Assert.assertEquals("abc", StringUtil.stripToNull(" abc"));
		Assert.assertEquals("abc", StringUtil.stripToNull(" abc "));
		Assert.assertEquals("a b c", StringUtil.stripToNull(" a b c "));
		Assert.assertEquals("abc", StringUtil.stripToNull("xyabcyx", 'x', 'y', 'z'));
		Assert.assertEquals(null, StringUtil.stripToNull("xyzyx", 'x', 'y', 'z'));
	}

	/**
	 * La pruebas sobre el m�todo <i><b>match</b></i> de {@link StringUtil}.
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