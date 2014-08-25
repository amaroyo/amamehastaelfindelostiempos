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
import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOUpdateException;
import es.oyssen.mrm.negocio.dao.rowmappers.AnexoMapper;
import es.oyssen.mrm.negocio.vo.AnexoVO;


public class MySqlDAOAnexosImpl extends DAOBase implements DAOAnexos{

	private static String SQL_INSERT = "insert into anexos (id_portafolio, nombre, anexo,fecha_subida) values (?,?,?,?)";
	private static String SQL_UPDATE = "update anexos set anexo=?, nombre =?, fecha_subida=?";
	private static String SQL_DELETE = "delete from anexos where id_portafolio = ? and id_anexo = ?";
	private static String SQL_FIND_BY_PORTAFOLIO = "select * from anexos where id_portafolio = ?";



	public void insert(final AnexoVO anexo) throws DAOException,
	DAOInsertException {
		try{
			KeyHolder kh = new GeneratedKeyHolder();
			getJdbcTemplate().update(new PreparedStatementCreator() {

				public PreparedStatement createPreparedStatement(Connection conn)
						throws SQLException {
					PreparedStatement ps = conn.prepareStatement(SQL_INSERT, new String[]{"id_anexo"});
					ps.setString(1, anexo.getIdPortafolio());
					ps.setString(2, anexo.getNombre());
					InputStream datos = new ByteArrayInputStream(anexo.getAnexo());
					try {
						ps.setBinaryStream(3, datos, datos.available());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ps.setString(4, anexo.getFechaSubida());
					return ps;

				}
			}
			,kh);
			anexo.setIdAnexo(kh.getKey().toString());

		} catch (Exception e) {
			throw new DAOInsertException(e);
		}			
	}

	public void update(AnexoVO anexo) throws DAOException,
	DAOUpdateException {
		try {
			 
			String query = SQL_UPDATE;

			query += " where id_anexo = ? and id_portafolio = ?";

			getJdbcTemplate().update(query, new Object[]{
					anexo.getAnexo(),
					anexo.getNombre(),
					anexo.getFechaSubida(),
					anexo.getIdAnexo(),
					anexo.getIdPortafolio()});
		} catch(Exception e) {
			throw new DAOUpdateException(e);
		}

	}

	public void delete(AnexoVO anexo) throws DAOException,
	DAODeleteException {
		try {
			getJdbcTemplate().update(SQL_DELETE, new Object[]{anexo.getIdAnexo(),anexo.getIdPortafolio()});
		} catch (Exception e) {
			throw new DAODeleteException(e);
		}

	}


	public List<AnexoVO> findAllByPortafolio(AnexoVO anexo) throws DAOException {
		try {
			return getJdbcTemplate().query(SQL_FIND_BY_PORTAFOLIO, new Object[]{anexo.getIdPortafolio()}, new AnexoMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

}


