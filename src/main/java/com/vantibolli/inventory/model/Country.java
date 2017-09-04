/*
 * Copyright (C) 2017 Van Tibolli, All Rights Reserved.
 */
package com.vantibolli.inventory.model;

import static javax.persistence.FetchType.LAZY;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The persistent class for the country database table.
 * 
 * @author  mukhtiar.ahmed
 * @version 1.0
 */
@Entity
@Table(name="country")
public class Country extends BaseEntity  {
	
	private static final long serialVersionUID = 1L;

	
	@NotEmpty
	@Column(name="country_name", unique=true)
	private String countryName;
	
	@JsonIgnore
	@OneToMany(mappedBy="country", fetch=LAZY, cascade = CascadeType.REMOVE)	
	private List<Warehouse> warehouses;	

	
	/**
	 * @return the countryName
	 */
	public String getCountryName() {
		return countryName;
	}

	/**
	 * @param countryName the countryName to set
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	/**
	 * @return the warehouses
	 */
	public List<Warehouse> getWarehouse() {
		return warehouses;
	}

	/**
	 * @param warehouses the warehouses to set
	 */
	public void setWarehouse(List<Warehouse> warehouses) {
		this.warehouses = warehouses;
	}
	


}