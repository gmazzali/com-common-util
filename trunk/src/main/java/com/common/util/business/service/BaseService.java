package com.common.util.business.service;

import java.io.Serializable;
import java.util.List;

import com.common.util.domain.exception.ServiceException;
import com.common.util.domain.model.entity.Entity;
import com.common.util.domain.model.entity.Persistence;
import com.common.util.persistence.dao.BaseDao;
import com.common.util.persistence.filter.BaseFilter;
import com.common.util.persistence.filter.order.Orders;

/**
 * La interfaz que permite establecer un servicio para un elemento genérico junto al DAO correspondiente a este.
 * 
 * @see BaseFilter
 * @see Entity
 * @see Orders
 * @see Persistence
 * @see Serializable
 * 
 * @see BaseDao
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
public interface BaseService<E extends Persistence<PK>, PK extends Serializable> extends Serializable {

	/**
	 * LPermite cargar el {@link BaseDao} al servicio del elemento al que vamos a prestar los servicios de este elemento.
	 * 
	 * @see BaseDao
	 * 
	 * @param dao
	 *            El {@link BaseDao} que nos va a permitir acceder a la base de datos.
	 */
	public void setDao(BaseDao<E, PK> dao);

	/**
	 * Permite validar el contenido de la entidad antes de almacenarlo en la base de datos o de ocuparlo en el sistema.
	 * 
	 * @param entity
	 *            La entidad que va a corroborarse su validez.
	 * @throws ServiceException
	 *             En caso de que el contenido de la entidad no sea válido dentro del sistema.
	 */
	public void validate(E entity) throws ServiceException;

	/**
	 * Se encarga de contar la cantidad de registros que tenemos dentro de la base de datos que corresponden a esta entidad.
	 * 
	 * @see BaseDao#count()
	 * 
	 * @see #countByFilter(BaseFilter)
	 * 
	 * @return El número de registros que tenemos almacenados dentro de la base de datos.
	 * @throws ServiceException
	 *             En caso de que ocurra un error a la hora de contar las entidades dentro de la base de datos.
	 */
	public Long count() throws ServiceException;

	/**
	 * Se encarga de contar la cantidad de registros que tenemos dentro de la base de datos que corresponden a esta entidad y que cumplen con la
	 * condición dada en el filtro {@link BaseFilter} recibido.
	 * 
	 * @see BaseFilter
	 * 
	 * @see BaseDao#countByFilter(BaseFilter)
	 * 
	 * @see #count()
	 * 
	 * @param filter
	 *            El filtro {@link BaseFilter} para realizar la cuenta de registro dentro de la base de datos.
	 * @return El número de registros que tenemos almacenados dentro de la base de datos y que corresponden con el filtro recibido.
	 * @throws ServiceException
	 *             En caso de que ocurra un error a la hora de contar filas dentro de la base de datos.
	 */
	public Long countByFilter(BaseFilter<E, PK> filter) throws ServiceException;

	/**
	 * La función que nos permite recuperar todos las entidades del mismo tipo almacenados dentro de la base de datos.
	 * 
	 * @see Orders
	 * 
	 * @see BaseDao#findAll(Orders)
	 * 
	 * @see #findAll()
	 * @see #findById(Serializable)
	 * @see #findByFilter(BaseFilter)
	 * 
	 * @param orders
	 *            Los ordenes dentro de {@link Orders} en los que queremos recuperar las entidades. Si el mismo es mulo, se recuperan si un orden en
	 *            particular.
	 * @return El listado de los elementos almacenados.
	 * @throws ServiceException
	 *             En caso de un problema durante la recuperación de todos las entidades desde la base de datos.
	 */
	public List<E> findAll(Orders orders) throws ServiceException;

	/**
	 * La función que nos permite recuperar todos las entidades del mismo tipo almacenados dentro de la base de datos.
	 * 
	 * @see BaseDao#findAll(Orders)
	 * 
	 * @see #findAll(Orders)
	 * @see #findById(Serializable)
	 * @see #findByFilter(BaseFilter)
	 * 
	 * @return El listado de los elementos almacenados.
	 * @throws ServiceException
	 *             En caso de un problema durante la recuperación de todos las entidades desde la base de datos.
	 */
	public List<E> findAll() throws ServiceException;

	/**
	 * La función que nos permite recuperar todos las entidades del mismo tipo almacenados dentro de la base de datos y que cumplen un filtro
	 * {@link BaseFilter} recibido.
	 * 
	 * @see BaseFilter
	 * @see Orders
	 * 
	 * @see BaseDao#findByFilter(BaseFilter)
	 * 
	 * @see #findAll()
	 * @see #findAll(Orders)
	 * @see #findById(Serializable)
	 * 
	 * @param filter
	 *            El filtro {@link BaseFilter} para realizar la consulta de registro dentro de la base de datos.
	 * @return El listado de las entidades almacenados.
	 * @throws ServiceException
	 *             En caso de un problema durante la recuperación de todos las entidades desde la base de datos.
	 */
	public List<E> findByFilter(BaseFilter<E, PK> filter) throws ServiceException;

	/**
	 * La función que utilizamos para recuperar una entidad dado su identificador.
	 * 
	 * @see BaseDao#findById(Serializable)
	 * 
	 * @see #findAll()
	 * @see #findAll(Orders)
	 * @see #findByFilter(BaseFilter)
	 * 
	 * @param id
	 *            El identificador {@link PK} de la entidad que vamos a recuperar desde la base de datos.
	 * @return La entidad {@link E} que corresponde al identificador recibido. En caso de no encontrar nada, retorna un <code>null</code>.
	 * @throws ServiceException
	 *             En caso de un problema durante la recuperación de la entidad desde la base de datos.
	 */
	public E findById(PK id) throws ServiceException;

	/**
	 * La función para guardar la entidad dentro de la base de datos.
	 * 
	 * @see Entity
	 * 
	 * @see BaseDao#save(Persistence)
	 * 
	 * @see #update(Persistence)
	 * @see #saveOrUpdate(Persistence)
	 * @see #delete(Persistence)
	 * @see #deleteById(Serializable)
	 * 
	 * @param entity
	 *            La entidad {@link E} que vamos a almacenar.
	 * @throws ServiceException
	 *             En caso de un problema durante el guardado de la entidad dentro de la base de datos.
	 */
	public void save(E entity) throws ServiceException;

	/**
	 * La función para insertar una nueva entidad o actualizar una que ya se encuentre dentro de la base de datos.
	 * 
	 * @see Entity
	 * 
	 * @see BaseDao#saveOrUpdate(Persistence)
	 * 
	 * @see #save(Persistence)
	 * @see #update(Persistence)
	 * @see #delete(Persistence)
	 * @see #deleteById(Serializable)
	 * 
	 * @param entity
	 *            La entidad {@link E} que vamos a insertar o actualizar.
	 * @throws ServiceException
	 *             En caso de un problema durante la inserción o actualización de la entidad dentro de la base de datos.
	 */
	public void saveOrUpdate(E entity) throws ServiceException;

	/**
	 * La función para actualizar la entidad dentro de la base de datos.
	 * 
	 * @see Entity
	 * 
	 * @see BaseDao#update(Persistence)
	 * 
	 * @see #save(Persistence)
	 * @see #saveOrUpdate(Persistence)
	 * @see #delete(Persistence)
	 * @see #deleteById(Serializable)
	 * 
	 * @param entity
	 *            La entidad {@link E} que vamos a actualizar.
	 * @throws ServiceException
	 *             En caso de un problema durante la actualización de la entidad dentro de la base de datos.
	 */
	public void update(E entity) throws ServiceException;

	/**
	 * La función para eliminar la entidad dentro de la base de datos.
	 * 
	 * @see Entity
	 * 
	 * @see BaseDao#delete(Persistence)
	 * 
	 * @see #save(Persistence)
	 * @see #update(Persistence)
	 * @see #saveOrUpdate(Persistence)
	 * @see #deleteById(Serializable)
	 * 
	 * @param entity
	 *            La entidad {@link E} que vamos a eliminar.
	 * @throws ServiceException
	 *             En caso de un problema durante la eliminación de la entidad dentro de la base de datos.
	 */
	public void delete(E entity) throws ServiceException;

	/**
	 * La función para eliminar la entidad dentro de la base de datos de acuerdo a su identificador.
	 * 
	 * @see BaseDao#deleteById(Serializable)
	 * 
	 * @see #save(Persistence)
	 * @see #update(Persistence)
	 * @see #saveOrUpdate(Persistence)
	 * @see #delete(Persistence)
	 * 
	 * @param id
	 *            El identificador {@link PK} de la entidad que queremos eliminar de la base de datos.
	 * @throws ServiceException
	 *             En caso de un problema durante la eliminación de la entidad dentro de la base de datos.
	 */
	public void deleteById(PK id) throws ServiceException;
}