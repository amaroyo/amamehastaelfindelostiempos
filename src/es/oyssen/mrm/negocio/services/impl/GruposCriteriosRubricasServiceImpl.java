package es.oyssen.mrm.negocio.services.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.EmptyResultDataAccessException;

import es.oyssen.mrm.negocio.dao.DAOGruposCriteriosRubricas;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.rowmappers.GrupoCriteriosRubricasMapper;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.services.GruposCriteriosRubricasService;
import es.oyssen.mrm.negocio.vo.CriterioRubricaVO;
import es.oyssen.mrm.negocio.vo.GrupoCriteriosRubricasVO;
import es.oyssen.mrm.negocio.vo.GruposCriteriosRubricaAsignaturaVO;

public class GruposCriteriosRubricasServiceImpl implements GruposCriteriosRubricasService{
	
	private static Log log = LogFactory.getLog(GruposCriteriosRubricasServiceImpl.class);
	private DAOGruposCriteriosRubricas daoGruposCriteriosRubricas;

	
	public void setDaoGruposCriteriosRubricas(DAOGruposCriteriosRubricas daoGruposCriteriosRubricas) {
		this.daoGruposCriteriosRubricas = daoGruposCriteriosRubricas;
	}
	
	public void insert(GrupoCriteriosRubricasVO grupoCriterioRubrica)
			throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {
			daoGruposCriteriosRubricas.insert(grupoCriterioRubrica);	
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error creando grupo criterio rubrica", e);
			throw new ServiceException(e);
		}		
	}

	public void update(GrupoCriteriosRubricasVO grupoCriterioRubrica) 
			throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {
			daoGruposCriteriosRubricas.update(grupoCriterioRubrica);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error update grupo criterio rubrica", e);
			throw new ServiceException(e);
		}		
	}

	public void delete(GrupoCriteriosRubricasVO grupoCriterioRubrica)
			throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {			
			daoGruposCriteriosRubricas.delete(grupoCriterioRubrica);			
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error delete grupo criterio rubrica", e);
			throw new ServiceException(e);
		}			
	}

	
	
	public GrupoCriteriosRubricasVO findById(GrupoCriteriosRubricasVO grupoCriterioRubrica) throws ServiceException {
		try {
			return daoGruposCriteriosRubricas.findById(grupoCriterioRubrica);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error findById grupo criterio rubrica", e);
			throw new ServiceException(e.getMessage());
		}
	}
	
	public List<GrupoCriteriosRubricasVO> findByAsignaturaNombreTipo(GrupoCriteriosRubricasVO grupoCriterioRubrica) throws ServiceException {
		try {
			return daoGruposCriteriosRubricas.findByAsignaturaNombreTipo(grupoCriterioRubrica);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error findById grupo criterio rubrica", e);
			throw new ServiceException(e.getMessage());
		}
	}
	
	public List<GrupoCriteriosRubricasVO> findAllByAsignatura(GrupoCriteriosRubricasVO grupoCriterioRubrica) throws ServiceException {
		try {
			return daoGruposCriteriosRubricas.findAllByAsignatura(grupoCriterioRubrica);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error findAllByAsignatura grupo criterio rubrica", e);
			throw new ServiceException(e.getMessage());
		}
	}
	
	public List<GruposCriteriosRubricaAsignaturaVO> findGruposCriteriosRubricaAsignatura(GrupoCriteriosRubricasVO grupoCriterioRubrica) throws ServiceException {
		try {
			return daoGruposCriteriosRubricas.findGruposCriteriosRubricaAsignatura(grupoCriterioRubrica);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error findAllByAsignatura criterio rubrica", e);
			throw new ServiceException(e.getMessage());
		}
	}
	
	public List<GruposCriteriosRubricaAsignaturaVO> findGruposAnexoAsignatura(GrupoCriteriosRubricasVO grupoCriterioRubrica) throws ServiceException {
		try {
			return daoGruposCriteriosRubricas.findGruposAnexoRubricaAsignatura(grupoCriterioRubrica);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error findAllByAsignatura criterio rubrica", e);
			throw new ServiceException(e.getMessage());
		}
	}

	

	
	
}
