package es.oyssen.mrm.negocio.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import es.oyssen.mrm.negocio.vo.UsuarioEstanciaUnidadClinicaVO;
import es.oyssen.mrm.util.StringUtil;


public class UsuarioEstanciaUnidadClinicaMapper implements RowMapper {

	public static final String FIELD_ID_USUARIO = "id_usuario";
    public static final String FIELD_NOMBRE = "nombre";
    public static final String FIELD_APELLIDO1 = "apellido1";
    public static final String FIELD_APELLIDO2 = "apellido2";
    public static final String FIELD_DNI = "dni";
    public static final String FIELD_CENTRO_ASOCIADO = "centro_asociado";
    public static final String FIELD_UNIDAD_CLINICA = "unidad_clinica";
    public static final String FIELD_TURNO = "turno";
    public static final String FIELD_ID_PROFESOR = "id_profesor";
    
    
    
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	
    	UsuarioEstanciaUnidadClinicaVO o = new UsuarioEstanciaUnidadClinicaVO();
    	
    	o.setIdUsuario(StringUtil.nullToString(rs.getString(FIELD_ID_USUARIO)));
    	o.setNombre(StringUtil.nullToString(rs.getString(FIELD_NOMBRE)));
    	o.setApellido1(StringUtil.nullToString(rs.getString(FIELD_APELLIDO1)));
    	o.setApellido2(StringUtil.nullToString(rs.getString(FIELD_APELLIDO2)));
    	o.setDni(StringUtil.nullToString(rs.getString(FIELD_DNI)));
    	o.setCentroAsociado(StringUtil.nullToString(rs.getString(FIELD_CENTRO_ASOCIADO)));
    	o.setUnidadClinica(StringUtil.nullToString(rs.getString(FIELD_UNIDAD_CLINICA)));
    	o.setTurno(StringUtil.nullToString(rs.getString(FIELD_TURNO)));
    	o.setIdProfesor(StringUtil.nullToString(rs.getString(FIELD_ID_PROFESOR)));
		return o;
	}
}

