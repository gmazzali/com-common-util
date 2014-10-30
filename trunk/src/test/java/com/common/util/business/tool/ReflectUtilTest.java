package com.common.util.business.tool;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.util.Map;

import org.apache.log4j.BasicConfigurator;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * La clase de pruebas de la clase base que permite manipular clases java.
 * 
 * @since 30/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class ReflectUtilTest {

	@BeforeClass
	public static void initClass() {
		BasicConfigurator.configure();
	}

	/**
	 * Cuando se termina de ejecutar la prueba, dejamos un espacio en blanco.
	 */
	@AfterClass
	public static void afterClass() {
		System.out.println();
	}

	/**
	 * La pruebas sobre el método <i><b>getAllDeclaratedFields</b></i> de {@link ReflectUtil}.
	 * 
	 * @see ReflectUtil#getAllDeclaratedFields(Class)
	 */
	@Test
	public void testGetAllDeclaratedFields() throws Exception {
		Map<String, Field> campos = ReflectUtil.getAllDeclaratedFields(C.class);

		Assert.assertEquals(3, campos.keySet().size());
		Assert.assertEquals(Integer.class, campos.get("a").getType());
		Assert.assertEquals(Object.class, campos.get("b").getType());
		Assert.assertEquals(String.class, campos.get("c").getType());
	}

	/**
	 * La pruebas sobre el método <i><b>getAnnotatedField</b></i> de {@link ReflectUtil}.
	 * 
	 * @see ReflectUtil#getAnnotatedField(Class, Class)
	 */
	@Test
	public void testGetAnnotatedField() throws Exception {
		Map<String, Field> campos = ReflectUtil.getAnnotatedField(C.class, Anotate.class);

		Assert.assertEquals(2, campos.keySet().size());
		Assert.assertEquals(Integer.class, campos.get("a").getType());
		Assert.assertEquals(String.class, campos.get("c").getType());
	}

	// Las clases de pruebas.
	@Retention(RetentionPolicy.RUNTIME)
	static @interface Anotate {
	}

	static class A {
		@Anotate
		private Integer a;

		public Integer getA() {
			return a;
		}

		public void setA(Integer a) {
			this.a = a;
		}
	}

	static class B extends A {
		private Object b;

		public Object getB() {
			return b;
		}

		public void setB(Object b) {
			this.b = b;
		}
	}

	static class C extends B {
		@Anotate
		private String c;

		public String getC() {
			return c;
		}

		public void setC(String c) {
			this.c = c;
		}
	}
}