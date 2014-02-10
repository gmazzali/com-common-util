package com.common.util.tool;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

/**
 * La clase de pruebas de la clase base que permite manejar el dinero.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class CurrencyUtilTestUnit {

	/**
	 * Iniciamos el formato de la moneda.
	 */
	@Before
	public void initCurrencyUtil() {
		CurrencyUtil currencyUtil = new CurrencyUtil();
		currencyUtil.setCurrencySimbol("$");
		currencyUtil.setDecimals(2);
		currencyUtil.setDecimalSeparator(',');
		currencyUtil.setGroupSeparator('.');
	}

	/**
	 * La pruebas sobre el método <i><b>formatCurrency</b></i> de {@link CurrencyUtil}
	 * 
	 * @see CurrencyUtil#formatCurrency(Double)
	 */
	@Test
	public void testFormatCurrency() {

		Double currency = null;
		String output = null;

		try {
			CurrencyUtil.formatCurrency(null);
		} catch (Exception ex) {
			Assert.fail();
		}

		currency = -1000.00;
		output = "$ -1.000,00";
		Assert.assertEquals(output, CurrencyUtil.formatCurrency(currency));

		currency = -100.00;
		output = "$ -100,00";
		Assert.assertEquals(output, CurrencyUtil.formatCurrency(currency));

		currency = -10.00;
		output = "$ -10,00";
		Assert.assertEquals(output, CurrencyUtil.formatCurrency(currency));

		currency = -1.00;
		output = "$ -1,00";
		Assert.assertEquals(output, CurrencyUtil.formatCurrency(currency));

		currency = 0.00;
		output = "$ 0,00";
		Assert.assertEquals(output, CurrencyUtil.formatCurrency(currency));

		currency = 1.00;
		output = "$ 1,00";
		Assert.assertEquals(output, CurrencyUtil.formatCurrency(currency));

		currency = 10.00;
		output = "$ 10,00";
		Assert.assertEquals(output, CurrencyUtil.formatCurrency(currency));

		currency = 100.00;
		output = "$ 100,00";
		Assert.assertEquals(output, CurrencyUtil.formatCurrency(currency));

		currency = 1000.00;
		output = "$ 1.000,00";
		Assert.assertEquals(output, CurrencyUtil.formatCurrency(currency));
	}
}