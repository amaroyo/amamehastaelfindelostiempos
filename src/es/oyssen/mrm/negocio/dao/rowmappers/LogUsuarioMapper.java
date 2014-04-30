package es.oyssen.mrm.negocio.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import es.oyssen.mrm.negocio.vo.LogUsuarioVO;
import es.oyssen.mrm.util.StringUtil;


public class LogUsuarioMapper implements RowMapper {

	public static final String FIELD_ID = "id_logUsuario";
	public static final String FIELD_ID_USUARIO = "id_usuario";
	public static final String FIELD_NOMBRE = "usuario_nombre";
	public static final String FIELD_USER = "usuario_user";
    public static final String FIELD_FECHA = "fecha";
    
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	LogUsuarioVO o = new LogUsuarioVO();
    	o.setIdLogUsuario(StringUtil.nullToString(rs.getString(FIELD_ID)));
    	o.setIdUsuario(StringUtil.nullToString(rs.getString(FIELD_ID_USUARIO)));
    	o.setNombre(StringUtil.nullToString(rs.getString(FIELD_NOMBRE)));
    	o.setUser(StringUtil.nullToString(rs.getString(FIELD_USER)));
    	o.setFecha(rs.getDate(FIELD_FECHA));
    	
		return o;
	}
}

