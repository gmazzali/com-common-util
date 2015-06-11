package com.common.util.business.holder;

import java.io.Serializable;

import org.springframework.context.ApplicationContext;

/**
 * La clase encargada de mantener el contexto de la aplicaci�n al alcance de toda la aplicaci�n.
 * 
 * @see ConfigurableBean
 * @see ApplicationContextLoader
 * 
 * @since 05/02/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class ApplicationContextHolder implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * El contexto de la aplicaci�n para manejar Spring.
	 */
	private static ApplicationContext context;

	/**
	 * Permite cargar el contexto de la aplicaci�n.
	 * 
	 * @param context
	 *            El contexto de la aplicaci�n.
	 */
	protected static void setContext(ApplicationContext context) {
		ApplicationContextHolder.context = context;
	}

	/**
	 * La funci�n encargada de retornar el contexto de la aplicaci�n para que pueda usarse dentro del sistema.
	 * 
	 * @return El contexto de la aplicaci�n actual.
	 */
	public static ApplicationContext getContext() {
		return context;
	}

	/**
	 * La funci�n encargada de retornar el beans que corresponde con el nombre que recibimos.
	 * 
	 * @param beanName
	 *            El nombre del bean que queremos recuperar desde el contexto.
	 * @return El elemento que corresponde con el bean que recuperamos.
	 */
	public static Object getBean(String beanName) {
		return context.getBean(beanName);
	}

	/**
	 * La funci�n encargada de retornar el beans que corresponde con el tipo que recibimos.
	 * 
	 * @param beanClass
	 *            La clase que define el tipo del bean que queremos recuperar desde el contexto.
	 * @param <E>
	 *            EL tipo de bean que vamos a buscar.
	 * @return El elemento que corresponde con el bean que recuperamos.
	 */
	public static <E> E getBean(Class<E> beanClass) {
		return context.getBean(beanClass);
	}
}