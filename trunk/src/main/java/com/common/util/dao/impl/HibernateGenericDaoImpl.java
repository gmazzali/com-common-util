package com.common.util.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map.Entry;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.common.util.dao.GenericDao;
import com.common.util.model.Persistence;
import com.common.util.model.query.filter.AtomicFilter;
import com.common.util.model.query.filter.BetweenFilter;
import com.common.util.model.query.filter.BinaryComplexFilter;
import com.common.util.model.query.filter.CompareFilter;
import com.common.util.model.query.filter.ComplexFilter;
import com.common.util.model.query.filter.Filter;
import com.common.util.model.query.filter.InFilter;
import com.common.util.model.query.filter.LikeFilter;
import com.common.util.model.query.filter.NullFilter;
import com.common.util.model.query.filter.UnaryComplexFilter;
import com.common.util.model.query.order.Order;
import com.common.util.model.query.order.OrderBy;

/**
 * La clase que implementa la interfaz {@link GenericDao} para acceder a una base de datos desde el framework Hibernate.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @see GenericDao
 * 
 * @param <E>
 *            La clase que corresponde a la entidad que vamos a persistir dentro de la base de datos.
 * @param <PK>
 *            La clase que corresponde al identificador de la entidad <T>.
 */
@SuppressWarnings("unchecked")
public abstract class HibernateGenericDaoImpl<E extends Persistence<PK>, PK extends Serializable> implements GenericDao<E, PK> {
	private static final long serialVersionUID = 1L;

	/**
	 * La enumeración de los posibles cierres para una transacción.
	 * 
	 * @author Guillermo Mazzali
	 * @version 1.0
	 */
	protected enum TransactionAction {
		COMMIT, ROLLBACK;
	}

	/**
	 * La clase que persistimos dentro de la base de datos.
	 */
	private final Class<E> persistentClass;
	/**
	 * La fabrica de sesiones que vamos a utilizar para acceder a la base de datos.
	 */
	private SessionFactory sessionFactory;
	/**
	 * La sesión que vamos a ocupar dentro de este DAO.
	 */
	private Session session;

