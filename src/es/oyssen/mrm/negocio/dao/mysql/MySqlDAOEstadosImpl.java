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
import es.oyssen.mrm.negocio.dao.DAOEstado;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.rowmappers.EstadoMapper;
import es.oyssen.mrm.negocio.vo.EstadoVO;

public class MySqlDAOEstadosImpl extends DAOBase implements DAOEstado {
	
	private static String SQL_INSERT = "insert into estados (nombre) values (?)";
	private static String SQL_FIND_ID = "select * from estados where id_estado = ?";
	private static String SQL_FIND_NAME = "select * from estados where upper(nombre) = upper(?)";
	private static String SQL_FINDALL = "select * from estados";



	public List<EstadoVO> findAll() throws DAOException {
		try {
			return getJdbcTemplate().query(SQL_FINDALL, new EstadoMapper());
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	public EstadoVO findById(EstadoVO estado) throws DAOException {
		try {
			return (EstadoVO) getJdbcTemplate().queryForObject(SQL_FIND_ID, new Object[]{estado.getIdEstado()}, new EstadoMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}	
	}

	public EstadoVO findByNombre(EstadoVO estado) throws DAOException {
		try {
			return (EstadoVO) getJdbcTemplate().queryForObject(SQL_FIND_NAME, new Object[]{estado.getNombre()}, new EstadoMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}	
	}

	public void insert(final EstadoVO estado) throws DAOException, DAOInsertException {
		try{
			KeyHolder kh = new GeneratedKeyHolder();
			getJdbcTemplate().update(new PreparedStatementCreator() {
				
				public PreparedStatement createPreparedStatement(Connection conn)
						throws SQLException {
					PreparedStatement ps = conn.prepareStatement(SQL_INSERT, new String[]{"id_estado"});
					ps.setString(1, estado.getNombre());
					return ps;
				}
			}
			,kh);
			estado.setIdEstado(kh.getKey().toString());
			
		} catch (Exception e) {
			throw new DAOInsertException(e);
		}	
	}

}
