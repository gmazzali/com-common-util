package com.common.util.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.common.util.dao.GenericDao;
import com.common.util.exception.CheckedException;
import com.common.util.model.Entity;
import com.common.util.model.query.filter.Filter;
import com.common.util.model.query.order.OrderBy;
import com.common.util.service.GenericService;

/**
 * La clase que vamos a utilizar para probar las diferentes partes del sistema manteniendo un servicio para las entidades de manera desconectada de
 * los DAOs.
 * 
 * @since 05/02/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de los Eos que vamos a almacenar dentro de este servicio.
 * @param <PK>
 *            La clase que hace de clave primaria para los Eos que vamos a almacenar con este servicio.
 */
public abstract class TestServiceImpl<E extends Entity<PK>, PK extends Serializable> implements GenericService<E, PK> {
	private static final long serialVersionUID = 1L;

	/**
	 * El logger de la ventana.
	 */
	private static final Logger log = Logger.getLogger(TestServiceImpl.class);

	/**
	 * El mapa de las entidades.
	 */
	protected Map<PK, E> entities = new HashMap<PK, E>();
	/**
	 * El identificador de las entidades.
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

	@Override
	public void setDao(GenericDao<E, PK> dao) {
		TestServiceImpl.log.trace("set dao");
	}

	@Override
	public void validate(E entity) throws CheckedException {
		TestServiceImpl.log.trace("validate");
	}

	@Override
	public Long count() throws CheckedException {
		TestServiceImpl.log.trace("count");

		return (long) this.entities.size();
	}

	@Override
	public Long countByFilter(Filter filter) throws CheckedException {
		return null;
	}

	@Override
	public E findById(PK id) throws CheckedException {
		TestServiceImpl.log.trace("find by id: " + id);

		this.incrementId();
		return this.entities.get(id);
	}

	@Override
	public List<E> findAll(OrderBy orders) throws CheckedException {
		TestServiceImpl.log.trace("find all");

		return (List<E>) this.entities.values();
	}

	@Override
	public List<E> findByFilter(Filter filter, OrderBy orders) throws CheckedException {
		return null;
	}

	@Override
	public void save(E entity) throws CheckedException {
		TestServiceImpl.log.trace("save: " + entity);

		entity.setId(this.id);
		this.entities.put(this.id, entity);
	}

	@Override
	public void saveOrUpdate(E entity) throws CheckedException {
		TestServiceImpl.log.trace("save or update: " + entity);

		E update = this.entities.get(entity.getId());
		if (update == null) {
			this.save(entity);
		} else {
			this.update(entity);
		}
	}

	@Override
	public void update(E entity) throws CheckedException {
		TestServiceImpl.log.trace("update: " + entity);

		this.entities.remove(entity.getId());
		this.entities.put(entity.getId(), entity);
	}

	@Override
	public void delete(E entity) throws CheckedException {
		TestServiceImpl.log.trace("delete: " + entity);

		E delete = this.entities.get(entity.getId());
		if (delete != null) {
			this.entities.remove(entity.getId());
		}
	}

	@Override
	public void deleteById(PK id) throws CheckedException {
		TestServiceImpl.log.trace("delete by id: " + id);

		this.entities.remove(id);
	}
}