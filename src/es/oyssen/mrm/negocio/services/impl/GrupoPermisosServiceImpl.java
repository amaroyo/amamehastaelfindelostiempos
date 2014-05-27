package es.oyssen.mrm.negocio.services.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.oyssen.mrm.negocio.dao.DAOGrupoPermisos;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.services.GrupoPermisosService;
import es.oyssen.mrm.negocio.vo.GrupoVO;
import es.oyssen.mrm.negocio.vo.GrupoPermisoVO;
import es.oyssen.mrm.negocio.vo.PermisoVO;


public class GrupoPermisosServiceImpl implements GrupoPermisosService{
	
	private static Log log = LogFactory.getLog(GrupoPermisosServiceImpl.class);

	private DAOGrupoPermisos daoGrupoPermisos;
	
	
	public void setDaoGrupoPermisos(DAOGrupoPermisos daoGrupoPermisos) {
		this.daoGrupoPermisos = daoGrupoPermisos;
	}
	
	
	public List<PermisoVO> findByGrupo(GrupoVO grupo)
			throws ServiceException {
		try {
			return daoGrupoPermisos.findByGrupo(grupo);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error findByGrupo grupo permiso", e);
			throw new ServiceException(e);
		}
	}

	public void delete(GrupoPermisoVO permisoGrupo) throws ServiceException {
		try {
			daoGrupoPermisos.delete(permisoGrupo);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error eliminando el permiso del grupo", e);
			throw new ServiceException(e);
		}		
	}

	public void insert(GrupoPermisoVO permisoGrupo) throws ServiceException {
		try {
			daoGrupoPermisos.insert(permisoGrupo);	
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error creando el permiso para el grupo", e);
			throw new ServiceException(e);
		}
	}			
	
}
