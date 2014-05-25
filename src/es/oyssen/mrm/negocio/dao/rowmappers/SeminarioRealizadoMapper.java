package es.oyssen.mrm.negocio.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import es.oyssen.mrm.negocio.vo.SeminarioRealizadoVO;
import es.oyssen.mrm.util.StringUtil;


public class SeminarioRealizadoMapper implements RowMapper {

	public static final String FIELD_ID_SEMINARIO = "id_seminario";
	public static final String FIELD_ID_PORTAFOLIO = "id_portafolio";
    
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	SeminarioRealizadoVO o = new SeminarioRealizadoVO();
    	o.setIdSeminario(StringUtil.nullToString(rs.getString(FIELD_ID_SEMINARIO)));
    	o.setIdPortafolio(StringUtil.nullToString(rs.getString(FIELD_ID_PORTAFOLIO)));
		return o;
	}
}

