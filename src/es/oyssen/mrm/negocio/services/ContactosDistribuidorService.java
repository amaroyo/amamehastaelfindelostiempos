package es.oyssen.mrm.negocio.services;

import java.util.List;

import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.vo.ContactoDistribuidorVO;
import es.oyssen.mrm.negocio.vo.CriterioContactoDistribuidorVO;
import es.oyssen.mrm.negocio.vo.DistribuidorVO;

public interface ContactosDistribuidorService {

	public List<ContactoDistribuidorVO> findByCriterio(CriterioContactoDistribuidorVO criterio) throws ServiceException;
	
	public ContactoDistribuidorVO findById(ContactoDistribuidorVO contacto) throws ServiceException;
	
	public void update(ContactoDistribuidorVO contacto) throws ServiceException;
	
	public void delete(ContactoDistribuidorVO contacto) throws ServiceException;
	
	public void insert(ContactoDistribuidorVO contacto) throws ServiceException;
	
	public void deleteByDistribuidor(DistribuidorVO distribuidor) throws ServiceException;

}
