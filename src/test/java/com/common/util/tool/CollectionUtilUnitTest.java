package com.common.util.tool;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.common.util.tool.collection.CollectionUtil;
import com.common.util.tool.collection.Predicate;

/**
 * La clase que permite probar los métodos para las colecciones.
 * 
 * @since 05/03/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class CollectionUtilUnitTest {
	/**
	 * La pruebas sobre el método <i><b>isEmpty</b></i> de {@link CollectionUtil}
	 * 
	 * @see CollectionUtil#count(Collection, Predicate)
	 */
	@Test
	public void testCount() {

		// Probamos con el valor nulo.
		try {
			CollectionUtil.count(null, null);
			Assert.fail();
		} catch (Exception ex) {
			Assert.assertTrue(true);
		}

		try {
			CollectionUtil.<String> count(new ArrayList<String>(), null);
			Assert.fail();
		} catch (Exception ex) {
			Assert.assertTrue(true);
		}

		try {
			CollectionUtil.<String> count(null, new Predicate<String>() {
				@Override
				public boolean evaluate(String item) {
					return false;
				}
			});
			Assert.fail();
		} catch (Exception ex) {
			Assert.assertTrue(true);
		}

		List<String> items = new ArrayList<String>();
		items.add(null);
		items.add("a");
		items.add("a");
		items.add("b");
		items.add("b");
		items.add("b");
		items.add("c");
		items.add("c");
		items.add("c");
		items.add("c");
		
		Assert.assertEquals(1, CollectionUtil.<String> count(items, new Predicate<String>() {

			@Override
			public boolean evaluate(String item) {
				return item == null;
			}
		}));
		
		Assert.assertEquals(2, CollectionUtil.<String> count(items, new Predicate<String>() {

			@Override
			public boolean evaluate(String item) {
				return "a".equals(item);
			}
		}));

		Assert.assertEquals(3, CollectionUtil.<String> count(items, new Predicate<String>() {

			@Override
			public boolean evaluate(String item) {
				return "b".equals(item);
			}
		}));

		Assert.assertEquals(4, CollectionUtil.<String> count(items, new Predicate<String>() {

			@Override
			public boolean evaluate(String item) {
				return "c".equals(item);
			}
		}));
	}
}