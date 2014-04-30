package es.oyssen.mrm.negocio.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import es.oyssen.mrm.negocio.dao.DAOBase;
import es.oyssen.mrm.negocio.dao.DAOLeadActionsHistory;
import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.rowmappers.ActionHistoryMapper;
import es.oyssen.mrm.negocio.vo.ActionHistoryVO;
import es.oyssen.mrm.negocio.vo.LeadVO;


public class MySqlDAOLeadActionsHistoryImpl extends DAOBase implements DAOLeadActionsHistory {

	private static String SQL_FIND_CRITERIO_LEAD = "select ah.*, a.nombre action_name from leads_actions_history ah inner join actions a on a.id_action = ah.id_action where ah.id_lead = ?";
	private static String SQL_INSERT = "insert into leads_actions_history (id_lead, id_action, comments, date) values (?,?,?,?)";
	private static String SQL_DELETE = "delete from leads_actions_history where id_action_history = ?";

	public List<ActionHistoryVO> findByCriterio(LeadVO lead)
			throws DAOException {
		try {
			return getJdbcTemplate().query(SQL_FIND_CRITERIO_LEAD, new Object[]{lead.getIdLead()}, new ActionHistoryMapper());
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	public void insert(final LeadVO lead, final ActionHistoryVO action)
			throws DAOException, DAOInsertException {
		try{
			KeyHolder kh = new GeneratedKeyHolder();
			getJdbcTemplate().update(new PreparedStatementCreator() {
				
				public PreparedStatement createPreparedStatement(Connection conn)
						throws SQLException {
					PreparedStatement ps = conn.prepareStatement(SQL_INSERT, new String[]{"id_action_history"});

					ps.setString(1, lead.getIdLead());
					ps.setString(2, action.getAction().getIdAction());
					ps.setString(3, action.getComments());
					ps.setDate(4, new java.sql.Date(new Date().getTime()));
					return ps;
				}
			}
			,kh);
			action.setIdActionHistory(kh.getKey().toString());
			
		} catch (Exception e) {
			throw new DAOInsertException(e);
		}	
	}

	public void delete(ActionHistoryVO action) throws DAOException,
			DAODeleteException {
		try {
			getJdbcTemplate().update(SQL_DELETE, new Object[]{
					action.getIdActionHistory()});
		} catch (Exception e) {
			throw new DAOException(e);
		}		
	}
	
	
}


