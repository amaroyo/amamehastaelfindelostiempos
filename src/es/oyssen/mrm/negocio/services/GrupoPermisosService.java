package es.oyssen.mrm.negocio.services;

import java.util.List;

import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.vo.GrupoVO;
import es.oyssen.mrm.negocio.vo.GrupoPermisoVO;


public interface GrupoPermisosService {
	
	public List<GrupoPermisoVO> findByGrupo(GrupoVO grupo) throws ServiceException;
	
	public void insert(GrupoPermisoVO permisoGrupo) throws ServiceException;
	
	public void delete(GrupoPermisoVO permisoGrupo) throws ServiceException;	

}
