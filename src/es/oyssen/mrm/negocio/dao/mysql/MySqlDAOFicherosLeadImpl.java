package es.oyssen.mrm.negocio.dao.mysql;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import es.oyssen.mrm.negocio.dao.DAOBase;
import es.oyssen.mrm.negocio.dao.DAOFicherosLead;
import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOUpdateException;
import es.oyssen.mrm.negocio.dao.rowmappers.FicheroLeadMapper;
import es.oyssen.mrm.negocio.vo.CriterioFicheroVO;
import es.oyssen.mrm.negocio.vo.FicheroVO;
import es.oyssen.mrm.negocio.vo.ServicioVO;
import es.oyssen.mrm.util.StringUtil;


public class MySqlDAOFicherosLeadImpl extends DAOBase implements DAOFicherosLead{
	
	private static String SQL_INSERT = "insert into ficheros_leads (id_lead, nombre, tipoContenido, datos, descripcion) values (?,?,?,?,?)";
	private static String SQL_UPDATE = "update ficheros_leads set id_lead=?, nombre=?, tipoContenido=?, datos=? where id_fichero = ?";
	private static String SQL_DELETE = "delete from ficheros_leads where id_fichero = ?";
	private static String SQL_DELETE_BY_SERVICIO = "delete from ficheros_leads where id_servicio = ?";
	private static String SQL_FIND_ID = "select * from ficheros_leads where id_fichero = ?";
	//private static String SQL_FIND_CRITERIO = "select * from ficheros_leads";
	private static String SQL_FIND_CRITERIO = "select id_fichero, nombre, descripcion, fechaUltimaDescarga from ficheros_leads";
	private static String SQL_UPDATE_FECHA_ULTIMA_DESCARGA = "update ficheros_leads set fechaUltimaDescarga=? where id_fichero = ?";
	
	
	public void insert(final FicheroVO fichero) throws DAOException,
			DAOInsertException {
		try{
			KeyHolder kh = new GeneratedKeyHolder();
			getJdbcTemplate().update(new PreparedStatementCreator() {
				
				public PreparedStatement createPreparedStatement(Connection conn)
						throws SQLException {
					PreparedStatement ps = conn.prepareStatement(SQL_INSERT, new String[]{"id_fichero"});
					ps.setString(1, fichero.getIdLead());
					ps.setString(2, fichero.getNombre());
					ps.setString(3, fichero.getTipoContenido());
					
					InputStream datos = new ByteArrayInputStream(fichero.getDatos());
					try {
						ps.setBinaryStream(4, datos, datos.available());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ps.setString(5, fichero.getDescripcion());
					
					return ps;
				}
			}
			,kh);
			fichero.setIdFichero(kh.getKey().toString());
			
		} catch (Exception e) {
			throw new DAOInsertException(e);
		}			
	}
	
	public void update(FicheroVO fichero) throws DAOException,
			DAOUpdateException {
		try {
			getJdbcTemplate().update(SQL_UPDATE, new Object[]{
					fichero.getIdLead(),
					fichero.getNombre(),
					fichero.getTipoContenido(),
					fichero.getDatos(),
					fichero.getIdFichero()});
		} catch(Exception e) {
			throw new DAOUpdateException(e);
		}
		
	}
	
	public void delete(FicheroVO fichero) throws DAOException,
			DAODeleteException {
		try {
			getJdbcTemplate().update(SQL_DELETE, new Object[]{fichero.getIdFichero()});
		} catch (Exception e) {
			throw new DAODeleteException(e);
		}
		
	}

	public void deleteByServicio(ServicioVO servicio) throws DAOException,
			DAODeleteException {
		try {
			getJdbcTemplate().update(SQL_DELETE_BY_SERVICIO, new Object[]{servicio.getIdServicio()});
		} catch (Exception e) {
			throw new DAODeleteException(e);
		}

	}	
	
	public List<FicheroVO> findByCriterio(CriterioFicheroVO criterio)
			throws DAOException {
		try {
			StringBuffer sb = new StringBuffer(SQL_FIND_CRITERIO);
			String cadena = " where ";
			ArrayList params = new ArrayList();
			if (!StringUtil.isNullOrBlank(criterio.getIdLead())) {
				sb.append(cadena + "id_lead = " + criterio.getIdLead());
				cadena = " and ";
			}
			if (!StringUtil.isNullOrBlank(criterio.getNombre())) {
				sb.append(cadena + "upper(nombre) = (?)");
				cadena = " and ";
				params.add("%" + criterio.getNombre() + "%");
			}			
			if (!StringUtil.isNullOrBlank(criterio.getTipoContenido())) {
				sb.append(cadena + "upper(tipoContenido) = (?)");
				cadena = " and ";
				params.add("%" + criterio.getTipoContenido() + "%");
			}
			return getJdbcTemplate().query(sb.toString(), params.toArray(), new FicheroLeadMapper());
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	public FicheroVO findById(FicheroVO fichero) throws DAOException {
		try {
			return (FicheroVO) getJdbcTemplate().queryForObject(SQL_FIND_ID, new Object[]{fichero.getIdFichero()}, new FicheroLeadMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	public void updateFechaUltimaDescarga(FicheroVO fichero) throws DAOException, DAOUpdateException {
		try {
			java.util.Date utilDate = new java.util.Date();
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			getJdbcTemplate().update(SQL_UPDATE_FECHA_ULTIMA_DESCARGA, new Object[]{sqlDate, fichero.getIdFichero()});
		} catch(Exception e) {
			throw new DAOUpdateException(e);
		}
	}
	
}


