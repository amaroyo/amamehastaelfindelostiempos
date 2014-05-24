package es.oyssen.mrm.negocio.services;

import java.util.List;

import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.vo.ProfesorAsociadoVO;

public interface ProfesoresAsociadosService {
	
	public void insert(ProfesorAsociadoVO profesor) throws ServiceException, DAOException;
	
	public void update(ProfesorAsociadoVO profesor) throws ServiceException, DAOException;
	
	public void delete(ProfesorAsociadoVO profesor) throws ServiceException, DAOException;
	
	public List<ProfesorAsociadoVO> findAll(ProfesorAsociadoVO profesor) throws ServiceException;
	
	public ProfesorAsociadoVO findById(ProfesorAsociadoVO profesor) throws ServiceException;
	
	public List<ProfesorAsociadoVO> findByCentroTurnoAsignatura(ProfesorAsociadoVO profesor) throws ServiceException;
	
	public List<ProfesorAsociadoVO> findByAnyoAcademico(ProfesorAsociadoVO profesor) throws ServiceException;
	
	public List<ProfesorAsociadoVO> findByAsignatura(ProfesorAsociadoVO profesor) throws ServiceException;
	
	


}
