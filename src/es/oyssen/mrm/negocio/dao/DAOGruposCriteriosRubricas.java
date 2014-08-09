package es.oyssen.mrm.negocio.dao;

import java.util.List;

import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOUpdateException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.vo.CriterioRubricaVO;
import es.oyssen.mrm.negocio.vo.GrupoCriteriosRubricasVO;
import es.oyssen.mrm.negocio.vo.GruposCriteriosRubricaAsignaturaVO;

public interface DAOGruposCriteriosRubricas {

	public void insert(GrupoCriteriosRubricasVO grupoCriterioRubrica) throws DAOException, DAOInsertException;
	
	public void update(GrupoCriteriosRubricasVO grupoCriterioRubrica) throws DAOException, DAOUpdateException;
	
	public void delete(GrupoCriteriosRubricasVO grupoCriterioRubrica) throws DAOException, DAODeleteException;
		
	public GrupoCriteriosRubricasVO findById(GrupoCriteriosRubricasVO grupoCriterioRubrica) throws DAOException;
	
	public List<GrupoCriteriosRubricasVO> findAllByAsignatura(GrupoCriteriosRubricasVO grupoCriterioRubrica) throws DAOException;

	public List<GruposCriteriosRubricaAsignaturaVO> findGruposCriteriosRubricaAsignatura(GrupoCriteriosRubricasVO grupoCriterioRubrica) throws DAOException;


		
	

}
