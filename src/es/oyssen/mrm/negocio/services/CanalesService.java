package es.oyssen.mrm.negocio.services;

import java.util.List;

import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.vo.CanalVO;
import es.oyssen.mrm.negocio.vo.CriterioCanalVO;


public interface CanalesService {

	public List<CanalVO> findByCriterio(CriterioCanalVO criterio) throws ServiceException;
	
	public CanalVO findById(CanalVO canal) throws ServiceException;
	
	public void update(CanalVO canal) throws ServiceException;
	
	public void delete(CanalVO canal) throws ServiceException;
	
	public void insert(CanalVO canal) throws ServiceException;

}
