package es.oyssen.mrm.negocio.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import es.oyssen.mrm.negocio.vo.ServicioUsuarioVO;
import es.oyssen.mrm.util.StringUtil;


public class ServicioUsuarioMapper implements RowMapper {

	public static final String FIELD_ID = "id_servicioUsuario";
	public static final String FIELD_ID_USUARIO = "id_usuario";
	public static final String FIELD_ID_SERVICIO = "id_servicio";
    public static final String FIELD_NOMBRE = "servicio_nombre";
    
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	ServicioUsuarioVO o = new ServicioUsuarioVO();
    	o.setIdServicioUsuario(StringUtil.nullToString(rs.getString(FIELD_ID)));
    	o.setIdUsuario(StringUtil.nullToString(rs.getString(FIELD_ID_USUARIO)));
    	o.setIdServicio(StringUtil.nullToString(rs.getString(FIELD_ID_SERVICIO)));
    	o.setNombre(StringUtil.nullToString(rs.getString(FIELD_NOMBRE)));
    	
		return o;
	}
}

