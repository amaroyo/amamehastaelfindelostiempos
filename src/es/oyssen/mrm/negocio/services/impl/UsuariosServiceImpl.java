package es.oyssen.mrm.negocio.services.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.oyssen.mrm.negocio.dao.DAOUsuarios;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.services.UsuariosService;
import es.oyssen.mrm.negocio.vo.GrupoVO;
import es.oyssen.mrm.negocio.vo.PortafolioVO;
import es.oyssen.mrm.negocio.vo.UsuarioAsignaturaVO;
import es.oyssen.mrm.negocio.vo.UsuarioVO;

public class UsuariosServiceImpl implements UsuariosService{
	
	private static Log log = LogFactory.getLog(UsuariosServiceImpl.class);
	private DAOUsuarios daoUsuarios;

	
	public void setDaoUsuarios(DAOUsuarios daoUsuarios) {
		this.daoUsuarios = daoUsuarios;
	}
	
	public void insert(UsuarioVO usuario)
			throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {
			daoUsuarios.insert(usuario);	
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error creando usuario", e);
			throw new ServiceException(e);
		}		
	}

	public void update(UsuarioVO usuario)
			throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {
			daoUsuarios.update(usuario);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error update usuario", e);
			throw new ServiceException(e);
		}		
	}

	public void delete(UsuarioVO usuario)
			throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {			
			daoUsuarios.delete(usuario);			
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error eliminando usuario", e);
			throw new ServiceException(e);
		}			
	}

	public List<UsuarioVO> findAll() throws ServiceException {
		try {
			return daoUsuarios.findAll();
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error findAll usuario", e);
			throw new ServiceException(e.getMessage());
		}
	}
	
	public List<UsuarioVO> findByGrupo(GrupoVO grupo) throws ServiceException {
		try {
			return daoUsuarios.findByGrupo(grupo);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error findByGrupo usuario", e);
			throw new ServiceException(e.getMessage());
		}
	}


	public UsuarioVO findById(UsuarioVO usuario)
			throws ServiceException {
		try {
			return daoUsuarios.findById(usuario);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error findById usuario", e);
			throw new ServiceException(e);
		}
	}
	
	public UsuarioVO findByCorreo(UsuarioVO usuario)
			throws ServiceException {
		try {
			return daoUsuarios.findByCorreo(usuario);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error findByCorreo usuario", e);
			throw new ServiceException(e);
		}
	}
	
	public UsuarioVO findByCorreoPass(UsuarioVO usuario)
			throws ServiceException {
		try {
			return daoUsuarios.findByCorreoPass(usuario);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error findByCorreoPass usuario", e);
			throw new ServiceException(e);
		}
	}

	@Override
	public UsuarioVO findByDni(UsuarioVO usuario)
			throws ServiceException {
		try {
			return daoUsuarios.findByDni(usuario);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error findByDni usuario", e);
			throw new ServiceException(e);
		}
	}
	
	@Override
	public UsuarioVO findByNombreApellidos(UsuarioVO usuario)
			throws ServiceException {
		try {
			return daoUsuarios.findByNombreApellidos(usuario);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error findByNombreApellidos usuario", e);
			throw new ServiceException(e);
		}
	}

	@Override
	public List<UsuarioAsignaturaVO> findAllbyAnyoAcademico(PortafolioVO p)
			throws ServiceException {
		try {
			return daoUsuarios.findAllbyAnyoAcademico(p);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error findAllbyAnyoAcademico usuario", e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<UsuarioAsignaturaVO> findAllbyProfesor(PortafolioVO p)
			throws ServiceException {
		try {
			return daoUsuarios.findAllbyProfesor(p);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error findAllbyProfesor usuario", e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<UsuarioAsignaturaVO> findAllbyProfesorDemas(PortafolioVO p)
			throws ServiceException {
		try {
			return daoUsuarios.findAllbyProfesorDemas(p);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error findAllbyProfesorDemas usuario", e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void updateGrupo(UsuarioVO usuario) throws ServiceException,
			DAOException {
		try {
			daoUsuarios.updateGrupo(usuario);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error updateGrupo usuario", e);
			throw new ServiceException(e);
		}		
		
	}

	@Override
	public List<UsuarioAsignaturaVO> findAllByAsignatura(PortafolioVO p)
			throws ServiceException {
		try {
			return daoUsuarios.findAllByAsignatura(p);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error findAllByAsignatura usuario", e);
			throw new ServiceException(e.getMessage());
		}
	}
	
}
