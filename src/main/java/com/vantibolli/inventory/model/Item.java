/*
 * Copyright (C) 2017 Van Tibolli, All Rights Reserved.
 */
package com.vantibolli.inventory.model;

import static javax.persistence.FetchType.LAZY;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The persistent class for the Item database table.
 * 
 * @author  mukhtiar.ahmed
 * @version 1.0
 */
@Entity

@Table(name="item", 
uniqueConstraints=@UniqueConstraint(columnNames={"product_id", "item_variants_id"}))
public class Item extends BaseEntity  {
	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="item_variants_id")	
	private ItemVariants itemVariants;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="product_id")
	private Product product;	
	
	@JsonIgnore
	@OneToMany(mappedBy="item", fetch=LAZY, cascade = CascadeType.REMOVE)	
	private List<Stock> stocks;		

	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}
	
	/**
	 * @return the itemVariants
	 */
	public ItemVariants getItemVariants() {
		return itemVariants;
	}

	/**
	 * @param itemVariants the itemVariants to set
	 */
	public void setItemVariants(ItemVariants itemVariants) {
		this.itemVariants = itemVariants;
	}
	
	/**
	 * @return the stocks
	 */
	public List<Stock> getStocks() {
		return stocks;
	}

	/**
	 * @param stocks the stocks to set
	 */
	public void setStocks(List<Stock> stocks) {
		this.stocks = stocks;
	}

}