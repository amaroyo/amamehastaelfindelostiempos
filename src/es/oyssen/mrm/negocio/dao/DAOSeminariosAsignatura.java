package es.oyssen.mrm.negocio.dao;

import java.util.List;

import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOUpdateException;
import es.oyssen.mrm.negocio.vo.SeminarioAsignaturaVO;

public interface DAOSeminariosAsignatura {

	public void insert(SeminarioAsignaturaVO seminarioAsignatura) throws DAOException, DAOInsertException;
	
	public void update(SeminarioAsignaturaVO seminarioAsignatura) throws DAOException, DAOUpdateException;
	
	public void delete(SeminarioAsignaturaVO seminarioAsignatura) throws DAOException, DAODeleteException;
		
	public SeminarioAsignaturaVO findById(SeminarioAsignaturaVO seminarioAsignatura) throws DAOException;
	
	public List<SeminarioAsignaturaVO> findByNombre(SeminarioAsignaturaVO seminarioAsignatura) throws DAOException;
	
	public List<SeminarioAsignaturaVO> findByCodigo(SeminarioAsignaturaVO seminarioAsignatura) throws DAOException;
	
	public List<SeminarioAsignaturaVO> findAllByAsignatura(SeminarioAsignaturaVO seminarioAsignatura) throws DAOException;

	

}
