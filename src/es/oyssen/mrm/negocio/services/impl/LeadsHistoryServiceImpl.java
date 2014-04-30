package es.oyssen.mrm.negocio.services.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.oyssen.mrm.negocio.dao.DAOLeadsHistory;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.services.LeadsHistoryService;
import es.oyssen.mrm.negocio.vo.LeadHistoryVO;
import es.oyssen.mrm.negocio.vo.LeadVO;

public class LeadsHistoryServiceImpl implements LeadsHistoryService{
	
	private static Log log = LogFactory.getLog(LeadsHistoryServiceImpl.class);

	private DAOLeadsHistory daoLeadsHistory;

	
	public void setDaoLeadsHistory(DAOLeadsHistory daoLeadsHistory) {
		this.daoLeadsHistory = daoLeadsHistory;
	}

	public List<LeadHistoryVO> findByLead(LeadVO lead)
			throws ServiceException {
		try {
			return daoLeadsHistory.findByLead(lead);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}
	
	public void deleteByLead(LeadVO lead) throws ServiceException {
		try {
			daoLeadsHistory.deleteByLead(lead);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error eliminando el lead history", e);
			throw new ServiceException(e);
		}		
	}

	public void insertByLead(LeadVO lead) throws ServiceException {
		try {
			daoLeadsHistory.insertByLead(lead);	
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error creando lead history", e);
			throw new ServiceException(e);
		}
	}
	
	public void insertByLead(LeadVO lead, LeadVO leadAnterior) throws ServiceException {
		try {
			daoLeadsHistory.insertByLead(lead, leadAnterior);	
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error creando lead history", e);
			throw new ServiceException(e);
		}
	}
	
}
