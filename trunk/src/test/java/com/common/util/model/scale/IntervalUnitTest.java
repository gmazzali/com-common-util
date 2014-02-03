package com.common.util.model.scale;

import junit.framework.Assert;

import org.junit.Test;

import com.common.util.exception.UncheckedException;

/**
 * Clase de prueba que permite validar el correcto funcionamiento de la clase que representa un rango de valores.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class IntervalUnitTest {

	/**
	 * La pruebas sobre el método <i><b>validateInterval</b></i> de {@link Interval} de los rangos por omisión <i>[min; max)</i>.
	 * 
	 * @see Interval#validateInterval()
	 */
	@Test
	public void testValidateInterval() {
		Interval<Integer> interval;

		try {
			interval = new Interval<Integer>(null, null);
			interval.validateInterval();
			Assert.fail();
		} catch (UncheckedException e) {
		}

		try {
			interval = new Interval<Integer>(null, 0);
			interval.validateInterval();
			Assert.fail();
		} catch (UncheckedException e) {
		}

		try {
			interval = new Interval<Integer>(0, null);
			interval.validateInterval();
			Assert.fail();
		} catch (UncheckedException e) {
		}

		try {
			interval = new Interval<Integer>(1, 0);
			interval.validateInterval();
			Assert.fail();
		} catch (UncheckedException e) {
		}

		try {
			interval = new Interval<Integer>(10, 9);
			interval.validateInterval();
			Assert.fail();
		} catch (UncheckedException e) {
		}

		try {
			interval = new Interval<Integer>(19, 10);
			interval.validateInterval();
			Assert.fail();
		} catch (UncheckedException e) {
		}

		try {
			interval = new Interval<Integer>(0, 0);
			interval.validateInterval();

			interval = new Interval<Integer>(0, 1);
			interval.validateInterval();

			interval = new Interval<Integer>(0, 10);
			interval.validateInterval();

			interval = new Interval<Integer>(0, 20);
			interval.validateInterval();

			interval = new Interval<Integer>(10, 20);
			interval.validateInterval();

			interval = new Interval<Integer>(19, 20);
			interval.validateInterval();

			interval = new Interval<Integer>(21, 22);
			interval.validateInterval();

		} catch (UncheckedException e) {
			Assert.fail();
		}
	}
}