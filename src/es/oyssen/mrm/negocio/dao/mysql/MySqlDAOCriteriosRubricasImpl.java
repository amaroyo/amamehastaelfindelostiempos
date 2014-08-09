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
import es.oyssen.mrm.negocio.dao.DAOCriteriosRubricas;
import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOUpdateException;
import es.oyssen.mrm.negocio.dao.rowmappers.CriterioRubricaMapper;
import es.oyssen.mrm.negocio.vo.CriterioRubricaVO;


public class MySqlDAOCriteriosRubricasImpl extends DAOBase implements DAOCriteriosRubricas{

	private static String SQL_INSERT = "insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (?,?,?)";
	private static String SQL_UPDATE = "update criterios_rubricas set nombre=?";
	private static String SQL_DELETE = "delete from criterios_rubricas where id_criterio = ? ";
	private static String SQL_FIND_BY_ID = "select * from criterios_rubricas where id_criterio = ?";
	private static String SQL_FIND_BY_ASIGNATURA = "select * from criterios_rubricas where id_asignatura = ?";
	private static String SQL_FIND_BY_GRUPO_CRITERIO = "select * from criterios_rubricas where id_grupo_criterio = ?";




	public void insert(final CriterioRubricaVO criterioRubrica) throws DAOException,
	DAOInsertException {
		try{
			KeyHolder kh = new GeneratedKeyHolder();
			getJdbcTemplate().update(new PreparedStatementCreator() {
				
				public PreparedStatement createPreparedStatement(Connection conn)
						throws SQLException {
					PreparedStatement ps = conn.prepareStatement(SQL_INSERT, new String[]{"id_criterio"});
					ps.setString(1, criterioRubrica.getIdAsignatura());
					ps.setString(2, criterioRubrica.getIdGrupoCriterio());
					ps.setString(3, criterioRubrica.getNombre());
					return ps;
					
				}
			}
			,kh);
			criterioRubrica.setIdCriterio(kh.getKey().toString());
			
		} catch (Exception e) {
			throw new DAOInsertException(e);
		}	
	}

	public void update(CriterioRubricaVO criterioRubrica) throws DAOException,
	DAOUpdateException {
		try {
			 
			String query = SQL_UPDATE;

			query += " where id_criterio = ?";

			getJdbcTemplate().update(query, new Object[]{
					criterioRubrica.getNombre(),
					criterioRubrica.getIdCriterio()});
		} catch(Exception e) {
			throw new DAOUpdateException(e);
		}

	}

	public void delete(CriterioRubricaVO criterioRubrica) throws DAOException,
	DAODeleteException {
		try {
			getJdbcTemplate().update(SQL_DELETE, new Object[]{criterioRubrica.getIdCriterio()});
		} catch (Exception e) {
			throw new DAODeleteException(e);
		}

	}

	@Override
	public CriterioRubricaVO findById(CriterioRubricaVO criterioRubrica) throws DAOException {
		try {
			return (CriterioRubricaVO) getJdbcTemplate().queryForObject(SQL_FIND_BY_ID, new Object[]{criterioRubrica.getIdCriterio()}, new CriterioRubricaMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	public List<CriterioRubricaVO> findAllByAsignatura(CriterioRubricaVO criterioRubrica) throws DAOException {
		try {
			return getJdbcTemplate().query(SQL_FIND_BY_ASIGNATURA, new Object[]{criterioRubrica.getIdAsignatura()}, new CriterioRubricaMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	public List<CriterioRubricaVO> findAllByGrupoCriterio(CriterioRubricaVO criterioRubrica) throws DAOException {
		try {
			return getJdbcTemplate().query(SQL_FIND_BY_GRUPO_CRITERIO, new Object[]{criterioRubrica.getIdGrupoCriterio()}, new CriterioRubricaMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	

}


