package com.common.util.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.common.util.dao.GenericDao;
import com.common.util.exception.CheckedException;
import com.common.util.model.Entity;
import com.common.util.service.GenericService;

/**
 * La clase que vamos a utilizar para probar las diferentes partes del sistema manteniendo un servicio para las entidades de manera desconectada de
 * los DAOs.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de los Eos que vamos a almacenar dentro de este servicio.
 * @param <PK>
 *            La clase que hace de clave primaria para los Eos que vamos a almacenar con este servicio.
 */
public abstract class TestServiceImpl<E extends Entity<PK>, PK extends Serializable> implements GenericService<E, PK> {

	/**
	 * El logger de la ventana.
	 */
	private static final Logger log = Logger.getLogger(TestServiceImpl.class);

	/**
	 * El listado de las entidades.
	 */
	protected List<E> entities = new ArrayList<E>();
	/**
	 * El id.
	 */
	protected PK id;

	/**
	 * El constructor de este servicio que recibe el primer id que vamos a almacenar.
	 * 
	 * @param id
	 *            El primer id de las entidades.
	 */
	public TestServiceImpl(PK id) {
		super();
		this.id = id;
	}

	/**
	 * La función encargada de incrementar el identificador.
	 */
	protected abstract void incrementId();

	/**
	 * @see com.common.util.service.GenericService#setDao(com.common.util.dao.GenericDao)
	 */
	@Override
	public void setDao(GenericDao<E, PK> dao) {
		TestServiceImpl.log.trace("DefaultServiceImpl SetDAO");
	}

	/**
	 * @see com.common.util.service.GenericService#validate(com.common.util.model.Persistence)
	 */
	@Override
	public void validate(E entity) throws CheckedException {
		TestServiceImpl.log.trace("DefaultServiceImpl validate");
	}

	/**
	 * @see com.common.util.service.GenericService#findById(java.io.Serializable)
	 */
	@Override
	public E findById(PK id) throws CheckedException {
		TestServiceImpl.log.trace("DefaultServiceImpl FindById: " + id);
		this.incrementId();
		return this.get(id);
	}

	/**
	 * @see com.common.util.service.GenericService#findAll()
	 */
	@Override
	public List<E> findAll() throws CheckedException {
		TestServiceImpl.log.trace("DefaultServiceImpl FindAll");
		return this.entities;
	}

	/**
	 * @see com.common.util.service.GenericService#save(com.common.util.model.Persistence)
	 */
	@Override
	public void save(E entity) throws CheckedException {
		TestServiceImpl.log.trace("DefaultServiceImpl Save: " + entity);
		entity.setId(this.id);
		this.entities.add(entity);
	}

	/**
	 * @see com.common.util.service.GenericService#saveOrUpdate(com.common.util.model.Persistence)
	 */
	@Override
	public void saveOrUpdate(E entity) throws CheckedException {
		E delete = this.get(entity.getId());
		if (delete == null) {
			this.save(entity);
		} else {
			this.update(entity);
		}
	}

	/**
	 * @see com.common.util.service.GenericService#update(com.common.util.model.Persistence)
	 */
	@Override
	public void update(E entity) throws CheckedException {
		TestServiceImpl.log.trace("DefaultServiceImpl Update: " + entity);
		E update = this.get(entity.getId());
		this.entities.remove(update);
		this.entities.add(entity);
	}

	/**
	 * @see com.common.util.service.GenericService#delete(com.common.util.model.Persistence)
	 */
	@Override
	public void delete(E entity) throws CheckedException {
		TestServiceImpl.log.trace("DefaultServiceImpl Delete: " + entity);
		E delete = this.get(entity.getId());
		if (delete != null) {
			this.entities.remove(delete);
		}
	}

	/**
	 * La función encargada de buscar el Eo dentro del listado.
	 * 
	 * @param id
	 *            El identificador del Eo.
	 * @return La entidad que corresponde a ese id.
	 */
	private E get(PK id) {
		for (E e : this.entities) {
			if (id.equals(e.getId())) {
				return e;
			}
		}
		return null;
	}
}
