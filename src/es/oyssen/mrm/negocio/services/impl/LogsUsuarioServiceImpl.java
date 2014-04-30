package es.oyssen.mrm.negocio.services.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.oyssen.mrm.negocio.dao.DAOLogsUsuario;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.services.LogsUsuarioService;
import es.oyssen.mrm.negocio.vo.LogUsuarioVO;


public class LogsUsuarioServiceImpl implements LogsUsuarioService{
	
	private static Log log = LogFactory.getLog(LogsUsuarioServiceImpl.class);

	private DAOLogsUsuario daoLogsUsuario;
	
	
	public void setDaoLogsUsuario(DAOLogsUsuario daoLogsUsuario) {
		this.daoLogsUsuario = daoLogsUsuario;
	}
	
	
	public List<LogUsuarioVO> findAll()
			throws ServiceException {
		try {
			return daoLogsUsuario.findAll();
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	public void insert(LogUsuarioVO logUsuario) throws ServiceException {
		try {
			daoLogsUsuario.insert(logUsuario);	
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error creando el log del usuario", e);
			throw new ServiceException(e);
		}
	}			
	
}
