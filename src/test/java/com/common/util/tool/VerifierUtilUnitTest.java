package com.common.util.tool;

import java.io.Serializable;

import junit.framework.Assert;

import org.junit.Test;

/**
 * La clase que permite probar los métodos de verificación base de la libreria.
 * 
 * @since 05/03/2014
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

	/**
	 * La pruebas sobre el método <i><b>isEqual</b></i> de {@link VerifierUtil}
	 * 
	 * @see VerifierUtil#equals(Serializable, Serializable)
	 */
	@Test
	public void testIsEqual() {

		// Valores nulos.
		Assert.assertEquals(true, VerifierUtil.<String>equals(null, null));
		
		// Cadenas.
		Assert.assertEquals(false, VerifierUtil.<String>equals(null, "bob"));
		Assert.assertEquals(false, VerifierUtil.<String>equals("bob", null));
		Assert.assertEquals(true, VerifierUtil.<String>equals("bob_1", "bob_1"));
		Assert.assertEquals(false, VerifierUtil.<String>equals("bob_1", "bob_2"));
		
		// Enteros.
		Assert.assertEquals(false, VerifierUtil.<Integer>equals(null, 10));
		Assert.assertEquals(false, VerifierUtil.<Integer>equals(10, null));
		Assert.assertEquals(true, VerifierUtil.<Integer>equals(10, 10));
		Assert.assertEquals(false, VerifierUtil.<Integer>equals(10, 11));
		
		// Dobles.
		Assert.assertEquals(false, VerifierUtil.<Double>equals(null, 10.0));
		Assert.assertEquals(false, VerifierUtil.<Double>equals(10.0, null));
		Assert.assertEquals(true, VerifierUtil.<Double>equals(10.0, 10.0));
		Assert.assertEquals(true, VerifierUtil.<Double>equals(10.5, 10.5));
		Assert.assertEquals(false, VerifierUtil.<Double>equals(10.0, 11.0));
		Assert.assertEquals(false, VerifierUtil.<Double>equals(10.5, 11.5));
	}
}