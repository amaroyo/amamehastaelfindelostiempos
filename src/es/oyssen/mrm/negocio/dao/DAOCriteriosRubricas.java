package es.oyssen.mrm.negocio.dao;

import java.util.List;

import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOUpdateException;
import es.oyssen.mrm.negocio.vo.CriterioRubricaVO;

public interface DAOCriteriosRubricas {

	public void insert(CriterioRubricaVO criterioRubrica) throws DAOException, DAOInsertException;
	
	public void update(CriterioRubricaVO criterioRubrica) throws DAOException, DAOUpdateException;
	
	public void delete(CriterioRubricaVO criterioRubrica) throws DAOException, DAODeleteException;
		
	public CriterioRubricaVO findById(CriterioRubricaVO criterioRubrica) throws DAOException;
	
	public List<CriterioRubricaVO> findAllByAsignatura(CriterioRubricaVO criterioRubrica) throws DAOException;

	public List<CriterioRubricaVO> findAllByGrupoCriterio(CriterioRubricaVO criterioRubrica) throws DAOException;
	
	

}
