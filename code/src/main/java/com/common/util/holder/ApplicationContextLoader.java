package com.common.util.holder;

import java.io.Serializable;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Permite cargar un contexto de una aplicación y monitorear el mismo.
 * 
 * @see ApplicationContextMonitor
 * 
 * @since 17/11/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class ApplicationContextLoader implements Serializable, BeanPostProcessor, BeanFactoryPostProcessor {

	private static final long serialVersionUID = 1L;

	/**
	 * El monitor del proceso.
	 */
	private static ApplicationContextMonitor applicationContextMonitor;

	/**
	 * La función encargada de inicializar el contexto de la aplicación sin un monitor del contexto.
	 * 
	 * @param locations
	 *            El conjunto de ubicaciones de los archivos de configuración para Spring.
	 */
	public static void initApplicationContext(String[] locations) {
		ApplicationContextLoader.initApplicationContext(null, locations);
	}

	/**
	 * La función encargada de inicializar el contexto de la aplicación con el monitor de progreso.
	 * 
	 * @param applicationContextMonitor
	 *            El monitor del proceso de carga del contexto.
	 * @param locations
	 *            El conjunto de ubicaciones de los archivos de configuración para Spring.
	 */
	public static void initApplicationContext(ApplicationContextMonitor applicationContextMonitor, String[] locations) {
		ApplicationContextLoader.initApplicationContext(applicationContextMonitor, locations, true);
	}

	/**
	 * La función encargada de inicializar el contexto de la aplicación.
	 * 
	 * @param applicationContextMonitor
	 *            El monitor del proceso de carga del contexto.
	 * @param locations
	 *            El conjunto de ubicaciones de los archivos de configuración para Spring.
	 * @param refresh
	 *            permite definir si va a refrescarse el contexto o no.
	 */
	public static void initApplicationContext(ApplicationContextMonitor applicationContextMonitor, String[] locations, boolean refresh) {
		ApplicationContextLoader.applicationContextMonitor = applicationContextMonitor;
		ApplicationContextHolder.setContext(new ClassPathXmlApplicationContext(locations, refresh));
		System.out.println("Finish init context");
		if (ApplicationContextLoader.applicationContextMonitor != null) {
			ApplicationContextLoader.applicationContextMonitor.setContextComplete(true);
		}
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("Beans Count=" + beanFactory.getBeanDefinitionCount());
		if (ApplicationContextLoader.applicationContextMonitor != null) {
			ApplicationContextLoader.applicationContextMonitor.setBeanCount(beanFactory.getBeanDefinitionCount());
		}
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("Init Bean=" + beanName);
		if (ApplicationContextLoader.applicationContextMonitor != null) {
			ApplicationContextLoader.applicationContextMonitor.incrementBeanCount();
			ApplicationContextLoader.applicationContextMonitor.setCurrentBeanName(beanName);
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("Finish Bean=" + beanName);
		return bean;
	}
}