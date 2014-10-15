package com.common.util.business.holder;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Clase que permite agregar a un objeto las dependencias a partir de las que disponemos dentro del contexto de Spring.
 * 
 * @see HolderApplicationContext
 * 
 * @since 15/14/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Component
public class ConfigurableBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(ConfigurableBean.class);

	/**
	 * Carga los campos que estan anotados comos {@link Autowired} dentro del bean con los componentes que tenemos dentro del contexto.
	 * 
	 * @param bean
	 *            El bean que vamos a inicializar.
	 */
	public static <T> void initializeBean(T bean) {
		List<Field> autowiredFields = new ArrayList<Field>();
		ConfigurableBean.getAutowiredField(bean.getClass(), autowiredFields);
		for (Field autowiredField : autowiredFields) {
			Object parameter = null;
			try {
				LOGGER.debug("Inyectando en el bean " + bean.getClass().getName() + " la propiedad " + autowiredField.getName() + " por tipo");
				parameter = HolderApplicationContext.getBean(autowiredField.getType());
				if (parameter != null) {
					autowiredField.setAccessible(true);
					autowiredField.set(bean, parameter);
				}
			} catch (Exception e) {
				LOGGER.debug("Error al intentar autowire por tipo " + autowiredField.getName(), e);
				try {
					LOGGER.debug("Inyectando en el bean " + bean.getClass().getName() + " la propiedad " + autowiredField.getName() + " por nombre");
					// Si el objeto recuperado es nulo, lo intentamos buscar por nombre.
					if (autowiredField.getAnnotation(Qualifier.class) != null) {
						Qualifier qualifierField = autowiredField.getAnnotation(Qualifier.class);
						parameter = HolderApplicationContext.getBean(qualifierField.value());
						if (parameter != null) {
							autowiredField.setAccessible(true);
							autowiredField.set(bean, parameter);
						}
					}
				} catch (Exception e2) {
					LOGGER.debug("Error al intentar autowire por nombre " + autowiredField.getName(), e);
				}
			}
		}
	}

	/**
	 * Permite tener todos los campos que estan anotados como {@link Autowired} dentro del bean que no esta anotado dentro del contexto.
	 * 
	 * @param beanClass
	 *            La clase del bean que vamos a enlazar con los datos del contexto.
	 * @param fields
	 *            La lista que vamos a cargar con los campos que corresponde a la clase y que estan anotados como {@link Autowired}.
	 */
	public static void getAutowiredField(Class<?> beanClass, List<Field> fields) {
		if (beanClass != null && !beanClass.getClass().equals(Object.class)) {
			LOGGER.debug("Inspect class: " + beanClass.getName());
			for (Field field : beanClass.getDeclaredFields()) {
				if (field.getAnnotation(Autowired.class) != null) {
					fields.add(field);
				}
			}
			// Cargamos los campos de las clases superiores.
			ConfigurableBean.getAutowiredField(beanClass.getSuperclass(), fields);
		}
	}
}