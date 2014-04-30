package es.oyssen.mrm.negocio.dao.mysql;

import java.util.List;

import es.oyssen.mrm.negocio.dao.DAOBase;
import es.oyssen.mrm.negocio.dao.DAOComercialesPuntosVenta;
import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.vo.ComercialVO;
import es.oyssen.mrm.negocio.vo.PuntoVentaVO;

public class MySqlDAOComercialesPuntosVentaImpl extends DAOBase implements DAOComercialesPuntosVenta {

	public List<ComercialVO> findComerciales(PuntoVentaVO puntoVenta)
			throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<PuntoVentaVO> findPuntosVenta(ComercialVO comercial)
			throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	public void insert(PuntoVentaVO punto, ComercialVO comercial)
			throws DAOException, DAOInsertException {
		// TODO Auto-generated method stub
		
	}

	public void delete(PuntoVentaVO punto, ComercialVO comercial)
			throws DAOException, DAODeleteException {
		// TODO Auto-generated method stub
		
	}
	
	
}


