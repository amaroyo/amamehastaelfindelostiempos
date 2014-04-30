package es.oyssen.mrm.negocio.services;

import java.util.List;

import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.vo.GrupoVO;
import es.oyssen.mrm.negocio.vo.PermisoGrupoVO;


public interface PermisosGrupoService {
	
	public List<PermisoGrupoVO> findByGrupo(GrupoVO grupo) throws ServiceException;
	
	public void insert(PermisoGrupoVO permisoGrupo) throws ServiceException;
	
	public void delete(PermisoGrupoVO permisoGrupo) throws ServiceException;	

}
