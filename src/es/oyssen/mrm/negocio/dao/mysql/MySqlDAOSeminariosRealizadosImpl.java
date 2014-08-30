package es.oyssen.mrm.negocio.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;

import es.oyssen.mrm.negocio.dao.DAOBase;
import es.oyssen.mrm.negocio.dao.DAOSeminariosRealizados;
import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOUpdateException;
import es.oyssen.mrm.negocio.dao.rowmappers.SeminarioAsignaturaAnyoMapper;
import es.oyssen.mrm.negocio.dao.rowmappers.SeminarioAsignaturaMapper;
import es.oyssen.mrm.negocio.dao.rowmappers.SeminarioRealizadoMapper;
import es.oyssen.mrm.negocio.dao.rowmappers.UsuarioAnyoSeminarioMapper;
import es.oyssen.mrm.negocio.vo.PortafolioVO;
import es.oyssen.mrm.negocio.vo.SeminarioAsignaturaAnyoVO;
import es.oyssen.mrm.negocio.vo.SeminarioAsignaturaVO;
import es.oyssen.mrm.negocio.vo.SeminarioRealizadoVO;
import es.oyssen.mrm.negocio.vo.UsuarioAnyoSeminarioVO;


public class MySqlDAOSeminariosRealizadosImpl extends DAOBase implements DAOSeminariosRealizados{

	private static String SQL_INSERT = "insert into seminarios_realizados (id_portafolio,id_seminario) values (?,?)";
	private static String SQL_UPDATE = "";
	private static String SQL_DELETE = "delete from seminarios_realizados where id_portafolio = ? and id_seminario = ?";
	private static String SQL_FIND_BY_PORTAFOLIO = "select * from seminarios_realizados where id_portafolio = ?";
	private static String SQL_FIND_USERS_BY_PORTAFOLIO = "select u.id_usuario, u.correo, u.nombre, u.apellido1, u.apellido2, u.dni, u.telefono, p.anyo_academico " +
															"from usuarios as u, portafolios as p, seminarios_realizados as s "+
															"where u.id_usuario=p.id_alumno and p.id_portafolio=s.id_portafolio and s.id_seminario=? and "+
															"p.id_alumno in (select pe.id_alumno from portafolios as pe "+
															"where pe.id_asignatura=p.id_asignatura and pe.anyo_academico=?)";


	private static String SQL_FIND_SEMINARIOS_REALIZADOS = "select sa.id_seminario, sa.nombre, sa.codigo, p.anyo_academico " +
															"from seminarios_asignaturas as sa, portafolios as p, seminarios_realizados as sr " +
															"where p.id_portafolio = sr.id_portafolio and sr.id_seminario = sa.id_seminario " +
															"and p.id_alumno=? and p.id_asignatura=?";
	
	private static String SQL_FIND_SEMINARIOS_PENDIENTES = "select * " +
															"from seminarios_asignaturas as sa " +
															"where sa.id_asignatura =? " + 
															"and sa.id_seminario not in ( " +
															"select sa.id_seminario " +
															"from seminarios_asignaturas as sa, portafolios as p, seminarios_realizados as sr " +
															"where p.id_portafolio = sr.id_portafolio and sr.id_seminario = sa.id_seminario " +
															"and p.id_alumno =? and p.id_asignatura =? )";
	

	public void insert(final SeminarioRealizadoVO seminarioRealizado) throws DAOException,
	DAOInsertException {
		try{
			
			getJdbcTemplate().update(new PreparedStatementCreator() {

				public PreparedStatement createPreparedStatement(Connection conn)
						throws SQLException {
					PreparedStatement ps = conn.prepareStatement(SQL_INSERT, new String[]{});
					ps.setString(1, seminarioRealizado.getIdPortafolio());
					ps.setString(2, seminarioRealizado.getIdSeminario());
					return ps;

				}
			}
			);
			

		} catch (Exception e) {
			throw new DAOInsertException(e);
		}			
	}

	public void update(SeminarioRealizadoVO seminarioRealizado) throws DAOException,
	DAOUpdateException {
		try {
			 
			String query = SQL_UPDATE;

		} catch(Exception e) {
			throw new DAOUpdateException(e);
		}

	}

	public void delete(SeminarioRealizadoVO seminarioRealizado) throws DAOException,
	DAODeleteException {
		try {
			getJdbcTemplate().update(SQL_DELETE, new Object[]{seminarioRealizado.getIdSeminario(),seminarioRealizado.getIdPortafolio()});
		} catch (Exception e) {
			throw new DAODeleteException(e);
		}

	}


	public List<SeminarioRealizadoVO> findAllByPortafolio(SeminarioRealizadoVO seminarioRealizado) throws DAOException {
		try {
			return getJdbcTemplate().query(SQL_FIND_BY_PORTAFOLIO, new Object[]{seminarioRealizado.getIdPortafolio()}, new SeminarioRealizadoMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	
	public List<UsuarioAnyoSeminarioVO> findAllUsersByPortafolio(SeminarioRealizadoVO seminarioRealizado, PortafolioVO p) throws DAOException {
		try {
			return getJdbcTemplate().query(SQL_FIND_USERS_BY_PORTAFOLIO, new Object[]{seminarioRealizado.getIdSeminario(),p.getAnyoAcademico()}, new UsuarioAnyoSeminarioMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	@Override
	public List<SeminarioAsignaturaAnyoVO> findSeminariosRealizados(PortafolioVO p) throws DAOException {
		try {
			return getJdbcTemplate().query(SQL_FIND_SEMINARIOS_REALIZADOS, new Object[]{p.getIdAlumno(), p.getIdAsignatura()}, new SeminarioAsignaturaAnyoMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	@Override
	public List<SeminarioAsignaturaVO> findSeminariosPendientes(PortafolioVO p)throws DAOException {
		try {
			return getJdbcTemplate().query(SQL_FIND_SEMINARIOS_PENDIENTES, new Object[]{p.getIdAsignatura(), p.getIdAlumno(), p.getIdAsignatura()}, new SeminarioAsignaturaMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}



}


