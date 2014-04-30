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
import es.oyssen.mrm.negocio.dao.DAOComerciales;
import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOUpdateException;
import es.oyssen.mrm.negocio.dao.rowmappers.ComercialMapper;
import es.oyssen.mrm.negocio.vo.CanalVO;
import es.oyssen.mrm.negocio.vo.ComercialVO;
import es.oyssen.mrm.negocio.vo.CriterioComercialVO;
import es.oyssen.mrm.negocio.vo.DistribuidorVO;
import es.oyssen.mrm.util.StringUtil;


public class MySqlDAOComercialesImpl extends DAOBase implements DAOComerciales{
	
	private static String SQL_INSERT = "insert into comerciales (id_distribuidor, id_canal, nombre, telefono, telefonoMovil, direccion, codigoPostal, ciudad, pais, email, comentarios) values (?,?,?,?,?,?,?,?,?,?,?)";
	private static String SQL_UPDATE = "update comerciales set nombre=?, telefono=?, telefonoMovil=?, direccion=?, codigoPostal=?, ciudad=?, pais=?, email=?, comentarios=? where id_comercial = ?";
	private static String SQL_DELETE = "delete from comerciales where id_comercial = ?";
	private static String SQL_FIND_ID = "select * from comerciales where id_comercial = ?";
	private static String SQL_FIND_CRITERIO = "select * from comerciales";	
	//private static String SQL_FIND_BY_DISTRIBUIDOR = "select * from comerciales where id_comercial in (select id_comercial from leads where id_distribuidor = ?);";
	private static String SQL_FIND_BY_DISTRIBUIDOR = "select * from comerciales where  id_distribuidor = ?;";
	//private static String SQL_FIND_BY_CANAL = "select * from comerciales where id_comercial in (select id_comercial from leads where id_canal = ?);";
	private static String SQL_FIND_BY_CANAL = "select * from comerciales where id_canal = ?;";
	private static String SQL_UPDATE_CANAL = "update comerciales set id_canal = (select id_canal from leads where id_lead=?) where id_comercial = (select id_comercial from leads where id_lead=?);";
	
	
	public void insert(final ComercialVO comercial) throws DAOException,
			DAOInsertException {
		try{
			KeyHolder kh = new GeneratedKeyHolder();
			getJdbcTemplate().update(new PreparedStatementCreator() {
				
				public PreparedStatement createPreparedStatement(Connection conn)
						throws SQLException {
					PreparedStatement ps = conn.prepareStatement(SQL_INSERT, new String[]{"id_comercial"});
					ps.setString(1, comercial.getIdDistribuidor());
					ps.setString(2, comercial.getIdCanal());
					ps.setString(3, comercial.getNombre());
					ps.setString(4, comercial.getTelefono());
					ps.setString(5, comercial.getTelefonoMovil());
					ps.setString(6, comercial.getDireccion());
					ps.setString(7, comercial.getCodigoPostal());
					ps.setString(8, comercial.getCiudad());
					ps.setString(9, comercial.getPais());
					ps.setString(10, comercial.getEmail());
					ps.setString(11, comercial.getComentarios());					
					return ps;
				}
			}
			,kh);
			comercial.setIdComercial(kh.getKey().toString());
			
		} catch (Exception e) {
			throw new DAOInsertException(e);
		}			
	}
	
	
	public void update(ComercialVO comercial) throws DAOException,
			DAOUpdateException {
		try {
			getJdbcTemplate().update(SQL_UPDATE, new Object[]{
					comercial.getNombre(),
					comercial.getTelefono(),
					comercial.getTelefonoMovil(),
					comercial.getDireccion(),
					comercial.getCodigoPostal(),
					comercial.getCiudad(),
					comercial.getPais(),
					comercial.getEmail(),
					comercial.getComentarios(),
					comercial.getIdComercial()});
		} catch(Exception e) {
			throw new DAOUpdateException(e);
		}
		
	}
	public void delete(ComercialVO comercial) throws DAOException,
			DAODeleteException {
		try {
			getJdbcTemplate().update(SQL_DELETE, new Object[]{comercial.getIdComercial()});
		} catch (Exception e) {
			throw new DAODeleteException(e);
		}
		
	}
	public List<ComercialVO> findByCriterio(CriterioComercialVO criterio)
			throws DAOException {
		try {
			StringBuffer sb = new StringBuffer(SQL_FIND_CRITERIO);
			String cadena = " where ";
			ArrayList params = new ArrayList();
			if (!StringUtil.isNullOrBlank(criterio.getIdComercial())) {
				sb.append(cadena + "id_comercial = ?");
				cadena = " and ";
				params.add(criterio.getIdComercial());
			}
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
			if (!StringUtil.isNullOrBlank(criterio.getComentarios())) {
				sb.append(cadena + "upper(comentarios) = upper(?)");
				cadena = " and ";
				params.add(criterio.getComentarios());
			}
			return getJdbcTemplate().query(sb.toString(), params.toArray(), new ComercialMapper());
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	public ComercialVO findById(ComercialVO comercial) throws DAOException {
		try {
			return (ComercialVO) getJdbcTemplate().queryForObject(SQL_FIND_ID, new Object[]{comercial.getIdComercial()}, new ComercialMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	public List<ComercialVO> findByDistribuidor(DistribuidorVO distribuidor) throws DAOException {
		try {
			StringBuffer sb = new StringBuffer(SQL_FIND_BY_DISTRIBUIDOR);
			ArrayList params = new ArrayList();
			params.add(distribuidor.getIdDistribuidor());
			return getJdbcTemplate().query(sb.toString(), params.toArray(), new ComercialMapper());
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	public List<ComercialVO> findByCanal(CanalVO canal) throws DAOException {
		try {
			StringBuffer sb = new StringBuffer(SQL_FIND_BY_CANAL);
			ArrayList params = new ArrayList();
			params.add(canal.getIdCanal());
			return getJdbcTemplate().query(sb.toString(), params.toArray(), new ComercialMapper());
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


