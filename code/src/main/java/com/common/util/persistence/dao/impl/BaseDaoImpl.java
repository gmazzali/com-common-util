package com.common.util.persistence.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.common.util.business.tool.collection.ArrayUtil;
import com.common.util.domain.exception.PersistenceException;
import com.common.util.domain.model.entity.Persistence;
import com.common.util.domain.model.entity.impl.Entity;
import com.common.util.domain.model.util.RangeType;
import com.common.util.persistence.dao.BaseDao;
import com.common.util.persistence.filter.BaseFilter;
import com.common.util.persistence.filter.BaseFilter.Comparator;
import com.common.util.persistence.filter.Order;
import com.google.common.collect.Lists;

/**
 * The implementation of the interface that define all the commons behavior of the DAOs.
 * 
 * @see Entity
 * @see Persistence
 * @see Serializable
 * 
 * @since 05/02/2014
 * @author Guillermo S. Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            The entity of this DAO.
 * @param <PK>
 *            The primary key of the entity of this DAO.
 */
public abstract class BaseDaoImpl<E extends Persistence<PK>, PK extends Serializable> implements BaseDao<E, PK> {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(BaseDaoImpl.class);

	/**
	 * The persistent class.
	 */
	private final Class<?> persistentClass;
	/**
	 * The {@link SessionFactory} used to communicated with the database.
	 */
	private SessionFactory sessionFactory;
	/**
	 * The {@link Session} used in this DAO.
	 */
	private Session session;

