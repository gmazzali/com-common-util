package com.common.util.business.tool;

import java.io.Serializable;

import org.apache.log4j.BasicConfigurator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * La clase que permite probar los métodos de verificación base de la libreria.
 * 
 * @since 05/03/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class VerifierUtilTest {

	@BeforeClass
	public static void initClass() {
		BasicConfigurator.configure();
	}

	/**
	 * La pruebas sobre el método <i><b>isEqual</b></i> de {@link VerifierUtil}
	 * 
	 * @see VerifierUtil#equals(Serializable, Serializable)
	 */
	@Test
	public void testIsEqual() {
		// Valores nulos.
		Assert.assertEquals(true, VerifierUtil.<String> equals(null, null));

		// Cadenas.
		Assert.assertEquals(false, VerifierUtil.<String> equals(null, "bob"));
		Assert.assertEquals(false, VerifierUtil.<String> equals("bob", null));
		Assert.assertEquals(true, VerifierUtil.<String> equals("bob_1", "bob_1"));
		Assert.assertEquals(false, VerifierUtil.<String> equals("bob_1", "bob_2"));

		// Enteros.
		Assert.assertEquals(false, VerifierUtil.<Integer> equals(null, 10));
		Assert.assertEquals(false, VerifierUtil.<Integer> equals(10, null));
		Assert.assertEquals(true, VerifierUtil.<Integer> equals(10, 10));
		Assert.assertEquals(false, VerifierUtil.<Integer> equals(10, 11));

		// Dobles.
		Assert.assertEquals(false, VerifierUtil.<Double> equals(null, 10.0));
		Assert.assertEquals(false, VerifierUtil.<Double> equals(10.0, null));
		Assert.assertEquals(true, VerifierUtil.<Double> equals(10.0, 10.0));
		Assert.assertEquals(true, VerifierUtil.<Double> equals(10.5, 10.5));
		Assert.assertEquals(false, VerifierUtil.<Double> equals(10.0, 11.0));
		Assert.assertEquals(false, VerifierUtil.<Double> equals(10.5, 11.5));
	}
}