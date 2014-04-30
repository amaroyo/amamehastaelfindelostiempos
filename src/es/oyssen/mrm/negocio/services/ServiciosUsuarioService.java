package es.oyssen.mrm.negocio.services;

import java.util.List;

import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.vo.ServicioUsuarioVO;
import es.oyssen.mrm.negocio.vo.UsuarioVO;


public interface ServiciosUsuarioService {
	
	public List<ServicioUsuarioVO> findByUsuario(UsuarioVO usuario) throws ServiceException;
	
	public void insert(ServicioUsuarioVO servicioUsuario) throws ServiceException;
	
	public void delete(ServicioUsuarioVO servicioUsuario) throws ServiceException;	

}
