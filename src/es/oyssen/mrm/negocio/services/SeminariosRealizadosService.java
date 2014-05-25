package es.oyssen.mrm.negocio.services;

import java.util.List;

import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.vo.SeminarioRealizadoVO;

public interface SeminariosRealizadosService {
	
	public void insert(SeminarioRealizadoVO seminarioRealizado) throws ServiceException, DAOException;
	
	public void update(SeminarioRealizadoVO seminarioRealizado) throws ServiceException, DAOException;
	
	public void delete(SeminarioRealizadoVO seminarioRealizado) throws ServiceException, DAOException;
	
	public List<SeminarioRealizadoVO> findAllByPortafolio(SeminarioRealizadoVO seminarioRealizado) throws ServiceException;

}
