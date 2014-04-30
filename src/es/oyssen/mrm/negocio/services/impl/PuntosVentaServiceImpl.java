package es.oyssen.mrm.negocio.services.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.oyssen.mrm.negocio.dao.DAOComercialesPuntosVenta;
import es.oyssen.mrm.negocio.dao.DAOLeads;
import es.oyssen.mrm.negocio.dao.DAOPuntosVenta;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.services.PuntosVentaService;
import es.oyssen.mrm.negocio.vo.ComercialVO;
import es.oyssen.mrm.negocio.vo.CriterioPuntoVentaVO;
import es.oyssen.mrm.negocio.vo.LeadVO;
import es.oyssen.mrm.negocio.vo.PuntoVentaVO;

public class PuntosVentaServiceImpl implements PuntosVentaService{
	
	private static Log log = LogFactory.getLog(PuntosVentaServiceImpl.class);
	private DAOPuntosVenta daoPuntosVenta;
	private DAOComercialesPuntosVenta daoComercialesPuntosVenta;
	private DAOLeads daoLeads;
	
	public void setDaoLeads(DAOLeads daoLeads) {
		this.daoLeads = daoLeads;
	}
	
	public void setDaoComercialesPuntosVenta(
			DAOComercialesPuntosVenta daoComercialesPuntosVenta) {
		this.daoComercialesPuntosVenta = daoComercialesPuntosVenta;
	}
	
	public void setDaoPuntosVenta(DAOPuntosVenta daoPuntosVenta) {
		this.daoPuntosVenta = daoPuntosVenta;
	}
	
	public List<PuntoVentaVO> findByCriterio(CriterioPuntoVentaVO criterio)
			throws ServiceException {
		try {
			return daoPuntosVenta.findByCriterio(criterio);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
	public PuntoVentaVO findById(PuntoVentaVO punto) throws ServiceException {
		try {
			return daoPuntosVenta.findById(punto);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}
	public void save(PuntoVentaVO punto) throws ServiceException,
			DAOException {
		daoPuntosVenta.insert(punto);
		
	}
	public void update(PuntoVentaVO punto) throws ServiceException,
			DAOException {
		daoPuntosVenta.update(punto);
		
	}
	public void delete(PuntoVentaVO punto) throws ServiceException,
			DAOException {
		daoPuntosVenta.delete(punto);
		
	}

	public List<ComercialVO> findComerciales(PuntoVentaVO punto)
			throws ServiceException {
		try {
			return daoComercialesPuntosVenta.findComerciales(punto);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	public void addComercial(PuntoVentaVO punto, ComercialVO comercial)
			throws ServiceException {
		try {
			daoComercialesPuntosVenta.insert(punto, comercial);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
		
	}

	public void deleteComercial(PuntoVentaVO punto, ComercialVO comercial)
			throws ServiceException {
		try {
			daoComercialesPuntosVenta.delete(punto, comercial);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}		
	}

	public List<LeadVO> findLeads(PuntoVentaVO punto) throws ServiceException {
		try {
			return daoLeads.findByPuntoVenta(punto);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}
	
}
