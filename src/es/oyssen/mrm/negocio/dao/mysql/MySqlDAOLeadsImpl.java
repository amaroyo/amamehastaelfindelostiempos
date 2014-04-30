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
import es.oyssen.mrm.negocio.dao.DAOLeads;
import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOUpdateException;
import es.oyssen.mrm.negocio.dao.rowmappers.LeadMapper;
import es.oyssen.mrm.negocio.vo.ComercialVO;
import es.oyssen.mrm.negocio.vo.CriterioLeadVO;
import es.oyssen.mrm.negocio.vo.EmpresaVO;
import es.oyssen.mrm.negocio.vo.LeadVO;
import es.oyssen.mrm.negocio.vo.PuntoVentaVO;
import es.oyssen.mrm.util.StringUtil;


public class MySqlDAOLeadsImpl extends DAOBase implements DAOLeads{

	private static String SQL_INSERT = "insert into leads (fecha_visita, comentarios, fecha_creacion, id_estado,id_servicio,id_comercial,id_responsable,id_empresa, id_marketing_activity, id_distribuidor, id_canal, usuariosPotenciales, usuarios) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static String SQL_UPDATE = "update leads set ";	
	//private static String SQL_UPDATE = "update leads set id_canal=?, id_servicio=?, id_distribuidor=?, id_comercial=?, id_responsable=?, id_empresa=?, fecha_visita=?, id_estado=?,id_marketing_activity=?, comentarios=? where id_lead = ?";
//	private static String SQL_UPDATE = "update table leads set fecha_visita=?,comentarios=?,id_estado=?,id_servicio=?,id_comercial=?,id_responsable=?,id_empresa=?, id_marketing_update=?, id_distribuidor=? where id_lead = ?";
	private static String SQL_UPDATE_FECHA_MODIFICACION = "update leads set fecha_visita=? where id_lead=?";	
	private static String SQL_DELETE = "delete from leads where id_lead = ?";
	private static String SQL_DELETE_BY_EMPRESA = "delete from leads where id_empresa = ?";
	private static String SQL_FIND_ID = "select l.*, d.nombre distribuidor_nombre, m.nombre marketing_nombre ,s.nombre servicio_nombre, c.nombre comercial_nombre, r.nombre responsable_nombre, es.nombre estado_nombre, e.nombre empresa_nombre, e.orgn empresa_orgn, ca.nombre canal_nombre from leads l left join marketing_activities m on m.id_marketing_activity = l.id_marketing_activity left join estados es on es.id_estado = l.id_estado left join servicios s on s.id_servicio = l.id_servicio left join comerciales c on c.id_comercial = l.id_comercial left join responsables r on r.id_responsable = l.id_responsable left join empresas e on e.id_empresa = l.id_empresa left join distribuidores d on d.id_distribuidor = l.id_distribuidor left join canales ca on ca.id_canal = l.id_canal where l.id_lead = ?";
	private static String SQL_FIND_CRITERIO = "select l.*, d.nombre distribuidor_nombre, m.nombre marketing_nombre, s.nombre servicio_nombre, c.nombre comercial_nombre, r.nombre responsable_nombre, es.nombre estado_nombre, e.nombre empresa_nombre, e.orgn empresa_orgn, ca.nombre canal_nombre, e.nombre_persona_contacto empresa_nombre_persona_contacto, e.telefonoMovil empresa_telefono_movil, e.email empresa_email, e.direccion empresa_direccion from leads l left join marketing_activities m on m.id_marketing_activity = l.id_marketing_activity left join estados es on es.id_estado = l.id_estado left join servicios s on s.id_servicio = l.id_servicio left join comerciales c on c.id_comercial = l.id_comercial left join responsables r on r.id_responsable = l.id_responsable left join empresas e on e.id_empresa = l.id_empresa left join distribuidores d on d.id_distribuidor = l.id_distribuidor left join canales ca on ca.id_canal = l.id_canal";

	
	public LeadVO findById(LeadVO lead) throws DAOException {
		try {
			return (LeadVO) getJdbcTemplate().queryForObject(SQL_FIND_ID, new Object[]{lead.getIdLead()}, new LeadMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	public void insert(final LeadVO lead) throws DAOException, DAOInsertException {
		try{
			KeyHolder kh = new GeneratedKeyHolder();
			getJdbcTemplate().update(new PreparedStatementCreator() {
				
				public PreparedStatement createPreparedStatement(Connection conn)
						throws SQLException {
					PreparedStatement ps = conn.prepareStatement(SQL_INSERT, new String[]{"id_lead"});
					//ps.setDate(1, (lead.getFechaVisita() != null) ? new java.sql.Date(lead.getFechaVisita().getTime()) : null);
					ps.setDate(1, new java.sql.Date(lead.getFechaCreacion().getTime()));
					ps.setString(2, lead.getComentarios());
					ps.setDate(3, new java.sql.Date(lead.getFechaCreacion().getTime()));
					ps.setString(4, (lead.getEstado() != null) ? lead.getEstado().getIdEstado() : null);
					ps.setString(5, (lead.getServicio() != null) ? lead.getServicio().getIdServicio() : null);
					ps.setString(6, (lead.getComercial() != null) ? lead.getComercial().getIdComercial() : null);
					ps.setString(7, (lead.getResponsable() != null) ? lead.getResponsable().getIdResponsable() : null);
					ps.setString(8, (lead.getEmpresa() != null) ? lead.getEmpresa().getIdEmpresa() : null);	
					ps.setString(9, (lead.getMarketingActivity()!= null) ? lead.getMarketingActivity().getIdMarketingActivity() : null);
					ps.setString(10, (lead.getDistribuidor()!= null) ? lead.getDistribuidor().getIdDistribuidor() : null);
					ps.setString(11, (lead.getCanal()!= null) ? lead.getCanal().getIdCanal() : null);
					ps.setString(12, lead.getUsuariosPotenciales());
					ps.setString(13, lead.getUsuarios());
					return ps;
				}
			}
			,kh);
			lead.setIdLead(kh.getKey().toString());

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOInsertException(e);
		}	
		
	}

	public void update(LeadVO lead) throws DAOException, DAOUpdateException {
		try {
			
			java.util.Date utilDate = new java.util.Date();
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			
			
			StringBuffer sb = new StringBuffer(SQL_UPDATE);
			String cadena = "";
			if (!StringUtil.isNullOrBlank(lead.getCanal().getIdCanal())){
				sb.append(cadena+"id_canal="+lead.getCanal().getIdCanal());
				cadena = ", ";				
			}
			if (!StringUtil.isNullOrBlank(lead.getServicio().getIdServicio())){
				sb.append(cadena+"id_servicio="+lead.getServicio().getIdServicio());
				cadena = ", ";				
			}
			if (!StringUtil.isNullOrBlank(lead.getDistribuidor().getIdDistribuidor())){
				sb.append(cadena+"id_distribuidor="+lead.getDistribuidor().getIdDistribuidor());
				cadena = ", ";				
			}
			if (!StringUtil.isNullOrBlank(lead.getComercial().getIdComercial())){
				sb.append(cadena+"id_comercial="+lead.getComercial().getIdComercial());
				cadena = ", ";				
			}
			if (!StringUtil.isNullOrBlank(lead.getResponsable().getIdResponsable())){
				sb.append(cadena+"id_responsable="+lead.getResponsable().getIdResponsable());
				cadena = ", ";				
			}
			if (!StringUtil.isNullOrBlank(lead.getEmpresa().getIdEmpresa())){
				sb.append(cadena+"id_empresa="+lead.getEmpresa().getIdEmpresa());
				cadena = ", ";				
			}
		
			sb.append(cadena+"fecha_visita=?, id_estado=?,id_marketing_activity=?, comentarios=?, usuariosPotenciales=?, usuarios=?  where id_lead = ?");
			
			getJdbcTemplate().update(sb.toString(), new Object[]{
					sqlDate,
					lead.getEstado().getIdEstado(),					
					lead.getMarketingActivity().getIdMarketingActivity(),
					lead.getComentarios(),
					lead.getUsuariosPotenciales(),
					lead.getUsuarios(),
					lead.getIdLead()});
	
			
			
			
//			getJdbcTemplate().update(SQL_UPDATE, new Object[]{
//					lead.getFechaVisita(),
//					lead.getComentarios(),
//					lead.getEstado().getIdEstado(),
//					lead.getComercial().getIdComercial(),
//					lead.getResponsable().getIdResponsable(),
//					lead.getEmpresa().getIdEmpresa(),
//					lead.getMarketingActivity().getIdMarketingActivity(),
//					lead.getDistribuidor().getIdDistribuidor(),
//					lead.getIdLead()});
		} catch (Exception e) {
			throw new DAOException(e);
		}	
	}

	public void delete(LeadVO lead) throws DAOException, DAODeleteException {
		try {
			getJdbcTemplate().update(SQL_DELETE, new Object[]{
					lead.getIdLead()});
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	public List<LeadVO> findByComercial(ComercialVO comercial)
			throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<LeadVO> findByPuntoVenta(PuntoVentaVO punto)
			throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<LeadVO> findByCriterio(CriterioLeadVO criterio)
			throws DAOException {
		StringBuffer sb = new StringBuffer(SQL_FIND_CRITERIO);		
		String cadena = " where ";
		ArrayList params = new ArrayList();
		try {
			if (!StringUtil.isNullOrBlank(criterio.getEmpresa())) {
				sb.append(cadena + "upper(l.id_empresa) = upper(?)");
				params.add(criterio.getEmpresa());
				cadena = " and ";
			}
			if (!StringUtil.isNullOrBlank(criterio.getComercial())) {
				sb.append(cadena + "upper(l.id_comercial) = upper(?)");
				params.add(criterio.getComercial());
				cadena = " and ";
			}			
			if (!StringUtil.isNullOrBlank(criterio.getEstado())) {
				sb.append(cadena + "upper(l.id_estado) = upper(?)");
				params.add(criterio.getEstado());
				cadena = " and ";
			}			
			if (!StringUtil.isNullOrBlank(criterio.getResponsable())) {
				sb.append(cadena + "upper(l.id_responsable) = upper(?)");
				params.add(criterio.getResponsable());
				cadena = " and ";
			}	
			if (!StringUtil.isNullOrBlank(criterio.getDistribuidor())) {
				sb.append(cadena + "upper(l.id_distribuidor) = upper(?)");
				params.add(criterio.getDistribuidor());
				cadena = " and ";
			}
			if (!StringUtil.isNullOrBlank(criterio.getServicio())) {
				sb.append(cadena + "upper(l.id_servicio) = upper(?)");
				params.add(criterio.getServicio());
				cadena = " and ";
			}
			if (!StringUtil.isNullOrBlank(criterio.getCanal())) {
				sb.append(cadena + "upper(l.id_canal) = upper(?)");
				params.add(criterio.getCanal());
				cadena = " and ";
			}
			if (criterio.getFiltrarEstadoWon() != null && criterio.getFiltrarEstadoWon()) {
				sb.append(cadena + "l.id_estado != 6");
				cadena = " and ";
			}
			if (criterio.getFiltrarEstadoLost() != null && criterio.getFiltrarEstadoLost()) {
				sb.append(cadena + "l.id_estado != 7");
				cadena = " and ";
			}
			if (!StringUtil.isNullOrBlank(criterio.getCreacionDesde())) {
				sb.append(cadena + "l.fecha_creacion >= ?");
				params.add(criterio.getCreacionDesde());
				cadena = " and ";
			}
			if (!StringUtil.isNullOrBlank(criterio.getCreacionHasta())) {
				sb.append(cadena + "l.fecha_creacion <= ?");
				params.add(criterio.getCreacionHasta());
				cadena = " and ";
			}
			if (!StringUtil.isNullOrBlank(criterio.getModificacionDesde())) {
				sb.append(cadena + "l.fecha_visita >= ?");
				params.add(criterio.getModificacionDesde());
				cadena = " and ";
			}
			if (!StringUtil.isNullOrBlank(criterio.getModificacionHasta())) {
				sb.append(cadena + "l.fecha_visita <= ?");
				params.add(criterio.getModificacionHasta());
				cadena = " and ";
			}
			if (!StringUtil.isNullOrBlank(criterio.getSupplier())) {
				sb.append(cadena + "l.id_servicio in (select id_servicio from servicios_usuarios where id_usuario=?)");
				params.add(criterio.getSupplier());
				cadena = " and ";
			}
			return getJdbcTemplate().query(sb.toString(), params.toArray(), new LeadMapper());
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	public void delete(EmpresaVO empresa) throws DAOException {
		try {
			getJdbcTemplate().update(SQL_DELETE_BY_EMPRESA, new Object[]{
					empresa.getIdEmpresa()});
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	public void updateFechaModificacion(LeadVO lead) throws DAOException, DAOUpdateException {
		try {
			
			java.util.Date utilDate = new java.util.Date();
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			
			getJdbcTemplate().update(SQL_UPDATE_FECHA_MODIFICACION, new Object[]{
					sqlDate,
					lead.getIdLead()});

		} catch (Exception e) {
			throw new DAOException(e);
		}	
	}
	
}


