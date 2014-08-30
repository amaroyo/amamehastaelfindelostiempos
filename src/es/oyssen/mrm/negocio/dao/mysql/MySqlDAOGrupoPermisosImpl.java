package es.oyssen.mrm.negocio.dao.mysql;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;

import es.oyssen.mrm.negocio.dao.DAOBase;
import es.oyssen.mrm.negocio.dao.DAOGrupoPermisos;
import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.rowmappers.PermisoMapper;
import es.oyssen.mrm.negocio.vo.GrupoPermisoVO;
import es.oyssen.mrm.negocio.vo.GrupoVO;
import es.oyssen.mrm.negocio.vo.PermisoVO;


public class MySqlDAOGrupoPermisosImpl extends DAOBase implements DAOGrupoPermisos{
	
	private static String SQL_INSERT = "insert into grupos_permisos (id_grupo, id_permiso) values (?,?)";
	private static String SQL_DELETE = "delete from grupos_permisos where (id_grupo, id_permiso) = (?,?)";
	private static String SQL_FIND_BY_GRUPO = "select p.* from grupos_permisos as gp, permisos as p where p.id_permiso = gp.id_permiso and gp.id_grupo=?";
	
	
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

	public List<PermisoVO> findByGrupo(GrupoVO grupo) throws DAOException {
		try {
			return getJdbcTemplate().query(SQL_FIND_BY_GRUPO, new Object[]{grupo.getIdGrupo()}, new PermisoMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
}


