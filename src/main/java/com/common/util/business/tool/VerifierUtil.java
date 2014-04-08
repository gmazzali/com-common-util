package com.common.util.business.tool;

import java.io.Serializable;

import org.apache.log4j.Logger;

import com.common.util.domain.exception.UncheckedException;

/**
 * La clase encargada de verificar que los campos de entrada de una aplicación tenga los valores correctos y que no sean cualquier cosa.
 * 
 * @since 05/02/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class VerifierUtil implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(VerifierUtil.class);

	/**
	 * Se encarga de validar que los 2 elementos recibidos sean iguales. Permite recibir los 2 elementos nulos, lo que retorna que los elementos sean
	 * iguales.
	 * 
	 * <ul>
	 * <li>VerifierUtil.[String] isEqual(null, null) = true</li>
	 * <li>VerifierUtil.[String] isEqual("bob", null) = false</li>
	 * <li>VerifierUtil.[String] isEqual(null, "bob") = false</li>
	 * <li>VerifierUtil.[String] isEqual("bob", "bob") = true</li>
	 * <li>VerifierUtil.[String] isEqual("bob1", "bob2") = false</li>
	 * </ul>
	 * 
	 * <ul>
	 * <li>VerifierUtil.[Integer] isEqual(null, null) = true</li>
	 * <li>VerifierUtil.[Integer] isEqual(10, null) = false</li>
	 * <li>VerifierUtil.[Integer] isEqual(null, 10) = false</li>
	 * <li>VerifierUtil.[Integer] isEqual(10, 10) = true</li>
	 * <li>VerifierUtil.[Integer] isEqual(10, 11) = false</li>
	 * </ul>
	 * 
	 * @see Object#equals(Object)
	 * 
	 * @param firstEntity
	 *            El primer elemento que vamos a comparar, puede ser nulo.
	 * @param secondEntity
	 *            El segundo elemento que vamos a comparar, puede ser nulo.
	 * @return <i>true</i> en caso que la primer entidad sea igual a la segunda, en caso contrario, retornamos <i>false</i>.
	 */
	public static <S extends Serializable> boolean equals(S firstEntity, S secondEntity) {
		if (firstEntity == null && secondEntity == null) {
			return true;
		} else if (firstEntity != null) {
			return firstEntity.equals(secondEntity);
		} else {
			return secondEntity.equals(firstEntity);
		}
	}

	/**
	 * Permite verificar si un objeto recibido es <code>null</code> o no. En caso de que el objeto recibido sea <code>null</code> se va a lanzar
	 * {@link UncheckedException} para cortar validaciones.
	 * 
	 * @param object
	 *            El objeto que vamos a verificar si es <code>null</code> o no.
	 * @param defaultMessage
	 *            El mensaje por omisión que vamos a incluir dentro de la {@link UncheckedException} en caso de que el objeto recibido sea
	 *            <code>null</code>.
	 * @param keyMessage
	 *            La clave del mensaje que vamos a incluir dentro de la {@link UncheckedException} en caso de que el objeto recibido sea
	 *            <code>null</code>.
	 */
	public static void checkNotNull(Object object, String defaultMessage, String keyMessage) {
		if (object == null) {
			log.warn("DEFAULT: " + defaultMessage + " KEY: " + keyMessage);
			throw new UncheckedException(defaultMessage, keyMessage);
		}
	}
}