package es.oyssen.mrm.negocio.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import es.oyssen.mrm.negocio.dao.DAOBase;
import es.oyssen.mrm.negocio.dao.DAOGrupoPermisos;
import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.rowmappers.GrupoPermisoMapper;
import es.oyssen.mrm.negocio.vo.GrupoVO;
import es.oyssen.mrm.negocio.vo.GrupoPermisoVO;


public class MySqlDAOGrupoPermisosImpl extends DAOBase implements DAOGrupoPermisos{
	
	private static String SQL_INSERT = "insert into grupos_permisos (id_grupo, id_permiso, descripcion) values (?,?)";
	private static String SQL_DELETE = "delete from grupos_permisos where (id_grupo, id_permiso) = (?,?)";
	private static String SQL_FIND_BY_GRUPO = "select pg.*, p.nombre permiso_nombre from grupos_permisos pg left join permisos p on p.id_permiso = pg.id_permiso where pg.id_grupo = ?";
	
	
	public void insert(final GrupoPermisoVO permisoGrupo) throws DAOException,
			DAOInsertException {
		try{
			getJdbcTemplate().update(SQL_INSERT, new Object[]{permisoGrupo.getIdGrupo(), permisoGrupo.getIdPermiso()});
			
		} catch (Exception e) {
			throw new DAOInsertException(e);
		}			
	}
	
	
	public void delete(GrupoPermisoVO permisoGrupo) throws DAOException,
			DAODeleteException {
		try {
			getJdbcTemplate().update(SQL_DELETE, new Object[]{permisoGrupo.getIdGrupo(), permisoGrupo.getIdPermiso()});
		} catch (Exception e) {
			throw new DAODeleteException(e);
		}
		
	}

	public List<GrupoPermisoVO> findByGrupo(GrupoVO grupo) throws DAOException {
		try {
			return getJdbcTemplate().query(SQL_FIND_BY_GRUPO, new Object[]{grupo.getIdGrupo()}, new GrupoPermisoMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
}


