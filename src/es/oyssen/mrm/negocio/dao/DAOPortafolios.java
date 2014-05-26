package es.oyssen.mrm.negocio.dao;

import java.util.List;

import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOUpdateException;
import es.oyssen.mrm.negocio.vo.PortafolioVO;

public interface DAOPortafolios {
	
	public List<PortafolioVO> findAll(PortafolioVO portafolio) throws DAOException;
	
	public PortafolioVO findById(PortafolioVO portafolio) throws DAOException;
	
	public List<PortafolioVO> findByAlumno(PortafolioVO portafolio) throws DAOException;

	public List<PortafolioVO> findByProfesor(PortafolioVO portafolio) throws DAOException;
	
	public List<PortafolioVO> findByAsignatura(PortafolioVO portafolio) throws DAOException;

	public List<PortafolioVO> findByAnyoAcademico(PortafolioVO portafolio) throws DAOException;	
	
	public void insert(PortafolioVO portafolio) throws DAOException, DAOInsertException;
	
	public void update(PortafolioVO portafolio) throws DAOException, DAOUpdateException;
	
	public void delete(PortafolioVO portafolio) throws DAOException, DAODeleteException;

}
