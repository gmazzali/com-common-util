package com.common.util.app;

import java.io.Serializable;

import com.common.util.business.holder.ApplicationContextMonitor;

/**
 * La clase que permite recuperar los parámetros de configuración de la aplicación.
 * 
 * @since 21/11/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public abstract class ApplicacionParameter implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Retorna el nombre de la variable de configuración.
	 * 
	 * @return El nombre de la variable de configuración.
	 */
	public abstract String getConfigurationDirectoryVariable();

	/**
	 * Retorna la ubicación del directorio de configuración de la aplicación.
	 * 
	 * @return El path de la ubicación del directorio de configuración de la aplicación.
	 */
	public abstract String getConfigurationDirectoryPath();

	/**
	 * Retorna las ubicaciones de los archivos de configuración de Spring.
	 * 
	 * @return Las ubicaciones de los archivos de configuración de Spring.
	 */
	public abstract String[] getConfigurationSpringFiles();

	/**
	 * Permite retornar el monitor de carga del contexto de la aplicación.
	 * 
	 * @return El monitor de carga del contexto de la aplicación.
	 */
	public abstract ApplicationContextMonitor getApplicationContextMonitor();
}