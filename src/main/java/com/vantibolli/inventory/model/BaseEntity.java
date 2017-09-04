/*
 * Copyright (C) 2017 Van Tibolli, All Rights Reserved.
 */
package com.vantibolli.inventory.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * The super class of all entities of the persistence.
 *
 * @author  mukhtiar.ahmed
 * @version 1.0
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    /**
	 * The serial version id.
	 */
	private static final long serialVersionUID = 1L;
	/**
     * The ID.
     */   
	@Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_date", updatable=false)
	private Date createDate = new Date();
	
    public void setId(long id) {
    	this.id = id;
    }
    
    public long getId() {
    	return this.id;
    }
    
    public void setcreateDate(Date createDate) {
    	this.createDate = createDate;
    }
    
    public Date getCreateDate() {
    	return this.createDate;
    }
    

    /**
     * Override the equals method.
     * @param target the target
     * @return true if two entities have the same ID
     */
    @Override
    public boolean equals(Object target) {
        if (target instanceof BaseEntity) {
            BaseEntity entity = (BaseEntity) target;
            return entity.getId() == this.id && entity.getId() > 0;
        }
        return false;
    }
}
