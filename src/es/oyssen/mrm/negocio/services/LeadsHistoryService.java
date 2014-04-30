package es.oyssen.mrm.negocio.services;

import java.util.List;

import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.vo.LeadHistoryVO;
import es.oyssen.mrm.negocio.vo.LeadVO;

public interface LeadsHistoryService {
	
	public List<LeadHistoryVO> findByLead(LeadVO lead) throws ServiceException;
	
	public void insertByLead(LeadVO lead) throws ServiceException;
	
	public void insertByLead(LeadVO lead, LeadVO leadAnterior) throws ServiceException;
	
	public void deleteByLead(LeadVO lead) throws ServiceException;

}
