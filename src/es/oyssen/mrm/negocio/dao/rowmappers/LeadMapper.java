package es.oyssen.mrm.negocio.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import es.oyssen.mrm.negocio.vo.CanalVO;
import es.oyssen.mrm.negocio.vo.ComercialVO;
import es.oyssen.mrm.negocio.vo.DistribuidorVO;
import es.oyssen.mrm.negocio.vo.EmpresaVO;
import es.oyssen.mrm.negocio.vo.EstadoVO;
import es.oyssen.mrm.negocio.vo.LeadVO;
import es.oyssen.mrm.negocio.vo.MarketingActivityVO;
import es.oyssen.mrm.negocio.vo.ResponsableVO;
import es.oyssen.mrm.negocio.vo.ServicioVO;
import es.oyssen.mrm.util.StringUtil;

public class LeadMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		LeadVO o = new LeadVO();
		o.setComentarios(StringUtil.nullToString(rs.getString("comentarios")));
		ComercialVO comercial = new ComercialVO();
		comercial.setIdComercial(rs.getString("id_comercial"));
		comercial.setNombre(rs.getString("comercial_nombre"));
		o.setComercial(comercial );
		EmpresaVO empresa =new EmpresaVO();
		empresa.setIdEmpresa(rs.getString("id_empresa"));
		empresa.setNombre(rs.getString("empresa_nombre"));
		empresa.setOrgN(rs.getString("empresa_orgn"));
		
		try {
			empresa.setInformacionContacto(rs.getString("empresa_nombre_persona_contacto"));
			empresa.setTelefonoMovil(rs.getString("empresa_telefono_movil"));
			empresa.setEmail(rs.getString("empresa_email"));
			empresa.setDireccion(rs.getString("empresa_direccion"));
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} 
		
		o.setEmpresa(empresa );
		EstadoVO estado = new EstadoVO();
		estado.setIdEstado(rs.getString("id_estado"));		
		estado.setNombre(rs.getString("estado_nombre"));
		o.setEstado(estado );
		o.setFechaCreacion(rs.getDate("fecha_creacion"));
		o.setFechaVisita(rs.getDate("fecha_visita"));
		o.setIdLead(rs.getString("id_lead"));
		ResponsableVO responsable = new ResponsableVO();
		responsable.setIdResponsable(rs.getString("id_responsable"));
		responsable.setNombre(rs.getString("responsable_nombre"));
		o.setResponsable(responsable );
		ServicioVO servicio = new ServicioVO();
		servicio.setIdServicio(rs.getString("id_servicio"));
		servicio.setNombre(rs.getString("servicio_nombre"));
		o.setServicio(servicio );
		MarketingActivityVO marketingActivity = new MarketingActivityVO();
		marketingActivity.setIdMarketingActivity(rs.getString("id_marketing_activity"));
		marketingActivity.setNombre(rs.getString("marketing_nombre"));
		o.setMarketingActivity(marketingActivity);		
		o.setDistribuidor(new DistribuidorVO(rs.getString("id_distribuidor"), rs.getString("distribuidor_nombre")));
		
		o.setCanal(new CanalVO(rs.getString("id_canal"), rs.getString("canal_nombre")));
		
		o.setUsuariosPotenciales(StringUtil.nullToString(rs.getString("usuariosPotenciales")));
		o.setUsuarios(StringUtil.nullToString(rs.getString("usuarios")));
		
		return o;
	}

}
