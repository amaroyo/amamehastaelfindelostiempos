package es.oyssen.mrm.negocio.services;

import java.util.List;

import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.vo.GrupoVO;
import es.oyssen.mrm.negocio.vo.PortafolioVO;
import es.oyssen.mrm.negocio.vo.UsuarioAsignaturaVO;
import es.oyssen.mrm.negocio.vo.UsuarioVO;

public interface UsuariosService {
	
	public void insert(UsuarioVO usuario) throws ServiceException, DAOException;
	
	public void update(UsuarioVO usuario) throws ServiceException, DAOException;
	
	public void delete(UsuarioVO usuario) throws ServiceException, DAOException;
	
	public List<UsuarioVO> findAll() throws ServiceException;
	
	public List<UsuarioVO> findByGrupo(GrupoVO grupo) throws ServiceException;
	
	public UsuarioVO findById(UsuarioVO usuario) throws ServiceException;
	
	public UsuarioVO findByDni(UsuarioVO usuario) throws ServiceException;
	
	public UsuarioVO findByCorreo(UsuarioVO usuario) throws ServiceException;
	
	public UsuarioVO findByCorreoPass(UsuarioVO usuario) throws ServiceException;
	
	public UsuarioVO findByNombreApellidos(UsuarioVO usuario) throws ServiceException;

	public List<UsuarioAsignaturaVO> findAllbyAnyoAcademico(PortafolioVO p) throws ServiceException;

	public List<UsuarioAsignaturaVO> findAllbyProfesor(PortafolioVO p) throws ServiceException;

	public List<UsuarioAsignaturaVO> findAllbyProfesorDemas(PortafolioVO p)  throws ServiceException;

	public void updateGrupo(UsuarioVO usuario) throws ServiceException, DAOException;

	public List<UsuarioAsignaturaVO> findAllByAsignatura(PortafolioVO p) throws ServiceException;

	public List<UsuarioVO> findIndefinidos() throws ServiceException;


}
