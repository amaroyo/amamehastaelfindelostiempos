package es.oyssen.mrm.negocio.services;

import java.util.List;

import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.vo.AnexoVO;

public interface AnexosService {
	
	public void insert(AnexoVO anexo) throws ServiceException, DAOException;
	
	public void update(AnexoVO anexo) throws ServiceException, DAOException;
	
	public void delete(AnexoVO anexo) throws ServiceException, DAOException;
	
	public List<AnexoVO> findAllByPortafolio(AnexoVO anexo) throws ServiceException;

}
