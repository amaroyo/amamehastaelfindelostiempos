package es.oyssen.mrm.negocio.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import es.oyssen.mrm.negocio.vo.EmpresaVO;
import es.oyssen.mrm.util.StringUtil;

public class EmpresaMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		EmpresaVO o = new EmpresaVO();
		o.setIdEmpresa(StringUtil.nullToString(rs.getString("id_empresa")));
		o.setNombre(StringUtil.nullToString(rs.getString("nombre")));
		o.setOrgN(StringUtil.nullToString(rs.getString("orgn")));
		o.setTelefono(StringUtil.nullToString(rs.getString("telefono")));
		o.setTelefonoMovil(StringUtil.nullToString(rs.getString("telefonoMovil")));
		o.setEmail(StringUtil.nullToString(rs.getString("email")));
		o.setInformacionContacto(StringUtil.nullToString(rs.getString("nombre_persona_contacto")));
		o.setDireccion(StringUtil.nullToString(rs.getString("direccion")));
		return o;
	}

	

}
