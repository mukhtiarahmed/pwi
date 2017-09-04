/*
 * Copyright (C) 2017 Van Tibolli, All Rights Reserved.
 */

package com.vantibolli.inventory.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.vantibolli.inventory.exception.EntityNotFoundException;
import com.vantibolli.inventory.exception.InventoryException;
import com.vantibolli.inventory.model.Stock;
import com.vantibolli.inventory.repository.interfaces.StockRepository;
/**
 * The Stock repository. 
 * @author  mukhtiar.ahmed
 * @version 1.0
 */
@Repository
public class StockRepositoryImpl extends AbstractRepository<Stock> implements StockRepository {
	
	

	@Override
	public Stock findStockByItemIdAndWarehouseId(long itemId, long warehouseId)  throws InventoryException  {
		Criteria criteria = createEntityCriteria();	
		criteria.add(Restrictions.eq( "item.id", itemId));
		criteria.add(Restrictions.eq( "warehouse.id", warehouseId));
		Stock stock =  (Stock) criteria.uniqueResult();
		if (stock == null) {
			throw new EntityNotFoundException("stock of itemId=" + itemId + " can not be found.");
		}
		return stock;
	}

	
	@Override
	@SuppressWarnings("unchecked")
	public List<Stock> findStockByItemIdAndCountryId(long itemId, long countryId) throws InventoryException  {
		Criteria criteria = createEntityCriteria();
		criteria.createAlias("warehouse", "wh");	
		criteria.add(Restrictions.eq( "item.id", itemId));
		criteria.add(Restrictions.eq( "wh.country.id", countryId));
		return criteria.list();
	}


	@Override
	@SuppressWarnings("unchecked")
	public List<Stock> findStockByItemId(long itemId) throws InventoryException  {
		Criteria criteria = createEntityCriteria();	
		criteria.add(Restrictions.eq( "item.id", itemId));
		return criteria.list();
	}


	@Override
	public void updateByItemIdAndWarehouseId(long itemId, long warehouseId, int quantity) throws InventoryException {
		Query query = getNamedQuery(Stock.UPDATE_BY_ITEM_ID_WAREHOUSE_ID);
		query.setParameter("itemId", itemId);
		query.setParameter("warehouseId", warehouseId);
		query.setParameter("quantity", quantity);
		query.executeUpdate();		
	}


	@Override
	public void updateByItemIdAndCountryId(long itemId, long countryId, int quantity) throws InventoryException {
		Query query = getNamedQuery(Stock.UPDATE_BY_ITEM_ID_COUNTRY_ID);
		query.setParameter("itemId", itemId);
		query.setParameter("countryId", countryId);
		query.setParameter("quantity", quantity);
		query.executeUpdate();				
	}


	@Override
	public void updateByItemId(long itemId, int quantity) throws InventoryException {
		Query query = getNamedQuery(Stock.UPDATE_BY_ITEM_ID);
		query.setParameter("itemId", itemId);
		query.setParameter("quantity", quantity);
		query.executeUpdate();			
	}

}
