package es.oyssen.mrm.negocio.dao;

import java.util.List;

import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOUpdateException;
import es.oyssen.mrm.negocio.vo.CanalVO;
import es.oyssen.mrm.negocio.vo.CriterioCanalVO;

public interface DAOCanales {
	
	public List<CanalVO> findByCriterio(CriterioCanalVO criterio) throws DAOException;
	
	public CanalVO findById(CanalVO canal) throws DAOException;
	
	public void insert(CanalVO canal) throws DAOException, DAOInsertException;
	
	public void update(CanalVO canal) throws DAOException, DAOUpdateException;
	
	public void delete(CanalVO canal) throws DAOException, DAODeleteException;

}
