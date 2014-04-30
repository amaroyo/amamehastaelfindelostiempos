package es.oyssen.mrm.negocio.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import es.oyssen.mrm.negocio.vo.ServicioVO;
import es.oyssen.mrm.util.StringUtil;

public class ServicioMapper implements RowMapper {

    public static final String FIELD_ID = "id_servicio";    
    public static final String FIELD_NOMBRE = "nombre";
    public static final String FIELD_PERSONA_CONTACTO = "personaContacto";
    public static final String FIELD_PROVEEDOR = "proveedor";
	
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		ServicioVO o = new ServicioVO();
		o.setIdServicio(StringUtil.nullToString(rs.getString(FIELD_ID)));
		o.setNombre(StringUtil.nullToString(rs.getString(FIELD_NOMBRE)));
		o.setPersonaContacto(StringUtil.nullToString(rs.getString(FIELD_PERSONA_CONTACTO)));
		o.setProveedor(StringUtil.nullToString(rs.getString(FIELD_PROVEEDOR)));
		return o;
	}

}
