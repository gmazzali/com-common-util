package com.common.util.service;

import java.io.Serializable;
import java.util.List;

import com.common.util.dao.GenericDao;
import com.common.util.exception.CheckedException;
import com.common.util.model.Persistence;
import com.common.util.model.query.filter.Filter;

/**
 * La interfaz que permite establecer un servicio para un elemento gen�rico junto al DAO correspondiente a este.
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
	 * La funci�n encargada de cargar el DAO al servicio del elemento al que vamos a prestar los servicios de este elemento.
	 * 
	 * @param dao
	 *            El DAO que nos va a permitir acceder a la base de datos.
	 */
	public void setDao(GenericDao<E, PK> dao);

	/**
	 * Funci�n encargada de validar el contenido de la entidad antes de almacenarlo en la base de datos o de ocuparlo en el sistema.
	 * 
	 * @param entity
	 *            La entidad que va a corroborarse su validez.
	 * @throws CheckedException
	 *             En caso de que el contenido de la entidad no sea v�lido dentro del sistema.
	 */
	public void validate(E entity) throws CheckedException;

	/**
	 * La funci�n encargada de contar la cantidad de registros que tenemos dentro de la base de datos que corresponden a esta entidad.
	 * 
	 * @return El n�mero de registros que tenemos almacenados dentro de la base de datos.
	 * @throws CheckedException
	 *             En caso de que ocurra un error a la hora de contar filas dentro de la base de datos.
	 */
	public Integer count() throws CheckedException;

	/**
	 * La funci�n que nos permite recuperar todos las entidades del mismo tipo almacenados dentro de la base de datos.
	 * 
	 * @return El listado de los elementos almacenados.
	 * @throws CheckedException
	 *             En caso de un problema durante la recuperaci�n de todos las entidades desde la base de datos.
	 */
	public List<E> findAll() throws CheckedException;

	/**
	 * La funci�n encargada de contar la cantidad de registros que tenemos dentro de la base de datos que corresponden a esta entidad y que cumplen
	 * con la condici�n dada en el filtro recibido.
	 * 
	 * @param filter
	 *            El filtro para realizar la cuenta de registro dentro de la base de datos.
	 * @return El n�mero de registros que tenemos almacenados dentro de la base de datos y que corresponden con el filtro recibido.
	 * @throws CheckedException
	 *             En caso de que ocurra un error a la hora de contar filas dentro de la base de datos.
	 */
	public Integer countByFilter(Filter filter) throws CheckedException;

	/**
	 * La funci�n que nos permite recuperar todos las entidades del mismo tipo almacenados dentro de la base de datos y que cumplen un filtro
	 * recibido.
	 * 
	 * @param filter
	 *            El filtro para realizar la consulta de registro dentro de la base de datos.
	 * @return El listado de las entidades almacenados.
	 * @throws CheckedException
	 *             En caso de un problema durante la recuperaci�n de todos las entidades desde la base de datos.
	 */
	public List<E> findByFilter(Filter filter) throws CheckedException;

	/**
	 * La funci�n que utilizamos para recuperar una entidad dado su identificador.
	 * 
	 * @param id
	 *            El identificador de la entidad que vamos a recuperar desde la base de datos.
	 * @return La entidad que corresponde al identificador recibido. En caso de no encontrar nada, retorna un NULL.
	 * @throws CheckedException
	 *             En caso de un problema durante la recuperaci�n de la entidad desde la base de datos.
	 */
	public E findById(PK id) throws CheckedException;

	/**
	 * La funci�n para guardar la entidad dentro de la base de datos.
	 * 
	 * @param entity
	 *            La entidad que vamos a almacenar.
	 * @throws CheckedException
	 *             En caso de un problema durante el guardado de la entidad dentro de la base de datos.
	 */
	public void save(E entity) throws CheckedException;

	/**
	 * La funci�n para actualizar la entidad dentro de la base de datos.
	 * 
	 * @param entity
	 *            La entidad que vamos a actualizar.
	 * @throws CheckedException
	 *             En caso de un problema durante la actualizaci�n de la entidad dentro de la base de datos.
	 */
	public void update(E entity) throws CheckedException;

	/**
	 * La funci�n para insertar una nueva entidad o actualizar una que ya se encuentre dentro de la base de datos.
	 * 
	 * @param entity
	 *            La entidad que vamos a insertar o actualizar.
	 * @throws CheckedException
	 *             En caso de un problema durante la inserci�n o actualizaci�n de la entidad dentro de la base de datos.
	 */
	public void saveOrUpdate(E entity) throws CheckedException;

	/**
	 * La funci�n para eliminar la entidad dentro de la base de datos.
	 * 
	 * @param entity
	 *            La entidad que vamos a eliminar.
	 * @throws CheckedException
	 *             En caso de un problema durante la eliminaci�n de la entidad dentro de la base de datos.
	 */
	public void delete(E entity) throws CheckedException;

	/**
	 * La funci�n para eliminar la entidad dentro de la base de datos de acuerdo a su identificador.
	 * 
	 * @param id
	 *            El identificador que representa la entidad que vamos a eliminar.
	 * @throws CheckedException
	 *             En caso de un problema durante la eliminaci�n de la entidad dentro de la base de datos.
	 */
	public void deleteById(PK id) throws CheckedException;
}