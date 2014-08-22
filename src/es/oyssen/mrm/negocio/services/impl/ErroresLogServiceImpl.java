package es.oyssen.mrm.negocio.services.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.oyssen.mrm.negocio.dao.DAOAnexos;
import es.oyssen.mrm.negocio.dao.DAOErroresLog;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.services.AnexosService;
import es.oyssen.mrm.negocio.services.ErroresLogService;
import es.oyssen.mrm.negocio.vo.AnexoVO;
import es.oyssen.mrm.negocio.vo.ErrorLogVO;

public class ErroresLogServiceImpl implements ErroresLogService{
	
	private static Log log = LogFactory.getLog(ErroresLogServiceImpl.class);
	private DAOErroresLog daoErroresLog;

	
	public void setDaoErroresLog(DAOErroresLog daoErroresLog) {
		this.daoErroresLog = daoErroresLog;
	}
	
	public void insert(ErrorLogVO error)
			throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {
			daoErroresLog.insert(error);	
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error insert error", e);
			throw new ServiceException(e);
		}		
	}

	public void update(ErrorLogVO error)
			throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {
			daoErroresLog.update(error);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error update error", e);
			throw new ServiceException(e);
		}		
	}

	public void delete(ErrorLogVO error)
			throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {			
			daoErroresLog.delete(error);			
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error delete error", e);
			throw new ServiceException(e);
		}			
	}

	
	
	 public List<ErrorLogVO> findAll() throws ServiceException {
		try {
			return daoErroresLog.findAll();
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error findAll error", e);
			throw new ServiceException(e.getMessage());
		}
	}
	 
	 public ErrorLogVO findById(ErrorLogVO error) throws ServiceException {
			try {
				return daoErroresLog.findById(error);
			} catch (DAOException e) {
				e.printStackTrace();
				log.error("Error findById error", e);
				throw new ServiceException(e.getMessage());
			}
		}
	
	
	
}
