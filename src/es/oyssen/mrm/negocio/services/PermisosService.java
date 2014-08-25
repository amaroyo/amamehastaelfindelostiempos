package es.oyssen.mrm.negocio.services;

import java.util.List;

import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.vo.GrupoVO;
import es.oyssen.mrm.negocio.vo.PermisoVO;


public interface PermisosService {

	public List<PermisoVO> findAll() throws ServiceException;

	public List<PermisoVO> findRestantes(GrupoVO g) throws ServiceException;

}
