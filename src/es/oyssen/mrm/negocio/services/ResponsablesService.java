package es.oyssen.mrm.negocio.services;

import java.util.List;

import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.vo.ComercialVO;
import es.oyssen.mrm.negocio.vo.CriterioResponsableVO;
import es.oyssen.mrm.negocio.vo.LeadVO;
import es.oyssen.mrm.negocio.vo.ResponsableVO;

public interface ResponsablesService {

	public List<ResponsableVO> findByCriterio(CriterioResponsableVO criterio) throws ServiceException;
	
	public ResponsableVO findById(ResponsableVO responsable) throws ServiceException;
	
	public List<ComercialVO> findComerciales(ResponsableVO responsable) throws ServiceException;
	
	public List<LeadVO> findLeads(ResponsableVO responsable) throws ServiceException;
	
	public void update(ResponsableVO responsable) throws ServiceException;
	
	public void delete(ResponsableVO responsable) throws ServiceException;
	
	public void insert(ResponsableVO responsable) throws ServiceException;

}
