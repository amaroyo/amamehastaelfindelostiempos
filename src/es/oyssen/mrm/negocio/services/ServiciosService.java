package es.oyssen.mrm.negocio.services;

import java.util.List;

import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.vo.CriterioServicioVO;
import es.oyssen.mrm.negocio.vo.ServicioVO;

public interface ServiciosService {

	public ServicioVO findByNombre(ServicioVO estado) throws ServiceException;
	
	public List<ServicioVO> findByCriterio(CriterioServicioVO criterio) throws ServiceException;
	
	public ServicioVO findById(ServicioVO responsable) throws ServiceException;
	
	public void insert(ServicioVO servicio) throws ServiceException;
	
	public void update(ServicioVO servicio) throws ServiceException;
	
	public void delete(ServicioVO servicio) throws ServiceException;

}
