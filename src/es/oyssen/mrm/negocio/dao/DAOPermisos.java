package es.oyssen.mrm.negocio.dao;

import java.util.List;

import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.vo.PermisoVO;

public interface DAOPermisos {
	
	public List<PermisoVO> findAll() throws DAOException;

}
