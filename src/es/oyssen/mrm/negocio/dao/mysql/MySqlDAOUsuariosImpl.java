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
import es.oyssen.mrm.negocio.dao.rowmappers.UsuarioAsignaturaMapper;
import es.oyssen.mrm.negocio.dao.rowmappers.UsuarioMapper;
import es.oyssen.mrm.negocio.vo.GrupoVO;
import es.oyssen.mrm.negocio.vo.PortafolioVO;
import es.oyssen.mrm.negocio.vo.UsuarioAsignaturaVO;
import es.oyssen.mrm.negocio.vo.UsuarioVO;


public class MySqlDAOUsuariosImpl extends DAOBase implements DAOUsuarios{
	
	private static String SQL_INSERT = "insert into usuarios (id_grupo, correo, contrasenya, nombre, apellido1, apellido2, dni, telefono) values (?,?,?,?,?,?,?,?)";
	private static String SQL_UPDATE = "update usuarios set nombre=?, apellido1=?, apellido2=?, dni=?, correo=?, telefono=?";
	private static String SQL_UPDATE_GRUPO = "update usuarios set id_grupo=?";
	private static String SQL_DELETE = "delete from usuarios where id_usuario = ?";
	private static String SQL_FIND_ALL = "select * from usuarios";
	private static String SQL_FIND_BY_ID = "select * from usuarios where id_usuario = ?";
	private static String SQL_FIND_BY_CORREO_PASS = "select * from usuarios where correo = ? and contrasenya = ?";
	private static String SQL_FIND_BY_GRUPO = "select * from usuarios where id_grupo = ?";
	private static String SQL_FIND_BY_CORREO = "select * from usuarios where correo = ?";
	private static String SQL_FIND_BY_DNI = "select * from usuarios where dni = ?";
	private static String SQL_FIND_BY_NOMBRE_APELLIDOS = "select * from usuarios where nombre = ? and apellido1 = ? and apellido2 = ?";
	private static String SQL_FIND_ALL_BY_ANYO = "select u.id_usuario, u.nombre, u.apellido1, u.apellido2, u.dni, a.id_asignatura, a.codigo, a.nombre, p.id_profesor, p.id_portafolio " +
												"from asignaturas as a, usuarios as u, portafolios as p "+
												"where u.id_usuario = p.id_alumno and a.id_asignatura = p.id_asignatura and p.anyo_academico =?";
	
	private static String SQL_FIND_ALL_BY_PROFESOR = "select u.id_usuario, u.nombre, u.apellido1, u.apellido2, u.dni, a.id_asignatura, a.codigo, a.nombre, p.id_profesor, p.id_portafolio " +
														"from asignaturas as a, usuarios as u, portafolios as p "+
														"where u.id_usuario = p.id_alumno and a.id_asignatura = p.id_asignatura and p.anyo_academico =? and p.id_profesor =?";
	
	private static String SQL_FIND_ALL_BY_PROFESOR_DEMAS = "select u.id_usuario, u.nombre, u.apellido1, u.apellido2, u.dni, a.id_asignatura, a.codigo, a.nombre, p.id_profesor, p.id_portafolio " + 
															"from asignaturas as a, usuarios as u, portafolios as p " +
															"where u.id_usuario = p.id_alumno and a.id_asignatura = p.id_asignatura and p.anyo_academico =? and p.id_profesor !=?";
	
	
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
	

					/*InputStream foto = new ByteArrayInputStream(usuario.getFotoFile());
					try {
						ps.setBinaryStream(9, foto, foto.available());
					}
					catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
				
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
			Object[] o;

			
			if (usuario.getContrasenya() != null)
					query += ", contrasenya='" + usuario.getContrasenya()+"'";
			
			if (usuario.getFotoFile() != null){
				query += ", foto= ?";
				o = new Object[]{
						usuario.getNombre(),
						usuario.getApellido1(),
						usuario.getApellido2(),
						usuario.getDni(),
						usuario.getCorreo(),
						usuario.getTelefono(),
						usuario.getFotoFile(),
						usuario.getIdUsuario()};
			}
			else {
				o = new Object[]{
						usuario.getNombre(),
						usuario.getApellido1(),
						usuario.getApellido2(),
						usuario.getDni(),
						usuario.getCorreo(),
						usuario.getTelefono(),
						usuario.getIdUsuario()};
			}
			
			query += " where id_usuario = ?";
			
			
			getJdbcTemplate().update(query, o);
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

	@Override
	public List<UsuarioAsignaturaVO> findAllbyAnyoAcademico(PortafolioVO p)
			throws DAOException {
		try {
			return getJdbcTemplate().query(SQL_FIND_ALL_BY_ANYO, new Object[]{p.getAnyoAcademico()}, new UsuarioAsignaturaMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	@Override
	public List<UsuarioAsignaturaVO> findAllbyProfesor(PortafolioVO p)
			throws DAOException {
		try {
			return getJdbcTemplate().query(SQL_FIND_ALL_BY_PROFESOR, new Object[]{p.getAnyoAcademico(), p.getIdProfesor()}, new UsuarioAsignaturaMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	@Override
	public List<UsuarioAsignaturaVO> findAllbyProfesorDemas(PortafolioVO p)
			throws DAOException {
		try {
			return getJdbcTemplate().query(SQL_FIND_ALL_BY_PROFESOR_DEMAS, new Object[]{p.getAnyoAcademico(), p.getIdProfesor()}, new UsuarioAsignaturaMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void updateGrupo(UsuarioVO usuario) throws DAOException,
			DAOUpdateException {
		try {
			 
			String query = SQL_UPDATE_GRUPO;

			query += " where id_usuario=?";

			getJdbcTemplate().update(query, new Object[]{
					usuario.getIdGrupo(),
					usuario.getIdUsuario()});
		} catch(Exception e) {
			throw new DAOUpdateException(e);
		}
		
	}
	
}


