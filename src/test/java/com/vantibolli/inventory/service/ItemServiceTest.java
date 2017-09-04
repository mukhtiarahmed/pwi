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
import com.vantibolli.inventory.dto.ItemQuantityDto;
import com.vantibolli.inventory.exception.InventoryException;
import com.vantibolli.inventory.model.Item;
import com.vantibolli.inventory.model.ItemType;
import com.vantibolli.inventory.model.Stock;
import com.vantibolli.inventory.repository.interfaces.ItemRepository;
import com.vantibolli.inventory.service.impl.ItemServiceImpl;

public class ItemServiceTest extends AbstractServiceTest{
	
	@InjectMocks
	ItemServiceImpl itemService;
	
	@Mock
	ItemRepository itemRepository;
	
	@Mock
	ProductService productService;
	
	@Mock
	ItemVariantsService itemVariantService;
	
	@Mock
	StockService stockService;

	@Test
	public void create() throws InventoryException {
		Item item = (Item) entityList.get(0);
		Item persisted = (Item) entityList.get(0);	
		long id = 2;
		persisted.setId(id);
		when(productService.get(anyLong())).thenReturn(persisted.getProduct());
		when(itemVariantService.get(anyLong())).thenReturn(persisted.getItemVariants());
		doNothing().when(itemRepository).persist(persisted);
		when(itemRepository.getByKey(anyLong())).thenReturn(persisted);			
		Item resultItem = itemService.create(item);
		Assert.assertNotNull(resultItem);
		Assert.assertEquals(persisted.getId(), resultItem.getId());
		verify(itemRepository, atLeastOnce()).persist(item);		
	}
	
	@Test
	public void update() throws InventoryException {
		Item item = (Item) entityList.get(0);
		Item persisted = (Item) entityList.get(0);	
		long id = 2;
		persisted.setId(id);
		when(productService.get(2)).thenReturn(persisted.getProduct());
		when(itemVariantService.get(2)).thenReturn(persisted.getItemVariants());
		when(itemRepository.getByKey(2L)).thenReturn(persisted);
		Item resultItem = itemService.update(2, item);
		Assert.assertNotNull(resultItem);
		Assert.assertEquals(resultItem.getId(), persisted.getId());
	}
	
	@Test
	public void getItemQuantityByWarehouseId() throws InventoryException {
		when(stockService.getByItemIdAndWarehouseId(2,3)).thenReturn(InventoryTestHelper.getDefaultStock());
		ItemQuantityDto itemQuantityDto = itemService.getItemQuantityByWarehouseId(2, 3);
		Assert.assertNotNull(itemQuantityDto);
	}
	
	@Test
	public void getItemQuantityByCountryId() throws InventoryException {
		List<Stock> stockList = new ArrayList<>();
		stockList.add(InventoryTestHelper.getDefaultStock());
		stockList.add(InventoryTestHelper.getDefaultStock());
		when(stockService.getByItemIdAndCountryId(2,3)).thenReturn(stockList);
		List<ItemQuantityDto> itemQuantityDtoList = itemService.getItemQuantityByCountryId(2, 3);
		Assert.assertNotNull(itemQuantityDtoList);
		Assert.assertNotEquals(itemQuantityDtoList.size(), 0);
	}
	
	@Test
	public void getItemQuantityAllWarehouse() throws InventoryException {
		List<Stock> stockList = new ArrayList<>();
		stockList.add(InventoryTestHelper.getDefaultStock());
		stockList.add(InventoryTestHelper.getDefaultStock());
		when(stockService.getByItemId(2)).thenReturn(stockList);
		List<ItemQuantityDto> itemQuantityDtoList = itemService.getItemQuantityAllWarehouse(2);
		Assert.assertNotNull(itemQuantityDtoList);
		Assert.assertNotEquals(itemQuantityDtoList.size(), 0);
	}
	
	
	@Override
	public void setList() {
		entityList.add(InventoryTestHelper.getItem("Item's Product 1", "Item's brand 1", ItemType.COMPONENT, "Size : 123"));
		entityList.add(InventoryTestHelper.getItem("Item's Product 2", "Item's brand 2", ItemType.PACKAGING_MATERIAL, "Size : 456"));
	}

}
