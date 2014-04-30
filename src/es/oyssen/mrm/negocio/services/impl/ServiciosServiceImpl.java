package es.oyssen.mrm.negocio.services.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.oyssen.mrm.negocio.dao.DAOFicheros;
import es.oyssen.mrm.negocio.dao.DAOLeads;
import es.oyssen.mrm.negocio.dao.DAOServicios;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.services.ServiciosService;
import es.oyssen.mrm.negocio.vo.CriterioLeadVO;
import es.oyssen.mrm.negocio.vo.CriterioServicioVO;
import es.oyssen.mrm.negocio.vo.LeadVO;
import es.oyssen.mrm.negocio.vo.ServicioVO;

public class ServiciosServiceImpl implements ServiciosService{
	
	private static Log log = LogFactory.getLog(ServiciosServiceImpl.class);

	private DAOServicios daoServicios;
	private DAOFicheros daoFicheros;
	private DAOLeads daoLeads;
	
	public void setDaoLeads(DAOLeads daoLeads) {
		this.daoLeads = daoLeads;
	}
	
	public void setDaoFicheros(DAOFicheros daoFicheros) {
		this.daoFicheros = daoFicheros;
	}	
	
	public void setDaoServicios(DAOServicios daoServicios) {
		this.daoServicios = daoServicios;
	}
	

	public ServicioVO findByNombre(ServicioVO servicio)
			throws ServiceException {
		try {
			return daoServicios.findByNombre(servicio);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}
	
	public List<ServicioVO> findByCriterio(CriterioServicioVO criterio)
			throws ServiceException {
		try {
			return daoServicios.findByCriterio(criterio);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	public ServicioVO findById(ServicioVO servicio)
			throws ServiceException {
		try {
			return daoServicios.findById(servicio);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}
	
	public void update(ServicioVO servicio) throws ServiceException {
		try {
			daoServicios.update(servicio);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}		
	}
	
	public void delete(ServicioVO servicio) throws ServiceException {
		try {
			CriterioLeadVO criterio = new CriterioLeadVO();
			criterio.setServicio(servicio.getIdServicio());
			List<LeadVO> listaLeads = daoLeads.findByCriterio(criterio);
			if (listaLeads.size() == 0){			
				daoFicheros.deleteByServicio(servicio);
				daoServicios.delete(servicio);
			}
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error eliminando el servicio", e);
			throw new ServiceException(e);
		}		
	}

	public void insert(ServicioVO servicio) throws ServiceException {
		try {
			daoServicios.insert(servicio);	
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error creando servicio", e);
			throw new ServiceException(e);
		}
	}			
	
}
