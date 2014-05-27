package es.oyssen.mrm.negocio.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import es.oyssen.mrm.negocio.vo.UsuarioPermisosVO;
import es.oyssen.mrm.util.StringUtil;


public class UsuarioPermisosMapper implements RowMapper {

	public static final String FIELD_ID_USUARIO = "id_usuario";
	public static final String FIELD_ID_PERMISO = "id_permiso";
    
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	UsuarioPermisosVO o = new UsuarioPermisosVO();
    	o.setIdUsuario(StringUtil.nullToString(rs.getString(FIELD_ID_USUARIO)));
    	o.setIdPermiso(StringUtil.nullToString(rs.getString(FIELD_ID_PERMISO)));

		return o;
	}
}

