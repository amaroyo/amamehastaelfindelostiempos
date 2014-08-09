package es.oyssen.mrm.negocio.services;

import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.vo.TrabajoDeCampoInfoVO;

public interface TrabajosDeCampoInfoService {
	
	public String insert(TrabajoDeCampoInfoVO trabajoInfo) throws ServiceException, DAOException;
	
	public void update(TrabajoDeCampoInfoVO trabajoInfo) throws ServiceException, DAOException;
	
	public void delete(TrabajoDeCampoInfoVO trabajoInfo) throws ServiceException, DAOException;

	public TrabajoDeCampoInfoVO findById(TrabajoDeCampoInfoVO trabajoInfo) throws ServiceException;
	
	
}

