package com.common.util.model.scale;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.Test;

import com.common.util.exception.UncheckedException;
import com.common.util.model.scale.Interval;
import com.common.util.model.scale.Scale;

/**
 * Clase que prueba la correcta validación de una eescla de valores dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class ScaleUnitTest {

	/**
	 * Cuando se termina de ejecutar la prueba, dejamos un espacio en blanco.
	 */
	@AfterClass
	public static void afterClass() {
		System.out.println();
	}

	/**
	 * Prueba sobre la validación de la escala de valores.
	 */
	@Test
	public void pruebaSobreLaValidacionDeLaEscala() {
		System.out.println("<<<<<<<<<<<<<<< PRUEBA SOBRE LA VALIDACIÓN LA ESCALA DE VALORES >>>>>>>>>>>>>>>");

		Scale<Interval<Double>, Double> scale;

		scale = new Scale<Interval<Double>, Double>();
		try {
			scale.validateintervals();
			Assert.assertTrue(false);
		} catch (UncheckedException e) {
			Assert.assertTrue(true);
		}

		scale = new Scale<Interval<Double>, Double>();
		scale.addInterval(new Interval<Double>(0.0, 0.0));
		try {
			scale.validateintervals();
			Assert.assertTrue(true);
		} catch (UncheckedException e) {
			Assert.assertTrue(false);
		}

		scale = new Scale<Interval<Double>, Double>();
		scale.addInterval(new Interval<Double>(2.0, 0.0));
		try {
			scale.validateintervals();
			Assert.assertTrue(false);
		} catch (UncheckedException e) {
			Assert.assertTrue(true);
		}

		scale = new Scale<Interval<Double>, Double>();
		scale.addInterval(new Interval<Double>(0.0, 1.0));
		scale.addInterval(new Interval<Double>(1.0, 2.0));
		scale.addInterval(new Interval<Double>(1.0, 3.0));
		try {
			scale.validateintervals();
			Assert.assertTrue(false);
		} catch (UncheckedException e) {
			Assert.assertTrue(true);
		}

		scale = new Scale<Interval<Double>, Double>();
		scale.addInterval(new Interval<Double>(0.0, 1.0));
		scale.addInterval(new Interval<Double>(1.0, 2.0));
		scale.addInterval(new Interval<Double>(3.0, 5.0));
		try {
			scale.validateintervals();
			Assert.assertTrue(false);
		} catch (UncheckedException e) {
			Assert.assertTrue(true);
		}

		scale = new Scale<Interval<Double>, Double>();
		scale.addInterval(new Interval<Double>(0.0, 1.0));
		scale.addInterval(new Interval<Double>(1.0, 2.0));
		scale.addInterval(new Interval<Double>(2.0, 3.0));
		scale.addInterval(new Interval<Double>(3.0, 4.0));
		try {
			scale.validateintervals();
			Assert.assertTrue(true);
		} catch (UncheckedException e) {
			Assert.assertTrue(false);
		}

		scale = new Scale<Interval<Double>, Double>();
		scale.addInterval(new Interval<Double>(0.0, 10.0));
		scale.addInterval(new Interval<Double>(10.0, 20.0));
		scale.addInterval(new Interval<Double>(20.0, 30.0));
		scale.addInterval(new Interval<Double>(30.0, 40.0));
		try {
			scale.validateintervals();
			Assert.assertTrue(true);
		} catch (UncheckedException e) {
			Assert.assertTrue(false);
		}

		scale = new Scale<Interval<Double>, Double>();
		scale.addInterval(new Interval<Double>(20.0, 30.0));
		scale.addInterval(new Interval<Double>(30.0, 40.0));
		scale.addInterval(new Interval<Double>(0.0, 10.0));
		scale.addInterval(new Interval<Double>(10.0, 20.0));
		try {
			scale.validateintervals();
			Assert.assertTrue(true);
		} catch (UncheckedException e) {
			Assert.assertTrue(false);
		}
	}

	/**
	 * Prueba sobre la busqueda de rangos a partir de un valor dado.
	 */
	@Test
	public void pruebaDeBusquedaDeRangos() {
		System.out.println("<<<<<<<<<<< PRUEBAS EN LA BUSQUEDA DE UN RANGO DENTRO DE UNA ESCALA >>>>>>>>>>>");

		Scale<Interval<Integer>, Integer> scale;

		scale = new Scale<Interval<Integer>, Integer>();
		scale.addInterval(new Interval<Integer>(0, 10));
		scale.addInterval(new Interval<Integer>(10, 20));
		scale.addInterval(new Interval<Integer>(20, 30));
		scale.addInterval(new Interval<Integer>(30, 40));

		Assert.assertEquals(new Interval<Integer>(0, 10), scale.getInterval(0));
		Assert.assertEquals(new Interval<Integer>(0, 10), scale.getInterval(1));
		Assert.assertEquals(new Interval<Integer>(0, 10), scale.getInterval(9));
		Assert.assertEquals(new Interval<Integer>(10, 20), scale.getInterval(10));
		Assert.assertEquals(new Interval<Integer>(10, 20), scale.getInterval(11));
		Assert.assertEquals(new Interval<Integer>(10, 20), scale.getInterval(19));
		Assert.assertEquals(new Interval<Integer>(20, 30), scale.getInterval(20));
		Assert.assertEquals(new Interval<Integer>(20, 30), scale.getInterval(21));
		Assert.assertEquals(new Interval<Integer>(20, 30), scale.getInterval(29));
		Assert.assertEquals(new Interval<Integer>(30, 40), scale.getInterval(30));
		Assert.assertEquals(new Interval<Integer>(30, 40), scale.getInterval(31));
		Assert.assertEquals(new Interval<Integer>(30, 40), scale.getInterval(39));
		Assert.assertEquals(null, scale.getInterval(40));
		Assert.assertEquals(null, scale.getInterval(41));
	}

	/**
	 * Prueba sobre la devolucón de los valores límites de la escala.
	 */
	@Test
	public void pruebaDeValoresLimites() {
		System.out.println("<<<<<<<<<<<<<< PRUEBAS DE VALORES LÍMITE DE LA ESCALA DE VALORES >>>>>>>>>>>>>>");

		Scale<Interval<Integer>, Integer> scale;

		scale = new Scale<Interval<Integer>, Integer>();
		Assert.assertEquals(null, scale.getLowerValue());
		Assert.assertEquals(null, scale.getHigherValue());

		scale = new Scale<Interval<Integer>, Integer>();
		scale.addInterval(new Interval<Integer>(9, 10));
		Assert.assertEquals(new Integer(9), scale.getLowerValue());
		Assert.assertEquals(new Integer(10), scale.getHigherValue());

		scale = new Scale<Interval<Integer>, Integer>();
		scale.addInterval(new Interval<Integer>(0, 10));
		Assert.assertEquals(new Integer(0), scale.getLowerValue());
		Assert.assertEquals(new Integer(10), scale.getHigherValue());

		scale = new Scale<Interval<Integer>, Integer>();
		scale.addInterval(new Interval<Integer>(0, 10));
		scale.addInterval(new Interval<Integer>(10, 20));
		scale.addInterval(new Interval<Integer>(20, 30));
		scale.addInterval(new Interval<Integer>(30, 40));
		Assert.assertEquals(new Integer(0), scale.getLowerValue());
		Assert.assertEquals(new Integer(40), scale.getHigherValue());

		scale = new Scale<Interval<Integer>, Integer>();
		scale.addInterval(new Interval<Integer>(10000, 20000));
		scale.addInterval(new Interval<Integer>(20000, 30000));
		scale.addInterval(new Interval<Integer>(30000, 40000));
		Assert.assertEquals(new Integer(10000), scale.getLowerValue());
		Assert.assertEquals(new Integer(40000), scale.getHigherValue());

		Scale<Interval<Double>, Double> dScale = new Scale<Interval<Double>, Double>();
		dScale.addInterval(new Interval<Double>(0.0, 0.1));
		dScale.addInterval(new Interval<Double>(0.1, 0.2));
		dScale.addInterval(new Interval<Double>(0.2, 0.3));
		dScale.addInterval(new Interval<Double>(0.3, 0.5));
		Assert.assertEquals(new Double(0.0), dScale.getLowerValue(), 0.0);
		Assert.assertEquals(new Double(0.5), dScale.getHigherValue(), 0.0);
	}
}
