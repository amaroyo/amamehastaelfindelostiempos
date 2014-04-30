package es.oyssen.mrm.negocio.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import es.oyssen.mrm.negocio.vo.PuntoVentaVO;


public class PuntoVentaMapper implements RowMapper {

    public static final String FIELD_ID = "id_punto_venta";    
    public static final String FIELD_NOMBRE = "nombre";
    
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	PuntoVentaVO o = new PuntoVentaVO();
    	o.setIdPuntoVenta(rs.getString(FIELD_ID));
    	o.setNombre(rs.getString(FIELD_NOMBRE));
		return o;
	}
}

