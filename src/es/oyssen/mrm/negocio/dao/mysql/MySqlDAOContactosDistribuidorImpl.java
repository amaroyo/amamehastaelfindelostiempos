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
import es.oyssen.mrm.negocio.dao.DAOContactosDistribuidor;
import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOUpdateException;
import es.oyssen.mrm.negocio.dao.rowmappers.ContactoDistribuidorMapper;
import es.oyssen.mrm.negocio.vo.ContactoDistribuidorVO;
import es.oyssen.mrm.negocio.vo.CriterioContactoDistribuidorVO;
import es.oyssen.mrm.negocio.vo.DistribuidorVO;
import es.oyssen.mrm.util.StringUtil;


public class MySqlDAOContactosDistribuidorImpl extends DAOBase implements DAOContactosDistribuidor{
	
	private static String SQL_INSERT = "insert into contactos_distribuidores (id_distribuidor, nombre, telefono, telefonoMovil, direccion, codigoPostal, ciudad, pais, email, cargo, comentarios) values (?,?,?,?,?,?,?,?,?,?,?)";
	private static String SQL_UPDATE = "update contactos_distribuidores set id_distribuidor=?, nombre=?, telefono=?, telefonoMovil=?, direccion=?, codigoPostal=?, ciudad=?, pais=?, email=?, cargo=?, comentarios=? where id_contacto = ?";
	private static String SQL_DELETE = "delete from contactos_distribuidores where id_contacto = ?";
	private static String SQL_DELETE_BY_DISTRIBUIDOR = "delete from contactos_distribuidores where id_distribuidor = ?";	
	private static String SQL_FIND_ID = "select * from contactos_distribuidores where id_contacto = ?";
	private static String SQL_FIND_CRITERIO = "select * from contactos_distribuidores";
	
	
	public void insert(final ContactoDistribuidorVO contacto) throws DAOException,
			DAOInsertException {
		try{
			KeyHolder kh = new GeneratedKeyHolder();
			getJdbcTemplate().update(new PreparedStatementCreator() {
				
				public PreparedStatement createPreparedStatement(Connection conn)
						throws SQLException {
					PreparedStatement ps = conn.prepareStatement(SQL_INSERT, new String[]{"id_contacto"});
					ps.setString(1, contacto.getIdDistribuidor());
					ps.setString(2, contacto.getNombre());
					ps.setString(3, contacto.getTelefono());
					ps.setString(4, contacto.getTelefonoMovil());
					ps.setString(5, contacto.getDireccion());
					ps.setString(6, contacto.getCodigoPostal());
					ps.setString(7, contacto.getCiudad());
					ps.setString(8, contacto.getPais());
					ps.setString(9, contacto.getEmail());
					ps.setString(10, contacto.getCargo());
					ps.setString(11, contacto.getComentarios());
					return ps;
				}
			}
			,kh);
			contacto.setIdContacto(kh.getKey().toString());
			
		} catch (Exception e) {
			throw new DAOInsertException(e);
		}			
	}
	
	public void update(ContactoDistribuidorVO contacto) throws DAOException,
			DAOUpdateException {
		try {
			getJdbcTemplate().update(SQL_UPDATE, new Object[]{
					contacto.getIdDistribuidor(),
					contacto.getNombre(),
					contacto.getTelefono(),
					contacto.getTelefonoMovil(),
					contacto.getDireccion(),
					contacto.getCodigoPostal(),
					contacto.getCiudad(),
					contacto.getPais(),
					contacto.getEmail(),
					contacto.getCargo(),
					contacto.getComentarios(),
					contacto.getIdContacto()});
		} catch(Exception e) {
			throw new DAOUpdateException(e);
		}
		
	}
	
	public void delete(ContactoDistribuidorVO contacto) throws DAOException,
			DAODeleteException {
		try {
			getJdbcTemplate().update(SQL_DELETE, new Object[]{contacto.getIdContacto()});
		} catch (Exception e) {
			throw new DAODeleteException(e);
		}
		
	}

	public void deleteByDistribuidor(DistribuidorVO distribuidor) throws DAOException,
			DAODeleteException {
		try {
			getJdbcTemplate().update(SQL_DELETE_BY_DISTRIBUIDOR, new Object[]{distribuidor.getIdDistribuidor()});
		} catch (Exception e) {
			throw new DAODeleteException(e);
		}

	}	
	
	public List<ContactoDistribuidorVO> findByCriterio(CriterioContactoDistribuidorVO criterio)
			throws DAOException {
		try {
			StringBuffer sb = new StringBuffer(SQL_FIND_CRITERIO);
			String cadena = " where ";
			ArrayList params = new ArrayList();
			if (!StringUtil.isNullOrBlank(criterio.getIdDistribuidor())) {
				sb.append(cadena + "id_distribuidor = " + criterio.getIdDistribuidor());
				cadena = " and ";;
			}
			if (!StringUtil.isNullOrBlank(criterio.getNombre())) {
				sb.append(cadena + "upper(nombre) = (?)");
				cadena = " and ";
				params.add("%" + criterio.getNombre() + "%");
			}
			if (!StringUtil.isNullOrBlank(criterio.getTelefono())) {
				sb.append(cadena + "upper(telefono) = (?)");
				cadena = " and ";
				params.add("%" + criterio.getTelefono() + "%");
			}
			if (!StringUtil.isNullOrBlank(criterio.getTelefonoMovil())) {
				sb.append(cadena + "upper(telefonoMovil) = (?)");
				cadena = " and ";
				params.add("%" + criterio.getTelefonoMovil() + "%");
			}
			if (!StringUtil.isNullOrBlank(criterio.getDireccion())) {
				sb.append(cadena + "upper(direccion) = (?)");
				cadena = " and ";
				params.add("%" + criterio.getDireccion() + "%");
			}
			if (!StringUtil.isNullOrBlank(criterio.getCodigoPostal())) {
				sb.append(cadena + "upper(codigoPostal) = (?)");
				cadena = " and ";
				params.add("%" + criterio.getCodigoPostal() + "%");
			}
			if (!StringUtil.isNullOrBlank(criterio.getCiudad())) {
				sb.append(cadena + "upper(ciudad) = (?)");
				cadena = " and ";
				params.add("%" + criterio.getCiudad() + "%");
			}
			if (!StringUtil.isNullOrBlank(criterio.getPais())) {
				sb.append(cadena + "upper(pais) = (?)");
				cadena = " and ";
				params.add("%" + criterio.getPais() + "%");
			}
			if (!StringUtil.isNullOrBlank(criterio.getEmail())) {
				sb.append(cadena + "upper(email) = (?)");
				cadena = " and ";
				params.add("%" + criterio.getEmail() + "%");
			}
			if (!StringUtil.isNullOrBlank(criterio.getCargo())) {
				sb.append(cadena + "upper(cargo) = (?)");
				cadena = " and ";
				params.add("%" + criterio.getCargo() + "%");
			}			
			if (!StringUtil.isNullOrBlank(criterio.getComentarios())) {
				sb.append(cadena + "upper(comentarios) = (?)");
				cadena = " and ";
				params.add("%" + criterio.getComentarios() + "%");
			}		
			return getJdbcTemplate().query(sb.toString(), params.toArray(), new ContactoDistribuidorMapper());
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	public ContactoDistribuidorVO findById(ContactoDistribuidorVO contacto) throws DAOException {
		try {
			return (ContactoDistribuidorVO) getJdbcTemplate().queryForObject(SQL_FIND_ID, new Object[]{contacto.getIdContacto()}, new ContactoDistribuidorMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

}


