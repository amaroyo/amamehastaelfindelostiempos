package es.oyssen.mrm.negocio.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import es.oyssen.mrm.negocio.vo.DatosUsuarioEstanciaUnidadClinicaVO;
import es.oyssen.mrm.negocio.vo.UsuarioEstanciaUnidadClinicaVO;
import es.oyssen.mrm.util.StringUtil;


public class DatosUsuarioEstanciaUnidadClinicaMapper implements RowMapper {

	public static final String FIELD_ID_PORTAFOLIO = "id_portafolio";
    public static final String FIELD_ID_ESTANCIA_UNIDAD = "id_estancia_unidad";
    public static final String FIELD_CENTRO_ASOCIADO = "centro_asociado";
    public static final String FIELD_UNIDAD_CLINICA = "unidad_clinica";
    public static final String FIELD_NOMBRE = "nombre";
    public static final String FIELD_APELLIDO1 = "apellido1";
    public static final String FIELD_APELLIDO2 = "apellido2";
    public static final String FIELD_TURNO = "turno";
    public static final String FIELD_FECHA_INICIO = "fecha_inicio";
    public static final String FIELD_FECHA_FIN = "fecha_fin";
    
    
    

    
    
    
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	
    	DatosUsuarioEstanciaUnidadClinicaVO o = new DatosUsuarioEstanciaUnidadClinicaVO();
    	
    	o.setIdPortafolio(StringUtil.nullToString(rs.getString(FIELD_ID_PORTAFOLIO)));  	
    	o.setIdEstanciaUnidad(StringUtil.nullToString(rs.getString(FIELD_ID_ESTANCIA_UNIDAD)));
    	o.setCentroAsociado(StringUtil.nullToString(rs.getString(FIELD_CENTRO_ASOCIADO)));
    	o.setUnidadClinica(StringUtil.nullToString(rs.getString(FIELD_UNIDAD_CLINICA)));
    	o.setNombreProfesor(StringUtil.nullToString(rs.getString(FIELD_NOMBRE)));
    	o.setApellido1Profesor(StringUtil.nullToString(rs.getString(FIELD_APELLIDO1)));
    	o.setApellido2Profesor(StringUtil.nullToString(rs.getString(FIELD_APELLIDO2)));
    	o.setTurno(StringUtil.nullToString(rs.getString(FIELD_TURNO)));
    	o.setFechaInicio(StringUtil.nullToString(rs.getString(FIELD_FECHA_INICIO)));
    	o.setFechaFin(StringUtil.nullToString(rs.getString(FIELD_FECHA_FIN)));
    	
		return o;
	}
}

