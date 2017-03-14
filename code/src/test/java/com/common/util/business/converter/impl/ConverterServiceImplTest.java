package com.common.util.business.converter.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.common.util.business.converter.Converter;
import com.common.util.business.tool.collection.CollectionUtil;
import com.common.util.domain.exception.BusinessException;

/**
 * Clase de prueba para el servicio de conversión.
 */
@RunWith(MockitoJUnitRunner.class)
public class ConverterServiceImplTest {

	@Test
	public void testInitSinDuplicado() throws Exception {
		ConverterServiceImpl service = new ConverterServiceImpl();
		service.init(CollectionUtil.newArrayList(new StringToDateConverter(), new IntegerToIntegerConverter(), new IntegerToDateConverter()));
	}

	@Test(expected = BusinessException.class)
	public void testInitConDuplicado() throws Exception {
		ConverterServiceImpl service = new ConverterServiceImpl();
		service.init(CollectionUtil.newArrayList(new StringToDateConverter(), new IntegerToIntegerConverter(), new IntegerToDateConverter(),
				new IntegerToIntegerConverter()));
	}

	@Test
	public void testCanConvertVerdadero() throws Exception {
		ConverterServiceImpl service = new ConverterServiceImpl();
		service.init(CollectionUtil.newArrayList(new StringToDateConverter(), new IntegerToIntegerConverter(), new IntegerToDateConverter()));

		Assert.assertTrue(service.canConvert(String.class, Date.class));
		Assert.assertTrue(service.canConvert(Integer.class, Integer.class));
		Assert.assertTrue(service.canConvert(Integer.class, Date.class));
	}

	@Test
	public void testCanConvertFalso() throws Exception {
		ConverterServiceImpl service = new ConverterServiceImpl();
		service.init(CollectionUtil.newArrayList(new StringToDateConverter(), new IntegerToIntegerConverter(), new IntegerToDateConverter()));

		Assert.assertFalse(service.canConvert(Date.class, String.class));
		Assert.assertFalse(service.canConvert(Date.class, Integer.class));
		Assert.assertFalse(service.canConvert(Integer.class, String.class));
	}

	@Test
	public void testConvert() throws Exception {
		ConverterServiceImpl service = new ConverterServiceImpl();
		service.init(CollectionUtil.newArrayList(new StringToDateConverter(), new IntegerToIntegerConverter(), new StringToIntegerConverter()));

		Assert.assertEquals(new Integer(10), service.convert("10", Integer.class));
		Assert.assertEquals(new Integer(10), service.convert(new Integer(10), Integer.class));
		Assert.assertEquals(new SimpleDateFormat("dd/MM/yyyy").parse("10/10/2014"), service.convert("10/10/2014", Date.class));
	}

	@Test(expected = BusinessException.class)
	public void testConvertFalla() throws Exception {
		ConverterServiceImpl service = new ConverterServiceImpl();
		service.init(CollectionUtil.newArrayList(new StringToDateConverter(), new IntegerToIntegerConverter(), new StringToIntegerConverter()));

		service.convert(new Date(), Integer.class);
	}

	/**
	 * Las clases de pruebas.
	 */
	public class StringToIntegerConverter extends Converter<String, Integer> {
		private static final long serialVersionUID = 1L;

		@Override
		public Integer convert(String source) {
			return new Integer(source);
		}
	}

	public class StringToDateConverter extends Converter<String, Date> {
		private static final long serialVersionUID = 1L;

		@Override
		public Date convert(String source) {
			try {
				return new SimpleDateFormat("dd/MM/yyyy").parse(source);
			} catch (ParseException e) {
				return null;
			}
		}
	}

	public class IntegerToIntegerConverter extends Converter<Integer, Integer> {
		private static final long serialVersionUID = 1L;

		@Override
		public Integer convert(Integer source) {
			return source;
		}
	}

	public class IntegerToDateConverter extends Converter<Integer, Date> {
		private static final long serialVersionUID = 1L;

		@Override
		public Date convert(Integer source) {
			return new Date();
		}
	}
}