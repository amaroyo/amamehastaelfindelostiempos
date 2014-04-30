package es.oyssen.mrm.negocio.dao;

import java.util.List;

import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.vo.ActionHistoryVO;
import es.oyssen.mrm.negocio.vo.LeadVO;

public interface DAOLeadActionsHistory {
			
	public List<ActionHistoryVO> findByCriterio(LeadVO lead) throws DAOException;
	
	public void insert(LeadVO lead, ActionHistoryVO action) throws DAOException, DAOInsertException;
	
	public void delete(ActionHistoryVO action) throws DAOException, DAODeleteException;

}
