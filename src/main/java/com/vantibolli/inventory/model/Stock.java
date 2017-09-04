/*
 * Copyright (C) 2017 Van Tibolli, All Rights Reserved.
 */
package com.vantibolli.inventory.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 * The persistent class for the stock database table.
 * 
 * @author  mukhtiar.ahmed
 * @version 1.0
 */
@Entity
@Table(name="stock",
uniqueConstraints=@UniqueConstraint(columnNames={"item_id", "warehouse_id"}))
@NamedQueries ({@NamedQuery(name = Stock.UPDATE_BY_ITEM_ID, query = Stock.QUERY_UPDATE_BY_ITEM_ID ),
	@NamedQuery(name = Stock.UPDATE_BY_ITEM_ID_COUNTRY_ID, query = Stock.QUERY_UPDATE_BY_ITEM_ID_COUNTRY_ID ),
	@NamedQuery(name = Stock.UPDATE_BY_ITEM_ID_WAREHOUSE_ID, query = Stock.QUERY_UPDATE_BY_ITEM_ID_WAREHOUSE_ID),})
public class Stock extends BaseEntity  {
	/**
	 * The serial version id.
	 */	
	private static final long serialVersionUID = 1L;
	
	public static final String UPDATE_BY_ITEM_ID = "updateByItemId";
	public static final String UPDATE_BY_ITEM_ID_COUNTRY_ID = "updateByItemIdAndCountryId";
	public static final String UPDATE_BY_ITEM_ID_WAREHOUSE_ID = "updateByItemIdAndWarehouseId";
	public static final String QUERY_UPDATE_BY_ITEM_ID = "update Stock set availableQuantity = :quantity where item.id = :itemId";
	public static final String QUERY_UPDATE_BY_ITEM_ID_WAREHOUSE_ID ="update Stock set availableQuantity = :quantity where item.id = :itemId  and warehouse.id = :warehouseId";
	public static final String QUERY_UPDATE_BY_ITEM_ID_COUNTRY_ID = "update Stock set availableQuantity = :quantity where item.id = :itemId "
				+ " and warehouse.id in (select id from Warehouse where  country.id = :countryId)";


	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="warehouse_id", nullable=false)
	private Warehouse warehouse;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="item_id", nullable=false)
	private Item item;

	@Column(name="in_stock")
	private int inStock;
	
	@Column(name="avl_qty")	
	private int availableQuantity;

	@Column(name="in_transit")	
	private int inTransit;
	
	@Column(name="moq")
	private int  minOrderquantity;

	
	@Column(name="qpb")
	private int  quantityPerBox ;

	
	@Column(name="reorder_point")
	private int  reorderPoint;


	/**
	 * @return the warehouse
	 */
	public Warehouse getWarehouse() {
		return warehouse;
	}

	/**
	 * @param warehouse the warehouse to set
	 */
	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	/**
	 * @return the item
	 */
	public Item getItem() {
		return item;
	}

	/**
	 * @param item the item to set
	 */
	public void setItem(Item item) {
		this.item = item;
	}
	
	/**
	 * @return the inStock
	 */
	public int getInStock() {
		return inStock;
	}

	/**
	 * @param inStock the inStock to set
	 */
	public void setInStock(int inStock) {
		this.inStock = inStock;
	}

	/**
	 * @return the availableQuantity
	 */
	public int getAvailableQuantity() {
		return availableQuantity;
	}

	/**
	 * @param availableQuantity the availableQuantity to set
	 */
	public void setAvailableQuantity(int availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	/**
	 * @return the inTransit
	 */
	public int getInTransit() {
		return inTransit;
	}

	/**
	 * @param inTransit the inTransit to set
	 */
	public void setInTransit(int inTransit) {
		this.inTransit = inTransit;
	}

	/**
	 * @return the minOrderquantity
	 */
	public int getMinOrderquantity() {
		return minOrderquantity;
	}

	/**
	 * @param minOrderquantity the minOrderquantity to set
	 */
	public void setMinOrderquantity(int minOrderquantity) {
		this.minOrderquantity = minOrderquantity;
	}

	/**
	 * @return the quantityPerBox
	 */
	public int getQuantityPerBox() {
		return quantityPerBox;
	}

	/**
	 * @param quantityPerBox the quantityPerBox to set
	 */
	public void setQuantityPerBox(int quantityPerBox) {
		this.quantityPerBox = quantityPerBox;
	}

	/**
	 * @return the reorderPoint
	 */
	public int getReorderPoint() {
		return reorderPoint;
	}

	/**
	 * @param reorderPoint the reorderPoint to set
	 */
	public void setReorderPoint(int reorderPoint) {
		this.reorderPoint = reorderPoint;
	}
	


}