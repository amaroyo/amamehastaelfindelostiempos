package es.oyssen.mrm.negocio.services;

import java.io.InputStream;
import java.util.List;

import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.vo.ActionHistoryVO;
import es.oyssen.mrm.negocio.vo.CriterioLeadVO;
import es.oyssen.mrm.negocio.vo.LeadVO;


public interface LeadsService {

	public void process(InputStream fis, String idCanal) throws ServiceException;
	
	public List<LeadVO> findByCriterio(CriterioLeadVO criterio) throws ServiceException;
	
	public LeadVO findById(LeadVO lead) throws ServiceException;
	
	public void update(LeadVO lead) throws ServiceException;
	
	public void insert(LeadVO lead) throws ServiceException;
	
	public void insertByName(LeadVO lead) throws ServiceException;
	
	public void delete(LeadVO lead) throws ServiceException;
	
	public void addAction(LeadVO lead, ActionHistoryVO action) throws ServiceException;
	
	public List<ActionHistoryVO> findActionsHistory(LeadVO lead) throws ServiceException;
	
	public void deleteActionHistory(ActionHistoryVO action) throws ServiceException;
	
	public void updateFechaModificacion(LeadVO lead) throws ServiceException;
	
}
