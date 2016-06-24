package com.common.util.domain.model;

import com.common.util.domain.model.entity.impl.Entity;

/**
 * La extension para probar la clase {@link Entity}
 * 
 * @since 11/06/2015
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class EntityTest extends Entity<Long> {

	private static final long serialVersionUID = 1L;

	@Override
	public Long getId() {
		return id;
	}
}