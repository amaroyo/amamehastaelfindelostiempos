package es.oyssen.mrm.negocio.services.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.oyssen.mrm.negocio.dao.DAOAsignaturas;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.services.AsignaturasService;
import es.oyssen.mrm.negocio.vo.AsignaturaVO;
import es.oyssen.mrm.negocio.vo.PortafolioVO;
import es.oyssen.mrm.negocio.vo.ProfesorAsociadoVO;

public class AsignaturasServiceImpl implements AsignaturasService{
	
	private static Log log = LogFactory.getLog(AsignaturasServiceImpl.class);
	private DAOAsignaturas daoAsignaturas;

	
	public void setDaoAsignaturas(DAOAsignaturas daoAsignaturas) {
		this.daoAsignaturas = daoAsignaturas;
	}
	
	public void insert(AsignaturaVO asignatura)
			throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {
			daoAsignaturas.insert(asignatura);	
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error creando asignatura", e);
			throw new ServiceException(e);
		}		
	}

	public void update(AsignaturaVO asignatura)
			throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {
			daoAsignaturas.update(asignatura);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}		
	}

	public void delete(AsignaturaVO asignatura)
			throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {			
			daoAsignaturas.delete(asignatura);			
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error eliminando asignatura", e);
			throw new ServiceException(e);
		}			
	}

	public List<AsignaturaVO> findAll(String anyoAcademico) throws ServiceException {
		try {
			return daoAsignaturas.findAll(anyoAcademico);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
	
	public AsignaturaVO findById(AsignaturaVO asignatura)
			throws ServiceException {
		try {
			return daoAsignaturas.findById(asignatura);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}
	
	public List<AsignaturaVO> findByCurso(AsignaturaVO asignatura) throws ServiceException {
		try {
			return daoAsignaturas.findByCurso(asignatura);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
	
	public List<AsignaturaVO> findByProfesorAnyoAcademico(ProfesorAsociadoVO profesor) throws ServiceException {
		try {
			return daoAsignaturas.findByProfesorAnyoAcademico(profesor);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
	
	public List<AsignaturaVO> findByAlumnoAnyoAcademico(PortafolioVO portafolio) throws ServiceException {
		try {
			return daoAsignaturas.findByAlumnoAnyoAcademico(portafolio);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	
	public AsignaturaVO findByNombre(AsignaturaVO asignatura) throws ServiceException {
		try {
			return daoAsignaturas.findByNombre(asignatura);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	
	public List<AsignaturaVO> findByCodigo(AsignaturaVO asignatura) throws ServiceException {
		try {
			return daoAsignaturas.findByCodigo(asignatura);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	
}
