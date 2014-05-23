package es.oyssen.mrm.negocio.dao;

import java.util.List;

import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOUpdateException;
import es.oyssen.mrm.negocio.vo.AsignaturaVO;

public interface DAOAsignaturas {
	
	public List<AsignaturaVO> findAll() throws DAOException;
	
	public List<AsignaturaVO> findByCurso(AsignaturaVO asignatura) throws DAOException;
	
	public AsignaturaVO findById(AsignaturaVO asignatura) throws DAOException;
	
	public List<AsignaturaVO> findByCodigo(AsignaturaVO asignatura) throws DAOException;
	
	public AsignaturaVO findByNombre(AsignaturaVO asignatura) throws DAOException;
	
	public void insert(AsignaturaVO asignatura) throws DAOException, DAOInsertException;
	
	public void update(AsignaturaVO asignatura) throws DAOException, DAOUpdateException;
	
	public void delete(AsignaturaVO asignatura) throws DAOException, DAODeleteException;

}
