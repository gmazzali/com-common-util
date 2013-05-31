package com.common.util.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**
 * La anotaci�n que nos permite definir los elementos de la capa de presentaci�n de una aplicaci�n.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Component
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface View {

	public String value() default "";
}
