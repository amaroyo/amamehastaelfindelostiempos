package es.oyssen.mrm.negocio.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import es.oyssen.mrm.negocio.vo.GrupoCriteriosRubricasVO;
import es.oyssen.mrm.util.StringUtil;


public class GrupoCriteriosRubricasMapper implements RowMapper {

	
	public static final String FIELD_ID_GRUPO_CRITERIO = "id_grupo_criterio";
	public static final String FIELD_ID_ASIGNATURA = "id_asignatura";
	public static final String FIELD_NOMBRE = "nombre";
    
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	GrupoCriteriosRubricasVO o = new GrupoCriteriosRubricasVO();
    	
    	o.setIdGrupoCriterio(StringUtil.nullToString(rs.getString(FIELD_ID_GRUPO_CRITERIO)));
    	o.setIdAsignatura(StringUtil.nullToString(rs.getString(FIELD_ID_ASIGNATURA)));
    	o.setNombre(StringUtil.nullToString(rs.getString(FIELD_NOMBRE)));
		return o;
	}
}

