package com.vantibolli.inventory.repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.h2.tools.RunScript;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;

import com.vantibolli.inventory.HibernateTestConfiguration;



@ContextConfiguration(classes = { HibernateTestConfiguration.class })
public abstract class AbstractRepositoryTest  extends AbstractTransactionalTestNGSpringContextTests {

	/**
     * The logger package name.
     */
    protected static final Logger LOGGER = Logger.getLogger(AbstractRepositoryTest.class.getName());
    
    private static boolean isImport = false;
    
	@Autowired
	SessionFactory sessionFactory;
	
	
    public void init() throws SQLException {
		if(isImport) return;
		Session session = sessionFactory.openSession();
		session.doWork(new org.hibernate.jdbc.Work() {			
			@Override
			public void execute(Connection connection) throws SQLException {
				File script = new File(getClass().getResource("/import.sql").getFile());
                try {
					RunScript.execute(connection, new FileReader(script));
				} catch (FileNotFoundException e) {
					LOGGER.error("error :", e);
				}
				
			}
		});
		
		session.close();	
		isImport = true;
		
    }
	
}
