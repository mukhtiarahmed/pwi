package com.vantibolli.inventory.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.vantibolli.inventory.InventoryTestHelper;
import com.vantibolli.inventory.exception.InventoryException;
import com.vantibolli.inventory.model.Country;
import com.vantibolli.inventory.repository.interfaces.CountryRepository;

public class CountryRepositoryTest extends AbstractRepositoryTest {
	
	
	@Autowired
	private CountryRepository repository; 
	
	@Test
	public void getByKey() throws InventoryException {
		Assert.assertNotNull(repository.getByKey(1L));		
	}
	
	@Test(expectedExceptions = InventoryException.class)
	public void getByKeyFailure() throws InventoryException {
		Assert.assertNotNull(repository.getByKey(1211L));		
	}

	@Test
	public void insert() throws InventoryException  {
		Country entity = InventoryTestHelper.getCountry("Pakistan");
		long count = repository.count();	
		repository.persist(entity);
		Assert.assertNotEquals(entity.getId(), 0);
		long countAfterInsert = repository.count();
		Assert.assertEquals(countAfterInsert , count + 1);
	}
	
	@Test
	public void update() throws InventoryException {
		long id = InventoryTestHelper.COUNTRY_ID;
		Country entity = repository.getByKey(id);
		entity.setCountryName("China");
		repository.persist(entity);
		Country updated = repository.getByKey(id);
		Assert.assertEquals(entity.getCountryName(), updated.getCountryName());
		
	}

	
	@Test
	public void delete() throws InventoryException  {	
		Country entity = InventoryTestHelper.getCountry("Pakistan");
		repository.persist(entity);		
		long count = repository.count();			
		repository.delete(entity);
		long countAfterDelete = repository.count();
		Assert.assertNotEquals(count, countAfterDelete);
		
	}
	
	@Test
	public void findAll() throws InventoryException {
		List<Country> countries= repository.findAll();
		long count = repository.count();		
		Assert.assertEquals(countries.size(), count);
		Assert.assertNotNull(countries);
		
	}

}
