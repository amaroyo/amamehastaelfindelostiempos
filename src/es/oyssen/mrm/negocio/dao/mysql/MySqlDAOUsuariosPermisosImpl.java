package es.oyssen.mrm.negocio.dao.mysql;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import es.oyssen.mrm.negocio.dao.DAOBase;
import es.oyssen.mrm.negocio.dao.DAOUsuariosPermisos;
import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.rowmappers.UsuarioPermisosMapper;
import es.oyssen.mrm.negocio.vo.UsuarioVO;
import es.oyssen.mrm.negocio.vo.UsuarioPermisosVO;


public class MySqlDAOUsuariosPermisosImpl extends DAOBase implements DAOUsuariosPermisos{
	
	private static String SQL_INSERT = "insert into usuarios_permisos (id_usuario, id_permiso) values (?,?)";
	private static String SQL_DELETE = "delete from usuarios_permisos where (id_usuario, id_permiso) = (?,?)";
	private static String SQL_FIND_BY_GRUPO = "select pg.*, p.nombre permiso_nombre from usuarios_permisos pg left join permisos p on p.id_permiso = pg.id_permiso where pg.id_usuario = ?";
	
	
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

	public List<UsuarioPermisosVO> findByUsuario(UsuarioVO usuario) throws DAOException {
		try {
			return getJdbcTemplate().query(SQL_FIND_BY_GRUPO, new Object[]{usuario.getIdUsuario()}, new UsuarioPermisosMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
}


