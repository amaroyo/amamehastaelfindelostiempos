package es.oyssen.mrm.negocio.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import es.oyssen.mrm.negocio.vo.GrupoVO;
import es.oyssen.mrm.util.StringUtil;


public class GrupoMapper implements RowMapper {

    public static final String FIELD_ID = "id_grupo";    
    public static final String FIELD_NOMBRE = "nombre";
    
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	GrupoVO o = new GrupoVO();
    	o.setIdGrupo(StringUtil.nullToString(rs.getString(FIELD_ID)));
    	o.setNombre(StringUtil.nullToString(rs.getString(FIELD_NOMBRE)));

		return o;
	}
}

