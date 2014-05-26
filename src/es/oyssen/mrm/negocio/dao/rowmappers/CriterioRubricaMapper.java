package es.oyssen.mrm.negocio.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import es.oyssen.mrm.negocio.vo.CriterioRubricaVO;
import es.oyssen.mrm.util.StringUtil;


public class CriterioRubricaMapper implements RowMapper {

	public static final String FIELD_ID_CRITERIO = "id_criterio";
	public static final String FIELD_ID_ASIGNATURA = "id_asignatura";
	public static final String FIELD_ID_GRUPO_CRITERIO = "id_grupo_criterio";
	public static final String FIELD_NOMBRE = "nombre";
    
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	CriterioRubricaVO o = new CriterioRubricaVO();
    	o.setIdCriterio(StringUtil.nullToString(rs.getString(FIELD_ID_CRITERIO)));
    	o.setIdAsignatura(StringUtil.nullToString(rs.getString(FIELD_ID_ASIGNATURA)));
    	o.setIdGrupoCriterio(StringUtil.nullToString(rs.getString(FIELD_ID_GRUPO_CRITERIO)));
    	o.setNombre(StringUtil.nullToString(rs.getString(FIELD_NOMBRE)));
		return o;
	}
}

