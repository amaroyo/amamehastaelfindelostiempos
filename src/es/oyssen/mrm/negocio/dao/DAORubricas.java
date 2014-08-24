package es.oyssen.mrm.negocio.dao;



import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOUpdateException;
import es.oyssen.mrm.negocio.vo.PortafolioVO;
import es.oyssen.mrm.negocio.vo.RubricaVO;

public interface DAORubricas {

	public void insert(RubricaVO rubrica) throws DAOException, DAOInsertException;
	
	public void update(RubricaVO rubrica) throws DAOException, DAOUpdateException;
	
	public void delete(RubricaVO rubrica) throws DAOException, DAODeleteException;
		
	public RubricaVO findById(RubricaVO rubrica) throws DAOException;

	
	


}
