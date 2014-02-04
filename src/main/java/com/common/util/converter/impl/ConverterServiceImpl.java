package com.common.util.converter.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;

import com.common.util.converter.ConverterService;
import com.common.util.converter.GenericConverter;

/**
 * La clase que nos permite manipular los conversores que nos ofrece SPRING para convertir entidades entre ellas.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class ConverterServiceImpl extends DefaultConversionService implements ConverterService {

	@Override
	public <S, T> boolean canConvertTo(Class<S> sourceClass, Class<T> targetClass) {
		return this.canConvert(sourceClass, targetClass);
	}
	
	@Override
	public <S, T> T convertTo(S source, Class<T> targetClass) {
		return this.convert(source, targetClass);
	}

	@Override
	public <S, T> List<T> convertListTo(Collection<S> collection, Class<T> returnClass) {
		List<T> resultList = new ArrayList<T>();
		if (!collection.isEmpty()) {
			S type = collection.iterator().next();
			if (this.canConvertTo(type.getClass(), returnClass)) {
				for (S source : collection) {
					resultList.add(this.convertTo(source, returnClass));
				}
			}
		}
		return resultList;
	}

	public void setConverters(Set<GenericConverter<?, ?>> converters) {
		if (converters != null) {
			for (Converter<?, ?> converter : converters) {
				this.addConverter(converter);
			}
		}
	}
}