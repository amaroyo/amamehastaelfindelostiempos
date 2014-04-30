package es.oyssen.mrm.negocio.services.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.oyssen.mrm.negocio.dao.DAOServiciosUsuario;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.services.ServiciosUsuarioService;
import es.oyssen.mrm.negocio.vo.ServicioUsuarioVO;
import es.oyssen.mrm.negocio.vo.UsuarioVO;


public class ServiciosUsuarioServiceImpl implements ServiciosUsuarioService{
	
	private static Log log = LogFactory.getLog(ServiciosUsuarioServiceImpl.class);

	private DAOServiciosUsuario daoServiciosUsuario;
	
	
	public void setDaoServiciosUsuario(DAOServiciosUsuario daoServiciosUsuario) {
		this.daoServiciosUsuario = daoServiciosUsuario;
	}
	
	
	public List<ServicioUsuarioVO> findByUsuario(UsuarioVO usuario)
			throws ServiceException {
		try {
			return daoServiciosUsuario.findByUsuario(usuario);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	public void delete(ServicioUsuarioVO servicioUsuario) throws ServiceException {
		try {
			daoServiciosUsuario.delete(servicioUsuario);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error eliminando el servicio del usuario", e);
			throw new ServiceException(e);
		}		
	}

	public void insert(ServicioUsuarioVO servicioUsuario) throws ServiceException {
		try {
			daoServiciosUsuario.insert(servicioUsuario);	
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error creando el servicio del usuario", e);
			throw new ServiceException(e);
		}
	}			
	
}
