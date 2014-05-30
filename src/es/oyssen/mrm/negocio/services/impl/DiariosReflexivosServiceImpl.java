package es.oyssen.mrm.negocio.services.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.oyssen.mrm.negocio.dao.DAODiariosReflexivos;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.services.DiariosReflexivosService;
import es.oyssen.mrm.negocio.vo.DiarioReflexivoVO;

public class DiariosReflexivosServiceImpl implements DiariosReflexivosService{
	
	private static Log log = LogFactory.getLog(DiariosReflexivosServiceImpl.class);
	private DAODiariosReflexivos daoDiariosReflexivos;

	
	public void setDaoDiariosReflexivos(DAODiariosReflexivos daoDiariosReflexivos) {
		this.daoDiariosReflexivos = daoDiariosReflexivos;
	}
	
	public void insert(DiarioReflexivoVO diarioReflexivo)
			throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {
			daoDiariosReflexivos.insert(diarioReflexivo);	
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error creando diario reflexivo", e);
			throw new ServiceException(e);
		}		
	}

	public void update(DiarioReflexivoVO diarioReflexivo)
			throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {
			daoDiariosReflexivos.update(diarioReflexivo);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error update diario reflexivo", e);
			throw new ServiceException(e);
		}		
	}

	public void delete(DiarioReflexivoVO diarioReflexivo)
			throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {			
			daoDiariosReflexivos.delete(diarioReflexivo);			
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error delete diario reflexivo", e);
			throw new ServiceException(e);
		}			
	}

	
	
	public List<DiarioReflexivoVO> findAllByPortafolio(DiarioReflexivoVO diarioReflexivo) throws ServiceException {
		try {
			return daoDiariosReflexivos.findAllByPortafolio(diarioReflexivo);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error findAllByPortafolio diario reflexivo", e);
			throw new ServiceException(e.getMessage());
		}
	}
	
	
	
}
