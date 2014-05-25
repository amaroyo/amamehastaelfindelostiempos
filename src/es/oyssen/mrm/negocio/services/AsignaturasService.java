package es.oyssen.mrm.negocio.services;

import java.util.List;

import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.vo.AsignaturaVO;
import es.oyssen.mrm.negocio.vo.PortafolioVO;
import es.oyssen.mrm.negocio.vo.ProfesorAsociadoVO;

public interface AsignaturasService {
	
	public void insert(AsignaturaVO asignatura) throws ServiceException, DAOException;
	
	public void update(AsignaturaVO asignatura) throws ServiceException, DAOException;
	
	public void delete(AsignaturaVO asignatura) throws ServiceException, DAOException;
	
	public List<AsignaturaVO> findAll(String anyoAcademico) throws ServiceException;
	
	public AsignaturaVO findById(AsignaturaVO asignatura) throws ServiceException;
	
	public AsignaturaVO findByNombre(AsignaturaVO asignatura) throws ServiceException;
	
	public List<AsignaturaVO> findByProfesorAnyoAcademico(ProfesorAsociadoVO profesor) throws ServiceException;
	
	public List<AsignaturaVO> findByAlumnoAnyoAcademico(PortafolioVO portafolio) throws ServiceException;
	
	public List<AsignaturaVO> findByCodigo(AsignaturaVO asignatura) throws ServiceException;
	
	public List<AsignaturaVO> findByCurso(AsignaturaVO asignatura) throws ServiceException;
	

}
