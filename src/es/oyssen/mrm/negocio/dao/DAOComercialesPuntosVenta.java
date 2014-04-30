package es.oyssen.mrm.negocio.dao;

import java.util.List;

import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.vo.ComercialVO;
import es.oyssen.mrm.negocio.vo.PuntoVentaVO;

public interface DAOComercialesPuntosVenta {
	public List<ComercialVO> findComerciales(PuntoVentaVO puntoVenta) throws DAOException;
	public List<PuntoVentaVO> findPuntosVenta(ComercialVO comercial) throws DAOException;
	
	public void insert(PuntoVentaVO punto, ComercialVO comercial) throws DAOException, DAOInsertException;
	public void delete(PuntoVentaVO punto, ComercialVO comercial) throws DAOException, DAODeleteException;
}
