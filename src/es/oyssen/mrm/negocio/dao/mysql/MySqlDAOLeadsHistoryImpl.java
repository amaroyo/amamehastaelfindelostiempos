package es.oyssen.mrm.negocio.dao.mysql;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import es.oyssen.mrm.negocio.dao.DAOBase;
import es.oyssen.mrm.negocio.dao.DAOFicheros;
import es.oyssen.mrm.negocio.dao.DAOLeadsHistory;
import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOUpdateException;
import es.oyssen.mrm.negocio.dao.rowmappers.FicheroMapper;
import es.oyssen.mrm.negocio.dao.rowmappers.LeadHistoryMapper;
import es.oyssen.mrm.negocio.vo.CriterioFicheroVO;
import es.oyssen.mrm.negocio.vo.FicheroVO;
import es.oyssen.mrm.negocio.vo.LeadHistoryVO;
import es.oyssen.mrm.negocio.vo.LeadVO;
import es.oyssen.mrm.negocio.vo.ServicioVO;
import es.oyssen.mrm.util.StringUtil;


public class MySqlDAOLeadsHistoryImpl extends DAOBase implements DAOLeadsHistory{
	
	private static String SQL_INSERT = "insert into leads_history (id_lead, tipo, fecha, campo, valorAnterior, valorPosterior) values (?,?,?,?,?,?)";
	private static String SQL_DELETE = "delete from leads_history where id_lead = ?";
	private static String SQL_FIND= "select * from leads_history where id_lead = ?";
	
	
	public void insertByLead(final LeadVO lead) throws DAOException,
			DAOInsertException {
		try{
			KeyHolder kh = new GeneratedKeyHolder();
			getJdbcTemplate().update(new PreparedStatementCreator() {
				
				public PreparedStatement createPreparedStatement(Connection conn)
						throws SQLException {
					PreparedStatement ps = conn.prepareStatement(SQL_INSERT, new String[]{"id_history"});
					ps.setString(1, lead.getIdLead());
					ps.setString(2, "Created");
					ps.setDate(3, new java.sql.Date(lead.getFechaCreacion().getTime()));
					ps.setString(4, null);
					ps.setString(5, null);
					ps.setString(6, null);
					return ps;
				}
			}
			,kh);
			
		} catch (Exception e) {
			throw new DAOInsertException(e);
		}			
	}
	
