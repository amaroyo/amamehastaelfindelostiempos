package es.oyssen.mrm.negocio.dao;

import java.util.List;

import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOUpdateException;
import es.oyssen.mrm.negocio.vo.GrupoVO;
import es.oyssen.mrm.negocio.vo.UsuarioVO;

public interface DAOUsuarios {
	

	public void insert(UsuarioVO usuario) throws DAOException, DAOInsertException;
	
	public void update(UsuarioVO usuario) throws DAOException, DAOUpdateException;
	
	public void delete(UsuarioVO usuario) throws DAOException, DAODeleteException;
	
	public List<UsuarioVO> findAll() throws DAOException;
	
	public List<UsuarioVO> findByGrupo(GrupoVO grupo) throws DAOException;
	
	public UsuarioVO findById(UsuarioVO usuario) throws DAOException;
	
	public UsuarioVO findByCorreo(UsuarioVO usuario) throws DAOException;
	
	public UsuarioVO findByDni(UsuarioVO usuario) throws DAOException;
	
	public UsuarioVO findByCorreoPass(UsuarioVO usuario) throws DAOException;
	
	public UsuarioVO findByNombreApellidos(UsuarioVO usuario) throws DAOException;
	

}
