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
import es.oyssen.mrm.negocio.dao.DAOEstanciasUnidadClinica;
import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOUpdateException;
import es.oyssen.mrm.negocio.dao.rowmappers.EstanciaUnidadClinicaMapper;
import es.oyssen.mrm.negocio.vo.EstanciaUnidadClinicaVO;


public class MySqlDAOEstanciasUnidadClinicaImpl extends DAOBase implements DAOEstanciasUnidadClinica{
	
	private static String SQL_INSERT = "insert into estancias_unidad_clinica (id_portafolio, centro_asociado, unidad_clinica, turno, fecha_inicio, fecha_fin) values (?,?,?,?,?,?)";
	private static String SQL_UPDATE = "update estancias_unidad_clinica set turno=?, fecha_inicio=?, fecha_fin=?";
	private static String SQL_DELETE = "delete from estancias_unidad_clinica where id_estancia_unidad = ? and id_portafolio = ?";
	private static String SQL_FIND_BY_PORTAFOLIO = "select * from estancias_unidad_clinica where id_portafolio = ?";
	private static String SQL_FIND_BY_CENTRO_TURNO = "select * from estancias_unidad_clinica where centro_asociado = ? and turno = ?";

	
	public void insert(final EstanciaUnidadClinicaVO estanciaUnidadClinica) throws DAOException,
			DAOInsertException {
		try{
			KeyHolder kh = new GeneratedKeyHolder();
			getJdbcTemplate().update(new PreparedStatementCreator() {
				
				public PreparedStatement createPreparedStatement(Connection conn)
						throws SQLException {
					PreparedStatement ps = conn.prepareStatement(SQL_INSERT, new String[]{"id_estancia_unidad"});
					ps.setString(1, estanciaUnidadClinica.getIdPortafolio());
					ps.setString(2, estanciaUnidadClinica.getCentroAsociado());
					ps.setString(3, estanciaUnidadClinica.getUnidadClinica());
					ps.setString(4, estanciaUnidadClinica.getTurno());
					ps.setString(5, estanciaUnidadClinica.getFechaInicio());
					ps.setString(6, estanciaUnidadClinica.getFechaFin());
					return ps;
					
				}
			}
			,kh);
			estanciaUnidadClinica.setIdEstanciaUnidad(kh.getKey().toString());
			
		} catch (Exception e) {
			throw new DAOInsertException(e);
		}			
	}
	
	public void update(EstanciaUnidadClinicaVO estanciaUnidadClinica) throws DAOException,
			DAOUpdateException {
		try {
			
			String query = SQL_UPDATE;
			
			query += " where id_estancia_unidad = ? and id_portafolio = ?";
			
			getJdbcTemplate().update(query, new Object[]{
					estanciaUnidadClinica.getTurno(),
					estanciaUnidadClinica.getFechaInicio(),
					estanciaUnidadClinica.getFechaFin(),
					estanciaUnidadClinica.getIdEstanciaUnidad(),
					estanciaUnidadClinica.getIdPortafolio()});
		} catch(Exception e) {
			throw new DAOUpdateException(e);
		}
		
	}
	
	public void delete(EstanciaUnidadClinicaVO estanciaUnidadClinica) throws DAOException,
			DAODeleteException {
		try {
			getJdbcTemplate().update(SQL_DELETE, new Object[]{estanciaUnidadClinica.getIdEstanciaUnidad(),estanciaUnidadClinica.getIdPortafolio()});
		} catch (Exception e) {
			throw new DAODeleteException(e);
		}
		
	}

	
	public List<EstanciaUnidadClinicaVO> findAllByPortafolio(EstanciaUnidadClinicaVO estanciaUnidadClinica) throws DAOException {
		try {
			return getJdbcTemplate().query(SQL_FIND_BY_PORTAFOLIO, new Object[]{estanciaUnidadClinica.getIdPortafolio()}, new EstanciaUnidadClinicaMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	public List<EstanciaUnidadClinicaVO> findByCentroTurno(EstanciaUnidadClinicaVO estanciaUnidadClinica) throws DAOException {
		try {
			return getJdbcTemplate().query(SQL_FIND_BY_CENTRO_TURNO, new Object[]{estanciaUnidadClinica.getCentroAsociado(),estanciaUnidadClinica.getTurno()}, new EstanciaUnidadClinicaMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	
	
}


