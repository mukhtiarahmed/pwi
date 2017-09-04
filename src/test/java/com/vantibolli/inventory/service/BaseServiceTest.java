package com.vantibolli.inventory.service;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.vantibolli.inventory.InventoryTestHelper;
import com.vantibolli.inventory.exception.InventoryException;
import com.vantibolli.inventory.model.BaseEntity;
import com.vantibolli.inventory.repository.interfaces.GenericRepository;
import com.vantibolli.inventory.service.impl.BaseService;

public class BaseServiceTest extends BaseService<BaseEntity> {
	
	@InjectMocks
	BaseService<BaseEntity> baseService = this;
	
	@Mock
	GenericRepository<BaseEntity> genericRepository;
	
	@Spy
	List<BaseEntity> entityList = new ArrayList<>();
	
	@BeforeClass
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		entityList = new ArrayList<>();
		setList();
	}
	
	@Test
	public void get() throws InventoryException {
		BaseEntity country = entityList.get(0);
		BaseEntity brand = entityList.get(1);
		country.setId(2);
		brand.setId(3);
		when(genericRepository.getByKey(2)).thenReturn(country);
		BaseEntity resultCountry = baseService.get(2);
		when(genericRepository.getByKey(3)).thenReturn(brand);	
		BaseEntity resultBrand = baseService.get(3);
		Assert.assertNotNull(resultCountry);
		Assert.assertNotNull(resultBrand);
		Assert.assertEquals(country.getId(), resultCountry.getId());
		Assert.assertEquals(brand.getId(), resultBrand.getId());
	}
	@Test
	public void create() throws InventoryException {
		BaseEntity country = entityList.get(0);
		BaseEntity brand = entityList.get(1);
		BaseEntity persistedCountry = entityList.get(0);
		BaseEntity persistedBrand = entityList.get(1);
		persistedCountry.setId(2);
		persistedBrand.setId(3);
		doNothing().when(genericRepository).persist(persistedCountry);
		doNothing().when(genericRepository).persist(persistedBrand);
		when(genericRepository.getByKey(anyLong())).thenReturn(persistedCountry);
		BaseEntity resultCountry = baseService.create(country);
		when(genericRepository.getByKey(anyLong())).thenReturn(persistedBrand);
		BaseEntity resultBrand = baseService.create(brand);
		Assert.assertNotNull(resultCountry);
		Assert.assertNotNull(resultBrand);
		Assert.assertEquals(persistedCountry.getId(), resultCountry.getId());
		Assert.assertEquals(persistedBrand.getId(), resultBrand.getId());
		verify(genericRepository, atLeastOnce()).persist(country);
		verify(genericRepository, atLeastOnce()).persist(brand);
	}
	@Test
	public void delete() throws InventoryException {
		BaseEntity country = entityList.get(0);
		BaseEntity brand = entityList.get(1);
		when(baseService.get(2)).thenReturn(country);
		when(baseService.get(3)).thenReturn(brand);
		verify(genericRepository, atLeastOnce()).persist(country);
		verify(genericRepository, atLeastOnce()).persist(brand);
	}
		
	public void setList() {
		entityList.add(InventoryTestHelper.getCountry("Pakistan"));
		entityList.add(InventoryTestHelper.getBrand("Brand B"));
	}
	
	@Override
	public BaseEntity update(long id, BaseEntity entity) throws InventoryException {
		return null;
	}

	

}
