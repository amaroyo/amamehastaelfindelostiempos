package es.oyssen.mrm.negocio.dao;

import java.util.List;

import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOUpdateException;
import es.oyssen.mrm.negocio.vo.CriterioPuntoVentaVO;
import es.oyssen.mrm.negocio.vo.PuntoVentaVO;

public interface DAOPuntosVenta {
	
	public List<PuntoVentaVO> findByCriterio(CriterioPuntoVentaVO criterio) throws DAOException;
	
	public PuntoVentaVO findById(PuntoVentaVO punto) throws DAOException;
	
	public void insert(PuntoVentaVO punto) throws DAOException, DAOInsertException;
	
	public void update(PuntoVentaVO punto) throws DAOException, DAOUpdateException;
	
	public void delete(PuntoVentaVO punto) throws DAOException, DAODeleteException;


}
