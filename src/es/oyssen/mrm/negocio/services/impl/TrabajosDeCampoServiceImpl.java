package es.oyssen.mrm.negocio.services.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.oyssen.mrm.negocio.dao.DAOTrabajosDeCampo;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.services.TrabajosDeCampoService;
import es.oyssen.mrm.negocio.vo.PortafolioVO;
import es.oyssen.mrm.negocio.vo.TrabajoDeCampoNombreVO;
import es.oyssen.mrm.negocio.vo.TrabajoDeCampoVO;
import es.oyssen.mrm.negocio.vo.UsuarioTrabajoCampoVO;

public class TrabajosDeCampoServiceImpl implements TrabajosDeCampoService{
	
	private static Log log = LogFactory.getLog(TrabajosDeCampoServiceImpl.class);
	private DAOTrabajosDeCampo daoTrabajosDeCampo;

	
	public void setDaoTrabajosDeCampo(DAOTrabajosDeCampo daoTrabajosDeCampo) {
		this.daoTrabajosDeCampo = daoTrabajosDeCampo;
	}
	
	public void insert(TrabajoDeCampoVO trabajoDeCampo)
			throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {
			daoTrabajosDeCampo.insert(trabajoDeCampo);	
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error creando trabajo de campo", e);
			throw new ServiceException(e);
		}		
	}

	public void update(TrabajoDeCampoVO trabajoDeCampo)
			throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {
			daoTrabajosDeCampo.update(trabajoDeCampo);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error update trabajo de campo", e);
			throw new ServiceException(e);
		}		
	}

	public void delete(TrabajoDeCampoVO trabajoDeCampo)
			throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {			
			daoTrabajosDeCampo.delete(trabajoDeCampo);			
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error delete trabajo de campo", e);
			throw new ServiceException(e);
		}			
	}

	
	
	public List<TrabajoDeCampoVO> findAllByPortafolio(TrabajoDeCampoVO trabajoDeCampo) throws ServiceException {
		try {
			return daoTrabajosDeCampo.findAllByPortafolio(trabajoDeCampo);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error findAllByPortafolio trabajo de campo", e);
			throw new ServiceException(e.getMessage());
		}
	}

	
	public List<TrabajoDeCampoVO> findAllByAsignatura(PortafolioVO p) throws ServiceException {
		try {
			return daoTrabajosDeCampo.findAllByAsignatura(p);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error findAllByAsignatura trabajo de campo", e);
			throw new ServiceException(e.getMessage());
		}
	}

	
	public List<UsuarioTrabajoCampoVO> findAllByAsignaturaTrabajo(PortafolioVO p, TrabajoDeCampoVO t) throws ServiceException {
		try {
			return daoTrabajosDeCampo.findAllByAsignaturaTrabajo(p,t);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error findAllByAsignaturaTrabajo trabajo de campo", e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<TrabajoDeCampoNombreVO> findAllNombresByAsignatura(PortafolioVO p) throws ServiceException {
		try {
			return daoTrabajosDeCampo.findAllNombresByAsignaturaTrabajo(p);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error findAllNombresByAsignatura trabajo de campo", e);
			throw new ServiceException(e.getMessage());
		}
	}
	
	
	
}
