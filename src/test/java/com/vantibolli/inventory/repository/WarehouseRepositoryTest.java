package com.vantibolli.inventory.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.vantibolli.inventory.InventoryTestHelper;
import com.vantibolli.inventory.exception.InventoryException;
import com.vantibolli.inventory.model.Warehouse;
import com.vantibolli.inventory.repository.interfaces.WarehouseRepository;

public class WarehouseRepositoryTest extends AbstractRepositoryTest {
	
	@Autowired
	private WarehouseRepository repository; 
	
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
		long count = repository.count();	
		Warehouse entity = InventoryTestHelper.getWarehouse();		
		repository.persist(entity);
		Assert.assertNotEquals(entity.getId(), 0);
		long countAfterInsert = repository.count();
		Assert.assertEquals(countAfterInsert , count + 1);
	}
	
	@Test
	public void update() throws InventoryException {
		long id = InventoryTestHelper.WAREHOUSE_ID;
		Warehouse entity = repository.getByKey(id);
		entity.setName("Updated Warehouse");
		repository.persist(entity);
		Warehouse updated = repository.getByKey(id);
		Assert.assertEquals(entity.getName(), updated.getName());
		
	}

	
	@Test
	public void delete() throws InventoryException  {	
		long id = 2L;
		long count = repository.count();
		repository.delete(repository.getByKey(id));
		long countAfterDelete = repository.count();
		Assert.assertNotEquals(count, countAfterDelete);
		
	}
	
	@Test
	public void findAll() throws InventoryException {
		List<Warehouse> entities= repository.findAll();
		long count = repository.count();		
		Assert.assertEquals(entities.size(), count);
		Assert.assertNotNull(entities);
		
	}

}
