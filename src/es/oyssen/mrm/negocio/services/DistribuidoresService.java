package es.oyssen.mrm.negocio.services;

import java.util.List;

import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.vo.CanalVO;
import es.oyssen.mrm.negocio.vo.ComercialVO;
import es.oyssen.mrm.negocio.vo.DistribuidorVO;
import es.oyssen.mrm.negocio.vo.CriterioDistribuidorVO;


public interface DistribuidoresService {

	public List<DistribuidorVO> findByCriterio(CriterioDistribuidorVO criterio) throws ServiceException;
	
	public DistribuidorVO findById(DistribuidorVO distribuidor) throws ServiceException;
	
	public void update(DistribuidorVO distribuidor) throws ServiceException;
	
	public void delete(DistribuidorVO distribuidor) throws ServiceException;
	
	public void insert(DistribuidorVO distribuidor) throws ServiceException;
	
	public List<DistribuidorVO> findByCanal(CanalVO canal) throws ServiceException;
	
	public void bloquear(DistribuidorVO distribuidor) throws ServiceException;
	
	public void desbloquear(DistribuidorVO distribuidor) throws ServiceException;
	
	public void updateCanal(String idLead) throws ServiceException;

}
