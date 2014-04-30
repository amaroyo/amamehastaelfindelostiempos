/*
 * DAOBase.java
 *
 * Created on 20 de enero de 2009
 * Copyright 2009. All rights reserved.
 */
package es.oyssen.mrm.negocio.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import es.oyssen.mrm.negocio.vo.Credenciales;


public class DAOBase extends JdbcDaoSupport {
	
	protected final Log log = LogFactory.getLog(getClass());
	

	protected Credenciales credenciales;
	

	protected int maxRows;
		
	
	/**
	 * Constructor.
	 */
	public DAOBase() {
	}
	

	public void setCredenciales(Credenciales credenciales) {
		this.credenciales = credenciales;
	}
	

	public void setMaxRows(int maxRows) {
		this.maxRows = maxRows;
	}
}
