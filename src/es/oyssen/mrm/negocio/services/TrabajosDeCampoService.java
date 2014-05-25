package es.oyssen.mrm.negocio.services;

import java.util.List;

import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOUpdateException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.vo.TrabajoDeCampoVO;

public interface TrabajosDeCampoService {
	
	public void insert(TrabajoDeCampoVO trabajoDeCampo) throws ServiceException, DAOException;
	
	public void update(TrabajoDeCampoVO trabajoDeCampo) throws ServiceException, DAOException;
	
	public void delete(TrabajoDeCampoVO trabajoDeCampo) throws ServiceException, DAOException;
	
	public List<TrabajoDeCampoVO> findAllByPortafolio(TrabajoDeCampoVO trabajoDeCampo) throws ServiceException;

}
