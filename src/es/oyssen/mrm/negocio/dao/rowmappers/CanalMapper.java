package es.oyssen.mrm.negocio.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import es.oyssen.mrm.negocio.vo.CanalVO;
import es.oyssen.mrm.util.StringUtil;


public class CanalMapper implements RowMapper {

    public static final String FIELD_ID = "id_canal";    
    public static final String FIELD_NOMBRE = "nombre";
    public static final String FIELD_TELEFONO = "telefono";
    public static final String FIELD_TELEFONO_MOVIL = "telefonoMovil";
    public static final String FIELD_DIRECCION = "direccion";
    public static final String FIELD_CODIGO_POSTAL = "codigoPostal";
    public static final String FIELD_CIUDAD = "ciudad";
    public static final String FIELD_PAIS = "pais";
    public static final String FIELD_EMAIL = "email";   
    public static final String FIELD_DIRECCION_FACTURACION = "direccionFacturacion";
    public static final String FIELD_CODIGO_POSTAL_FACTURACION = "codigoPostalFacturacion";
    public static final String FIELD_CIUDAD_FACTURACION = "ciudadFacturacion";
    public static final String FIELD_PAIS_FACTURACION = "paisFacturacion";
    public static final String FIELD_NOMBRE_FACTURACION = "nombreFacturacion";
    public static final String FIELD_TELEFONO_FACTURACION = "telefonoFacturacion";
    public static final String FIELD_TELEFONO_MOVIL_FACTURACION = "telefonoMovilFacturacion";
    public static final String FIELD_EMAIL_FACTURACION = "emailFacturacion";
    public static final String FIELD_DIRECCION_WEB = "direccionWeb";
    public static final String FIELD_MRM_RESPONSABLE = "mrmResponsable";
    
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	CanalVO o = new CanalVO();
    	o.setIdCanal(StringUtil.nullToString(rs.getString(FIELD_ID)));
    	o.setNombre(StringUtil.nullToString(rs.getString(FIELD_NOMBRE)));
    	o.setTelefono(StringUtil.nullToString(rs.getString(FIELD_TELEFONO)));
    	o.setTelefonoMovil(StringUtil.nullToString(rs.getString(FIELD_TELEFONO_MOVIL)));
    	o.setDireccion(StringUtil.nullToString(rs.getString(FIELD_DIRECCION)));
    	o.setCodigoPostal(StringUtil.nullToString(rs.getString(FIELD_CODIGO_POSTAL)));
    	o.setCiudad(StringUtil.nullToString(rs.getString(FIELD_CIUDAD)));
    	o.setPais(StringUtil.nullToString(rs.getString(FIELD_PAIS)));
    	o.setEmail(StringUtil.nullToString(rs.getString(FIELD_EMAIL)));
    	o.setDireccionFacturacion(StringUtil.nullToString(rs.getString(FIELD_DIRECCION_FACTURACION)));
    	o.setCodigoPostalFacturacion(StringUtil.nullToString(rs.getString(FIELD_CODIGO_POSTAL_FACTURACION)));
    	o.setCiudadFacturacion(StringUtil.nullToString(rs.getString(FIELD_CIUDAD_FACTURACION)));
    	o.setPaisFacturacion(StringUtil.nullToString(rs.getString(FIELD_PAIS_FACTURACION)));
    	o.setNombreFacturacion(StringUtil.nullToString(rs.getString(FIELD_NOMBRE_FACTURACION)));
    	o.setTelefonoFacturacion(StringUtil.nullToString(rs.getString(FIELD_TELEFONO_FACTURACION)));
    	o.setTelefonoMovilFacturacion(StringUtil.nullToString(rs.getString(FIELD_TELEFONO_MOVIL_FACTURACION)));
    	o.setEmailFacturacion(StringUtil.nullToString(rs.getString(FIELD_EMAIL_FACTURACION)));
    	o.setDireccionWeb(StringUtil.nullToString(rs.getString(FIELD_DIRECCION_WEB)));
    	o.setMrmResponsable(StringUtil.nullToString(rs.getString(FIELD_MRM_RESPONSABLE)));
    	
		return o;
	}
}

