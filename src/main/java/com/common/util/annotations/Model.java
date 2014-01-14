package com.common.util.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * La anotación que nos permite definir los elementos del modelo de una aplicación.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Component
@Documented
@Scope("prototype")
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Model {
}