package es.oyssen.mrm.negocio.dao.mysql;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;

import es.oyssen.mrm.negocio.dao.DAOBase;
import es.oyssen.mrm.negocio.dao.DAOGrupos;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.rowmappers.GrupoMapper;
import es.oyssen.mrm.negocio.vo.GrupoVO;


public class MySqlDAOGruposImpl extends DAOBase implements DAOGrupos{

	private static String SQL_FIND_ALL = "select * from grupos";
	

	public List<GrupoVO> findAll() throws DAOException {
		try {
			return getJdbcTemplate().query(SQL_FIND_ALL, new Object[]{}, new GrupoMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

}


