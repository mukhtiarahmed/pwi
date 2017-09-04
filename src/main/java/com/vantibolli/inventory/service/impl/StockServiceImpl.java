package com.vantibolli.inventory.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vantibolli.inventory.InventoryHelper;
import com.vantibolli.inventory.exception.InventoryException;
import com.vantibolli.inventory.model.Stock;
import com.vantibolli.inventory.repository.interfaces.StockRepository;
import com.vantibolli.inventory.service.ItemService;
import com.vantibolli.inventory.service.StockService;
import com.vantibolli.inventory.service.WarehouseService;
/**
 * The Stock Service.
 * @author mukhtiar.ahmed
 * version 1.0
 */
@Service
public class StockServiceImpl extends BaseService<Stock> implements StockService {

	@Autowired
	private ItemService itemService;
	
	@Autowired
	private WarehouseService warehouseService;
	
	/*
	 * (non-Javadoc)
	 * @see com.vantibolli.inventory.service.impl.BaseService#create(com.vantibolli.inventory.model.BaseEntity)
	 */
	@Override
	@Transactional
	public Stock create(Stock entity) throws InventoryException {	
		validateStock(entity);
		getRepository().persist(entity);
		return getRepository().getByKey(entity.getId());
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.vantibolli.inventory.service.GenericService#update(long, com.vantibolli.inventory.model.BaseEntity)
	 */
	@Override
	@Transactional
	public Stock update(long id, Stock entity) throws InventoryException {	
		validateStock(entity);
		Stock oldEntity = get(id);		
		oldEntity.setItem(entity.getItem());
		oldEntity.setWarehouse(entity.getWarehouse());
		oldEntity.setAvailableQuantity(entity.getAvailableQuantity());
		oldEntity.setInStock(entity.getInStock());
		oldEntity.setInTransit(entity.getInTransit());
		oldEntity.setMinOrderquantity(entity.getMinOrderquantity());
		oldEntity.setQuantityPerBox(entity.getQuantityPerBox());
		oldEntity.setReorderPoint(entity.getReorderPoint());
		getRepository().persist(oldEntity);
		return getRepository().getByKey(id);
	}
	
	@Override
	public Stock getByItemIdAndWarehouseId(long itemId, long warehouseId) throws InventoryException {
		InventoryHelper.checkPositive(itemId, "itemId");
		InventoryHelper.checkPositive(warehouseId, "warehouseId");
		return ((StockRepository)getRepository())
				.findStockByItemIdAndWarehouseId(itemId, warehouseId);
	}
	
	@Override
	public List<Stock> getByItemIdAndCountryId(long itemId, long countryId) throws InventoryException  {
		InventoryHelper.checkPositive(itemId, "itemId");
		InventoryHelper.checkPositive(countryId, "countryId");
		return ((StockRepository)getRepository())
				.findStockByItemIdAndCountryId(itemId, countryId);
	}
	
	@Override
	public List<Stock> getByItemId(long itemId) throws InventoryException  {
		InventoryHelper.checkPositive(itemId, "itemId");		
		return ((StockRepository)getRepository())
				.findStockByItemId(itemId);
	}
	
	@Override
	@Transactional
	public void updateByItemIdAndWarehouseId(long itemId, long warehouseId, int quantity) throws InventoryException {
		InventoryHelper.checkPositive(itemId, "itemId");
		InventoryHelper.checkPositive(warehouseId, "warehouseId");
		InventoryHelper.checkPositive(quantity, "quantity");
		((StockRepository)getRepository())
				.updateByItemIdAndWarehouseId(itemId, warehouseId, quantity);
	}
	
	@Override
	@Transactional
	public void updateByItemIdAndCountryId(long itemId, long countryId, int quantity) throws InventoryException {
		InventoryHelper.checkPositive(itemId, "itemId");
		InventoryHelper.checkPositive(countryId, "countryId");
		InventoryHelper.checkPositive(quantity, "quantity");		
		((StockRepository)getRepository()).updateByItemIdAndCountryId(itemId, countryId, quantity);
	}

	@Override
	@Transactional
	public void updateByItemId(long itemId, int quantity) throws InventoryException {
		InventoryHelper.checkPositive(itemId, "itemId");		
		InventoryHelper.checkPositive(quantity, "quantity");
		((StockRepository)getRepository()).updateByItemId(itemId, quantity);
	}
	
	/**
	 * Validate Stock entity.
	 * @param entity the Stock entity.
	 * @throws InventoryException
	 */
	private void validateStock(Stock entity) throws InventoryException {
		InventoryHelper.checkNull(entity, "entity");
		InventoryHelper.checkNull(entity.getItem(), "item");
		InventoryHelper.checkNull(entity.getWarehouse(), "warehouse");	
		warehouseService.get(entity.getWarehouse().getId());
		itemService.get(entity.getItem().getId());
		InventoryHelper.checkPositive(entity.getMinOrderquantity(), "minOrderquantity");
		InventoryHelper.checkPositive(entity.getQuantityPerBox(), "quantityPerBox");
		InventoryHelper.checkPositive(entity.getReorderPoint(), "reorderPoint");
	}

	

}
