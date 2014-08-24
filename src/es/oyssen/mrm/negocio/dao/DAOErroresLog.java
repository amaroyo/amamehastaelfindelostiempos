package es.oyssen.mrm.negocio.dao;

import java.util.List;

import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOUpdateException;
import es.oyssen.mrm.negocio.vo.AnexoVO;
import es.oyssen.mrm.negocio.vo.ErrorLogVO;

public interface DAOErroresLog {

	public void insert(ErrorLogVO error) throws DAOException, DAOInsertException;
	
	public void update(ErrorLogVO error) throws DAOException, DAOUpdateException;
	
	public void delete(ErrorLogVO error) throws DAOException, DAODeleteException;
		
	public List<ErrorLogVO> findAll() throws DAOException;
	
	public ErrorLogVO findById(ErrorLogVO error) throws DAOException;
	
	public ErrorLogVO findAnyoAcademico(ErrorLogVO error) throws DAOException;
	
	public void updateAnyo(ErrorLogVO error) throws DAOException, DAOUpdateException;
	
	
	

}
