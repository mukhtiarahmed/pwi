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
import org.testng.Assert;
import org.testng.annotations.Test;

import com.vantibolli.inventory.InventoryTestHelper;
import com.vantibolli.inventory.exception.InventoryException;
import com.vantibolli.inventory.model.Stock;
import com.vantibolli.inventory.repository.interfaces.StockRepository;
import com.vantibolli.inventory.service.impl.StockServiceImpl;

public class StockServiceTest extends AbstractServiceTest {
	
	@InjectMocks
	StockServiceImpl stockService;
	
	@Mock
	StockRepository stockRepository;
	
	@Mock
	ItemService itemService;
	
	@Mock
	WarehouseService warehouseService;
	
	@Test
	public void create() throws InventoryException {
		Stock stock = InventoryTestHelper.getDefaultStock();
		Stock persisted = InventoryTestHelper.getDefaultStock();
		long id = 2;
		persisted.setId(id);
		when(stockRepository.getByKey(anyLong())).thenReturn(persisted);		
		when(itemService.get(anyLong())).thenReturn(persisted.getItem());
		when(warehouseService.get(anyLong())).thenReturn(persisted.getWarehouse());		
		doNothing().when(stockRepository).persist(persisted);
		Stock resultStock = stockService.create(stock);
		verify(stockRepository, atLeastOnce()).persist(stock);		
		Assert.assertNotNull(resultStock);
		Assert.assertEquals(resultStock, persisted);
		Assert.assertEquals(resultStock.getAvailableQuantity(), persisted.getAvailableQuantity());
		Assert.assertEquals(resultStock.getQuantityPerBox(), persisted.getQuantityPerBox());
		Assert.assertEquals(resultStock.getReorderPoint(), persisted.getReorderPoint());
		Assert.assertEquals(resultStock.getInTransit(), persisted.getInTransit());
		Assert.assertEquals(resultStock.getInStock(), persisted.getInStock());
		Assert.assertEquals(resultStock.getMinOrderquantity(), persisted.getMinOrderquantity());
	}
	
	@Test
	public void update() throws InventoryException {		
		Stock stock = InventoryTestHelper.getDefaultStock();
		Stock persisted = InventoryTestHelper.getDefaultStock();
		long id = 2;
		persisted.setId(id);
		when(stockRepository.getByKey(id)).thenReturn(persisted);		
		when(itemService.get(anyLong())).thenReturn(persisted.getItem());
		when(warehouseService.get(anyLong())).thenReturn(persisted.getWarehouse());		
		doNothing().when(stockRepository).persist(persisted);
		Stock resultStock = stockService.update(id,stock);
		Assert.assertNotNull(resultStock);
		Assert.assertEquals(resultStock, persisted);
		Assert.assertEquals(resultStock.getId(), persisted.getId());		
		Assert.assertEquals(resultStock.getAvailableQuantity(), persisted.getAvailableQuantity());
		Assert.assertEquals(resultStock.getQuantityPerBox(), persisted.getQuantityPerBox());
		Assert.assertEquals(resultStock.getReorderPoint(), persisted.getReorderPoint());
		Assert.assertEquals(resultStock.getInTransit(), persisted.getInTransit());
		Assert.assertEquals(resultStock.getInStock(), persisted.getInStock());
		Assert.assertEquals(resultStock.getMinOrderquantity(), persisted.getMinOrderquantity());
		Assert.assertEquals(resultStock.getItem().getId(), stock.getItem().getId());
		Assert.assertEquals(resultStock.getWarehouse().getId(), stock.getWarehouse().getId());
		
	}
	
	@Test
	public void getByItemIdAndWarehouseId() throws InventoryException {
		Stock stock = (Stock) entityList.get(0);
		long itemId = 11;
		long warehouseId = 1;
		long id = 2;
		stock.setId(id);
		stock.getItem().setId(itemId);
		stock.getWarehouse().setId(warehouseId);
		when(stockRepository.findStockByItemIdAndWarehouseId(itemId, warehouseId)).thenReturn(stock);
		Stock resultStock = stockService.getByItemIdAndWarehouseId(itemId, warehouseId);
		Assert.assertNotNull(resultStock);
		Assert.assertEquals(resultStock.getId(), stock.getId());
		Assert.assertEquals(resultStock.getItem().getId(), stock.getItem().getId());
		Assert.assertEquals(resultStock.getWarehouse().getId(), stock.getWarehouse().getId());
		Assert.assertEquals(resultStock.getAvailableQuantity(), stock.getAvailableQuantity());
		Assert.assertEquals(resultStock.getQuantityPerBox(), stock.getQuantityPerBox());
		Assert.assertEquals(resultStock.getReorderPoint(), stock.getReorderPoint());
		Assert.assertEquals(resultStock.getInTransit(), stock.getInTransit());
		Assert.assertEquals(resultStock.getInStock(), stock.getInStock());
		Assert.assertEquals(resultStock.getMinOrderquantity(), stock.getMinOrderquantity());
		
	}
	
	@Test
	public void getByItemIdAndCountryId() throws InventoryException {
		List<Stock> stock = new ArrayList<>();
		stock.add((Stock) entityList.get(0));
		stock.add((Stock) entityList.get(1));
		when(stockRepository.findStockByItemIdAndCountryId(2,3)).thenReturn(stock);
		List<Stock> resultStock = stockService.getByItemIdAndCountryId(2,3);
		Assert.assertNotNull(resultStock);
		Assert.assertEquals(2, resultStock.size());
	}
	
	@Test
	public void getByItemId() throws InventoryException {
		List<Stock> stock = new ArrayList<>();
		stock.add((Stock) entityList.get(0));
		stock.add((Stock) entityList.get(1));
		when(stockRepository.findStockByItemId(2)).thenReturn(stock);
		List<Stock> resultStock = stockService.getByItemId(2);
		Assert.assertNotNull(resultStock);
		Assert.assertEquals(resultStock, stock);
	
	}
	
	@Test
	public void updateByItemIdAndWarehouseId() throws InventoryException {		
		long itemId = 11;
		long warehouseId = 1;
		int quantity = 26;
		doNothing().when(stockRepository).updateByItemIdAndWarehouseId(itemId, warehouseId, quantity);
    	stockService.updateByItemIdAndWarehouseId(itemId, warehouseId, quantity);
    	verify(stockRepository).updateByItemIdAndWarehouseId(itemId, warehouseId, quantity);
	
	}	
	
	@Test
	public void updateByItemIdAndCountryId() throws InventoryException {
		long itemId = 2;
		long countryId = 1;
		int quantity = 20;
		doNothing().when(stockRepository).updateByItemIdAndCountryId(itemId, countryId, quantity);
		stockService.updateByItemIdAndCountryId(itemId, countryId, quantity);
		verify(stockRepository).updateByItemIdAndCountryId(itemId, countryId, quantity);
	}
	
	public void updateByItemId() throws InventoryException {
		long itemId = 2;
		int quantity = 24;
		doNothing().when(stockRepository).updateByItemId(itemId, quantity);
		stockService.updateByItemId(2,25);
		verify(stockRepository).updateByItemId(itemId, quantity);
	}
	
	

	@Override
	public void setList() {
		entityList.add(InventoryTestHelper.getDefaultStock());
		entityList.add(InventoryTestHelper.getDefaultStock());
	}

}
