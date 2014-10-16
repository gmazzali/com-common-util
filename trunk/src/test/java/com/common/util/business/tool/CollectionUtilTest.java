package com.common.util.business.tool;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.log4j.BasicConfigurator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.common.util.business.tool.collection.CollectionUtil;
import com.common.util.business.tool.collection.Predicate;
import com.common.util.business.tool.collection.Process;
import com.common.util.business.tool.collection.Transformer;

/**
 * La clase que permite probar los métodos para las colecciones.
 * 
 * @since 05/03/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class CollectionUtilTest {

	@BeforeClass
	public static void initClass() {
		BasicConfigurator.configure();
	}

	/**
	 * La pruebas sobre los métodos <i><b>isEmpty</b></i> y <i><b>isNotEmpty</b></i> de {@link CollectionUtil}
	 * 
	 * @see CollectionUtil#isEmpty(Collection)
	 * @see CollectionUtil#isNotEmpty(Collection)
	 */
	@Test
	public void testEmpty() {
		// Probamos con el valor nulo.
		try {
			CollectionUtil.isEmpty(null);
		} catch (Exception ex) {
			Assert.fail();
		}

		List<String> items = new ArrayList<String>();
		items.add("a");
		items.add("a");

		Assert.assertEquals(true, CollectionUtil.isEmpty(null));
		Assert.assertEquals(true, CollectionUtil.isEmpty(new ArrayList<String>()));
		Assert.assertEquals(false, CollectionUtil.isEmpty(items));

		Assert.assertEquals(false, CollectionUtil.isNotEmpty(null));
		Assert.assertEquals(false, CollectionUtil.isNotEmpty(new ArrayList<String>()));
		Assert.assertEquals(true, CollectionUtil.isNotEmpty(items));
	}

	/**
	 * La pruebas sobre el método <i><b>isInclude</b></i> de {@link CollectionUtil}
	 * 
	 * @see CollectionUtil#isInclude(Collection, Serializable)
	 */
	@Test
	public void testInclude() {
		// Probamos con el valor nulo.
		try {
			CollectionUtil.<String> isInclude(null, null);
		} catch (Exception ex) {
			Assert.fail();
		}

		try {
			CollectionUtil.<String> isInclude(new ArrayList<String>(), null);
		} catch (Exception ex) {
			Assert.fail();
		}

		try {
			CollectionUtil.<String> isInclude(null, "");
		} catch (Exception ex) {
			Assert.fail();
		}

		List<String> items = new ArrayList<String>();
		items.add(null);
		items.add("a");
		items.add("b");

		Assert.assertEquals(false, CollectionUtil.<String> isInclude(null, null));
		Assert.assertEquals(true, CollectionUtil.<String> isInclude(items, null));
		Assert.assertEquals(true, CollectionUtil.<String> isInclude(items, "a"));
		Assert.assertEquals(true, CollectionUtil.<String> isInclude(items, "b"));
		Assert.assertEquals(false, CollectionUtil.<String> isInclude(items, "c"));
	}

	/**
	 * La pruebas sobre el método <i><b>cardinalities</b></i> de {@link CollectionUtil}
	 * 
	 * @see CollectionUtil#cardinalities(Collection)
	 */
	@Test
	public void testCardinalities() {
		// Probamos con el valor nulo.
		try {
			CollectionUtil.<String> cardinalities(null);
			Assert.fail();
		} catch (Exception ex) {
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

		Map<String, Integer> cardinalities = CollectionUtil.<String> cardinalities(items);

		Assert.assertEquals(new Integer(1), cardinalities.get(null));
		Assert.assertEquals(new Integer(2), cardinalities.get("a"));
		Assert.assertEquals(new Integer(3), cardinalities.get("b"));
		Assert.assertEquals(new Integer(4), cardinalities.get("c"));
	}

	/**
	 * La pruebas sobre el método <i><b>cardinality</b></i> de {@link CollectionUtil}
	 * 
	 * @see CollectionUtil#cardinality(Collection, Serializable)
	 */
	@Test
	public void testCardinality() {
		// Probamos con el valor nulo.
		try {
			CollectionUtil.<String> cardinality(null, null);
			Assert.fail();
		} catch (Exception ex) {
		}

		try {
			CollectionUtil.<String> cardinality(new ArrayList<String>(), null);
		} catch (Exception ex) {
			Assert.fail();
		}

		try {
			CollectionUtil.<String> cardinality(null, "");
			Assert.fail();
		} catch (Exception ex) {
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

		Assert.assertEquals(1, CollectionUtil.<String> cardinality(items, null));
		Assert.assertEquals(2, CollectionUtil.<String> cardinality(items, "a"));
		Assert.assertEquals(3, CollectionUtil.<String> cardinality(items, "b"));
		Assert.assertEquals(4, CollectionUtil.<String> cardinality(items, "c"));
	}

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
		}

		try {
			CollectionUtil.<String> count(new ArrayList<String>(), null);
			Assert.fail();
		} catch (Exception ex) {
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
		}

		List<String> items = new ArrayList<String>();
		items.add(null);
		items.add("a");
		items.add("a");
		items.add("b");
		items.add("b");
		items.add("b");

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

		Assert.assertEquals(0, CollectionUtil.<String> count(items, new Predicate<String>() {

			@Override
			public boolean evaluate(String item) {
				return "c".equals(item);
			}
		}));
	}

	/**
	 * La pruebas sobre el método <i><b>exist</b></i> de {@link CollectionUtil}
	 * 
	 * @see CollectionUtil#exist(Collection, Predicate)
	 */
	@Test
	public void testExist() {
		// Probamos con el valor nulo.
		try {
			CollectionUtil.<String> exist(null, null);
			Assert.fail();
		} catch (Exception ex) {
		}

		try {
			CollectionUtil.<String> exist(new ArrayList<String>(), null);
			Assert.fail();
		} catch (Exception ex) {
		}

		try {
			CollectionUtil.<String> exist(null, new Predicate<String>() {

				@Override
				public boolean evaluate(String item) {
					return false;
				}
			});
			Assert.fail();
		} catch (Exception ex) {
		}

		List<String> items = new ArrayList<String>();
		items.add(null);
		items.add("a");
		items.add("a");
		items.add("b");
		items.add("b");
		items.add("b");

		Assert.assertEquals(true, CollectionUtil.<String> exist(items, new Predicate<String>() {

			@Override
			public boolean evaluate(String item) {
				return item == null;
			}
		}));

		Assert.assertEquals(true, CollectionUtil.<String> exist(items, new Predicate<String>() {

			@Override
			public boolean evaluate(String item) {
				return "a".equals(item);
			}
		}));

		Assert.assertEquals(true, CollectionUtil.<String> exist(items, new Predicate<String>() {

			@Override
			public boolean evaluate(String item) {
				return "b".equals(item);
			}
		}));

		Assert.assertEquals(false, CollectionUtil.<String> exist(items, new Predicate<String>() {

			@Override
			public boolean evaluate(String item) {
				return "c".equals(item);
			}
		}));

		Assert.assertEquals(false, CollectionUtil.<String> exist(items, new Predicate<String>() {

			@Override
			public boolean evaluate(String item) {
				return "d".equals(item);
			}
		}));
	}

	/**
	 * La pruebas sobre el método <i><b>filter</b></i> de {@link CollectionUtil}
	 * 
	 * @see CollectionUtil#filter(Collection, Predicate)
	 */
	@Test
	public void testFilter() {
		// Probamos con el valor nulo.
		try {
			CollectionUtil.<String> filter(null, null);
			Assert.fail();
		} catch (Exception ex) {
		}

		try {
			CollectionUtil.<String> filter(new ArrayList<String>(), null);
			Assert.fail();
		} catch (Exception ex) {
		}

		try {
			CollectionUtil.<String> filter(null, new Predicate<String>() {

				@Override
				public boolean evaluate(String item) {
					return false;
				}
			});
			Assert.fail();
		} catch (Exception ex) {
		}

		List<String> aList = new ArrayList<String>();
		aList.add(null);
		aList.add("a");
		aList.add("a");

		List<String> bList = new ArrayList<String>();
		bList.add("b");
		bList.add("b");
		bList.add("b");

		List<String> items = new ArrayList<String>();
		items.clear();
		items.addAll(aList);
		items.addAll(bList);

		CollectionUtil.<String> filter(items, new Predicate<String>() {

			@Override
			public boolean evaluate(String item) {
				return item == null || "a".equals(item);
			}
		});

		Assert.assertEquals(aList, items);

		items.clear();
		items.addAll(aList);
		items.addAll(bList);

		CollectionUtil.<String> filter(items, new Predicate<String>() {

			@Override
			public boolean evaluate(String item) {
				return "b".equals(item);
			}
		});

		Assert.assertEquals(bList, items);
	}

	/**
	 * La pruebas sobre el método <i><b>find</b></i> de {@link CollectionUtil}
	 * 
	 * @see CollectionUtil#find(Collection, Predicate)
	 */
	@Test
	public void testFind() {
		// Probamos con el valor nulo.
		try {
			CollectionUtil.<String> find(null, null);
			Assert.fail();
		} catch (Exception ex) {
		}

		try {
			CollectionUtil.<String> find(new ArrayList<String>(), null);
			Assert.fail();
		} catch (Exception ex) {
		}

		try {
			CollectionUtil.<String> find(null, new Predicate<String>() {

				@Override
				public boolean evaluate(String item) {
					return false;
				}
			});
			Assert.fail();
		} catch (Exception ex) {
		}

		List<String> items = new ArrayList<String>();
		items.add(null);
		items.add("a");
		items.add("a");
		items.add("b");
		items.add("b");
		items.add("b");

		Assert.assertEquals(null, CollectionUtil.<String> find(items, new Predicate<String>() {

			@Override
			public boolean evaluate(String item) {
				return item == null;
			}
		}));

		Assert.assertEquals("a", CollectionUtil.<String> find(items, new Predicate<String>() {

			@Override
			public boolean evaluate(String item) {
				return "a".equals(item);
			}
		}));

		Assert.assertEquals("b", CollectionUtil.<String> find(items, new Predicate<String>() {

			@Override
			public boolean evaluate(String item) {
				return "b".equals(item);
			}
		}));

		Assert.assertEquals(null, CollectionUtil.<String> find(items, new Predicate<String>() {

			@Override
			public boolean evaluate(String item) {
				return "c".equals(item);
			}
		}));
	}

	/**
	 * La pruebas sobre el método <i><b>select</b></i> de {@link CollectionUtil}
	 * 
	 * @see CollectionUtil#select(Collection, Predicate)
	 */
	@Test
	public void testSelect() {
		// Probamos con el valor nulo.
		try {
			CollectionUtil.<String> select(null, null);
			Assert.fail();
		} catch (Exception ex) {
		}

		try {
			CollectionUtil.<String> select(new ArrayList<String>(), null);
			Assert.fail();
		} catch (Exception ex) {
		}

		try {
			CollectionUtil.<String> select(null, new Predicate<String>() {

				@Override
				public boolean evaluate(String item) {
					return false;
				}
			});
			Assert.fail();
		} catch (Exception ex) {
		}

		List<String> aList = new ArrayList<String>();
		aList.add(null);
		aList.add("a");
		aList.add("a");

		List<String> bList = new ArrayList<String>();
		bList.add("b");
		bList.add("b");
		bList.add("b");

		List<String> items = new ArrayList<String>();
		items.clear();
		items.addAll(aList);
		items.addAll(bList);

		Assert.assertEquals(aList, CollectionUtil.<String> select(items, new Predicate<String>() {

			@Override
			public boolean evaluate(String item) {
				return item == null || "a".equals(item);
			}
		}));

		Assert.assertEquals(bList, CollectionUtil.<String> select(items, new Predicate<String>() {

			@Override
			public boolean evaluate(String item) {
				return "b".equals(item);
			}
		}));
	}

	/**
	 * La pruebas sobre el método <i><b>processAll</b></i> de {@link CollectionUtil}
	 * 
	 * @see CollectionUtil#processAll(Collection, Process)
	 */
	@Test
	public void testProcessAll() {
		// Probamos con el valor nulo.
		try {
			CollectionUtil.<StringBuffer> processAll(null, null);
			Assert.fail();
		} catch (Exception ex) {
		}

		try {
			CollectionUtil.<StringBuffer> processAll(new ArrayList<StringBuffer>(), null);
			Assert.fail();
		} catch (Exception ex) {
		}

		try {
			CollectionUtil.<StringBuffer> processAll(null, new Process<StringBuffer>() {

				@Override
				public void process(StringBuffer item) {
				}
			});
			Assert.fail();
		} catch (Exception ex) {
		}

		Process<StringBuffer> process = new Process<StringBuffer>() {

			@Override
			public void process(StringBuffer item) {
				item.append("-procesado");
			}
		};

		List<StringBuffer> input = new ArrayList<StringBuffer>();
		input.add(new StringBuffer("a"));
		input.add(new StringBuffer("b"));
		input.add(new StringBuffer("c"));
		input.add(new StringBuffer("d"));

		List<StringBuffer> output = new ArrayList<StringBuffer>();
		output.add(new StringBuffer("a-procesado"));
		output.add(new StringBuffer("b-procesado"));
		output.add(new StringBuffer("c-procesado"));
		output.add(new StringBuffer("d-procesado"));

		CollectionUtil.<StringBuffer> processAll(input, process);

		Assert.assertEquals(output.toString(), input.toString());
	}

	/**
	 * La pruebas sobre el método <i><b>transform</b></i> de {@link CollectionUtil}
	 * 
	 * @see CollectionUtil#transform(Collection, Transformer)
	 */
	@Test
	public void testTransform() {
		// Probamos con el valor nulo.
		try {
			CollectionUtil.<String, Integer> transform(null, null);
			Assert.fail();
		} catch (Exception ex) {
		}

		try {
			CollectionUtil.<String, Integer> transform(new ArrayList<String>(), null);
			Assert.fail();
		} catch (Exception ex) {
		}

		try {
			CollectionUtil.<String, Integer> transform(null, new Transformer<String, Integer>() {

				@Override
				public Integer transform(String item) {
					return null;
				}
			});
			Assert.fail();
		} catch (Exception ex) {
		}

		Transformer<String, Integer> transformer = new Transformer<String, Integer>() {

			@Override
			public Integer transform(String item) {
				return item != null ? Integer.parseInt(item) : null;
			}
		};

		List<String> input = new ArrayList<String>();
		input.add(null);
		input.add("1");
		input.add("2");
		input.add("3");

		List<Integer> output = new ArrayList<Integer>();
		output.add(null);
		output.add(1);
		output.add(2);
		output.add(3);

		Assert.assertEquals(output, CollectionUtil.<String, Integer> transform(input, transformer));
	}

	/**
	 * La pruebas sobre el método <i><b>union</b></i> de {@link CollectionUtil}
	 * 
	 * @see CollectionUtil#union(Collection, Collection)
	 */
	@Test
	public void testUnion() {
		// Probamos con el valor nulo.
		try {
			CollectionUtil.<String> union(null, null);
			Assert.fail();
		} catch (Exception ex) {
		}

		try {
			CollectionUtil.<String> union(new ArrayList<String>(), null);
			Assert.fail();
		} catch (Exception ex) {
		}

		try {
			CollectionUtil.<String> union(null, new ArrayList<String>());
			Assert.fail();
		} catch (Exception ex) {
		}

		// A: [null, b, c, c, a]
		// B: [b, b, b, a]
		// U: [null, b, b, b, c, c, a]
		List<String> aList = new ArrayList<String>();
		aList.add(null);
		aList.add("b");
		aList.add("c");
		aList.add("c");
		aList.add("a");

		List<String> bList = new ArrayList<String>();
		bList.add("b");
		bList.add("b");
		bList.add("b");
		bList.add("a");

		List<String> output = new ArrayList<String>();
		output.add(null);
		output.add("b");
		output.add("b");
		output.add("b");
		output.add("c");
		output.add("c");
		output.add("a");

		Assert.assertEquals(aList, CollectionUtil.<String> union(aList, new ArrayList<String>()));
		Assert.assertEquals(bList, CollectionUtil.<String> union(new ArrayList<String>(), bList));
		Assert.assertEquals(output, CollectionUtil.<String> union(aList, bList));
	}

	/**
	 * La pruebas sobre el método <i><b>intersection</b></i> de {@link CollectionUtil}
	 * 
	 * @see CollectionUtil#intersection(Collection, Collection)
	 */
	@Test
	public void testIntersection() {
		// Probamos con el valor nulo.
		try {
			CollectionUtil.<String> intersection(null, null);
			Assert.fail();
		} catch (Exception ex) {
		}

		try {
			CollectionUtil.<String> intersection(new ArrayList<String>(), null);
			Assert.fail();
		} catch (Exception ex) {
		}

		try {
			CollectionUtil.<String> intersection(null, new ArrayList<String>());
			Assert.fail();
		} catch (Exception ex) {
		}

		// A: [null, b, c, c, a]
		// B: [b, b, b, a]
		// I: [b, a]
		List<String> aList = new ArrayList<String>();
		aList.add(null);
		aList.add("b");
		aList.add("c");
		aList.add("c");
		aList.add("a");

		List<String> bList = new ArrayList<String>();
		bList.add("b");
		bList.add("b");
		bList.add("b");
		bList.add("a");

		List<String> output = new ArrayList<String>();
		output.add("b");
		output.add("a");

		Assert.assertEquals(new ArrayList<String>(), CollectionUtil.<String> intersection(aList, new ArrayList<String>()));
		Assert.assertEquals(new ArrayList<String>(), CollectionUtil.<String> intersection(new ArrayList<String>(), bList));
		Assert.assertEquals(output, CollectionUtil.<String> intersection(aList, bList));
	}

	/**
	 * La pruebas sobre el método <i><b>disjunction</b></i> de {@link CollectionUtil}
	 * 
	 * @see CollectionUtil#disjunction(Collection, Collection)
	 */
	@Test
	public void testDisjunction() {
		// Probamos con el valor nulo.
		try {
			CollectionUtil.<String> disjunction(null, null);
			Assert.fail();
		} catch (Exception ex) {
		}

		try {
			CollectionUtil.<String> disjunction(new ArrayList<String>(), null);
			Assert.fail();
		} catch (Exception ex) {
		}

		try {
			CollectionUtil.<String> disjunction(null, new ArrayList<String>());
			Assert.fail();
		} catch (Exception ex) {
		}

		// A: [null, b, c, c, a]
		// B: [b, b, b, a]
		// D: [null, b, b, c, c]
		List<String> aList = new ArrayList<String>();
		aList.add(null);
		aList.add("b");
		aList.add("c");
		aList.add("c");
		aList.add("a");

		List<String> bList = new ArrayList<String>();
		bList.add("b");
		bList.add("b");
		bList.add("b");
		bList.add("a");

		List<String> output = new ArrayList<String>();
		output.add(null);
		output.add("b");
		output.add("b");
		output.add("c");
		output.add("c");

		Assert.assertEquals(aList, CollectionUtil.<String> disjunction(aList, new ArrayList<String>()));
		Assert.assertEquals(bList, CollectionUtil.<String> disjunction(new ArrayList<String>(), bList));
		Assert.assertEquals(output, CollectionUtil.<String> disjunction(aList, bList));
	}

	/**
	 * La pruebas sobre el método <i><b>subtract</b></i> de {@link CollectionUtil}
	 * 
	 * @see CollectionUtil#subtract(Collection, Collection)
	 */
	@Test
	public void testSubtract() {
		// Probamos con el valor nulo.
		try {
			CollectionUtil.<String> subtract(null, null);
			Assert.fail();
		} catch (Exception ex) {
		}

		try {
			CollectionUtil.<String> subtract(new ArrayList<String>(), null);
			Assert.fail();
		} catch (Exception ex) {
		}

		try {
			CollectionUtil.<String> subtract(null, new ArrayList<String>());
			Assert.fail();
		} catch (Exception ex) {
		}

		// A: [null, b, c, c, a]
		// B: [b, b, b, a]
		// S: [null c, c]
		List<String> aList = new ArrayList<String>();
		aList.add(null);
		aList.add("b");
		aList.add("c");
		aList.add("c");
		aList.add("a");

		List<String> bList = new ArrayList<String>();
		bList.add("b");
		bList.add("b");
		bList.add("b");
		bList.add("a");

		List<String> output = new ArrayList<String>();
		output.add(null);
		output.add("c");
		output.add("c");

		Assert.assertEquals(aList, CollectionUtil.<String> subtract(aList, new ArrayList<String>()));
		Assert.assertEquals(new ArrayList<String>(), CollectionUtil.<String> subtract(new ArrayList<String>(), bList));
		Assert.assertEquals(output, CollectionUtil.<String> subtract(aList, bList));
	}
}