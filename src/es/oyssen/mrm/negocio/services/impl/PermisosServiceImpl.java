package es.oyssen.mrm.negocio.services.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.oyssen.mrm.negocio.dao.DAOGrupos;
import es.oyssen.mrm.negocio.dao.DAOPermisos;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.services.GruposService;
import es.oyssen.mrm.negocio.services.PermisosService;
import es.oyssen.mrm.negocio.vo.GrupoVO;
import es.oyssen.mrm.negocio.vo.PermisoVO;


public class PermisosServiceImpl implements PermisosService{
	
	private static Log log = LogFactory.getLog(PermisosServiceImpl.class);

	private DAOPermisos daoPermisos;
	
	
	public void setDaoPermisos(DAOPermisos daoPermisos) {
		this.daoPermisos = daoPermisos;
	}
	

	public List<PermisoVO> findAll()
			throws ServiceException {
		try {
			return daoPermisos.findAll();
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error findAll permisos", e);
			throw new ServiceException(e);
		}
	}
	
}
