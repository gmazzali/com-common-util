package com.common.util.business.tool;


import org.apache.log4j.BasicConfigurator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.common.util.business.tool.collection.ArrayUtil;
import com.common.util.business.tool.collection.CollectionUtil;

/**
 * La clase que permite probar los métodos para los arreglos.
 * 
 * @since 27/03/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class ArrayUtilTest {

	@BeforeClass
	public static void initClass() {
		BasicConfigurator.configure();
	}
	
	/**
	 * La pruebas sobre los métodos <i><b>isEmpty</b></i> y <i><b>isNotEmpty</b></i> de {@link ArrayUtil}
	 * 
	 * @see ArrayUtil#isEmpty(Object[])
	 */
	@Test
	public void testEmpty() {
		// Probamos con el valor nulo.
		try {
			CollectionUtil.isEmpty(null);
		} catch (Exception ex) {
			Assert.fail();
		}

		String[] items = new String[2];
		items[0] = "a";
		items[1] = "a";

		Assert.assertEquals(true, ArrayUtil.isEmpty(null));
		Assert.assertEquals(true, ArrayUtil.isEmpty(new String[0]));
		Assert.assertEquals(false, ArrayUtil.isEmpty(items));

		Assert.assertEquals(false, ArrayUtil.isNotEmpty(null));
		Assert.assertEquals(false, ArrayUtil.isNotEmpty(new String[0]));
		Assert.assertEquals(true, ArrayUtil.isNotEmpty(items));
	}
}