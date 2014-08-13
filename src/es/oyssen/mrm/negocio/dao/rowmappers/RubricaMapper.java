package es.oyssen.mrm.negocio.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import es.oyssen.mrm.negocio.vo.RubricaVO;
import es.oyssen.mrm.util.StringUtil;


public class RubricaMapper implements RowMapper {


	public static final String FIELD_ID_ASIGNATURA = "id_asignatura";
	public static final String FIELD_COMPETENCIAS = "competencias";
	public static final String FIELD_NUMERO_CRITERIOS = "numero_criterios";
	public static final String FIELD_ANEXO = "anexo";
    
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	RubricaVO o = new RubricaVO();
    	
    	o.setIdAsignatura(StringUtil.nullToString(rs.getString(FIELD_ID_ASIGNATURA)));
    	o.setCompetencias(StringUtil.nullToString(rs.getString(FIELD_COMPETENCIAS)));
    	o.setNumeroCriterios(StringUtil.nullToString(rs.getString(FIELD_NUMERO_CRITERIOS)));
    	o.setAnexo(StringUtil.nullToString(rs.getString(FIELD_ANEXO)));
		return o;
	}
}

