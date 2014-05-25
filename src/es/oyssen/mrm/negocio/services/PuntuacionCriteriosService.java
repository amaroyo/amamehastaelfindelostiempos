package es.oyssen.mrm.negocio.services;

import java.util.List;

import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOUpdateException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.vo.CasoClinicoVO;
import es.oyssen.mrm.negocio.vo.PuntuacionCriterioVO;

public interface PuntuacionCriteriosService {
	
	public void insert(PuntuacionCriterioVO puntuacionCriterio) throws ServiceException, DAOException;
	
	public void update(PuntuacionCriterioVO puntuacionCriterio) throws ServiceException, DAOException;
	
	public void delete(PuntuacionCriterioVO puntuacionCriterio) throws ServiceException, DAOException;
	
	public List<PuntuacionCriterioVO> findAllByPortafolio(PuntuacionCriterioVO puntuacionCriterio) throws ServiceException;

}


