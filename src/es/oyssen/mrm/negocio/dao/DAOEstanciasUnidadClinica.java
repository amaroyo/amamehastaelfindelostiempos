package es.oyssen.mrm.negocio.dao;

import java.util.List;

import es.oyssen.mrm.negocio.dao.exceptions.DAODeleteException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOInsertException;
import es.oyssen.mrm.negocio.dao.exceptions.DAOUpdateException;
import es.oyssen.mrm.negocio.vo.EstanciaUnidadClinicaVO;

public interface DAOEstanciasUnidadClinica {
	
	public void insert(EstanciaUnidadClinicaVO estanciaUnidadClinica) throws DAOException, DAOInsertException;
	
	public void update(EstanciaUnidadClinicaVO estanciaUnidadClinica) throws DAOException, DAOUpdateException;
	
	public void delete(EstanciaUnidadClinicaVO estanciaUnidadClinica) throws DAOException, DAODeleteException;
	
	public List<EstanciaUnidadClinicaVO> findByPortafolio(EstanciaUnidadClinicaVO estanciaUnidadClinica) throws DAOException;
	
	public List<EstanciaUnidadClinicaVO> findByCentroTurno(EstanciaUnidadClinicaVO estanciaUnidadClinica) throws DAOException;
	
	
}
