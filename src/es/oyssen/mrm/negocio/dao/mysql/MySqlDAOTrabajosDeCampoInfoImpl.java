package es.oyssen.mrm.negocio.dao.mysql;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import es.oyssen.mrm.negocio.dao.DAOBase;
import es.oyssen.mrm.negocio.dao.DAOAnexos;
import es.oyssen.mrm.negocio.dao.DAOTrabajosDeCampoInfo;
import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOUpdateException;
import es.oyssen.mrm.negocio.dao.rowmappers.AnexoMapper;
import es.oyssen.mrm.negocio.dao.rowmappers.RubricaMapper;
import es.oyssen.mrm.negocio.dao.rowmappers.TrabajoDeCampoInfoMapper;
import es.oyssen.mrm.negocio.dao.rowmappers.TrabajoDeCampoMapper;
import es.oyssen.mrm.negocio.vo.AnexoVO;
import es.oyssen.mrm.negocio.vo.RubricaVO;
import es.oyssen.mrm.negocio.vo.TrabajoDeCampoInfoVO;


public class MySqlDAOTrabajosDeCampoInfoImpl extends DAOBase implements DAOTrabajosDeCampoInfo{
	
	private static String SQL_INSERT = "insert into trabajos_de_campo_info (nombre, descripcion) values (?,?)";
	private static String SQL_UPDATE = "update trabajos_de_campo_info set nombre=?, enunciado=?, descripcion=?";
	private static String SQL_DELETE = "delete from trabajos_de_campo_info where id_asignatura = ? ";
	private static String SQL_FIND_BY_ID = "select * from trabajos_de_campo_info where id_trabajo_info = ?";
	


	public String insert(final TrabajoDeCampoInfoVO trabajo) throws DAOException,
	DAOInsertException {
		try{
			KeyHolder kh = new GeneratedKeyHolder();
			getJdbcTemplate().update(new PreparedStatementCreator() {
				
				public PreparedStatement createPreparedStatement(Connection conn)
						throws SQLException {
					PreparedStatement ps = conn.prepareStatement(SQL_INSERT, new String[]{"id_usuario"});
					ps.setString(1, trabajo.getNombre());
					ps.setString(2, trabajo.getDescripcion());
	
					return ps;
					
				}
			}
			,kh);
			return kh.getKey().toString();
			
		} catch (Exception e) {
			throw new DAOInsertException(e);
		}		
	}

	public void update(final TrabajoDeCampoInfoVO trabajo) throws DAOException,
	DAOUpdateException {
		try {
			 
			final String query = SQL_UPDATE + " where id_trabajo_info = ?";
			
			KeyHolder kh = new GeneratedKeyHolder();
			getJdbcTemplate().update(new PreparedStatementCreator() {
				
				public PreparedStatement createPreparedStatement(Connection conn)
						throws SQLException {
					PreparedStatement ps = conn.prepareStatement(query, new String[]{""});
					ps.setString(1, trabajo.getNombre());
					
					InputStream datos = new ByteArrayInputStream(trabajo.getEnunciado());
					try {
						ps.setBinaryStream(2, datos, datos.available());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					ps.setString(3, trabajo.getDescripcion());
					ps.setString(4, trabajo.getIdTrabajoInfo());
	
					return ps;
					
				}
			}
			,kh);
					
		} catch(Exception e) {
			throw new DAOUpdateException(e);
		}

	}

	public void delete(TrabajoDeCampoInfoVO trabajo) throws DAOException,
	DAODeleteException {
		try {
			getJdbcTemplate().update(SQL_DELETE, new Object[]{trabajo.getIdTrabajoInfo()});
		} catch (Exception e) {
			throw new DAODeleteException(e);
		}

	}

	@Override
	public TrabajoDeCampoInfoVO findById(TrabajoDeCampoInfoVO trabajo) throws DAOException {
		try {
			return (TrabajoDeCampoInfoVO) getJdbcTemplate().queryForObject(SQL_FIND_BY_ID, new Object[]{trabajo.getIdTrabajoInfo()}, new TrabajoDeCampoInfoMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	

}


