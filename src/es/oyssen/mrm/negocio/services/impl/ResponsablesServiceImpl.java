package es.oyssen.mrm.negocio.services.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.oyssen.mrm.negocio.dao.DAOLeads;
import es.oyssen.mrm.negocio.dao.DAOResponsables;
import es.oyssen.mrm.negocio.dao.DAOResponsablesComerciales;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.services.ResponsablesService;
import es.oyssen.mrm.negocio.vo.ComercialVO;
import es.oyssen.mrm.negocio.vo.CriterioLeadVO;
import es.oyssen.mrm.negocio.vo.CriterioResponsableVO;
import es.oyssen.mrm.negocio.vo.LeadVO;
import es.oyssen.mrm.negocio.vo.ResponsableVO;

public class ResponsablesServiceImpl implements ResponsablesService{
	
	private static Log log = LogFactory.getLog(ResponsablesServiceImpl.class);

	private DAOResponsables daoResponsables;
	private DAOResponsablesComerciales daoResponsablesComerciales;
	private DAOLeads daoLeads;
	
	public void setDaoLeads(DAOLeads daoLeads) {
		this.daoLeads = daoLeads;
	}
	
	public void setDaoResponsablesComerciales(
			DAOResponsablesComerciales daoResponsablesComerciales) {
		this.daoResponsablesComerciales = daoResponsablesComerciales;
	}
	
	public void setDaoResponsables(DAOResponsables daoResponsables) {
		this.daoResponsables = daoResponsables;
	}
	
	public List<ResponsableVO> findByCriterio(CriterioResponsableVO criterio)
			throws ServiceException {
		try {
			return daoResponsables.findByCriterio(criterio);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	public ResponsableVO findById(ResponsableVO responsable)
			throws ServiceException {
		try {
			return daoResponsables.findById(responsable);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	public List<ComercialVO> findComerciales(ResponsableVO responsable)
			throws ServiceException {
		try {
			return daoResponsablesComerciales.findComerciales(responsable);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	public List<LeadVO> findLeads(ResponsableVO responsable)
			throws ServiceException {
		try {
			CriterioLeadVO criterio = new CriterioLeadVO();
			criterio.setResponsable(responsable.getIdResponsable());
			return daoLeads.findByCriterio(criterio );
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}
	
	public void update(ResponsableVO responsable) throws ServiceException {
		try {
			daoResponsables.update(responsable);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}		
	}
	
	public void delete(ResponsableVO responsable) throws ServiceException {
		try {
			CriterioLeadVO criterio = new CriterioLeadVO();
			criterio.setResponsable(responsable.getIdResponsable());
			List<LeadVO> listaLeads = daoLeads.findByCriterio(criterio);
			if (listaLeads.size() == 0){			
				daoResponsables.delete(responsable);
			}
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error eliminando el responsable", e);
			throw new ServiceException(e);
		}		
	}

	public void insert(ResponsableVO responsable) throws ServiceException {
		try {
			daoResponsables.insert(responsable);	
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error creando responsable", e);
			throw new ServiceException(e);
		}
	}			
	
}
