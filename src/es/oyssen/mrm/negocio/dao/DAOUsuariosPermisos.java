package es.oyssen.mrm.negocio.dao;

import java.util.List;

import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.vo.UsuarioVO;
import es.oyssen.mrm.negocio.vo.UsuarioPermisosVO;

public interface DAOUsuariosPermisos {
	
	public List<UsuarioPermisosVO> findByUsuario(UsuarioVO usuario) throws DAOException;
	
	public void insert(UsuarioPermisosVO usuarioPermiso) throws DAOException, DAOInsertException;
	
	public void delete(UsuarioPermisosVO usuarioPermiso) throws DAOException, DAODeleteException;

}
