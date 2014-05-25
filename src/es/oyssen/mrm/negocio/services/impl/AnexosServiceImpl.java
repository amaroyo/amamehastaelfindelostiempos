package es.oyssen.mrm.negocio.services.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.oyssen.mrm.negocio.dao.DAOAnexos;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.services.AnexosService;
import es.oyssen.mrm.negocio.vo.AnexoVO;

public class AnexosServiceImpl implements AnexosService{
	
	private static Log log = LogFactory.getLog(AnexosServiceImpl.class);
	private DAOAnexos daoAnexos;

	
	public void setDaoAnexos(DAOAnexos daoAnexos) {
		this.daoAnexos = daoAnexos;
	}
	
	public void insert(AnexoVO anexo)
			throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {
			daoAnexos.insert(anexo);	
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error creando anexo", e);
			throw new ServiceException(e);
		}		
	}

	public void update(AnexoVO anexo)
			throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {
			daoAnexos.update(anexo);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error update anexo", e);
			throw new ServiceException(e);
		}		
	}

	public void delete(AnexoVO anexo)
			throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {			
			daoAnexos.delete(anexo);			
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error delete anexo", e);
			throw new ServiceException(e);
		}			
	}

	
	
	public List<AnexoVO> findAllByPortafolio(AnexoVO anexo) throws ServiceException {
		try {
			return daoAnexos.findAllByPortafolio(anexo);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error findAllByPortafolio anexo", e);
			throw new ServiceException(e.getMessage());
		}
	}
	
	
	
}
