package es.oyssen.mrm.negocio.dao;

import java.util.List;

import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOUpdateException;
import es.oyssen.mrm.negocio.vo.PortafolioVO;
import es.oyssen.mrm.negocio.vo.PuntuacionCriterioVO;

public interface DAOPuntuacionCriterios {

	public void insert(PuntuacionCriterioVO puntuacionCriterio) throws DAOException, DAOInsertException;
	
	public void update(PuntuacionCriterioVO puntuacionCriterio) throws DAOException, DAOUpdateException;
	
	public void delete(PuntuacionCriterioVO puntuacionCriterio) throws DAOException, DAODeleteException;
		
	public List<PuntuacionCriterioVO> findAllByPortafolio(PuntuacionCriterioVO puntuacionCriterio) throws DAOException;

	public void insertOnDuplicateKeyUpdate(PuntuacionCriterioVO puntuacionCriterio) throws DAOException, DAOInsertException;

	public List<PuntuacionCriterioVO> findAllNotasByPortafolio(PortafolioVO p) throws DAOException;

}
