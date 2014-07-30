package es.oyssen.mrm.negocio.services;

import java.util.List;

import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.vo.CasoClinicoVO;
import es.oyssen.mrm.negocio.vo.DatosUsuarioEstanciaUnidadClinicaVO;
import es.oyssen.mrm.negocio.vo.DiarioReflexivoVO;
import es.oyssen.mrm.negocio.vo.PortafolioVO;
import es.oyssen.mrm.negocio.vo.TrabajoDeCampoVO;
import es.oyssen.mrm.negocio.vo.UsuarioAnyoSeminarioVO;
import es.oyssen.mrm.negocio.vo.UsuarioEstanciaUnidadClinicaVO;
import es.oyssen.mrm.negocio.vo.UsuarioPortafolioVO;
import es.oyssen.mrm.negocio.vo.UsuarioTrabajoCampoVO;
import es.oyssen.mrm.negocio.vo.UsuarioVO;

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

	public DatosUsuarioEstanciaUnidadClinicaVO findDatosUsuarioEstanciaUnidadClinica(PortafolioVO portafolio) throws ServiceException;

	public void updateEstancia(DatosUsuarioEstanciaUnidadClinicaVO data) throws ServiceException, DAOException;

	public List<UsuarioPortafolioVO> findUsuariosByAsignatura(PortafolioVO p) throws ServiceException;

	public List<TrabajoDeCampoVO> findTrabajosByPortafolio(PortafolioVO p, TrabajoDeCampoVO t) throws ServiceException;

	public List<CasoClinicoVO> findCasosByPortafolio(PortafolioVO p) throws ServiceException;

	public List<DiarioReflexivoVO> findDiariosByPortafolio(PortafolioVO p) throws ServiceException;
	



}
