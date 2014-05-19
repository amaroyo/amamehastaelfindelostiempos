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
import es.oyssen.mrm.negocio.dao.DAOPermisosGrupo;
import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.rowmappers.PermisoGrupoMapper;
import es.oyssen.mrm.negocio.vo.GrupoVO;
import es.oyssen.mrm.negocio.vo.PermisoGrupoVO;


public class MySqlDAOPermisosGrupoImpl extends DAOBase implements DAOPermisosGrupo{
	
	private static String SQL_INSERT = "insert into permisos_grupos (id_grupo, id_permiso) values (?,?)";
	private static String SQL_DELETE = "delete from permisos_grupos where id_permisoGrupo = ?";
	private static String SQL_FIND_BY_GRUPO = "select pg.*, p.nombre permiso_nombre from grupos_permisos pg left join permisos p on p.id_permiso = pg.id_permiso where pg.id_grupo = ?";
	
	
	public void insert(final PermisoGrupoVO permisoGrupo) throws DAOException,
			DAOInsertException {
		try{
			KeyHolder kh = new GeneratedKeyHolder();
			getJdbcTemplate().update(new PreparedStatementCreator() {
				
				public PreparedStatement createPreparedStatement(Connection conn)
						throws SQLException {
					PreparedStatement ps = conn.prepareStatement(SQL_INSERT, new String[]{"id_permisoGrupo"});
					ps.setString(1, permisoGrupo.getIdGrupo());
					ps.setString(2, permisoGrupo.getIdPermiso());
					
					return ps;
				}
			}
			,kh);
			permisoGrupo.setIdPermisoGrupo(kh.getKey().toString());
			
		} catch (Exception e) {
			throw new DAOInsertException(e);
		}			
	}
	
	public void delete(PermisoGrupoVO permisoGrupo) throws DAOException,
			DAODeleteException {
		try {
			getJdbcTemplate().update(SQL_DELETE, new Object[]{permisoGrupo.getIdPermisoGrupo()});
		} catch (Exception e) {
			throw new DAODeleteException(e);
		}
		
	}

	public List<PermisoGrupoVO> findByGrupo(GrupoVO grupo) throws DAOException {
		try {
			return getJdbcTemplate().query(SQL_FIND_BY_GRUPO, new Object[]{grupo.getIdGrupo()}, new PermisoGrupoMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
}


