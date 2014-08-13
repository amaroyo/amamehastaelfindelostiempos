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
import es.oyssen.mrm.negocio.dao.DAOTrabajosDeCampo;
import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOUpdateException;
import es.oyssen.mrm.negocio.dao.rowmappers.TrabajoDeCampoMapper;
import es.oyssen.mrm.negocio.dao.rowmappers.TrabajoDeCampoInfoMapper;
import es.oyssen.mrm.negocio.dao.rowmappers.UsuarioTrabajoCampoMapper;
import es.oyssen.mrm.negocio.vo.PortafolioVO;
import es.oyssen.mrm.negocio.vo.TrabajoDeCampoInfoVO;
import es.oyssen.mrm.negocio.vo.TrabajoDeCampoVO;
import es.oyssen.mrm.negocio.vo.UsuarioTrabajoCampoVO;


public class MySqlDAOTrabajosDeCampoImpl extends DAOBase implements DAOTrabajosDeCampo{

	private static String SQL_INSERT = "insert into trabajos_de_campo (id_portafolio, id_trabajo_info, fecha_limite) values (?,?,?)";
	private static String SQL_UPDATE = "update trabajos_de_campo set id_trabajo_info=?, trabajo_de_campo=?, correccion_trabajo=?, fecha_limite=?";
	private static String SQL_UPDATE_DATE = "update trabajos_de_campo set fecha_limite=?";
	private static String SQL_UPDATE_CORRECCION = "update trabajos_de_campo set nombre_correccion=?, correccion_trabajo=?";	
	private static String SQL_UPDATE_TRABAJO = "update trabajos_de_campo set nombre_trabajo=?, trabajo_de_campo=?";
	private static String SQL_DELETE = "delete from trabajos_de_campo where id_portafolio = ? and id_trabajo_de_campo = ?";
	private static String SQL_FIND_BY_PORTAFOLIO = "select t.*,i.* from trabajos_de_campo as t, trabajos_de_campo_info as i where t.id_trabajo_info = i.id_trabajo_info and id_portafolio = ?";
	private static String SQL_FIND_BY_ASIGNATURA = "select t.*,i.* from trabajos_de_campo as t, trabajos_de_campo_info as i, portafolios as p where t.id_trabajo_info = i.id_trabajo_info and p.id_portafolio = t.id_portafolio and p.id_asignatura =? and p.anyo_academico =?";
	private static String SQL_FIND_NOMBRE_BY_ASIGNATURA = "select distinct i.*, t.fecha_limite from trabajos_de_campo as t, trabajos_de_campo_info as i, portafolios as p where p.id_portafolio = t.id_portafolio and t.id_trabajo_info = i.id_trabajo_info and p.id_asignatura =? and p.anyo_academico =?";
	private static String SQL_FIND_BY_ASIGNATURA_TRABAJO = "select t.id_portafolio, t.id_trabajo_de_campo, t.trabajo_de_campo, t.correccion_trabajo, t.fecha_limite, u.id_usuario, u.nombre, u.apellido1, u.apellido2, u.dni" +  
															" from trabajos_de_campo as t, portafolios as p, usuarios as u" + 
															" where p.id_portafolio = t.id_portafolio and p.id_alumno=u.id_usuario and p.id_asignatura =? and p.anyo_academico =? and t.id_trabajo_info =?";

	private static String SQL_FIND_BY_IDs = "select t.fecha_limite,i.* from trabajos_de_campo as t, trabajos_de_campo_info as i where t.id_trabajo_info = i.id_trabajo_info and id_portafolio = ? and id_trabajo_de_campo=?";
	private static String SQL_FIND_BY_IDsTC = "select t.*,i.* from trabajos_de_campo as t, trabajos_de_campo_info as i where t.id_trabajo_info = i.id_trabajo_info and id_portafolio = ? and id_trabajo_de_campo=?";
	private static String SQL_FIND_ALL_BY_ID_INFO = "select t.*,i.* from trabajos_de_campo as t, trabajos_de_campo_info as i where t.id_trabajo_info = i.id_trabajo_info and i.id_trabajo_info=?";
	private static String SQL_FIND_BY_PORTAFOLIO_INFO = "select t.*,i.* from trabajos_de_campo as t, trabajos_de_campo_info as i where t.id_trabajo_info = i.id_trabajo_info and t.id_portafolio = ? and t.id_trabajo_info=?";

