package com.common.util.business.holder;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.common.util.business.tool.ReflectUtil;

/**
 * Clase que permite agregar a un objeto las dependencias a partir de las que disponemos dentro del contexto de Spring.
 * 
 * @see ApplicationContextHolder
 * 
 * @since 15/10/2014
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
	 * @param <T>
	 *            El tipo de bean que vamos a enlazar.
	 */
	public static <T> void initializeBean(T bean) {
		Map<String, Field> autowiredFields = ReflectUtil.getAnnotatedField(bean.getClass(), Autowired.class);
		for (Field autowiredField : autowiredFields.values()) {
			Object parameter = null;
			try {
				LOGGER.debug("Inyectando en el bean " + bean.getClass().getName() + " la propiedad " + autowiredField.getName() + " por tipo");
				parameter = ApplicationContextHolder.getBean(autowiredField.getType());
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
						parameter = ApplicationContextHolder.getBean(qualifierField.value());
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
}