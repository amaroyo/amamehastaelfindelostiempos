package es.oyssen.mrm.negocio.dao.rowmappers;

import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.io.IOUtils;
import org.springframework.jdbc.core.RowMapper;

import es.oyssen.mrm.negocio.vo.ServicioVO;
import es.oyssen.mrm.negocio.vo.TrabajoDeCampoInfoVO;
import es.oyssen.mrm.util.StringUtil;

public class TrabajoDeCampoInfoMapper implements RowMapper {
  
	public static final String FIELD_ID_TRABAJO_INFO = "id_trabajo_info";
    public static final String FIELD_NOMBRE = "nombre";
    public static final String FIELD_ENUNCIADO = "enunciado";
    public static final String FIELD_DESCRIPCION = "descripcion";
 
	
    
	
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		TrabajoDeCampoInfoVO o = new TrabajoDeCampoInfoVO();
		o.setIdTrabajoInfo(StringUtil.nullToString(rs.getString(FIELD_ID_TRABAJO_INFO)));
		o.setNombre(StringUtil.nullToString(rs.getString(FIELD_NOMBRE)));
		
		o.setDescripcion(StringUtil.nullToString(rs.getString(FIELD_DESCRIPCION)));
		
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
