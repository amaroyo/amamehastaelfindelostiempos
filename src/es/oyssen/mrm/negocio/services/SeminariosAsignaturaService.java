package es.oyssen.mrm.negocio.services;

import java.util.List;

import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.vo.SeminarioAsignaturaCodigoVO;
import es.oyssen.mrm.negocio.vo.SeminarioAsignaturaVO;
import es.oyssen.mrm.negocio.vo.UsuarioPortafolioVO;

public interface SeminariosAsignaturaService {
	
	public void insert(SeminarioAsignaturaVO seminarioAsignatura) throws ServiceException, DAOException;
	
	public void update(SeminarioAsignaturaVO seminarioAsignatura) throws ServiceException, DAOException;
	
	public void delete(SeminarioAsignaturaVO seminarioAsignatura) throws ServiceException, DAOException;

	public SeminarioAsignaturaVO findById(SeminarioAsignaturaVO seminarioAsignatura) throws ServiceException;
	
	public List<SeminarioAsignaturaVO> findByNombre(SeminarioAsignaturaVO seminarioAsignatura) throws ServiceException;
	
	public SeminarioAsignaturaVO findByCodigo(SeminarioAsignaturaVO seminarioAsignatura) throws ServiceException;
	
	public List<SeminarioAsignaturaVO> findAllByAsignatura(SeminarioAsignaturaVO seminarioAsignatura) throws ServiceException;

	public List<SeminarioAsignaturaCodigoVO> findAll(String anyoAcademico) throws ServiceException;

	public List<UsuarioPortafolioVO> findAlumnosMissing(SeminarioAsignaturaVO sa) throws ServiceException;


}



