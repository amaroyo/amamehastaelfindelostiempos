package es.oyssen.mrm.negocio.services.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.oyssen.mrm.negocio.dao.DAOProfesoresAsociados;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.services.ProfesoresAsociadosService;
import es.oyssen.mrm.negocio.vo.ProfesorAsociadoVO;

public class ProfesoresAsociadosServiceImpl implements ProfesoresAsociadosService{
	
	private static Log log = LogFactory.getLog(ProfesoresAsociadosServiceImpl.class);
	private DAOProfesoresAsociados daoProfesoresAsociados;

	
	public void setDaoProfesoresAsociados(DAOProfesoresAsociados daoProfesoresAsociados) {
		this.daoProfesoresAsociados = daoProfesoresAsociados;
	}
	
	public void insert(ProfesorAsociadoVO profesor) throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {
			daoProfesoresAsociados.insert(profesor);	
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error creando profesor", e);
			throw new ServiceException(e);
		}		
	}

	public void update(ProfesorAsociadoVO profesor) throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {
			daoProfesoresAsociados.update(profesor);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}		
	}

	public void delete(ProfesorAsociadoVO profesor) throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {			
			daoProfesoresAsociados.delete(profesor);			
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error eliminando profesor", e);
			throw new ServiceException(e);
		}			
	}

	public List<ProfesorAsociadoVO> findAll(ProfesorAsociadoVO profesor) throws ServiceException {
		try {
			return daoProfesoresAsociados.findAll(profesor);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
	
	public ProfesorAsociadoVO findById(ProfesorAsociadoVO profesor)
			throws ServiceException {
		try {
			return daoProfesoresAsociados.findById(profesor);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	public List<ProfesorAsociadoVO> findByCentroTurno(ProfesorAsociadoVO profesor) throws ServiceException {
		try {
			return daoProfesoresAsociados.findByCentroTurnoAsignatura(profesor);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	public List<ProfesorAsociadoVO> findByAnyoAcademico(ProfesorAsociadoVO profesor) throws ServiceException {
		try {
			return daoProfesoresAsociados.findByAnyoAcademico(profesor);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
	
	public List<ProfesorAsociadoVO> findByAsignatura(ProfesorAsociadoVO profesor) throws ServiceException {
		try {
			return daoProfesoresAsociados.findByAsignatura(profesor);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	
	public List<ProfesorAsociadoVO> findByCentroTurnoAsignatura(ProfesorAsociadoVO profesor) throws ServiceException {
		try {
			return daoProfesoresAsociados.findByCentroTurnoAsignatura(profesor);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
	
	public List<ProfesorAsociadoVO> findByProfesor(ProfesorAsociadoVO profesor) throws ServiceException {
		try {
			return daoProfesoresAsociados.findByProfesor(profesor);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void updateTODO(ProfesorAsociadoVO profe) throws ServiceException,
			DAOException {
		try {
			daoProfesoresAsociados.updateTODO(profe);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}		
		
	}
	
	
	
	
	
}
