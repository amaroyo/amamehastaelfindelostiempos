package es.oyssen.mrm.negocio.services;

import java.util.List;

import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.vo.PortafolioVO;
import es.oyssen.mrm.negocio.vo.UsuarioEstanciaUnidadClinicaVO;

public interface PortafoliosService {
	
	public void insert(PortafolioVO portafolio) throws ServiceException, DAOException;
	
	public void update(PortafolioVO portafolio) throws ServiceException, DAOException;
	
	public void delete(PortafolioVO portafolio) throws ServiceException, DAOException;
	
	public List<PortafolioVO> findAll(PortafolioVO portafolio) throws ServiceException;
	
	public PortafolioVO findById(PortafolioVO portafolio) throws ServiceException;
	
	public List<PortafolioVO> findByAlumno(PortafolioVO portafolio) throws ServiceException;
	
	public List<PortafolioVO> findByProfesor(PortafolioVO portafolio) throws ServiceException;
	
	public List<PortafolioVO> findByAsignatura(PortafolioVO portafolio) throws ServiceException;

	public List<PortafolioVO> findByAnyoAcademico(PortafolioVO portafolio) throws ServiceException;
	
	public List<UsuarioEstanciaUnidadClinicaVO> findUsuariosEstanciaUnidadClinica(PortafolioVO portafolio) throws ServiceException;

	public findDatosUsuarioEstanciaUnidadClinica(PortafolioVO portafolio) throws ServiceException;
	



}
