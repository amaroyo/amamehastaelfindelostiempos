package es.oyssen.mrm.negocio.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import es.oyssen.mrm.negocio.vo.SeminarioAsignaturaCodigoVO;
import es.oyssen.mrm.negocio.vo.SeminarioAsignaturaVO;
import es.oyssen.mrm.util.StringUtil;


public class SeminarioAsignaturaCodigoMapper implements RowMapper {

	public static final String FIELD_ID_SEMINARIO = "id_seminario";
	public static final String FIELD_NOMBRE = "nombre";
	public static final String FIELD_CODIGO = "codigo";
	public static final String FIELD_COGIDO_ASIGNATURA = "a.codigo";
	public static final String FIELD_CURSO = "curso";
    
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	SeminarioAsignaturaCodigoVO o = new SeminarioAsignaturaCodigoVO();
    	o.setIdSeminario(StringUtil.nullToString(rs.getString(FIELD_ID_SEMINARIO)));
    	o.setNombre(StringUtil.nullToString(rs.getString(FIELD_NOMBRE)));
    	o.setCodigo(StringUtil.nullToString(rs.getString(FIELD_CODIGO)));
    	o.setCodigoAsignatura(StringUtil.nullToString(rs.getString(FIELD_COGIDO_ASIGNATURA)));
    	o.setCurso(StringUtil.nullToString(rs.getString(FIELD_CURSO)));
		return o;
	}
}

