package com.common.util.persistence.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.common.util.domain.exception.UncheckedException;
import com.common.util.domain.model.Entity;
import com.common.util.domain.model.Persistence;
import com.common.util.domain.model.RangeType;
import com.common.util.persistence.dao.BaseDao;
import com.common.util.persistence.filter.BaseFilter;
import com.common.util.persistence.filter.order.Order;
import com.common.util.persistence.filter.order.Orders;

/**
 * La clase que implementa la interfaz {@link BaseDao} para acceder a una base de datos desde el framework Hibernate.
 * 
 * @see BaseDao
 * 
 * @since 05/02/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase que corresponde a la entidad que vamos a persistir dentro de la base de datos.
 * @param <PK>
 *            La clase que corresponde al identificador de la entidad {@link E}.
 */
@SuppressWarnings("unchecked")
public abstract class BaseDaoImpl<E extends Persistence<PK>, PK extends Serializable> implements BaseDao<E, PK> {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(BaseDaoImpl.class);

	/**
	 * La clase que persistimos dentro de la base de datos.
	 */
	private final Class<E> persistentClass;
	/**
	 * La {@link SessionFactory} que vamos a utilizar para acceder a la base de datos.
	 */
	private SessionFactory sessionFactory;
	/**
	 * La {@link Session} que vamos a ocupar dentro de este DAO.
	 */
	private Session session;

