package com.common.util.model;

import org.junit.Test;

/**
 * La clase para hacer pruebas generales.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class GeneralTest {

	@Test
	public void test() {
		String value;
		Double parse;

		value = "0.001";
		parse = Double.parseDouble(value);

		System.out.println("VALOR: " + parse);
	}

}
