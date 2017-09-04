package com.vantibolli.inventory.service;


import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.vantibolli.inventory.InventoryTestHelper;
import com.vantibolli.inventory.exception.InventoryException;
import com.vantibolli.inventory.model.Country;
import com.vantibolli.inventory.repository.interfaces.CountryRepository;
import com.vantibolli.inventory.service.impl.CountryServiceImpl;


public class CountryServiceTest extends AbstractServiceTest {
	
	@InjectMocks
	CountryServiceImpl countryService;
	
	@Mock
	CountryRepository countryRepository;
	
	@Test
	public void update() throws InventoryException {
		Country country = (Country) entityList.get(0);
		Country presisted = (Country) entityList.get(0);
		long id = 2;
		presisted.setId(id);
		when(countryRepository.getByKey(id)).thenReturn(country);
		when(countryService.get(id)).thenReturn(country);
		Country countryResult = countryService.update(id, country);
		Assert.assertNotNull(countryResult);
		Assert.assertEquals(countryResult.getCountryName(), presisted.getCountryName());
		Assert.assertEquals(countryResult.getId(), presisted.getId());
	}

	@Override
	public void setList() {
		entityList.add(InventoryTestHelper.getCountry("Pakistan"));
		entityList.add(InventoryTestHelper.getCountry("China"));
	}

}
