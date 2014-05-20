package es.oyssen.mrm.negocio.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import es.oyssen.mrm.negocio.vo.PermisoGrupoVO;
import es.oyssen.mrm.util.StringUtil;


public class PermisoGrupoMapper implements RowMapper {

	public static final String FIELD_ID_GRUPO = "id_grupo";
	public static final String FIELD_ID_PERMISO = "id_permiso";
    
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	PermisoGrupoVO o = new PermisoGrupoVO();
    	o.setIdGrupo(StringUtil.nullToString(rs.getString(FIELD_ID_GRUPO)));
    	o.setIdPermiso(StringUtil.nullToString(rs.getString(FIELD_ID_PERMISO)));

		return o;
	}
}

