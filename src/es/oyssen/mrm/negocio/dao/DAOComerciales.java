package es.oyssen.mrm.negocio.dao;

import java.util.List;

import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOUpdateException;
import es.oyssen.mrm.negocio.vo.CanalVO;
import es.oyssen.mrm.negocio.vo.ComercialVO;
import es.oyssen.mrm.negocio.vo.CriterioComercialVO;
import es.oyssen.mrm.negocio.vo.DistribuidorVO;

public interface DAOComerciales {
	
	public List<ComercialVO> findByCriterio(CriterioComercialVO criterio) throws DAOException;
	
	public ComercialVO findById(ComercialVO comercial) throws DAOException;
	
	public void insert(ComercialVO comercial) throws DAOException, DAOInsertException;
	
	public void update(ComercialVO comercial) throws DAOException, DAOUpdateException;
	
	public void delete(ComercialVO comercial) throws DAOException, DAODeleteException;

	public List<ComercialVO> findByDistribuidor(DistribuidorVO distribuidor) throws DAOException;
	
	public List<ComercialVO> findByCanal(CanalVO canal) throws DAOException;
	
	public void updateCanal(String idLead) throws DAOException;
	
}
