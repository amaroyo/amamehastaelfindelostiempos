package es.oyssen.mrm.negocio.dao;

import java.util.List;

import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOUpdateException;
import es.oyssen.mrm.negocio.vo.CanalVO;
import es.oyssen.mrm.negocio.vo.CriterioDistribuidorVO;
import es.oyssen.mrm.negocio.vo.DistribuidorVO;

public interface DAODistribuidores {
	
	public List<DistribuidorVO> findByCriterio(CriterioDistribuidorVO criterio) throws DAOException;
	
	public DistribuidorVO findById(DistribuidorVO distribuidor) throws DAOException;
	
	public void insert(DistribuidorVO distribuidor) throws DAOException, DAOInsertException;
	
	public void update(DistribuidorVO distribuidor) throws DAOException, DAOUpdateException;
	
	public void delete(DistribuidorVO distribuidor) throws DAOException, DAODeleteException;

	public List<DistribuidorVO> findByCanal(CanalVO canal) throws DAOException;
	
	public void bloquear(DistribuidorVO distribuidor) throws DAOException;
	
	public void desbloquear(DistribuidorVO distribuidor) throws DAOException;
	
	public void updateCanal(String idLead) throws DAOException;
	
}
