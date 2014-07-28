package es.oyssen.mrm.negocio.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import es.oyssen.mrm.negocio.vo.SeminarioAsignaturaAnyoVO;
import es.oyssen.mrm.negocio.vo.SeminarioAsignaturaVO;
import es.oyssen.mrm.util.StringUtil;


public class SeminarioAsignaturaAnyoMapper implements RowMapper {

	public static final String FIELD_ID_SEMINARIO = "id_seminario";
	public static final String FIELD_NOMBRE = "nombre";
	public static final String FIELD_CODIGO = "codigo";
	public static final String FIELD_ANYO_ACADEMICO = "anyo_academico";
	

	
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	SeminarioAsignaturaAnyoVO o = new SeminarioAsignaturaAnyoVO();
    	o.setIdSeminario(StringUtil.nullToString(rs.getString(FIELD_ID_SEMINARIO)));
    	o.setNombre(StringUtil.nullToString(rs.getString(FIELD_NOMBRE)));
    	o.setCodigo(StringUtil.nullToString(rs.getString(FIELD_CODIGO)));
    	o.setAnyoAcademico(StringUtil.nullToString(rs.getString(FIELD_ANYO_ACADEMICO)));
		return o;
	}
}

