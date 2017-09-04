/*
 * Copyright (C) 2017 Van Tibolli, All Rights Reserved.
 */
package com.vantibolli.inventory.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The persistent class for the item_variants database table.
 * 
 * @author  mukhtiar.ahmed
 * @version 1.0
 */
@Entity
@Table(name="item_variants")
public class ItemVariants extends BaseEntity {

	/**
	 * The serial version id.
	 */
	private static final long serialVersionUID = 1L;

	@NotEmpty
	@Column(name="size", unique=true, nullable=false)
	private String size;
	
	@JsonIgnore
	@OneToMany(mappedBy="itemVariants", fetch=FetchType.LAZY, cascade = CascadeType.REMOVE)	
	private List<Item> items;

	public String getSize(){
		return this.size;
	}

	public void setSize(String size) {
		this.size = size;
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
