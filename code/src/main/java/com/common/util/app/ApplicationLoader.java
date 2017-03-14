package com.common.util.app;

import java.io.Serializable;

import com.common.util.app.context.ApplicationContextLoader;

/**
 * La clase que nos permite arrancar el aplicativo.
 * 
 * @since 21/11/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class ApplicationLoader implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Los parametros de configuraci�n.
	 */
	private ApplicacionParameter applicacionParameter;

	/**
	 * Permite iniciar la aplicaci�n.
	 */
	public void startContext() {
		System.setProperty(this.applicacionParameter.getConfigurationDirectoryVariable(), this.applicacionParameter.getConfigurationDirectoryPath());
		ApplicationContextLoader.initApplicationContext(this.applicacionParameter.getApplicationContextMonitor(),
				this.applicacionParameter.getConfigurationSpringFiles());
	}

	/**
	 * Permite cargar los par�metros para iniciar el contexto de la aplicaci�n.
	 * 
	 * @param applicacionParameter
	 *            Los par�metros para cargar el contexto.
	 * @return La instancia del cargador de la aplicaci�n.
	 */
	public ApplicationLoader setApplicacionParameter(ApplicacionParameter applicacionParameter) {
		this.applicacionParameter = applicacionParameter;
		return this;
	}
}