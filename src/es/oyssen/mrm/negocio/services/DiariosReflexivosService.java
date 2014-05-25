package es.oyssen.mrm.negocio.services;

import java.util.List;

import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.vo.DiarioReflexivoVO;

public interface DiariosReflexivosService {
	
	public void insert(DiarioReflexivoVO diarioReflexivo) throws ServiceException, DAOException;
	
	public void update(DiarioReflexivoVO diarioReflexivo) throws ServiceException, DAOException;
	
	public void delete(DiarioReflexivoVO diarioReflexivo) throws ServiceException, DAOException;
	
	public List<DiarioReflexivoVO> findAllByPortafolio(DiarioReflexivoVO diarioReflexivo) throws ServiceException;

}
