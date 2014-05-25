package es.oyssen.mrm.negocio.services.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.oyssen.mrm.negocio.dao.DAOCasosClinicos;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.services.CasosClinicosService;
import es.oyssen.mrm.negocio.vo.CasoClinicoVO;

public class CasosClinicosServiceImpl implements CasosClinicosService{
	
	private static Log log = LogFactory.getLog(CasosClinicosServiceImpl.class);
	private DAOCasosClinicos daoCasosClinicos;

	
	public void setDaoCasosClinicos(DAOCasosClinicos daoCasosClinicos) {
		this.daoCasosClinicos = daoCasosClinicos;
	}
	
	public void insert(CasoClinicoVO casoClinico)
			throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {
			daoCasosClinicos.insert(casoClinico);	
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error creando caso clinico", e);
			throw new ServiceException(e);
		}		
	}

	public void update(CasoClinicoVO casoClinico) 
			throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {
			daoCasosClinicos.update(casoClinico);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error update caso clinico", e);
			throw new ServiceException(e);
		}		
	}

	public void delete(CasoClinicoVO casoClinico)
			throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {			
			daoCasosClinicos.delete(casoClinico);			
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error delete caso clinico", e);
			throw new ServiceException(e);
		}			
	}

	
	
	public List<CasoClinicoVO> findAllByPortafolio(CasoClinicoVO casoClinico) throws ServiceException {
		try {
			return daoCasosClinicos.findAllByPortafolio(casoClinico);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error findAllByPortafolio caso clinico", e);
			throw new ServiceException(e.getMessage());
		}
	}
	
	
	
}
