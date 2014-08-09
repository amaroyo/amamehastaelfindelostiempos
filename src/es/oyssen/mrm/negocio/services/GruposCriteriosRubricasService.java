package es.oyssen.mrm.negocio.services;

import java.util.List;

import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.vo.GrupoCriteriosRubricasVO;
import es.oyssen.mrm.negocio.vo.GruposCriteriosRubricaAsignaturaVO;

public interface GruposCriteriosRubricasService {
	
	public void insert(GrupoCriteriosRubricasVO grupoCriterioRubrica) throws ServiceException, DAOException;
	
	public void update(GrupoCriteriosRubricasVO grupoCriterioRubrica) throws ServiceException, DAOException;
	
	public void delete(GrupoCriteriosRubricasVO grupoCriterioRubrica) throws ServiceException, DAOException;

	public GrupoCriteriosRubricasVO findById(GrupoCriteriosRubricasVO grupoCriterioRubrica) throws ServiceException;
	
	public List<GrupoCriteriosRubricasVO> findAllByAsignatura(GrupoCriteriosRubricasVO grupoCriterioRubrica) throws ServiceException;
	
	public List<GruposCriteriosRubricaAsignaturaVO> findGruposCriteriosRubricaAsignatura (GrupoCriteriosRubricasVO grupoCriterioRubrica) throws ServiceException;
}