	public void insertByLead(final LeadVO lead, final LeadVO leadAnterior) throws DAOException,
			DAOInsertException {
		try{
			KeyHolder kh = new GeneratedKeyHolder();

			if (!lead.getCanal().getIdCanal().equals(leadAnterior.getCanal().getIdCanal())){
				getJdbcTemplate().update(new PreparedStatementCreator() {
					
					public PreparedStatement createPreparedStatement(Connection conn)
							throws SQLException {
						PreparedStatement ps = conn.prepareStatement(SQL_INSERT, new String[]{"id_history"});
						ps.setString(1, lead.getIdLead());
						ps.setString(2, "Modified");
						java.util.Date fechaActual = new java.util.Date();
						ps.setDate(3, new java.sql.Date(fechaActual.getTime()));
						ps.setString(4, "Channel");
						ps.setString(5, leadAnterior.getCanal().getNombre());
						ps.setString(6, lead.getCanal().getNombre());
						return ps;
					}
				}
				,kh);
			}
			
			if (!lead.getServicio().getIdServicio().equals(leadAnterior.getServicio().getIdServicio())){
				getJdbcTemplate().update(new PreparedStatementCreator() {
					
					public PreparedStatement createPreparedStatement(Connection conn)
							throws SQLException {
						PreparedStatement ps = conn.prepareStatement(SQL_INSERT, new String[]{"id_history"});
						ps.setString(1, lead.getIdLead());
						ps.setString(2, "Modified");
						java.util.Date fechaActual = new java.util.Date();
						ps.setDate(3, new java.sql.Date(fechaActual.getTime()));
						ps.setString(4, "Service");
						ps.setString(5, leadAnterior.getServicio().getNombre());
						ps.setString(6, lead.getServicio().getNombre());
						return ps;
					}
				}
				,kh);
			}
			
			if (!lead.getDistribuidor().getIdDistribuidor().equals(leadAnterior.getDistribuidor().getIdDistribuidor())){
				getJdbcTemplate().update(new PreparedStatementCreator() {
					
					public PreparedStatement createPreparedStatement(Connection conn)
							throws SQLException {
						PreparedStatement ps = conn.prepareStatement(SQL_INSERT, new String[]{"id_history"});
						ps.setString(1, lead.getIdLead());
						ps.setString(2, "Modified");
						java.util.Date fechaActual = new java.util.Date();
						ps.setDate(3, new java.sql.Date(fechaActual.getTime()));
						ps.setString(4, "Distributor");
						ps.setString(5, leadAnterior.getDistribuidor().getNombre());
						ps.setString(6, lead.getDistribuidor().getNombre());
						return ps;
					}
				}
				,kh);
			}
			
			if (!lead.getComercial().getIdComercial().equals(leadAnterior.getComercial().getIdComercial())){
				getJdbcTemplate().update(new PreparedStatementCreator() {
					
					public PreparedStatement createPreparedStatement(Connection conn)
							throws SQLException {
						PreparedStatement ps = conn.prepareStatement(SQL_INSERT, new String[]{"id_history"});
						ps.setString(1, lead.getIdLead());
						ps.setString(2, "Modified");
						java.util.Date fechaActual = new java.util.Date();
						ps.setDate(3, new java.sql.Date(fechaActual.getTime()));
						ps.setString(4, "Sales rep.");
						ps.setString(5, leadAnterior.getComercial().getNombre());
						ps.setString(6, lead.getComercial().getNombre());
						return ps;
					}
				}
				,kh);
			}		
			
			if (!lead.getResponsable().getIdResponsable().equals(leadAnterior.getResponsable().getIdResponsable())){
				getJdbcTemplate().update(new PreparedStatementCreator() {
					
					public PreparedStatement createPreparedStatement(Connection conn)
							throws SQLException {
						PreparedStatement ps = conn.prepareStatement(SQL_INSERT, new String[]{"id_history"});
						ps.setString(1, lead.getIdLead());
						ps.setString(2, "Modified");
						java.util.Date fechaActual = new java.util.Date();
						ps.setDate(3, new java.sql.Date(fechaActual.getTime()));
						ps.setString(4, "Responsible");
						ps.setString(5, leadAnterior.getResponsable().getNombre());
						ps.setString(6, lead.getResponsable().getNombre());
						return ps;
					}
				}
				,kh);
			}
			
			if (!lead.getEmpresa().getIdEmpresa().equals(leadAnterior.getEmpresa().getIdEmpresa())){
				getJdbcTemplate().update(new PreparedStatementCreator() {
					
					public PreparedStatement createPreparedStatement(Connection conn)
							throws SQLException {
						PreparedStatement ps = conn.prepareStatement(SQL_INSERT, new String[]{"id_history"});
						ps.setString(1, lead.getIdLead());
						ps.setString(2, "Modified");
						java.util.Date fechaActual = new java.util.Date();
						ps.setDate(3, new java.sql.Date(fechaActual.getTime()));
						ps.setString(4, "Company");
						ps.setString(5, leadAnterior.getEmpresa().getNombre());
						ps.setString(6, lead.getEmpresa().getNombre());
						return ps;
					}
				}
				,kh);
			}			
						
			if (!lead.getEstado().getIdEstado().equals(leadAnterior.getEstado().getIdEstado())){
				getJdbcTemplate().update(new PreparedStatementCreator() {
					
					public PreparedStatement createPreparedStatement(Connection conn)
							throws SQLException {
						PreparedStatement ps = conn.prepareStatement(SQL_INSERT, new String[]{"id_history"});
						ps.setString(1, lead.getIdLead());
						ps.setString(2, "Modified");
						java.util.Date fechaActual = new java.util.Date();
						ps.setDate(3, new java.sql.Date(fechaActual.getTime()));
						ps.setString(4, "Status");
						ps.setString(5, leadAnterior.getEstado().getNombre());
						ps.setString(6, lead.getEstado().getNombre());
						return ps;
					}
				}
				,kh);
			}
			
			if (!lead.getMarketingActivity().getIdMarketingActivity().equals(leadAnterior.getMarketingActivity().getIdMarketingActivity())){
				getJdbcTemplate().update(new PreparedStatementCreator() {
					
					public PreparedStatement createPreparedStatement(Connection conn)
							throws SQLException {
						PreparedStatement ps = conn.prepareStatement(SQL_INSERT, new String[]{"id_history"});
						ps.setString(1, lead.getIdLead());
						ps.setString(2, "Modified");
						java.util.Date fechaActual = new java.util.Date();
						ps.setDate(3, new java.sql.Date(fechaActual.getTime()));
						ps.setString(4, "Marketing activity");
						ps.setString(5, leadAnterior.getMarketingActivity().getNombre());
						ps.setString(6, lead.getMarketingActivity().getNombre());
						return ps;
					}
				}
				,kh);
			}
			
			if (!lead.getComentarios().equals(leadAnterior.getComentarios())){
				getJdbcTemplate().update(new PreparedStatementCreator() {
					
					public PreparedStatement createPreparedStatement(Connection conn)
							throws SQLException {
						PreparedStatement ps = conn.prepareStatement(SQL_INSERT, new String[]{"id_history"});
						ps.setString(1, lead.getIdLead());
						ps.setString(2, "Modified");
						java.util.Date fechaActual = new java.util.Date();
						ps.setDate(3, new java.sql.Date(fechaActual.getTime()));
						ps.setString(4, "Comments");
						ps.setString(5, leadAnterior.getComentarios());
						ps.setString(6, lead.getComentarios());
						return ps;
					}
				}
				,kh);
			}
			
		} catch (Exception e) {
			throw new DAOInsertException(e);
		}			
	}
	
	public void deleteByLead(LeadVO lead) throws DAOException,
			DAODeleteException {
		try {
			getJdbcTemplate().update(SQL_DELETE, new Object[]{lead.getIdLead()});
		} catch (Exception e) {
			throw new DAODeleteException(e);
		}
		
	}
	
	public List<LeadHistoryVO> findByLead(LeadVO lead) throws DAOException {
		try {
			return getJdbcTemplate().query(SQL_FIND, new Object[]{lead.getIdLead()}, new LeadHistoryMapper());
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

}


