package com.common.util.converter;

import java.util.Set;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;

/**
 * La clase que nos permite manipular los conversores que nos ofrece SPRING para convertir entidades entre ellas.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class SpringConverterService extends DefaultConversionService implements ConverterService, InitializingBean {

	private Set<Converter<?, ?>> converters;

	@Override
	public <S, T> T convertTo(S source, Class<T> targetClass) {
		return this.convert(source, targetClass);
	}

	@Override
	public <S, T> boolean canConvertTo(Class<S> sourceClass, Class<T> targetClass) {
		return this.canConvert(sourceClass, targetClass);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (this.converters != null) {
			for (Converter<?, ?> converter : this.converters) {
				this.addConverter(converter);
			}
		}
	}

	public void setConverters(Set<Converter<?, ?>> converters) {
		this.converters = converters;
	}
}