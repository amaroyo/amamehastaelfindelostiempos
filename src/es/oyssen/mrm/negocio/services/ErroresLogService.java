package es.oyssen.mrm.negocio.services;

import java.util.List;

import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.vo.AnexoVO;
import es.oyssen.mrm.negocio.vo.ErrorLogVO;

public interface ErroresLogService {
	
	public void insert(ErrorLogVO error) throws ServiceException, DAOException;
	
	public void update(ErrorLogVO error) throws ServiceException, DAOException;
	
	public void delete(ErrorLogVO error) throws ServiceException, DAOException;
	
	public List<ErrorLogVO> findAll() throws ServiceException;
	
	public ErrorLogVO findById(ErrorLogVO error) throws ServiceException;

}
