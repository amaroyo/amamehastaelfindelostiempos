package es.oyssen.mrm.negocio.dao;

import java.util.List;

import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOUpdateException;
import es.oyssen.mrm.negocio.vo.ProfesorAsociadoVO;

public interface DAOProfesoresAsociados {
	
	public List<ProfesorAsociadoVO> findAll(ProfesorAsociadoVO profesor) throws DAOException;
	
	public ProfesorAsociadoVO findById(ProfesorAsociadoVO profesor) throws DAOException;
	
	public List<ProfesorAsociadoVO> findByProfesor(ProfesorAsociadoVO profesor) throws DAOException;
	
	public List<ProfesorAsociadoVO> findByAsignatura(ProfesorAsociadoVO profesor) throws DAOException;
	
	public List<ProfesorAsociadoVO> findByAnyoAcademico(ProfesorAsociadoVO profesor) throws DAOException;
	
	public List<ProfesorAsociadoVO> findByCentroTurnoAsignatura(ProfesorAsociadoVO profesor) throws DAOException;
	
	public void insert(ProfesorAsociadoVO profesor) throws DAOException, DAOInsertException;
	
	public void update(ProfesorAsociadoVO profesor) throws DAOException, DAOUpdateException;
	
	public void delete(ProfesorAsociadoVO profesor) throws DAOException, DAODeleteException;

}
