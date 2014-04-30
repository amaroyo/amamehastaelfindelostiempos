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
import es.oyssen.mrm.negocio.dao.DAOEmpresas;
import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOUpdateException;
import es.oyssen.mrm.negocio.dao.rowmappers.EmpresaMapper;
import es.oyssen.mrm.negocio.vo.CriterioEmpresaVO;
import es.oyssen.mrm.negocio.vo.EmpresaVO;
import es.oyssen.mrm.util.StringUtil;

public class MySqlDAOEmpresasImpl extends DAOBase implements DAOEmpresas {
	
	private static String SQL_INSERT = "insert into empresas (nombre, orgn, telefono, telefonoMovil, email, nombre_persona_contacto, direccion) values (?,?,?,?,?,?,?)";
	private static String SQL_UPDATE = "update empresas set nombre=?,orgn=?,telefono=?,telefonoMovil=?,email=?,nombre_persona_contacto=?,direccion=? where id_empresa = ?";
	private static String SQL_DELETE = "delete from empresas where id_empresa = ?";
	private static String SQL_FIND_ID = "select * from empresas where id_empresa = ?";
	private static String SQL_FIND_ORGN = "select * from empresas where orgn = ?";
	private static String SQL_FIND_CRITERIO = "select * from empresas";

	
	public List<EmpresaVO> findByCriterio(CriterioEmpresaVO criterio)
			throws DAOException {
		StringBuffer sb = new StringBuffer(SQL_FIND_CRITERIO);		
		String cadena = " where ";
		ArrayList params = new ArrayList();
		try {
			if (!StringUtil.isNullOrBlank(criterio.getNombre())) {
				if (criterio.isBusquedaExacta()) {
					sb.append(cadena + "upper(nombre) = upper(?)");
					params.add(criterio.getNombre());
				} else {
					sb.append(cadena + "upper(nombre) like upper(?)");
					params.add("%" + criterio.getNombre() + "%");
				}				
				cadena = " and ";
			}
			if (!StringUtil.isNullOrBlank(criterio.getCiudad())) {
				if (criterio.isBusquedaExacta()) {
					sb.append(cadena + "upper(ciudad) = upper(?)");
					params.add(criterio.getCiudad());
				} else {
					sb.append(cadena + "upper(ciudad) like upper(?)");
					params.add("%" + criterio.getCiudad() + "%");
				}				
				cadena = " and ";
			}
			// Este bloque debe ir siempre al final
			if (!StringUtil.isNullOrBlank(criterio.getCanal())) {
				sb.append(cadena + "id_empresa in (select id_empresa from leads where id_canal = ?)");
				params.add(criterio.getCanal());
			}
			else if (!StringUtil.isNullOrBlank(criterio.getDistribuidor())) {
				sb.append(cadena + "id_empresa in (select id_empresa from leads where id_distribuidor = ?)");
				params.add(criterio.getDistribuidor());				
			}
			else if (!StringUtil.isNullOrBlank(criterio.getComercial())) {
				sb.append(cadena + "id_empresa in (select id_empresa from leads where id_comercial = ?)");
				params.add(criterio.getComercial());				
			}
			return getJdbcTemplate().query(sb.toString(), params.toArray(), new EmpresaMapper());
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	public EmpresaVO findById(EmpresaVO empresa) throws DAOException {
		try {
			return (EmpresaVO) getJdbcTemplate().queryForObject(SQL_FIND_ID, new Object[]{empresa.getIdEmpresa()}, new EmpresaMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
	}
	
	public EmpresaVO findByOrgn(EmpresaVO empresa) throws DAOException {
		try {
			return (EmpresaVO) getJdbcTemplate().queryForObject(SQL_FIND_ORGN, new Object[]{empresa.getOrgN()}, new EmpresaMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
	}

	public void insert(final EmpresaVO empresa) throws DAOException,
			DAOInsertException {
		try{
			KeyHolder kh = new GeneratedKeyHolder();
			getJdbcTemplate().update(new PreparedStatementCreator() {
				
				public PreparedStatement createPreparedStatement(Connection conn)
						throws SQLException {
					PreparedStatement ps = conn.prepareStatement(SQL_INSERT, new String[]{"id_empresa"});
					ps.setString(1, empresa.getNombre());
					ps.setString(2, empresa.getOrgN());
					ps.setString(3, empresa.getTelefono());
					ps.setString(4, empresa.getTelefonoMovil());
					ps.setString(5, empresa.getEmail());
					ps.setString(6, empresa.getInformacionContacto());
					ps.setString(7, empresa.getDireccion());
					return ps;
				}
			}
			,kh);
			empresa.setIdEmpresa(kh.getKey().toString());
			
		} catch (Exception e) {
			throw new DAOInsertException(e);
		}	
	}

	public void update(EmpresaVO empresa) throws DAOException,
			DAOUpdateException {
		try {
			getJdbcTemplate().update(SQL_UPDATE, new Object[]{
					empresa.getNombre(),
					empresa.getOrgN(),
					empresa.getTelefono(),
					empresa.getTelefonoMovil(),
					empresa.getEmail(),
					empresa.getInformacionContacto(),
					empresa.getDireccion(),
					empresa.getIdEmpresa()});
		} catch (Exception e) {
			throw new DAOException(e);
		}		
	}

	public void delete(EmpresaVO empresa) throws DAOException,
			DAODeleteException {		
		try {
			getJdbcTemplate().update(SQL_DELETE, new Object[]{empresa.getIdEmpresa()});
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

}
