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
import es.oyssen.mrm.negocio.dao.DAODistribuidores;
import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOUpdateException;
import es.oyssen.mrm.negocio.dao.rowmappers.DistribuidorMapper;
import es.oyssen.mrm.negocio.vo.CanalVO;
import es.oyssen.mrm.negocio.vo.CriterioDistribuidorVO;
import es.oyssen.mrm.negocio.vo.DistribuidorVO;
import es.oyssen.mrm.util.StringUtil;


public class MySqlDAODistribuidoresImpl extends DAOBase implements DAODistribuidores{
	
	private static String SQL_INSERT = "insert into distribuidores (id_canal, nombre, telefono, telefonoMovil, direccion, codigoPostal, ciudad, pais, email, direccionFacturacion, codigoPostalFacturacion, ciudadFacturacion, paisFacturacion, nombreFacturacion, telefonoFacturacion, telefonoMovilFacturacion, emailFacturacion) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static String SQL_UPDATE = "update distribuidores set nombre=?, telefono=?, telefonoMovil=?, direccion=?, codigoPostal=?, ciudad=?, pais=?, email=?, direccionFacturacion=?, codigoPostalFacturacion=?, ciudadFacturacion=?, paisFacturacion=?, nombreFacturacion=?, telefonoFacturacion=?, telefonoMovilFacturacion=?, emailFacturacion=? where id_distribuidor = ?";
	private static String SQL_DELETE = "delete from distribuidores where id_distribuidor = ?";
	private static String SQL_FIND_ID = "select * from distribuidores where id_distribuidor = ?";
	private static String SQL_FIND_CRITERIO = "select * from distribuidores";
	//private static String SQL_FIND_BY_CANAL = "select * from distribuidores where id_distribuidor in (select id_distribuidor from leads where id_canal = ?);";
	private static String SQL_FIND_BY_CANAL = "select * from distribuidores where id_canal = ?;";
	private static String SQL_BLOQUEAR = "update distribuidores set bloqueado = 'YES' where id_distribuidor = ?;";
	private static String SQL_DESBLOQUEAR = "update distribuidores set bloqueado = 'NO' where id_distribuidor = ?;";
	private static String SQL_UPDATE_CANAL = "update distribuidores set id_canal = (select id_canal from leads where id_lead=?) where id_distribuidor = (select id_distribuidor from leads where id_lead=?);";

	
	
	public void insert(final DistribuidorVO distribuidor) throws DAOException,
			DAOInsertException {
		try{
			KeyHolder kh = new GeneratedKeyHolder();
			getJdbcTemplate().update(new PreparedStatementCreator() {
				
				public PreparedStatement createPreparedStatement(Connection conn)
						throws SQLException {
					PreparedStatement ps = conn.prepareStatement(SQL_INSERT, new String[]{"id_distribuidor"});
					ps.setString(1, distribuidor.getIdCanal());
					ps.setString(2, distribuidor.getNombre());
					ps.setString(3, distribuidor.getTelefono());
					ps.setString(4, distribuidor.getTelefonoMovil());
					ps.setString(5, distribuidor.getDireccion());
					ps.setString(6, distribuidor.getCodigoPostal());
					ps.setString(7, distribuidor.getCiudad());
					ps.setString(8, distribuidor.getPais());
					ps.setString(9, distribuidor.getEmail());
					ps.setString(10, distribuidor.getDireccionFacturacion());
					ps.setString(11, distribuidor.getCodigoPostalFacturacion());
					ps.setString(12, distribuidor.getCiudadFacturacion());
					ps.setString(13, distribuidor.getPaisFacturacion());
					ps.setString(14, distribuidor.getNombreFacturacion());
					ps.setString(15, distribuidor.getTelefonoFacturacion());
					ps.setString(16, distribuidor.getTelefonoMovilFacturacion());
					ps.setString(17, distribuidor.getEmailFacturacion());
					
					return ps;
				}
			}
			,kh);
			distribuidor.setIdDistribuidor(kh.getKey().toString());
			
		} catch (Exception e) {
			throw new DAOInsertException(e);
		}			
	}
	
	
	public void update(DistribuidorVO distribuidor) throws DAOException,
			DAOUpdateException {
		try {
			getJdbcTemplate().update(SQL_UPDATE, new Object[]{
					distribuidor.getNombre(),
					distribuidor.getTelefono(),
					distribuidor.getTelefonoMovil(),
					distribuidor.getDireccion(),
					distribuidor.getCodigoPostal(),
					distribuidor.getCiudad(),
					distribuidor.getPais(),
					distribuidor.getEmail(),
					distribuidor.getDireccionFacturacion(),
					distribuidor.getCodigoPostalFacturacion(),
					distribuidor.getCiudadFacturacion(),
					distribuidor.getPaisFacturacion(),
					distribuidor.getNombreFacturacion(),
					distribuidor.getTelefonoFacturacion(),
					distribuidor.getTelefonoMovilFacturacion(),
					distribuidor.getEmailFacturacion(),							
					distribuidor.getIdDistribuidor()});
		} catch(Exception e) {
			throw new DAOUpdateException(e);
		}
		
	}
	public void delete(DistribuidorVO distribuidor) throws DAOException,
			DAODeleteException {
		try {
			getJdbcTemplate().update(SQL_DELETE, new Object[]{distribuidor.getIdDistribuidor()});
		} catch (Exception e) {
			throw new DAODeleteException(e);
		}
		
	}
	public List<DistribuidorVO> findByCriterio(CriterioDistribuidorVO criterio)
			throws DAOException {
		try {
			StringBuffer sb = new StringBuffer(SQL_FIND_CRITERIO);
			ArrayList params = new ArrayList();
			String cadena = " where ";
			if (!StringUtil.isNullOrBlank(criterio.getIdDistribuidor())) {
				sb.append(cadena + "id_distribuidor = ?");
				cadena = " and ";
				params.add(criterio.getIdDistribuidor());
			}
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
			
			return getJdbcTemplate().query(sb.toString(), params.toArray(), new DistribuidorMapper());
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	public DistribuidorVO findById(DistribuidorVO distribuidor) throws DAOException {
		try {
			return (DistribuidorVO) getJdbcTemplate().queryForObject(SQL_FIND_ID, new Object[]{distribuidor.getIdDistribuidor()}, new DistribuidorMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	public List<DistribuidorVO> findByCanal(CanalVO canal) throws DAOException {
		try {
			StringBuffer sb = new StringBuffer(SQL_FIND_BY_CANAL);
			ArrayList params = new ArrayList();
			params.add(canal.getIdCanal());
			return getJdbcTemplate().query(sb.toString(), params.toArray(), new DistribuidorMapper());
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	public void bloquear(DistribuidorVO distribuidor) throws DAOException {
		try {
			getJdbcTemplate().update(SQL_BLOQUEAR, new Object[]{distribuidor.getIdDistribuidor()});
		} catch (Exception e) {
			throw new DAOException(e);
		}

	}
	public void desbloquear(DistribuidorVO distribuidor) throws DAOException {
		try {
			getJdbcTemplate().update(SQL_DESBLOQUEAR, new Object[]{distribuidor.getIdDistribuidor()});
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
	}
	public void updateCanal(String idLead) throws DAOException {
		try {
			getJdbcTemplate().update(SQL_UPDATE_CANAL, new Object[]{idLead,idLead});
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
	}
}


