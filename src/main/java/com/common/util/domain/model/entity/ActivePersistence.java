package com.common.util.domain.model.entity;

import java.io.Serializable;

/**
 * La interfaz que extiende {@link Persistence} y que define las entidades que vamos a borrar logicamente dentro de la base de datos.
 * 
 * @see Persistence
 * 
 * @since 11/11/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <PK>
 *            La clase que va a representar el identificador de las entidades activables que van a implementar esta interfaz.
 */
public interface ActivePersistence<PK extends Serializable> extends Persistence<PK> {

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