package es.oyssen.mrm.negocio.dao.rowmappers;

import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.io.IOUtils;
import org.springframework.jdbc.core.RowMapper;

import es.oyssen.mrm.negocio.vo.TrabajoDeCampoVO;
import es.oyssen.mrm.util.StringUtil;


public class TrabajoDeCampoMapper implements RowMapper {

	
	public static final String FIELD_ID_TRABAJO_DE_CAMPO = "id_trabajo_de_campo";
	public static final String FIELD_ID_PORTAFOLIO = "id_portafolio";
	public static final String FIELD_NOMBRE = "nombre";
	public static final String FIELD_NOMBRE_TRABAJO = "nombre_trabajo";
    public static final String FIELD_TRABAJO_DE_CAMPO = "trabajo_de_campo";
    public static final String FIELD_NOMBRE_CORRECCION = "nombre_correccion";
    public static final String FIELD_CORRECCION_TRABAJO = "correccion_trabajo";
    public static final String FIELD_FECHA_LIMITE = "fecha_limite";
    public static final String FIELD_ENUNCIADO = "enunciado";
    public static final String FIELD_NOMBRE_ARCHIVO = "nombre_archivo";
    public static final String FIELD_DESCRIPCION = "descripcion";
    public static final String FIELD_ID_TRABAJO_INFO = "id_trabajo_info";
    
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	TrabajoDeCampoVO o = new TrabajoDeCampoVO();
    	o.setIdTrabajoDeCampo(StringUtil.nullToString(rs.getString(FIELD_ID_TRABAJO_DE_CAMPO)));
    	o.setIdPortafolio(StringUtil.nullToString(rs.getString(FIELD_ID_PORTAFOLIO)));
    	o.setNombre(StringUtil.nullToString(rs.getString(FIELD_NOMBRE)));
    	o.setTrabajoDeCampo(StringUtil.nullToString(rs.getString(FIELD_TRABAJO_DE_CAMPO)));
    	o.setCorreccionTrabajo(StringUtil.nullToString(rs.getString(FIELD_CORRECCION_TRABAJO)));
    	o.setFechaLimite(StringUtil.nullToString(rs.getString(FIELD_FECHA_LIMITE)));
    	o.setDescripcion(StringUtil.nullToString(rs.getString(FIELD_DESCRIPCION)));
    	o.setIdTrabajoInfo(StringUtil.nullToString(rs.getString(FIELD_ID_TRABAJO_INFO)));
    	o.setNombreTrabajo(StringUtil.nullToString(rs.getString(FIELD_NOMBRE_TRABAJO)));
    	o.setNombreCorreccion(StringUtil.nullToString(rs.getString(FIELD_NOMBRE_CORRECCION)));
    	o.setNombreArchivo(StringUtil.nullToString(rs.getString(FIELD_NOMBRE_ARCHIVO)));
    	
    	
    	try {
        	InputStream datos = rs.getBinaryStream(FIELD_ENUNCIADO);

        	byte[] archivo;
        	if(datos != null) {
        		archivo = IOUtils.toByteArray(datos);
        	}
        	else{
        		archivo = null;
        	}
        	o.setEnunciado(archivo);
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

