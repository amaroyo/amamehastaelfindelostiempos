package es.oyssen.mrm.negocio.dao;

import java.util.List;

import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.vo.ComercialVO;
import es.oyssen.mrm.negocio.vo.ResponsableVO;

public interface DAOResponsablesComerciales {
	
	public List<ComercialVO> findComerciales(ResponsableVO responsable) throws DAOException;
	
	public ComercialVO findComercial(ResponsableVO responsable, ComercialVO comercial) throws DAOException;
	
	public void insert(ResponsableVO responsable, ComercialVO comercial) throws DAOException, DAOInsertException;	
	
	public void delete(ResponsableVO responsable, ComercialVO comercial) throws DAOException, DAODeleteException;

}
