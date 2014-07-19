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
import es.oyssen.mrm.negocio.dao.DAOSeminariosAsignatura;
import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOUpdateException;
import es.oyssen.mrm.negocio.dao.rowmappers.SeminarioAsignaturaMapper;
import es.oyssen.mrm.negocio.vo.SeminarioAsignaturaVO;


public class MySqlDAOSeminariosAsignaturaImpl extends DAOBase implements DAOSeminariosAsignatura{

	private static String SQL_INSERT = "insert into seminarios_asignaturas (id_asignatura, nombre, codigo, descripcion) values (?,?,?,?)";
	private static String SQL_UPDATE = "update seminarios_asignaturas set descripcion=?";
	private static String SQL_DELETE = "delete from seminarios_asignaturas where id_seminario = ? ";
	private static String SQL_FIND_BY_ID = "select * from seminarios_asignaturas where id_seminario = ?";
	private static String SQL_FIND_BY_NOMBRE = "select * from seminarios_asignaturas where nombre = ?";
	private static String SQL_FIND_BY_CODIGO = "select * from seminarios_asignaturas where codigo = ?";
	private static String SQL_FIND_BY_ASIGNATURA = "select * from seminarios_asignaturas where id_asignatura = ?";



	public void insert(final SeminarioAsignaturaVO seminarioAsignatura) throws DAOException,
	DAOInsertException {
		try{
			KeyHolder kh = new GeneratedKeyHolder();
			getJdbcTemplate().update(new PreparedStatementCreator() {
				
				public PreparedStatement createPreparedStatement(Connection conn)
						throws SQLException {
					PreparedStatement ps = conn.prepareStatement(SQL_INSERT, new String[]{"id_seminario"});
					ps.setString(1, seminarioAsignatura.getIdAsignatura());
					ps.setString(2, seminarioAsignatura.getNombre());
					ps.setString(3, seminarioAsignatura.getCodigo());
					ps.setString(4, seminarioAsignatura.getDescripcion());
					return ps;
					
				}
			}
			,kh);
			seminarioAsignatura.setIdSeminario(kh.getKey().toString());
			
		} catch (Exception e) {
			throw new DAOInsertException(e);
		}	
	}

	public void update(SeminarioAsignaturaVO seminarioAsignatura) throws DAOException,
	DAOUpdateException {
		try {
			 
			String query = SQL_UPDATE;

			query += " where id_seminario = ?";

			getJdbcTemplate().update(query, new Object[]{
					seminarioAsignatura.getDescripcion(),
					seminarioAsignatura.getIdSeminario()});
		} catch(Exception e) {
			throw new DAOUpdateException(e);
		}

	}

	public void delete(SeminarioAsignaturaVO seminarioAsignatura) throws DAOException,
	DAODeleteException {
		try {
			getJdbcTemplate().update(SQL_DELETE, new Object[]{seminarioAsignatura.getIdSeminario()});
		} catch (Exception e) {
			throw new DAODeleteException(e);
		}

	}

	@Override
	public SeminarioAsignaturaVO findById(SeminarioAsignaturaVO seminarioAsignatura) throws DAOException {
		try {
			return (SeminarioAsignaturaVO) getJdbcTemplate().queryForObject(SQL_FIND_BY_ID, new Object[]{seminarioAsignatura.getIdSeminario()}, new SeminarioAsignaturaMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	public List<SeminarioAsignaturaVO> findByNombre(SeminarioAsignaturaVO seminarioAsignatura) throws DAOException {
		try {
			return getJdbcTemplate().query(SQL_FIND_BY_NOMBRE, new Object[]{seminarioAsignatura.getNombre()}, new SeminarioAsignaturaMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	public List<SeminarioAsignaturaVO> findByCodigo(SeminarioAsignaturaVO seminarioAsignatura) throws DAOException {
		try {
			return getJdbcTemplate().query(SQL_FIND_BY_CODIGO, new Object[]{seminarioAsignatura.getCodigo()}, new SeminarioAsignaturaMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	
	public List<SeminarioAsignaturaVO> findAllByAsignatura(SeminarioAsignaturaVO seminarioAsignatura) throws DAOException {
		try {
			return getJdbcTemplate().query(SQL_FIND_BY_ASIGNATURA, new Object[]{seminarioAsignatura.getIdAsignatura()}, new SeminarioAsignaturaMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

}


