package com.common.util.domain.model.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * La interfaz que extiende {@link ActivePersistence} y que define las entidades auditables.
 * 
 * @see Persistence
 * @see ActivePersistence
 * 
 * @since 02/07/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <PK>
 *            La clase que va a representar el identificador de las entidades auditables.
 */
public interface AuditablePersistence<PK extends Serializable> extends ActivePersistence<PK> {

	/**
	 * Se encarga de definir si la entidad es una entidad para guardar o una entidad para actualizar.
	 * 
	 * @return <code>true</code> en caso de que la entidad no se encuentre guardada dentro de la base de datos, en caso contrario, retorna
	 *         <code>false</code>.
	 */
	public Boolean isNew();

	/**
	 * Permite retornar la fecha de creaci�n de la entidad.
	 * 
	 * @return La fecha de creaci�n de la entidad.
	 */
	public Date getCreateDate();

	/**
	 * Permite cargar la fecha de creaci�n de la entidad.
	 * 
	 * @param createDate
	 *            La fecha de creaci�n de la entidad.
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Permite retornar la fecha de modificaci�n de la entidad.
	 * 
	 * @return La fecha de modificaci�n de la entidad.
	 */
	public Date getModifyDate();

	/**
	 * Permite cargar la fecha de modificaci�n de la entidad.
	 * 
	 * @param modifyDate
	 *            La fecha de modificaci�n de la entidad.
	 */
	public void setModifyDate(Date modifyDate);

	/**
	 * Permite retornar la fecha de borrado l�gico de la entidad.
	 * 
	 * @return La fecha de borrado l�gico de la entidad.
	 */
	public Date getDeleteDate();

	/**
	 * Permite cargar la fecha de baja de la entidad.
	 * 
	 * @param deleteDate
	 *            La fecha de baja de la entidad.
	 */
	public void setDeleteDate(Date deleteDate);
}