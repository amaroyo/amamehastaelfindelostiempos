package es.oyssen.mrm.negocio.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import es.oyssen.mrm.negocio.vo.UsuarioAsignaturaVO;
import es.oyssen.mrm.util.StringUtil;


public class UsuarioAsignaturaMapper implements RowMapper {

	public static final String FIELD_ID = "id_usuario";
	public static final String FIELD_ID_ASIGNATURA = "id_asignatura";
    public static final String FIELD_NOMBRE = "u.nombre";
    public static final String FIELD_APELLIDO1 = "apellido1";
    public static final String FIELD_APELLIDO2 = "apellido2";
    public static final String FIELD_CODIGO = "codigo";
    public static final String FIELD_NOMBRE_ASIGNATURA = "a.nombre";
    public static final String FIELD_DNI = "dni";
    public static final String FIELD_ID_PROFESOR = "id_profesor";
    public static final String FIELD_ID_PORTAFOLIO = "id_portafolio";
    
    
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	UsuarioAsignaturaVO o = new UsuarioAsignaturaVO();
    	
    	o.setIdUsuario(StringUtil.nullToString(rs.getString(FIELD_ID)));
    	o.setNombre(StringUtil.nullToString(rs.getString(FIELD_NOMBRE)));
    	o.setApellido1(StringUtil.nullToString(rs.getString(FIELD_APELLIDO1)));
    	o.setApellido2(StringUtil.nullToString(rs.getString(FIELD_APELLIDO2)));
    	o.setIdAsignatura(StringUtil.nullToString(rs.getString(FIELD_ID_ASIGNATURA)));
    	o.setCodigo(StringUtil.nullToString(rs.getString(FIELD_CODIGO)));
    	o.setNombreAsignatura(StringUtil.nullToString(rs.getString(FIELD_NOMBRE_ASIGNATURA)));
    	o.setDni(StringUtil.nullToString(rs.getString(FIELD_DNI)));
    	o.setIdProfesor(StringUtil.nullToString(rs.getString(FIELD_ID_PROFESOR)));
    	o.setIdPortafolio(StringUtil.nullToString(rs.getString(FIELD_ID_PORTAFOLIO)));
    	
    	
    	
    	
		return o;
	}
}

