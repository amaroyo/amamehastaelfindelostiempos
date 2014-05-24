package es.oyssen.mrm.negocio.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import es.oyssen.mrm.negocio.vo.ProfesorAsociadoVO;
import es.oyssen.mrm.util.StringUtil;


public class ProfesorAsociadoMapper implements RowMapper {

	public static final String FIELD_ID_PROFESOR = "id_profesor";
	public static final String FIELD_ID_ASIGNATURA = "id_asignatura";
    public static final String FIELD_ANYO_ACADEMICO = "anyo_academico";
    public static final String FIELD_CENTRO_ASOCIADO = "centro_asociado";
    public static final String FIELD_TURNO = "turno";
    
    
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	ProfesorAsociadoVO o = new ProfesorAsociadoVO();
    	o.setIdProfesor(StringUtil.nullToString(rs.getString(FIELD_ID_PROFESOR)));
    	o.setIdAsignatura(StringUtil.nullToString(rs.getString(FIELD_ID_ASIGNATURA)));
    	o.setAnyoAcademico(StringUtil.nullToString(rs.getString(FIELD_ANYO_ACADEMICO)));
    	o.setCentroAsociado(StringUtil.nullToString(rs.getString(FIELD_CENTRO_ASOCIADO)));
    	o.setTurno(StringUtil.nullToString(rs.getString(FIELD_TURNO)));
		return o;
	}
}

