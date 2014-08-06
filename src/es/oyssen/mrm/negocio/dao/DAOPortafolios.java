package es.oyssen.mrm.negocio.dao;

import java.util.List;

import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOUpdateException;
import es.oyssen.mrm.negocio.vo.CasoClinicoVO;
import es.oyssen.mrm.negocio.vo.DatosUsuarioEstanciaUnidadClinicaVO;
import es.oyssen.mrm.negocio.vo.DiarioReflexivoVO;
import es.oyssen.mrm.negocio.vo.PortafolioVO;
import es.oyssen.mrm.negocio.vo.TrabajoDeCampoVO;
import es.oyssen.mrm.negocio.vo.UsuarioEstanciaUnidadClinicaVO;
import es.oyssen.mrm.negocio.vo.UsuarioPortafolioVO;
import es.oyssen.mrm.negocio.vo.UsuarioVO;

public interface DAOPortafolios {
	
	public List<UsuarioEstanciaUnidadClinicaVO> findUsuariosEstanciaUnidadClinica(PortafolioVO portafolio) throws DAOException;
	
	public List<PortafolioVO> findAll(PortafolioVO portafolio) throws DAOException;
	
	public PortafolioVO findById(PortafolioVO portafolio) throws DAOException;
	
	public DatosUsuarioEstanciaUnidadClinicaVO findDatosUsuarioEstanciaUnidadClinica(PortafolioVO portafolio) throws DAOException;
	
	public List<PortafolioVO> findByAlumno(PortafolioVO portafolio) throws DAOException;

	public List<PortafolioVO> findByProfesor(PortafolioVO portafolio) throws DAOException;
	
	public List<PortafolioVO> findByAsignatura(PortafolioVO portafolio) throws DAOException;

	public List<PortafolioVO> findByAnyoAcademico(PortafolioVO portafolio) throws DAOException;	
	
	public void insert(PortafolioVO portafolio) throws DAOException, DAOInsertException;
	
	public void update(PortafolioVO portafolio) throws DAOException, DAOUpdateException;
	
	public void delete(PortafolioVO portafolio) throws DAOException, DAODeleteException;

	public void updateEstancia(DatosUsuarioEstanciaUnidadClinicaVO data) throws DAOException, DAOUpdateException;

	public List<UsuarioPortafolioVO> findUsuariosByAsignatura(PortafolioVO p) throws DAOException;

	public List<TrabajoDeCampoVO> findTrabajosByPortafolio(PortafolioVO p) throws DAOException;

	public List<CasoClinicoVO> findCasosByPortafolio(PortafolioVO p) throws DAOException;

	public List<DiarioReflexivoVO> findDiariosByPortafolio(PortafolioVO p) throws DAOException;

	public PortafolioVO findByAlumnoAsignatura(PortafolioVO p) throws DAOException;

	public UsuarioVO findAlumnoByPortafolio(PortafolioVO p) throws DAOException;

	
	

}
