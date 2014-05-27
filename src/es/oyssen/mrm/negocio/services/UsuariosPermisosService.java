package es.oyssen.mrm.negocio.services;

import java.util.List;

import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.vo.GrupoPermisoVO;
import es.oyssen.mrm.negocio.vo.UsuarioPermisosVO;
import es.oyssen.mrm.negocio.vo.UsuarioVO;


public interface UsuariosPermisosService {
	
	public List<UsuarioPermisosVO> findByUsuario(UsuarioVO usuario) throws ServiceException;
	
	public void insert(UsuarioPermisosVO usuarioPermiso) throws ServiceException;
	
	public void delete(UsuarioPermisosVO usuarioPermiso) throws ServiceException;	

}
