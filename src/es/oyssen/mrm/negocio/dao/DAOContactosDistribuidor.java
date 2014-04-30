package es.oyssen.mrm.negocio.dao;

import java.util.List;

import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOUpdateException;
import es.oyssen.mrm.negocio.vo.ContactoDistribuidorVO;
import es.oyssen.mrm.negocio.vo.CriterioContactoDistribuidorVO;
import es.oyssen.mrm.negocio.vo.DistribuidorVO;

public interface DAOContactosDistribuidor {
	
	public List<ContactoDistribuidorVO> findByCriterio(CriterioContactoDistribuidorVO criterio) throws DAOException;
	
	public ContactoDistribuidorVO findById(ContactoDistribuidorVO contacto) throws DAOException;
	
	public void insert(ContactoDistribuidorVO contacto) throws DAOException, DAOInsertException;
	
	public void update(ContactoDistribuidorVO contacto) throws DAOException, DAOUpdateException;
	
	public void delete(ContactoDistribuidorVO contacto) throws DAOException, DAODeleteException;

	public void deleteByDistribuidor(DistribuidorVO distribuidor) throws DAOException, DAODeleteException;
	
}
