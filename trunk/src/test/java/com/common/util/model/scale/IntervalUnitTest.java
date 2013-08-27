package com.common.util.model.scale;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.Test;

import com.common.util.exception.UncheckedException;
import com.common.util.model.scale.Interval;

/**
 * Clase de prueba que permite validar el correcto funcionamiento de la clase que representa un rango de valores.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class IntervalUnitTest {

	/**
	 * Cuando se termina de ejecutar la prueba, dejamos un espacio en blanco.
	 */
	@AfterClass
	public static void afterClass() {
		System.out.println();
	}

	/**
	 * Prueba en la validación de los rangos.
	 */
	@Test
	public void pruebaDeValicadionDeRangos() {
		System.out.println("<<<<<<<<<<<<<<< PRUEBAS SOBRE LA VALIDACIÓN DE RANGO DE VALORES >>>>>>>>>>>>>>>");

		Interval<Integer> range;

		range = new Interval<Integer>(null, null);
		try {
			range.validateInterval();
			Assert.assertTrue(false);
		} catch (UncheckedException e) {
			Assert.assertTrue(true);
		}

		range = new Interval<Integer>(null, 0);
		try {
			range.validateInterval();
			Assert.assertTrue(false);
		} catch (UncheckedException e) {
			Assert.assertTrue(true);
		}

		range = new Interval<Integer>(0, null);
		try {
			range.validateInterval();
			Assert.assertTrue(false);
		} catch (UncheckedException e) {
			Assert.assertTrue(true);
		}

		range = new Interval<Integer>(1, 0);
		try {
			range.validateInterval();
			Assert.assertTrue(false);
		} catch (UncheckedException e) {
			Assert.assertTrue(true);
		}

		range = new Interval<Integer>(10, 9);
		try {
			range.validateInterval();
			Assert.assertTrue(false);
		} catch (UncheckedException e) {
			Assert.assertTrue(true);
		}

		range = new Interval<Integer>(19, 10);
		try {
			range.validateInterval();
			Assert.assertTrue(false);
		} catch (UncheckedException e) {
			Assert.assertTrue(true);
		}

		range = new Interval<Integer>(0, 0);
		try {
			range.validateInterval();
			Assert.assertTrue(true);
		} catch (UncheckedException e) {
			Assert.assertTrue(false);
		}

		range = new Interval<Integer>(0, 1);
		try {
			range.validateInterval();
			Assert.assertTrue(true);
		} catch (UncheckedException e) {
			Assert.assertTrue(false);
		}

		range = new Interval<Integer>(0, 10);
		try {
			range.validateInterval();
			Assert.assertTrue(true);
		} catch (UncheckedException e) {
			Assert.assertTrue(false);
		}

		range = new Interval<Integer>(0, 20);
		try {
			range.validateInterval();
			Assert.assertTrue(true);
		} catch (UncheckedException e) {
			Assert.assertTrue(false);
		}

		range = new Interval<Integer>(10, 20);
		try {
			range.validateInterval();
			Assert.assertTrue(true);
		} catch (UncheckedException e) {
			Assert.assertTrue(false);
		}

		range = new Interval<Integer>(19, 20);
		try {
			range.validateInterval();
			Assert.assertTrue(true);
		} catch (UncheckedException e) {
			Assert.assertTrue(false);
		}

		range = new Interval<Integer>(21, 22);
		try {
			range.validateInterval();
			Assert.assertTrue(true);
		} catch (UncheckedException e) {
			Assert.assertTrue(false);
		}
	}
}
