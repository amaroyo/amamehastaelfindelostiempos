package es.oyssen.mrm.negocio.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import es.oyssen.mrm.negocio.vo.UsuarioPortafolioVO;
import es.oyssen.mrm.util.StringUtil;


public class UsuarioPortafolioMapper implements RowMapper {

	public static final String FIELD_ID = "id_usuario";
    public static final String FIELD_CORREO = "correo";
    public static final String FIELD_NOMBRE = "nombre";
    public static final String FIELD_APELLIDO1 = "apellido1";
    public static final String FIELD_APELLIDO2 = "apellido2";
    public static final String FIELD_DNI = "dni";
    public static final String FIELD_TELEFONO = "telefono";
    public static final String FIELD_ID_PORTAFOLIO= "id_portafolio";
    
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	UsuarioPortafolioVO o = new UsuarioPortafolioVO();
    	o.setIdUsuario(StringUtil.nullToString(rs.getString(FIELD_ID)));
    	o.setCorreo(StringUtil.nullToString(rs.getString(FIELD_CORREO)));
    	o.setNombre(StringUtil.nullToString(rs.getString(FIELD_NOMBRE)));
    	o.setApellido1(StringUtil.nullToString(rs.getString(FIELD_APELLIDO1)));
    	o.setApellido2(StringUtil.nullToString(rs.getString(FIELD_APELLIDO2)));
    	o.setDni(StringUtil.nullToString(rs.getString(FIELD_DNI)));
    	o.setTelefono(StringUtil.nullToString(rs.getString(FIELD_TELEFONO)));
    	o.setIdPortafolio(rs.getString(FIELD_ID_PORTAFOLIO));
    	
    	
		return o;
	}
}

