package es.oyssen.mrm.negocio.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import es.oyssen.mrm.negocio.vo.PortafolioVO;
import es.oyssen.mrm.util.StringUtil;


public class PortafolioMapper implements RowMapper {

	public static final String FIELD_ID = "id_portafolio";
	public static final String FIELD_ID_ALUMNO = "id_alumno";
	public static final String FIELD_ID_PROFESOR = "id_profesor";
    public static final String FIELD_ID_ASIGNATURA = "id_asignatura";
    public static final String FIELD_ANYO_ACADEMICO = "anyo_academico";
    
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	PortafolioVO o = new PortafolioVO();
    	o.setIdPortafolio(StringUtil.nullToString(rs.getString(FIELD_ID)));
    	o.setIdAlumno(StringUtil.nullToString(rs.getString(FIELD_ID_ALUMNO)));
    	o.setIdProfesor(StringUtil.nullToString(rs.getString(FIELD_ID_PROFESOR)));
    	o.setIdAsignatura(StringUtil.nullToString(rs.getString(FIELD_ID_ASIGNATURA)));
    	o.setAnyoAcademico(StringUtil.nullToString(rs.getString(FIELD_ANYO_ACADEMICO)));

		return o;
	}
}

