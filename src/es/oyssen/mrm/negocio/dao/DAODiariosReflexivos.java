package es.oyssen.mrm.negocio.dao;

import java.util.List;

import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOUpdateException;
import es.oyssen.mrm.negocio.vo.DiarioReflexivoVO;

public interface DAODiariosReflexivos {

	public void insert(DiarioReflexivoVO diarioReflexivo) throws DAOException, DAOInsertException;
	
	public void update(DiarioReflexivoVO diarioReflexivo) throws DAOException, DAOUpdateException;
	
	public void delete(DiarioReflexivoVO diarioReflexivo) throws DAOException, DAODeleteException;
		
	public List<DiarioReflexivoVO> findAllByPortafolio(DiarioReflexivoVO diarioReflexivo) throws DAOException;

	public DiarioReflexivoVO findByIDs(DiarioReflexivoVO d) throws DAOException;
	
	
	

}
