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
import es.oyssen.mrm.negocio.dao.DAOResponsables;
import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOUpdateException;
import es.oyssen.mrm.negocio.dao.rowmappers.ResponsableMapper;
import es.oyssen.mrm.negocio.vo.CriterioResponsableVO;
import es.oyssen.mrm.negocio.vo.ResponsableVO;
import es.oyssen.mrm.util.StringUtil;


public class MySqlDAOResponsablesImpl extends DAOBase implements DAOResponsables{
	
	private static String SQL_INSERT = "insert into responsables (nombre, telefono, telefonoMovil, direccion, codigoPostal, ciudad, pais, email, comentarios) values (?,?,?,?,?,?,?,?,?)";
	private static String SQL_UPDATE = "update responsables set nombre=?, telefono=?, telefonoMovil=?, direccion=?, codigoPostal=?, ciudad=?, pais=?, email=?, comentarios=? where id_responsable = ?";
	private static String SQL_DELETE = "delete from responsables where id_responsable = ?";
	private static String SQL_FIND_ID = "select * from responsables where id_responsable = ?";
	private static String SQL_FIND_CRITERIO = "select * from responsables";
	
	
	public void insert(final ResponsableVO responsable) throws DAOException,
			DAOInsertException {
		try{
			KeyHolder kh = new GeneratedKeyHolder();
			getJdbcTemplate().update(new PreparedStatementCreator() {
				
				public PreparedStatement createPreparedStatement(Connection conn)
						throws SQLException {
					PreparedStatement ps = conn.prepareStatement(SQL_INSERT, new String[]{"id_responsable"});
					ps.setString(1, responsable.getNombre());
					ps.setString(2, responsable.getTelefono());
					ps.setString(3, responsable.getTelefonoMovil());
					ps.setString(4, responsable.getDireccion());
					ps.setString(5, responsable.getCodigoPostal());
					ps.setString(6, responsable.getCiudad());
					ps.setString(7, responsable.getPais());
					ps.setString(8, responsable.getEmail());
					ps.setString(9, responsable.getComentarios());
					return ps;
				}
			}
			,kh);
			responsable.setIdResponsable(kh.getKey().toString());
			
		} catch (Exception e) {
			throw new DAOInsertException(e);
		}			
	}
	
	public void update(ResponsableVO responsable) throws DAOException,
			DAOUpdateException {
		try {
			getJdbcTemplate().update(SQL_UPDATE, new Object[]{
					responsable.getNombre(),
					responsable.getTelefono(),
					responsable.getTelefonoMovil(),
					responsable.getDireccion(),
					responsable.getCodigoPostal(),
					responsable.getCiudad(),
					responsable.getPais(),
					responsable.getEmail(),
					responsable.getComentarios(),
					responsable.getIdResponsable()});
		} catch(Exception e) {
			throw new DAOUpdateException(e);
		}
		
	}
	
	public void delete(ResponsableVO responsable) throws DAOException,
			DAODeleteException {
		try {
			getJdbcTemplate().update(SQL_DELETE, new Object[]{responsable.getIdResponsable()});
		} catch (Exception e) {
			throw new DAODeleteException(e);
		}
		
	}
	
	public List<ResponsableVO> findByCriterio(CriterioResponsableVO criterio)
			throws DAOException {
		try {
			StringBuffer sb = new StringBuffer(SQL_FIND_CRITERIO);
			String cadena = " where ";
			ArrayList params = new ArrayList();
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
			return getJdbcTemplate().query(sb.toString(), params.toArray(), new ResponsableMapper());
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	public ResponsableVO findById(ResponsableVO responsable) throws DAOException {
		try {
			return (ResponsableVO) getJdbcTemplate().queryForObject(SQL_FIND_ID, new Object[]{responsable.getIdResponsable()}, new ResponsableMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

}


