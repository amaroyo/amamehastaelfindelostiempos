package es.oyssen.mrm.negocio.services.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.oyssen.mrm.negocio.dao.DAOPermisosGrupo;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.services.PermisosGrupoService;
import es.oyssen.mrm.negocio.vo.GrupoVO;
import es.oyssen.mrm.negocio.vo.PermisoGrupoVO;


public class PermisosGrupoServiceImpl implements PermisosGrupoService{
	
	private static Log log = LogFactory.getLog(PermisosGrupoServiceImpl.class);

	private DAOPermisosGrupo daoPermisosGrupo;
	
	
	public void setDaoPermisosGrupo(DAOPermisosGrupo daoPermisosGrupo) {
		this.daoPermisosGrupo = daoPermisosGrupo;
	}
	
	
	public List<PermisoGrupoVO> findByGrupo(GrupoVO grupo)
			throws ServiceException {
		try {
			return daoPermisosGrupo.findByGrupo(grupo);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	public void delete(PermisoGrupoVO permisoGrupo) throws ServiceException {
		try {
			daoPermisosGrupo.delete(permisoGrupo);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error eliminando el permiso del grupo", e);
			throw new ServiceException(e);
		}		
	}

	public void insert(PermisoGrupoVO permisoGrupo) throws ServiceException {
		try {
			daoPermisosGrupo.insert(permisoGrupo);	
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error creando el permiso para el grupo", e);
			throw new ServiceException(e);
		}
	}			
	
}
