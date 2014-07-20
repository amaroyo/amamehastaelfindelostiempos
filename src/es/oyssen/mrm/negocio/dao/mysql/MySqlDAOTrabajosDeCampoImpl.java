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
import es.oyssen.mrm.negocio.dao.DAOTrabajosDeCampo;
import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOUpdateException;
import es.oyssen.mrm.negocio.dao.rowmappers.TrabajoDeCampoMapper;
import es.oyssen.mrm.negocio.vo.PortafolioVO;
import es.oyssen.mrm.negocio.vo.TrabajoDeCampoVO;


public class MySqlDAOTrabajosDeCampoImpl extends DAOBase implements DAOTrabajosDeCampo{

	private static String SQL_INSERT = "insert into trabajos_de_campo (id_portafolio, nombre, trabajo_de_campo, correccion_trabajo, fecha_limite) values (?,?,?,?,?)";
	private static String SQL_UPDATE = "update usuarios set nombte=?, trabajo_de_campo=?, correccion_trabajo=?, fecha_limite=?";
	private static String SQL_DELETE = "delete from trabajos_de_campo where id_portafolio = ? and id_trabajo_de_campo = ?";
	private static String SQL_FIND_BY_PORTAFOLIO = "select * from trabajos_de_campo where id_portafolio = ?";
	private static String SQL_FIND_BY_ASIGNATURA = "select * from trabajos_de_campo as t, portafolios as p where p.id_portafolio = t.id_portafolio and p.id_asignatura =? and p.anyo_academico =?";



	public void insert(final TrabajoDeCampoVO trabajoDeCampo) throws DAOException,
	DAOInsertException {
		try{
			KeyHolder kh = new GeneratedKeyHolder();
			getJdbcTemplate().update(new PreparedStatementCreator() {

				public PreparedStatement createPreparedStatement(Connection conn)
						throws SQLException {
					PreparedStatement ps = conn.prepareStatement(SQL_INSERT, new String[]{"id_trabajo_de_campo"});
					ps.setString(1, trabajoDeCampo.getIdPortafolio());
					ps.setString(2, trabajoDeCampo.getNombre());
					ps.setString(3, trabajoDeCampo.getTrabajoDeCampo());
					ps.setString(4, trabajoDeCampo.getCorreccionTrabajo());
					ps.setString(5, trabajoDeCampo.getFechaLimite());
					return ps;

				}
			}
			,kh);
			trabajoDeCampo.setIdTrabajoDeCampo(kh.getKey().toString());

		} catch (Exception e) {
			throw new DAOInsertException(e);
		}			
	}

	public void update(TrabajoDeCampoVO trabajoDeCampo) throws DAOException,
	DAOUpdateException {
		try {
			 
			String query = SQL_UPDATE;

			query += " where id_trabajo_de_campo = ? and id_portafolio = ?";

			getJdbcTemplate().update(query, new Object[]{
					trabajoDeCampo.getNombre(),
					trabajoDeCampo.getTrabajoDeCampo(),
					trabajoDeCampo.getCorreccionTrabajo(),
					trabajoDeCampo.getFechaLimite(),
					trabajoDeCampo.getIdTrabajoDeCampo(),
					trabajoDeCampo.getIdPortafolio()});
		} catch(Exception e) {
			throw new DAOUpdateException(e);
		}

	}

	public void delete(TrabajoDeCampoVO trabajoDeCampo) throws DAOException,
	DAODeleteException {
		try {
			getJdbcTemplate().update(SQL_DELETE, new Object[]{trabajoDeCampo.getIdTrabajoDeCampo(),trabajoDeCampo.getIdPortafolio()});
		} catch (Exception e) {
			throw new DAODeleteException(e);
		}

	}


	public List<TrabajoDeCampoVO> findAllByPortafolio(TrabajoDeCampoVO trabajoDeCampo) throws DAOException {
		try {
			return getJdbcTemplate().query(SQL_FIND_BY_PORTAFOLIO, new Object[]{trabajoDeCampo.getIdPortafolio()}, new TrabajoDeCampoMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	
	public List<TrabajoDeCampoVO> findAllByAsignatura(PortafolioVO p) throws DAOException {
		try {
			return getJdbcTemplate().query(SQL_FIND_BY_ASIGNATURA, new Object[]{p.getIdAsignatura(),p.getAnyoAcademico()}, new TrabajoDeCampoMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

}


