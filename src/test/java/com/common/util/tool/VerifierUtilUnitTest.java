package com.common.util.tool;

import junit.framework.Assert;

import org.junit.Test;

import com.common.util.tool.VerifierUtil;

/**
 * La clase que permite probar los métodos de verificación base de la libreria.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class VerifierUtilUnitTest {
	/**
	 * La pruebas sobre el método <i><b>isEmpty</b></i> de {@link VerifierUtil}
	 * 
	 * @see VerifierUtil#isEmpty(String)
	 */
	@Test
	public void testIsEmpty() {

		// Valor nulo.
		Assert.assertEquals(true, VerifierUtil.isEmpty(null));

		// Cadenas vacias o con espacios.
		Assert.assertEquals(true, VerifierUtil.isEmpty(""));
		Assert.assertEquals(false, VerifierUtil.isEmpty(" "));
		Assert.assertEquals(false, VerifierUtil.isEmpty("  "));

		// Cadenas con datos.
		Assert.assertEquals(false, VerifierUtil.isEmpty("bob"));
		Assert.assertEquals(false, VerifierUtil.isEmpty(" bob "));
		Assert.assertEquals(false, VerifierUtil.isEmpty("  bob  "));
	}

	/**
	 * La pruebas sobre el método <i><b>isBlank</b></i> de {@link VerifierUtil}
	 * 
	 * @see VerifierUtil#isBlank(String)
	 */
	@Test
	public void testIsBlank() {

		// Valor nulo.
		Assert.assertEquals(true, VerifierUtil.isBlank(null));

		// Cadenas vacias o con espacios.
		Assert.assertEquals(true, VerifierUtil.isBlank(""));
		Assert.assertEquals(true, VerifierUtil.isBlank(" "));
		Assert.assertEquals(true, VerifierUtil.isBlank("  "));

		// Cadenas con datos.
		Assert.assertEquals(false, VerifierUtil.isBlank("bob"));
		Assert.assertEquals(false, VerifierUtil.isBlank(" bob "));
		Assert.assertEquals(false, VerifierUtil.isBlank("  bob  "));
	}
}