package es.oyssen.mrm.negocio.services;

import java.util.List;

import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.vo.GrupoVO;
import es.oyssen.mrm.negocio.vo.GrupoPermisoVO;
import es.oyssen.mrm.negocio.vo.PermisoVO;


public interface GrupoPermisosService {
	
	public List<PermisoVO> findByGrupo(GrupoVO grupo) throws ServiceException;
	
	public void insert(GrupoPermisoVO permisoGrupo) throws ServiceException;
	
	public void delete(GrupoPermisoVO permisoGrupo) throws ServiceException;	

}
