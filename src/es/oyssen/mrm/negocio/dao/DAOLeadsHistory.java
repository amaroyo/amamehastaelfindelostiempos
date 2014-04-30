package es.oyssen.mrm.negocio.dao;

import java.util.List;

import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.vo.LeadHistoryVO;
import es.oyssen.mrm.negocio.vo.LeadVO;

public interface DAOLeadsHistory {
	
	public List<LeadHistoryVO> findByLead(LeadVO lead) throws DAOException;
	
	public void insertByLead(LeadVO lead) throws DAOException, DAOInsertException;
	
	public void insertByLead(LeadVO lead, LeadVO leadAnterior) throws DAOException, DAOInsertException;
	
	public void deleteByLead(LeadVO lead) throws DAOException, DAODeleteException;
	
}
