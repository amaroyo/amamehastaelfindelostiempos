package es.oyssen.mrm.negocio.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import es.oyssen.mrm.negocio.vo.ActionHistoryVO;
import es.oyssen.mrm.negocio.vo.ActionVO;

public class ActionHistoryMapper implements RowMapper {

    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	ActionHistoryVO o = new ActionHistoryVO();
    	ActionVO action = new ActionVO();
    	action.setIdAction(rs.getString("id_action"));
    	action.setName(rs.getString("action_name"));
		o.setAction(action);
    	o.setComments(rs.getString("comments"));
    	o.setDate(rs.getDate("date"));
    	o.setIdActionHistory(rs.getString("id_action_history"));
		return o;
	}
}

