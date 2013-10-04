package com.common.util.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * La anotación que nos permite definir los elementos de la capa de servicio de una aplicación.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@org.springframework.stereotype.Service
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Service {
	public String value() default "";
}