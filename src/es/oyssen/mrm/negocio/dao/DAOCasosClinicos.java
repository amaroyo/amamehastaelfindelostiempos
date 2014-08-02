package es.oyssen.mrm.negocio.dao;

import java.util.List;

import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOUpdateException;
import es.oyssen.mrm.negocio.vo.CasoClinicoVO;
import es.oyssen.mrm.negocio.vo.PortafolioVO;

public interface DAOCasosClinicos {

	public void insert(CasoClinicoVO casoClinico) throws DAOException, DAOInsertException;
	
	public void update(CasoClinicoVO casoClinico) throws DAOException, DAOUpdateException;
	
	public void delete(CasoClinicoVO casoClinico) throws DAOException, DAODeleteException;
		
	public List<CasoClinicoVO> findAllByPortafolio(CasoClinicoVO casoClinico) throws DAOException;


	public CasoClinicoVO findByIDs(CasoClinicoVO caso) throws DAOException;
	
	
	

}
