package es.oyssen.mrm.negocio.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import es.oyssen.mrm.negocio.vo.CasoClinicoVO;
import es.oyssen.mrm.util.StringUtil;


public class CasoClinicoMapper implements RowMapper {

	public static final String FIELD_ID_CASO_CLINICO = "id_caso_clinico";
	public static final String FIELD_ID_PORTAFOLIO = "id_portafolio";
	public static final String FIELD_NOMBRE = "nombre";
	public static final String FIELD_CASO_CLINICO = "caso_clinico";
    
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	CasoClinicoVO o = new CasoClinicoVO();
    	o.setIdCasoClinico(StringUtil.nullToString(rs.getString(FIELD_ID_CASO_CLINICO)));
    	o.setIdPortafolio(StringUtil.nullToString(rs.getString(FIELD_ID_PORTAFOLIO)));
    	o.setNombre(StringUtil.nullToString(rs.getString(FIELD_NOMBRE)));
    	o.setCasoClinico(StringUtil.nullToString(rs.getString(FIELD_CASO_CLINICO)));
		return o;
	}
}

