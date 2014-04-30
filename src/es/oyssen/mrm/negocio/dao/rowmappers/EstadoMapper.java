package es.oyssen.mrm.negocio.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import es.oyssen.mrm.negocio.vo.EstadoVO;

public class EstadoMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		EstadoVO o = new EstadoVO();
		o.setIdEstado(rs.getString("id_estado"));
		o.setNombre(rs.getString("nombre"));
		return o;
	}

}
