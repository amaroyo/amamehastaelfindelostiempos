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
import es.oyssen.mrm.negocio.dao.DAODiariosReflexivos;
import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOUpdateException;
import es.oyssen.mrm.negocio.dao.rowmappers.CasoClinicoMapper;
import es.oyssen.mrm.negocio.dao.rowmappers.DiarioReflexivoMapper;
import es.oyssen.mrm.negocio.vo.CasoClinicoVO;
import es.oyssen.mrm.negocio.vo.DiarioReflexivoVO;


public class MySqlDAODiariosReflexivosImpl extends DAOBase implements DAODiariosReflexivos{

	private static String SQL_INSERT = "insert into diarios_reflexivos (id_portafolio, nombre, diario_reflexivo, fecha_subida) values (?,?,?,?)";
	private static String SQL_UPDATE = "update diarios_reflexivos set diario_reflexivo=?, nombre=?, fecha_subida=?";
	private static String SQL_DELETE = "delete from diarios_reflexivos where id_portafolio = ? and id_diario_reflexivo = ?";
	private static String SQL_FIND_BY_PORTAFOLIO = "select * from diarios_reflexivos where id_portafolio = ?";
	private static String SQL_FIND_BY_IDs = "select * from diarios_reflexivos where id_portafolio = ? and id_diario_reflexivo=?";



	public void insert(final DiarioReflexivoVO diarioReflexivo) throws DAOException,
	DAOInsertException {
		try{
			KeyHolder kh = new GeneratedKeyHolder();
			getJdbcTemplate().update(new PreparedStatementCreator() {
				
				public PreparedStatement createPreparedStatement(Connection conn)
						throws SQLException {
					PreparedStatement ps = conn.prepareStatement(SQL_INSERT, new String[]{"id_diario_reflexivo"});
					ps.setString(1, diarioReflexivo.getIdPortafolio());
					ps.setString(2, diarioReflexivo.getNombre());
					
					InputStream datos = new ByteArrayInputStream(diarioReflexivo.getDiarioReflexivo());
					try {
						ps.setBinaryStream(3, datos, datos.available());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ps.setString(4, diarioReflexivo.getFechaSubida());
					
					return ps;
				}
			}
			,kh);
			
			
		} catch (Exception e) {
			throw new DAOInsertException(e);
		}
		
	}

	public void update(DiarioReflexivoVO diarioReflexivo) throws DAOException,
	DAOUpdateException {
		try {
			 
			String query = SQL_UPDATE;

			query += " where id_diario_reflexivo = ? and id_portafolio = ?";

			getJdbcTemplate().update(query, new Object[]{
					diarioReflexivo.getDiarioReflexivo(),
					diarioReflexivo.getNombre(),
					diarioReflexivo.getFechaSubida(),
					diarioReflexivo.getIdDiarioReflexivo(),
					diarioReflexivo.getIdPortafolio()});
		} catch(Exception e) {
			throw new DAOUpdateException(e);
		}

	}

	public void delete(DiarioReflexivoVO diarioReflexivo) throws DAOException,
	DAODeleteException {
		try {
			getJdbcTemplate().update(SQL_DELETE, new Object[]{diarioReflexivo.getIdDiarioReflexivo(),diarioReflexivo.getIdPortafolio()});
		} catch (Exception e) {
			throw new DAODeleteException(e);
		}

	}


	public List<DiarioReflexivoVO> findAllByPortafolio(DiarioReflexivoVO diarioReflexivo) throws DAOException {
		try {
			return getJdbcTemplate().query(SQL_FIND_BY_PORTAFOLIO, new Object[]{diarioReflexivo.getIdPortafolio()}, new DiarioReflexivoMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	@Override
	public DiarioReflexivoVO findByIDs(DiarioReflexivoVO d) throws DAOException {
		try {
			return (DiarioReflexivoVO) getJdbcTemplate().queryForObject(SQL_FIND_BY_IDs, new Object[]{d.getIdPortafolio(), d.getIdDiarioReflexivo()}, new DiarioReflexivoMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
}


