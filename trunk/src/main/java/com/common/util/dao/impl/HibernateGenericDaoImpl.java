package com.common.util.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

	/**
	 * La clase que persistimos dentro de la base de datos.
	 */
	private final Class<E> persistentClass;
	/**
	 * La session factory que vamos a utilizar para acceder a la base de datos.
	 */
	private SessionFactory sessionFactory;
	/**
	 * La session que vamos a ocupar dentro de este DAO.
	 */
	private Session session;

	/**
	 * El constructor de un elemento que nos permite manejar todos los DAOs que van a utilizar Hibernate como framework de acceso a la base de datos.
	 */
	public HibernateGenericDaoImpl() {
		this.persistentClass = (Class<E>) ((java.lang.reflect.ParameterizedType) super.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Override
	public Integer count() throws RuntimeException {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(this.persistentClass);
		return (Integer) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}

	@Override
	public Integer countByFilter(Filter filter) throws RuntimeException {
		if (filter != null) {
			Session session = this.getSession();
			Criteria criteria = session.createCriteria(this.persistentClass);
			return (Integer) criteria.add(this.getCriterion(filter)).setProjection(Projections.rowCount()).uniqueResult();
		} else {
			return this.count();
		}
	}

	@Override
	public List<E> findAll() throws RuntimeException {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(this.persistentClass);
		List<E> listado = criteria.list();
		this.closeSession(session);
		return listado;
	}

	@Override
	public E findById(PK id) throws RuntimeException {
		Session session = this.getSession();
		E entity = (E) session.get(this.persistentClass, id);
		this.closeSession(session);
		return entity;
	}

	@Override
	public List<E> findByFilter(Filter filter) throws RuntimeException {
		if (filter != null) {
			Session session = this.getSession();
			Criteria criteria = session.createCriteria(this.persistentClass);
			List<E> listado = criteria.add(this.getCriterion(filter)).list();
			this.closeSession(session);
			return listado;
		} else {
			return this.findAll();
		}
	}

	@Override
	public void save(E entity) throws RuntimeException {
		Session session = this.getSession();
		session.save(entity);
		session.flush();
		this.closeSession(session);
	}

	@Override
	public void update(E entity) throws RuntimeException {
		Session session = this.getSession();
		session.update(entity);
		session.flush();
		this.closeSession(session);
	}

	@Override
	public void saveOrUpdate(E entity) throws RuntimeException {
		Session session = this.getSession();
		session.saveOrUpdate(entity);
		session.flush();
		this.closeSession(session);
	}

	@Override
	public void delete(E entity) throws RuntimeException {
		Session session = this.getSession();
		session.delete(entity);
		session.flush();
		this.closeSession(session);
	}

	@Override
	public void deleteById(PK id) throws RuntimeException {
		Session session = this.getSession();
		E entity = (E) session.load(this.persistentClass, id);
		session.delete(entity);
		session.flush();
		this.closeSession(session);
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
	 * @param filter
	 *            El filtro que vamos a utilizar para crear el Criterion.
	 * @return El Criterion que corresponde al filtro de búsqueda.
	 */
	protected Criterion getCriterion(Filter filter) {
		// Si es un filtro atómico.
		if (filter instanceof AtomicFilter) {
			AtomicFilter<Serializable> af = (AtomicFilter<Serializable>) filter;

			switch (af.getAtomicFilterType()) {

				case BETWEEN:
					// Si el filtro es de una operación "between"
					BetweenFilter<Serializable> br = (BetweenFilter<Serializable>) af;
					return Restrictions.between(br.getAttribute(), br.getLowValue(), br.getHighValue());

				case COMPARE:
					// Si el filtro es de una operación "=" o "<>" o "<" o "<=" o ">" o ">="
					CompareFilter<Serializable> cf = (CompareFilter<Serializable>) af;
					switch (cf.getCompareFilterType()) {
						case EQUALS:
							return Restrictions.eq(cf.getAttribute(), cf.getValue());
						case NOT_EQUALS:
							return Restrictions.ne(cf.getAttribute(), cf.getValue());
						case GREATER:
							return Restrictions.gt(cf.getAttribute(), cf.getValue());
						case GREATER_OR_EQUALS:
							return Restrictions.ge(cf.getAttribute(), cf.getValue());
						case LESSER:
							return Restrictions.lt(cf.getAttribute(), cf.getValue());
						case LESSER_OR_EQUALS:
							return Restrictions.le(cf.getAttribute(), cf.getValue());
					}

				case IN:
					// Si el filtro es de una operación "in"
					InFilter<Serializable> inf = (InFilter<Serializable>) af;
					return Restrictions.in(inf.getAttribute(), inf.getList());

				case LIKE:
					// Si el filtro es de una operación "like"
					LikeFilter lf = (LikeFilter) af;
					return Restrictions.like(lf.getAttribute(), lf.getLike());

				case NULL:
					// Si el filtro es de una operación "is Null"
					NullFilter nf = (NullFilter) af;
					return Restrictions.isNull(nf.getAttribute());
			}
		} else if (filter instanceof ComplexFilter) {

			// Si el filtro es uno complejo, la procesamos.
			ComplexFilter cf = (ComplexFilter) filter;
			switch (cf.getComplexType()) {

				case UNARY:
					// Si el filtro, es un filtro unario del tipo "not"
					UnaryComplexFilter ucr = (UnaryComplexFilter) cf;
					return Restrictions.not(this.getCriterion(ucr.getFilter()));

				case BINARY:
					// Si el filtro es un filtro binario.
					BinaryComplexFilter bcr = (BinaryComplexFilter) cf;
					switch (bcr.getBinaryType()) {

						case AND:
							// Si el filtro es del tipo "and"
							return Restrictions.and(this.getCriterion(bcr.getFirstFilter()), this.getCriterion(bcr.getSecondFilter()));

						case OR:
							// Si el filtro es del tipo "or"
							return Restrictions.or(this.getCriterion(bcr.getFirstFilter()), this.getCriterion(bcr.getSecondFilter()));
					}
			}
		}
		return null;
	}

	/**
	 * Función que permite crear o recuperar una sesión a partir de la session factory.
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
	 * @param session
	 *            La sesión abierta que queremos cerrar dentro del sistema.
	 */
	protected void closeSession(Session session) {
		// session.close();
	}
}
