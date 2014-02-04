package com.common.util.service;

import java.io.Serializable;
import java.util.List;

import com.common.util.dao.GenericDao;
import com.common.util.exception.CheckedException;
import com.common.util.model.Entity;
import com.common.util.model.Persistence;
import com.common.util.model.query.filter.Filter;
import com.common.util.model.query.order.OrderBy;

/**
 * La interfaz que permite establecer un servicio para un elemento genérico junto al DAO correspondiente a este.
 * 
 * @see Filter
 * @see Entity
 * @see OrderBy
 * @see Persistence
 * @see Serializable
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase del modelo que vamos a persistir dentro de la base de datos.
 * @param <PK>
 *            La clase que va a representar la clave primaria de la clase <E>.
 */
public interface GenericService<E extends Persistence<PK>, PK extends Serializable> {

	/**
	 * La función encargada de cargar el DAO al servicio del elemento al que vamos a prestar los servicios de este elemento.
	 * 
	 * @see GenericDao
	 * 
	 * @param dao
	 *            El DAO que nos va a permitir acceder a la base de datos.
	 */
	public void setDao(GenericDao<E, PK> dao);

	/**
	 * Función encargada de validar el contenido de la entidad antes de almacenarlo en la base de datos o de ocuparlo en el sistema.
	 * 
	 * @param entity
	 *            La entidad que va a corroborarse su validez.
	 * @throws CheckedException
	 *             En caso de que el contenido de la entidad no sea válido dentro del sistema.
	 */
	public void validate(E entity) throws CheckedException;

	/**
	 * La función encargada de contar la cantidad de registros que tenemos dentro de la base de datos que corresponden a esta entidad.
	 * 
	 * @see GenericService#countByFilter(Filter)
	 * 
	 * @return El número de registros que tenemos almacenados dentro de la base de datos.
	 * @throws CheckedException
	 *             En caso de que ocurra un error a la hora de contar las entidades dentro de la base de datos.
	 */
	public Long count() throws CheckedException;

	/**
	 * La función encargada de contar la cantidad de registros que tenemos dentro de la base de datos que corresponden a esta entidad y que cumplen
	 * con la condición dada en el filtro recibido.
	 * 
	 * @see Filter
	 * @see GenericService#count()
	 * 
	 * @param filter
	 *            El filtro para realizar la cuenta de registro dentro de la base de datos.
	 * @return El número de registros que tenemos almacenados dentro de la base de datos y que corresponden con el filtro recibido.
	 * @throws CheckedException
	 *             En caso de que ocurra un error a la hora de contar filas dentro de la base de datos.
	 */
	public Long countByFilter(Filter filter) throws CheckedException;

	/**
	 * La función que utilizamos para recuperar una entidad dado su identificador.
	 * 
	 * @see GenericService#findAll()
	 * @see GenericService#findByFilter(Filter)
	 * 
	 * @param id
	 *            El identificador de la entidad que vamos a recuperar desde la base de datos.
	 * @return La entidad que corresponde al identificador recibido. En caso de no encontrar nada, retorna un NULL.
	 * @throws CheckedException
	 *             En caso de un problema durante la recuperación de la entidad desde la base de datos.
	 */
	public E findById(PK id) throws CheckedException;

	/**
	 * La función que nos permite recuperar todos las entidades del mismo tipo almacenados dentro de la base de datos.
	 * 
	 * @see OrderBy
	 * @see GenericService#findById(Serializable)
	 * @see GenericService#findByFilter(Filter)
	 * 
	 * @param orders
	 *            Los ordenes en los que queremos recuperar las entidades. Si el mismo es mulo, se recuperan si un orden en particular.
	 * @return El listado de los elementos almacenados.
	 * @throws CheckedException
	 *             En caso de un problema durante la recuperación de todos las entidades desde la base de datos.
	 */
	public List<E> findAll(OrderBy orders) throws CheckedException;

	/**
	 * La función que nos permite recuperar todos las entidades del mismo tipo almacenados dentro de la base de datos y que cumplen un filtro
	 * recibido.
	 * 
	 * @see Filter
	 * @see OrderBy
	 * @see GenericService#findAll()
	 * @see GenericService#findById(Serializable)
	 * 
	 * @param filter
	 *            El filtro para realizar la consulta de registro dentro de la base de datos.
	 * @param orders
	 *            Los ordenes en los que queremos recuperar las entidades. Si el mismo es mulo, se recuperan si un orden en particular.
	 * @return El listado de las entidades almacenados.
	 * @throws CheckedException
	 *             En caso de un problema durante la recuperación de todos las entidades desde la base de datos.
	 */
	public List<E> findByFilter(Filter filter, OrderBy orders) throws CheckedException;

	/**
	 * La función para guardar la entidad dentro de la base de datos.
	 * 
	 * @see Entity
	 * @see GenericService#update(Persistence)
	 * @see GenericService#saveOrUpdate(Persistence)
	 * @see GenericService#delete(Persistence)
	 * @see GenericService#deleteById(Serializable)
	 * 
	 * @param entity
	 *            La entidad que vamos a almacenar.
	 * @throws CheckedException
	 *             En caso de un problema durante el guardado de la entidad dentro de la base de datos.
	 */
	public void save(E entity) throws CheckedException;

	/**
	 * La función para actualizar la entidad dentro de la base de datos.
	 * 
	 * @see Entity
	 * @see GenericService#save(Persistence)
	 * @see GenericService#saveOrUpdate(Persistence)
	 * @see GenericService#delete(Persistence)
	 * @see GenericService#deleteById(Serializable)
	 * 
	 * @param entity
	 *            La entidad que vamos a actualizar.
	 * @throws CheckedException
	 *             En caso de un problema durante la actualización de la entidad dentro de la base de datos.
	 */
	public void update(E entity) throws CheckedException;

	/**
	 * La función para insertar una nueva entidad o actualizar una que ya se encuentre dentro de la base de datos.
	 * 
	 * @see Entity
	 * @see GenericService#save(Persistence)
	 * @see GenericService#update(Persistence)
	 * @see GenericService#delete(Persistence)
	 * @see GenericService#deleteById(Serializable)
	 * 
	 * @param entity
	 *            La entidad que vamos a insertar o actualizar.
	 * @throws CheckedException
	 *             En caso de un problema durante la inserción o actualización de la entidad dentro de la base de datos.
	 */
	public void saveOrUpdate(E entity) throws CheckedException;

	/**
	 * La función para eliminar la entidad dentro de la base de datos.
	 * 
	 * @see Entity
	 * @see GenericService#save(Persistence)
	 * @see GenericService#update(Persistence)
	 * @see GenericService#saveOrUpdate(Persistence)
	 * @see GenericService#deleteById(Serializable)
	 * 
	 * @param entity
	 *            La entidad que vamos a eliminar.
	 * @throws CheckedException
	 *             En caso de un problema durante la eliminación de la entidad dentro de la base de datos.
	 */
	public void delete(E entity) throws CheckedException;

	/**
	 * La función para eliminar la entidad dentro de la base de datos de acuerdo a su identificador.
	 * 
	 * @see GenericService#save(Persistence)
	 * @see GenericService#update(Persistence)
	 * @see GenericService#saveOrUpdate(Persistence)
	 * @see GenericService#delete(Persistence)
	 * 
	 * @param id
	 *            El identificador de la entidad que queremos eliminar de la base de datos.
	 * @throws CheckedException
	 *             En caso de un problema durante la eliminación de la entidad dentro de la base de datos.
	 */
	public void deleteById(PK id) throws CheckedException;
}