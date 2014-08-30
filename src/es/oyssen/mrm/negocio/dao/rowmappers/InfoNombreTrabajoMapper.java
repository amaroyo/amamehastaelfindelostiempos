package es.oyssen.mrm.negocio.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import es.oyssen.mrm.negocio.vo.InfoNombreTrabajoVO;
import es.oyssen.mrm.util.StringUtil;


public class InfoNombreTrabajoMapper implements RowMapper {

	public static final String FIELD_ID_TRABAJO_INFO = "id_trabajo_info";
	public static final String FIELD_NOMBRE = "nombre";
    
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	InfoNombreTrabajoVO o = new InfoNombreTrabajoVO();
    	
    	o.setNombre(StringUtil.nullToString(rs.getString(FIELD_NOMBRE)));
    	o.setIdTrabajoInfo(StringUtil.nullToString(rs.getString(FIELD_ID_TRABAJO_INFO)));
		return o;
	}
}

