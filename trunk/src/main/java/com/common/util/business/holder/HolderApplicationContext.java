package com.common.util.business.holder;

import java.io.Serializable;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * La clase encargada de mantener el contexto de la aplicación al alcance de toda la aplicación.
 * 
 * @see ConfigurableBean
 * 
 * @since 05/02/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class HolderApplicationContext implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * El contexto de la aplicación para manejar Spring.
	 */
	private static ApplicationContext context;

	/**
	 * La función encargada de inicializar el contexto de la aplicación dado un conjunto de ubicaciones de los archivos de configuración para Spring.
	 * 
	 * @param locations
	 *            El conjunto de ubicaciones de los archivos de configuración para Spring.
	 */
	public static ApplicationContext initApplicationContext(String[] locations) {
		return HolderApplicationContext.initApplicationContext(locations, true);
	}

	/**
	 * La función encargada de inicializar el contexto de la aplicación dado un conjunto de ubicaciones de los archivos de configuración para Spring.
	 * 
	 * @param locations
	 *            El conjunto de ubicaciones de los archivos de configuración para Spring.
	 * @param refresh
	 *            permite definir si va a refrescarse el contexto o no.
	 */
	public static ApplicationContext initApplicationContext(String[] locations, boolean refresh) {
		context = new ClassPathXmlApplicationContext(locations, true);
		return context;
	}

	/**
	 * La función encargada de retornar el contexto de la aplicación para que pueda usarse dentro del sistema.
	 * 
	 * @return El contexto de la aplicación actual.
	 */
	public static ApplicationContext getContext() {
		return context;
	}

	/**
	 * La función encargada de retornar el beans que corresponde con el nombre que recibimos.
	 * 
	 * @param beanName
	 *            El nombre del bean que queremos recuperar desde el contexto.
	 * @return El elemento que corresponde con el bean que recuperamos.
	 */
	public static Object getBean(String beanName) {
		return context.getBean(beanName);
	}

	/**
	 * La función encargada de retornar el beans que corresponde con el tipo que recibimos.
	 * 
	 * @param beanClass
	 *            La clase que define el tipo del bean que queremos recuperar desde el contexto.
	 * @return El elemento que corresponde con el bean que recuperamos.
	 */
	public static <E> E getBean(Class<E> beanClass) {
		return beanClass.cast(context.getBean(beanClass));
	}
}