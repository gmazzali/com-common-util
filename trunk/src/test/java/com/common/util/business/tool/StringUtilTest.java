package com.common.util.business.tool;

import java.util.Arrays;

import org.apache.log4j.BasicConfigurator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.common.util.business.util.Patterns;

/**
 * La clase que permite probar los métodos de manejo de Strings base de la libreria.
 * 
 * @since 06/03/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class StringUtilTest {

	@BeforeClass
	public static void initClass() {
		BasicConfigurator.configure();
	}

	/**
	 * La pruebas sobre el método <i><b>isEmpty</b></i> de {@link StringUtil}.
	 * 
	 * @see StringUtil#isEmpty(String)
	 */
	@Test
	public void testIsEmpty() {
		// Valor nulo.
		Assert.assertTrue(StringUtil.isEmpty(null));

		// Cadenas vacias o con espacios.
		Assert.assertTrue(StringUtil.isEmpty(""));
		Assert.assertFalse(StringUtil.isEmpty(" "));
		Assert.assertFalse(StringUtil.isEmpty("  "));

		// Cadenas con datos.
		Assert.assertFalse(StringUtil.isEmpty("bob"));
		Assert.assertFalse(StringUtil.isEmpty(" bob "));
		Assert.assertFalse(StringUtil.isEmpty("  bob  "));
	}

	/**
	 * La pruebas sobre el método <i><b>isNotEmpty</b></i> de {@link StringUtil}.
	 * 
	 * @see StringUtil#isNotEmpty(String)
	 */
	@Test
	public void testIsNotEmpty() {
		// Valor nulo.
		Assert.assertFalse(StringUtil.isNotEmpty(null));

		// Cadenas vacias o con espacios.
		Assert.assertFalse(StringUtil.isNotEmpty(""));
		Assert.assertTrue(StringUtil.isNotEmpty(" "));
		Assert.assertTrue(StringUtil.isNotEmpty("  "));

		// Cadenas con datos.
		Assert.assertTrue(StringUtil.isNotEmpty("bob"));
		Assert.assertTrue(StringUtil.isNotEmpty(" bob "));
		Assert.assertTrue(StringUtil.isNotEmpty("  bob  "));
	}

	/**
	 * La pruebas sobre el método <i><b>isBlank</b></i> de {@link StringUtil}.
	 * 
	 * @see StringUtil#isBlank(String)
	 */
	@Test
	public void testIsBlank() {
		// Valor nulo.
		Assert.assertTrue(StringUtil.isBlank(null));

		// Cadenas vacias o con espacios.
		Assert.assertTrue(StringUtil.isBlank(""));
		Assert.assertTrue(StringUtil.isBlank(" "));
		Assert.assertTrue(StringUtil.isBlank("  "));

		// Cadenas con datos.
		Assert.assertFalse(StringUtil.isBlank("bob"));
		Assert.assertFalse(StringUtil.isBlank(" bob "));
		Assert.assertFalse(StringUtil.isBlank("  bob  "));
	}

	/**
	 * La pruebas sobre el método <i><b>isNotBlank</b></i> de {@link StringUtil}.
	 * 
	 * @see StringUtil#isNotBlank(String)
	 */
	@Test
	public void testIsNotBlank() {
		// Valor nulo.
		Assert.assertFalse(StringUtil.isNotBlank(null));

		// Cadenas vacias o con espacios.
		Assert.assertFalse(StringUtil.isNotBlank(""));
		Assert.assertFalse(StringUtil.isNotBlank(" "));
		Assert.assertFalse(StringUtil.isNotBlank("  "));

		// Cadenas con datos.
		Assert.assertTrue(StringUtil.isNotBlank("bob"));
		Assert.assertTrue(StringUtil.isNotBlank(" bob "));
		Assert.assertTrue(StringUtil.isNotBlank("  bob  "));
	}

	/**
	 * La pruebas sobre el método <i><b>arrayToString</b></i> de {@link StringUtil}.
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
	 * La pruebas sobre el método <i><b>stringToArray</b></i> de {@link StringUtil}.
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
	 * La pruebas sobre los métodos <i><b>trim</b></i>, <i><b>trimToEmpty</b></i> y <i><b>trimToNull</b></i> de {@link StringUtil}.
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
	 * La pruebas sobre los métodos <i><b>strip</b></i>, <i><b>stripEnd</b></i>, <i><b>stripStart</b></i>, <i><b>stripToEmpty</b></i> y
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
	 * La pruebas sobre el método <i><b>match</b></i> de {@link StringUtil}.
	 * 
	 * @see StringUtil#match(String, String)
	 * @see Patterns
	 */
	@Test
	public void testMatch() {
		// Valor nulo.
		Assert.assertTrue(StringUtil.match(null, null));

		// Cadenas vacias o con espacios.
		Assert.assertTrue(StringUtil.match("", ""));
		Assert.assertFalse(StringUtil.match(" ", ""));
		Assert.assertFalse(StringUtil.match(" ", ""));
		Assert.assertFalse(StringUtil.match("  ", ""));

		// Cadenas con datos.
		Assert.assertTrue(StringUtil.match("a", "[a-z]"));
		Assert.assertFalse(StringUtil.match("  a  ", "[a-z]"));
		Assert.assertTrue(StringUtil.match(" ", "[a-z\\s]"));
		Assert.assertFalse(StringUtil.match("a", "[A-Z]"));
		Assert.assertTrue(StringUtil.match("A", "[A-Z]"));

		Assert.assertTrue(StringUtil.match("asd", "[a-z]*"));
		Assert.assertFalse(StringUtil.match("  asd  ", "[a-z]*"));
		Assert.assertTrue(StringUtil.match("  asd  ", "[a-z\\s]*"));
		Assert.assertFalse(StringUtil.match("asd", "[A-Z]*"));
		Assert.assertTrue(StringUtil.match("ASD", "[A-Z]*"));

		// Pruebas de los patrones que tenemos.
		// CUIT.
		Assert.assertFalse(StringUtil.match(null, Patterns.CUIT_PATTERN.getPattern()));
		Assert.assertFalse(StringUtil.match("", Patterns.CUIT_PATTERN.getPattern()));
		Assert.assertFalse(StringUtil.match(" ", Patterns.CUIT_PATTERN.getPattern()));
		Assert.assertFalse(StringUtil.match("a", Patterns.CUIT_PATTERN.getPattern()));
		Assert.assertFalse(StringUtil.match("as", Patterns.CUIT_PATTERN.getPattern()));
		Assert.assertFalse(StringUtil.match("asd", Patterns.CUIT_PATTERN.getPattern()));
		Assert.assertFalse(StringUtil.match("1", Patterns.CUIT_PATTERN.getPattern()));
		Assert.assertFalse(StringUtil.match("2012345678", Patterns.CUIT_PATTERN.getPattern()));
		Assert.assertFalse(StringUtil.match("20123456789", Patterns.CUIT_PATTERN.getPattern()));
		Assert.assertFalse(StringUtil.match("20 1234567 8", Patterns.CUIT_PATTERN.getPattern()));
		Assert.assertFalse(StringUtil.match("20 12345678 9", Patterns.CUIT_PATTERN.getPattern()));
		Assert.assertFalse(StringUtil.match("20/1234567/8", Patterns.CUIT_PATTERN.getPattern()));
		Assert.assertFalse(StringUtil.match("20/12345678/9", Patterns.CUIT_PATTERN.getPattern()));

		Assert.assertTrue(StringUtil.match("20-1234567-8", Patterns.CUIT_PATTERN.getPattern()));
		Assert.assertTrue(StringUtil.match("99-9999999-9", Patterns.CUIT_PATTERN.getPattern()));
		Assert.assertTrue(StringUtil.match("20-12345678-9", Patterns.CUIT_PATTERN.getPattern()));
		Assert.assertTrue(StringUtil.match("99-99999999-9", Patterns.CUIT_PATTERN.getPattern()));
	}
}