package es.oyssen.mrm.negocio.services.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.oyssen.mrm.negocio.dao.DAOContactosDistribuidor;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.services.ContactosDistribuidorService;
import es.oyssen.mrm.negocio.vo.ContactoDistribuidorVO;
import es.oyssen.mrm.negocio.vo.CriterioContactoDistribuidorVO;
import es.oyssen.mrm.negocio.vo.DistribuidorVO;

public class ContactosDistribuidorServiceImpl implements ContactosDistribuidorService{
	
	private static Log log = LogFactory.getLog(ContactosDistribuidorServiceImpl.class);

	private DAOContactosDistribuidor daoContactosDistribuidor;
	
	public void setDaoContactosDistribuidor(DAOContactosDistribuidor daoContactosDistribuidor) {
		this.daoContactosDistribuidor = daoContactosDistribuidor;
	}
	
	public List<ContactoDistribuidorVO> findByCriterio(CriterioContactoDistribuidorVO criterio)
			throws ServiceException {
		try {
			return daoContactosDistribuidor.findByCriterio(criterio);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	public ContactoDistribuidorVO findById(ContactoDistribuidorVO contacto)
			throws ServiceException {
		try {
			return daoContactosDistribuidor.findById(contacto);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}
	
	public void update(ContactoDistribuidorVO contacto) throws ServiceException {
		try {
			daoContactosDistribuidor.update(contacto);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}		
	}
	
	public void delete(ContactoDistribuidorVO contacto) throws ServiceException {
		try {
			daoContactosDistribuidor.delete(contacto);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error eliminando el contacto", e);
			throw new ServiceException(e);
		}		
	}

	public void deleteByDistribuidor(DistribuidorVO distribuidor) throws ServiceException {
		try {
			daoContactosDistribuidor.deleteByDistribuidor(distribuidor);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error eliminando el contacto", e);
			throw new ServiceException(e);
		}		
	}	
	
	public void insert(ContactoDistribuidorVO contacto) throws ServiceException {
		try {
			daoContactosDistribuidor.insert(contacto);	
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error creando contacto", e);
			throw new ServiceException(e);
		}
	}			
	
}
