package es.oyssen.mrm.negocio.services.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.oyssen.mrm.negocio.dao.DAOPortafolios;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.services.PortafoliosService;
import es.oyssen.mrm.negocio.vo.CasoClinicoVO;
import es.oyssen.mrm.negocio.vo.DatosUsuarioEstanciaUnidadClinicaVO;
import es.oyssen.mrm.negocio.vo.DiarioReflexivoVO;
import es.oyssen.mrm.negocio.vo.PortafolioVO;
import es.oyssen.mrm.negocio.vo.TrabajoDeCampoVO;
import es.oyssen.mrm.negocio.vo.UsuarioEstanciaUnidadClinicaVO;
import es.oyssen.mrm.negocio.vo.UsuarioPortafolioVO;

public class PortafoliosServiceImpl implements PortafoliosService{
	
	private static Log log = LogFactory.getLog(PortafoliosServiceImpl.class);
	private DAOPortafolios daoPortafolios;

	
	public void setDaoPortafolios(DAOPortafolios daoPortafolios) {
		this.daoPortafolios = daoPortafolios;
	}
	
	public void insert(PortafolioVO portafolio)
			throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {
			daoPortafolios.insert(portafolio);	
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error creando portafolio", e);
			throw new ServiceException(e);
		}		
	}

	public void update(PortafolioVO portafolio)
			throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {
			daoPortafolios.update(portafolio);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error haciendo update portafolio", e);
			throw new ServiceException(e);
		}		
	}

	public void delete(PortafolioVO portafolio)
			throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {			
			daoPortafolios.delete(portafolio);			
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error eliminando portafolio", e);
			throw new ServiceException(e);
		}			
	}

	public List<PortafolioVO> findAll(PortafolioVO portafolio) throws ServiceException {
		try {
			return daoPortafolios.findAll(portafolio);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error findAll portafolio", e);
			throw new ServiceException(e.getMessage());
		}
	}
	
	

	public PortafolioVO findById(PortafolioVO portafolio)
			throws ServiceException {
		try {
			return daoPortafolios.findById(portafolio);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error findById portafolio", e);
			throw new ServiceException(e);
		}
	}
	

	
	public List<PortafolioVO> findByAlumno(PortafolioVO portafolio)  throws ServiceException {
		try {
			return daoPortafolios.findByAlumno(portafolio);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error findByAlumno portafolio", e);
			throw new ServiceException(e.getMessage());
		}
	}

	public List<PortafolioVO> findByProfesor(PortafolioVO portafolio)  throws ServiceException {
		try {
			return daoPortafolios.findByProfesor(portafolio);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error findByProfesor portafolio", e);
			throw new ServiceException(e.getMessage());
		}
	}
	
	public List<PortafolioVO> findByAsignatura(PortafolioVO portafolio)  throws ServiceException {
		try {
			return daoPortafolios.findByAsignatura(portafolio);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error findByAsignatura portafolio", e);
			throw new ServiceException(e.getMessage());
		}
	}
	
	public List<PortafolioVO> findByAnyoAcademico(PortafolioVO portafolio)  throws ServiceException {
		try {
			return daoPortafolios.findByAnyoAcademico(portafolio);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error findByAnyoAcademico portafolio", e);
			throw new ServiceException(e.getMessage());
		}
	}
	
	
	public List<UsuarioEstanciaUnidadClinicaVO> findUsuariosEstanciaUnidadClinica(PortafolioVO portafolio)  throws ServiceException {
		try {
			return daoPortafolios.findUsuariosEstanciaUnidadClinica(portafolio);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error findUsuariosEstanciaUnidadClinica portafolio", e);
			throw new ServiceException(e.getMessage());
		}
	}

	public DatosUsuarioEstanciaUnidadClinicaVO findDatosUsuarioEstanciaUnidadClinica(PortafolioVO portafolio) throws ServiceException {
		try {
			return daoPortafolios.findDatosUsuarioEstanciaUnidadClinica(portafolio);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error findUsuariosEstanciaUnidadClinica portafolio", e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void updateEstancia(DatosUsuarioEstanciaUnidadClinicaVO data) 
						throws es.oyssen.mrm.negocio.exceptions.ServiceException,
							es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		
		try {
			daoPortafolios.updateEstancia(data);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error haciendo update en la estancia Unidad Clinica", e);
			throw new ServiceException(e);
		}
		
		
	}

	@Override
	public List<UsuarioPortafolioVO> findUsuariosByAsignatura(PortafolioVO p) throws ServiceException {
		try {
			return daoPortafolios.findUsuariosByAsignatura(p);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error findUsuariosByAsignatura portafolio", e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<TrabajoDeCampoVO> findTrabajosByPortafolio(PortafolioVO p, TrabajoDeCampoVO t) throws ServiceException {
		try {
			return daoPortafolios.findTrabajosByPortafolio(p,t);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error findTrabajosByPortafolio portafolio", e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<CasoClinicoVO> findCasosByPortafolio(PortafolioVO p) throws ServiceException {
		try {
			return daoPortafolios.findCasosByPortafolio(p);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error findCasosByPortafolio portafolio", e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<DiarioReflexivoVO> findDiariosByPortafolio(PortafolioVO p) throws ServiceException {
		try {
			return daoPortafolios.findDiariosByPortafolio(p);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error findDiariosByPortafolio portafolio", e);
			throw new ServiceException(e.getMessage());
		}
	}
	
	
	
				
	
	
	
}
