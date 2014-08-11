package es.oyssen.mrm.negocio.dao;

import java.util.List;

import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOUpdateException;
import es.oyssen.mrm.negocio.vo.PortafolioVO;
import es.oyssen.mrm.negocio.vo.TrabajoDeCampoInfoVO;
import es.oyssen.mrm.negocio.vo.TrabajoDeCampoVO;
import es.oyssen.mrm.negocio.vo.UsuarioTrabajoCampoVO;

public interface DAOTrabajosDeCampo {

	public void insert(TrabajoDeCampoVO trabajoDeCampo) throws DAOException, DAOInsertException;
	
	public void update(TrabajoDeCampoVO trabajoDeCampo) throws DAOException, DAOUpdateException;
	
	public void delete(TrabajoDeCampoVO trabajoDeCampo) throws DAOException, DAODeleteException;
		
	public List<TrabajoDeCampoVO> findAllByPortafolio(TrabajoDeCampoVO trabajoDeCampo) throws DAOException;

	public List<TrabajoDeCampoVO> findAllByAsignatura(PortafolioVO p) throws DAOException;

	public List<UsuarioTrabajoCampoVO> findAllByAsignaturaTrabajo(PortafolioVO p, TrabajoDeCampoVO t) throws DAOException;

	public List<TrabajoDeCampoInfoVO> findAllNombresByAsignaturaTrabajo(PortafolioVO p) throws DAOException;

	public TrabajoDeCampoInfoVO findByIDs(TrabajoDeCampoVO t) throws DAOException;

	public void updateTrabajoCampo(TrabajoDeCampoVO tc) throws DAOException, DAOUpdateException;

	public void updateTrabajoCampoCorreccion(TrabajoDeCampoVO tc) throws DAOException, DAOUpdateException;

	public TrabajoDeCampoVO findByIDsTC(TrabajoDeCampoVO tc) throws DAOException;

	public List<TrabajoDeCampoVO> findAllByIdInfo(TrabajoDeCampoInfoVO i) throws DAOException;
	
	
	

}
