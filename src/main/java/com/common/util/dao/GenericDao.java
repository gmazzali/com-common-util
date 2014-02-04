package com.common.util.dao;

import java.io.Serializable;
import java.util.List;

import com.common.util.model.Entity;
import com.common.util.model.Persistence;
import com.common.util.model.query.filter.Filter;
import com.common.util.model.query.order.OrderBy;

/**
 * La interfaz que define todos los métodos comunes a todos los DAOs que vamos a generar dentro de un sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @see Entity
 * @see Persistence
 * @see Serializable
 * 
 * @param <E>
 *            La clase que corresponde a la entidad que vamos a manipular dentro de la base de datos.
 * @param <PK>
 *            La clase que corresponde al identificador de la entidad <b>E</b>.
 */
public abstract interface GenericDao<E extends Persistence<PK>, PK extends Serializable> extends Serializable {

	/**
	 * La función encargada de contar la cantidad de entidades.
	 * 
	 * @see GenericDao#countByFilter(Filter)
	 * 
	 * @return El numero de registros.
	 * @throws RuntimeException
	 *             En caso de un problema durante la consulta de la cantidad de entidades.
	 */
	public Long count() throws RuntimeException;

	/**
	 * La función encargada de contar la cantidad de entidades que corresponden a un filtro del tipo {@link Filter} que se recibe.
	 * 
	 * @see Filter
	 * @see GenericDao#count()
	 * 
	 * @param filter
	 *            El filtro del tipo {@link Filter} con el que vamos a realizar la consulta de la cantidad de entidades que vamos a recuperar.
	 * @return El numero de registros que va a corresponder a la consulta con el filtro.
	 * @throws RuntimeException
	 *             En caso de un problema durante la consulta de la cantidad de entidades.
	 */
	public Long countByFilter(Filter filter) throws RuntimeException;

	/**
	 * La función que utilizamos para recuperar una entidad dado su identificador.
	 * 
	 * @see GenericDao#findAll()
	 * @see GenericDao#findByFilter(Filter)
	 * 
	 * @param id
	 *            El identificador de la entidad que vamos a recuperar desde la base de datos.
	 * @return La entidad que corresponde al identificador recibido. En caso de no encontrar nada, retorna <i>NULL</i>.
	 * @throws RuntimeException
	 *             En caso de un problema durante la recuperación de la entidad desde la base de datos.
	 */
	public E findById(PK id) throws RuntimeException;

	/**
	 * La función que nos permite recuperar todos las entidades del mismo tipo almacenados dentro de la base de datos.
	 * 
	 * @see OrderBy
	 * @see GenericDao#findById(Serializable)
	 * @see GenericDao#findByFilter(Filter)
	 * 
	 * @param orders
	 *            Los ordenes en los que queremos recuperar las entidades. Si el mismo es mulo, se recuperan si un orden en particular.
	 * @return El listado de entidades almacenadas.
	 * @throws RuntimeException
	 *             En caso de un problema durante la recuperación de todos las entidades desde la base de datos.
	 */
	public List<E> findAll(OrderBy orders) throws RuntimeException;

	/**
	 * La función que utilizamos para recuperar una entidad dado un filtro del tipo {@link Filter} para la consulta.
	 * 
	 * @see Filter
	 * @see OrderBy
	 * @see GenericDao#findAll()
	 * @see GenericDao#findById(Serializable)
	 * 
	 * @param filter
	 *            El filtro del tipo {@link Filter} que vamos a ocupar para recuperar un listado de entidades de acuerdo a un criterio.
	 * @param orders
	 *            Los ordenes en los que queremos recuperar las entidades. Si el mismo es mulo, se recuperan si un orden en particular.
	 * @return El listado de las entidades almacenadas que cumplen con la consulta recibida.
	 * @throws RuntimeException
	 *             En caso de un problema durante la recuperación de las entidades desde la base de datos.
	 */
	public List<E> findByFilter(Filter filter, OrderBy orders) throws RuntimeException;

	/**
	 * La función para guardar una nueva entidad dentro de la base de datos.
	 * 
	 * @see Entity
	 * @see GenericDao#saveOrUpdate(Persistence)
	 * @see GenericDao#update(Persistence)
	 * @see GenericDao#delete(Persistence)
	 * @see GenericDao#deleteById(Serializable)
	 * 
	 * @param entity
	 *            La entidad que vamos a almacenar.
	 * @throws RuntimeException
	 *             En caso de un problema durante el guardado de la entidad dentro de la base de datos.
	 */
	public void save(E entity) throws RuntimeException;

	/**
	 * La función para actualizar una entidad dentro de la base de datos.
	 * 
	 * @see Entity
	 * @see GenericDao#save(Persistence)
	 * @see GenericDao#saveOrUpdate(Persistence)
	 * @see GenericDao#delete(Persistence)
	 * @see GenericDao#deleteById(Serializable)
	 * 
	 * @param entity
	 *            La entidad que vamos a actualizar.
	 * @throws RuntimeException
	 *             En caso de un problema durante la actualización de la entidad dentro de la base de datos.
	 */
	public void update(E entity) throws RuntimeException;

	/**
	 * La función para insertar una nueva entidad o actualizar una que ya se encuentre dentro de la base de datos.
	 * 
	 * @see Entity
	 * @see GenericDao#save(Persistence)
	 * @see GenericDao#update(Persistence)
	 * @see GenericDao#delete(Persistence)
	 * @see GenericDao#deleteById(Serializable)
	 * 
	 * @param entity
	 *            La entidad que vamos a insertar o actualizar.
	 * @throws RuntimeException
	 *             En caso de un problema durante la inserción o actualización de la entidad dentro de la base de datos.
	 */
	public void saveOrUpdate(E entity) throws RuntimeException;

	/**
	 * La función para eliminar una entidad dentro de la base de datos.
	 * 
	 * @see Entity
	 * @see GenericDao#save(Persistence)
	 * @see GenericDao#update(Persistence)
	 * @see GenericDao#saveOrUpdate(Persistence)
	 * @see GenericDao#deleteById(Serializable)
	 * 
	 * @param entity
	 *            La entidad que vamos a eliminar.
	 * @throws RuntimeException
	 *             En caso de un problema durante la eliminación de la entidad dentro de la base de datos.
	 */
	public void delete(E entity) throws RuntimeException;

	/**
	 * La función que nos permite eliminar una entidad de la base de datos solo dando su identificador.
	 * 
	 * @see GenericDao#save(Persistence)
	 * @see GenericDao#update(Persistence)
	 * @see GenericDao#saveOrUpdate(Persistence)
	 * @see GenericDao#delete(Serializable)
	 * 
	 * @param id
	 *            El identificador de la entidad que queremos eliminar de la base de datos.
	 * @throws RuntimeException
	 *             En caso de un problema durante la eliminación de la entidad dentro de la base de datos.
	 */
	public void deleteById(PK id) throws RuntimeException;
}