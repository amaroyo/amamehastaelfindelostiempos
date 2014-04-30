package es.oyssen.mrm.negocio.dao.rowmappers;

import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.io.IOUtils;
import org.springframework.jdbc.core.RowMapper;

import es.oyssen.mrm.negocio.vo.FicheroVO;
import es.oyssen.mrm.util.StringUtil;


public class FicheroLeadMapper implements RowMapper {

	public static final String FIELD_ID_FICHERO = "id_fichero";
	public static final String FIELD_ID_LEAD = "id_lead";
    public static final String FIELD_NOMBRE = "nombre";
    public static final String FIELD_TIPO_CONTENIDO = "tipoContenido";
    public static final String FIELD_DATOS = "datos";
    public static final String FIELD_FECHA_ULTIMA_DESCARGA = "fechaUltimaDescarga";
    public static final String FIELD_DESCRIPCION = "descripcion";

    
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	FicheroVO o = new FicheroVO();
    	o.setIdFichero(StringUtil.nullToString(rs.getString(FIELD_ID_FICHERO)));
    	o.setNombre(StringUtil.nullToString(rs.getString(FIELD_NOMBRE)));
    	o.setFechaUltimaDescarga(rs.getDate(FIELD_FECHA_ULTIMA_DESCARGA));
    	o.setDescripcion(StringUtil.nullToString(rs.getString(FIELD_DESCRIPCION)));
    	
    	try {
        	o.setIdServicio(StringUtil.nullToString(rs.getString(FIELD_ID_LEAD)));
        	o.setTipoContenido(StringUtil.nullToString(rs.getString(FIELD_TIPO_CONTENIDO)));
        	InputStream datos = rs.getBinaryStream(FIELD_DATOS);
			o.setDatos(IOUtils.toByteArray(datos));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} 
    	
		return o;
	}
}

