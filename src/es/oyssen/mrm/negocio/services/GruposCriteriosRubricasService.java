package es.oyssen.mrm.negocio.services;

import java.util.List;

import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOUpdateException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.vo.CriterioRubricaVO;
import es.oyssen.mrm.negocio.vo.GrupoCriteriosRubricasVO;

public interface GruposCriteriosRubricasService {
	
	public void insert(GrupoCriteriosRubricasVO grupoCriterioRubrica) throws ServiceException, DAOException;
	
	public void update(GrupoCriteriosRubricasVO grupoCriterioRubrica) throws ServiceException, DAOException;
	
	public void delete(GrupoCriteriosRubricasVO grupoCriterioRubrica) throws ServiceException, DAOException;

	public GrupoCriteriosRubricasVO findById(GrupoCriteriosRubricasVO grupoCriterioRubrica) throws ServiceException;
	
	public List<GrupoCriteriosRubricasVO> findAllByAsignatura(GrupoCriteriosRubricasVO grupoCriterioRubrica) throws ServiceException;
	
	
}

