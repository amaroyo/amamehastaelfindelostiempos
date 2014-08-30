package es.oyssen.mrm.negocio.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;

import es.oyssen.mrm.negocio.dao.DAOBase;
import es.oyssen.mrm.negocio.dao.DAOProfesoresAsociados;
import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOUpdateException;
import es.oyssen.mrm.negocio.dao.rowmappers.ProfesorAsociadoMapper;
import es.oyssen.mrm.negocio.vo.ProfesorAsociadoVO;


public class MySqlDAOProfesoresAsociadosImpl extends DAOBase implements DAOProfesoresAsociados{
	
	private static String SQL_INSERT = "insert into profesores_asociados (id_profesor, id_asignatura, centro_asociado, turno, anyo_academico) values (?,?,?,?,?)";
	private static String SQL_UPDATE = "update profesores_asociados set id_asignatura=?, centro_asociado=?, turno=?, anyo_academico=?";
	private static String SQL_UPDATE_TODO = "update profesores_asociados set centro_asociado=?, turno=?";
	private static String SQL_DELETE = "delete from profesores_asociados where id_profesor = ? and id_asignatura = ? and anyo_academico = ?";
	private static String SQL_FIND_ALL = "select * from profesores_asociados where anyo_academico =?";
	private static String SQL_FIND_BY_ID = "select * from profesores_asociados where id_profesor = ? and id_asignatura = ? and anyo_academico =?";
	private static String SQL_FIND_BY_PROFESOR = "select * from profesores_asociados where id_profesor = ? and anyo_academico =?";
	private static String SQL_FIND_BY_ASIGNATURA = "select * from profesores_asociados where id_asignatura = ? and anyo_academico =?";
	private static String SQL_FIND_BY_ANYO_ACADEMICO = "select * from profesores_asociados where anyo_academico =?";
	private static String SQL_FIND_BY_CENTRO_TURNO_ASIGNATURA = "select * from profesores_asociados where centro = ? and turno = ? and id_asignatura = ? and anyo_academico =?";

	
	public void insert(final ProfesorAsociadoVO profesor) throws DAOException,
			DAOInsertException {
		try{
			getJdbcTemplate().update(new PreparedStatementCreator() {
				
				public PreparedStatement createPreparedStatement(Connection conn)
						throws SQLException {
					PreparedStatement ps = conn.prepareStatement(SQL_INSERT, new String[]{});
					ps.setString(1, profesor.getIdProfesor());
					ps.setString(2, profesor.getIdAsignatura());
					ps.setString(3, profesor.getCentroAsociado());
					ps.setString(4, profesor.getTurno());
					ps.setString(5, profesor.getAnyoAcademico());
					return ps;
					
				}
			});
		} catch (Exception e) {
			throw new DAOInsertException(e);
		}			
	}
	
	public void update(ProfesorAsociadoVO profesor) throws DAOException,
			DAOUpdateException {
		try {
			
			String query = SQL_UPDATE;
			
			query += " where id_profesor = ?";
			
			getJdbcTemplate().update(query, new Object[]{					
					profesor.getCentroAsociado(),
					profesor.getTurno(),
					profesor.getIdAsignatura(),
					profesor.getAnyoAcademico(),
					profesor.getIdProfesor()});
		} catch(Exception e) {
			throw new DAOUpdateException(e);
		}
		
	}
	public void updateTODO(ProfesorAsociadoVO profesor) throws DAOException,
	DAOUpdateException {
		try {

			String query = SQL_UPDATE_TODO;

			
			query += " where id_asignatura =? and anyo_academico =? and id_profesor = ?";
			
			getJdbcTemplate().update(query, new Object[]{
					profesor.getIdAsignatura(),
					profesor.getCentroAsociado(),
					profesor.getTurno(),
					profesor.getAnyoAcademico(),
					profesor.getIdProfesor()});
		} catch(Exception e) {
			throw new DAOUpdateException(e);
		}

	}
	
	public void delete(ProfesorAsociadoVO profesor) throws DAOException,
			DAODeleteException {
		try {
			getJdbcTemplate().update(SQL_DELETE, new Object[]{profesor.getIdProfesor()});
		} catch (Exception e) {
			throw new DAODeleteException(e);
		}
		
	}

	public List<ProfesorAsociadoVO> findAll(ProfesorAsociadoVO profesor) throws DAOException {
		try {
			return getJdbcTemplate().query(SQL_FIND_ALL, new Object[]{profesor.getAnyoAcademico()}, new ProfesorAsociadoMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	
	public ProfesorAsociadoVO findById(ProfesorAsociadoVO profesor) throws DAOException {
		try {
			return
					(ProfesorAsociadoVO) getJdbcTemplate().queryForObject(SQL_FIND_BY_ID, new Object[]{
							profesor.getIdProfesor(),
							profesor.getIdAsignatura(),
							profesor.getAnyoAcademico()}, new ProfesorAsociadoMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	
	public List<ProfesorAsociadoVO> findByProfesor(ProfesorAsociadoVO profesor) throws DAOException {
		try {
			return getJdbcTemplate().query(SQL_FIND_BY_PROFESOR, new Object[]{
					profesor.getIdProfesor(),profesor.getAnyoAcademico()}, new ProfesorAsociadoMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	
	public List<ProfesorAsociadoVO> findByAsignatura(ProfesorAsociadoVO profesor) throws DAOException {
		try {
			return getJdbcTemplate().query(SQL_FIND_BY_ASIGNATURA, new Object[]{
					profesor.getIdAsignatura(),profesor.getAnyoAcademico()}, new ProfesorAsociadoMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	
	public List<ProfesorAsociadoVO> findByAnyoAcademico(ProfesorAsociadoVO profesor) throws DAOException {
		try {
			return getJdbcTemplate().query(SQL_FIND_BY_ANYO_ACADEMICO, new Object[]{
					profesor.getAnyoAcademico()}, new ProfesorAsociadoMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	@Override
	public List<ProfesorAsociadoVO> findByCentroTurnoAsignatura(ProfesorAsociadoVO profesor) throws DAOException {
		try {
			return getJdbcTemplate().query(SQL_FIND_BY_CENTRO_TURNO_ASIGNATURA, new Object[]{
					profesor.getCentroAsociado(),
					profesor.getTurno(),
					profesor.getIdAsignatura(),
					profesor.getAnyoAcademico()}, new ProfesorAsociadoMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	
	
}


