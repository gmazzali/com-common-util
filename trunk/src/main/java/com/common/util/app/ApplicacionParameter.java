package com.common.util.app;

import java.io.Serializable;

import com.common.util.business.holder.ApplicationContextMonitor;

/**
 * La clase que permite recuperar los par�metros de configuraci�n de la aplicaci�n.
 * 
 * @since 21/11/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public abstract class ApplicacionParameter implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Retorna el nombre de la variable de configuraci�n.
	 * 
	 * @return El nombre de la variable de configuraci�n.
	 */
	public abstract String getConfigurationDirectoryVariable();

	/**
	 * Retorna la ubicaci�n del directorio de configuraci�n de la aplicaci�n.
	 * 
	 * @return El path de la ubicaci�n del directorio de configuraci�n de la aplicaci�n.
	 */
	public abstract String getConfigurationDirectoryPath();

	/**
	 * Retorna las ubicaciones de los archivos de configuraci�n de Spring.
	 * 
	 * @return Las ubicaciones de los archivos de configuraci�n de Spring.
	 */
	public abstract String[] getConfigurationSpringFiles();

	/**
	 * Permite retornar el monitor de carga del contexto de la aplicaci�n.
	 * 
	 * @return El monitor de carga del contexto de la aplicaci�n.
	 */
	public abstract ApplicationContextMonitor getApplicationContextMonitor();
}