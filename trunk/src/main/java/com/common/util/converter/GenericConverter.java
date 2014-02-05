package com.common.util.converter;

import org.springframework.core.convert.converter.Converter;

/**
 * La interfaz que vamos a utilizar para definir los conversores entre entidades.
 * 
 * @since 05/02/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <S>
 *            La clase de origen que vamos a convertir.
 * @param <T>
 *            La clase objetivo a la que vamos a convertir.
 */
public interface GenericConverter<S, T> extends Converter<S, T> {
}