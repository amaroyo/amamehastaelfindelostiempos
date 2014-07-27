package es.oyssen.mrm.negocio.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import es.oyssen.mrm.negocio.vo.ServicioVO;
import es.oyssen.mrm.negocio.vo.TrabajoDeCampoNombreVO;
import es.oyssen.mrm.util.StringUtil;

public class TrabajoDeCampoNombreMapper implements RowMapper {
  
    public static final String FIELD_NOMBRE = "nombre";
	
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		TrabajoDeCampoNombreVO o = new TrabajoDeCampoNombreVO();
		o.setNombre(StringUtil.nullToString(rs.getString(FIELD_NOMBRE)));
		return o;
	}

}
