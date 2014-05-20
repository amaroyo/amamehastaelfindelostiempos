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
import es.oyssen.mrm.negocio.dao.DAOLogsUsuario;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.rowmappers.LogUsuarioMapper;
import es.oyssen.mrm.negocio.vo.LogUsuarioVO;


public class MySqlDAOLogsUsuarioImpl extends DAOBase implements DAOLogsUsuario{
	
	private static String SQL_INSERT = "insert into logs_usuarios (id_usuario, fecha) values (?,?)";
	private static String SQL_FIND_ALL = "select lu.*, u.nombre usuario_nombre, u.user usuario_user from logs_usuarios lu left join usuarios u on u.id_usuario = lu.id_usuario";
	
	
	public void insert(final LogUsuarioVO logUsuario) throws DAOException,
			DAOInsertException {
		/*try{
			KeyHolder kh = new GeneratedKeyHolder();
			getJdbcTemplate().update(new PreparedStatementCreator() {
				
				public PreparedStatement createPreparedStatement(Connection conn)
						throws SQLException {
					PreparedStatement ps = conn.prepareStatement(SQL_INSERT, new String[]{"id_servicioUsuario"});
					ps.setString(1, logUsuario.getIdUsuario());				
					java.util.Date utilDate = new java.util.Date();
					java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
					ps.setDate(2, sqlDate);
					
					return ps;
				}
			}
			,kh);
			logUsuario.setIdLogUsuario(kh.getKey().toString());
			
		} catch (Exception e) {
			throw new DAOInsertException(e);
		}/*/		
	}
	

	public List<LogUsuarioVO> findAll() throws DAOException {
		/*try {
			return getJdbcTemplate().query(SQL_FIND_ALL, new Object[]{}, new LogUsuarioMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}*/
		return null;
	}
	
}


