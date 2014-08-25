package es.oyssen.mrm.negocio.dao.rowmappers;

import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.io.IOUtils;
import org.springframework.jdbc.core.RowMapper;

import es.oyssen.mrm.negocio.vo.AnexoVO;
import es.oyssen.mrm.util.StringUtil;


public class AnexoMapper implements RowMapper {

	public static final String FIELD_ID_ANEXO = "id_anexo";
	public static final String FIELD_ID_PORTAFOLIO = "id_portafolio";
	public static final String FIELD_NOMBRE = "nombre";
	public static final String FIELD_FECHA_SUBIDA = "fecha_subida";
	public static final String FIELD_ANEXO = "anexo";
    
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	AnexoVO o = new AnexoVO();
    	o.setIdAnexo(StringUtil.nullToString(rs.getString(FIELD_ID_ANEXO)));
    	o.setIdPortafolio(StringUtil.nullToString(rs.getString(FIELD_ID_PORTAFOLIO)));
    	o.setNombre(StringUtil.nullToString(rs.getString(FIELD_NOMBRE)));
    	o.setFechaSubida(StringUtil.nullToString(rs.getString(FIELD_FECHA_SUBIDA)));
    	
    	
    	try {
        	InputStream datos = rs.getBinaryStream(FIELD_ANEXO);

        	byte[] archivo;
        	if(datos != null) {
        		archivo = IOUtils.toByteArray(datos);
        	}
        	else{
        		archivo = null;
        	}
        	o.setAnexo(archivo);
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

