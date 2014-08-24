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
import es.oyssen.mrm.negocio.dao.DAOPuntuacionCriterios;
import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOUpdateException;
import es.oyssen.mrm.negocio.dao.rowmappers.PuntuacionCriterioMapper;
import es.oyssen.mrm.negocio.vo.PortafolioVO;
import es.oyssen.mrm.negocio.vo.PuntuacionCriterioVO;


public class MySqlDAOPuntuacionCriteriosImpl extends DAOBase implements DAOPuntuacionCriterios{

	private static String SQL_INSERT = "insert into puntuacion_criterios (id_portafolio, id_criterio, nota) values (?,?,?)";
	private static String SQL_UPDATE = "update puntuacion_criterios set nota=?";
	private static String SQL_INSERT_ON_DUPLICATE_KEY_UPDATE = "insert into puntuacion_criterios (id_portafolio, id_criterio, nota) values (?,?,?)" +
																"on duplicate key update nota = ?";
	private static String SQL_DELETE = "delete from puntuacion_criterios where id_portafolio = ? and id_criterio = ?";
	private static String SQL_FIND_BY_PORTAFOLIO = "select * from puntuacion_criterios where id_portafolio = ?";
	private static String SQL_ALL_NOTAS_BY_PORTAFOLIO = "select pc.* "+
														"from puntuacion_criterios as pc, grupos_criterios_rubricas as gcr, criterios_rubricas as cr "+
														"where gcr.tipo = 'NOTA' and gcr.id_grupo_criterio = cr.id_grupo_criterio "+
														"and pc.id_criterio = cr.id_criterio and pc.id_portafolio = ?";


	public void insert(final PuntuacionCriterioVO puntuacionCriterio) throws DAOException,
	DAOInsertException {
		try{
			getJdbcTemplate().update(new PreparedStatementCreator() {

				public PreparedStatement createPreparedStatement(Connection conn)
						throws SQLException {
					PreparedStatement ps = conn.prepareStatement(SQL_INSERT, new String[]{});
					ps.setString(1, puntuacionCriterio.getIdPortafolio());
					ps.setString(2, puntuacionCriterio.getIdCriterio());
					ps.setString(3, puntuacionCriterio.getNota());
					return ps;

				}
			}
			);

		} catch (Exception e) {
			throw new DAOInsertException(e);
		}			
	}
	
	public void insertOnDuplicateKeyUpdate(final PuntuacionCriterioVO puntuacionCriterio) throws DAOException,
	DAOInsertException {
		try{
			getJdbcTemplate().update(new PreparedStatementCreator() {

				public PreparedStatement createPreparedStatement(Connection conn)
						throws SQLException {
					PreparedStatement ps = conn.prepareStatement(SQL_INSERT_ON_DUPLICATE_KEY_UPDATE, new String[]{});
					ps.setString(1, puntuacionCriterio.getIdPortafolio());
					ps.setString(2, puntuacionCriterio.getIdCriterio());
					ps.setString(3, puntuacionCriterio.getNota());
					ps.setString(4, puntuacionCriterio.getNota());
					return ps;

				}
			}
			);

		} catch (Exception e) {
			throw new DAOInsertException(e);
		}			
	}

	public void update(PuntuacionCriterioVO puntuacionCriterio) throws DAOException,
	DAOUpdateException {
		try {
			 
			String query = SQL_UPDATE;

			query += " where id_criterio = ? and id_portafolio = ?";

			getJdbcTemplate().update(query, new Object[]{
					puntuacionCriterio.getNota(),
					puntuacionCriterio.getIdCriterio(),
					puntuacionCriterio.getIdPortafolio()});
		} catch(Exception e) {
			throw new DAOUpdateException(e);
		}

	}

	public void delete(PuntuacionCriterioVO puntuacionCriterio) throws DAOException,
	DAODeleteException {
		try {
			getJdbcTemplate().update(SQL_DELETE, new Object[]{puntuacionCriterio.getIdCriterio(),puntuacionCriterio.getIdPortafolio()});
		} catch (Exception e) {
			throw new DAODeleteException(e);
		}

	}


	public List<PuntuacionCriterioVO> findAllByPortafolio(PuntuacionCriterioVO puntuacionCriterio) throws DAOException {
		try {
			return getJdbcTemplate().query(SQL_FIND_BY_PORTAFOLIO, new Object[]{puntuacionCriterio.getIdPortafolio()}, new PuntuacionCriterioMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	@Override
	public List<PuntuacionCriterioVO> findAllNotasByPortafolio(PortafolioVO p)
			throws DAOException {
		try {
			return getJdbcTemplate().query(SQL_ALL_NOTAS_BY_PORTAFOLIO, new Object[]{p.getIdPortafolio()}, new PuntuacionCriterioMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

}


