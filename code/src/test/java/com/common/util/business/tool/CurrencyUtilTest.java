package com.common.util.business.tool;

import org.apache.log4j.BasicConfigurator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * La clase de pruebas de la clase base que permite manejar el dinero.
 * 
 * @since 05/03/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class CurrencyUtilTest {

	@BeforeClass
	public static void initClass() {
		BasicConfigurator.configure();
	}

	/**
	 * Iniciamos el formato de la moneda.
	 */
	@Before
	public void initCurrencyUtil() {
		new CurrencyUtil().setCurrencySimbol("$");
		new CurrencyUtil().setDecimals(2);
		new CurrencyUtil().setDecimalSeparator(',');
		new CurrencyUtil().setGroupSeparator('.');
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