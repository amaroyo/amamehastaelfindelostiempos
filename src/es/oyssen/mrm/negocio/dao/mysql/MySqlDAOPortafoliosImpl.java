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
import es.oyssen.mrm.negocio.dao.DAOPortafolios;
import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOUpdateException;
import es.oyssen.mrm.negocio.dao.rowmappers.PortafolioMapper;
import es.oyssen.mrm.negocio.vo.PortafolioVO;


public class MySqlDAOPortafoliosImpl extends DAOBase implements DAOPortafolios{
	
	private static String SQL_INSERT = "insert into portafolios (id_alumno, id_asignatura, anyo_academico) values (?,?,?)";
	private static String SQL_UPDATE = "";
	private static String SQL_DELETE = "delete from portafolios where id_portafolio = ?";
	private static String SQL_FIND_ALL = "select * from portafolios where anyo_academico = ?";
	private static String SQL_FIND_BY_ID = "select * from portafolios where id_portafolio = ?";
	private static String SQL_FIND_BY_ALUMNO = "select * from portafolios where id_alumno = ? and anyo_academico = ?";
	private static String SQL_FIND_BY_ASIGNATURA = "select * from portafolios where id_asignatura = ? and anyo_academico = ?";
	private static String SQL_FIND_BY_ANYO_ACADEMICO = "select * from portafolios where anyo_academico = ?";
	


	public List<PortafolioVO> findAll(PortafolioVO portafolio) throws DAOException {
		try {
			return getJdbcTemplate().query(SQL_FIND_ALL, new Object[]{portafolio.getAnyoAcademico()}, new PortafolioMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}


	@Override
	public PortafolioVO findById(PortafolioVO portafolio) throws DAOException {
		try {
			return (PortafolioVO) getJdbcTemplate().queryForObject(SQL_FIND_BY_ID, new Object[]{portafolio.getIdPortafolio()}, new PortafolioMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	@Override
	public List<PortafolioVO> findByAlumno(PortafolioVO portafolio) throws DAOException {
		try {
			return getJdbcTemplate().query(SQL_FIND_BY_ALUMNO, new Object[]{portafolio.getIdAlumno(), portafolio.getAnyoAcademico()}, new PortafolioMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	@Override
	public List<PortafolioVO> findByAsignatura(PortafolioVO portafolio) throws DAOException {
		try {
			return getJdbcTemplate().query(SQL_FIND_BY_ASIGNATURA, new Object[]{portafolio.getIdAsignatura(), portafolio.getAnyoAcademico()}, new PortafolioMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	@Override
	public List<PortafolioVO> findByAnyoAcademico(PortafolioVO portafolio) throws DAOException {
		try {
			return getJdbcTemplate().query(SQL_FIND_BY_ANYO_ACADEMICO, new Object[]{portafolio.getAnyoAcademico()}, new PortafolioMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void insert(final PortafolioVO portafolio) throws DAOException, DAOInsertException {
		try{
			KeyHolder kh = new GeneratedKeyHolder();
			getJdbcTemplate().update(new PreparedStatementCreator() {
				
				public PreparedStatement createPreparedStatement(Connection conn)
						throws SQLException {
					PreparedStatement ps = conn.prepareStatement(SQL_INSERT, new String[]{"id_portafolio"});
					ps.setString(1, portafolio.getIdAlumno());
					ps.setString(2, portafolio.getIdAsignatura());
					ps.setString(3, portafolio.getAnyoAcademico());
					return ps;
					
				}
			}
			,kh);
			portafolio.setIdPortafolio(kh.getKey().toString());
			
		} catch (Exception e) {
			throw new DAOInsertException(e);
		}
		
	}

	@Override
	public void update(PortafolioVO portafolio) throws DAOException, DAOUpdateException {
		try {
			
			String query = SQL_UPDATE;
			
		} catch(Exception e) {
			throw new DAOUpdateException(e);
		}
		
	}

	@Override
	public void delete(PortafolioVO portafolio) throws DAOException, DAODeleteException {
		try {
			getJdbcTemplate().update(SQL_DELETE, new Object[]{portafolio.getIdPortafolio()});
		} catch (Exception e) {
			throw new DAODeleteException(e);
		}
		
	}
	
}


