package es.oyssen.mrm.negocio.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import es.oyssen.mrm.negocio.vo.TrabajoDeCampoVO;
import es.oyssen.mrm.util.StringUtil;


public class TrabajoDeCampoMapper implements RowMapper {

	
	public static final String FIELD_ID_TRABAJO_DE_CAMPO = "id_trabajo_de_campo";
	public static final String FIELD_ID_PORTAFOLIO = "id_portafolio";
	public static final String FIELD_NOMBRE = "nombre";
    public static final String FIELD_TRABAJO_DE_CAMPO = "trabajo_de_campo";
    public static final String FIELD_CORRECCION_TRABAJO = "correccion_trabajo";
    public static final String FIELD_FECHA_LIMITE = "fecha_limite";
    
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	TrabajoDeCampoVO o = new TrabajoDeCampoVO();
    	o.setIdTrabajoDeCampo(StringUtil.nullToString(rs.getString(FIELD_ID_TRABAJO_DE_CAMPO)));
    	o.setIdPortafolio(StringUtil.nullToString(rs.getString(FIELD_ID_PORTAFOLIO)));
    	o.setNombre(StringUtil.nullToString(rs.getString(FIELD_NOMBRE)));
    	o.setTrabajoDeCampo(StringUtil.nullToString(rs.getString(FIELD_TRABAJO_DE_CAMPO)));
    	o.setCorreccionTrabajo(StringUtil.nullToString(rs.getString(FIELD_CORRECCION_TRABAJO)));
    	o.setFechaLimite(StringUtil.nullToString(rs.getString(FIELD_FECHA_LIMITE)));
		return o;
	}
}

