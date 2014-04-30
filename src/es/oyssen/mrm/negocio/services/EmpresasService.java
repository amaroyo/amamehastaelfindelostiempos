package es.oyssen.mrm.negocio.services;

import java.util.List;

import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.vo.CriterioEmpresaVO;
import es.oyssen.mrm.negocio.vo.EmpresaVO;
import es.oyssen.mrm.negocio.vo.LeadVO;

public interface EmpresasService {

	public List<EmpresaVO> findByCriterio(CriterioEmpresaVO criterio) throws ServiceException;	
	
	public EmpresaVO findById(EmpresaVO empresa) throws ServiceException;	
	
	public EmpresaVO findByOrgn(EmpresaVO empresa) throws ServiceException;	

	public List<LeadVO> findLeads(EmpresaVO empresa) throws ServiceException;
	
	public void insert(EmpresaVO empresa) throws ServiceException;
	
	public void update(EmpresaVO empresa) throws ServiceException;
	
	public void delete(EmpresaVO empresa) throws ServiceException;
}
