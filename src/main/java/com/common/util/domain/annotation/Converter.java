package com.common.util.domain.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * La anotación que nos permite definir los conversores de entidades dentro de una aplicación.
 * 
 * @since 05/02/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Documented
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Converter {
	public String value() default "";
}