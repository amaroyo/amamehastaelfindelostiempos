package es.oyssen.mrm.negocio.services.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.oyssen.mrm.negocio.dao.DAOCanales;
import es.oyssen.mrm.negocio.dao.DAOContactosCanal;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.services.CanalesService;
import es.oyssen.mrm.negocio.vo.CanalVO;
import es.oyssen.mrm.negocio.vo.CriterioCanalVO;


public class CanalesServiceImpl implements CanalesService{
	
	private static Log log = LogFactory.getLog(CanalesServiceImpl.class);

	private DAOCanales daoCanales;
	private DAOContactosCanal daoContactosCanal;
	
	
	public void setDaoContactosCanal(DAOContactosCanal daoContactosCanal) {
		this.daoContactosCanal = daoContactosCanal;
	}
	
	public void setDaoCanales(DAOCanales daoCanales) {
		this.daoCanales = daoCanales;
	}
	
	public List<CanalVO> findByCriterio(CriterioCanalVO criterio)
			throws ServiceException {
		try {
			return daoCanales.findByCriterio(criterio);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	public CanalVO findById(CanalVO canal)
			throws ServiceException {
		try {
			return daoCanales.findById(canal);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}
	
	public void update(CanalVO canal) throws ServiceException {
		try {
			daoCanales.update(canal);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}		
	}
	
	public void delete(CanalVO canal) throws ServiceException {
		try {
			daoContactosCanal.deleteByCanal(canal);
			daoCanales.delete(canal);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error eliminando el canal", e);
			throw new ServiceException(e);
		}		
	}

	public void insert(CanalVO canal) throws ServiceException {
		try {
			daoCanales.insert(canal);	
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error creando canal", e);
			throw new ServiceException(e);
		}
	}			
	
}
