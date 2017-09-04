/*
 * Copyright (C) 2017 Van Tibolli, All Rights Reserved.
 */

package com.vantibolli.inventory.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vantibolli.inventory.InventoryHelper;
import com.vantibolli.inventory.dto.ItemQuantityDto;
import com.vantibolli.inventory.exception.ConfigurationException;
import com.vantibolli.inventory.exception.InventoryException;
import com.vantibolli.inventory.mapper.GeneralMapper;
import com.vantibolli.inventory.model.Item;
import com.vantibolli.inventory.model.ItemVariants;
import com.vantibolli.inventory.model.Product;
import com.vantibolli.inventory.model.Stock;
import com.vantibolli.inventory.service.ItemService;
import com.vantibolli.inventory.service.ItemVariantsService;
import com.vantibolli.inventory.service.ProductService;
import com.vantibolli.inventory.service.StockService;
/**
 * The Item Service.
 * @author  mukhtiar.ahmed
 * version 1.0
 */
@Service
public class ItemServiceImpl extends BaseService<Item> implements ItemService {

	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ItemVariantsService itemVariantsService;
	
	@Autowired
	private StockService stockService;
	
	/**
     * Check the configuration.
     *
     * @throws ConfigurationException if there's any error in configuration
     */
    @PostConstruct
    public void checkConfiguration() {  
    	InventoryHelper.checkConfigNotNull(stockService, "stockService");
    	InventoryHelper.checkConfigNotNull(productService, "productService");
    	InventoryHelper.checkConfigNotNull(itemVariantsService, "itemVariantsService");
    }
	
	
	@Override
	@Transactional
	public Item create(Item entity) throws InventoryException {
		InventoryHelper.checkNull(entity, "entity");
		Product product = entity.getProduct();
		ItemVariants itemVariants = entity.getItemVariants();
		// add validation.
		InventoryHelper.checkNull(product, "product");
		InventoryHelper.checkNull(itemVariants, "itemVariants");
		productService.get(product.getId());
		itemVariantsService.get(itemVariants.getId());		
		getRepository().persist(entity);
		return getRepository().getByKey(entity.getId());
	}
	
	@Override
	@Transactional
	public Item update(long id, Item entity) throws InventoryException {	
		InventoryHelper.checkNull(entity, "entity");
		Item oldEntity = get(id);
		Product product = entity.getProduct();
		ItemVariants itemVariants = entity.getItemVariants();
		// add validation.
		InventoryHelper.checkNull(product, "product");
		InventoryHelper.checkNull(itemVariants, "itemVariants");
		productService.get(product.getId());
		itemVariantsService.get(itemVariants.getId());		
		oldEntity.setProduct(product);
		oldEntity.setItemVariants(itemVariants);
		getRepository().persist(oldEntity);
		return getRepository().getByKey(id);
	}
	
	public ItemQuantityDto getItemQuantityByWarehouseId(long itemId, long warehouseId) throws InventoryException  {
		Stock stock = stockService.getByItemIdAndWarehouseId(itemId, warehouseId);
		return GeneralMapper.convertStockToItmDto(stock);
	}
	
	public List<ItemQuantityDto> getItemQuantityByCountryId(long itemId, long countryId) throws InventoryException  {
		List<Stock> stocks = stockService.getByItemIdAndCountryId(itemId, countryId);
		return  GeneralMapper.convertStockListToItmDtoList(stocks);
	}
	
	public List<ItemQuantityDto> getItemQuantityAllWarehouse(long itemId) throws InventoryException  {
		List<Stock> stocks = stockService.getByItemId(itemId);
		return GeneralMapper.convertStockListToItmDtoList(stocks);
	}

}
