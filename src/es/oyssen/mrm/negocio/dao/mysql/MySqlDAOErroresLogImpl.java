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
import es.oyssen.mrm.negocio.dao.DAOErroresLog;
import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOUpdateException;
import es.oyssen.mrm.negocio.dao.rowmappers.ErrorLogMapper;
import es.oyssen.mrm.negocio.vo.ErrorLogVO;


public class MySqlDAOErroresLogImpl extends DAOBase implements DAOErroresLog{

	private static String SQL_INSERT = "insert into errores_log (tipo, descripcion, fecha) values (?,?,?)";
	private static String SQL_UPDATE = "update errores_log set tipo=?, descripcion =?, fecha=?";
	private static String SQL_DELETE = "delete from errores_log where id_error = ?";
	private static String SQL_FIND_BY_ID = "select * from errores_log where id_error = ?";
	private static String SQL_FIND_ALL = "select * from errores_log where tipo !='anyo_academico'";
	private static String SQL_FIND_ANYO = "select * from errores_log where id_error='1'";
	private static String SQL_UPDATE_ANYO = "update errores_log set fecha=?";

	public void insert(final ErrorLogVO error) throws DAOException,
	DAOInsertException {
		try{
			KeyHolder kh = new GeneratedKeyHolder();
			getJdbcTemplate().update(new PreparedStatementCreator() {

				public PreparedStatement createPreparedStatement(Connection conn)
						throws SQLException {
					PreparedStatement ps = conn.prepareStatement(SQL_INSERT, new String[]{"id_error"});
					ps.setString(1, error.getTipo());
					ps.setString(2, error.getDescripcion());
					ps.setString(3, error.getFecha());
					return ps;

				}
			}
			,kh);
			error.setIdError(kh.getKey().toString());

		} catch (Exception e) {
			throw new DAOInsertException(e);
		}			
	}

	public void update(ErrorLogVO error) throws DAOException,
	DAOUpdateException {
		try {
			 
			String query = SQL_UPDATE;

			query += " where id_error = ? ";

			getJdbcTemplate().update(query, new Object[]{
					error.getTipo(),
					error.getDescripcion(),
					error.getFecha(),
					error.getIdError()});
		} catch(Exception e) {
			throw new DAOUpdateException(e);
		}

	}

	public void delete(ErrorLogVO error) throws DAOException,
	DAODeleteException {
		try {
			getJdbcTemplate().update(SQL_DELETE, new Object[]{error.getIdError()});
		} catch (Exception e) {
			throw new DAODeleteException(e);
		}

	}


	public List<ErrorLogVO> findAll() throws DAOException {
		try {
			return getJdbcTemplate().query(SQL_FIND_ALL, new Object[]{}, new ErrorLogMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	public ErrorLogVO findById(ErrorLogVO error) throws DAOException {
		try {
			return (ErrorLogVO) getJdbcTemplate().queryForObject(SQL_FIND_BY_ID, new Object[]{error.getIdError()}, new ErrorLogMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	public ErrorLogVO findAnyoAcademico(ErrorLogVO error) throws DAOException {
		try {
			return (ErrorLogVO) getJdbcTemplate().queryForObject(SQL_FIND_ANYO, new Object[]{}, new ErrorLogMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	public void updateAnyo(ErrorLogVO error) throws DAOException,
	DAOUpdateException {
		try {
			 
			String query = SQL_UPDATE_ANYO;

			query += " where id_error = 1 ";

			getJdbcTemplate().update(query, new Object[]{
					
					error.getFecha()
					});
		} catch(Exception e) {
			throw new DAOUpdateException(e);
		}

	}
}


