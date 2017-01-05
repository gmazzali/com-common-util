package com.common.util.persistence.dao;

import java.io.Serializable;

import com.common.util.domain.model.entity.ActivePersistence;
import com.common.util.domain.model.entity.Persistence;
import com.common.util.domain.model.entity.impl.Entity;

/**
 * The extension of the interface {@link BaseDao} that managed active entities {@link ActivePersistence}.
 * 
 * @see Entity
 * @see Persistence
 * @see Serializable
 * 
 * @since 25/11/2016
 * @author Guillermo S. Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            The active entity of this DAO.
 * @param <PK>
 *            The primary key of the active entity of this DAO.
 */
public abstract interface BaseActiveDao<E extends ActivePersistence<PK>, PK extends Serializable> extends BaseDao<E, PK> {
}