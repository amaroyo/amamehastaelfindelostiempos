package es.oyssen.mrm.negocio.dao.mysql;

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
import es.oyssen.mrm.negocio.dao.DAOServicios;
import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOUpdateException;
import es.oyssen.mrm.negocio.dao.rowmappers.ServicioMapper;
import es.oyssen.mrm.negocio.vo.CriterioServicioVO;
import es.oyssen.mrm.negocio.vo.ServicioVO;
import es.oyssen.mrm.util.StringUtil;

public class MySqlDAOServiciosImpl extends DAOBase implements DAOServicios {

	private static String SQL_FIND_NAME = "select * from servicios where nombre = ?";
	private static String SQL_INSERT = "insert into servicios (nombre, personaContacto, proveedor) values (?,?,?)";
	private static String SQL_UPDATE = "update servicios set nombre=?, personaContacto=?, proveedor=? where id_servicio = ?";
	private static String SQL_DELETE = "delete from servicios where id_servicio = ?";
	private static String SQL_FIND_ID = "select * from servicios where id_servicio = ?";
	private static String SQL_FIND_CRITERIO = "select * from servicios";

	public ServicioVO findByNombre(ServicioVO servicio) throws DAOException {
		try {
			return (ServicioVO) getJdbcTemplate().queryForObject(SQL_FIND_NAME, new Object[]{servicio.getNombre()}, new ServicioMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}	
	}
	
	public void insert(final ServicioVO servicio) throws DAOException, DAOInsertException {
		try{
			KeyHolder kh = new GeneratedKeyHolder();
			getJdbcTemplate().update(new PreparedStatementCreator() {
				
				public PreparedStatement createPreparedStatement(Connection conn)
						throws SQLException {
					PreparedStatement ps = conn.prepareStatement(SQL_INSERT, new String[]{"id_servicio"});
					ps.setString(1, servicio.getNombre());
					ps.setString(2, servicio.getPersonaContacto());
					ps.setString(3, servicio.getProveedor());
					return ps;
				}
			}
			,kh);
			servicio.setIdServicio(kh.getKey().toString());
			
		} catch (Exception e) {
			throw new DAOInsertException(e);
		}	
	}

	public void update(ServicioVO servicio) throws DAOException,
			DAOUpdateException {
		try {
			getJdbcTemplate().update(SQL_UPDATE, new Object[]{
					servicio.getNombre(),
					servicio.getPersonaContacto(),
					servicio.getProveedor(),
					servicio.getIdServicio()});
		} catch(Exception e) {
			throw new DAOUpdateException(e);
		}

	}

	
	public void delete(ServicioVO servicio) throws DAOException,
			DAODeleteException {
		try {
			getJdbcTemplate().update(SQL_DELETE, new Object[]{servicio.getIdServicio()});
		} catch (Exception e) {
			throw new DAODeleteException(e);
		}	
	}
	
	public List<ServicioVO> findByCriterio(CriterioServicioVO criterio)
			throws DAOException {
		try {
			StringBuffer sb = new StringBuffer(SQL_FIND_CRITERIO);
			String cadena = " where ";
			ArrayList params = new ArrayList();
			if (!StringUtil.isNullOrBlank(criterio.getNombre())) {
				sb.append(cadena + "upper(nombre) = (?)");
				cadena = " and ";
				params.add("%" + criterio.getNombre() + "%");
			}
			if (!StringUtil.isNullOrBlank(criterio.getPersonaContacto())) {
				sb.append(cadena + "upper(personaContacto) = (?)");
				cadena = " and ";
				params.add("%" + criterio.getPersonaContacto() + "%");
			}
			if (!StringUtil.isNullOrBlank(criterio.getProveedor())) {
				sb.append(cadena + "upper(proveedor) = (?)");
				cadena = " and ";
				params.add("%" + criterio.getProveedor() + "%");
			}		
			if (!StringUtil.isNullOrBlank(criterio.getIdUsuario())) {
				sb.append(cadena + "id_servicio in (select id_servicio from servicios_usuarios where id_usuario=?)");
				cadena = " and ";
				params.add(criterio.getIdUsuario());
			}		
			return getJdbcTemplate().query(sb.toString(), params.toArray(), new ServicioMapper());
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}	
	
	public ServicioVO findById(ServicioVO servicio) throws DAOException {
		try {
			return (ServicioVO) getJdbcTemplate().queryForObject(SQL_FIND_ID, new Object[]{servicio.getIdServicio()}, new ServicioMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
}
