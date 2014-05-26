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
import es.oyssen.mrm.negocio.dao.DAORubricas;
import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOUpdateException;
import es.oyssen.mrm.negocio.dao.rowmappers.RubricaMapper;
import es.oyssen.mrm.negocio.vo.RubricaVO;


public class MySqlDAORubricasImpl extends DAOBase implements DAORubricas{

	private static String SQL_INSERT = "insert into rubricas (id_asignatura, competencias, numero_criterios) values (?,?,?)";
	private static String SQL_UPDATE = "update rubricas set nombre=?, numero_criterios=?";
	private static String SQL_DELETE = "delete from rubricas where id_asignatura = ? ";
	private static String SQL_FIND_BY_ID = "select * from rubricas where id_asignatura = ?";
	


	public void insert(final RubricaVO rubrica) throws DAOException,
	DAOInsertException {
		try{
			
			getJdbcTemplate().update(new PreparedStatementCreator() {

				public PreparedStatement createPreparedStatement(Connection conn)
						throws SQLException {
					PreparedStatement ps = conn.prepareStatement(SQL_INSERT, new String[]{});
					ps.setString(1, rubrica.getIdAsignatura());
					ps.setString(2, rubrica.getCompetencias());
					ps.setString(3, rubrica.getNumeroCriterios());
					return ps;

				}
			}
			);
			

		} catch (Exception e) {
			throw new DAOInsertException(e);
		}	
	}

	public void update(RubricaVO rubrica) throws DAOException,
	DAOUpdateException {
		try {
			 
			String query = SQL_UPDATE;

			query += " where id_asignatura = ?";

			getJdbcTemplate().update(query, new Object[]{
					rubrica.getCompetencias(),
					rubrica.getNumeroCriterios(),
					rubrica.getIdAsignatura()});
		} catch(Exception e) {
			throw new DAOUpdateException(e);
		}

	}

	public void delete(RubricaVO rubrica) throws DAOException,
	DAODeleteException {
		try {
			getJdbcTemplate().update(SQL_DELETE, new Object[]{rubrica.getIdAsignatura()});
		} catch (Exception e) {
			throw new DAODeleteException(e);
		}

	}

	@Override
	public RubricaVO findById(RubricaVO rubrica) throws DAOException {
		try {
			return (RubricaVO) getJdbcTemplate().queryForObject(SQL_FIND_BY_ID, new Object[]{rubrica.getIdAsignatura()}, new RubricaMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	

}


