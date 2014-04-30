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
import es.oyssen.mrm.negocio.dao.DAOCanales;
import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOUpdateException;
import es.oyssen.mrm.negocio.dao.rowmappers.CanalMapper;
import es.oyssen.mrm.negocio.vo.CanalVO;
import es.oyssen.mrm.negocio.vo.CriterioCanalVO;
import es.oyssen.mrm.util.StringUtil;


public class MySqlDAOCanalesImpl extends DAOBase implements DAOCanales{
	
	private static String SQL_INSERT = "insert into canales (nombre, telefono, telefonoMovil, direccion, codigoPostal, ciudad, pais, email, direccionFacturacion, codigoPostalFacturacion, ciudadFacturacion, paisFacturacion, nombreFacturacion, telefonoFacturacion, telefonoMovilFacturacion, emailFacturacion, direccionWeb, mrmResponsable) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static String SQL_UPDATE = "update canales set nombre=?, telefono=?, telefonoMovil=?, direccion=?, codigoPostal=?, ciudad=?, pais=?, email=?, direccionFacturacion=?, codigoPostalFacturacion=?, ciudadFacturacion=?, paisFacturacion=?, nombreFacturacion=?, telefonoFacturacion=?, telefonoMovilFacturacion=?, emailFacturacion=?, direccionWeb=?, mrmResponsable=? where id_canal = ?";
	private static String SQL_DELETE = "delete from canales where id_canal = ?";
	private static String SQL_FIND_ID = "select * from canales where id_canal = ?";
	private static String SQL_FIND_CRITERIO = "select * from canales";
	
	
	public void insert(final CanalVO canal) throws DAOException,
			DAOInsertException {
		try{
			KeyHolder kh = new GeneratedKeyHolder();
			getJdbcTemplate().update(new PreparedStatementCreator() {
				
				public PreparedStatement createPreparedStatement(Connection conn)
						throws SQLException {
					PreparedStatement ps = conn.prepareStatement(SQL_INSERT, new String[]{"id_canal"});
					ps.setString(1, canal.getNombre());
					ps.setString(2, canal.getTelefono());
					ps.setString(3, canal.getTelefonoMovil());
					ps.setString(4, canal.getDireccion());
					ps.setString(5, canal.getCodigoPostal());
					ps.setString(6, canal.getCiudad());
					ps.setString(7, canal.getPais());
					ps.setString(8, canal.getEmail());
					ps.setString(9, canal.getDireccionFacturacion());
					ps.setString(10, canal.getCodigoPostalFacturacion());
					ps.setString(11, canal.getCiudadFacturacion());
					ps.setString(12, canal.getPaisFacturacion());
					ps.setString(13, canal.getNombreFacturacion());
					ps.setString(14, canal.getTelefonoFacturacion());
					ps.setString(15, canal.getTelefonoMovilFacturacion());
					ps.setString(16, canal.getEmailFacturacion());
					ps.setString(17, canal.getDireccionWeb());
					ps.setString(18, canal.getMrmResponsable());
					
					return ps;
				}
			}
			,kh);
			canal.setIdCanal(kh.getKey().toString());
			
		} catch (Exception e) {
			throw new DAOInsertException(e);
		}			
	}
	
	
	public void update(CanalVO canal) throws DAOException,
			DAOUpdateException {
		try {
			getJdbcTemplate().update(SQL_UPDATE, new Object[]{
					canal.getNombre(),
					canal.getTelefono(),
					canal.getTelefonoMovil(),
					canal.getDireccion(),
					canal.getCodigoPostal(),
					canal.getCiudad(),
					canal.getPais(),
					canal.getEmail(),
					canal.getDireccionFacturacion(),
					canal.getCodigoPostalFacturacion(),
					canal.getCiudadFacturacion(),
					canal.getPaisFacturacion(),
					canal.getNombreFacturacion(),
					canal.getTelefonoFacturacion(),
					canal.getTelefonoMovilFacturacion(),
					canal.getEmailFacturacion(),
					canal.getDireccionWeb(),
					canal.getMrmResponsable(),
					canal.getIdCanal()});
		} catch(Exception e) {
			throw new DAOUpdateException(e);
		}
		
	}
	public void delete(CanalVO canal) throws DAOException,
			DAODeleteException {
		try {
			getJdbcTemplate().update(SQL_DELETE, new Object[]{canal.getIdCanal()});
		} catch (Exception e) {
			throw new DAODeleteException(e);
		}
		
	}
	public List<CanalVO> findByCriterio(CriterioCanalVO criterio)
			throws DAOException {
		try {
			StringBuffer sb = new StringBuffer(SQL_FIND_CRITERIO);
			ArrayList params = new ArrayList();
			String cadena = " where ";
			if (!StringUtil.isNullOrBlank(criterio.getIdCanal())) {
				sb.append(cadena + "id_canal = ?");
				cadena = " and ";
				params.add(criterio.getIdCanal());
			}
			if (!StringUtil.isNullOrBlank(criterio.getNombre())) {
				sb.append(cadena + "upper(nombre) = upper(?)");
				cadena = " and ";
				params.add(criterio.getNombre());
			}
			if (!StringUtil.isNullOrBlank(criterio.getTelefono())) {
				sb.append(cadena + "upper(telefono) = upper(?)");
				cadena = " and ";
				params.add(criterio.getTelefono());
			}
			if (!StringUtil.isNullOrBlank(criterio.getTelefonoMovil())) {
				sb.append(cadena + "upper(telefonoMovil) = upper(?)");
				cadena = " and ";
				params.add(criterio.getTelefonoMovil());
			}
			if (!StringUtil.isNullOrBlank(criterio.getDireccion())) {
				sb.append(cadena + "upper(direccion) = upper(?)");
				cadena = " and ";
				params.add(criterio.getDireccion());
			}
			if (!StringUtil.isNullOrBlank(criterio.getCodigoPostal())) {
				sb.append(cadena + "upper(codigoPostal) = upper(?)");
				cadena = " and ";
				params.add(criterio.getCodigoPostal());
			}
			if (!StringUtil.isNullOrBlank(criterio.getCiudad())) {
				sb.append(cadena + "upper(ciudad) = upper(?)");
				cadena = " and ";
				params.add(criterio.getCiudad());
			}
			if (!StringUtil.isNullOrBlank(criterio.getPais())) {
				sb.append(cadena + "upper(pais) = upper(?)");
				cadena = " and ";
				params.add(criterio.getPais());
			}
			if (!StringUtil.isNullOrBlank(criterio.getEmail())) {
				sb.append(cadena + "upper(email) = upper(?)");
				cadena = " and ";
				params.add(criterio.getEmail());
			}
			if (!StringUtil.isNullOrBlank(criterio.getDireccionFacturacion())) {
				sb.append(cadena + "upper(direccionFacturacion) = upper(?)");
				cadena = " and ";
				params.add(criterio.getDireccionFacturacion());
			}
			if (!StringUtil.isNullOrBlank(criterio.getCodigoPostalFacturacion())) {
				sb.append(cadena + "upper(codigoPostalFacturacion) = upper(?)");
				cadena = " and ";
				params.add(criterio.getCodigoPostalFacturacion());
			}
			if (!StringUtil.isNullOrBlank(criterio.getCiudadFacturacion())) {
				sb.append(cadena + "upper(ciudadFacturacion) = upper(?)");
				cadena = " and ";
				params.add(criterio.getCiudadFacturacion());
			}
			if (!StringUtil.isNullOrBlank(criterio.getPaisFacturacion())) {
				sb.append(cadena + "upper(paisFacturacion) = upper(?)");
				cadena = " and ";
				params.add(criterio.getPaisFacturacion());
			}			
			if (!StringUtil.isNullOrBlank(criterio.getNombreFacturacion())) {
				sb.append(cadena + "upper(nombreFacturacion) = upper(?)");
				cadena = " and ";
				params.add(criterio.getNombreFacturacion());
			}			
			if (!StringUtil.isNullOrBlank(criterio.getTelefonoFacturacion())) {
				sb.append(cadena + "upper(telefonoFacturacion) = upper(?)");
				cadena = " and ";
				params.add(criterio.getTelefonoFacturacion());
			}
			if (!StringUtil.isNullOrBlank(criterio.getTelefonoMovilFacturacion())) {
				sb.append(cadena + "upper(telefonoMovilFacturacion) = upper(?)");
				cadena = " and ";
				params.add(criterio.getTelefonoMovilFacturacion());
			}
			if (!StringUtil.isNullOrBlank(criterio.getEmailFacturacion())) {
				sb.append(cadena + "upper(emailFacturacion) = upper(?)");
				cadena = " and ";
				params.add(criterio.getEmailFacturacion());
			}
			if (!StringUtil.isNullOrBlank(criterio.getDireccionWeb())) {
				sb.append(cadena + "upper(direccionWeb) = upper(?)");
				cadena = " and ";
				params.add(criterio.getDireccionWeb());
			}
			if (!StringUtil.isNullOrBlank(criterio.getMrmResponsable())) {
				sb.append(cadena + "upper(mrmResponsable) = upper(?)");
				cadena = " and ";
				params.add(criterio.getMrmResponsable());
			}
			
			return getJdbcTemplate().query(sb.toString(), params.toArray(), new CanalMapper());
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	public CanalVO findById(CanalVO canal) throws DAOException {
		try {
			return (CanalVO) getJdbcTemplate().queryForObject(SQL_FIND_ID, new Object[]{canal.getIdCanal()}, new CanalMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
}


