package es.oyssen.mrm.negocio.services;

import java.util.List;

import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.vo.ComercialVO;
import es.oyssen.mrm.negocio.vo.CriterioPuntoVentaVO;
import es.oyssen.mrm.negocio.vo.LeadVO;
import es.oyssen.mrm.negocio.vo.PuntoVentaVO;

public interface PuntosVentaService {

	public List<PuntoVentaVO> findByCriterio(CriterioPuntoVentaVO criterio) throws ServiceException;
	
	
	public PuntoVentaVO findById(PuntoVentaVO punto) throws ServiceException;
	
	
	public void save(PuntoVentaVO punto) throws ServiceException, DAOException;
	

	public void update(PuntoVentaVO punto) throws ServiceException, DAOException;
	

	public void delete(PuntoVentaVO punto) throws ServiceException, DAOException;
	
	
	public List<ComercialVO> findComerciales(PuntoVentaVO punto) throws ServiceException;
	
	
	public void addComercial(PuntoVentaVO punto, ComercialVO comercial) throws ServiceException;
	
	
	public void deleteComercial(PuntoVentaVO punto, ComercialVO comercial) throws ServiceException;
	
	public List<LeadVO> findLeads(PuntoVentaVO punto) throws ServiceException;

}
