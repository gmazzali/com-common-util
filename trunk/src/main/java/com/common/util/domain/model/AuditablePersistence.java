package com.common.util.domain.model;

import java.io.Serializable;
import java.util.Date;

/**
 * La interfaz que extiende {@link Persistence} y que define las entidades auditables que van a almacenarse dentro de la base de datos.
 * 
 * @see Persistence
 * 
 * @since 02/07/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <PK>
 *            La clase que va a representar el identificador de las entidades auditables que van a implementar esta interfaz.
 */
public interface AuditablePersistence<PK extends Serializable> extends Persistence<PK> {

	/**
	 * Se encarga de definir si la entidad es una entidad para guardar o una entidad para actualizar.
	 * 
	 * @return <code>true</code> en caso de que la entidad no se encuentre guardada dentro de la base de datos, en caso contrario, retorna
	 *         <code>false</code>.
	 */
	public Boolean isNew();

	/**
	 * Permite retornar la fecha de creación de la entidad.
	 * 
	 * @return La fecha de creación de la entidad.
	 */
	public Date getCreateDate();

	/**
	 * Permite cargar la fecha de creación de la entidad.
	 * 
	 * @param createDate
	 *            La fecha de creación de la entidad.
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Permite retornar la fecha de modificación de la entidad.
	 * 
	 * @return La fecha de modificación de la entidad.
	 */
	public Date getModifyDate();

	/**
	 * Permite cargar la fecha de modificación de la entidad.
	 * 
	 * @param modifyDate
	 *            La fecha de modificación de la entidad.
	 */
	public void setModifyDate(Date modifyDate);

	/**
	 * Permite retornar la fecha de borrado lógico de la entidad.
	 * 
	 * @return La fecha de borrado lógico de la entidad.
	 */
	public Date getDeleteDate();

	/**
	 * Permite cargar la fecha de baja de la entidad.
	 * 
	 * @param deleteDate
	 *            La fecha de baja de la entidad.
	 */
	public void setDeleteDate(Date deleteDate);

	/***
	 * Permite saber si la entidad se encuentra actualmente activa dentro del sistema o no.
	 * 
	 * @return <code>true</code> en caso de que la entidad no se haya borrado lógicamente dentro del sistema, en caso contrario, retorna
	 *         <code>false</code>.
	 */
	public Boolean getActive();

	/**
	 * Permite definirle a una entidad si esta va a estar borrada lógicamente dentro del sistema o no.
	 * 
	 * @param active
	 *            El valor booleano que nos permite definir si borramos lógicamente o no la entidad dada.
	 */
	public void setActive(Boolean active);
}