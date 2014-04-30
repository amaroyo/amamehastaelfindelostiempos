package es.oyssen.mrm.negocio.services;

import java.util.List;

import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.vo.LogUsuarioVO;

public interface LogsUsuarioService {
	
	public List<LogUsuarioVO> findAll() throws ServiceException;
	
	public void insert(LogUsuarioVO logUsuario) throws ServiceException;

}
