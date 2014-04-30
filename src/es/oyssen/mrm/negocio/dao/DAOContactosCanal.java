package es.oyssen.mrm.negocio.dao;

import java.util.List;

import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOUpdateException;
import es.oyssen.mrm.negocio.vo.CanalVO;
import es.oyssen.mrm.negocio.vo.ContactoCanalVO;
import es.oyssen.mrm.negocio.vo.CriterioContactoCanalVO;

public interface DAOContactosCanal {
	
	public List<ContactoCanalVO> findByCriterio(CriterioContactoCanalVO criterio) throws DAOException;
	
	public ContactoCanalVO findById(ContactoCanalVO contacto) throws DAOException;
	
	public void insert(ContactoCanalVO contacto) throws DAOException, DAOInsertException;
	
	public void update(ContactoCanalVO contacto) throws DAOException, DAOUpdateException;
	
	public void delete(ContactoCanalVO contacto) throws DAOException, DAODeleteException;

	public void deleteByCanal(CanalVO canal) throws DAOException, DAODeleteException;
	
}
