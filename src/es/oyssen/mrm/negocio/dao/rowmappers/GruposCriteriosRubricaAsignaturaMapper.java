package es.oyssen.mrm.negocio.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import es.oyssen.mrm.negocio.vo.CriterioRubricaVO;
import es.oyssen.mrm.negocio.vo.GrupoCriteriosRubricasVO;
import es.oyssen.mrm.negocio.vo.GruposCriteriosRubricaAsignaturaVO;
import es.oyssen.mrm.util.StringUtil;


public class GruposCriteriosRubricaAsignaturaMapper implements RowMapper {

	
	public static final String FIELD_ID_CRITERIO = "id_criterio";
	public static final String FIELD_ID_ASIGNATURA = "id_asignatura";
	public static final String FIELD_ID_GRUPO_CRITERIO = "id_grupo_criterio";
	public static final String FIELD_NOMBRE_CRITERIO = "nombre_criterio";
	public static final String FIELD_NOMBRE_GRUPO_CRITERIO = "nombre_grupo_criterio";
    
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	GruposCriteriosRubricaAsignaturaVO o = new GruposCriteriosRubricaAsignaturaVO();
    	o.setIdCriterio(StringUtil.nullToString(rs.getString(FIELD_ID_CRITERIO)));
    	o.setIdAsignatura(StringUtil.nullToString(rs.getString(FIELD_ID_ASIGNATURA)));
    	o.setIdGrupoCriterio(StringUtil.nullToString(rs.getString(FIELD_ID_GRUPO_CRITERIO)));
    	o.setNombreCriterio(StringUtil.nullToString(rs.getString(FIELD_NOMBRE_CRITERIO)));
    	o.setNombreGrupoCriterio(StringUtil.nullToString(rs.getString(FIELD_NOMBRE_GRUPO_CRITERIO)));
		return o;
	}
}