	/**
	 * El constructor de un elemento que nos permite manejar todos los DAOs que van a utilizar Hibernate como framework de acceso a la base de datos.
	 */
	public HibernateGenericDaoImpl() {
		this.persistentClass = (Class<E>) ((java.lang.reflect.ParameterizedType) super.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Override
	public Long count() throws RuntimeException {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(this.persistentClass);
		Long value = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
		this.closeSession(session);
		return value;
	}

	@Override
	public Long countByFilter(Filter filter) throws RuntimeException {
		Long value = null;
		if (filter != null) {
			Session session = this.getSession();
			Criteria criteria = session.createCriteria(this.persistentClass);

			// Cargamos el filtro.
			this.loadFilterToCriteria(criteria, filter);

			value = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			this.closeSession(session);
		} else {
			value = this.count();
		}
		return value;
	}

	@Override
	public E findById(PK id) throws RuntimeException {
		Session session = this.getSession();
		E entity = (E) session.get(this.persistentClass, id);
		this.closeSession(session);
		return entity;
	}

	@Override
	public List<E> findAll(OrderBy orders) throws RuntimeException {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(this.persistentClass);

		// Cargamos los ordenes.
		if (orders != null && !orders.getOrders().isEmpty()) {
			this.loadOrderToCriteria(criteria, orders);
		}

		List<E> listado = criteria.list();
		this.closeSession(session);
		return listado;
	}

	@Override
	public List<E> findByFilter(Filter filter, OrderBy orders) throws RuntimeException {
		List<E> entities = null;

		if (filter != null) {
			Session session = this.getSession();
			Criteria criteria = session.createCriteria(this.persistentClass);

			// Cargamos el filtro.
			this.loadFilterToCriteria(criteria, filter);

			// Cargamos los ordenes.
			if (orders != null && !orders.getOrders().isEmpty()) {
				this.loadOrderToCriteria(criteria, orders);
			}

			entities = criteria.list();
			this.closeSession(session);
		} else {
			entities = this.findAll(orders);
		}

		return entities;
	}

	@Override
	public void save(E entity) throws RuntimeException {
		Session session = this.getSession();
		Transaction transaction = this.beginTransaction(session);

		session.save(entity);

		this.endTransaction(transaction, TransactionAction.COMMIT);
		this.closeSession(session);
	}

	@Override
	public void update(E entity) throws RuntimeException {
		Session session = this.getSession();
		Transaction transaction = this.beginTransaction(session);

		session.update(entity);

		this.endTransaction(transaction, TransactionAction.COMMIT);
		this.closeSession(session);
	}

	@Override
	public void saveOrUpdate(E entity) throws RuntimeException {
		Session session = this.getSession();
		Transaction transaction = this.beginTransaction(session);

		session.saveOrUpdate(entity);

		this.endTransaction(transaction, TransactionAction.COMMIT);
		this.closeSession(session);
	}

	@Override
	public void delete(E entity) throws RuntimeException {
		Session session = this.getSession();
		Transaction transaction = this.beginTransaction(session);

		session.delete(entity);

		this.endTransaction(transaction, TransactionAction.COMMIT);
		this.closeSession(session);
	}

	@Override
	public void deleteById(PK id) throws RuntimeException {
		Session session = this.getSession();
		Transaction transaction = this.beginTransaction(session);

		E entity = (E) session.load(this.persistentClass, id);
		session.delete(entity);

		this.endTransaction(transaction, TransactionAction.COMMIT);
		this.closeSession(session);
	}

	/**
	 * Función que permite crear o recuperar una sesión a partir de la session factory.
	 * 
	 * @see HibernateGenericDaoImpl#closeSession(Session)
	 * 
	 * @return La sesión que vamos a ocupar para acceder a la base de datos.
	 */
	protected Session getSession() {
		if (this.session == null) {
			this.session = this.sessionFactory.openSession();
		}
		return this.session;
	}

	/**
	 * Función que permite cerrar la sesión actual para liberar recursos.
	 * 
	 * @see HibernateGenericDaoImpl#getSession()
	 * 
	 * @param session
	 *            La sesión abierta que queremos cerrar dentro del sistema.
	 */
	protected void closeSession(Session session) {
	}

	/**
	 * Se encarga de crear una transacción para limitar el campo de efecto de las acciones contra la base de datos.
	 * 
	 * @see HibernateGenericDaoImpl#endTransaction(Transaction, TransactionAction)
	 * 
	 * @param session
	 *            La sesión sobre la que se va a comenzar una transacción.
	 * @return La transacción creada desde la sesión.
	 */
	protected Transaction beginTransaction(Session session) {
		Transaction transaction = session.beginTransaction();
		return transaction;
	}

	/**
	 * Se encarga de cerrar una transacción para impactar o deshacer los cambios contra la base de datos.
	 * 
	 * @see HibernateGenericDaoImpl#beginTransaction(Session)
	 * 
	 * @param transaction
	 *            La transacción que vamos a impactar o deshacer.
	 * @param action
	 *            La acción que indica si vamos a impactar los cambios o vamos a deshacerlos.
	 */
	protected void endTransaction(Transaction transaction, TransactionAction action) {
		switch (action) {
		case COMMIT:
			transaction.commit();
			break;

		case ROLLBACK:
			transaction.rollback();
			break;
		}
	}

	/**
	 * La función que permite retornar la clase a la que estamos haciendo persistencia.
	 * 
	 * @return La clase a la que hacemos persistencia.
	 */
	public Class<E> getPersistentClass() {
		return this.persistentClass;
	}

	/**
	 * Función encargada de cargar la session factory del dao genérico.
	 * 
	 * @param sessionFactory
	 *            La session factory conectada a la base de datos.
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * La función encargada de crear un objeto Criterion dado un criterio de búsqueda.
	 * 
	 * @see Filter
	 * 
	 * @param filter
	 *            El filtro que vamos a utilizar para crear el Criterion.
	 * @return El Criterion que corresponde al filtro de búsqueda.
	 */
	protected void loadFilterToCriteria(Criteria criteria, Filter filter) {
		criteria.add(this.getCriterion(filter));
	}

	/**
	 * Se encarga de cargar los ordenes en las que queremos recuperar las entidades.
	 * 
	 * @param criteria
	 *            La criteria a la que le vamos a cargar los ordenes que recibimos.
	 * @param orders
	 *            Los orcenes de las propiedades de las entidades que queremos ordenar.
	 */
	protected void loadOrderToCriteria(Criteria criteria, OrderBy orders) {
		for (Entry<String, Order> entry : orders.getOrders().entrySet()) {
			if (Order.ASC.equals(entry.getValue())) {
				criteria.addOrder(org.hibernate.criterion.Order.asc(entry.getKey()));
			} else {
				criteria.addOrder(org.hibernate.criterion.Order.desc(entry.getKey()));
			}
		}
	}

	/**
	 * La función encargada de crear un objeto Criterion dado un criterio de búsqueda.
	 * 
	 * @see Filter
	 * 
	 * @param filter
	 *            El filtro que vamos a utilizar para crear el Criterion.
	 * @return El Criterion que corresponde al filtro de búsqueda.
	 */
	protected Criterion getCriterion(Filter filter) {
		// Si es un filtro atómico.
		if (filter instanceof AtomicFilter) {
			AtomicFilter<Serializable> atomicFilter = (AtomicFilter<Serializable>) filter;

			switch (atomicFilter.getAtomicFilterType()) {

			case BETWEEN:
				// Si el filtro es de una operación "between"
				BetweenFilter<Serializable> betweenFilter = (BetweenFilter<Serializable>) atomicFilter;
				return Restrictions.between(betweenFilter.getAttribute(), betweenFilter.getLowValue(), betweenFilter.getHighValue());

			case COMPARE:
				// Si el filtro es de una operación "=" o "<>" o "<" o "<=" o ">" o ">="
				CompareFilter<Serializable> compareFilter = (CompareFilter<Serializable>) atomicFilter;
				switch (compareFilter.getCompareFilterType()) {
				case EQUALS:
					return Restrictions.eq(compareFilter.getAttribute(), compareFilter.getValue());

				case NOT_EQUALS:
					return Restrictions.ne(compareFilter.getAttribute(), compareFilter.getValue());

				case GREATER:
					return Restrictions.gt(compareFilter.getAttribute(), compareFilter.getValue());

				case GREATER_OR_EQUALS:
					return Restrictions.ge(compareFilter.getAttribute(), compareFilter.getValue());

				case LESSER:
					return Restrictions.lt(compareFilter.getAttribute(), compareFilter.getValue());

				case LESSER_OR_EQUALS:
					return Restrictions.le(compareFilter.getAttribute(), compareFilter.getValue());
				}

			case IN:
				// Si el filtro es de una operación "in"
				InFilter<Serializable> inFilter = (InFilter<Serializable>) atomicFilter;
				return Restrictions.in(inFilter.getAttribute(), inFilter.getList());

			case LIKE:
				// Si el filtro es de una operación "like"
				LikeFilter likeFilter = (LikeFilter) atomicFilter;
				return Restrictions.like(likeFilter.getAttribute(), likeFilter.getLike());

			case NULL:
				// Si el filtro es de una operación "is Null"
				NullFilter nullFilter = (NullFilter) atomicFilter;
				return Restrictions.isNull(nullFilter.getAttribute());
			}
		} else if (filter instanceof ComplexFilter) {

			// Si el filtro es uno complejo, la procesamos.
			ComplexFilter complexFilter = (ComplexFilter) filter;
			switch (complexFilter.getComplexType()) {

			case UNARY:
				// Si el filtro, es un filtro unario del tipo "not"
				UnaryComplexFilter unaryComplexFilter = (UnaryComplexFilter) complexFilter;
				return Restrictions.not(this.getCriterion(unaryComplexFilter.getFilter()));

			case BINARY:
				// Si el filtro es un filtro binario.
				BinaryComplexFilter binaryComplexFilter = (BinaryComplexFilter) complexFilter;
				switch (binaryComplexFilter.getBinaryType()) {

				case AND:
					// Si el filtro es del tipo "and"
					return Restrictions.and(this.getCriterion(binaryComplexFilter.getFirstFilter()),
							this.getCriterion(binaryComplexFilter.getSecondFilter()));

				case OR:
					// Si el filtro es del tipo "or"
					return Restrictions.or(this.getCriterion(binaryComplexFilter.getFirstFilter()),
							this.getCriterion(binaryComplexFilter.getSecondFilter()));
				}
			}
		}
		return null;
	}
}