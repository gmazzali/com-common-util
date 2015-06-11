package com.common.util.business.service.impl;

import com.common.util.domain.exception.ServiceException;
import com.common.util.domain.model.EntityTest;

/**
 * La clase para probar {@link BaseServiceImpl}
 * 
 * @since 11/06/2015
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class ServiceTest extends BaseServiceImpl<EntityTest, Long> {

	private static final long serialVersionUID = 1L;

	@Override
	public void validate(EntityTest entity) throws ServiceException {
	}
}