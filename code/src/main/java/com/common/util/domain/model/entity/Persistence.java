package com.common.util.domain.model.entity;

import java.io.Serializable;

/**
 * La interfaz que define los elementos que van a almacenarse dentro de la base de datos.
 * 
 * @since 05/02/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <PK>
 *            La clase que va a representar el identificador de la clase que implemente esa interfaz.
 */
public interface Persistence<PK extends Serializable> extends Serializable {

	/**
	 * Se encarga de definir si la entidad es una entidad para guardar o una entidad para actualizar.
	 * 
	 * @return <code>true</code> en caso de que la entidad no se encuentre guardada dentro de la base de datos, en caso contrario, retorna <code>false</code>.
	 */
	public Boolean isNew();

	/**
	 * La función encargada de cargar el objeto identificador a este elemento.
	 * 
	 * @param id
	 *            El identificador de este elemento.
	 */
	public void setId(PK id);

	/**
	 * La función encargada de retornar el objeto identificador de este elemento.
	 * 
	 * @return El objeto identificador de este elemento.
	 */
	public PK getId();
}