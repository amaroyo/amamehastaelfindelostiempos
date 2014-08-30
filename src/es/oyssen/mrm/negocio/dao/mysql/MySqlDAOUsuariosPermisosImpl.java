package es.oyssen.mrm.negocio.dao.mysql;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;

import es.oyssen.mrm.negocio.dao.DAOBase;
import es.oyssen.mrm.negocio.dao.DAOUsuariosPermisos;
import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.rowmappers.PermisoMapper;
import es.oyssen.mrm.negocio.vo.PermisoVO;
import es.oyssen.mrm.negocio.vo.UsuarioPermisosVO;
import es.oyssen.mrm.negocio.vo.UsuarioVO;


public class MySqlDAOUsuariosPermisosImpl extends DAOBase implements DAOUsuariosPermisos{
	
	private static String SQL_INSERT = "insert into usuarios_permisos (id_usuario, id_permiso) values (?,?)";
	private static String SQL_DELETE = "delete from usuarios_permisos where (id_usuario, id_permiso) = (?,?)";
	private static String SQL_FIND_BY_GRUPO = "select p.* from usuarios_permisos as up, permisos as p where p.id_permiso = up.id_permiso and up.id_usuario = ?";
	private static String SQL_FIND_DEMAS = "select * "+
											"from permisos "+
											"where id_permiso not in "+
											"(select up.id_permiso "+
											"from usuarios_permisos as up "+
											"where up.id_usuario=?)";
	
	public void insert(final UsuarioPermisosVO usuarioPermiso) throws DAOException,
			DAOInsertException {
		try{
			getJdbcTemplate().update(SQL_INSERT, new Object[]{usuarioPermiso.getIdUsuario(), usuarioPermiso.getIdPermiso()});
			
		} catch (Exception e) {
			throw new DAOInsertException(e);
		}			
	}
	
	
	public void delete(UsuarioPermisosVO usuarioPermiso) throws DAOException,
			DAODeleteException {
		try {
			getJdbcTemplate().update(SQL_DELETE, new Object[]{usuarioPermiso.getIdUsuario(), usuarioPermiso.getIdPermiso()});
		} catch (Exception e) {
			throw new DAODeleteException(e);
		}
		
	}

	public List<PermisoVO> findByUsuario(UsuarioVO usuario) throws DAOException {
		try {
			return getJdbcTemplate().query(SQL_FIND_BY_GRUPO, new Object[]{usuario.getIdUsuario()}, new PermisoMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}


	@Override
	public List<PermisoVO> findRestantes(UsuarioPermisosVO up)
			throws DAOException {
		try {
			return getJdbcTemplate().query(SQL_FIND_DEMAS, new Object[]{up.getIdUsuario()}, new PermisoMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
}


