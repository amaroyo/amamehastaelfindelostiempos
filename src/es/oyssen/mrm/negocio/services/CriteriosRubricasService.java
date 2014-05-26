package es.oyssen.mrm.negocio.services;

import java.util.List;

import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.vo.CriterioRubricaVO;

public interface CriteriosRubricasService {
	
	public void insert(CriterioRubricaVO criterioRubrica) throws ServiceException, DAOException;
	
	public void update(CriterioRubricaVO criterioRubrica) throws ServiceException, DAOException;
	
	public void delete(CriterioRubricaVO criterioRubrica) throws ServiceException, DAOException;

	public CriterioRubricaVO findById(CriterioRubricaVO criterioRubrica) throws ServiceException;
	
	public List<CriterioRubricaVO> findAllByAsignatura(CriterioRubricaVO criterioRubrica) throws ServiceException;
	
	public List<CriterioRubricaVO> findAllByGrupoCriterio(CriterioRubricaVO criterioRubrica) throws ServiceException;

}


