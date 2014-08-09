package es.oyssen.mrm.negocio.services.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.oyssen.mrm.negocio.dao.DAOTrabajosDeCampoInfo;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.services.TrabajosDeCampoInfoService;
import es.oyssen.mrm.negocio.vo.TrabajoDeCampoInfoVO;


public class TrabajosDeCampoInfoServiceImpl implements TrabajosDeCampoInfoService{
	
	
	private static Log log = LogFactory.getLog(TrabajosDeCampoInfoServiceImpl.class);
	private DAOTrabajosDeCampoInfo daoTrabajosDeCampoInfo;

	
	public void setDaoTrabajosDeCampoInfo(DAOTrabajosDeCampoInfo daoTrabajosDeCampoInfo) {
		this.daoTrabajosDeCampoInfo = daoTrabajosDeCampoInfo;
	}
	
	public void insert(TrabajoDeCampoInfoVO trabajo)
			throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {
			daoTrabajosDeCampoInfo.insert(trabajo);	
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error creando TrabajoDeCampoInfo", e);
			throw new ServiceException(e);
		}		
	}

	public void update(TrabajoDeCampoInfoVO trabajo) 
			throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {
			daoTrabajosDeCampoInfo.update(trabajo);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error update TrabajoDeCampoInfo", e);
			throw new ServiceException(e);
		}		
	}

	public void delete(TrabajoDeCampoInfoVO trabajo)
			throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {			
			daoTrabajosDeCampoInfo.delete(trabajo);			
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error delete TrabajoDeCampoInfo", e);
			throw new ServiceException(e);
		}			
	}

	
	
	public TrabajoDeCampoInfoVO findById(TrabajoDeCampoInfoVO trabajo) throws ServiceException {
		try {
			return daoTrabajosDeCampoInfo.findById(trabajo);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error findById TrabajoDeCampoInfo", e);
			throw new ServiceException(e.getMessage());
		}
	}
	
	
	
	
	
}