	/**
	 * El constructor de un elemento que nos permite manejar todos los DAOs que van a utilizar {@link Hibernate} como framework de acceso a la base de
	 * datos.
	 */
	public BaseDaoImpl() {
		try {
			this.persistentClass = (Class<E>) ((ParameterizedType) super.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		} catch (Exception ex) {
			log.error("The generic parameter class of the base dao cannot be empty", ex);
			throw new UncheckedException("The generic parameter class of the base dao cannot be empty", "base.dao.error.parameter.empty");
		}

		// this.persistentClass = (Class<E>) ((ParameterizedType) super.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/**
	 * Función encargada de cargar la {@link SessionFactory} del dao genérico.
	 * 
	 * @param sessionFactory
	 *            La {@link SessionFactory} conectada a la base de datos.
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Permite retornar la clase a la que estamos haciendo persistencia.
	 * 
	 * @return La clase que estamos manipualndo dentro de este Dao.
	 */
	public Class<E> getPersistentClass() {
		return this.persistentClass;
	}

	@Override
	public Long count() {
		try {
			Session session = this.getSession();
			Criteria criteria = session.createCriteria(this.persistentClass);
			Long value = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			this.closeSession(session);
			return value;
		} catch (RuntimeException e) {
			BaseDaoImpl.log.error("count failed", e);
			throw new UncheckedException(e);
		}
	}

	@Override
	public Long countByFilter(BaseFilter<E, PK> filter) {
		try {
			Long value = null;
			if (filter != null) {
				Session session = this.getSession();
				Criteria criteria = this.session.createCriteria(persistentClass);

				this.addFilterRestriction(criteria, filter);

				value = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
				this.closeSession(session);
			} else {
				value = this.count();
			}
			return value;
		} catch (RuntimeException e) {
			BaseDaoImpl.log.error("count by filter failed", e);
			throw new UncheckedException(e);
		}
	}

	@Override
	public E findById(PK id) {
		try {
			Session session = this.getSession();
			E entity = (E) session.get(this.persistentClass, id);
			this.closeSession(session);
			return entity;
		} catch (RuntimeException e) {
			BaseDaoImpl.log.error("find by id failed", e);
			throw new UncheckedException(e);
		}
	}

	@Override
	public List<E> findAll(Orders orders) {
		try {
			Session session = this.getSession();
			Criteria criteria = session.createCriteria(this.persistentClass);

			// Cargamos los ordenes.
			if (orders != null && !orders.getOrders().isEmpty()) {
				this.addOrders(criteria, orders);
			}

			List<E> listado = criteria.list();
			this.closeSession(session);
			return listado;
		} catch (RuntimeException e) {
			BaseDaoImpl.log.error("find all failed", e);
			throw new UncheckedException(e);
		}
	}

	@Override
	public List<E> findByFilter(BaseFilter<E, PK> filter) {
		try {
			List<E> entities = null;

			if (filter != null) {
				Orders orders = filter.getOrders();
				Session session = this.getSession();
				Criteria criteria = this.session.createCriteria(persistentClass);

				this.addFilterRestriction(criteria, filter);

				if (orders != null && !orders.getOrders().isEmpty()) {
					this.addOrders(criteria, orders);
				}

				this.addPagination(criteria, filter);

				entities = criteria.list();
				this.closeSession(session);
			}

			return entities;
		} catch (RuntimeException e) {
			BaseDaoImpl.log.error("find by filter failed", e);
			throw new UncheckedException(e);
		}
	}

	@Override
	public PK save(E entity) {
		try {
			Session session = this.getSession();
			PK pk = (PK) session.save(entity);
			this.closeSession(session);
			return pk;
		} catch (RuntimeException e) {
			BaseDaoImpl.log.error("save failed", e);
			throw new UncheckedException(e);
		}
	}

	@Override
	public void update(E entity) {
		try {
			Session session = this.getSession();
			session.update(entity);
			this.closeSession(session);
		} catch (RuntimeException e) {
			BaseDaoImpl.log.error("update failed", e);
			throw new UncheckedException(e);
		}
	}

	@Override
	public void saveOrUpdate(E entity) {
		try {
			Session session = this.getSession();
			session.saveOrUpdate(entity);
			this.closeSession(session);
		} catch (RuntimeException e) {
			BaseDaoImpl.log.error("save or update failed", e);
			throw new UncheckedException(e);
		}
	}

	@Override
	public void delete(E entity) {
		try {
			Session session = this.getSession();
			session.delete(entity);
			this.closeSession(session);
		} catch (RuntimeException e) {
			BaseDaoImpl.log.error("delete failed", e);
			throw new UncheckedException(e);
		}
	}

	@Override
	public void deleteById(PK id) {
		try {
			Session session = this.getSession();
			E entity = (E) session.load(this.persistentClass, id);
			session.delete(entity);
			this.closeSession(session);
		} catch (RuntimeException e) {
			BaseDaoImpl.log.error("delete by id failed", e);
			throw new UncheckedException(e);
		}
	}

	/**
	 * Función que permite crear o recuperar una {@link Session} a partir de la session factory.
	 * 
	 * @see #closeSession(Session)
	 * 
	 * @return La {@link Session} que vamos a ocupar para acceder a la base de datos.
	 */
	protected Session getSession() {
		if (this.session == null) {
			this.session = this.sessionFactory.openSession();
		}
		return this.session;
	}

	/**
	 * Función que permite cerrar la {@link Session} actual para liberar recursos.
	 * 
	 * @see #getSession()
	 * 
	 * @param session
	 *            La {@link Session} abierta que queremos cerrar dentro del sistema.
	 */
	protected void closeSession(Session session) {
	}

	/**
	 * Se encarga de cargar los ordenes en las que queremos recuperar las entidades.
	 * 
	 * @param criteria
	 *            La {@link Criteria} a la que le vamos a cargar los ordenes que recibimos.
	 * @param orders
	 *            Los {@link Order} de las propiedades de las entidades que queremos ordenar.
	 */
	protected void addOrders(Criteria criteria, Orders orders) {
		for (Entry<String, Order> entry : orders.getOrders().entrySet()) {
			if (Order.ASC.equals(entry.getValue())) {
				criteria.addOrder(org.hibernate.criterion.Order.asc(entry.getKey()));
			} else {
				criteria.addOrder(org.hibernate.criterion.Order.desc(entry.getKey()));
			}
		}
	}

	/**
	 * Permite cargar una {@link Criteria} con los valores del {@link BaseFilter} que recibimos como parámetro.
	 * 
	 * @see Criteria
	 * @see BaseFilter
	 * 
	 * @see #addPagination(Criteria, BaseFilter)
	 * @see #addRangeRestriction(Criteria, RangeType, String)
	 * 
	 * @param criteria
	 *            La {@link Criteria} que vamos a cargar con las restricciones del filtro recibido.
	 * @param filter
	 *            El filtro que vamos a utilizar para crear el {@link Criteria}.
	 * @return El {@link Criteria} que corresponde al filtro de búsqueda.
	 */
	protected Criteria addFilterRestriction(Criteria criteria, BaseFilter<E, PK> filter) {
		if (filter.getExcludeIds() != null && filter.getExcludeIds().length > 0) {
			criteria.add(Restrictions.not(Restrictions.in(Entity.Attributes.ID, filter.getExcludeIds())));
		}

		return criteria;
	}

	/**
	 * Permite crear una condición para los tipos de valores que vamos a manejar con rangos del tipo {@link RangeType}.
	 * 
	 * @see Criteria
	 * @see RangeType
	 * 
	 * @see #addFilterRestriction(Criteria, BaseFilter)
	 * @see #addPagination(Criteria, BaseFilter)
	 * 
	 * @param criteria
	 *            La {@link Criteria} que vamos a cargar con la restricción.
	 * @param range
	 *            El {@link RangeType} de valores que vamos a utilizar para la condición.
	 * @param field
	 *            El campo sobre el que se va a realizar la condición.
	 */
	protected void addRangeRestriction(Criteria criteria, RangeType<?> range, String field) {
		if (range != null) {
			if (range.getTo() != null && range.getFrom() != null) {
				criteria.add(Restrictions.between(field, range.getFrom(), range.getTo()));
			} else {
				if (range.getFrom() != null) {
					criteria.add(Restrictions.ge(field, range.getFrom()));
				}
				if (range.getTo() != null) {
					criteria.add(Restrictions.le(field, range.getTo()));
				}
			}
		}
	}

	/**
	 * Permite otorgarle paginación a las consultas sobre la base de datos para no sobrecargar las mismas.
	 * 
	 * @see Criteria
	 * @see BaseFilter
	 * 
	 * @see #addFilterRestriction(Criteria, BaseFilter)
	 * @see #addRangeRestriction(Criteria, RangeType, String)
	 * 
	 * @param criteria
	 *            La {@link Criteria} a la que le vamos a cargar la paginación de resultados.
	 * @param filter
	 *            El {@link BaseFilter} que vamos a usar para la paginación.
	 */
	protected void addPagination(Criteria criteria, BaseFilter<E, PK> filter) {
		if (filter.getFirstResult() != null) {
			criteria.setFirstResult(filter.getFirstResult());
		}
		if (filter.getMaxResult() != null) {
			criteria.setMaxResults(filter.getMaxResult());
		}
	}
}