package com.common.util.business.holder;

import java.io.Serializable;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Permite cargar un contexto de una aplicaci�n y monitorear el mismo.
 * 
 * @see MonitorApplicationContext
 * 
 * @since 17/11/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class LoaderApplicationContext implements Serializable, BeanPostProcessor, BeanFactoryPostProcessor {
	private static final long serialVersionUID = 1L;

	/**
	 * El monitor del proceso.
	 */
	private static MonitorApplicationContext monitorApplicationContext;

	/**
	 * La funci�n encargada de inicializar el contexto de la aplicaci�n sin un monitor del contexto.
	 * 
	 * @param locations
	 *            El conjunto de ubicaciones de los archivos de configuraci�n para Spring.
	 */
	public static void initApplicationContext(String[] locations) {
		LoaderApplicationContext.initApplicationContext(null, locations);
	}

	/**
	 * La funci�n encargada de inicializar el contexto de la aplicaci�n con el monitor de progreso.
	 * 
	 * @param monitorApplicationContext
	 *            El monitor del proceso de carga del contexto.
	 * @param locations
	 *            El conjunto de ubicaciones de los archivos de configuraci�n para Spring.
	 */
	public static void initApplicationContext(MonitorApplicationContext monitorApplicationContext, String[] locations) {
		LoaderApplicationContext.initApplicationContext(monitorApplicationContext, locations, true);
	}

	/**
	 * La funci�n encargada de inicializar el contexto de la aplicaci�n.
	 * 
	 * @param monitorApplicationContext
	 *            El monitor del proceso de carga del contexto.
	 * @param locations
	 *            El conjunto de ubicaciones de los archivos de configuraci�n para Spring.
	 * @param refresh
	 *            permite definir si va a refrescarse el contexto o no.
	 */
	public static void initApplicationContext(MonitorApplicationContext monitorApplicationContext, String[] locations, boolean refresh) {
		LoaderApplicationContext.monitorApplicationContext = monitorApplicationContext;
		HolderApplicationContext.setContext(new ClassPathXmlApplicationContext(locations, refresh));
		System.out.println("Finish init context");
		if (LoaderApplicationContext.monitorApplicationContext != null) {
			LoaderApplicationContext.monitorApplicationContext.setContextComplete(true);
		}
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("Beans Count=" + beanFactory.getBeanDefinitionCount());
		if (LoaderApplicationContext.monitorApplicationContext != null) {
			LoaderApplicationContext.monitorApplicationContext.setBeanCount(beanFactory.getBeanDefinitionCount());
		}
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("Init Bean=" + beanName);
		if (LoaderApplicationContext.monitorApplicationContext != null) {
			LoaderApplicationContext.monitorApplicationContext.incrementBeanCount();
			LoaderApplicationContext.monitorApplicationContext.setCurrentBeanName(beanName);
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("Finish Bean=" + beanName);
		return bean;
	}
}