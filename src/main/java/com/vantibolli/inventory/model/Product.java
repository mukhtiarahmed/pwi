/*
 * Copyright (C) 2017 Van Tibolli, All Rights Reserved.
 */
package com.vantibolli.inventory.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The persistent class for the Item database table.
 * 
 * @author  mukhtiar.ahmed
 * @version 1.0
 */
@Entity
@Table(name="product")
public class Product extends BaseEntity  {
	
	private static final long serialVersionUID = 1L;

	@NotEmpty
	@Column(name="name", unique=true, nullable=false)
	private String name;
	
	@Column(name="item_type", nullable=false) 
	@Enumerated(EnumType.ORDINAL)
	private ItemType itemType;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="brand_id")
	private Brand brand;
	
	@JsonIgnore
	@OneToMany(mappedBy="product", fetch=FetchType.LAZY, cascade = CascadeType.REMOVE)	
	private List<Item> items;
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the itemType
	 */
	public ItemType getItemType() {
		return itemType;
	}

	/**
	 * @param itemType the itemType to set
	 */
	public void setItemType(ItemType itemType) {
		this.itemType = itemType;
	}
	
	/**
	 * @return the brand
	 */
	public Brand getBrand() {
		return brand;
	}

	/**
	 * @param brand the brand to set
	 */
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	
	/**
	 * @return the items
	 */
	public List<Item> getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(List<Item> items) {
		this.items = items;
	}


}