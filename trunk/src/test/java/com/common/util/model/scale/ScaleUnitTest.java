package com.common.util.model.scale;

import junit.framework.Assert;

import org.junit.Test;

import com.common.util.exception.UncheckedException;

/**
 * Clase que prueba la correcta validación de una eescla de valores dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class ScaleUnitTest {

	/**
	 * La pruebas sobre el método <i><b>validateScale</b></i> de {@link Scale}.
	 * 
	 * @see Scale#validateScale()
	 */
	@Test
	public void testValidateScale() {
		Scale<Interval<Double>, Double> scale;

		try {
			scale = new Scale<Interval<Double>, Double>();
			scale.validateScale();
			Assert.fail();
		} catch (UncheckedException e) {
		}

		try {
			scale = new Scale<Interval<Double>, Double>();
			scale.addInterval(new Interval<Double>(0.0, 0.0));
			scale.validateScale();
		} catch (UncheckedException e) {
			Assert.fail();
		}

		try {
			scale = new Scale<Interval<Double>, Double>();
			scale.addInterval(new Interval<Double>(2.0, 0.0));
			scale.validateScale();
			Assert.fail();
		} catch (UncheckedException e) {
		}

		try {
			scale = new Scale<Interval<Double>, Double>();
			scale.addInterval(new Interval<Double>(0.0, 1.0));
			scale.addInterval(new Interval<Double>(1.0, 2.0));
			scale.addInterval(new Interval<Double>(1.0, 3.0));
			scale.validateScale();
			Assert.fail();
		} catch (UncheckedException e) {
		}

		try {
			scale = new Scale<Interval<Double>, Double>();
			scale.addInterval(new Interval<Double>(0.0, 1.0));
			scale.addInterval(new Interval<Double>(1.0, 2.0));
			scale.addInterval(new Interval<Double>(3.0, 5.0));
			scale.validateScale();
			Assert.fail();
		} catch (UncheckedException e) {
		}

		try {
			scale = new Scale<Interval<Double>, Double>();
			scale.addInterval(new Interval<Double>(0.0, 1.0));
			scale.addInterval(new Interval<Double>(1.0, 2.0));
			scale.addInterval(new Interval<Double>(2.0, 3.0));
			scale.addInterval(new Interval<Double>(3.0, 4.0));
			scale.validateScale();
		} catch (UncheckedException e) {
			Assert.fail();
		}

		try {
			scale = new Scale<Interval<Double>, Double>();
			scale.addInterval(new Interval<Double>(0.0, 10.0));
			scale.addInterval(new Interval<Double>(10.0, 20.0));
			scale.addInterval(new Interval<Double>(20.0, 30.0));
			scale.addInterval(new Interval<Double>(30.0, 40.0));
			scale.validateScale();
		} catch (UncheckedException e) {
			Assert.fail();
		}

		try {
			scale = new Scale<Interval<Double>, Double>();
			scale.addInterval(new Interval<Double>(20.0, 30.0));
			scale.addInterval(new Interval<Double>(30.0, 40.0));
			scale.addInterval(new Interval<Double>(0.0, 10.0));
			scale.addInterval(new Interval<Double>(10.0, 20.0));
			scale.validateScale();
		} catch (UncheckedException e) {
			Assert.fail();
		}

		try {
			scale = new Scale<Interval<Double>, Double>();
			scale.addInterval(new Interval<Double>(-20.0, -10.0));
			scale.addInterval(new Interval<Double>(-10.0, 0.0));
			scale.addInterval(new Interval<Double>(0.0, 10.0));
			scale.addInterval(new Interval<Double>(10.0, 20.0));
			scale.validateScale();
		} catch (UncheckedException e) {
			Assert.fail();
		}
	}

	/**
	 * La pruebas sobre el método <i><b>getIntervalForValue</b></i> de {@link Scale}.
	 * 
	 * @see Scale#getIntervalForValue(Number)
	 */
	@Test
	public void testGetIntervalForValue() {
		Scale<Interval<Integer>, Integer> scale;

		scale = new Scale<Interval<Integer>, Integer>();
		scale.addInterval(new Interval<Integer>(0, 10));
		scale.addInterval(new Interval<Integer>(10, 20));
		scale.addInterval(new Interval<Integer>(20, 30));
		scale.addInterval(new Interval<Integer>(30, 40));

		Assert.assertEquals(new Interval<Integer>(0, 10), scale.getIntervalForValue(0));
		Assert.assertEquals(new Interval<Integer>(0, 10), scale.getIntervalForValue(1));
		Assert.assertEquals(new Interval<Integer>(0, 10), scale.getIntervalForValue(9));
		Assert.assertEquals(new Interval<Integer>(10, 20), scale.getIntervalForValue(10));
		Assert.assertEquals(new Interval<Integer>(10, 20), scale.getIntervalForValue(11));
		Assert.assertEquals(new Interval<Integer>(10, 20), scale.getIntervalForValue(19));
		Assert.assertEquals(new Interval<Integer>(20, 30), scale.getIntervalForValue(20));
		Assert.assertEquals(new Interval<Integer>(20, 30), scale.getIntervalForValue(21));
		Assert.assertEquals(new Interval<Integer>(20, 30), scale.getIntervalForValue(29));
		Assert.assertEquals(new Interval<Integer>(30, 40), scale.getIntervalForValue(30));
		Assert.assertEquals(new Interval<Integer>(30, 40), scale.getIntervalForValue(31));
		Assert.assertEquals(new Interval<Integer>(30, 40), scale.getIntervalForValue(39));
		Assert.assertEquals(null, scale.getIntervalForValue(40));
		Assert.assertEquals(null, scale.getIntervalForValue(41));
	}

	/**
	 * La pruebas sobre el método <i><b>getLowerValue</b></i> de {@link Scale}.
	 * 
	 * @see Scale#getLowerValue()
	 */
	@Test
	public void testGetLowerValue() {
		Scale<Interval<Integer>, Integer> scale;

		scale = new Scale<Interval<Integer>, Integer>();
		Assert.assertEquals(null, scale.getLowerValue());

		scale = new Scale<Interval<Integer>, Integer>();
		scale.addInterval(new Interval<Integer>(9, 10));
		Assert.assertEquals(new Integer(9), scale.getLowerValue());

		scale = new Scale<Interval<Integer>, Integer>();
		scale.addInterval(new Interval<Integer>(0, 10));
		Assert.assertEquals(new Integer(0), scale.getLowerValue());

		scale = new Scale<Interval<Integer>, Integer>();
		scale.addInterval(new Interval<Integer>(0, 10));
		scale.addInterval(new Interval<Integer>(10, 20));
		scale.addInterval(new Interval<Integer>(20, 30));
		scale.addInterval(new Interval<Integer>(30, 40));
		Assert.assertEquals(new Integer(0), scale.getLowerValue());

		scale = new Scale<Interval<Integer>, Integer>();
		scale.addInterval(new Interval<Integer>(10000, 20000));
		scale.addInterval(new Interval<Integer>(20000, 30000));
		scale.addInterval(new Interval<Integer>(30000, 40000));
		Assert.assertEquals(new Integer(10000), scale.getLowerValue());

		Scale<Interval<Double>, Double> dScale = new Scale<Interval<Double>, Double>();
		dScale.addInterval(new Interval<Double>(0.0, 0.1));
		dScale.addInterval(new Interval<Double>(0.1, 0.2));
		dScale.addInterval(new Interval<Double>(0.2, 0.3));
		dScale.addInterval(new Interval<Double>(0.3, 0.5));
		Assert.assertEquals(new Double(0.0), dScale.getLowerValue(), 0.0);
	}

	/**
	 * La pruebas sobre el método <i><b>getHigherValue</b></i> de {@link Scale}.
	 * 
	 * @see Scale#getHigherValue()
	 */
	@Test
	public void testGetHigherValue() {
		Scale<Interval<Integer>, Integer> scale;

		scale = new Scale<Interval<Integer>, Integer>();
		Assert.assertEquals(null, scale.getHigherValue());

		scale = new Scale<Interval<Integer>, Integer>();
		scale.addInterval(new Interval<Integer>(9, 10));
		Assert.assertEquals(new Integer(10), scale.getHigherValue());

		scale = new Scale<Interval<Integer>, Integer>();
		scale.addInterval(new Interval<Integer>(0, 10));
		Assert.assertEquals(new Integer(10), scale.getHigherValue());

		scale = new Scale<Interval<Integer>, Integer>();
		scale.addInterval(new Interval<Integer>(0, 10));
		scale.addInterval(new Interval<Integer>(10, 20));
		scale.addInterval(new Interval<Integer>(20, 30));
		scale.addInterval(new Interval<Integer>(30, 40));
		Assert.assertEquals(new Integer(40), scale.getHigherValue());

		scale = new Scale<Interval<Integer>, Integer>();
		scale.addInterval(new Interval<Integer>(10000, 20000));
		scale.addInterval(new Interval<Integer>(20000, 30000));
		scale.addInterval(new Interval<Integer>(30000, 40000));
		Assert.assertEquals(new Integer(40000), scale.getHigherValue());

		Scale<Interval<Double>, Double> dScale = new Scale<Interval<Double>, Double>();
		dScale.addInterval(new Interval<Double>(0.0, 0.1));
		dScale.addInterval(new Interval<Double>(0.1, 0.2));
		dScale.addInterval(new Interval<Double>(0.2, 0.3));
		dScale.addInterval(new Interval<Double>(0.3, 0.5));
		Assert.assertEquals(new Double(0.5), dScale.getHigherValue(), 0.0);
	}
}