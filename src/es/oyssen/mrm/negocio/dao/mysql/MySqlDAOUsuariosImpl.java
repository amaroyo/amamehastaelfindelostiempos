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
import es.oyssen.mrm.negocio.dao.DAOUsuarios;
import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOUpdateException;
import es.oyssen.mrm.negocio.dao.rowmappers.UsuarioMapper;
import es.oyssen.mrm.negocio.vo.GrupoVO;
import es.oyssen.mrm.negocio.vo.UsuarioVO;


public class MySqlDAOUsuariosImpl extends DAOBase implements DAOUsuarios{
	
	private static String SQL_INSERT = "insert into usuarios (id_grupo, id_asociado, nombre, telefono, telefonoMovil, direccion, codigoPostal, ciudad, pais, email, comentarios, user, pass) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static String SQL_UPDATE = "update usuarios set nombre=?, telefono=?, telefonoMovil=?, direccion=?, codigoPostal=?, ciudad=?, pais=?, email=?, comentarios=?, user=?";
	private static String SQL_DELETE = "delete from usuarios where id_usuario = ?";
	private static String SQL_FIND_ALL = "select * from usuarios";
	private static String SQL_FIND_ID = "select * from usuarios where id_usuario = ?";
	private static String SQL_FIND_USER_PASS = "select * from usuarios where user = ? and pass = ?";
	private static String SQL_FIND_BY_GRUPO = "select * from usuarios where id_grupo = ?";
	private static String SQL_FIND_USER = "select * from usuarios where user = ?";
	private static String SQL_FIND_EMAIL = "select * from usuarios where email = ?";
	
	
	public void insert(final UsuarioVO usuario) throws DAOException,
			DAOInsertException {
		try{
			KeyHolder kh = new GeneratedKeyHolder();
			getJdbcTemplate().update(new PreparedStatementCreator() {
				
				public PreparedStatement createPreparedStatement(Connection conn)
						throws SQLException {
					PreparedStatement ps = conn.prepareStatement(SQL_INSERT, new String[]{"id_usuario"});
					ps.setString(1, usuario.getIdGrupo());
					ps.setString(2, usuario.getIdAsociado());
					ps.setString(3, usuario.getNombre());
					ps.setString(4, usuario.getTelefono());
					ps.setString(5, usuario.getTelefonoMovil());
					ps.setString(6, usuario.getDireccion());
					ps.setString(7, usuario.getCodigoPostal());
					ps.setString(8, usuario.getCiudad());
					ps.setString(9, usuario.getPais());
					ps.setString(10, usuario.getEmail());
					ps.setString(11, usuario.getComentarios());	
					ps.setString(12, usuario.getUser());	
					ps.setString(13, usuario.getPass());	
					return ps;
				}
			}
			,kh);
			usuario.setIdUsuario(kh.getKey().toString());
			
		} catch (Exception e) {
			throw new DAOInsertException(e);
		}			
	}
	
	public void update(UsuarioVO usuario) throws DAOException,
			DAOUpdateException {
		try {
			
			String query = SQL_UPDATE;
			
			if (usuario.getPass() != null)
					query += ", pass='" + usuario.getPass()+"'"; 
			
			query += " where id_usuario = ?";
			
			getJdbcTemplate().update(query, new Object[]{
					usuario.getNombre(),
					usuario.getTelefono(),
					usuario.getTelefonoMovil(),
					usuario.getDireccion(),
					usuario.getCodigoPostal(),
					usuario.getCiudad(),
					usuario.getPais(),
					usuario.getEmail(),
					usuario.getComentarios(),
					usuario.getUser(),
					usuario.getIdUsuario()});
		} catch(Exception e) {
			throw new DAOUpdateException(e);
		}
		
	}
	
	public void delete(UsuarioVO usuario) throws DAOException,
			DAODeleteException {
		try {
			getJdbcTemplate().update(SQL_DELETE, new Object[]{usuario.getIdUsuario()});
		} catch (Exception e) {
			throw new DAODeleteException(e);
		}
		
	}

	public List<UsuarioVO> findAll() throws DAOException {
		try {
			return getJdbcTemplate().query(SQL_FIND_ALL, new Object[]{}, new UsuarioMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	public List<UsuarioVO> findByGrupo(GrupoVO grupo) throws DAOException {
		try {
			return getJdbcTemplate().query(SQL_FIND_BY_GRUPO, new Object[]{grupo.getIdGrupo()}, new UsuarioMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	public UsuarioVO findById(UsuarioVO usuario) throws DAOException {
		try {
			return (UsuarioVO) getJdbcTemplate().queryForObject(SQL_FIND_ID, new Object[]{usuario.getIdUsuario()}, new UsuarioMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	public UsuarioVO findByUser(UsuarioVO usuario) throws DAOException {
		try {
			return (UsuarioVO) getJdbcTemplate().queryForObject(SQL_FIND_USER, new Object[]{usuario.getUser()}, new UsuarioMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	public UsuarioVO findByEmail(UsuarioVO usuario) throws DAOException {
		try {
			return (UsuarioVO) getJdbcTemplate().queryForObject(SQL_FIND_EMAIL, new Object[]{usuario.getEmail()}, new UsuarioMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	public UsuarioVO findByUserPass(UsuarioVO usuario) throws DAOException {
		try {
			return (UsuarioVO) getJdbcTemplate().queryForObject(SQL_FIND_USER_PASS, new Object[]{usuario.getUser(), usuario.getPass()}, new UsuarioMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
}


