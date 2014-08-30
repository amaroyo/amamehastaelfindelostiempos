package es.oyssen.mrm.negocio.dao;

import java.util.List;

import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOUpdateException;
import es.oyssen.mrm.negocio.vo.PortafolioVO;
import es.oyssen.mrm.negocio.vo.SeminarioAsignaturaAnyoVO;
import es.oyssen.mrm.negocio.vo.SeminarioAsignaturaVO;
import es.oyssen.mrm.negocio.vo.SeminarioRealizadoVO;
import es.oyssen.mrm.negocio.vo.UsuarioAnyoSeminarioVO;

public interface DAOSeminariosRealizados {

	public void insert(SeminarioRealizadoVO seminarioRealizado) throws DAOException, DAOInsertException;
	
	public void update(SeminarioRealizadoVO seminarioRealizado) throws DAOException, DAOUpdateException;
	
	public void delete(SeminarioRealizadoVO seminarioRealizado) throws DAOException, DAODeleteException;
		
	public List<SeminarioRealizadoVO> findAllByPortafolio(SeminarioRealizadoVO seminarioRealizado) throws DAOException;

	public List<UsuarioAnyoSeminarioVO> findAllUsersByPortafolio(SeminarioRealizadoVO seminarioRealizado, PortafolioVO p) throws DAOException;

	public List<SeminarioAsignaturaAnyoVO> findSeminariosRealizados(PortafolioVO p) throws DAOException;

	public List<SeminarioAsignaturaVO> findSeminariosPendientes(PortafolioVO p) throws DAOException;

	
	
	
	

}
