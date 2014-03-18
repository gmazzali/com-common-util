package com.common.util.dao;

import java.io.Serializable;
import java.util.List;

import com.common.util.exception.UncheckedException;
import com.common.util.model.Entity;
import com.common.util.model.Persistence;
import com.common.util.model.filter.BaseFilter;
import com.common.util.model.filter.order.OrderBy;

/**
 * La interfaz que define todos los métodos comunes a todos los DAOs que vamos a generar dentro de un sistema.
 * 
 * @see Entity
 * @see Persistence
 * @see Serializable
 * 
 * @since 05/02/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase que corresponde a la entidad que vamos a manipular dentro de este servicio.
 * @param <PK>
 *            La clase que corresponde al identificador de la entidad {@link E}.
 */
public abstract interface BaseDao<E extends Persistence<PK>, PK extends Serializable> extends Serializable {

	/**
	 * La función encargada de contar la cantidad de entidades.
	 * 
	 * @see #countByFilter(BaseFilter)
	 * 
	 * @return El numero de registros.
	 * @throws UncheckedException
	 *             En caso de un problema durante la consulta de la cantidad de entidades.
	 */
	public Long count() throws UncheckedException;

	/**
	 * La función encargada de contar la cantidad de entidades que corresponden a un filtro del tipo {@link BaseFilter} que se recibe.
	 * 
	 * @see BaseFilter
	 * 
	 * @see #count()
	 * 
	 * @param filter
	 *            El filtro del tipo {@link BaseFilter} con el que vamos a realizar la consulta de la cantidad de entidades que vamos a recuperar.
	 * @return El numero de registros que va a corresponder a la consulta con el filtro.
	 * @throws UncheckedException
	 *             En caso de un problema durante la consulta de la cantidad de entidades.
	 */
	public Long countByFilter(BaseFilter<PK> filter) throws UncheckedException;

	/**
	 * La función que utilizamos para recuperar una entidad dado su identificador.
	 * 
	 * @see #findAll()
	 * @see #findByFilter(BaseFilter)
	 * 
	 * @param id
	 *            El identificador de la entidad que vamos a recuperar desde la base de datos.
	 * @return La entidad que corresponde al identificador recibido. En caso de no encontrar nada, retorna <i>NULL</i>.
	 * @throws UncheckedException
	 *             En caso de un problema durante la recuperación de la entidad desde la base de datos.
	 */
	public E findById(PK id) throws UncheckedException;

	/**
	 * La función que nos permite recuperar todos las entidades del mismo tipo almacenados dentro de la base de datos.
	 * 
	 * @see OrderBy
	 * 
	 * @see #findById(Serializable)
	 * @see #findByFilter(BaseFilter)
	 * 
	 * @param orders
	 *            Los ordenes en los que queremos recuperar las entidades. Si el mismo es mulo, se recuperan si un orden en particular.
	 * @return El listado de entidades almacenadas.
	 * @throws UncheckedException
	 *             En caso de un problema durante la recuperación de todos las entidades desde la base de datos.
	 */
	public List<E> findAll(OrderBy orders) throws UncheckedException;

	/**
	 * La función que utilizamos para recuperar una entidad dado un filtro del tipo {@link BaseFilter} para la consulta.
	 * 
	 * @see BaseFilter
	 * @see OrderBy
	 * 
	 * @see #findAll()
	 * @see #findById(Serializable)
	 * 
	 * @param filter
	 *            El filtro del tipo {@link BaseFilter} que vamos a ocupar para recuperar un listado de entidades de acuerdo a un criterio.
	 * @param orders
	 *            Los ordenes dentro de {@link OrderBy} en los que queremos recuperar las entidades. Si el mismo es mulo, se recuperan si un orden en
	 *            particular.
	 * @return El listado de las entidades almacenadas que cumplen con la consulta recibida.
	 * @throws UncheckedException
	 *             En caso de un problema durante la recuperación de las entidades desde la base de datos.
	 */
	public List<E> findByFilter(BaseFilter<PK> filter, OrderBy orders) throws UncheckedException;

	/**
	 * La función para guardar una nueva entidad dentro de la base de datos.
	 * 
	 * @see Entity
	 * 
	 * @see #saveOrUpdate(Persistence)
	 * @see #update(Persistence)
	 * @see #delete(Persistence)
	 * @see #deleteById(Serializable)
	 * 
	 * @param entity
	 *            La entidad {@link E} que vamos a almacenar.
	 * @return El identificador {@link PK} del elemento que acabamos de guardar dentro de la base de datos.
	 * @throws UncheckedException
	 *             En caso de un problema durante el guardado de la entidad dentro de la base de datos.
	 */
	public PK save(E entity) throws UncheckedException;

	/**
	 * La función para actualizar una entidad dentro de la base de datos.
	 * 
	 * @see Entity
	 * 
	 * @see #save(Persistence)
	 * @see #saveOrUpdate(Persistence)
	 * @see #delete(Persistence)
	 * @see #deleteById(Serializable)
	 * 
	 * @param entity
	 *            La entidad {@link E} que vamos a actualizar.
	 * @throws UncheckedException
	 *             En caso de un problema durante la actualización de la entidad dentro de la base de datos.
	 */
	public void update(E entity) throws UncheckedException;

	/**
	 * La función para insertar una nueva entidad o actualizar una que ya se encuentre dentro de la base de datos.
	 * 
	 * @see Entity
	 * 
	 * @see #save(Persistence)
	 * @see #update(Persistence)
	 * @see #delete(Persistence)
	 * @see #deleteById(Serializable)
	 * 
	 * @param entity
	 *            La entidad {@link E} que vamos a insertar o actualizar.
	 * @throws UncheckedException
	 *             En caso de un problema durante la inserción o actualización de la entidad dentro de la base de datos.
	 */
	public void saveOrUpdate(E entity) throws UncheckedException;

	/**
	 * La función para eliminar una entidad dentro de la base de datos.
	 * 
	 * @see Entity
	 * 
	 * @see #save(Persistence)
	 * @see #update(Persistence)
	 * @see #saveOrUpdate(Persistence)
	 * @see #deleteById(Serializable)
	 * 
	 * @param entity
	 *            La entidad {@link E} que vamos a eliminar.
	 * @throws UncheckedException
	 *             En caso de un problema durante la eliminación de la entidad dentro de la base de datos.
	 */
	public void delete(E entity) throws UncheckedException;

	/**
	 * La función que nos permite eliminar una entidad de la base de datos solo dando su identificador.
	 * 
	 * @see #save(Persistence)
	 * @see #update(Persistence)
	 * @see #saveOrUpdate(Persistence)
	 * @see #delete(Serializable)
	 * 
	 * @param id
	 *            El identificador {@link PK} de la entidad que queremos eliminar de la base de datos.
	 * @throws UncheckedException
	 *             En caso de un problema durante la eliminación de la entidad dentro de la base de datos.
	 */
	public void deleteById(PK id) throws UncheckedException;
}