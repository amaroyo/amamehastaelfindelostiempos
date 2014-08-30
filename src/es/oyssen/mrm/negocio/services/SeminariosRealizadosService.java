package es.oyssen.mrm.negocio.services;

import java.util.List;

import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.vo.PortafolioVO;
import es.oyssen.mrm.negocio.vo.SeminarioAsignaturaAnyoVO;
import es.oyssen.mrm.negocio.vo.SeminarioAsignaturaVO;
import es.oyssen.mrm.negocio.vo.SeminarioRealizadoVO;
import es.oyssen.mrm.negocio.vo.UsuarioAnyoSeminarioVO;

public interface SeminariosRealizadosService {
	
	public void insert(SeminarioRealizadoVO seminarioRealizado) throws ServiceException, DAOException;
	
	public void update(SeminarioRealizadoVO seminarioRealizado) throws ServiceException, DAOException;
	
	public void delete(SeminarioRealizadoVO seminarioRealizado) throws ServiceException, DAOException;
	
	public List<SeminarioRealizadoVO> findAllByPortafolio(SeminarioRealizadoVO seminarioRealizado) throws ServiceException;

	public List<UsuarioAnyoSeminarioVO> findAllUsersByPortafolio(SeminarioRealizadoVO sr, PortafolioVO p) throws ServiceException;

	public List<SeminarioAsignaturaAnyoVO> findSeminariosRealizados(PortafolioVO p) throws ServiceException;

	public List<SeminarioAsignaturaVO> findSeminariosPendientes(PortafolioVO p) throws ServiceException;

}
