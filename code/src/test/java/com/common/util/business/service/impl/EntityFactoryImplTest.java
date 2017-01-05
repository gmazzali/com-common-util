package com.common.util.business.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.Serializable;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EntityFactoryImplTest {

	@InjectMocks
	private EntityFactoryImpl factory;

	@Test
	public void testNewInstance() throws Exception {
		A a = this.factory.newInstance(A.class);
		assertNotNull(a);
		a = this.factory.newInstance(A.class, 1);
		assertNull(a);

		B b = this.factory.newInstance(B.class);
		assertNull(b);
		b = this.factory.newInstance(B.class, 1);
		assertNotNull(b);
		b = this.factory.newInstance(B.class, 1, "s");
		assertNull(b);

		C c = this.factory.newInstance(C.class);
		assertNull(c);
		c = this.factory.newInstance(C.class, 1);
		assertNotNull(c);
		c = this.factory.newInstance(C.class, 1, "s");
		assertNotNull(c);
		c = this.factory.newInstance(C.class, "s");
		assertNull(c);
	}

	public static class A implements Serializable {

		private static final long serialVersionUID = 1L;

		public A() {
		}
	}

	public static class B implements Serializable {

		private static final long serialVersionUID = 1L;

		public B(Integer b) {
		}
	}

	public static class C implements Serializable {

		private static final long serialVersionUID = 1L;

		public C(Integer c) {
		}

		public C(Integer c1, String c2) {
		}
	}
}