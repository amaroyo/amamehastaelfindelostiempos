package es.oyssen.mrm.negocio.dao;

import java.util.List;

import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOUpdateException;
import es.oyssen.mrm.negocio.vo.AnexoVO;

public interface DAOAnexos {

	public void insert(AnexoVO anexo) throws DAOException, DAOInsertException;
	
	public void update(AnexoVO anexo) throws DAOException, DAOUpdateException;
	
	public void delete(AnexoVO anexo) throws DAOException, DAODeleteException;
		
	public List<AnexoVO> findAllByPortafolio(AnexoVO anexo) throws DAOException;

	public AnexoVO findById(AnexoVO a) throws DAOException;
	
	
	

}
