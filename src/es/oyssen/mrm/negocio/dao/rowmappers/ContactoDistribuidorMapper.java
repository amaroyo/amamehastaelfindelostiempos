package es.oyssen.mrm.negocio.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import es.oyssen.mrm.negocio.vo.ContactoDistribuidorVO;
import es.oyssen.mrm.util.StringUtil;


public class ContactoDistribuidorMapper implements RowMapper {

	public static final String FIELD_ID_CONTACTO = "id_contacto";
    public static final String FIELD_ID_DISTRIBUIDOR = "id_distribuidor";    
    public static final String FIELD_NOMBRE = "nombre";
    public static final String FIELD_TELEFONO = "telefono";
    public static final String FIELD_TELEFONO_MOVIL = "telefonoMovil";
    public static final String FIELD_DIRECCION = "direccion";
    public static final String FIELD_CODIGO_POSTAL = "codigoPostal";
    public static final String FIELD_CIUDAD = "ciudad";
    public static final String FIELD_PAIS = "pais";
    public static final String FIELD_EMAIL = "email";
    public static final String FIELD_CARGO = "cargo";
    public static final String FIELD_COMENTARIOS = "comentarios";
   


    
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	ContactoDistribuidorVO o = new ContactoDistribuidorVO();
    	o.setIdContacto(StringUtil.nullToString(rs.getString(FIELD_ID_CONTACTO)));
    	o.setIdDistribuidor(StringUtil.nullToString(rs.getString(FIELD_ID_DISTRIBUIDOR)));
    	o.setNombre(StringUtil.nullToString(rs.getString(FIELD_NOMBRE)));
    	o.setTelefono(StringUtil.nullToString(rs.getString(FIELD_TELEFONO)));
    	o.setTelefonoMovil(StringUtil.nullToString(rs.getString(FIELD_TELEFONO_MOVIL)));
    	o.setDireccion(StringUtil.nullToString(rs.getString(FIELD_DIRECCION)));
    	o.setCodigoPostal(StringUtil.nullToString(rs.getString(FIELD_CODIGO_POSTAL)));
    	o.setCiudad(StringUtil.nullToString(rs.getString(FIELD_CIUDAD)));
    	o.setPais(StringUtil.nullToString(rs.getString(FIELD_PAIS)));
    	o.setEmail(StringUtil.nullToString(rs.getString(FIELD_EMAIL)));
    	o.setCargo(StringUtil.nullToString(rs.getString(FIELD_CARGO)));
    	o.setComentarios(StringUtil.nullToString(rs.getString(FIELD_COMENTARIOS)));
		return o;
	}
}

