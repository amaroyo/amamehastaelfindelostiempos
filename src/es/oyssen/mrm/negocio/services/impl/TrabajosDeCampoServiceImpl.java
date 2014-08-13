package es.oyssen.mrm.negocio.services.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.oyssen.mrm.negocio.dao.DAOTrabajosDeCampo;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.services.TrabajosDeCampoService;
import es.oyssen.mrm.negocio.vo.PortafolioVO;
import es.oyssen.mrm.negocio.vo.TrabajoDeCampoInfoVO;
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
	public List<TrabajoDeCampoInfoVO> findAllNombresByAsignatura(PortafolioVO p) throws ServiceException {
		try {
			return daoTrabajosDeCampo.findAllNombresByAsignaturaTrabajo(p);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error findAllNombresByAsignatura trabajo de campo", e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public TrabajoDeCampoInfoVO findByIDs(TrabajoDeCampoVO t) throws ServiceException {
		try {
			return daoTrabajosDeCampo.findByIDs(t);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error findByIDs trabajo de campo", e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void updateTrabajoCampo(TrabajoDeCampoVO tc) throws ServiceException, DAOException {
		try {
			daoTrabajosDeCampo.updateTrabajoCampo(tc);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error updateTrabajoCampo trabajo de campo", e);
			throw new ServiceException(e);
		}		
		
	}

	@Override
	public void updateTrabajoCampoCorreccion(TrabajoDeCampoVO tc)
			throws ServiceException, DAOException {
		try {
			daoTrabajosDeCampo.updateTrabajoCampoCorreccion(tc);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error updateTrabajoCampoCorreccion trabajo de campo", e);
			throw new ServiceException(e);
		}		
		
	}

	@Override
	public TrabajoDeCampoVO findByIDsTC(TrabajoDeCampoVO tc)
			throws ServiceException {
		try {
			return daoTrabajosDeCampo.findByIDsTC(tc);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error findByIDsTC trabajo de campo", e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<TrabajoDeCampoVO> findAllByIdInfo(TrabajoDeCampoInfoVO i)
			throws ServiceException {
		try {
			return daoTrabajosDeCampo.findAllByIdInfo(i);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error findAllByIdInfo trabajo de campo", e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public TrabajoDeCampoVO findByPortafolioInfo(TrabajoDeCampoVO trabajo)
			throws ServiceException {
		try {
			return daoTrabajosDeCampo.findByPortafolioInfo(trabajo);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error findByPortafolioInfo trabajo de campo", e);
			throw new ServiceException(e.getMessage());
		}
	}
	
	
	
}
