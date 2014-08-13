package es.oyssen.mrm.negocio.services;

import java.util.List;

import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.exceptions.ServiceException;
import es.oyssen.mrm.negocio.vo.PortafolioVO;
import es.oyssen.mrm.negocio.vo.TrabajoDeCampoInfoVO;
import es.oyssen.mrm.negocio.vo.TrabajoDeCampoVO;
import es.oyssen.mrm.negocio.vo.UsuarioAnyoSeminarioVO;
import es.oyssen.mrm.negocio.vo.UsuarioTrabajoCampoVO;

public interface TrabajosDeCampoService {
	
	public void insert(TrabajoDeCampoVO trabajoDeCampo) throws ServiceException, DAOException;
	
	public void update(TrabajoDeCampoVO trabajoDeCampo) throws ServiceException, DAOException;
	
	public void delete(TrabajoDeCampoVO trabajoDeCampo) throws ServiceException, DAOException;
	
	public List<TrabajoDeCampoVO> findAllByPortafolio(TrabajoDeCampoVO trabajoDeCampo) throws ServiceException;

	public List<TrabajoDeCampoVO> findAllByAsignatura(PortafolioVO p) throws ServiceException;

	public List<UsuarioTrabajoCampoVO> findAllByAsignaturaTrabajo(PortafolioVO p, TrabajoDeCampoVO t) throws ServiceException;

	public List<TrabajoDeCampoInfoVO> findAllNombresByAsignatura(PortafolioVO p) throws ServiceException;

	public TrabajoDeCampoInfoVO findByIDs(TrabajoDeCampoVO t) throws ServiceException;

	public void updateTrabajoCampo(TrabajoDeCampoVO tc) throws ServiceException, DAOException;

	public void updateTrabajoCampoCorreccion(TrabajoDeCampoVO tc) throws ServiceException, DAOException;

	public TrabajoDeCampoVO findByIDsTC(TrabajoDeCampoVO tc) throws ServiceException;

	public List<TrabajoDeCampoVO> findAllByIdInfo(TrabajoDeCampoInfoVO i) throws ServiceException;

	public TrabajoDeCampoVO findByPortafolioInfo(TrabajoDeCampoVO trabajo) throws ServiceException;

	public void updateIndividualDate(TrabajoDeCampoVO t) throws ServiceException, DAOException;

}
