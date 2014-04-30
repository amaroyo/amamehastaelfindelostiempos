package es.oyssen.mrm.negocio.dao;

import java.util.List;

import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.vo.ServicioUsuarioVO;
import es.oyssen.mrm.negocio.vo.UsuarioVO;

public interface DAOServiciosUsuario {
	
	public List<ServicioUsuarioVO> findByUsuario(UsuarioVO grupo) throws DAOException;
	
	public void insert(ServicioUsuarioVO permisoGrupo) throws DAOException, DAOInsertException;
	
	public void delete(ServicioUsuarioVO permisoGrupo) throws DAOException, DAODeleteException;

}
