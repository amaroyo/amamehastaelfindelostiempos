package es.oyssen.mrm.negocio.dao;

import java.util.List;

import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOUpdateException;
import es.oyssen.mrm.negocio.vo.CriterioServicioVO;
import es.oyssen.mrm.negocio.vo.ServicioVO;

public interface DAOServicios {
	
	public ServicioVO findByNombre(ServicioVO estado) throws DAOException;
	
	public List<ServicioVO> findByCriterio(CriterioServicioVO criterio) throws DAOException;
	
	public ServicioVO findById(ServicioVO responsable) throws DAOException;
	
	public void insert(ServicioVO servicio) throws DAOException, DAOInsertException;
	
	public void update(ServicioVO servicio) throws DAOException, DAOUpdateException;
	
	public void delete(ServicioVO servicio) throws DAOException, DAODeleteException;
	
}
