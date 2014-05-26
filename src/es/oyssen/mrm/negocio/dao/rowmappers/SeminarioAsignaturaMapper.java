package es.oyssen.mrm.negocio.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import es.oyssen.mrm.negocio.vo.SeminarioAsignaturaVO;
import es.oyssen.mrm.util.StringUtil;


public class SeminarioAsignaturaMapper implements RowMapper {

	public static final String FIELD_ID_SEMINARIO = "id_seminario";
	public static final String FIELD_ID_ASIGNATURA = "id_asignatura";
	public static final String FIELD_NOMBRE = "nombre";
	public static final String FIELD_CODIGO = "codigo";
	public static final String FIELD_DESCRIPCION = "descripcion";
    
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	SeminarioAsignaturaVO o = new SeminarioAsignaturaVO();
    	o.setIdSeminario(StringUtil.nullToString(rs.getString(FIELD_ID_SEMINARIO)));
    	o.setIdAsignatura(StringUtil.nullToString(rs.getString(FIELD_ID_ASIGNATURA)));
    	o.setNombre(StringUtil.nullToString(rs.getString(FIELD_NOMBRE)));
    	o.setCodigo(StringUtil.nullToString(rs.getString(FIELD_CODIGO)));
    	o.setDescripcion(StringUtil.nullToString(rs.getString(FIELD_DESCRIPCION)));
		return o;
	}
}

