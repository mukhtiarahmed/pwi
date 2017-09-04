/*
 * Copyright (C) 2017 Van Tibolli, All Rights Reserved.
 */

package com.vantibolli.inventory.repository;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.vantibolli.inventory.InventoryHelper;
import com.vantibolli.inventory.exception.InventoryException;
import com.vantibolli.inventory.model.BaseEntity;
import com.vantibolli.inventory.repository.interfaces.GenericRepository;
/**
 * The Abstract repository. 
 * @author  mukhtiar.ahmed
 * @version 1.0
 */
@Transactional
public abstract class AbstractRepository<T extends BaseEntity> implements GenericRepository<T> {
	
	/**
	 * The Persistent class.
	 */
	private final Class<T> persistentClass;
	
	/**
	 * The default constructor.
	 */
	@SuppressWarnings("unchecked")
	public AbstractRepository(){		
		this.persistentClass =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/**
	 * The SessionFactory.
	 */
	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	/**
     * Check the configuration.
     *
     * @throws ConfigurationException if there's any error in configuration
     */
    @PostConstruct
    public void checkConfiguration() {
    	InventoryHelper.checkConfigNotNull(sessionFactory, "sessionFactory");     
    }

    @Override
	@SuppressWarnings("unchecked")
	public T getByKey(long key) throws  InventoryException {
		T entity = (T) getSession().get(persistentClass, key);
		InventoryHelper.checkEntityExist(entity, key);
		return entity;
	}

    @Override
	public void persist(T entity) throws InventoryException  {
		InventoryHelper.checkNull(entity, "entity");
		getSession().persist(entity);
	}
    
    @Override
	public void delete(T entity)  throws InventoryException  {
		InventoryHelper.checkNull(entity, "entity");
		getSession().delete(entity);
	}
	
    @Override
	@SuppressWarnings("unchecked")
	public List<T> findAll() throws InventoryException  {
		return createEntityCriteria().list();
	}
	
    @Override
	public long count() throws  InventoryException {
		Number count = (Number)createEntityCriteria().setProjection(Projections.rowCount()).uniqueResult();
		return count.longValue();
	}
	
	
	public Criteria createEntityCriteria(){
		return getSession().createCriteria(persistentClass);
	}
	
	public Query getNamedQuery(String queryName){
		return getSession().getNamedQuery(queryName);
	}

}
