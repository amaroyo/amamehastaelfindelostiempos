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
import es.oyssen.mrm.negocio.dao.DAOUsuarios;
import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOUpdateException;
import es.oyssen.mrm.negocio.dao.rowmappers.UsuarioMapper;
import es.oyssen.mrm.negocio.vo.GrupoVO;
import es.oyssen.mrm.negocio.vo.UsuarioVO;


public class MySqlDAOUsuariosImpl extends DAOBase implements DAOUsuarios{
	
	private static String SQL_INSERT = "insert into usuarios (id_grupo, correo, contrasenya, nombre, apellido1, apellido2, dni, telefono, foto) values (?,?,?,?,?,?,?,?,?)";
	private static String SQL_UPDATE = "update usuarios set nombre=?, apellido1=?, apellido2=?, dni=?, correo=?, telefono=?, foto=?";
	private static String SQL_DELETE = "delete from usuarios where id_usuario = ?";
	private static String SQL_FIND_ALL = "select * from usuarios";
	private static String SQL_FIND_BY_ID = "select * from usuarios where id_usuario = ?";
	private static String SQL_FIND_BY_CORREO_PASS = "select * from usuarios where correo = ? and contrasenya = ?";
	private static String SQL_FIND_BY_GRUPO = "select * from usuarios where id_grupo = ?";
	private static String SQL_FIND_BY_CORREO = "select * from usuarios where correo = ?";
	private static String SQL_FIND_BY_DNI = "select * from usuarios where dni = ?";
	private static String SQL_FIND_BY_NOMBRE_APELLIDOS = "select * from usuarios where nombre = ? and apellido1 = ? and apellido2 = ?";
	
	
	public void insert(final UsuarioVO usuario) throws DAOException,
			DAOInsertException {
		try{
			KeyHolder kh = new GeneratedKeyHolder();
			getJdbcTemplate().update(new PreparedStatementCreator() {
				
				public PreparedStatement createPreparedStatement(Connection conn)
						throws SQLException {
					PreparedStatement ps = conn.prepareStatement(SQL_INSERT, new String[]{"id_usuario"});
					ps.setString(1, usuario.getIdGrupo());
					ps.setString(2, usuario.getCorreo());
					ps.setString(3, usuario.getContrasenya());
					ps.setString(4, usuario.getNombre());
					ps.setString(5, usuario.getApellido1());
					ps.setString(6, usuario.getApellido2());
					ps.setString(7, usuario.getDni());
					ps.setString(8, usuario.getTelefono());
	

					InputStream foto = new ByteArrayInputStream(usuario.getFotoFile());
					try {
						ps.setBinaryStream(9, foto, foto.available());
					}
					catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
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

			
			if (usuario.getContrasenya() != null)
					query += ", contrasenya='" + usuario.getContrasenya()+"'"; 
			
			query += " where id_usuario = ?";
			
			getJdbcTemplate().update(query, new Object[]{
					usuario.getNombre(),
					usuario.getApellido1(),
					usuario.getApellido2(),
					usuario.getDni(),
					usuario.getCorreo(),
					usuario.getTelefono(),
					usuario.getFotoFile(),
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
			return (UsuarioVO) getJdbcTemplate().queryForObject(SQL_FIND_BY_ID, new Object[]{usuario.getIdUsuario()}, new UsuarioMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	public UsuarioVO findByCorreo(UsuarioVO usuario) throws DAOException {
		try {
			return (UsuarioVO) getJdbcTemplate().queryForObject(SQL_FIND_BY_CORREO, new Object[]{usuario.getCorreo()}, new UsuarioMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	public UsuarioVO findByDni(UsuarioVO usuario) throws DAOException {
		try {
			return (UsuarioVO) getJdbcTemplate().queryForObject(SQL_FIND_BY_DNI, new Object[]{usuario.getDni()}, new UsuarioMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	public UsuarioVO findByCorreoPass(UsuarioVO usuario) throws DAOException {
		try {
			return (UsuarioVO) getJdbcTemplate().queryForObject(SQL_FIND_BY_CORREO_PASS, new Object[]{usuario.getCorreo(), usuario.getContrasenya()}, new UsuarioMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	public UsuarioVO findByNombreApellidos(UsuarioVO usuario) throws DAOException {
		try {
			return (UsuarioVO) getJdbcTemplate().queryForObject(SQL_FIND_BY_NOMBRE_APELLIDOS, new Object[]{usuario.getNombre(), usuario.getApellido1(), usuario.getApellido2()}, new UsuarioMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
}


