package es.oyssen.mrm.negocio.services.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.oyssen.mrm.negocio.dao.DAOComerciales;
import es.oyssen.mrm.negocio.dao.DAODistribuidores;
import es.oyssen.mrm.negocio.dao.DAOLeads;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.services.ComercialesService;
import es.oyssen.mrm.negocio.vo.CanalVO;
import es.oyssen.mrm.negocio.vo.ComercialVO;
import es.oyssen.mrm.negocio.vo.CriterioComercialVO;
import es.oyssen.mrm.negocio.vo.CriterioLeadVO;
import es.oyssen.mrm.negocio.vo.DistribuidorVO;
import es.oyssen.mrm.negocio.vo.LeadVO;

public class ComercialesServiceImpl implements ComercialesService{
	
	private static Log log = LogFactory.getLog(ComercialesServiceImpl.class);
	private DAOComerciales daoComerciales;
	private DAOLeads daoLeads;
	private DAODistribuidores daoDistribuidores;

	
	public void setDaoDistribuidores(DAODistribuidores daoDistribuidores) {
		this.daoDistribuidores = daoDistribuidores;
	}
	
	public void setDaoLeads(DAOLeads daoLeads) {
		this.daoLeads = daoLeads;
	}

	public void setDaoComerciales(DAOComerciales daoComerciales) {
		this.daoComerciales = daoComerciales;
	}

	public void insert(ComercialVO comercial)
			throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {
			DistribuidorVO distribuidor = new DistribuidorVO();
			distribuidor.setIdDistribuidor(comercial.getIdDistribuidor());
			distribuidor = daoDistribuidores.findById(distribuidor);
			comercial.setIdCanal(distribuidor.getIdCanal());
			daoComerciales.insert(comercial);	
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error creando comercial", e);
			throw new ServiceException(e);
		}		
	}

	public void update(ComercialVO comercial)
			throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {
			daoComerciales.update(comercial);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}		
	}

	public void delete(ComercialVO comercial)
			throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {			
			CriterioLeadVO criterio = new CriterioLeadVO();
			criterio.setComercial(comercial.getIdComercial());
			List<LeadVO> listaLeads = daoLeads.findByCriterio(criterio);
			if (listaLeads.size() == 0){
				daoComerciales.delete(comercial);
			}			
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error eliminando comercial", e);
			throw new ServiceException(e);
		}			
	}

	public List<ComercialVO> findByCriterio(CriterioComercialVO criterio)
			throws ServiceException {
		try {
			return daoComerciales.findByCriterio(criterio);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error buscando comerciales", e);
			throw new ServiceException("Error buscando comerciales", e);
		}
	}

	public ComercialVO findById(ComercialVO comercial) throws ServiceException {
		try {
			return daoComerciales.findById(comercial);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	public List<LeadVO> findLeads(ComercialVO comercial)
			throws ServiceException {
		try {
			CriterioLeadVO criterio = new CriterioLeadVO();
			criterio.setComercial(comercial.getIdComercial());
			return daoLeads.findByCriterio(criterio );
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	public List<ComercialVO> findByDistribuidor(DistribuidorVO distribuidor)
			throws ServiceException {
		try {
			return daoComerciales.findByDistribuidor(distribuidor);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}
	
	public List<ComercialVO> findByCanal(CanalVO canal)
			throws ServiceException {
		try {
			return daoComerciales.findByCanal(canal);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}	

	public void updateCanal(String idLead) throws ServiceException {
		try {
			daoComerciales.updateCanal(idLead);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}		
	}
}
