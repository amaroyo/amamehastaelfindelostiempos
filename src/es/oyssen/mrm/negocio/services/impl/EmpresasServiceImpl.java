package es.oyssen.mrm.negocio.services.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.oyssen.mrm.negocio.dao.DAOEmpresas;
import es.oyssen.mrm.negocio.dao.DAOLeads;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.services.EmpresasService;
import es.oyssen.mrm.negocio.vo.CriterioEmpresaVO;
import es.oyssen.mrm.negocio.vo.CriterioLeadVO;
import es.oyssen.mrm.negocio.vo.EmpresaVO;
import es.oyssen.mrm.negocio.vo.LeadVO;

public class EmpresasServiceImpl implements EmpresasService{
	
	private static Log log = LogFactory.getLog(EmpresasServiceImpl.class);
	private DAOEmpresas daoEmpresas;
	private DAOLeads daoLeads;
	
	public void setDaoLeads(DAOLeads daoLeads) {
		this.daoLeads = daoLeads;
	}
	
	public void setDaoEmpresas(DAOEmpresas daoEmpresas) {
		this.daoEmpresas = daoEmpresas;
	}

	public List<EmpresaVO> findByCriterio(CriterioEmpresaVO criterio)
			throws ServiceException {		
		try {
			return daoEmpresas.findByCriterio(criterio);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	public EmpresaVO findById(EmpresaVO empresa) throws ServiceException {		
		try {
			return daoEmpresas.findById(empresa);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}
	
	public EmpresaVO findByOrgn(EmpresaVO empresa) throws ServiceException {		
		try {
			return daoEmpresas.findByOrgn(empresa);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	public List<LeadVO> findLeads(EmpresaVO empresa) throws ServiceException {
		CriterioLeadVO criterio = new CriterioLeadVO();
		criterio.setEmpresa(empresa.getIdEmpresa());
		try {
			return daoLeads.findByCriterio(criterio );
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	public void insert(EmpresaVO empresa) throws ServiceException {
		try {
			daoEmpresas.insert(empresa);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("No se ha podido crear la empresa", e);
			throw new ServiceException(e);
		}
	}

	public void update(EmpresaVO empresa) throws ServiceException {
		try {
			daoEmpresas.update(empresa);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("No se ha podido actualizar la empresa", e);
			throw new ServiceException(e);
		}
	}

	public void delete(EmpresaVO empresa) throws ServiceException {
		try {			
			CriterioLeadVO criterio = new CriterioLeadVO();
			criterio.setEmpresa(empresa.getIdEmpresa());
			List<LeadVO> listaLeads = daoLeads.findByCriterio(criterio);
			if (listaLeads.size() == 0){
				daoEmpresas.delete(empresa);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error eliminando la empresa", e);
			throw new ServiceException(e);
		}
	}
	
}
