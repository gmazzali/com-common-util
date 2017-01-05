package com.common.util.persistence.dao;

import java.io.Serializable;

import com.common.util.domain.model.entity.AuditablePersistence;
import com.common.util.domain.model.entity.Persistence;
import com.common.util.domain.model.entity.impl.Entity;

/**
 * The extension of the interface {@link BaseActiveDao} that managed auditable entities {@link AuditablePersistence}.
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
 *            The auditable entity of this DAO.
 * @param <PK>
 *            The primary key of the auditable entity of this DAO.
 */
public abstract interface BaseAuditableDao<E extends AuditablePersistence<PK>, PK extends Serializable> extends BaseActiveDao<E, PK> {
}