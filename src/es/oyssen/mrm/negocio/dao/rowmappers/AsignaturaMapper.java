package es.oyssen.mrm.negocio.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import es.oyssen.mrm.negocio.vo.AsignaturaVO;
import es.oyssen.mrm.util.StringUtil;


public class AsignaturaMapper implements RowMapper {

	public static final String FIELD_ID = "id_asignatura";
	public static final String FIELD_CURSO = "curso";
    public static final String FIELD_CODIGO = "codigo";
    public static final String FIELD_NOMBRE = "nombre";
    public static final String FIELD_DESCRIPCION = "descripcion";
    
    
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	AsignaturaVO o = new AsignaturaVO();
    	o.setIdAsignatura(StringUtil.nullToString(rs.getString(FIELD_ID)));
    	o.setCurso(StringUtil.nullToString(rs.getString(FIELD_CURSO)));
    	o.setCodigo(StringUtil.nullToString(rs.getString(FIELD_CODIGO)));
    	o.setNombre(StringUtil.nullToString(rs.getString(FIELD_NOMBRE)));
    	o.setDescripcion(StringUtil.nullToString(rs.getString(FIELD_DESCRIPCION)));
		return o;
	}
}

