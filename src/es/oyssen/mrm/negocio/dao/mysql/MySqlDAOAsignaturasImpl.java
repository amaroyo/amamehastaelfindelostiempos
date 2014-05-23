package es.oyssen.mrm.negocio.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import es.oyssen.mrm.negocio.dao.DAOAsignaturas;
import es.oyssen.mrm.negocio.dao.DAOBase;
import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOUpdateException;
import es.oyssen.mrm.negocio.dao.rowmappers.AsignaturaMapper;
import es.oyssen.mrm.negocio.vo.AsignaturaVO;


public class MySqlDAOAsignaturasImpl extends DAOBase implements DAOAsignaturas{
	
	private static String SQL_INSERT = "insert into asignaturas (nombre, codigo, curso, descripcion) values (?,?,?,?)";
	private static String SQL_UPDATE = "update asignaturas set nombre=?, codigo=?, curso=?, descripcion=?";
	private static String SQL_DELETE = "delete from asignaturas where id_asignatura = ?";
	private static String SQL_FIND_ALL = "select * from asignaturas";
	private static String SQL_FIND_ID = "select * from asignaturas where id_asignatura = ?";
	private static String SQL_FIND_BY_CURSO = "select * from asignaturas where curso = ?";
	private static String SQL_FIND_BY_CODIGO = "select * from asignaturas where codigo = ?";
	private static String SQL_FIND_BY_NOMBRE = "select * from asignaturas where nombre = ?";

	@Override
	public void insert(final AsignaturaVO asignatura) throws DAOException,
			DAOInsertException {
		try{
			KeyHolder kh = new GeneratedKeyHolder();
			getJdbcTemplate().update(new PreparedStatementCreator() {
				
				public PreparedStatement createPreparedStatement(Connection conn)
						throws SQLException {
					PreparedStatement ps = conn.prepareStatement(SQL_INSERT, new String[]{"id_asignatura"});
					ps.setString(1, asignatura.getNombre());
					ps.setString(2, asignatura.getCodigo());
					ps.setString(3, asignatura.getCurso());
					ps.setString(4, asignatura.getDescripcion());
					return ps;
					
				}
			}
			,kh);
			asignatura.setIdAsignatura(kh.getKey().toString());
			
		} catch (Exception e) {
			throw new DAOInsertException(e);
		}			
	}
	
	@Override
	public void update(AsignaturaVO asignatura) throws DAOException,
			DAOUpdateException {
		try {
			
			String query = SQL_UPDATE;

			
			query += " where id_asignatura = ?";
			
			getJdbcTemplate().update(query, new Object[]{
					asignatura.getNombre(),
					asignatura.getCodigo(),
					asignatura.getCurso(),
					asignatura.getDescripcion(),
					asignatura.getIdAsignatura()});
		} catch(Exception e) {
			throw new DAOUpdateException(e);
		}
		
	}
	
	@Override
	public void delete(AsignaturaVO asignatura) throws DAOException,
			DAODeleteException {
		try {
			getJdbcTemplate().update(SQL_DELETE, new Object[]{asignatura.getIdAsignatura()});
		} catch (Exception e) {
			throw new DAODeleteException(e);
		}
		
	}

	@Override
	public List<AsignaturaVO> findAll() throws DAOException {
		try {
			return getJdbcTemplate().query(SQL_FIND_ALL, new Object[]{}, new AsignaturaMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	@Override
	public List<AsignaturaVO> findByCurso(AsignaturaVO asignatura) throws DAOException {
		try {
			return getJdbcTemplate().query(SQL_FIND_BY_CURSO, new Object[]{asignatura.getCurso()}, new AsignaturaMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	public AsignaturaVO findById(AsignaturaVO asignatura) throws DAOException {
		try {
			return (AsignaturaVO) getJdbcTemplate().queryForObject(SQL_FIND_ID, new Object[]{asignatura.getIdAsignatura()}, new AsignaturaMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}


	@Override
	public List<AsignaturaVO> findByCodigo(AsignaturaVO asignatura) throws DAOException {
		try {
			return getJdbcTemplate().query(SQL_FIND_BY_CODIGO, new Object[]{asignatura.getCodigo()}, new AsignaturaMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	@Override
	public AsignaturaVO findByNombre(AsignaturaVO asignatura) throws DAOException {
		try {
			return (AsignaturaVO) getJdbcTemplate().queryForObject(SQL_FIND_BY_NOMBRE, new Object[]{asignatura.getNombre()}, new AsignaturaMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	
}


