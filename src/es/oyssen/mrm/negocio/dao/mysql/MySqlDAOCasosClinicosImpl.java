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
import es.oyssen.mrm.negocio.dao.DAOCasosClinicos;
import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOUpdateException;
import es.oyssen.mrm.negocio.dao.rowmappers.CasoClinicoMapper;
import es.oyssen.mrm.negocio.vo.CasoClinicoVO;


public class MySqlDAOCasosClinicosImpl extends DAOBase implements DAOCasosClinicos{
	
	private static String SQL_INSERT = "insert into casos_clinicos (id_portafolio, nombre, caso_clinico, fecha_subida) values (?,?,?,?)";
	private static String SQL_UPDATE = "update casos_clinicos set caso_clinico=?, nombre=?, fecha_subida=?";
	private static String SQL_DELETE = "delete from casos_clinicos where id_portafolio = ? and id_caso_clinico = ?";
	private static String SQL_FIND_BY_PORTAFOLIO = "select * from casos_clinicos where id_portafolio = ?";
	private static String SQL_FIND_BY_IDs = "select * from casos_clinicos where id_portafolio = ? and id_caso_clinico=?";


	
	
	

	public void update(CasoClinicoVO casoClinico) throws DAOException,
	DAOUpdateException {
		try {
			 
			String query = SQL_UPDATE;

			query += " where id_caso_clinico = ? and id_portafolio = ?";

			getJdbcTemplate().update(query, new Object[]{
					casoClinico.getCasoClinico(),
					casoClinico.getNombre(),
					casoClinico.getFechaSubida(),
					casoClinico.getIdCasoClinico(),
					casoClinico.getIdPortafolio()});
		} catch(Exception e) {
			throw new DAOUpdateException(e);
		}

	}

	public void delete(CasoClinicoVO casoClinico) throws DAOException,
	DAODeleteException {
		try {
			getJdbcTemplate().update(SQL_DELETE, new Object[]{casoClinico.getIdCasoClinico(),casoClinico.getIdPortafolio()});
		} catch (Exception e) {
			throw new DAODeleteException(e);
		}

	}


	public List<CasoClinicoVO> findAllByPortafolio(CasoClinicoVO casoClinico) throws DAOException {
		try {
			return getJdbcTemplate().query(SQL_FIND_BY_PORTAFOLIO, new Object[]{casoClinico.getIdPortafolio()}, new CasoClinicoMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	
	public void insert(final CasoClinicoVO caso) throws DAOException {
		try{
			KeyHolder kh = new GeneratedKeyHolder();
			getJdbcTemplate().update(new PreparedStatementCreator() {
				
				public PreparedStatement createPreparedStatement(Connection conn)
						throws SQLException {
					PreparedStatement ps = conn.prepareStatement(SQL_INSERT, new String[]{"id_caso_clinico"});
					ps.setString(1, caso.getIdPortafolio());
					ps.setString(2, caso.getNombre());
					
					InputStream datos = new ByteArrayInputStream(caso.getCasoClinico());
					try {
						ps.setBinaryStream(3, datos, datos.available());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ps.setString(4, caso.getFechaSubida());
					
					return ps;
				}
			}
			,kh);
			
			
		} catch (Exception e) {
			throw new DAOInsertException(e);
		}
		
	}



	@Override
	public CasoClinicoVO findByIDs(CasoClinicoVO caso) throws DAOException {
		try {
			return (CasoClinicoVO) getJdbcTemplate().queryForObject(SQL_FIND_BY_IDs, new Object[]{caso.getIdPortafolio(), caso.getIdCasoClinico()}, new CasoClinicoMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	

	

}


