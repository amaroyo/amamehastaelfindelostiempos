package es.oyssen.mrm.negocio.dao;

import java.util.List;

import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOUpdateException;
import es.oyssen.mrm.negocio.vo.CriterioFicheroVO;
import es.oyssen.mrm.negocio.vo.FicheroVO;
import es.oyssen.mrm.negocio.vo.ServicioVO;

public interface DAOFicheros {
	
	public List<FicheroVO> findByCriterio(CriterioFicheroVO criterio) throws DAOException;
	
	public FicheroVO findById(FicheroVO fichero) throws DAOException;
	
	public void insert(FicheroVO fichero) throws DAOException, DAOInsertException;
	
	public void update(FicheroVO fichero) throws DAOException, DAOUpdateException;
	
	public void delete(FicheroVO fichero) throws DAOException, DAODeleteException;

	public void deleteByServicio(ServicioVO servicio) throws DAOException, DAODeleteException;
	
	public void updateFechaUltimaDescarga(FicheroVO fichero) throws DAOException, DAOUpdateException;
	
}
