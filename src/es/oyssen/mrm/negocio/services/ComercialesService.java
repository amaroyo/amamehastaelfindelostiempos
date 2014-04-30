package es.oyssen.mrm.negocio.services;

import java.util.List;

import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.vo.CanalVO;
import es.oyssen.mrm.negocio.vo.ComercialVO;
import es.oyssen.mrm.negocio.vo.CriterioComercialVO;
import es.oyssen.mrm.negocio.vo.DistribuidorVO;
import es.oyssen.mrm.negocio.vo.LeadVO;

public interface ComercialesService {

	public List<ComercialVO> findByCriterio(CriterioComercialVO criterio) throws ServiceException;
		
	public ComercialVO findById(ComercialVO comercial) throws ServiceException;
	
	public void insert(ComercialVO comercial) throws ServiceException, DAOException;
	
	public void update(ComercialVO comercial) throws ServiceException, DAOException;
	
	public void delete(ComercialVO comercial) throws ServiceException, DAOException;
	
	public List<LeadVO> findLeads(ComercialVO comercial) throws ServiceException;
	
	public List<ComercialVO> findByDistribuidor(DistribuidorVO distribuidor) throws ServiceException;
	
	public List<ComercialVO> findByCanal(CanalVO canal) throws ServiceException;
	
	public void updateCanal(String idLead) throws ServiceException;

}
