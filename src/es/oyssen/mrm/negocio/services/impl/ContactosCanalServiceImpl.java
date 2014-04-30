package es.oyssen.mrm.negocio.services.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.oyssen.mrm.negocio.dao.DAOContactosCanal;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.services.ContactosCanalService;
import es.oyssen.mrm.negocio.vo.CanalVO;
import es.oyssen.mrm.negocio.vo.ContactoCanalVO;
import es.oyssen.mrm.negocio.vo.CriterioContactoCanalVO;


public class ContactosCanalServiceImpl implements ContactosCanalService{
	
	private static Log log = LogFactory.getLog(ContactosCanalServiceImpl.class);

	private DAOContactosCanal daoContactosCanal;
	
	public void setDaoContactosCanal(DAOContactosCanal daoContactosCanal) {
		this.daoContactosCanal = daoContactosCanal;
	}
	
	public List<ContactoCanalVO> findByCriterio(CriterioContactoCanalVO criterio)
			throws ServiceException {
		try {
			return daoContactosCanal.findByCriterio(criterio);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	public ContactoCanalVO findById(ContactoCanalVO contacto)
			throws ServiceException {
		try {
			return daoContactosCanal.findById(contacto);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}
	
	public void update(ContactoCanalVO contacto) throws ServiceException {
		try {
			daoContactosCanal.update(contacto);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}		
	}
	
	public void delete(ContactoCanalVO contacto) throws ServiceException {
		try {
			daoContactosCanal.delete(contacto);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error eliminando el contacto", e);
			throw new ServiceException(e);
		}		
	}
	
	public void deleteByCanal(CanalVO canal) throws ServiceException {
		try {
			daoContactosCanal.deleteByCanal(canal);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error eliminando el contacto", e);
			throw new ServiceException(e);
		}		
	}

	public void insert(ContactoCanalVO contacto) throws ServiceException {
		try {
			daoContactosCanal.insert(contacto);	
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error creando contacto", e);
			throw new ServiceException(e);
		}
	}			
	
}
