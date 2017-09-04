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
 * The persistent class for the Item database table.
 * 
 * @author  mukhtiar.ahmed
 * @version 1.0
 */
@Entity
@Table(name="brand")
public class Brand extends BaseEntity  {
	/**
	 * The serial version id.
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@OneToMany(mappedBy="brand", fetch=FetchType.LAZY, cascade = CascadeType.REMOVE)	
	private List<Product> products;

	@NotEmpty
	@Column(name="name",unique=true)
	private String name;


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
	 * @return the products
	 */
	public List<Product> getProducts() {
		return products;
	}

	/**
	 * @param products the products to set
	 */
	public void setProducts(List<Product> products) {
		this.products = products;
	}
}