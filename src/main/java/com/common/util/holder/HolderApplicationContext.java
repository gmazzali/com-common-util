package com.common.util.holder;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * La clase encargada de mantener el contexto de la aplicación al alcance de toda la aplicación.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class HolderApplicationContext {

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
		HolderApplicationContext.context = new ClassPathXmlApplicationContext(locations);
		return HolderApplicationContext.context;
	}

	/**
	 * La función encargada de retornar el contexto de la aplicación para que pueda usarse dentro del sistma.
	 * 
	 * @return El contexto de la aplicación actual.
	 */
	public static ApplicationContext getContext() {
		return HolderApplicationContext.context;
	}
}
