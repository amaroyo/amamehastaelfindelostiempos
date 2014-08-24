package es.oyssen.mrm.negocio.services;

import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.vo.PortafolioVO;
import es.oyssen.mrm.negocio.vo.RubricaVO;

public interface RubricasService {
	
	public void insert(RubricaVO rubrica) throws ServiceException, DAOException;
	
	public void update(RubricaVO rubrica) throws ServiceException, DAOException;
	
	public void delete(RubricaVO rubrica) throws ServiceException, DAOException;

	public RubricaVO findById(RubricaVO rubrica) throws ServiceException;

	
	
	
}

