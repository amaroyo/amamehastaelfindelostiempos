package es.oyssen.mrm.negocio.dao.mysql;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;

import es.oyssen.mrm.negocio.dao.DAOBase;
import es.oyssen.mrm.negocio.dao.DAOPermisos;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.rowmappers.PermisoMapper;
import es.oyssen.mrm.negocio.vo.PermisoVO;


public class MySqlDAOPermisosImpl extends DAOBase implements DAOPermisos{

	private static String SQL_FIND_ALL = "select * from permisos";
	

	public List<PermisoVO> findAll() throws DAOException {
		try {
			return getJdbcTemplate().query(SQL_FIND_ALL, new Object[]{}, new PermisoMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

}


