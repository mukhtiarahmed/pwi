package com.vantibolli.inventory.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.vantibolli.inventory.InventoryTestHelper;
import com.vantibolli.inventory.exception.InventoryException;
import com.vantibolli.inventory.model.Stock;
import com.vantibolli.inventory.repository.interfaces.StockRepository;

public class StockRepositoryTest extends AbstractRepositoryTest {
	
	@Autowired
	private StockRepository repository; 
	
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
		Stock entity = InventoryTestHelper.getStock();
		repository.persist(entity);
		Assert.assertNotEquals(entity.getId(), 0);
		long countAfterInsert = repository.count();
		Assert.assertEquals(countAfterInsert , count + 1);
	}
	
	@Test
	public void update() throws InventoryException {
		long id = InventoryTestHelper.STOCK_ID;
		Stock entity = repository.getByKey(id);	
		entity.setAvailableQuantity(150);
		entity.setInStock(160);
		entity.setInTransit(10);
		entity.setMinOrderquantity(7);
		entity.setQuantityPerBox(3);
		entity.setReorderPoint(25);
		repository.persist(entity);
		Stock updated = repository.getByKey(id);
		Assert.assertEquals(entity.getAvailableQuantity(), updated.getAvailableQuantity());
		Assert.assertEquals(entity.getInStock(), updated.getInStock());
		Assert.assertEquals(entity.getInTransit(), updated.getInTransit());
		Assert.assertEquals(entity.getMinOrderquantity(), updated.getMinOrderquantity());
		Assert.assertEquals(entity.getReorderPoint(), updated.getReorderPoint());
		
	}

	
	@Test
	public void delete() throws InventoryException  {	
		Stock entity = InventoryTestHelper.getStock();
		repository.persist(entity);
		long count = repository.count();
		repository.delete(entity);
		long countAfterDelete = repository.count();
		Assert.assertNotEquals(count, countAfterDelete);
		
	}
	
	@Test
	public void findAll() throws InventoryException {
		List<Stock> entities= repository.findAll();
		long count = repository.count();		
		Assert.assertEquals(entities.size(), count);
		Assert.assertNotNull(entities);
		
	}
	
	@Test
	public void findStockByItemIdAndWarehouseId() throws InventoryException  {
		long itemId = 1;
		long warehouseId = 1;
		Stock stock = repository.findStockByItemIdAndWarehouseId(itemId, warehouseId);
		Assert.assertNotNull(stock);
		Assert.assertEquals(stock.getItem().getId(), itemId);
		Assert.assertEquals(stock.getWarehouse().getId(), warehouseId);
		
	}
	
	@Test
	public void findStockByItemIdAndCountryId()  throws InventoryException {
		long itemId = 3;
		long countryId = 1;
		List<Stock> entities = repository.findStockByItemIdAndCountryId(itemId, countryId);
		Assert.assertNotNull(entities);
		for(Stock stock : entities) {
			Assert.assertEquals(stock.getItem().getId(), itemId);
			Assert.assertEquals(stock.getWarehouse().getCountry().getId(), countryId);
		}
		
	}
	
	@Test
	public void findStockByItemId()  throws InventoryException {
		long itemId = 3;	
		List<Stock> entities = repository.findStockByItemId(itemId);
		Assert.assertNotNull(entities);
		for(Stock stock : entities) {
			Assert.assertEquals(stock.getItem().getId(), itemId);			
		}
		
	}
	
	@Test
	public void updateByItemIdAndWarehouseId() throws InventoryException  {
		long itemId = 1;
		long warehouseId = 1;
		int quantity = 215;
		repository.updateByItemIdAndWarehouseId(itemId, warehouseId, quantity);
		Stock stock = repository.findStockByItemIdAndWarehouseId(itemId, warehouseId);
		Assert.assertEquals(stock.getItem().getId(), itemId);
		Assert.assertEquals(stock.getWarehouse().getId(), warehouseId);
		Assert.assertEquals(stock.getAvailableQuantity(), quantity);
		
	}
	
	@Test
	public void updateByItemIdAndCountryId()  throws InventoryException {
		long itemId = 2;
		long countryId = 1;
		int quantity = 250;
		repository.updateByItemIdAndCountryId(itemId, countryId, quantity);
		List<Stock> entities = repository.findStockByItemIdAndCountryId(itemId, countryId);
		Assert.assertNotNull(entities);
		for(Stock stock : entities) {
			Assert.assertEquals(stock.getItem().getId(), itemId);
			Assert.assertEquals(stock.getWarehouse().getCountry().getId(), countryId);
			Assert.assertEquals(stock.getAvailableQuantity(), quantity);
		}
		
	}
	
	@Test
	public void updateByItemId()  throws InventoryException {
		long itemId = 3;
		int quantity = 225;
		repository.updateByItemId(itemId, quantity);
		List<Stock> entities = repository.findStockByItemId(itemId);
		Assert.assertNotNull(entities);
		for(Stock stock : entities) {
			Assert.assertEquals(stock.getItem().getId(), itemId);	
			Assert.assertEquals(stock.getAvailableQuantity(), quantity);
		}
		
		
	}

}
