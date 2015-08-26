package com.common.util.domain.model.info;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.common.util.domain.exception.CheckedException;
import com.common.util.domain.exception.UncheckedException;

/**
 * El conjunto de los detalles de las notificaciones no erroneas.
 * 
 * @see InfoDetail
 * @see CheckedException
 * @see UncheckedException
 * 
 * @since 26/08/2015
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class Infos implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * El listado de los detalles de las notificaciones.
	 */
	protected final Set<InfoDetail> infoDetails;

	/**
	 * El constructor por omisi�n de un conjunto de notificaciones.
	 */
	public Infos() {
		this.infoDetails = new HashSet<InfoDetail>();
	}

	/**
	 * Permite retornar el listado de las notificaciones que tenemos dentro de este contenedor.
	 * 
	 * @return El listado de las notificaciones y sus par�metros.
	 */
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		for (InfoDetail infoDetail : this.infoDetails) {
			buffer.append(infoDetail.toString());
			buffer.append("\n");
		}
		return buffer.toString();
	}

	/**
	 * Se encarga de agregar un nuevo detalle dentro de este conjunto.
	 * 
	 * @param defaultMessage
	 *            El mensaje por omisi�n del detalle de la notificaci�n.
	 * @param keyMessage
	 *            La clave del mensaje del detalle de la notificaci�n.
	 * @param parameters
	 *            El listado de los par�metros que vamos a utilizar para detallar la notificaci�n.
	 */
	public void addInfo(String defaultMessage, String keyMessage, Object... parameters) {
		this.infoDetails.add(new InfoDetail(defaultMessage, keyMessage, parameters));
	}

	/**
	 * Permite juntar a un conjunto de notificaciones dentro de este.
	 * 
	 * @param infos
	 *            El conjunto de notificaciones que vamos a unir.
	 */
	public void merge(Infos infos) {
		if (infos != null && infos.getInfoDetails() != null) {
			this.infoDetails.addAll(infos.getInfoDetails());
		}
	}

	/**
	 * Se encarga de verificar si hay alguna notificaci�n cargada dentro de este elemento.
	 * 
	 * @return <i>true</i> en caso de que exista al menos una notificaci�n dentro de este elemento, en caso contrario retorna <i>false</i>.
	 */
	public Boolean hasInfoDetails() {
		return this.infoDetails.size() > 0;
	}

	/**
	 * Se encarga de retornar el listado de detalles de notificaciones.
	 * 
	 * @return El listado de los detalles de las notificaciones.
	 */
	public Set<InfoDetail> getInfoDetails() {
		return this.infoDetails;
	}
}