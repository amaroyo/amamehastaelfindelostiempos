package es.oyssen.mrm.negocio.dao.rowmappers;

import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.io.IOUtils;
import org.springframework.jdbc.core.RowMapper;

import es.oyssen.mrm.negocio.vo.DiarioReflexivoVO;
import es.oyssen.mrm.util.StringUtil;


public class DiarioReflexivoMapper implements RowMapper {

	public static final String FIELD_ID_DIARIO_REFLEXIVO = "id_diario_reflexivo";
	public static final String FIELD_ID_PORTAFOLIO = "id_portafolio";
	public static final String FIELD_NOMBRE = "nombre";
	public static final String FIELD_FECHA_SUBIDA = "fecha_subida";
	public static final String FIELD_DIARIO_REFLEXIVO = "diario_reflexivo";
    
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	DiarioReflexivoVO o = new DiarioReflexivoVO();
    	o.setIdDiarioReflexivo(StringUtil.nullToString(rs.getString(FIELD_ID_DIARIO_REFLEXIVO)));
    	o.setIdPortafolio(StringUtil.nullToString(rs.getString(FIELD_ID_PORTAFOLIO)));
    	o.setNombre(StringUtil.nullToString(rs.getString(FIELD_NOMBRE)));
    	o.setFechaSubida(StringUtil.nullToString(rs.getString(FIELD_FECHA_SUBIDA)));
    	
		
    	try {
        	InputStream datos = rs.getBinaryStream(FIELD_DIARIO_REFLEXIVO);

        	byte[] archivo;
        	if(datos != null) {
        		archivo = IOUtils.toByteArray(datos);
        	}
        	else{
        		archivo = null;
        	}
        	o.setDiarioReflexivo(archivo);
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