	public void insert(final TrabajoDeCampoVO trabajoDeCampo) throws DAOException,
	DAOInsertException {
		try{
			KeyHolder kh = new GeneratedKeyHolder();
			getJdbcTemplate().update(new PreparedStatementCreator() {

				public PreparedStatement createPreparedStatement(Connection conn)
						throws SQLException {
					PreparedStatement ps = conn.prepareStatement(SQL_INSERT, new String[]{"id_trabajo_de_campo"});
					ps.setString(1, trabajoDeCampo.getIdPortafolio());
					ps.setString(2, trabajoDeCampo.getIdTrabajoInfo());
					ps.setString(3, trabajoDeCampo.getFechaLimite());
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
					trabajoDeCampo.getIdTrabajoInfo(),
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
			getJdbcTemplate().update(SQL_DELETE, new Object[]{trabajoDeCampo.getIdPortafolio(),trabajoDeCampo.getIdTrabajoDeCampo()});
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
	
	public List<TrabajoDeCampoInfoVO> findAllNombresByAsignaturaTrabajo(PortafolioVO p) throws DAOException {
		try {
			return getJdbcTemplate().query(SQL_FIND_NOMBRE_BY_ASIGNATURA, new Object[]{p.getIdAsignatura(),p.getAnyoAcademico()}, new TrabajoDeCampoInfoMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	@Override
	public List<UsuarioTrabajoCampoVO> findAllByAsignaturaTrabajo(PortafolioVO p, TrabajoDeCampoVO t) throws DAOException {
		
		try {
			return getJdbcTemplate().query(SQL_FIND_BY_ASIGNATURA_TRABAJO, new Object[]{p.getIdAsignatura(),p.getAnyoAcademico(),t.getIdTrabajoInfo()}, new UsuarioTrabajoCampoMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
	}

	@Override
	public TrabajoDeCampoInfoVO findByIDs(TrabajoDeCampoVO t) throws DAOException {
		try {
			return (TrabajoDeCampoInfoVO) getJdbcTemplate().queryForObject(SQL_FIND_BY_IDs, new Object[]{t.getIdPortafolio(),t.getIdTrabajoDeCampo()}, new TrabajoDeCampoInfoMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void updateTrabajoCampo(final TrabajoDeCampoVO tc) throws DAOException,
			DAOUpdateException {
		try {
			 
			final String query = SQL_UPDATE_TRABAJO + " where id_portafolio = ? and id_trabajo_de_campo=?";
			
			KeyHolder kh = new GeneratedKeyHolder();
			getJdbcTemplate().update(new PreparedStatementCreator() {
				
				public PreparedStatement createPreparedStatement(Connection conn)
						throws SQLException {
					PreparedStatement ps = conn.prepareStatement(query, new String[]{""});
					ps.setString(1, tc.getNombreTrabajo());
					
					InputStream datos = new ByteArrayInputStream(tc.getTrabajoDeCampo());
					try {
						ps.setBinaryStream(2, datos, datos.available());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					ps.setString(3, tc.getIdPortafolio());
					ps.setString(4, tc.getIdTrabajoDeCampo());
	
					return ps;
					
				}
			}
			,kh);
					
		} catch(Exception e) {
			throw new DAOUpdateException(e);
		}

	}

	@Override
	public void updateTrabajoCampoCorreccion(final TrabajoDeCampoVO tc)
			throws DAOException, DAOUpdateException {
		
		try{
			final String query = SQL_UPDATE_CORRECCION + " where id_portafolio = ? and id_trabajo_de_campo=?";
			
			KeyHolder kh = new GeneratedKeyHolder();
			getJdbcTemplate().update(new PreparedStatementCreator() {
				
				public PreparedStatement createPreparedStatement(Connection conn)
						throws SQLException {
					PreparedStatement ps = conn.prepareStatement(query, new String[]{""});
					ps.setString(1, tc.getNombreCorreccion());
					
					InputStream datos = new ByteArrayInputStream(tc.getCorreccionTrabajo());
					try {
						ps.setBinaryStream(2, datos, datos.available());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					ps.setString(3, tc.getIdPortafolio());
					ps.setString(4, tc.getIdTrabajoDeCampo());
	
					return ps;
					
				}
			}
			,kh);
					
		} catch(Exception e) {
			throw new DAOUpdateException(e);
		}		
	}

	@Override
	public TrabajoDeCampoVO findByIDsTC(TrabajoDeCampoVO tc)
			throws DAOException {
		try {
			return (TrabajoDeCampoVO) getJdbcTemplate().queryForObject(SQL_FIND_BY_IDsTC, new Object[]{tc.getIdPortafolio(),tc.getIdTrabajoDeCampo()}, new TrabajoDeCampoMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	@Override
	public List<TrabajoDeCampoVO> findAllByIdInfo(TrabajoDeCampoInfoVO i)
			throws DAOException {
		try {
			return getJdbcTemplate().query(SQL_FIND_ALL_BY_ID_INFO, new Object[]{i.getIdTrabajoInfo()}, new TrabajoDeCampoMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	@Override
	public TrabajoDeCampoVO findByPortafolioInfo(TrabajoDeCampoVO trabajo)
			throws DAOException {
		try {
			return (TrabajoDeCampoVO) getJdbcTemplate().queryForObject(SQL_FIND_BY_PORTAFOLIO_INFO, new Object[]{trabajo.getIdPortafolio(),trabajo.getIdTrabajoInfo()}, new TrabajoDeCampoMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void updateIndividualDate(TrabajoDeCampoVO t) throws DAOException,
			DAOUpdateException {
		try {
			 
			String query = SQL_UPDATE_DATE;

			query += " where id_trabajo_de_campo = ? and id_portafolio = ?";

			getJdbcTemplate().update(query, new Object[]{
					
					t.getFechaLimite(),
					t.getIdTrabajoDeCampo(),
					t.getIdPortafolio()});
		} catch(Exception e) {
			throw new DAOUpdateException(e);
		}
		
	}
	
	

	
}


