package com.common.util.business.tool;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * La clase que nos permite definir funciones de manipulación de clases de java.
 * 
 * @since 30/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class ReflectUtil {

	/**
	 * Permite recuperar los campos de una clase dada y de sus superclases.
	 * 
	 * @param clazz
	 *            La clase que vamos a analizarle los campos.
	 * @return El mapa de los campos de la clase de acuerdo a las entradas de [nombre_campo, campo].
	 */
	public static Map<String, Field> getAllDeclaratedFields(Class<?> clazz) {
		Map<String, Field> fields = new HashMap<String, Field>();
		while (clazz != null && !clazz.getClass().equals(Object.class)) {
			for (Field field : clazz.getDeclaredFields()) {
				fields.put(field.getName(), field);
			}
			clazz = clazz.getSuperclass();
		}
		return fields;
	}

	/**
	 * Permite buscar todos los campos de una clase (incluidas sus superclases) anotadas con la anotación recibida.
	 * 
	 * @param clazz
	 *            La clase que vamos a analizar.
	 * @param annotation
	 *            La anotación que vamos a analizar.
	 * @return El mapa de los campos que corresponden a la anotación recibida.
	 */
	public static <T extends Annotation> Map<String, Field> getAnnotatedField(Class<?> clazz, Class<T> annotation) {
		Map<String, Field> fields = new HashMap<String, Field>();
		while (clazz != null && !clazz.getClass().equals(Object.class)) {
			for (Field field : clazz.getDeclaredFields()) {
				if (field.isAnnotationPresent(annotation)) {
					fields.put(field.getName(), field);
				}
			}
			clazz = clazz.getSuperclass();
		}
		return fields;
	}
}