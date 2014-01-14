package com.common.util.exception;

import com.common.util.exception.error.ErrorDetail;
import com.common.util.exception.error.Errors;
import com.common.util.holder.HolderMessage;

/**
 * Las excepciones chequeadas que nos permite realizar un seguimiento de los lanzamiento de la misma para un mejor control en tiempo de desarrollo.
 * 
 * @see UncheckedException
 * @see ErrorDetail
 * @see Errors
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class CheckedException extends Exception {

	private static final long serialVersionUID = -3282018311354032412L;

	/**
	 * El listado de los errores.
	 */
	protected Errors errors;

	/**
	 * El constructor de una instancia de {@link CheckedException} que recibe como par�metro el conjunto de {@link Errors} que vamos a contener.
	 * 
	 * @param errors
	 *            El conjunto de errores que vamos a contener dentro de esta excepci�n.
	 */
	public CheckedException(Errors errors) {
		super();
		this.errors = errors;
	}

	/**
	 * El constructor de una instancia de {@link CheckedException} que recibe como par�metro un mensaje de {@link ErrorDetail} que vamos a crear en el
	 * momento.
	 * 
	 * @param message
	 *            El mensaje del error.
	 * @param parameter
	 *            Los par�metros que requerimos para el detalle del error.
	 */
	public CheckedException(String message, Object... parameter) {
		super(message);
		this.errors = new Errors();
		this.errors.addError(message, parameter);
	}

	/**
	 * La funci�n encargada de retornar los errores que tenemos dentro de esta excepci�n.
	 * 
	 * @return El conjunto de errores que tenemos dentro de esta excepci�n.
	 */
	public Errors getErrors() {
		return this.errors;
	}

	/**
	 * Funci�n encargada de retornar los mensajes de errores que tenemos en este elemento.
	 * 
	 * @return El mensaje de los errores que tenemos dentro de esta excepci�n.
	 */
	@Override
	public String getMessage() {
		StringBuffer stringBuffer = new StringBuffer();

		for (ErrorDetail errorDetail : this.errors.getErrorDetails()) {
			stringBuffer.append(HolderMessage.getMessage(errorDetail.getMessage(), errorDetail.getParameters()));
			stringBuffer.append(this.getMessageSeparator());
		}

		return stringBuffer.toString();
	}

	/**
	 * La funci�n encargada de retornar el separador para convertir el listado de errores en una oraci�n completa.
	 * 
	 * @return La cadena que vamos a utilizar para separar los mensajes dentro de la oraci�n completa.
	 */
	protected String getMessageSeparator() {
		return "\n";
	}
}