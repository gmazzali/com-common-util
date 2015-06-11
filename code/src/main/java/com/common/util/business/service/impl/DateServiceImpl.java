package com.common.util.business.service.impl;

import java.util.Date;

import com.common.util.business.service.DateService;

/**
 * Define la clase donde se van a poder obtener la fecha actual.
 * 
 * @since 11/11/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class DateServiceImpl implements DateService {

	private static final long serialVersionUID = 1L;

	@Override
	public Date getCurrentDate() {
		return new Date();
	}
}