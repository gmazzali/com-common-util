package com.common.util.domain.model.info;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.common.util.domain.exception.CheckedException;
import com.common.util.domain.exception.UncheckedException;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * El conjunto de los detalles de las notificaciones no erroneas.
 * 
 * @see InfoDetail
 * @see CheckedException
 * @see UncheckedException
 * 
 * @since 26/08/2015
 * @author Guillermo Mazzali
 * @version 1.1
 */
public class Infos implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * El listado de los detalles de las notificaciones.
	 */
	protected final List<InfoDetail> infoDetails = Lists.newArrayList();

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
	 * Se encarga de agregar un nuevo detalle dentro de este conjunto.
	 * 
	 * @param keyMessage
	 *            La clave del mensaje del detalle de la notificaci�n.
	 * @param parameters
	 *            El listado de los par�metros que vamos a utilizar para detallar la notificaci�n.
	 */
	public void addInfo(String keyMessage, Object... parameters) {
		this.infoDetails.add(new InfoDetail(keyMessage, keyMessage, parameters));
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
		return CollectionUtils.isNotEmpty(this.infoDetails);
	}

	/**
	 * Retorna el listado de detalles de notificaciones.
	 * 
	 * @return El listado de los detalles de las notificaciones.
	 */
	public List<InfoDetail> getInfoDetails() {
		return this.infoDetails;
	}

	/**
	 * Retorna el primer detalle de informaci�n dentro de este contenedor.
	 * 
	 * @return El primer detalle de informaci�n dentro de este contenedor, en caso de que este vac�o retorna <i>null</i>.
	 */
	public InfoDetail getFirstInfoDetail() {
		return Iterables.getFirst(this.infoDetails, null);
	}

	/**
	 * Retorna el �ltimo detalle de informaci�n dentro de este contenedor.
	 * 
	 * @return El �ltimo detalle de informaci�n dentro de este contenedor, en caso de que este vac�o retorna <i>null</i>.
	 */
	public InfoDetail getLastInfoDetail() {
		return Iterables.getLast(this.infoDetails, null);
	}
}