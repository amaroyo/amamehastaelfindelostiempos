package es.oyssen.mrm.negocio.services;

import java.util.List;

import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.vo.CanalVO;
import es.oyssen.mrm.negocio.vo.ContactoCanalVO;
import es.oyssen.mrm.negocio.vo.CriterioContactoCanalVO;

public interface ContactosCanalService {

	public List<ContactoCanalVO> findByCriterio(CriterioContactoCanalVO criterio) throws ServiceException;
	
	public ContactoCanalVO findById(ContactoCanalVO contacto) throws ServiceException;
	
	public void update(ContactoCanalVO contacto) throws ServiceException;
	
	public void delete(ContactoCanalVO contacto) throws ServiceException;
	
	public void insert(ContactoCanalVO contacto) throws ServiceException;
	
	public void deleteByCanal(CanalVO canal) throws ServiceException;

}
