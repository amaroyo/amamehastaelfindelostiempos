package es.oyssen.mrm.negocio.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import es.oyssen.mrm.negocio.vo.AnexoVO;
import es.oyssen.mrm.negocio.vo.ErrorLogVO;
import es.oyssen.mrm.util.StringUtil;


public class ErrorLogMapper implements RowMapper {

	public static final String FIELD_ID_ERROR = "id_error";
	public static final String FIELD_TIPO = "tipo";
	public static final String FIELD_DESCRIPCION = "descripcion";
	public static final String FIELD_FECHA = "fecha";
	
	
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	ErrorLogVO o = new ErrorLogVO();
    	o.setIdError(StringUtil.nullToString(rs.getString(FIELD_ID_ERROR)));
    	o.setTipo(StringUtil.nullToString(rs.getString(FIELD_TIPO)));
    	o.setDescripcion(StringUtil.nullToString(rs.getString(FIELD_DESCRIPCION)));
    	o.setFecha(StringUtil.nullToString(rs.getString(FIELD_FECHA)));
		return o;
	}
}

