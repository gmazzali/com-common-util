package com.common.util.business.tool;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.common.util.domain.exception.ServiceException;

/**
 * La clase que nos permite definir funciones de manipulación de clases de java.
 * 
 * @since 30/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class ReflectUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(ReflectUtil.class);

	/**
	 * Permite recuperar los campos de una clase dada y de sus superclases.
	 * 
	 * @param clazz
	 *            La clase que vamos a analizarle los campos.
	 * @return El mapa de los campos de la clase de acuerdo a las entradas de [nombre_campo, campo].
	 */
	public static Map<String, Field> getAllDeclaratedFields(Class<?> clazz) {
		Map<String, Field> fields = new HashMap<String, Field>();
		while (clazz != null && !clazz.equals(Object.class)) {
			LOGGER.debug("Getting the properties for class: " + clazz.getSimpleName());
			for (Field field : clazz.getDeclaredFields()) {
				fields.put(field.getName(), field);
			}
			clazz = clazz.getSuperclass();
		}
		return fields;
	}

	/**
	 * Permite recuperar el getter de una propiedad en una clase, o de sus superclases.
	 * 
	 * @param clazz
	 *            La clase desde la que vamos a recuperar el getter.
	 * @param property
	 *            La propiedad sobre la que vamos a recuperar el getter.
	 * @return El método getter que corresponde a la propiedad recibida.
	 * @throws ServiceException
	 *             En caso de que no se encuentre el getter.
	 */
	public static Method getGetter(Class<?> clazz, String property) {
		String getterName = "get" + property.substring(0, 1).toUpperCase() + property.substring(1);
		LOGGER.debug("Getting the getter: " + getterName + " from the class: " + clazz.getSimpleName());
		return ReflectUtil.getMethod(clazz, getterName);
	}

	/**
	 * Permite recuperar el setter de una propiedad en una clase, o de sus superclases.
	 * 
	 * @param clazz
	 *            La clase desde la que vamos a recuperar el setter.
	 * @param property
	 *            La propiedad sobre la que vamos a recuperar el setter.
	 * @param classParameter
	 *            El tipo de parámetro que recibimos en el setter.
	 * @return El método setter que corresponde a la propiedad recibida.
	 * @throws ServiceException
	 *             En caso de que no se encuentre el setter.
	 */
	public static Method getSetter(Class<?> clazz, String property, Class<?> classParameter) {
		String setterName = "set" + property.substring(0, 1).toUpperCase() + property.substring(1);
		LOGGER.debug("Getting the setter: " + setterName + " from the class: " + clazz.getSimpleName());
		return ReflectUtil.getMethod(clazz, setterName, classParameter);
	}

	/**
	 * Permite recuperar un método dado en una clase o en su super clase, seteando al mismo como accesible.
	 * 
	 * @param clazz
	 *            La clase desde la que vamos a recuperar el método.
	 * @param method
	 *            El nombre del método que vamos a buscar.
	 * @param classParameters
	 *            Los parámetros del método que queremos recuperar.
	 * @return El método de la clase que corresponde con el nombre recibido, en caso de que no se encuentre ninguno, lanzamos {@link ServiceException}
	 * @throws ServiceException
	 *             En caso de que no se encuentre el método requerido.
	 */
	public static Method getMethod(Class<?> clazz, String method, Class<?>... classParameters) {
		Method getMethod = null;
		if (clazz != null && !clazz.equals(Object.class)) {
			try {
				getMethod = clazz.getDeclaredMethod(method, classParameters);
				getMethod.setAccessible(true);
			} catch (Exception e) {
				try {
					getMethod = ReflectUtil.getMethod(clazz.getSuperclass(), method, classParameters);
				} catch (ServiceException ex) {
					LOGGER.warn("Problem getting the method " + method + " for class " + clazz.getName(), ex);
					throw new ServiceException(ex, "Problem getting the method " + method + " for class " + clazz.getName(), "");
				}
			}
		}
		if (getMethod == null) {
			LOGGER.warn("Cannot find the method " + method + " for class " + clazz.getName());
			throw new ServiceException("Cannot find the method " + method + " for class " + clazz.getName(), "");
		}
		return getMethod;
	}

	/**
	 * Permite buscar todos los campos de una clase (incluidas sus superclases) anotadas con la anotación recibida.
	 * 
	 * @param clazz
	 *            La clase que vamos a analizar.
	 * @param annotation
	 *            La anotación que vamos a analizar.
	 * @param <T>
	 *            El tipo de anotación que vamos a buscar.
	 * @return El mapa de los campos que corresponden a la anotación recibida.
	 */
	public static <T extends Annotation> Map<String, Field> getAnnotatedField(Class<?> clazz, Class<T> annotation) {
		Map<String, Field> fields = new HashMap<String, Field>();
		while (clazz != null && !clazz.equals(Object.class)) {
			LOGGER.debug("Getting the annotated properties for class: " + clazz.getSimpleName());
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