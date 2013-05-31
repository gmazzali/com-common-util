package com.common.util.exception.error;

import java.util.ArrayList;
import java.util.List;

/**
 * La clase que nos permite almacenar dentro de una misma excepción un conjunto de errores que tenemos en un proceso para luego largar los mismos
 * todos juntos en el momento adecuado.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class Errors {

	/**
	 * El listado de las claves de los errores.
	 */
	private final List<String> messagesKey;

	/**
	 * El constructor por omisión de un conjunto de errores.
	 */
	public Errors() {
		this.messagesKey = new ArrayList<>();
	}

	/**
	 * La función encargada de agregar una nueva clave dentro del listado de claves de los errores que tenemos dentro de este conjunto.
	 * 
	 * @param key
	 *            La nueva clave del error que acaba de producirse.
	 */
	public void addMessageKey(String key) {
		this.messagesKey.add(key);
	}

	/**
	 * La función encargada de retornar el listado de claves que tenemos de los errores que fuimos juntando durante la ejecución del sistema.
	 * 
	 * @return El listado de las claves de los errores que juntamos en el sistema.
	 */
	public List<String> getMessagesKey() {
		return this.messagesKey;
	}
}
