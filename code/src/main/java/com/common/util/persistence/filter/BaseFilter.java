package com.common.util.persistence.filter;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.tuple.Pair;

import com.common.util.domain.model.entity.Persistence;
import com.common.util.domain.model.util.RangeType;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Represent the base filter for the search of records in the database.
 * 
 * @since 16/11/2016
 * @author Guillermo S. Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            The entity of this filter.
 * @param <PK>
 *            The primary key of the entity of this filter.
 */
public class BaseFilter<E extends Persistence<PK>, PK extends Serializable> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * The comparators for the filter.
	 */
	public static enum Comparator {
		LESS, LESS_OR_EQUAL, EQUAL, NOT_EQUAL, GREATER_OR_EQUAL, GREATER, IN, BETWEEN, NULL, NOT_NULL;
	}

	/**
	 * The list of restrictions.
	 */
	private Map<String, List<Pair<Comparator, Object>>> restrictions = Maps.newHashMap();
	/**
	 * The list of orders.
	 */
	private List<Order> orders = Lists.newArrayList();
	/**
	 * The list of the excludes IDs.
	 */
	private List<PK> excludeIds = Lists.newArrayList();
	/**
	 * The position of the first element of the query.
	 */
	private Integer firstResult;
	/**
	 * The max size of the result.
	 */
	private Integer maxResult;

	/**
	 * Add a restriction "less" to this filter.
	 * 
	 * @param property
	 *            The property to this filter.
	 * @param value
	 *            The value to compare the property.
	 */
	public void less(String property, Object value) {
		this.addRestriction(property, Comparator.LESS, value);
	}

	/**
	 * Add a restriction "less or equal" to this filter.
	 * 
	 * @param property
	 *            The property to this filter.
	 * @param value
	 *            The value to compare the property.
	 */
	public void lessOrEquals(String property, Object value) {
		this.addRestriction(property, Comparator.LESS_OR_EQUAL, value);
	}

	/**
	 * Add a restriction "equals" to this filter.
	 * 
	 * @param property
	 *            The property to this filter.
	 * @param value
	 *            The value to compare the property.
	 */
	public void equals(String property, Object value) {
		this.addRestriction(property, Comparator.EQUAL, value);
	}

	/**
	 * Add a restriction "not equals" to this filter.
	 * 
	 * @param property
	 *            The property to this filter.
	 * @param value
	 *            The value to compare the property.
	 */
	public void notEquals(String property, Object value) {
		this.addRestriction(property, Comparator.NOT_EQUAL, value);
	}

	/**
	 * Add a restriction "great or equal" to this filter.
	 * 
	 * @param property
	 *            The property to this filter.
	 * @param value
	 *            The value to compare the property.
	 */
	public void greatOrEquals(String property, Object value) {
		this.addRestriction(property, Comparator.GREATER_OR_EQUAL, value);
	}

	/**
	 * Add a restriction "great" to this filter.
	 * 
	 * @param property
	 *            The property to this filter.
	 * @param value
	 *            The value to compare the property.
	 */
	public void great(String property, Object value) {
		this.addRestriction(property, Comparator.GREATER, value);
	}

	/**
	 * Add a restriction "in" to this filter.
	 * 
	 * @param property
	 *            The property to this filter.
	 * @param value
	 *            The list to compare the property.
	 */
	public void in(String property, Collection<?> value) {
		this.addRestriction(property, Comparator.IN, value);
	}

	/**
	 * Add a restriction "between" to this filter.
	 * 
	 * @param property
	 *            The property to this filter.
	 * @param value
	 *            The range to compare the property.
	 */
	public void between(String property, RangeType<?> value) {
		this.addRestriction(property, Comparator.BETWEEN, value);
	}

	/**
	 * Add a restriction "between" to this filter.
	 * 
	 * @param from
	 *            The value from.
	 * @param to
	 *            The value to.
	 * @param value
	 *            The range to compare the property.
	 */
	public void between(String property, Object from, Object to) {
		this.addRestriction(property, Comparator.BETWEEN, new RangeType<Object>(from, to));
	}

	/**
	 * Add a restriction "is null" to this filter.
	 * 
	 * @param property
	 *            The property to this filter.
	 */
	public void isNull(String property) {
		this.addRestriction(property, Comparator.NULL, null);
	}

	/**
	 * Add a restriction "is not null" to this filter.
	 * 
	 * @param property
	 *            The property to this filter.
	 */
	public void isNotNull(String property) {
		this.addRestriction(property, Comparator.NOT_NULL, null);
	}

	/**
	 * Add a restriction to this filter.
	 * 
	 * @param property
	 *            The property to filter.
	 * @param comparator
	 *            The comparator of this property.
	 * @param value
	 *            The value to compare.
	 */
	private void addRestriction(String property, Comparator comparator, Object value) {
		List<Pair<Comparator, Object>> restrictionForProperty = this.restrictions.get(property);
		if (restrictionForProperty == null) {
			restrictionForProperty = Lists.newArrayList();
			this.restrictions.put(property, restrictionForProperty);
		}
		restrictionForProperty.add(Pair.of(comparator, value));
	}

	/**
	 * Retrieve the set of restrictions of this filter.
	 * 
	 * @return The set of restrictions.
	 */
	public Map<String, List<Pair<Comparator, Object>>> getRestrictions() {
		return restrictions;
	}

	/**
	 * Retrieve the list of orders.
	 * 
	 * @return The list of orders.
	 */
	public List<Order> getOrders() {
		return orders;
	}

	/**
	 * Allow add some orders to the filter.
	 * 
	 * @param order
	 *            The new orders for the filter.
	 */
	public void addOrder(Order... newOrders) {
		if (ArrayUtils.isNotEmpty(newOrders)) {
			for (Order order : newOrders) {
				this.orders.add(order);
			}
		}
	}

	/**
	 * Retrieve the excludes IDs.
	 * 
	 * @return The excludes IDs.
	 */
	public List<PK> getExcludeIds() {
		return excludeIds;
	}

	/**
	 * Allow add some IDs to exclude to the filter.
	 * 
	 * @param order
	 *            The IDs to exclude to the filter.
	 */
	@SuppressWarnings("unchecked")
	public void addExcludeIds(PK... excludeIds) {
		if (ArrayUtils.isNotEmpty(excludeIds)) {
			for (PK pk : excludeIds) {
				this.excludeIds.add(pk);
			}
		}
	}

	/**
	 * Retrieve the position of the first element of the query.
	 * 
	 * @return The position of the first element of the query.
	 */
	public Integer getFirstResult() {
		return firstResult;
	}

	/**
	 * Set the position of the first element of the query.
	 * 
	 * @param firstResult
	 *            The position of the first element of the query.
	 */
	public void setFirstResult(Integer firstResult) {
		this.firstResult = firstResult;
	}

	/**
	 * Retrieve the max size of the result.
	 * 
	 * @return The max size of the result.
	 */
	public Integer getMaxResult() {
		return maxResult;
	}

	/**
	 * Set the position of the max size of the result.
	 * 
	 * @param maxResult
	 *            The max size of the result.
	 */
	public void setMaxResult(Integer maxResult) {
		this.maxResult = maxResult;
	}
}