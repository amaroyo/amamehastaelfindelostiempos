package es.oyssen.mrm.negocio.dao;

import java.util.List;

import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOUpdateException;
import es.oyssen.mrm.negocio.vo.TrabajoDeCampoVO;

public interface DAOTrabajosDeCampo {

	public void insert(TrabajoDeCampoVO trabajoDeCampo) throws DAOException, DAOInsertException;
	
	public void update(TrabajoDeCampoVO trabajoDeCampo) throws DAOException, DAOUpdateException;
	
	public void delete(TrabajoDeCampoVO trabajoDeCampo) throws DAOException, DAODeleteException;
		
	public List<TrabajoDeCampoVO> findAllByPortafolio(TrabajoDeCampoVO trabajoDeCampo) throws DAOException;
	
	
	

}
