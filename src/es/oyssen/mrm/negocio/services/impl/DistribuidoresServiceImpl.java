package es.oyssen.mrm.negocio.services.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.oyssen.mrm.negocio.dao.DAOContactosDistribuidor;
import es.oyssen.mrm.negocio.dao.DAODistribuidores;
import es.oyssen.mrm.negocio.dao.DAOLeads;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.services.DistribuidoresService;
import es.oyssen.mrm.negocio.vo.CanalVO;
import es.oyssen.mrm.negocio.vo.ComercialVO;
import es.oyssen.mrm.negocio.vo.CriterioLeadVO;
import es.oyssen.mrm.negocio.vo.DistribuidorVO;
import es.oyssen.mrm.negocio.vo.CriterioDistribuidorVO;
import es.oyssen.mrm.negocio.vo.LeadVO;


public class DistribuidoresServiceImpl implements DistribuidoresService{
	
	private static Log log = LogFactory.getLog(DistribuidoresServiceImpl.class);

	private DAODistribuidores daoDistribuidores;
	private DAOContactosDistribuidor daoContactosDistribuidor;
	private DAOLeads daoLeads;
	
	public void setDaoLeads(DAOLeads daoLeads) {
		this.daoLeads = daoLeads;
	}
	
	public void setDaoContactosDistribuidor(DAOContactosDistribuidor daoContactosDistribuidor) {
		this.daoContactosDistribuidor = daoContactosDistribuidor;
	}	
	
	public void setDaoDistribuidores(DAODistribuidores daoDistribuidores) {
		this.daoDistribuidores = daoDistribuidores;
	}	
	
	public List<DistribuidorVO> findByCriterio(CriterioDistribuidorVO criterio)
			throws ServiceException {
		try {
			return daoDistribuidores.findByCriterio(criterio);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	public DistribuidorVO findById(DistribuidorVO distribuidor)
			throws ServiceException {
		try {
			return daoDistribuidores.findById(distribuidor);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}
	
	public void update(DistribuidorVO distribuidor) throws ServiceException {
		try {
			daoDistribuidores.update(distribuidor);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}		
	}
	
	public void delete(DistribuidorVO distribuidor) throws ServiceException {
		try {
			CriterioLeadVO criterio = new CriterioLeadVO();
			criterio.setDistribuidor(distribuidor.getIdDistribuidor());
			List<LeadVO> listaLeads = daoLeads.findByCriterio(criterio);
			if (listaLeads.size() == 0){
				daoContactosDistribuidor.deleteByDistribuidor(distribuidor);
				daoDistribuidores.delete(distribuidor);
			}
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error eliminando el distribuidor", e);
			throw new ServiceException(e);
		}		
	}

	public void insert(DistribuidorVO distribuidor) throws ServiceException {
		try {
			daoDistribuidores.insert(distribuidor);	
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error creando distribuidor", e);
			throw new ServiceException(e);
		}
	}			

	public List<DistribuidorVO> findByCanal(CanalVO canal)
			throws ServiceException {
		try {
			return daoDistribuidores.findByCanal(canal);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}
	
	public void bloquear(DistribuidorVO distribuidor) throws ServiceException {
		try {
			daoDistribuidores.bloquear(distribuidor);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}		
	}
	
	public void desbloquear(DistribuidorVO distribuidor) throws ServiceException {
		try {
			daoDistribuidores.desbloquear(distribuidor);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}		
	}
	
	public void updateCanal(String idLead) throws ServiceException {
		try {
			daoDistribuidores.updateCanal(idLead);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}		
	}
	
}
