package com.common.util.domain.model.error;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.common.util.domain.exception.CheckedException;
import com.common.util.domain.exception.UncheckedException;
import com.common.util.domain.model.info.Infos;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * El conjunto de los detalles de los errores para manejarlos dentro del sistema.
 * 
 * @see ErrorDetail
 * @see Infos
 * @see CheckedException
 * @see UncheckedException
 * 
 * @since 05/02/2014
 * @author Guillermo Mazzali
 * @version 1.1
 */
public class Errors implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * El listado de los detalles de los errores.
	 */
	protected final List<ErrorDetail> errorDetails = Lists.newArrayList();

	/**
	 * Permite retornar el listado de los errores que tenemos dentro de este contenedor.
	 * 
	 * @return El listado de los errores y sus parámetros.
	 */
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		for (ErrorDetail error : this.errorDetails) {
			buffer.append(error.toString());
			buffer.append("\n");
		}
		return buffer.toString();
	}

	/**
	 * Se encarga de agregar un nuevo detalle dentro de este conjunto.
	 * 
	 * @param defaultMessage
	 *            El mensaje por omisión del detalle del error.
	 * @param keyMessage
	 *            La clave del mensaje del detalle del error.
	 * @param parameters
	 *            El listado de los parámetros que vamos a utilizar para detallar el error.
	 */
	public void addError(String defaultMessage, String keyMessage, Object... parameters) {
		this.errorDetails.add(new ErrorDetail(defaultMessage, keyMessage, parameters));
	}

	/**
	 * Se encarga de agregar un nuevo detalle dentro de este conjunto.
	 * 
	 * @param keyMessage
	 *            La clave del mensaje del detalle del error que tambien se va a utilizar como mensaje por omisión.
	 * @param parameters
	 *            El listado de los parámetros que vamos a utilizar para detallar el error.
	 */
	public void addError(String keyMessage, Object... parameters) {
		this.errorDetails.add(new ErrorDetail(keyMessage, keyMessage, parameters));
	}

	/**
	 * Permite juntar a un conjunto de errores dentro de este.
	 * 
	 * @param errors
	 *            El conjunto de errores que vamos a unir.
	 */
	public void merge(Errors errors) {
		if (errors != null && errors.getErrorDetails() != null) {
			this.errorDetails.addAll(errors.getErrorDetails());
		}
	}

	/**
	 * Se encarga de verificar si hay algún error cargado dentro de este elemento.
	 * 
	 * @return <i>true</i> en caso de que exista al menos un error dentro de este elemento, en caso contrario retorna <i>false</i>.
	 */
	public Boolean hasErrors() {
		return CollectionUtils.isNotEmpty(this.errorDetails);
	}

	/**
	 * Retorna el listado de detalles de errores.
	 * 
	 * @return El listado de los detalles de los errores.
	 */
	public List<ErrorDetail> getErrorDetails() {
		return this.errorDetails;
	}

	/**
	 * Retorna el primer detalle de error dentro de este contenedor.
	 * 
	 * @return El primer detalle de error dentro de este contenedor, en caso de que este vacío retorna <i>null</i>.
	 */
	public ErrorDetail getFirstErrorDetail() {
		return Iterables.getFirst(this.errorDetails, null);
	}

	/**
	 * Retorna el último detalle de error dentro de este contenedor.
	 * 
	 * @return El último detalle de error dentro de este contenedor, en caso de que este vacío retorna <i>null</i>.
	 */
	public ErrorDetail getLastErrorDetail() {
		return Iterables.getLast(this.errorDetails, null);
	}
}