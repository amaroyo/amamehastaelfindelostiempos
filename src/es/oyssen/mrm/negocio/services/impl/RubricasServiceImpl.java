package es.oyssen.mrm.negocio.services.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.oyssen.mrm.negocio.dao.DAORubricas;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.services.RubricasService;
import es.oyssen.mrm.negocio.vo.RubricaVO;

public class RubricasServiceImpl implements RubricasService{
	
	private static Log log = LogFactory.getLog(RubricasServiceImpl.class);
	private DAORubricas daoRubricas;

	
	public void setDaoRubricas(DAORubricas daoRubricas) {
		this.daoRubricas = daoRubricas;
	}
	
	public void insert(RubricaVO rubrica)
			throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {
			daoRubricas.insert(rubrica);	
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error creando rubrica", e);
			throw new ServiceException(e);
		}		
	}

	public void update(RubricaVO rubrica) 
			throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {
			daoRubricas.update(rubrica);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error update rubrica", e);
			throw new ServiceException(e);
		}		
	}

	public void delete(RubricaVO rubrica)
			throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {			
			daoRubricas.delete(rubrica);			
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error delete rubrica", e);
			throw new ServiceException(e);
		}			
	}

	
	
	public RubricaVO findById(RubricaVO rubrica) throws ServiceException {
		try {
			return daoRubricas.findById(rubrica);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error findById rubrica", e);
			throw new ServiceException(e.getMessage());
		}
	}
	
	
	
	
	
}
