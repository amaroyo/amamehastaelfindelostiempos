package es.oyssen.mrm.negocio.dao;

import java.util.List;

import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOUpdateException;
import es.oyssen.mrm.negocio.vo.CriterioResponsableVO;
import es.oyssen.mrm.negocio.vo.ResponsableVO;

public interface DAOResponsables {
	
	public List<ResponsableVO> findByCriterio(CriterioResponsableVO criterio) throws DAOException;
	
	public ResponsableVO findById(ResponsableVO responsable) throws DAOException;
	
	public void insert(ResponsableVO responsable) throws DAOException, DAOInsertException;
	
	public void update(ResponsableVO responsable) throws DAOException, DAOUpdateException;
	
	public void delete(ResponsableVO responsable) throws DAOException, DAODeleteException;


}
