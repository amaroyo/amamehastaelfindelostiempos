package es.oyssen.mrm.negocio.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import es.oyssen.mrm.negocio.vo.PuntuacionCriterioVO;
import es.oyssen.mrm.util.StringUtil;


public class PuntuacionCriterioMapper implements RowMapper {

	public static final String FIELD_ID_CRITERIO = "id_criterio";
	public static final String FIELD_ID_PORTAFOLIO = "id_portafolio";
	public static final String FIELD_NOTA = "nota";
    
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	PuntuacionCriterioVO o = new PuntuacionCriterioVO();
    	o.setIdCriterio(StringUtil.nullToString(rs.getString(FIELD_ID_CRITERIO)));
    	o.setIdPortafolio(StringUtil.nullToString(rs.getString(FIELD_ID_PORTAFOLIO)));
    	o.setNota(StringUtil.nullToString(rs.getString(FIELD_NOTA)));
		return o;
	}
}

