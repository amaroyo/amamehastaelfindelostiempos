package es.oyssen.mrm.negocio.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import es.oyssen.mrm.negocio.vo.MarketingActivityVO;

public class MarketingMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		MarketingActivityVO o = new MarketingActivityVO();
		o.setIdMarketingActivity(rs.getString("id_marketing_activity"));
		o.setNombre(rs.getString("nombre"));
		return o;
	}

}
