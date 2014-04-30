package es.oyssen.mrm.negocio.dao.mysql;

import java.util.List;

import es.oyssen.mrm.negocio.dao.DAOBase;
import es.oyssen.mrm.negocio.dao.DAOPuntosVenta;
import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOUpdateException;
import es.oyssen.mrm.negocio.vo.CriterioPuntoVentaVO;
import es.oyssen.mrm.negocio.vo.PuntoVentaVO;


public class MySqlDAOPuntosVentaImpl extends DAOBase implements DAOPuntosVenta {

	public List<PuntoVentaVO> findByCriterio(CriterioPuntoVentaVO criterio)
			throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	public PuntoVentaVO findById(PuntoVentaVO punto) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	public void insert(PuntoVentaVO punto) throws DAOException,
			DAOInsertException {
		// TODO Auto-generated method stub
		
	}

	public void update(PuntoVentaVO punto) throws DAOException,
			DAOUpdateException {
		// TODO Auto-generated method stub
		
	}

	public void delete(PuntoVentaVO punto) throws DAOException,
			DAODeleteException {
		// TODO Auto-generated method stub
		
	}
	
}


