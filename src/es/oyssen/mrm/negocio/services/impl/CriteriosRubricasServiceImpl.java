package es.oyssen.mrm.negocio.services.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.oyssen.mrm.negocio.dao.DAOCriteriosRubricas;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.services.CriteriosRubricasService;
import es.oyssen.mrm.negocio.vo.CriterioRubricaVO;

public class CriteriosRubricasServiceImpl implements CriteriosRubricasService{
	
	private static Log log = LogFactory.getLog(CriteriosRubricasServiceImpl.class);
	private DAOCriteriosRubricas daoCriteriosRubricas;

	
	public void setDaoCriteriosRubricas(DAOCriteriosRubricas daoCriteriosRubricas) {
		this.daoCriteriosRubricas = daoCriteriosRubricas;
	}
	
	public void insert(CriterioRubricaVO criterioRubrica)
			throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {
			daoCriteriosRubricas.insert(criterioRubrica);	
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error creando criterio rubrica", e);
			throw new ServiceException(e);
		}		
	}

	public void update(CriterioRubricaVO criterioRubrica) 
			throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {
			daoCriteriosRubricas.update(criterioRubrica);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error update criterio rubrica", e);
			throw new ServiceException(e);
		}		
	}

	public void delete(CriterioRubricaVO criterioRubrica)
			throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {			
			daoCriteriosRubricas.delete(criterioRubrica);			
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error delete criterio rubrica", e);
			throw new ServiceException(e);
		}			
	}

	
	
	public CriterioRubricaVO findById(CriterioRubricaVO criterioRubrica) throws ServiceException {
		try {
			return daoCriteriosRubricas.findById(criterioRubrica);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error findById criterio rubrica", e);
			throw new ServiceException(e.getMessage());
		}
	}
	
	public List<CriterioRubricaVO> findAllByAsignatura(CriterioRubricaVO criterioRubrica) throws ServiceException {
		try {
			return daoCriteriosRubricas.findAllByAsignatura(criterioRubrica);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error findAllByAsignatura criterio rubrica", e);
			throw new ServiceException(e.getMessage());
		}
	}
	
	public List<CriterioRubricaVO> findAllByGrupoCriterio(CriterioRubricaVO criterioRubrica) throws ServiceException {
		try {
			return daoCriteriosRubricas.findAllByGrupoCriterio(criterioRubrica);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error findAllByGrupoCriterio criterio rubrica", e);
			throw new ServiceException(e.getMessage());
		}
	}
	
}
