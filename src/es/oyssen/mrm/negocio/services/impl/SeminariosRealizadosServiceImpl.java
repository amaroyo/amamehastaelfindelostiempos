package es.oyssen.mrm.negocio.services.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.oyssen.mrm.negocio.dao.DAOSeminariosRealizados;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.services.SeminariosRealizadosService;
import es.oyssen.mrm.negocio.vo.PortafolioVO;
import es.oyssen.mrm.negocio.vo.SeminarioAsignaturaAnyoVO;
import es.oyssen.mrm.negocio.vo.SeminarioRealizadoVO;
import es.oyssen.mrm.negocio.vo.UsuarioAnyoSeminarioVO;
import es.oyssen.mrm.negocio.vo.UsuarioVO;

public class SeminariosRealizadosServiceImpl implements SeminariosRealizadosService{
	
	private static Log log = LogFactory.getLog(SeminariosRealizadosServiceImpl.class);
	private DAOSeminariosRealizados daoSeminariosRealizados;

	
	public void setDaoSeminariosRealizados(DAOSeminariosRealizados daoSeminariosRealizados) {
		this.daoSeminariosRealizados = daoSeminariosRealizados;
	}
	
	public void insert(SeminarioRealizadoVO seminarioRealizado)
			throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {
			daoSeminariosRealizados.insert(seminarioRealizado);	
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error creando seminario realizado", e);
			throw new ServiceException(e);
		}		
	}

	public void update(SeminarioRealizadoVO seminarioRealizado)
			throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {
			daoSeminariosRealizados.update(seminarioRealizado);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error update seminario realizado", e);
			throw new ServiceException(e);
		}		
	}

	public void delete(SeminarioRealizadoVO seminarioRealizado)
			throws es.oyssen.mrm.negocio.exceptions.ServiceException,
			es.oyssen.mrm.negocio.dao.exceptions.DAOException {
		try {			
			daoSeminariosRealizados.delete(seminarioRealizado);			
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error delete seminario realizado", e);
			throw new ServiceException(e);
		}			
	}

	
	
	public List<SeminarioRealizadoVO> findAllByPortafolio(SeminarioRealizadoVO seminarioRealizado) throws ServiceException {
		try {
			return daoSeminariosRealizados.findAllByPortafolio(seminarioRealizado);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error findAllByPortafolio seminario realizado", e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<UsuarioAnyoSeminarioVO> findAllUsersByPortafolio(SeminarioRealizadoVO seminarioRealizado) throws ServiceException {
		try {
			return daoSeminariosRealizados.findAllUsersByPortafolio(seminarioRealizado);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error findAllUsersByPortafolio seminario realizado", e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<SeminarioAsignaturaAnyoVO> findSeminariosRealizados(PortafolioVO p) throws ServiceException {
		try {
			return daoSeminariosRealizados.findSeminariosRealizados(p);
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("Error findSeminariosRealizados seminario realizado", e);
			throw new ServiceException(e.getMessage());
		}
	}
	
	
	
}
