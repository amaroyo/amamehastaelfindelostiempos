package es.oyssen.mrm.negocio.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import es.oyssen.mrm.negocio.vo.UsuarioVO;
import es.oyssen.mrm.util.StringUtil;


public class UsuarioMapper implements RowMapper {

	public static final String FIELD_ID = "id_usuario";
	public static final String FIELD_ID_GRUPO = "id_grupo";
	public static final String FIELD_ID_ASOCIADO = "id_asociado";
    public static final String FIELD_NOMBRE = "nombre";
    public static final String FIELD_TELEFONO = "telefono";
    public static final String FIELD_TELEFONO_MOVIL = "telefonoMovil";
    public static final String FIELD_DIRECCION = "direccion";
    public static final String FIELD_CODIGO_POSTAL = "codigoPostal";
    public static final String FIELD_CIUDAD = "ciudad";
    public static final String FIELD_PAIS = "pais";
    public static final String FIELD_EMAIL = "email";
    public static final String FIELD_COMENTARIOS = "comentarios";
    public static final String FIELD_USER = "user";
    public static final String FIELD_PASS = "pass";
   
    
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	UsuarioVO o = new UsuarioVO();
    	o.setIdUsuario(StringUtil.nullToString(rs.getString(FIELD_ID)));
    	o.setIdGrupo(StringUtil.nullToString(rs.getString(FIELD_ID_GRUPO)));
    	o.setIdAsociado(StringUtil.nullToString(rs.getString(FIELD_ID_ASOCIADO)));
    	o.setNombre(StringUtil.nullToString(rs.getString(FIELD_NOMBRE)));
    	o.setTelefono(StringUtil.nullToString(rs.getString(FIELD_TELEFONO)));
    	o.setTelefonoMovil(StringUtil.nullToString(rs.getString(FIELD_TELEFONO_MOVIL)));
    	o.setDireccion(StringUtil.nullToString(rs.getString(FIELD_DIRECCION)));
    	o.setCodigoPostal(StringUtil.nullToString(rs.getString(FIELD_CODIGO_POSTAL)));
    	o.setCiudad(StringUtil.nullToString(rs.getString(FIELD_CIUDAD)));
    	o.setPais(StringUtil.nullToString(rs.getString(FIELD_PAIS)));
    	o.setEmail(StringUtil.nullToString(rs.getString(FIELD_EMAIL)));
    	o.setComentarios(StringUtil.nullToString(rs.getString(FIELD_COMENTARIOS)));
    	o.setUser(StringUtil.nullToString(rs.getString(FIELD_USER)));
    	o.setPass(StringUtil.nullToString(rs.getString(FIELD_PASS)));
		return o;
	}
}

