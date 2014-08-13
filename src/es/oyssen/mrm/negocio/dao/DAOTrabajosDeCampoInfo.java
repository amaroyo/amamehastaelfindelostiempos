package es.oyssen.mrm.negocio.dao;



import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOUpdateException;
import es.oyssen.mrm.negocio.vo.RubricaVO;
import es.oyssen.mrm.negocio.vo.TrabajoDeCampoInfoVO;
import es.oyssen.mrm.negocio.vo.TrabajoDeCampoVO;

public interface DAOTrabajosDeCampoInfo {

	public String insert(TrabajoDeCampoInfoVO trabajoInfo) throws DAOException, DAOInsertException;
	
	public void update(TrabajoDeCampoInfoVO trabajoInfo) throws DAOException, DAOUpdateException;
	
	public void delete(TrabajoDeCampoInfoVO trabajoInfo) throws DAOException, DAODeleteException;
		
	public TrabajoDeCampoInfoVO findById(TrabajoDeCampoInfoVO trabajoInfo) throws DAOException;

	public void updateSimple(TrabajoDeCampoInfoVO t) throws DAOException, DAOUpdateException;

	public void updateDates(TrabajoDeCampoVO t) throws DAOException, DAOUpdateException;
	


}