	/**
	 * The default constructor.
	 */
	public BaseDaoImpl() {
		try {
			this.persistentClass = (Class<?>) ((ParameterizedType) super.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		} catch (Exception ex) {
			LOGGER.error("The generic parameter class of the base dao cannot be empty", ex);
			throw new PersistenceException("The generic parameter class of the base dao cannot be empty", "base.dao.error.parameter.empty");
		}
	}

	/**
	 * Allow set the {@link SessionFactory} for this DAO.
	 * 
	 * @param sessionFactory
	 *            The {@link SessionFactory} already connect to the database.
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Long count() throws PersistenceException {
		try {
			Session session = this.getSession();
			Criteria criteria = session.createCriteria(this.persistentClass);
			Long value = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			this.closeSession(session);
			return value;
		} catch (RuntimeException e) {
			LOGGER.error("count failed", e);
			throw new PersistenceException(e);
		}
	}

	@Override
	public Long count(BaseFilter<E, PK> filter) throws PersistenceException {
		try {
			Long value = null;
			if (filter != null) {
				Session session = this.getSession();
				DetachedCriteria detachedCriteria = DetachedCriteria.forClass(this.persistentClass);
				this.addFilterRestriction(detachedCriteria, filter);
				Criteria criteria = detachedCriteria.getExecutableCriteria(this.getSession());
				value = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
				this.closeSession(session);
			} else {
				value = this.count();
			}
			return value;
		} catch (RuntimeException e) {
			LOGGER.error("count by filter failed", e);
			throw new PersistenceException(e);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public E getById(PK id) throws PersistenceException {
		try {
			Session session = this.getSession();
			E entity = (E) session.get(this.persistentClass, id);
			this.closeSession(session);
			return entity;
		} catch (RuntimeException e) {
			LOGGER.error("get by id failed", e);
			throw new PersistenceException(e);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public E findById(PK id) throws PersistenceException {
		try {
			Session session = this.getSession();
			E entity = (E) session.load(this.persistentClass, id);
			this.closeSession(session);
			return entity;
		} catch (RuntimeException e) {
			LOGGER.error("find by id failed", e);
			throw new PersistenceException(e);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<E> getAll(Order... orders) throws PersistenceException {
		try {
			Session session = this.getSession();
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(this.persistentClass);
			if (ArrayUtil.isNotEmpty(orders)) {
				this.addOrders(detachedCriteria, Lists.newArrayList(orders));
			}
			Criteria criteria = detachedCriteria.getExecutableCriteria(this.getSession());
			List<E> listado = criteria.list();
			this.closeSession(session);
			return listado;
		} catch (RuntimeException e) {
			LOGGER.error("find all failed", e);
			throw new PersistenceException(e);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<E> filter(BaseFilter<E, PK> filter) throws PersistenceException {
		try {
			List<E> entities = null;
			if (filter != null) {
				List<Order> orders = filter.getOrders();
				Session session = this.getSession();
				DetachedCriteria detachedCriteria = DetachedCriteria.forClass(this.persistentClass);
				this.addFilterRestriction(detachedCriteria, filter);
				if (CollectionUtils.isNotEmpty(orders)) {
					this.addOrders(detachedCriteria, orders);
				}
				Criteria criteria = detachedCriteria.getExecutableCriteria(this.getSession());
				this.addPagination(criteria, filter);
				entities = criteria.list();
				this.closeSession(session);
			}
			return entities;
		} catch (RuntimeException e) {
			LOGGER.error("find by filter failed", e);
			throw new PersistenceException(e);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public PK save(E entity) throws PersistenceException {
		try {
			Session session = this.getSession();
			PK pk = (PK) session.save(entity);
			this.closeSession(session);
			return pk;
		} catch (RuntimeException e) {
			LOGGER.error("save failed", e);
			throw new PersistenceException(e);
		}
	}

	@Override
	public void update(E entity) throws PersistenceException {
		try {
			Session session = this.getSession();
			session.update(entity);
			this.closeSession(session);
		} catch (RuntimeException e) {
			LOGGER.error("update failed", e);
			throw new PersistenceException(e);
		}
	}

	@Override
	public void saveOrUpdate(E entity) throws PersistenceException {
		try {
			Session session = this.getSession();
			session.saveOrUpdate(entity);
			this.closeSession(session);
		} catch (RuntimeException e) {
			LOGGER.error("save or update failed", e);
			throw new PersistenceException(e);
		}
	}

	@Override
	public void delete(E entity) throws PersistenceException {
		try {
			Session session = this.getSession();
			session.delete(entity);
			this.closeSession(session);
		} catch (RuntimeException e) {
			LOGGER.error("delete failed", e);
			throw new PersistenceException(e);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public void deleteById(PK id) throws PersistenceException {
		try {
			Session session = this.getSession();
			E entity = (E) session.load(this.persistentClass, id);
			session.delete(entity);
			this.closeSession(session);
		} catch (RuntimeException e) {
			LOGGER.error("delete by id failed", e);
			throw new PersistenceException(e);
		}
	}

	/**
	 * Allow create or return the {@link Session} from the {@link SessionFactory}
	 * 
	 * @see #closeSession(Session)
	 * 
	 * @return The {@link Session} to used inside this DAO.
	 */
	protected Session getSession() {
		if (this.session == null) {
			try {
				LOGGER.info("get current session");
				this.session = this.sessionFactory.getCurrentSession();
			} catch (RuntimeException e) {
				LOGGER.error("fail get current session", e);
				LOGGER.info("open new session");
				this.session = this.sessionFactory.openSession();
			}
		}
		return this.session;
	}

	/**
	 * Allow close the current {@link Session}.
	 * 
	 * @see #getSession()
	 * 
	 * @param session
	 *            The current {@link Session} opened to be closed.
	 */
	protected void closeSession(Session session) {
	}

	/**
	 * Add the {@link Order} to the {@link Criteria}.
	 * 
	 * @param criteria
	 *            The {@link Criteria} where we put the orders.
	 * @param orders
	 *            The {@link Order} to put into the query.
	 */
	protected void addOrders(DetachedCriteria criteria, List<Order> orders) {
		for (Order entry : orders) {
			if (Order.OrderType.ASC.equals(entry.getOrderType())) {
				criteria.addOrder(org.hibernate.criterion.Order.asc(entry.getProperty()));
			} else {
				criteria.addOrder(org.hibernate.criterion.Order.desc(entry.getProperty()));
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
	 * @see #addRangeRestriction(DetachedCriteria, RangeType, String)
	 * 
	 * @param criteria
	 *            La {@link Criteria} que vamos a cargar con las restricciones del filtro recibido.
	 * @param filter
	 *            El filtro que vamos a utilizar para crear el {@link Criteria}.
	 */
	protected void addFilterRestriction(DetachedCriteria criteria, BaseFilter<E, PK> filter) {
		if (CollectionUtils.isNotEmpty(filter.getExcludeIds())) {
			criteria.add(Restrictions.not(Restrictions.in(Entity.Attributes.ID, filter.getExcludeIds())));
		}

		Set<String> properties = filter.getRestrictions().keySet();
		for (String property : properties) {
			List<Pair<Comparator, Object>> restrictions = filter.getRestrictions().get(property);
			for (Pair<Comparator, Object> restriction : restrictions) {
				switch (restriction.getLeft()) {
				case LESS:
					criteria.add(Restrictions.lt(property, restriction.getRight()));
					break;

				case LESS_OR_EQUAL:
					criteria.add(Restrictions.le(property, restriction.getRight()));
					break;

				case EQUAL:
					criteria.add(Restrictions.eq(property, restriction.getRight()));
					break;

				case NOT_EQUAL:
					criteria.add(Restrictions.ne(property, restriction.getRight()));
					break;

				case GREATER_OR_EQUAL:
					criteria.add(Restrictions.ge(property, restriction.getRight()));
					break;

				case GREATER:
					criteria.add(Restrictions.gt(property, restriction.getRight()));
					break;

				case IN:
					Collection<?> list = (Collection<?>) restriction.getRight();
					criteria.add(Restrictions.in(property, list));

				case BETWEEN:
					RangeType<?> range = (RangeType<?>) restriction.getRight();
					this.addRangeRestriction(criteria, range, property);
					break;

				case NOT_NULL:
					criteria.add(Restrictions.isNotNull(property));
					break;

				case NULL:
					criteria.add(Restrictions.isNull(property));
					break;
				}
			}
		}
	}

	/**
	 * Allow add a type of restrictions "BETWEEN" with a parameter of {@link RangeType}. If the parameter is complete (the field "from" and "to" are
	 * complete) the query will be complete with a "BETWEEN" restrictions, in others case, the query will be complete with a ">=" or "<="
	 * restrictions.
	 * 
	 * @see RangeType
	 * 
	 * @see #addFilterRestriction(Criteria, BaseFilter)
	 * @see #addPagination(Criteria, BaseFilter)
	 * 
	 * @param criteria
	 *            The {@link DetachedCriteria} where we put the restriction.
	 * @param range
	 *            The {@link RangeType} where we get the range.
	 * @param field
	 *            The field of the entity for the query.
	 */
	protected void addRangeRestriction(DetachedCriteria criteria, RangeType<?> range, String field) {
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
	 * Allow add a restrictions of paginations to the query.
	 * 
	 * @see Criteria
	 * @see BaseFilter
	 * 
	 * @see #addFilterRestriction(Criteria, BaseFilter)
	 * @see #addRangeRestriction(DetachedCriteria, RangeType, String)
	 * 
	 * @param criteria
	 *            The {@link Criteria} where we put the restriction.
	 * @param filter
	 *            The {@link BaseFilter} filter where we get the restrictions.
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