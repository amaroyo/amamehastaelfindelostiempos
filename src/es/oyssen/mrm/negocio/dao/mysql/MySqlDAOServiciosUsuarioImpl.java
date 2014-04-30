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
import es.oyssen.mrm.negocio.dao.DAOServiciosUsuario;
import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.rowmappers.ServicioUsuarioMapper;
import es.oyssen.mrm.negocio.vo.ServicioUsuarioVO;
import es.oyssen.mrm.negocio.vo.UsuarioVO;


public class MySqlDAOServiciosUsuarioImpl extends DAOBase implements DAOServiciosUsuario{
	
	private static String SQL_INSERT = "insert into servicios_usuarios (id_servicio, id_usuario) values (?,?)";
	private static String SQL_DELETE = "delete from servicios_usuarios where id_servicioUsuario = ?";
	private static String SQL_FIND_BY_USUARIO = "select su.*, s.nombre servicio_nombre from servicios_usuarios su left join servicios s on s.id_servicio = su.id_servicio where su.id_usuario = ?";
	
	
	public void insert(final ServicioUsuarioVO servicioUsuario) throws DAOException,
			DAOInsertException {
		try{
			KeyHolder kh = new GeneratedKeyHolder();
			getJdbcTemplate().update(new PreparedStatementCreator() {
				
				public PreparedStatement createPreparedStatement(Connection conn)
						throws SQLException {
					PreparedStatement ps = conn.prepareStatement(SQL_INSERT, new String[]{"id_servicioUsuario"});
					ps.setString(1, servicioUsuario.getIdServicio());
					ps.setString(2, servicioUsuario.getIdUsuario());
					
					return ps;
				}
			}
			,kh);
			servicioUsuario.setIdServicioUsuario(kh.getKey().toString());
			
		} catch (Exception e) {
			throw new DAOInsertException(e);
		}			
	}
	
	public void delete(ServicioUsuarioVO servicioUsuario) throws DAOException,
			DAODeleteException {
		try {
			getJdbcTemplate().update(SQL_DELETE, new Object[]{servicioUsuario.getIdServicioUsuario()});
		} catch (Exception e) {
			throw new DAODeleteException(e);
		}
		
	}

	public List<ServicioUsuarioVO> findByUsuario(UsuarioVO usuario) throws DAOException {
		try {
			return getJdbcTemplate().query(SQL_FIND_BY_USUARIO, new Object[]{usuario.getIdUsuario()}, new ServicioUsuarioMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
}


