package es.oyssen.mrm.negocio.services.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.oyssen.mrm.negocio.dao.DAOEstanciasUnidadClinica;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.services.EstanciasUnidadClinicaService;
import es.oyssen.mrm.negocio.vo.EstanciaUnidadClinicaVO;

public class EstanciasUnidadClinicaServiceImpl implements EstanciasUnidadClinicaService{
	
	private static Log log = LogFactory.getLog(EstanciasUnidadClinicaServiceImpl.class);
	private DAOEstanciasUnidadClinica daoEstanciasUnidadClinica;

	
	public void setDaoEstanciasUnidadClinica(DAOEstanciasUnidadClinica daoEstanciasUnidadClinica) {
		this.daoEstanciasUnidadClinica = daoEstanciasUnidadClinica;
	}
	
	public void insert(EstanciaUnidadClinicaVO estanciaUnidadClinica)
			throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {
			daoEstanciasUnidadClinica.insert(estanciaUnidadClinica);	
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error creando estancia por unidad clinica", e);
			throw new ServiceException(e);
		}		
	}

	public void update(EstanciaUnidadClinicaVO estanciaUnidadClinica)
			throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {
			daoEstanciasUnidadClinica.update(estanciaUnidadClinica);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error update estancia por unidad clinica", e);
			throw new ServiceException(e);
		}		
	}

	public void delete(EstanciaUnidadClinicaVO estanciaUnidadClinica)
			throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {			
			daoEstanciasUnidadClinica.delete(estanciaUnidadClinica);			
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error delete estancia por unidad clinica", e);
			throw new ServiceException(e);
		}			
	}

	
	
	public List<EstanciaUnidadClinicaVO> findByPortafolio(EstanciaUnidadClinicaVO estanciaUnidadClinica) throws ServiceException {
		try {
			return daoEstanciasUnidadClinica.findByPortafolio(estanciaUnidadClinica);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error findByPortafolio estancia por unidad clinica", e);
			throw new ServiceException(e.getMessage());
		}
	}
	
	public List<EstanciaUnidadClinicaVO> findByCentroTurno(EstanciaUnidadClinicaVO estanciaUnidadClinica) throws ServiceException {
		try {
			return daoEstanciasUnidadClinica.findByCentroTurno(estanciaUnidadClinica);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error findByCentroTurno estancia por unidad clinica", e);
			throw new ServiceException(e.getMessage());
		}
	}
	
}
