package es.oyssen.mrm.negocio.services;

import java.util.List;

import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.vo.EstanciaUnidadClinicaVO;

public interface EstanciasUnidadClinicaService {
	
	public void insert(EstanciaUnidadClinicaVO estanciaUnidadClinica) throws ServiceException, DAOException;
	
	public void update(EstanciaUnidadClinicaVO estanciaUnidadClinica) throws ServiceException, DAOException;
	
	public void delete(EstanciaUnidadClinicaVO estanciaUnidadClinica) throws ServiceException, DAOException;
	
	public List<EstanciaUnidadClinicaVO> findByPortafolio(EstanciaUnidadClinicaVO estanciaUnidadClinica) throws ServiceException;
	
	public List<EstanciaUnidadClinicaVO> findByCentroTurno(EstanciaUnidadClinicaVO estanciaUnidadClinica) throws ServiceException;
	

}
