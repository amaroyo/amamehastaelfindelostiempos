package es.oyssen.mrm.negocio.dao.rowmappers;

import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.springframework.jdbc.core.RowMapper;

import es.oyssen.mrm.negocio.vo.UsuarioTrabajoCampoVO;
import es.oyssen.mrm.negocio.vo.UsuarioVO;
import es.oyssen.mrm.util.StringUtil;


public class UsuarioTrabajoCampoMapper implements RowMapper {

	public static final String FIELD_ID_PORTAFOLIO = "id_portafolio";
	public static final String FIELD_ID_TRABAJO_DE_CAMPO = "id_trabajo_de_campo";
    public static final String FIELD_TRABAJO_DE_CAMPO = "trabajo_de_campo";
    public static final String FIELD_CORRECCION_TRABAJO= "correccion_trabajo";
    public static final String FIELD_FECHA_LIMITE= "fecha_limite";
    public static final String FIELD_ID_USUARIO= "id_usuario";
    public static final String FIELD_NOMBRE = "nombre";
    public static final String FIELD_APELLIDO1 = "apellido1";
    public static final String FIELD_APELLIDO2 = "apellido2";
    public static final String FIELD_DNI = "dni";
    

    
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	UsuarioTrabajoCampoVO o = new UsuarioTrabajoCampoVO();
    	
    	o.setIdPortafolio(StringUtil.nullToString(rs.getString(FIELD_ID_PORTAFOLIO)));
    	o.setIdTrabajoDeCampo(StringUtil.nullToString(rs.getString(FIELD_ID_TRABAJO_DE_CAMPO)));
    	o.setFechaLimite(StringUtil.nullToString(rs.getString(FIELD_FECHA_LIMITE)));
    	o.setIdUsuario(StringUtil.nullToString(rs.getString(FIELD_ID_USUARIO)));
    	o.setNombre(StringUtil.nullToString(rs.getString(FIELD_NOMBRE)));
    	o.setApellido1(StringUtil.nullToString(rs.getString(FIELD_APELLIDO1)));
    	o.setApellido2(StringUtil.nullToString(rs.getString(FIELD_APELLIDO2)));
    	o.setDni(StringUtil.nullToString(rs.getString(FIELD_DNI)));
    	
    	
    	try {

    		
        	InputStream datosTrabajo = rs.getBinaryStream(FIELD_TRABAJO_DE_CAMPO);
        	InputStream datosCorreccion = rs.getBinaryStream(FIELD_CORRECCION_TRABAJO);
        	byte[] trabajo;
        	byte[] correccion;
        	if(datosTrabajo != null) {
        		trabajo = IOUtils.toByteArray(datosTrabajo);
        	}
        	else{
        		trabajo = null;
        	}
        	if(datosCorreccion != null) {
        		correccion = IOUtils.toByteArray(datosCorreccion);
        	}
        	else{
        		correccion = null;
        	}
        	o.setTrabajoDeCampoFile(trabajo);
        	o.setCorreccionTrabajoFile(correccion);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return o;
	}
}

