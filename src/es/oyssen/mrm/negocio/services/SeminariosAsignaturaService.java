package es.oyssen.mrm.negocio.services;

import java.util.List;

import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.vo.SeminarioAsignaturaVO;

public interface SeminariosAsignaturaService {
	
	public void insert(SeminarioAsignaturaVO seminarioAsignatura) throws ServiceException, DAOException;
	
	public void update(SeminarioAsignaturaVO seminarioAsignatura) throws ServiceException, DAOException;
	
	public void delete(SeminarioAsignaturaVO seminarioAsignatura) throws ServiceException, DAOException;

	public SeminarioAsignaturaVO findById(SeminarioAsignaturaVO seminarioAsignatura) throws ServiceException;
	
	public List<SeminarioAsignaturaVO> findByNombre(SeminarioAsignaturaVO seminarioAsignatura) throws ServiceException;
	
	public List<SeminarioAsignaturaVO> findByCodigo(SeminarioAsignaturaVO seminarioAsignatura) throws ServiceException;
	
	public List<SeminarioAsignaturaVO> findAllByAsignatura(SeminarioAsignaturaVO seminarioAsignatura) throws ServiceException;

}



