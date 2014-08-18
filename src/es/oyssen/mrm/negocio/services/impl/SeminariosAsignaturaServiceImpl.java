package es.oyssen.mrm.negocio.services.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.oyssen.mrm.negocio.dao.DAOSeminariosAsignatura;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.services.SeminariosAsignaturaService;
import es.oyssen.mrm.negocio.vo.SeminarioAsignaturaCodigoVO;
import es.oyssen.mrm.negocio.vo.SeminarioAsignaturaVO;

public class SeminariosAsignaturaServiceImpl implements SeminariosAsignaturaService{
	
	private static Log log = LogFactory.getLog(SeminariosAsignaturaServiceImpl.class);
	private DAOSeminariosAsignatura daoSeminariosAsignatura;

	
	public void setDaoSeminariosAsignatura(DAOSeminariosAsignatura daoSeminariosAsignatura) {
		this.daoSeminariosAsignatura = daoSeminariosAsignatura;
	}
	
	public void insert(SeminarioAsignaturaVO seminarioAsignatura)
			throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {
			daoSeminariosAsignatura.insert(seminarioAsignatura);	
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error creando seminario asignatura", e);
			throw new ServiceException(e);
		}		
	}

	public void update(SeminarioAsignaturaVO seminarioAsignatura) 
			throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {
			daoSeminariosAsignatura.update(seminarioAsignatura);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error update seminario asignatura", e);
			throw new ServiceException(e);
		}		
	}

	public void delete(SeminarioAsignaturaVO seminarioAsignatura)
			throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {			
			daoSeminariosAsignatura.delete(seminarioAsignatura);			
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error delete seminario asignatura", e);
			throw new ServiceException(e);
		}			
	}

	
	
	public SeminarioAsignaturaVO findById(SeminarioAsignaturaVO seminarioAsignatura) throws ServiceException {
		try {
			return daoSeminariosAsignatura.findById(seminarioAsignatura);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error findById seminario asignatura", e);
			throw new ServiceException(e.getMessage());
		}
	}
	
	public List<SeminarioAsignaturaVO> findByNombre(SeminarioAsignaturaVO seminarioAsignatura) throws ServiceException {
		try {
			return daoSeminariosAsignatura.findByNombre(seminarioAsignatura);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error findById findByNombre asignatura", e);
			throw new ServiceException(e.getMessage());
		}
	}
	
	public List<SeminarioAsignaturaVO> findByCodigo(SeminarioAsignaturaVO seminarioAsignatura) throws ServiceException {
		try {
			return daoSeminariosAsignatura.findByCodigo(seminarioAsignatura);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error findById findByCodigo asignatura", e);
			throw new ServiceException(e.getMessage());
		}
	}
	
	public List<SeminarioAsignaturaVO> findAllByAsignatura(SeminarioAsignaturaVO seminarioAsignatura) throws ServiceException {
		try {
			return daoSeminariosAsignatura.findAllByAsignatura(seminarioAsignatura);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error findById findAllByAsignatura asignatura", e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<SeminarioAsignaturaCodigoVO> findAll(String anyoAcademico)
			throws ServiceException {
		try {
			return daoSeminariosAsignatura.findAll(anyoAcademico);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error findById findAll asignatura", e);
			throw new ServiceException(e.getMessage());
		}
	}
	
}
