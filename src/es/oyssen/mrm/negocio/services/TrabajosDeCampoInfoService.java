package es.oyssen.mrm.negocio.services;

import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.vo.TrabajoDeCampoInfoVO;
import es.oyssen.mrm.struts.forms.asignaturas.SubirArchivoForm;

public interface TrabajosDeCampoInfoService {
	
	public String insert(TrabajoDeCampoInfoVO trabajoInfo) throws ServiceException, DAOException;
	
	public void update(TrabajoDeCampoInfoVO trabajoInfo) throws ServiceException, DAOException;
	
	public void delete(TrabajoDeCampoInfoVO trabajoInfo) throws ServiceException, DAOException;

	public TrabajoDeCampoInfoVO findById(TrabajoDeCampoInfoVO trabajoInfo) throws ServiceException;

	public void process(SubirArchivoForm f) throws ServiceException;

	public void updateSimple(TrabajoDeCampoInfoVO t) throws ServiceException, DAOException;

	public void updateDates(TrabajoDeCampoInfoVO t) throws ServiceException, DAOException;
	
	
}

