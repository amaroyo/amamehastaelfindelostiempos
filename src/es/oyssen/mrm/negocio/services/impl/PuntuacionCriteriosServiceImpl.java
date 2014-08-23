package es.oyssen.mrm.negocio.services.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.oyssen.mrm.negocio.dao.DAOPuntuacionCriterios;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.services.PuntuacionCriteriosService;
import es.oyssen.mrm.negocio.vo.PuntuacionCriterioVO;

public class PuntuacionCriteriosServiceImpl implements PuntuacionCriteriosService{
	
	private static Log log = LogFactory.getLog(PuntuacionCriteriosServiceImpl.class);
	private DAOPuntuacionCriterios daoPuntuacionCriterios;

	
	public void setDaoPuntuacionCriterios(DAOPuntuacionCriterios daoPuntuacionCriterios) {
		this.daoPuntuacionCriterios = daoPuntuacionCriterios;
	}
	
	public void insert(PuntuacionCriterioVO puntuacionCriterio)
			throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {
			daoPuntuacionCriterios.insert(puntuacionCriterio);	
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error creando puntuacion criterio", e);
			throw new ServiceException(e);
		}		
	}
	
	public void insertOnDuplicateKeyUpdate(PuntuacionCriterioVO puntuacionCriterio)
			throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {
			daoPuntuacionCriterios.insertOnDuplicateKeyUpdate(puntuacionCriterio);	
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error creando puntuacion criterio", e);
			throw new ServiceException(e);
		}		
	}

	public void update(PuntuacionCriterioVO puntuacionCriterio) 
			throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {
			daoPuntuacionCriterios.update(puntuacionCriterio);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error update puntuacion criterio", e);
			throw new ServiceException(e);
		}		
	}

	public void delete(PuntuacionCriterioVO puntuacionCriterio)
			throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {			
			daoPuntuacionCriterios.delete(puntuacionCriterio);			
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error delete puntuacion criterio", e);
			throw new ServiceException(e);
		}			
	}

	
	
	public List<PuntuacionCriterioVO> findAllByPortafolio(PuntuacionCriterioVO puntuacionCriterio) throws ServiceException {
		try {
			return daoPuntuacionCriterios.findAllByPortafolio(puntuacionCriterio);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error delete findAllByPortafolio criterio", e);
			throw new ServiceException(e.getMessage());
		}
	}
	
	
	
}
