package com.common.util.business.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.common.util.domain.model.EntityTest;
import com.common.util.persistence.dao.impl.DaoTest;
import com.common.util.persistence.filter.FilterTest;
import com.common.util.persistence.filter.order.Orders;
import com.google.common.collect.Lists;

/**
 * El test de {@link BaseServiceImpl}
 * 
 * @since 11/06/2015
 * @author Guillermo Mazzali
 * @version 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class BaseServiceImplTest {

	@Mock
	private DaoTest dao;

	@InjectMocks
	private ServiceTest serviceTest;

	@Test
	public void testCount() throws Exception {
		Long cantidad = 12L;

		when(this.dao.count()).thenReturn(cantidad);

		Long retorno = this.serviceTest.count();
		assertNotNull(retorno);
		assertEquals(cantidad, retorno);

		verify(this.dao, times(1)).count();
	}

	@Test
	public void testCountByFilter() throws Exception {
		Long cantidad = 12L;

		FilterTest filterTest = mock(FilterTest.class);

		when(this.dao.countByFilter(filterTest)).thenReturn(cantidad);

		Long retorno = this.serviceTest.countByFilter(filterTest);
		assertNotNull(retorno);
		assertEquals(cantidad, retorno);

		verify(this.dao, times(1)).countByFilter(filterTest);
	}

	@Test
	public void testGetAll() throws Exception {
		List<EntityTest> entidades = Lists.newArrayList();

		when(this.dao.getAll()).thenReturn(entidades);

		List<EntityTest> retorno = this.serviceTest.getAll();
		assertNotNull(retorno);
		assertEquals(entidades, retorno);

		verify(this.dao, times(1)).getAll();
	}

	@Test
	public void testGetAllOrders() throws Exception {
		Orders orders = mock(Orders.class);

		List<EntityTest> entidades = Lists.newArrayList();

		when(this.dao.getAll(orders)).thenReturn(entidades);

		List<EntityTest> retorno = this.serviceTest.getAll(orders);
		assertNotNull(retorno);
		assertEquals(entidades, retorno);

		verify(this.dao, times(1)).getAll(orders);
	}

	@Test
	public void testGetByFilter() throws Exception {
		FilterTest filterTest = mock(FilterTest.class);

		List<EntityTest> entidades = Lists.newArrayList();

		when(this.dao.getByFilter(filterTest)).thenReturn(entidades);

		List<EntityTest> retorno = this.serviceTest.getByFilter(filterTest);
		assertNotNull(retorno);
		assertEquals(entidades, retorno);

		verify(this.dao, times(1)).getByFilter(filterTest);
	}

	@Test
	public void testGetById() throws Exception {
		Long idEntity = 123L;

		EntityTest entityTest = mock(EntityTest.class);

		when(this.dao.getById(idEntity)).thenReturn(entityTest);

		EntityTest retorno = this.serviceTest.getById(idEntity);
		assertNotNull(retorno);
		assertEquals(entityTest, retorno);

		verify(this.dao, times(1)).getById(idEntity);
	}

	@Test
	public void testSave() throws Exception {
		Long idEntity = 123L;

		EntityTest entityTest = mock(EntityTest.class);

		when(this.dao.save(entityTest)).thenReturn(idEntity);

		Long retorno = this.serviceTest.save(entityTest);
		assertNotNull(retorno);
		assertEquals(idEntity, retorno);

		verify(this.dao, times(1)).save(entityTest);
	}

	@Test
	public void testSaveOrUpdate() throws Exception {
		EntityTest entityTest = mock(EntityTest.class);

		this.serviceTest.saveOrUpdate(entityTest);

		verify(this.dao, times(1)).saveOrUpdate(entityTest);
	}

	@Test
	public void testUpdate() throws Exception {
		EntityTest entityTest = mock(EntityTest.class);

		this.serviceTest.update(entityTest);

		verify(this.dao, times(1)).update(entityTest);
	}

	@Test
	public void testDelete() throws Exception {
		EntityTest entityTest = mock(EntityTest.class);

		this.serviceTest.delete(entityTest);

		verify(this.dao, times(1)).delete(entityTest);
	}

	@Test
	public void testDeleteById() throws Exception {
		Long idEntity = 123L;

		this.serviceTest.deleteById(idEntity);

		verify(this.dao, times(1)).deleteById(idEntity);
	}
}